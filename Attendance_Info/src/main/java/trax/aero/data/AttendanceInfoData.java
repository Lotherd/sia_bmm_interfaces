package trax.aero.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import trax.aero.controller.AttendanceInfoController;
import trax.aero.exception.CustomizeHandledException;
import trax.aero.logger.LogManager;
import trax.aero.model.InterfaceLockMaster;
import trax.aero.pojo.Humanica;
import trax.aero.pojo.Import;
import trax.aero.pojo.Message;
import trax.aero.pojo.Punch;
import trax.aero.service.HumanicaService;
import trax.aero.utils.DataSourceClient;
import trax.aero.utils.ValidationUtils;

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
            String workDateString = sdFormat.format(new Date(punchDate.getMillis()));
            
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime punchD = formatter.parseDateTime(workDateString);
            
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
            throw new CustomizeHandledException("Database error while processing clock data: " + sqle.getMessage());
        } catch (Exception e) {
            logger.severe("Error inserting punches: " + e.getMessage());
            throw new CustomizeHandledException("Error processing clock data: " + e.getMessage());
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
    
    /**
     * Delete existing clock in/out record
     */
    private void deleteClockInClockOut(Punch punch, String location, String site) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String q = "DELETE FROM employee_attendance_current WHERE EMPLOYEE = ?";
            ps = connection.prepareStatement(q);
            ps.setString(1, punch.getEmployeeId());
            ps.executeUpdate();
            
            logger.info("Current employee attendance records have been removed for employee: " + punch.getEmployeeId());
        } catch (SQLException sqle) {
            logger.severe("SQL error deleting existing punch: " + sqle.getMessage());
            throw new CustomizeHandledException("Database error while removing existing clock record: " + sqle.getMessage());
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
    
    /**
     * Add employee attendance log record
     */
    private void addEmployeeAttendanceLog(Punch punch, String location, String site) throws Exception {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement checkDuplicates = null;
        
        try {
            // Check for duplicate records
            String checkQuery = "SELECT COUNT(*) FROM employee_attendance_log " +
                              "WHERE employee = ? AND type_off = ? " +
                              "AND TRUNC(start_time) = TRUNC(?) " +
                              "AND ABS(TO_NUMBER(TO_CHAR(start_time, 'HH24MISS')) - TO_NUMBER(TO_CHAR(?, 'HH24MISS'))) < 1000";
                              
            DateTime punchDateTime = new DateTime(punch.getPunchDateTime());
            
            checkDuplicates = connection.prepareStatement(checkQuery);
            checkDuplicates.setString(1, punch.getEmployeeId());
            checkDuplicates.setString(2, punch.getPunchType());
            checkDuplicates.setDate(3, new java.sql.Date(punchDateTime.getMillis()));
            checkDuplicates.setDate(4, new java.sql.Date(punchDateTime.getMillis()));
            
            ResultSet rs = checkDuplicates.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                logger.info("Skipping duplicate punch record for employee: " + punch.getEmployeeId() + 
                          ", type: " + punch.getPunchType() + 
                          ", time: " + punch.getPunchDateTime());
                return;
            }
            
            // Extract date from datetime
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            String punchDateString = sdFormat.format(new java.util.Date(punchDateTime.getMillis()));
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime punchDate = formatter.parseDateTime(punchDateString);
            
            // Get transaction number for this record
            BigDecimal transactionNo = getTransactionNo();
            
            // Insert new log record
            String q = "INSERT INTO employee_attendance_log " +
                      "(transaction_no, transaction_type, employee, start_time, created_by, created_date, " +
                      "location, site, start_date, type_off, \"GROUP\") " +
                      "VALUES (?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, 'testGroup')";
            
            ps = connection.prepareStatement(q);
            ps.setBigDecimal(1, transactionNo);
            ps.setString(2, "LOG");
            ps.setString(3, punch.getEmployeeId());
            ps.setDate(4, new java.sql.Date(punchDateTime.getMillis()));
            ps.setString(5, "TRAXIFACE");
            ps.setString(6, location != null ? location : "");
            ps.setString(7, site != null ? site : "");
            ps.setDate(8, new java.sql.Date(punchDate.getMillis()));
            ps.setString(9, punch.getPunchType());
            ps.executeUpdate();
            
            // Update end time if this is a clock out
            if ("OUT".equalsIgnoreCase(punch.getPunchType())) {
                // 1. First update the current OUT record with END_DATE and END_TIME
                String updateOutQuery = "UPDATE EMPLOYEE_ATTENDANCE_LOG " +
                                       "SET END_DATE = ?, END_TIME = ? " +
                                       "WHERE TRANSACTION_NO = ?";
                                       
                ps1 = connection.prepareStatement(updateOutQuery);
                ps1.setDate(1, new java.sql.Date(punchDate.getMillis()));
                ps1.setTimestamp(2, new java.sql.Timestamp(punchDateTime.getMillis()));
                ps1.setBigDecimal(3, transactionNo);
                
                int outRowsUpdated = ps1.executeUpdate();
                logger.info("Updated " + outRowsUpdated + " OUT record(s) with end time");
                ps1.close();
                
                // 2. Find and update the most recent IN record for this employee
                String findInQuery = "SELECT TRANSACTION_NO FROM EMPLOYEE_ATTENDANCE_LOG " +
                                   "WHERE EMPLOYEE = ? AND TYPE_OFF = 'IN' " +
                                   "ORDER BY CREATED_DATE DESC FETCH FIRST 1 ROW ONLY";
                
                ps1 = connection.prepareStatement(findInQuery);
                ps1.setString(1, punch.getEmployeeId());
                ResultSet inRs = ps1.executeQuery();
                
                if (inRs.next()) {
                    BigDecimal inTransactionNo = inRs.getBigDecimal("TRANSACTION_NO");
                    ps1.close();
                    
                    // Update the IN record with END_DATE and END_TIME
                    String updateInQuery = "UPDATE EMPLOYEE_ATTENDANCE_LOG " +
                                         "SET END_DATE = ?, END_TIME = ? " +
                                         "WHERE TRANSACTION_NO = ?";
                    
                    ps1 = connection.prepareStatement(updateInQuery);
                    ps1.setDate(1, new java.sql.Date(punchDate.getMillis()));
                    ps1.setTimestamp(2, new java.sql.Timestamp(punchDateTime.getMillis()));
                    ps1.setBigDecimal(3, inTransactionNo);
                    
                    int inRowsUpdated = ps1.executeUpdate();
                    logger.info("Updated " + inRowsUpdated + " IN record(s) with end time");
                }
                inRs.close();
            }
            
            logger.info("Attendance log created for employee: " + punch.getEmployeeId() + 
                      ", type: " + punch.getPunchType() + 
                      ", location: " + location + 
                      ", site: " + site);
            
        } catch (SQLException sqle) {
            logger.severe("SQL error creating attendance log: " + sqle.getMessage());
            throw new CustomizeHandledException("Database error while creating attendance log: " + sqle.getMessage());
        } finally {
            try {
                if (checkDuplicates != null && !checkDuplicates.isClosed()) {
                    checkDuplicates.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (ps1 != null && !ps1.isClosed()) {
                    ps1.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statements: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get transaction number for attendance log
     */
    private BigDecimal getTransactionNo() throws Exception {
        CallableStatement cstmt = null;
        try {
            cstmt = connection.prepareCall("{?=call PKG_APPLICATION_GF.gf_config_number(?)}");
            cstmt.registerOutParameter(1, Types.BIGINT);
            cstmt.setString(2, "EMPATTSEQ");
            cstmt.executeUpdate();
            return cstmt.getBigDecimal(1);
        } catch (SQLException sqle) {
            logger.severe("Error getting transaction number: " + sqle.getMessage());
            throw new CustomizeHandledException("Error getting sequence number: " + sqle.getMessage());
        } finally {
            try {
                if (cstmt != null && !cstmt.isClosed()) {
                    cstmt.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing callable statement: " + e.getMessage());
            }
        }
    }
    
    /**
     * Change task card status when employee clocks out
     */
    private void changeTaskCardStatus(Humanica humanica) throws Exception {
        logger.info("Setting Task Card work Status");
        
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        
        try {
            // Query to find employee's active task cards
            String sqlEmployee = 
                "SELECT wtcwip.category, wtcwip.task_card_pn, wtcwip.task_card_pn_sn, " +
                "wtcwip.task_card, WTCWIP.WO, wtcwip.task_card_item, WTCWIP.AC, wtcwip.employee " +
                "FROM wo_task_card_work_in_progress WTCWIP WHERE employee = ?";
            
            String sqlItem = 
                "SELECT mechanic, mechanic_status FROM wo_task_card_item " +
                "WHERE wo = ? AND task_card = ? AND task_card_item = ? AND " +
                "task_card_pn = ? AND task_card_pn_sn = ? AND ac = ?";
            
            pstmt1 = connection.prepareStatement(sqlEmployee);
            pstmt2 = connection.prepareStatement(sqlItem);
            
            pstmt1.setString(1, humanica.getStaffNo());
            rs = pstmt1.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    logger.info("Processing task card: " + rs.getString(4));
                    
                    // Update task card status
                    // Implementation would call REST APIs to update task cards
                    // This is a placeholder for the actual implementation
                }
            }
        } catch (Exception e) {
            logger.severe("Error changing task card status: " + e.getMessage());
            throw new CustomizeHandledException("Error updating task card status: " + e.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (pstmt1 != null && !pstmt1.isClosed()) {
                    pstmt1.close();
                }
                if (pstmt2 != null && !pstmt2.isClosed()) {
                    pstmt2.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statements: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get cost center by employee ID
     */
    private String getCostCenterByEmployee(String employee) throws Exception {
        logger.info("Getting cost center for employee: " + employee);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String costCenter = "";
        
        try {
            String query = "SELECT cost_center FROM RELATION_MASTER " +
                          "WHERE relation_code = ? AND RELATION_TRANSACTION = 'EMPLOYEE' AND rownum = 1";
            
            ps = connection.prepareStatement(query);
            ps.setString(1, employee);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                costCenter = rs.getString(1);
            }
            
            return costCenter;
        } catch (SQLException sqle) {
            logger.severe("SQL error getting cost center: " + sqle.getMessage());
            throw new CustomizeHandledException("Error retrieving cost center: " + sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get group by cost center
     */
    private String getGroupByCostCenterEmployee(String costCenter) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String group = "";
        
        try {
            String query = "SELECT \"GROUP\" FROM SITE_GROUP_MASTER WHERE cost_centre = ?";
            
            ps = connection.prepareStatement(query);
            ps.setString(1, costCenter);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                group = rs.getString(1);
            }
            
            return group;
        } catch (SQLException sqle) {
            logger.severe("SQL error getting group: " + sqle.getMessage());
            throw new CustomizeHandledException("Error retrieving group: " + sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get location and site by group
     */
    private String[] getLocationSiteByGroup(String group) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] locationSite = new String[2];
        
        try {
            String query = "SELECT LOCATION, SITE FROM EMPLOYEE_SCHEDULE_GROUP WHERE \"GROUP\" = ?";
            
            ps = connection.prepareStatement(query);
            ps.setString(1, group);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                locationSite[0] = rs.getString(1);
                locationSite[1] = rs.getString(2);
            }
            
            return locationSite;
        } catch (SQLException sqle) {
            logger.severe("SQL error getting location/site: " + sqle.getMessage());
            throw new CustomizeHandledException("Error retrieving location/site: " + sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.severe("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    /**
     * Convert Humanica object to Punch object
     */
    private Punch convertHumanicaToPunch(Humanica humanica) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        
        Punch punch = new Punch();
        
        if (humanica.getStaffNo() != null && !humanica.getStaffNo().isEmpty()) {
            punch.setEmployeeId(humanica.getStaffNo());
        }
        
        if (humanica.getMsgType() != null && !humanica.getMsgType().isEmpty()) {
            if ("ClockIn".equalsIgnoreCase(humanica.getMsgType())) {
                try {
                    if (humanica.getClkInTime() != null && !humanica.getClkInTime().isEmpty()) {
                        try {
                            date = formatter.parse(humanica.getClkInTime());
                        } catch (ParseException e) {
                            logger.warning("Could not parse clock in time: " + humanica.getClkInTime() + ". Using current time.");
                            date = new Date();
                        }
                        punch.setPunchDateTime(date);
                    } else {
                        punch.setPunchDateTime(new Date());
                    }
                    
                    punch.setPunchType("IN");
                } catch (Exception e) {
                    logger.severe("Error processing ClockIn time: " + e.getMessage());
                    throw new CustomizeHandledException("Error processing ClockIn time: " + e.getMessage());
                }
            } else if ("ClockOut".equalsIgnoreCase(humanica.getMsgType())) {
                try {
                    if (humanica.getClkOutTime() != null && !humanica.getClkOutTime().isEmpty()) {
                        try {
                            date = formatter.parse(humanica.getClkOutTime());
                        } catch (ParseException e) {
                            logger.warning("Could not parse clock out time: " + humanica.getClkOutTime() + ". Using current time.");
                            date = new Date();
                        }
                        punch.setPunchDateTime(date);
                    } else {
                        punch.setPunchDateTime(new Date());
                    }
                    
                    punch.setPunchType("OUT");
                } catch (Exception e) {
                    logger.severe("Error processing ClockOut time: " + e.getMessage());
                    throw new CustomizeHandledException("Error processing ClockOut time: " + e.getMessage());
                }
            } else {
                logger.warning("Unknown message type: " + humanica.getMsgType() + ". Default to IN.");
                punch.setPunchType("IN");
                punch.setPunchDateTime(new Date());
            }
        } else {
            logger.warning("No message type specified. Default to IN.");
            punch.setPunchType("IN");
            punch.setPunchDateTime(new Date());
        }
        
        return punch;
    }
    
    /**
     * Lock management methods
     */
    public boolean lockAvailable() {
        try {
            em.getTransaction().begin();
            InterfaceLockMaster lock = em.createQuery(
                "SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", 
                InterfaceLockMaster.class)
                .setParameter("type", interfaceType)
                .getSingleResult();
            
            em.refresh(lock);
            
            if (lock.getLocked().intValue() == 1) {
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime locked = LocalDateTime.ofInstant(lock.getLockedDate().toInstant(), ZoneId.systemDefault());
                Duration diff = Duration.between(locked, today);
                
                if (diff.getSeconds() >= lock.getMaxLock().longValue()) {
                    lock.setLocked(new BigDecimal(1));
                    em.merge(lock);
                    em.getTransaction().commit();
                    return true;
                }
                em.getTransaction().commit();
                return false;
            } else {
                lock.setLocked(new BigDecimal(1));
                em.merge(lock);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            logger.severe("Error checking lock availability: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public void lockTable() {
        try {
            em.getTransaction().begin();
            InterfaceLockMaster lock = em.createQuery(
                "SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", 
                InterfaceLockMaster.class)
                .setParameter("type", interfaceType)
                .getSingleResult();
            
            lock.setLocked(new BigDecimal(1));
            lock.setLockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
                lock.setCurrentServer(address.getHostName());
            } catch (UnknownHostException e) {
                logger.severe("Error getting hostname: " + e.getMessage());
            }
            
            em.merge(lock);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Error locking table: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
    
    public void unlockTable() {
        try {
            em.getTransaction().begin();
            InterfaceLockMaster lock = em.createQuery(
                "SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", 
                InterfaceLockMaster.class)
                .setParameter("type", interfaceType)
                .getSingleResult();
            
            lock.setLocked(new BigDecimal(0));
            lock.setUnlockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            
            em.merge(lock);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Error unlocking table: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
    
    /**
     * Close connections
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (factory != null && factory.isOpen()) {
                factory.close();
            }
        } catch (SQLException e) {
            logger.severe("Error closing connections: " + e.getMessage());
        }
    }

}
