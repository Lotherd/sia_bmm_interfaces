package trax.aero.utils;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import trax.aero.logger.LogManager;

public class DataSourceClient {
    
    private static final Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    
    /**
     * Get database connection
     * 
     * @return Connection object
     * @throws Exception if connection cannot be established
     */
    public static Connection getConnection() throws Exception {
        Connection connection;
        Context ctx;
        
        try {
            ctx = new InitialContext();
            
            DataSource ds = null;
            
            if (System.getProperty("jboss.server.config.dir") != null) {
                ds = (DataSource) ctx.lookup("java:/TraxStandaloneDS");
            } else {
                ds = (DataSource) ctx.lookup("TraxStandaloneDS");
            }
            
            connection = ds.getConnection();
            
        } catch (Exception e) {
            logger.severe("An error occurred trying to connect to the DataSource: TraxStandaloneDS");
            throw new Exception("\nGetting error trying to connect to the datasource. " + 
                              "\n error: " + ErrorType.INTERNAL_SERVER_ERROR.getValue());
        }
        
        return connection;
    }
}