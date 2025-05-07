package trax.aero.start;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import trax.aero.logger.LogManager;
import trax.aero.utils.RunAble;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Startup
@Singleton
public class Start
{

	private ScheduledExecutorService scheduledServ;
	RunAble timer = null;
	Logger logger = LogManager.getLogger("ShiftInfo_I02");
	
	@PostConstruct
    public void start() {
        timer = new RunAble();
        
        if (scheduledServ == null) {
            int scheduledPoolSize = 1;
            logger.info("Creating default Scheduled Executor Service [poolSize =" + String.valueOf(scheduledPoolSize) + "]");
            this.scheduledServ = Executors.newScheduledThreadPool(scheduledPoolSize);
        }
        
        
        String scheduleType = System.getProperty("ShiftInfo_scheduleType", "interval");
        
        if ("daily".equals(scheduleType)) {
            
            int hour = Integer.parseInt(System.getProperty("ShiftInfo_hour", "2"));
            int minute = Integer.parseInt(System.getProperty("ShiftInfo_minute", "0"));
            
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(0);
            
            
            if (now.isAfter(nextRun)) {
                nextRun = nextRun.plusDays(1);
            }
            
            
            long initialDelay = Duration.between(now, nextRun).getSeconds();
            
            
            scheduledServ.scheduleAtFixedRate(timer, initialDelay, 86400, TimeUnit.SECONDS);
            
            logger.info("Shift data export scheduled to run at " + hour + ":" + minute + " daily. First run in " + initialDelay + " seconds");
            
        } else if ("interval".equals(scheduleType)) {
            
            long interval = Long.parseLong(System.getProperty("ShiftInfo_interval", "3600"));
            long initialDelay = Long.parseLong(System.getProperty("ShiftInfo_initialDelay", "30"));
            
            scheduledServ.scheduleAtFixedRate(timer, initialDelay, interval, TimeUnit.SECONDS);
            
            logger.info("Shift data export scheduled to run every " + interval + " seconds. First run in " + initialDelay + " seconds");
        }
    }
    
    @PreDestroy
    public void stop() {
        if (!scheduledServ.isShutdown()) {
            scheduledServ.shutdown();
        }
    }
}