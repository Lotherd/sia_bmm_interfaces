package trax.aero.controller;

import java.util.logging.Logger;

import trax.aero.logger.LogManager;

public class AttendanceInfoController {

	private static Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    private static StringBuilder errors = new StringBuilder();
    
    /**
     * Add error message to the log
     * 
     * @param error Error message
     */
    public static void addError(String error) {
        errors.append(error).append(System.lineSeparator()).append(System.lineSeparator());
    }
    
    /**
     * Get all error messages
     * 
     * @return String containing all error messages
     */
    public static String getErrors() {
        return errors.toString();
    }
    
    /**
     * Clear all error messages
     */
    public static void clearErrors() {
        errors = new StringBuilder();
    }
}
