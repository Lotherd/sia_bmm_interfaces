package trax.aero.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import trax.aero.controller.AttendanceInfoController;
import trax.aero.exception.CustomizeHandledException;
import trax.aero.logger.LogManager;
import trax.aero.pojo.Humanica;

public class ValidationUtils {

    private static final Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    
    /**
     * Validate Humanica data
     * 
     * @param humanica The Humanica data to validate
     * @throws ClockOnOffException If validation fails
     */
    public static void validateClockData(Humanica humanica) throws CustomizeHandledException {
        try {
            String result = validatePunchesEntries(humanica);
            
            if (!"OK".equals(result)) {
                logger.severe(result);
                throw new CustomizeHandledException(result);
            }
        } catch (CustomizeHandledException e) {
            throw e;
        } catch (Exception e) {
            logger.severe("Error validating clock data: " + e.getMessage());
            throw new CustomizeHandledException("Error validating clock data: " + e.getMessage());
        }
    }
    
    /**
     * Validate punch entries
     * 
     * @param humanica The Humanica data to validate
     * @return "OK" if validation passes, error message otherwise
     */
    private static String validatePunchesEntries(Humanica humanica) {
        String isOkay = "OK";
        
        // Validate cost center
        if (humanica.getCostCentre() == null || humanica.getCostCentre().isEmpty()) {
            isOkay = "Error: Cost Centre is null or empty";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate sequence number
        if (humanica.getSeqNo() == null) {
            isOkay = "Error: Sequence Number is null";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate status
        if (humanica.getStatus() == null || humanica.getStatus().isEmpty()) {
            isOkay = "Error: Status is null or empty";
            AttendanceInfoController.addError(isOkay);
        } else if (!humanica.getStatus().equalsIgnoreCase("N") && !humanica.getStatus().equalsIgnoreCase("R")) {
            isOkay = "Status: " + humanica.getStatus() + " Error: Status is not N or R";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate message type
        if (humanica.getMsgType() == null || humanica.getMsgType().isEmpty()) {
            isOkay = "Error: Message Type is null or empty";
            AttendanceInfoController.addError(isOkay);
        } else if (!humanica.getMsgType().equalsIgnoreCase("ClockIn") && !humanica.getMsgType().equalsIgnoreCase("ClockOut")) {
            isOkay = "Message Type: " + humanica.getMsgType() + " Error: Message Type is not ClockIn or ClockOut";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate clock in time
        if ((humanica.getClkInTime() == null || humanica.getClkInTime().isEmpty() || !isValidDate(humanica.getClkInTime())) 
            && humanica.getMsgType() != null && humanica.getMsgType().equalsIgnoreCase("ClockIn")) {
            isOkay = humanica.getClkInTime() + " Error: Clock in Time is null or empty or invalid";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate clock out time
        if ((humanica.getClkOutTime() == null || humanica.getClkOutTime().isEmpty() || !isValidDate(humanica.getClkOutTime())) 
            && humanica.getMsgType() != null && humanica.getMsgType().equalsIgnoreCase("ClockOut")) {
            isOkay = humanica.getClkOutTime() + " Error: Clock Out Time is null or empty or invalid";
            AttendanceInfoController.addError(isOkay);
        }
        
        // Validate employee exists
        if (humanica.getStaffNo() != null && !humanica.getStaffNo().isEmpty()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = null;
            
            try {
                con = DataSourceClient.getConnection();
                
                String query = "SELECT RELATION_CODE FROM RELATION_MASTER WHERE RELATION_CODE = ? AND RELATION_TRANSACTION = 'EMPLOYEE'";
                
                ps = con.prepareStatement(query);
                ps.setString(1, humanica.getStaffNo());
                
                rs = ps.executeQuery();
                
                if (!rs.next()) {
                    isOkay = "Employee: " + humanica.getStaffNo() + " Error: Employee does not exist";
                    logger.severe(isOkay);
                    AttendanceInfoController.addError(isOkay);
                }
            } catch (Exception e) {
                logger.severe("Error validating employee: " + e.getMessage());
                isOkay = "An error occurred when validating user: Employee = " + humanica.getStaffNo();
                AttendanceInfoController.addError(isOkay);
            } finally {
                try {
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                    if (ps != null && !ps.isClosed()) {
                        ps.close();
                    }
                    if (con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException e) {
                    logger.severe("Error closing database resources: " + e.getMessage());
                }
            }
        } else {
            isOkay = "Error: Staff number is null or empty";
            AttendanceInfoController.addError(isOkay);
        }
        
        return isOkay;
    }
    
    /**
     * Check if a date string is valid
     * 
     * @param dateStr The date string to validate
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}