package trax.aero.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import trax.aero.logger.LogManager;

/**
 * Web listener to start and stop the scheduler
 */
@WebListener
public class AttendanceInfoScheduler implements ServletContextListener {
    
    private static Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    private ScheduledExecutorService scheduledExecutor;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing Clock On/Off scheduler");
        
        scheduledExecutor = Executors.newScheduledThreadPool(1);
        
        // Schedule task
        int interval = Integer.parseInt(System.getProperty("AttendaceInfo_interval", "60"));
        scheduledExecutor.scheduleAtFixedRate(new AttendanceTask(), 30, interval, TimeUnit.SECONDS);
        
        logger.info("Clock On/Off scheduler initialized with interval: " + interval + " seconds");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Clock On/Off scheduler");
        
        if (scheduledExecutor != null && !scheduledExecutor.isShutdown()) {
            scheduledExecutor.shutdown();
        }
        
        logger.info("Clock On/Off scheduler shutdown complete");
    }
}