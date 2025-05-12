package trax.aero.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import trax.aero.logger.LogManager;

public class AttendanceInfoData {
	
    private static final Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    
    private EntityManagerFactory factory;
    private EntityManager em;
    private Connection connection;
    private HumanicaService humanicaService;
    
    private final String interfaceType = "I03";
    private final String resendUrl = System.getProperty("AttendaceInfo_URL");
    
    /**
     * Constructor
     */
    public AttendanceInfoData() {
        try {
            // Initialize connection
            this.connection = DataSourceClient.getConnection();
            logger.info("Connection established successfully: " + !this.connection.isClosed());
            
            // Initialize EntityManager
            factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
            em = factory.createEntityManager();
            
            // Initialize HumanicaService
            humanicaService = new HumanicaService(resendUrl);
            
        } catch (Exception e) {
            logger.severe("Error initializing ClockOnOffDataService: " + e.getMessage());
            AttendanceInfoController.addError(e.getMessage());
        }
    }
    
    /**
     * Process the clock data
     */
    public String processClockData(Import clockData) throws Exception {
        String result = "OK";
        boolean isOkay = true;
        
        logger.info("Processing clock data");
        
        try {
            // Validate the data
            ValidationUtils.validateClockData(clockData.getMessage().getHumanica());
            
            // Convert Humanica data to Punch
            Punch punch = convertHumanicaToPunch(clockData.getMessage().getHumanica());
            
            // Add clock in/out record
            addClockInClockOut(punch);
            
            // If it's a clock out, update task card status
            if (punch.getPunchType().equalsIgnoreCase("OUT")) {
                changeTaskCardStatus(clockData.getMessage().getHumanica());
            }
            
            return result;
            
        } catch (Exception e) {
            logger.severe("Error processing clock data: " + e.getMessage());
            AttendanceInfoController.addError(e.getMessage());
            
            // Request resend if processing failed
            if (clockData != null && clockData.getMessage() != null && 
                clockData.getMessage().getHumanica() != null) {
                
                Import resend = new Import();
                Message message = new Message();
                
                resend.setMessage(message);
                resend.getMessage().setResendRequest(clockData.getMessage().getHumanica());
                
                boolean success = humanicaService.sendResendRequest(resend);
                
                if (!success) {
                    String errorMsg = "Unable to send resend request for punch with Seq No: " + 
                                     clockData.getMessage().getHumanica().getSeqNo().toString();
                    logger.severe(errorMsg);
                    AttendanceInfoController.addError(errorMsg);
                } else {
                    logger.info("Resend request sent successfully for Seq No: " + 
                               clockData.getMessage().getHumanica().getSeqNo().toString());
                }
            }
            
            throw e;
        }
    }
    
    /**
     * Add clock in/out record to the database
     */
    private void addClockInClockOut(Punch p) throws Exception {
        logger.info("Inserting punches...");
        PreparedStatement ps = null;
        
        try {
            logger.info("Preparing the connection and the data to insert the punches...");
            
            DateTime punchDate = new DateTime(p.getPunchDateTime());
            String[] result = new String[2];
            
            // Extracting the Date from the DateTime
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            String workDateString = sdFormat.format(new java.util.Date(punchDate.getMillis()));
            
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            Date punchD = formatter.parseDateTime(workDateString);
            
            String costCenter;
            String group;
            try {
                costCenter = getCostCenterByEmployee(p.getEmployeeId());
                group = getGroupByCostCenterEmployee(costCenter);
                result = getLocationSiteByGroup(group);
            } catch (Exception e) {
                logger.severe("Error retrieving employee location information: " + e.getMessage());
                result[0] = "";
                result[1] = "";
                costCenter = "";
                group = "";
            }
            
            // Delete existing clock in/out record
            deleteClockInClockOut(p, result[0], result[1]);
            
            // Add new clock in record if this is a clock in
            if ("IN".equalsIgnoreCase(p.getPunchType())) {
                String q = "INSERT INTO employee_attendance_current " +
                           "(EMPLOYEE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, START_TIME, LOCATION, SITE, TYPE_OFF) " +
                           "VALUES (?, 'TRAXIFACE', sysdate, 'TRAXIFACE', sysdate, ?, ?, ?, ?)";
                
                ps = connection.prepareStatement(q);
                ps.setString(1, p.getEmployeeId());
                ps.setDate(2, new java.sql.Date(punchDate.getMillis()));
                ps.setString(3, result[0] != null ? result[0] : "");
                ps.setString(4, result[1] != null ? result[1] : "");
                ps.setString(5, p.getPunchType());
                ps.executeUpdate();
            }
            
            // Add log record
            addEmployeeAttendanceLog(p, result[0], result[1]);
            
            logger.info("The punches have been created successfully");
            
        } catch (SQLException sqle) {
            logger.severe("SQL error inserting punches: " + sqle.getMessage());
            throw new ClockOnOffException("Database error while processing clock data: " + sqle.getMessage());
        } catch (Exception e) {
            logger.severe("Error inserting punches: " + e.getMessage());
            throw new ClockOnOffException("Error processing clock data: " + e.getMessage());
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statement: " + e.getMessage());
            }
        }
    }

}
