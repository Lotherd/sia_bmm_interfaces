package trax.aero.start;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import trax.aero.logger.LogManager;
import trax.aero.utils.RunAble;

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
public class Start {

    static Logger logger = LogManager.getLogger("SlotsESlot_I31");
    private ScheduledExecutorService scheduledServ;
    RunAble timer = null;

    @PostConstruct
    public void start() {
        timer = new RunAble();
        
        if (scheduledServ == null) {
            int scheduledPoolSize = 1;
            logger.info("Creating default Scheduled Executor Service [poolSize =" + String.valueOf(scheduledPoolSize) + "]");
            this.scheduledServ = Executors.newScheduledThreadPool(scheduledPoolSize);
        }
        scheduledServ.scheduleAtFixedRate(timer, 30, Long.parseLong(System.getProperty("SlotsESlot_interval")), TimeUnit.SECONDS);
    }
    
    @PreDestroy
    public void stop() {
        if(!scheduledServ.isShutdown()) {
            scheduledServ.shutdown();
        }
    }
}