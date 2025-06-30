package trax.aero.data;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import trax.aero.logger.LogManager;
import trax.aero.model.InterfaceLockMaster;
import trax.aero.exception.CustomizeHandledException;
import trax.aero.utils.DataSourceClient;
import trax.aero.pojo.ShiftInfo;

public class ShiftInfoData {
    
    private Connection con;
    private EntityManagerFactory factory;
    private EntityManager em;
    private String executed = "OK";
   
    static Logger logger = LogManager.getLogger("ShiftInfo_I02");
    
    public ShiftInfoData() {
        factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
        em = factory.createEntityManager();
        
        try {
            if (this.con == null || this.con.isClosed()) {
                this.con = DataSourceClient.getConnection();
                logger.info("The connection was established successfully with status: " + String.valueOf(!this.con.isClosed()));
            }        
        } catch (SQLException | CustomizeHandledException e) {
            logger.severe(e.toString());
        } catch (Exception e) {
            logger.severe(e.toString());
        }
    }
    
    public Connection getCon() {
        return con;
    }
    
    /**
     * Retrieves data for the EmployeeSchedule file
     * 
     * @return List of ShiftInfo objects containing employee and schedule data
     */
    public List<ShiftInfo> getEmployeeScheduleData() {
        List<ShiftInfo> shiftList = new ArrayList<>();
        
        String selectSql =
                "SELECT es.\"GROUP\" AS SHIFTGROUPCODE, " +
                "       'SIABMM'      AS COMPANY_CODE, " +
                "       es.EMPLOYEE   AS EMP_NO, " +
                "       es.MODIFIED_DATE AS STARTSHIFTDATE, " +
                "       'N'           AS ALWAYS_PRESENT " +
                "  FROM EMPLOYEE_SCHEDULE es " +
                " WHERE es.BMM_INTERFACE_DATE IS NULL " +
                "   AND es.BMM_INTERFACE_FLAG IS NULL";

            String updateSql =
                "UPDATE EMPLOYEE_SCHEDULE " +
                "   SET BMM_INTERFACE_DATE = SYSDATE, " +
                "       BMM_INTERFACE_FLAG = 'Y' " +
                " WHERE EMPLOYEE = ?";

            try (
                PreparedStatement psSelect = con.prepareStatement(selectSql);
                PreparedStatement psUpdate = con.prepareStatement(updateSql);
                ResultSet rs = psSelect.executeQuery()
            ) {
                while (rs.next()) {
                    ShiftInfo shift = new ShiftInfo();
                    shift.setRecordType("EMPLOYEE_SCHEDULE");
                    shift.setShiftGroupCode(rs.getString("SHIFTGROUPCODE"));
                    shift.setCompanyCode(   rs.getString("COMPANY_CODE"));
                    shift.setEmpNo(         rs.getString("EMP_NO"));
                    shift.setStartShiftDate(rs.getString("STARTSHIFTDATE"));
                    shift.setAlwaysPresent( rs.getString("ALWAYS_PRESENT"));

                    shiftList.add(shift);

                 
                    String empNo = rs.getString("EMP_NO");
                    psUpdate.setString(1, empNo);
                    psUpdate.executeUpdate();
                   
                }

                logger.info("Successfully retrieved and marked " + shiftList.size() + " records");
            } catch (Exception ex) {
            String errorMsg = "Error retrieving employee schedule data: " + ex.toString();
            logger.severe(errorMsg);
        }
        
        return shiftList;
    }
    
    /**
     * Retrieves data for the ShiftPatterns file
     * This method fetches shift pattern information from the database including daily codes,
     * time slots, breaks, and all associated configuration parameters needed for BMM interface
     * 
     * @return List of ShiftInfo objects containing shift pattern data
     */
    public List<ShiftInfo> getShiftPatternsData() {
        List<ShiftInfo> shiftList = new ArrayList<>();
        
        String selectSql =
                "SELECT " +
                        "  dsp.DAY_PATTERN            AS SHIFTDAILYCODE, " +
                        "  'SIABMM'                   AS COMPANY_CODE, " +
                        "  -- Validate minutes are between 0-59 for start time " +
                        "  CASE " +
                        "    WHEN dsp.DAY_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.DAY_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.DAY_START_HOUR, 2, '0') || ':' || LPAD(dsp.DAY_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS START_TIME, " +
                        "  -- Validate minutes and hours for end time calculation " +
                        "  CASE " +
                        "    WHEN dsp.DAY_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.DAY_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.DAY_START_HOUR + dsp.DAY_WORK_HOURS) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.DAY_START_HOUR + dsp.DAY_WORK_HOURS, 2, '0') || ':' || LPAD(dsp.DAY_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.DAY_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.DAY_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      -- Handle hour overflow by using date arithmetic " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.DAY_START_HOUR + dsp.DAY_WORK_HOURS)/24 + dsp.DAY_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS ENDTIME, " +
                        "  dsp.DAY_WORK_HOURS         AS PRODUCTIVEHOURS, " +
                        "  sp.DAY_PATTERN_1           AS DAYTYPE, " +
                        "  dsp.NOTES                  AS REMARK, " +
                        "  '1'                        AS IS_ACTIVE, " +
                        "  'N'                        AS HALFDAY, " +
                        "  -- Break 1 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_01_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_01_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_01_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_01_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_1, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_01_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_01_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_01_START_HOUR + dsp.BREAK_01) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_01_START_HOUR + dsp.BREAK_01, 2, '0') || ':' || LPAD(dsp.BREAK_01_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_01_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_01_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_01_START_HOUR + dsp.BREAK_01)/24 + dsp.BREAK_01_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_1, " +
                        "  -- Break 2 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_02_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_02_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_02_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_02_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_2, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_02_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_02_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_02_START_HOUR + dsp.BREAK_02) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_02_START_HOUR + dsp.BREAK_02, 2, '0') || ':' || LPAD(dsp.BREAK_02_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_02_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_02_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_02_START_HOUR + dsp.BREAK_02)/24 + dsp.BREAK_02_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_2, " +
                        "  -- Break 3 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_03_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_03_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_03_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_03_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_3, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_03_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_03_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_03_START_HOUR + dsp.BREAK_03) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_03_START_HOUR + dsp.BREAK_03, 2, '0') || ':' || LPAD(dsp.BREAK_03_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_03_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_03_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_03_START_HOUR + dsp.BREAK_03)/24 + dsp.BREAK_03_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_3, " +
                        "  -- Break 4 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_04_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_04_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_04_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_04_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_4, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_04_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_04_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_04_START_HOUR + dsp.BREAK_04) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_04_START_HOUR + dsp.BREAK_04, 2, '0') || ':' || LPAD(dsp.BREAK_04_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_04_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_04_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_04_START_HOUR + dsp.BREAK_04)/24 + dsp.BREAK_04_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_4, " +
                        "  -- Break 5 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_05_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_05_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_05_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_05_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_5, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_05_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_05_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_05_START_HOUR + dsp.BREAK_05) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_05_START_HOUR + dsp.BREAK_05, 2, '0') || ':' || LPAD(dsp.BREAK_05_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_05_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_05_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_05_START_HOUR + dsp.BREAK_05)/24 + dsp.BREAK_05_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_5, " +
                        "  -- Break 6 times with validation " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_06_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_06_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_06_START_HOUR, 2, '0') || ':' || LPAD(dsp.BREAK_06_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_STARTTIME_6, " +
                        "  CASE " +
                        "    WHEN dsp.BREAK_06_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_06_START_HOUR BETWEEN 0 AND 23 " +
                        "     AND (dsp.BREAK_06_START_HOUR + dsp.BREAK_06) <= 23 THEN " +
                        "      TO_CHAR(TO_DATE(LPAD(dsp.BREAK_06_START_HOUR + dsp.BREAK_06, 2, '0') || ':' || LPAD(dsp.BREAK_06_START_MINUTE, 2, '0'), 'HH24:MI'), 'HH24:MI') " +
                        "    WHEN dsp.BREAK_06_START_MINUTE BETWEEN 0 AND 59 " +
                        "     AND dsp.BREAK_06_START_HOUR BETWEEN 0 AND 23 THEN " +
                        "      TO_CHAR(TO_DATE('00:00', 'HH24:MI') + (dsp.BREAK_06_START_HOUR + dsp.BREAK_06)/24 + dsp.BREAK_06_START_MINUTE/(24*60), 'HH24:MI') " +
                        "    ELSE NULL " +
                        "  END AS BREAK_ENDTIME_6, " +
                        "  sp.PATTERN                 AS SHIFTGROUPCODE, " +
                        "  CASE " +
                        "    WHEN sp.PATTERN LIKE '%D_%H_%' THEN " +
                        "      REPLACE( " +
                        "        REPLACE(sp.PATTERN, 'D_', ' Days '), " +
                        "        'H_', 'H Work ' " +
                        "      ) " +
                        "    ELSE sp.PATTERN " +
                        "  END                         AS SHIFTGROUPNAME, " +
                        "  'SIABMM'                   AS COMPANY_CODE_1, " +
                        "  sp.TYPE                    AS TOTALDAYS, " +
                        "  '1'                        AS IS_ACTIVE_1, " +
                        "  sp.DAY_PATTERN_1           AS SHIFTDAILYCODE_1, " +
                        "  sp.DAY_PATTERN_2           AS SHIFTDAILYCODE_2, " +
                        "  sp.DAY_PATTERN_3           AS SHIFTDAILYCODE_3, " +
                        "  sp.DAY_PATTERN_4           AS SHIFTDAILYCODE_4, " +
                        "  sp.DAY_PATTERN_5           AS SHIFTDAILYCODE_5, " +
                        "  sp.DAY_PATTERN_6           AS SHIFTDAILYCODE_6, " +
                        "  sp.DAY_PATTERN_7           AS SHIFTDAILYCODE_7 " +
                        "FROM daily_shift_pattern dsp " +
                        "JOIN shift_pattern sp ON dsp.DAY_PATTERN = sp.DAY_PATTERN_1 " +
                        "WHERE sp.BMM_INTERFACE_DATE     IS NULL " +
                        "  AND sp.BMM_INTERFACE_FLAG     IS NULL " +
                        "  AND dsp.BMM_INTERFACE_DATE    IS NULL " +
                        "  AND dsp.BMM_INTERFACE_FLAG    IS NULL";

            String updateDailySql =
                "UPDATE daily_shift_pattern " +
                "   SET BMM_INTERFACE_DATE = SYSDATE, " +
                "       BMM_INTERFACE_FLAG = 'Y' " +
                " WHERE DAY_PATTERN = ?";

            String updateShiftSql =
                "UPDATE shift_pattern " +
                "   SET BMM_INTERFACE_DATE = SYSDATE, " +
                "       BMM_INTERFACE_FLAG = 'Y' " +
                " WHERE DAY_PATTERN_1 = ?";

            try (
                PreparedStatement psSelect     = con.prepareStatement(selectSql);
                PreparedStatement psUpdateDaily = con.prepareStatement(updateDailySql);
                PreparedStatement psUpdateShift = con.prepareStatement(updateShiftSql);
                ResultSet rs                   = psSelect.executeQuery()
            ) {
                while (rs.next()) {
                    ShiftInfo shift = new ShiftInfo();
                    shift.setRecordType("SHIFT_PATTERNS");
                    shift.setShiftDailyCode( rs.getString("SHIFTDAILYCODE"));
                    shift.setCompanyCode(    rs.getString("COMPANY_CODE"));
                    shift.setStartTime(      rs.getString("START_TIME"));
                    shift.setEndTime(        rs.getString("ENDTIME"));
                    shift.setProductiveHours(rs.getString("PRODUCTIVEHOURS"));
                    shift.setDayType(        rs.getString("DAYTYPE"));
                    shift.setRemark(         rs.getString("REMARK"));
                    shift.setIsActive(       rs.getString("IS_ACTIVE"));
                    shift.setHalfDay(        rs.getString("HALFDAY"));
                    shift.setBreakStartTime1(rs.getString("BREAK_STARTTIME_1"));
                    shift.setBreakEndTime1(  rs.getString("BREAK_ENDTIME_1"));
                    shift.setBreakStartTime2(rs.getString("BREAK_STARTTIME_2"));
                    shift.setBreakEndTime2(  rs.getString("BREAK_ENDTIME_2"));
                    shift.setBreakStartTime3(rs.getString("BREAK_STARTTIME_3"));
                    shift.setBreakEndTime3(  rs.getString("BREAK_ENDTIME_3"));
                    shift.setBreakStartTime4(rs.getString("BREAK_STARTTIME_4"));
                    shift.setBreakEndTime4(  rs.getString("BREAK_ENDTIME_4"));
                    shift.setBreakStartTime5(rs.getString("BREAK_STARTTIME_5"));
                    shift.setBreakEndTime5(  rs.getString("BREAK_ENDTIME_5"));
                    shift.setBreakStartTime6(rs.getString("BREAK_STARTTIME_6"));
                    shift.setBreakEndTime6(  rs.getString("BREAK_ENDTIME_6"));
                    shift.setShiftGroupCode( rs.getString("SHIFTGROUPCODE"));
                    shift.setShiftGroupName( rs.getString("SHIFTGROUPNAME"));
                    shift.setCompanyCode1(   rs.getString("COMPANY_CODE_1"));
                    shift.setTotalDays(      rs.getString("TOTALDAYS"));
                    shift.setIsActive1(      rs.getString("IS_ACTIVE_1"));
                    shift.setShiftDailyCode1(rs.getString("SHIFTDAILYCODE_1"));
                    shift.setShiftDailyCode2(rs.getString("SHIFTDAILYCODE_2"));
                    shift.setShiftDailyCode3(rs.getString("SHIFTDAILYCODE_3"));
                    shift.setShiftDailyCode4(rs.getString("SHIFTDAILYCODE_4"));
                    shift.setShiftDailyCode5(rs.getString("SHIFTDAILYCODE_5"));
                    shift.setShiftDailyCode6(rs.getString("SHIFTDAILYCODE_6"));
                    shift.setShiftDailyCode7(rs.getString("SHIFTDAILYCODE_7"));

                    shiftList.add(shift);

                   
                    psUpdateDaily.setString(1, rs.getString("SHIFTDAILYCODE"));
                    psUpdateDaily.executeUpdate();

                    
                    psUpdateShift.setString(1, rs.getString("SHIFTDAILYCODE_1"));
                    psUpdateShift.executeUpdate();
                }

                logger.info("Successfully retrieved and marked " + shiftList.size() + " shift pattern records");
            }  catch (Exception ex) {
            String errorMsg = "Error retrieving shift pattern data: " + ex.toString();
            logger.severe(errorMsg);
        }
        
        return shiftList;
    }
    
    /**
     * Inserts or updates generic data into the database
     * This method handles transaction management for persisting objects,
     * including commit and rollback operations in case of exceptions
     * 
     * @param <T> Generic type parameter for the data object
     * @param data The object to be inserted or updated in the database
     */
    private <T> void insertData(T data) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(data);  
            em.flush();  
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  
            }
            logger.severe("Error during insert/update: " + e.getMessage());
        }
    }

    
    public boolean lockAvailable(String notificationType) {
        InterfaceLockMaster lock = em.createQuery("SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType).getSingleResult();
        em.refresh(lock);

        if (lock.getLocked().intValue() == 1) {                
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime locked = LocalDateTime.ofInstant(lock.getLockedDate().toInstant(), ZoneId.systemDefault());
            Duration diff = Duration.between(locked, today);

            if (diff.getSeconds() >= lock.getMaxLock().longValue()) {
                lock.setLocked(new BigDecimal(1));
                insertData(lock);
                return true;
            }
            return false;
        } else {
            lock.setLocked(new BigDecimal(1));
            insertData(lock);
            return true;
        }
    }

    public void lockTable(String notificationType) {
        em.getTransaction().begin();
        InterfaceLockMaster lock = em.createQuery("SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType).getSingleResult();
        lock.setLocked(new BigDecimal(1));
        
        lock.setLockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.info(e.getMessage());
        }
        lock.setCurrentServer(address.getHostName());
        em.merge(lock);
        em.getTransaction().commit();
    }

    public void unlockTable(String notificationType) {
        em.getTransaction().begin();

        InterfaceLockMaster lock = em.createQuery("SELECT i FROM InterfaceLockMaster i WHERE i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType).getSingleResult();
        lock.setLocked(new BigDecimal(0));

        lock.setUnlockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        em.merge(lock);
        em.getTransaction().commit();
    }
}