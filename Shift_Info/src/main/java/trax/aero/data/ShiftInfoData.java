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
     * Obtiene los datos para el archivo EmployeeSchedule
     * 
     * @return Lista de objetos ShiftInfo con los datos de empleados y horarios
     */
    public List<ShiftInfo> getEmployeeScheduleData() {
        List<ShiftInfo> shiftList = new ArrayList<>();
        
        try {
            // Consulta SQL específica para EmployeeSchedule
            String queryStr = "SELECT es.\"GROUP\" as SHIFTGROUPCODE, " +
                              "'SIABMM' as COMPANY_CODE, " +
                              "es.EMPLOYEE as EMP_NO, " +
                              "es.MODIFIED_DATE as STARTSHIFTDATE, " +
                              "'N' as ALWAYS_PRESENT " +
                              "FROM EMPLOYEE_SCHEDULE es";
            
            PreparedStatement ps = con.prepareStatement(queryStr);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ShiftInfo shift = new ShiftInfo();
                
                // Establecer tipo de registro
                shift.setRecordType("EMPLOYEE_SCHEDULE");
                
                // Setear los valores desde el ResultSet
                shift.setShiftGroupCode(rs.getString("SHIFTGROUPCODE"));
                shift.setCompanyCode(rs.getString("COMPANY_CODE"));
                shift.setEmpNo(rs.getString("EMP_NO"));
                shift.setStartShiftDate(rs.getString("STARTSHIFTDATE"));
                shift.setAlwaysPresent(rs.getString("ALWAYS_PRESENT"));
                
                shiftList.add(shift);
            }
            
            rs.close();
            ps.close();
            
            logger.info("Successfully retrieved " + shiftList.size() + " employee schedule records from database");
            
        } catch (Exception ex) {
            String errorMsg = "Error retrieving employee schedule data: " + ex.toString();
            logger.severe(errorMsg);
        }
        
        return shiftList;
    }
    
    /**
     * Obtiene los datos para el archivo ShiftPatterns
     * 
     * @return Lista de objetos ShiftInfo con los datos de patrones de turnos
     */
    public List<ShiftInfo> getShiftPatternsData() {
        List<ShiftInfo> shiftList = new ArrayList<>();
        
        try {
            // Consulta SQL específica para ShiftPatterns
            String queryStr = "select " +
                "dsp.DAY_PATTERN as SHIFTDAILYCODE, " +
                "'SIABMM' as COMPANY_CODE, " +
                "TO_CHAR(TO_DATE( dsp.DAY_START_HOUR || ':' || dsp.DAY_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS START_TIME, " +
                "TO_CHAR(TO_DATE( (dsp.DAY_START_HOUR + dsp.DAY_WORK_HOURS) || ':' || dsp.DAY_START_MINUTE, 'HH24:MI'), 'HH24:MI') as ENDTIME, " +
                "dsp.DAY_WORK_HOURS as PRODUCTIVEHOURS, " +
                "sp.DAY_PATTERN_1 as DAYTYPE, " +
                "dsp.NOTES as REMARK, " +
                "'1' as IS_ACTIVE, " +
                "'N' as HALFDAY, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_01_START_HOUR || ':' || dsp.BREAK_01_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_1, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_01_START_HOUR + dsp.BREAK_01) || ':' || dsp.BREAK_01_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_1, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_02_START_HOUR || ':' || dsp.BREAK_02_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_2, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_02_START_HOUR + dsp.BREAK_02) || ':' || dsp.BREAK_02_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_2, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_03_START_HOUR || ':' || dsp.BREAK_03_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_3, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_03_START_HOUR + dsp.BREAK_03) || ':' || dsp.BREAK_03_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_3, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_04_START_HOUR || ':' || dsp.BREAK_04_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_4, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_04_START_HOUR + dsp.BREAK_04) || ':' || dsp.BREAK_04_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_4, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_05_START_HOUR || ':' || dsp.BREAK_05_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_5, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_05_START_HOUR + dsp.BREAK_05) || ':' || dsp.BREAK_05_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_5, " +
                "TO_CHAR(TO_DATE( dsp.BREAK_06_START_HOUR || ':' || dsp.BREAK_06_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_STARTTIME_6, " +
                "TO_CHAR(TO_DATE( (dsp.BREAK_06_START_HOUR + dsp.BREAK_06) || ':' || dsp.BREAK_06_START_MINUTE, 'HH24:MI'), 'HH24:MI') AS BREAK_ENDTIME_6, " +
                "sp.PATTERN as SHIFTGROUPCODE, " +
                "CASE " +
                "   WHEN sp.PATTERN LIKE '%D_%H_%' THEN " +
                "       REPLACE( " +
                "           REPLACE( " +
                "               REPLACE(sp.PATTERN, 'D_', ' Days '), " +
                "               'H_', 'H Work ' " +
                "           ), " +
                "           SUBSTR(sp.PATTERN, -4), " +
                "           CASE " +
                "               WHEN SUBSTR(sp.PATTERN, -4, 1) = '0' THEN " +
                "                   SUBSTR(sp.PATTERN, -3, 1) || '.' || SUBSTR(sp.PATTERN, -2) || 'AM' " +
                "               ELSE " +
                "                   SUBSTR(sp.PATTERN, -4, 1) || '.' || SUBSTR(sp.PATTERN, -3, 2) || 'AM' " +
                "               END " +
                "       ) " +
                "   ELSE sp.PATTERN " +
                "END as SHIFTGROUPNAME, " +
                "'SIABMM' as COMPANY_CODE_1, " +
                "sp.TYPE as TOTALDAYS, " +
                "'1' as IS_ACTIVE_1, " +
                "sp.DAY_PATTERN_1 as SHIFTDAILYCODE_1, " +
                "sp.DAY_PATTERN_2 as SHIFTDAILYCODE_2, " +
                "sp.DAY_PATTERN_3 as SHIFTDAILYCODE_3, " +
                "sp.DAY_PATTERN_4 as SHIFTDAILYCODE_4, " +
                "sp.DAY_PATTERN_5 as SHIFTDAILYCODE_5, " +
                "sp.DAY_PATTERN_6 as SHIFTDAILYCODE_6, " +
                "sp.DAY_PATTERN_7 as SHIFTDAILYCODE_7 " +
                "from daily_shift_pattern dsp, " +
                "shift_pattern sp " +
                "where dsp.DAY_PATTERN = sp.DAY_PATTERN_1 " +
                "and sp.DAY_PATTERN_2 is null";
            
            PreparedStatement ps = con.prepareStatement(queryStr);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ShiftInfo shift = new ShiftInfo();
                
                // Establecer tipo de registro
                shift.setRecordType("SHIFT_PATTERNS");
                
                // Setear los valores desde el ResultSet
                shift.setShiftDailyCode(rs.getString("SHIFTDAILYCODE"));
                shift.setCompanyCode(rs.getString("COMPANY_CODE"));
                shift.setStartTime(rs.getString("START_TIME"));
                shift.setEndTime(rs.getString("ENDTIME"));
                shift.setProductiveHours(rs.getString("PRODUCTIVEHOURS"));
                shift.setDayType(rs.getString("DAYTYPE"));
                shift.setRemark(rs.getString("REMARK"));
                shift.setIsActive(rs.getString("IS_ACTIVE"));
                shift.setHalfDay(rs.getString("HALFDAY"));
                
                // Tiempos de descanso
                shift.setBreakStartTime1(rs.getString("BREAK_STARTTIME_1"));
                shift.setBreakEndTime1(rs.getString("BREAK_ENDTIME_1"));
                shift.setBreakStartTime2(rs.getString("BREAK_STARTTIME_2"));
                shift.setBreakEndTime2(rs.getString("BREAK_ENDTIME_2"));
                shift.setBreakStartTime3(rs.getString("BREAK_STARTTIME_3"));
                shift.setBreakEndTime3(rs.getString("BREAK_ENDTIME_3"));
                shift.setBreakStartTime4(rs.getString("BREAK_STARTTIME_4"));
                shift.setBreakEndTime4(rs.getString("BREAK_ENDTIME_4"));
                shift.setBreakStartTime5(rs.getString("BREAK_STARTTIME_5"));
                shift.setBreakEndTime5(rs.getString("BREAK_ENDTIME_5"));
                shift.setBreakStartTime6(rs.getString("BREAK_STARTTIME_6"));
                shift.setBreakEndTime6(rs.getString("BREAK_ENDTIME_6"));
                
                // Información del grupo de turnos
                shift.setShiftGroupCode(rs.getString("SHIFTGROUPCODE"));
                shift.setShiftGroupCode1(rs.getString("SHIFTGROUPCODE")); // Mismo campo según SQL
                shift.setShiftGroupName(rs.getString("SHIFTGROUPNAME"));
                shift.setCompanyCode1(rs.getString("COMPANY_CODE_1"));
                shift.setTotalDays(rs.getString("TOTALDAYS"));
                shift.setIsActive1(rs.getString("IS_ACTIVE_1"));
                
                // Códigos de turnos diarios
                shift.setShiftDailyCode1(rs.getString("SHIFTDAILYCODE_1"));
                shift.setShiftDailyCode2(rs.getString("SHIFTDAILYCODE_2"));
                shift.setShiftDailyCode3(rs.getString("SHIFTDAILYCODE_3"));
                shift.setShiftDailyCode4(rs.getString("SHIFTDAILYCODE_4"));
                shift.setShiftDailyCode5(rs.getString("SHIFTDAILYCODE_5"));
                shift.setShiftDailyCode6(rs.getString("SHIFTDAILYCODE_6"));
                shift.setShiftDailyCode7(rs.getString("SHIFTDAILYCODE_7"));
                
                shiftList.add(shift);
            }
            
            rs.close();
            ps.close();
            
            logger.info("Successfully retrieved " + shiftList.size() + " shift pattern records from database");
            
        } catch (Exception ex) {
            String errorMsg = "Error retrieving shift pattern data: " + ex.toString();
            logger.severe(errorMsg);
        }
        
        return shiftList;
    }
    
 // Insert generic data into the database
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