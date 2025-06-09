package trax.aero.data;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import trax.aero.utils.ErrorType;
import trax.aero.controller.SlotsESlotController;
import trax.aero.exception.CustomizeHandledException;
import trax.aero.logger.LogManager;
import trax.aero.model.AcMaster;
import trax.aero.model.InterfaceLockMaster;
import trax.aero.model.JournalEntriesExpenditure;
import trax.aero.model.JournalEntriesExpenditurePK;
import trax.aero.model.LocationMaster;
import trax.aero.model.LocationSite;
import trax.aero.model.NotePad;
import trax.aero.model.NotePadPK;
import trax.aero.model.SystemTranCode;
import trax.aero.model.Wo;
import trax.aero.pojo.ESlotItem;
import trax.aero.pojo.ESlot;
import trax.aero.utils.DataSourceClient;

public class SlotsESlotData {

    Long woNumber;
    EntityManagerFactory factory;
    EntityManager em;
    String exceuted;
    public String WO;
    private Connection con;
    Logger logger = LogManager.getLogger("SlotsESlot_I31");
    public InterfaceLockMaster lock;
    
    /**
     * Default constructor that initializes the entity manager and database connection
     *
     * This constructor creates an EntityManagerFactory, gets an EntityManager instance,
     * and establishes a database connection. It logs connection status and catches
     * any exceptions that occur during initialization.
     */
    public SlotsESlotData() {
        factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
        em = factory.createEntityManager();
        
        try {
            if(this.con == null || this.con.isClosed()) {
                this.con = DataSourceClient.getConnection();
                logger.info("The connection was stablished successfully with status: " + String.valueOf(!this.con.isClosed()));
            }
        } 
        catch (SQLException e) {
            logger.severe("An error occured getting the status of the connection");
            SlotsESlotController.addError(e.toString());
        }
        catch (CustomizeHandledException e1) {
            SlotsESlotController.addError(e1.toString());
        } catch (Exception e) {
            SlotsESlotController.addError(e.toString());
        }
    }
        
    /**
     * Constructor that takes a string parameter and initializes entity manager
     *
     * This simplified constructor only creates an EntityManagerFactory and gets an
     * EntityManager instance without establishing a database connection.
     *
     * @param string A string parameter (not used in the implementation)
     */
    public SlotsESlotData(String string) {
        factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
        em = factory.createEntityManager();
    }

    public Connection getCon() {
        return con;
    }
    
    /**
     * Processes an ESlotItem for insertion, update, or deletion
     *
     * This method serves as the main entry point for slot processing operations.
     * It delegates to insertSlot() while handling exceptions and transaction management.
     *
     * @param item The ESlotItem object containing slot data to process
     * @return A string indicating the result of the operation ("OK" or error message)
     * @throws Exception If any error occurs during processing
     */
    public String slot(ESlotItem item) throws Exception {
        
        exceuted = "OK";
        
        try {
            insertSlot(item);
        }
        catch (Exception e) {
            SlotsESlotController.addError(e.toString());
            e.printStackTrace();
            em.getTransaction().rollback();
            exceuted = e.toString();
        }
        finally {
            //clean up 
            em.clear();
        }
        return exceuted;
    }
    
    /**
     * Handles the core logic for inserting, updating, or deleting slot data
     *
     * This method validates the input, checks if work order exists, processes location,
     * handles dates and times, and performs the appropriate action (insert/update/delete)
     * based on the action field in the ESlotItem.
     *
     * @param item The ESlotItem object containing slot data to process
     */
    private void insertSlot(ESlotItem item) {
        
        boolean existWO = false;
        boolean existNote = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm:ss",Locale.ENGLISH);    
        SimpleDateFormat formatterModDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");    
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yy",Locale.ENGLISH);
        SimpleDateFormat formatHours = new SimpleDateFormat("HH");
        SimpleDateFormat formatMinutes = new SimpleDateFormat("mm");
                
        Wo wo = null;
        NotePad notepad = null;
            
        //check if object has min values
        if(item.getEslot() != null || checkMinValue(item.getEslot())) {
            
            
            /*if (item.getEslot().getLocation() != null) {
                if (!item.getEslot().getLocation().equals("SZB")) {
                    
                    exceuted = "Location is not SZB, skipping processing for BMM TRAX";
                    logger.info(exceuted);
                    return;
                }
            } else {
                
                logger.info("No location specified, assuming BMM TRAX processing");
            }*/
            
            try {
                wo = em.createQuery("Select w From Wo w where w.cosl = :exRef", Wo.class)
                        .setParameter("exRef", item.getEslot().getOid())
                        .getSingleResult();
                existWO = true;
            }
            catch(Exception e) {
                wo = new Wo();
                wo.setCreatedDate(new Date());
                wo.setCreatedBy("TRAX_IFACE");
                
                //EMRO fields to create basic object
                wo.setGlCompany(System.getProperty("profile_company"));
                wo.setOrderType("W/O");
                wo.setModule("PRODUCTION");
                wo.setPaperChecked("NO");
                wo.setAuthorization("Y");
                wo.setNrReqItem("N");
                wo.setRestrictActual("N");
                wo.setNrAllow("YES");
                wo.setExcludeMhPlanner("N");
                wo.setThirdPartyWo("Y");
                
                wo.setExpenditure(setExpenditure("General"));
            }
            
            wo.setModifiedBy("TRAX_IFACE");
            wo.setModifiedDate(new Date());
            wo.setCosl(item.getEslot().getOid());
            wo.setCustomerWo(item.getEslot().getCustomer());
            
            if (getAC(item.getEslot().getACReg()) == null) {
                exceuted = "Can not insert/update/delete OID: "+ item.getEslot().getOid() +" as AC does not exist";
                logger.severe(exceuted);
                SlotsESlotController.addError(exceuted);
                return;
            } else {
            	String ac = item.getEslot().getACReg();
                wo.setAc(ac);
                wo.setAcType(getAcType(ac));
                wo.setAcSeries(getAcSeries(ac));
            }
            
            if (getCategory(item.getEslot().getCheckType()) == null) {
                exceuted = "Can not insert/update/delete OID: "+ item.getEslot().getOid() +" as Category does not exist";
                logger.severe(exceuted);
                SlotsESlotController.addError(exceuted);
                return;
            } else {
                wo.setWoCategory(item.getEslot().getCheckType());
            }
            
            wo.setOpsLine(item.getEslot().getLine());
            String site = getSiteOps(wo.getOpsLine());
            
            wo.setSite(site);
            wo.setLocation(setLocation(site));
            
            try {
                Date start = formatterDate.parse(item.getEslot().getPlannedStart());
                Date end = formatterDate.parse(item.getEslot().getPlannedEnd());
                
                wo.setScheduleStartDate(start);
                wo.setScheduleStartHour(new BigDecimal((formatHours.format(formatter.parse(item.getEslot().getPlannedStart())))));
                wo.setScheduleStartMinute(new BigDecimal(formatMinutes.format(formatter.parse(item.getEslot().getPlannedStart()))));
                
                wo.setActualStartDate(start);
                wo.setActualStartHour(new BigDecimal(formatHours.format(formatter.parse(item.getEslot().getPlannedStart()))));
                wo.setActualStartMinute(new BigDecimal(formatMinutes.format(formatter.parse(item.getEslot().getPlannedStart()))));
                
                wo.setScheduleOrgCompletionDate(end);
                wo.setScheduleOrgCompletionHour(new BigDecimal(formatHours.format(formatter.parse(item.getEslot().getPlannedEnd()))));
                wo.setScheduleOrgCompletionMinute(new BigDecimal(formatMinutes.format(formatter.parse(item.getEslot().getPlannedEnd()))));
                
                wo.setScheduleCompletionDate(end);
                wo.setScheduleCompletionHour(new BigDecimal(formatHours.format(formatter.parse(item.getEslot().getPlannedEnd()))));
                wo.setScheduleCompletionMinute(new BigDecimal(formatMinutes.format(formatter.parse(item.getEslot().getPlannedEnd()))));
                
            } catch (ParseException e1) {
                exceuted = "Can not insert/update/delete OID: "+ item.getEslot().getOid() +" as ERROR: Planned Start or Planned End Date is incorrect format";
                logger.severe(exceuted);
                SlotsESlotController.addError(exceuted);
                return;
            }
            
            if(item.getEslot().getConfirmationStatus().equalsIgnoreCase("1")) {
                wo.setStatus("OPEN");
            }
            if(item.getEslot().getConfirmationStatus().equalsIgnoreCase("0")) {
                wo.setStatus("SLOT");
            }
            
            if(item.getEslot().getCheckDescription() != null && !item.getEslot().getCheckDescription().isEmpty()) {
                wo.setWoDescription(item.getEslot().getCheckDescription());
            }
            
            if(item.getEslot().getRemarks() != null && !item.getEslot().getRemarks().isEmpty()) {
                try {
                    notepad = em.createQuery("Select n from NotePad n where n.id.notes = :not", NotePad.class)
                            .setParameter("not", wo.getNotes().longValue())
                            .getSingleResult();
                    existNote = true;
                }
                catch(Exception e) {
                    NotePadPK pk = new NotePadPK();
                    notepad = new NotePad();
                    notepad.setCreatedDate(new Date());
                    notepad.setCreatedBy("TRAX_IFACE");
                    notepad.setId(pk);
                    
                    notepad.getId().setNotesLine(1);
                    notepad.setPrintNotes("YES");
                    
                    try {
                        notepad.getId().setNotes(((getTransactionNo("NOTES").longValue())));
                        wo.setNotes(new BigDecimal(notepad.getId().getNotes()));
                    } catch (Exception e1) {
                        // Ignore
                    }
                }
                notepad.setModifiedBy("TRAX_IFACE");
                notepad.setModifiedDate(new Date());
                notepad.setNotesText(item.getEslot().getRemarks());
            }
            
            wo.setModifiedBy("TRAX_IFACE");
            wo.setModifiedDate(new Date());
            
            if(!existWO) {
                try {
                    wo.setWo(getTransactionNo("WOSEQ").longValue());
                } catch (Exception e1) {
                    // Ignore
                }
            }
            
            WO = String.valueOf(wo.getWo());
            
            if(item.getEslot().getAction().equalsIgnoreCase("I")) {
                if(notepad != null) {
                    logger.info("INSERTING NOTE: " + notepad.getId().getNotes());
                    insertData(notepad);
                }
                
                logger.info("INSERTING OID: " + item.getEslot().getOid() + " WO: " + wo.getWo());
                insertData(wo);
                woNumber = wo.getWo();
                
            } else if(item.getEslot().getAction().equalsIgnoreCase("D")) {
                if((wo.getWorkStarted() != null && wo.getWorkStarted().equalsIgnoreCase("Y") && item.getEslot().getConfirmationStatus().equalsIgnoreCase("1"))) {
                    exceuted = "Can not Delete OID: "+ item.getEslot().getOid() +" as ERROR: Work has started and Confirmed";
                    logger.severe(exceuted);
                    SlotsESlotController.addError(exceuted);
                    return;
                } else {
                    if(notepad != null) {
                        logger.info("DELETE NOTE: " + notepad.getId().getNotes()+ " WO: " + wo.getWo());
                        deleteData(notepad);
                    }
                    
                    logger.info("DELETE OID: " + item.getEslot().getOid()+ " WO: " + wo.getWo());
                    deleteData(wo);
                }
                
            } else if(item.getEslot().getAction().equalsIgnoreCase("U")) {
          
                if (!existWO) {
                    logger.info("Update requested for non-existent WO, treating as insert: " + item.getEslot().getOid());
                    if(notepad != null) {
                        logger.info("INSERTING NOTE: " + notepad.getId().getNotes());
                        insertData(notepad);
                    }
                    
                    logger.info("INSERTING OID: " + item.getEslot().getOid() + " WO: " + wo.getWo());
                    insertData(wo);
                    woNumber = wo.getWo();
                } else {
                    if(notepad != null) {
                        logger.info("UPDATING NOTE: " + notepad.getId().getNotes()+ " WO: " + wo.getWo());
                        insertData(notepad);
                    }
                    
                    logger.info("UPDATING OID: " + item.getEslot().getOid()+ " WO: " + wo.getWo());
                    insertData(wo);
                }
            } else {
                exceuted = "Can not insert/update/delete OID: "+ item.getEslot().getOid() +" as ERROR: ACTION is incorrect format";
                logger.severe(exceuted);
                SlotsESlotController.addError(exceuted);
                return;
            }
        } else {
            exceuted = "Can not insert/update/delete OID: "+ item.getEslot().getOid() +" as ERROR: ESlot is null or item does not have minimum values";
            logger.severe(exceuted);
            SlotsESlotController.addError(exceuted);
            return;
        }
    }
    
    /**
     * Retrieves the site associated with a given operations line
     *
     * Queries the OPS_LINE_EMAIL_MASTER table to find the site value for the
     * specified operations line.
     *
     * @param opsLine The operations line code to look up
     * @return The site value associated with the operations line, or empty string if not found
     */
    private String getSiteOps(String opsLine) {
        String group = "";
        String sql = " Select SITE FROM OPS_LINE_EMAIL_MASTER where OPS_LINE = ?";

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, opsLine);  
            
            group = (String) query.getSingleResult(); 
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            logger.severe("An Exception occurred executing the query to get the site. " + "\n error: " + e.toString());
        }
        finally {
            // No cleanup needed
        }
        return group;
    }
    
    /**
     * Adds a new operations line with associated site and email
     *
     * Inserts a new record into the OPS_LINE_EMAIL_MASTER table linking an operations
     * line with a site and email address.
     *
     * @param site The site code to associate with the operations line
     * @param opsLine The operations line code
     * @param email The email address to associate with the operations line
     * @return A string indicating the result ("OK" or error message)
     * @throws Exception If any database error occurs
     */
    public String setSite(String site, String opsLine, String email) throws Exception {
        String Exceuted = "OK";
        String query = "INSERT INTO OPS_LINE_EMAIL_MASTER (OPS_LINE, EMAIL,SITE) VALUES (?, ?, ?)";
        PreparedStatement ps = null;    
        
        try {
            if(con == null || con.isClosed()) {
                con = DataSourceClient.getConnection();
                logger.info("The connection was stablished successfully with status: " + String.valueOf(!con.isClosed()));
            }
            
            ps = con.prepareStatement(query);
            ps.setString(1, opsLine);
            ps.setString(2, email);
            ps.setString(3, site);
            ps.executeUpdate();
        }
        catch (Exception e) {
            logger.severe("An Exception occurred executing the query to set the site opsLine. " + "\n error: " + e.toString() );
            throw new Exception("An Exception occurred executing the query to set the site opsLine. " + "\n error: " + e.toString());
        }
        finally {
            try {
                if(ps != null && !ps.isClosed())
                    ps.close();
            } 
            catch (SQLException e) { 
                logger.severe("An error ocurrer trying to close the statement");
            }
        }
        return Exceuted;
    }
    
    /**
     * Deletes an operations line record from the system
     *
     * Removes a record from the OPS_LINE_EMAIL_MASTER table for the specified
     * operations line.
     *
     * @param opsline The operations line code to delete
     * @return A string indicating the result ("OK" or error message)
     * @throws Exception If any database error occurs
     */
    public String deleteSite(String opsline) throws Exception {
        String Exceuted = "OK";
        String query = "DELETE OPS_LINE_EMAIL_MASTER where OPS_LINE = ?";    
        PreparedStatement ps = null;
            
        try {    
            if(con == null || con.isClosed()) {
                con = DataSourceClient.getConnection();
                logger.info("The connection was stablished successfully with status: " + String.valueOf(!con.isClosed()));
            }
            
            ps = con.prepareStatement(query);
            ps.setString(1, opsline);
            ps.executeUpdate();        
        }
        catch (Exception e) {
            logger.severe("An Exception occurred executing the query to delete the site . " + "\n error: " + e.toString());
            throw new Exception("An Exception occurred executing the query to delete the site. " + "\n error: " + e.toString());
        }
        finally {
            try {
                if(ps != null && !ps.isClosed())
                    ps.close();
            } 
            catch (SQLException e) { 
                logger.severe("An error ocurrer trying to close the statement");
            }
        }
        return Exceuted;
    }
    
    /**
     * Retrieves operations line information from the system
     *
     * Queries the OPS_LINE_EMAIL_MASTER table to get records for a specific
     * operations line or all operations lines if parameter is null/empty.
     *
     * @param opsline The operations line code to look up, or null/empty for all
     * @return A formatted string containing all matching records
     * @throws Exception If any database error occurs
     */
    public String getSite(String opsline) throws Exception {
        ArrayList<String> groups = new ArrayList<String>();
        
        String query = "", group = "";
        if(opsline != null && !opsline.isEmpty()) {
            query = " Select OPS_LINE, site, EMAIL FROM OPS_LINE_EMAIL_MASTER where OPS_LINE = ?";
        } else {
            query = " Select OPS_LINE, site, EMAIL FROM OPS_LINE_EMAIL_MASTER";
        }
        PreparedStatement ps = null;
            
        try {
            if(con == null || con.isClosed()) {
                con = DataSourceClient.getConnection();
                logger.info("The connection was stablished successfully with status: " + String.valueOf(!con.isClosed()));
            }
            
            ps = con.prepareStatement(query);
            if(opsline != null && !opsline.isEmpty()) {
                ps.setString(1, opsline);
            }
            
            ResultSet rs = ps.executeQuery();        
            
            if (rs != null) {
                while (rs.next()) {
                    groups.add("Ops Line: "+rs.getString(1) + " Site: " +rs.getString(2) + " Email: " +rs.getString(3) );
                }
            }
            rs.close();
        }
        catch (Exception e) {
            logger.severe("An Exception occurred executing the query to get the site opsLine. " + "\n error: " + e.toString());
            throw new Exception("An Exception occurred executing the query to get the site opsLine. " + "\n error: " +  e.toString());
        }
        finally {
            try {
                if(ps != null && !ps.isClosed())
                    ps.close();
            } 
            catch (SQLException e) { 
                logger.severe("An error ocurrer trying to close the statement");
            }
        }
        
        for(String g : groups) {
            group = group + g +"\n";
        }
        
        return group;
    }
    
    /**
     * Generic method to insert or update data objects in the database
     *
     * This method begins a transaction if none is active, merges the provided object
     * into the persistence context, and commits the transaction.
     *
     * @param <T> The type of object to persist
     * @param data The object to persist
     */
    private <T> void insertData(T data) {
        try {    
            if(!em.getTransaction().isActive())
                em.getTransaction().begin();
                em.merge(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exceuted = "insertData has encountered an Exception: "+e.toString();
            SlotsESlotController.addError(exceuted);
            logger.severe(exceuted);
        }
    }
    
    /**
     * Generic method to delete data objects from the database
     *
     * This method begins a transaction if none is active, removes the provided object
     * from the persistence context, and commits the transaction.
     *
     * @param <T> The type of object to delete
     * @param data The object to delete
     */
    private <T> void deleteData(T data) {
        try {    
            if(!em.getTransaction().isActive())
                em.getTransaction().begin();
                em.remove(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            exceuted = "insertData has encountered an Exception: "+e.toString();
            SlotsESlotController.addError(exceuted);
            logger.severe(exceuted);
        }
    }
   
    /**
     * Validates that an ESlot object contains all required fields
     *
     * Checks that all mandatory fields in the ESlot object have non-null,
     * non-empty values before processing.
     *
     * @param eslot The ESlot object to validate
     * @return true if all required fields are present, false otherwise
     */
    private boolean checkMinValue(ESlot eslot) {
        if(eslot.getACReg() == null || eslot.getACReg().isEmpty()) {
            return false;
        }
            
        if(eslot.getCustomer() == null || eslot.getCustomer().isEmpty()) {
            return false;
        }
            
        if(eslot.getCheckType() == null || eslot.getCheckType().isEmpty()) {
            return false;
        }
            
        if(eslot.getPlannedStart() == null || eslot.getPlannedStart().isEmpty()) {
            return false;
        }
            
        if(eslot.getPlannedEnd() == null || eslot.getPlannedEnd().isEmpty()) {
            return false;
        }
            
        if(eslot.getAction() == null || eslot.getAction().isEmpty()) {
            return false;
        }
            
        if(eslot.getOid() == null || eslot.getOid().isEmpty()) {
            return false;
        }
            
        if(eslot.getConfirmationStatus() == null || eslot.getConfirmationStatus().isEmpty()) {
            return false;
        }
        if(eslot.getLine() == null || eslot.getLine().isEmpty()) {
            return false;
        }
            
        return true;
    }
    
    /**
     * Retrieves a sequence number for a given transaction code
     *
     * Calls the database function pkg_application_function.config_number
     * to get the next available number for a specified transaction code.
     *
     * @param code The transaction code to get a sequence number for
     * @return The next BigDecimal sequence number, or null if an error occurs
     */
    private BigDecimal getTransactionNo(String code) {        
        try {
            BigDecimal acctBal = (BigDecimal) em.createNativeQuery("SELECT pkg_application_function.config_number ( ? ) "
                    + " FROM DUAL ").setParameter(1, code).getSingleResult();
                        
            return acctBal;            
        }
        catch (Exception e) {
            logger.severe("An unexpected error occurred getting the sequence. " + "\nmessage: " + e.toString());
        }
        
        return null;
    }
    
    /**
     * Verifies if an aircraft exists in the system
     *
     * Queries the AcMaster table to check if the specified aircraft registration
     * exists in the database.
     *
     * @param AC The aircraft registration to look up
     * @return The aircraft registration if found, or a space character if not found
     */
    private String getAC(String AC) {
        try {    
            AcMaster acMaster = em.createQuery("Select a From AcMaster a where a.id.ac = :airC", AcMaster.class)
            .setParameter("airC", AC)
            .getSingleResult();
                    
            return acMaster.getAc();
        }
        catch (Exception e) {
            // Silent exception handling
        }
        return " ";
    }
    
    private String getAcType(String AC) {
        try {
            AcMaster acMaster = em.createQuery("Select a From AcMaster a where a.id.ac = :airC", AcMaster.class)
                    .setParameter("airC", AC)
                    .getSingleResult();
            
            return acMaster.getAcType(); 
        }
        catch (Exception e) {
            
        }
        return "";
    }

    private String getAcSeries(String AC) {
        try {
            AcMaster acMaster = em.createQuery("Select a From AcMaster a where a.id.ac = :airC", AcMaster.class)
                    .setParameter("airC", AC)
                    .getSingleResult();
            
            return acMaster.getAcSeries(); 
        }
        catch (Exception e) {
            
        }
        return "";
    }
    
    /**
     * Verifies if a work order category exists in the system
     *
     * Queries the SystemTranCode table to check if the specified category code
     * exists for the WOCATEGORY transaction type.
     *
     * @param Category The category code to look up
     * @return The category code if found, or null if not found
     */
    private String getCategory(String Category) {
        try {    
            SystemTranCode systemTranCode = em.createQuery("Select s From SystemTranCode s where s.id.systemCode = :cat and s.id.systemTransaction = :systran", SystemTranCode.class)
            .setParameter("cat", Category)
            .setParameter("systran", "WOCATEGORY")
            .getSingleResult();
                    
            return systemTranCode.getId().getSystemCode();
        }
        catch (Exception e) {
            // Silent exception handling
        }
        return null;
    }
 
    /**
     * Updates the site associated with an operations line
     *
     * Modifies an existing record in the OPS_LINE_EMAIL_MASTER table,
     * changing the site value for the specified operations line.
     *
     * @param site The new site value to set
     * @param opsLine The operations line code to update
     * @return A string indicating the result ("OK" or error message)
     * @throws Exception If any database error occurs
     */
    public String updateOpsLine(String site, String opsLine) throws Exception {
        String Exceuted = "OK";
        String query = "UPDATE OPS_LINE_EMAIL_MASTER SET SITE = ? WHERE OPS_LINE = ?";
        PreparedStatement ps = null;    
        
        try {
            if(con == null || con.isClosed()) {
                con = DataSourceClient.getConnection();
                logger.info("The connection was stablished successfully with status: " + String.valueOf(!con.isClosed()));
            }
            
            ps = con.prepareStatement(query);
            ps.setString(1, site);
            ps.setString(2, opsLine);
            ps.executeUpdate();
        }
        catch (Exception e) {
            logger.severe("An Exception occurred executing the query to update the site opsLine. " + "\n error: " + e.toString() );
            throw new Exception("An Exception occurred executing the query to update the site opsLine. " + "\n error: " + e.toString());
        }
        finally {
            try {
                if(ps != null && !ps.isClosed())
                    ps.close();
            } 
            catch (SQLException e) { 
                logger.severe("An error ocurrer trying to close the statement");
            }
        }
        return Exceuted;
    }
    
    /**
     * Retrieves or creates a default expenditure code
     *
     * Looks up an expenditure code in the JournalEntriesExpenditure table
     * based on the provided string. If not found, creates a new default entry.
     *
     * @param string The expenditure code to look up
     * @return The expenditure category code (either found or newly created)
     */
    private String setExpenditure(String string) {
        JournalEntriesExpenditure journalEntriesExpenditure = null;
        try {
            journalEntriesExpenditure = em.createQuery("SELECT j FROM JournalEntriesExpenditure j WHERE j.id.categoryCode = :code AND  j.id.transaction = :tra AND j.id.category = :cat", JournalEntriesExpenditure.class)
            .setParameter("code",string)
            .setParameter("tra", "WIP")
            .setParameter("cat", "EXPENDITURE")
            .getSingleResult();
            
            return journalEntriesExpenditure.getId().getCategoryCode();
        }
        catch (Exception e) {
            journalEntriesExpenditure = new JournalEntriesExpenditure();
            JournalEntriesExpenditurePK pk = new JournalEntriesExpenditurePK();
            journalEntriesExpenditure.setId(pk);
            journalEntriesExpenditure.setModifiedBy("TRAX_IFACE");
            journalEntriesExpenditure.setModifiedDate(new Date());
            journalEntriesExpenditure.setCreatedBy("TRAX_IFACE");
            journalEntriesExpenditure.setCreatedDate(new Date());
            
            journalEntriesExpenditure.getId().setCategoryCode("DEFAULT");
            journalEntriesExpenditure.getId().setTransaction("WIP");
            journalEntriesExpenditure.getId().setCategory("EXPENDITURE");
            
            journalEntriesExpenditure.getId().setClass_("LABOR");
            
            journalEntriesExpenditure.setJournalDescription("DEFAULT");
            journalEntriesExpenditure.setExpenditureUse("PRODUCTION");
            
            logger.info("INSERTING CODE: " + journalEntriesExpenditure.getId().getCategoryCode());
            insertData(journalEntriesExpenditure);
        }
        return journalEntriesExpenditure.getId().getCategoryCode();
    }
    
    /**
     * Finds an appropriate location for a given site
     *
     * Queries the LocationSite table to find locations associated with the
     * specified site, then verifies each location is a maintenance facility.
     *
     * @param site The site code to find a location for
     * @return The first valid maintenance facility location, or empty string if none found
     */
    private String setLocation(String site) {
        List<LocationSite> locationsites = null;
        try {
            locationsites = em.createQuery("SELECT l FROM LocationSite l WHERE l.id.site = :sit", LocationSite.class)
            .setParameter("sit", site)
            .getResultList();
            
            for(LocationSite ls : locationsites) {
                if(getLocationType(ls.getId().getLocation())) {
                    return ls.getId().getLocation();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
        }
        return "";
    }
    
    /**
     * Checks if a location is a maintenance facility
     *
     * Queries the LocationMaster table to determine if the specified
     * location is marked as a maintenance facility.
     *
     * @param loc The location code to check
     * @return true if the location is a maintenance facility, false otherwise
     */
    private boolean getLocationType(String loc) {
        LocationMaster locationMaster = null;
        try {
            locationMaster = em.createQuery("SELECT l FROM LocationMaster l WHERE l.location = :loc", LocationMaster.class)
            .setParameter("loc", loc)
            .getSingleResult();
            if(locationMaster.getMaintenanceFacility() != null && !locationMaster.getMaintenanceFacility() .isEmpty() && locationMaster.getMaintenanceFacility() .equalsIgnoreCase("Y")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
            return false;
        }
    }
    
    /**
     * Checks if an interface lock is available
     *
     * Verifies whether a lock for the specified interface type is currently held.
     * If locked, checks if the maximum lock time has been exceeded.
     *
     * @param notificationType The interface type to check
     * @return true if the lock is available or can be taken, false if locked
     */
    public boolean lockAvailable(String notificationType) {
        lock = em.createQuery("SELECT i FROM InterfaceLockMaster i where i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType).getSingleResult();
        if(lock.getLocked().intValue() == 1) {                
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime locked = LocalDateTime.ofInstant(lock.getLockedDate().toInstant(), ZoneId.systemDefault());
            Duration diff = Duration.between(locked, today);
            if(diff.getSeconds() >= lock.getMaxLock().longValue()) {
                return true;
            }
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Acquires a lock for an interface type
     *
     * Sets the lock flag to 1, records the current timestamp and server name
     * for the specified interface type.
     *
     * @param notificationType The interface type to lock
     */
    public void lockTable(String notificationType) {
        em.getTransaction().begin();
        lock = em.createQuery("SELECT i FROM InterfaceLockMaster i where i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        lock.setLocked(new BigDecimal(1));
        lock.setLockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.info(e.getMessage());
        }
        lock.setCurrentServer(address.getHostName());
        em.lock(lock, LockModeType.NONE);
        em.merge(lock);
        em.getTransaction().commit();
    }
    
    /**
     * Releases a lock for an interface type
     *
     * Sets the lock flag to 0 and records the unlock timestamp for the
     * specified interface type.
     *
     * @param notificationType The interface type to unlock
     */
    public void unlockTable(String notificationType) {
        em.getTransaction().begin();
        lock = em.createQuery("SELECT i FROM InterfaceLockMaster i where i.interfaceType = :type", InterfaceLockMaster.class)
                .setParameter("type", notificationType)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        lock.setLocked(new BigDecimal(0));
        lock.setUnlockedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        em.lock(lock, LockModeType.NONE);
        em.merge(lock);
        em.getTransaction().commit();
    }
 }