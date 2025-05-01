package trax.aero.data;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import trax.aero.controller.EmployeeInfoController;
import trax.aero.exception.CustomizeHandledException;
import trax.aero.logger.LogManager;
import trax.aero.model.InterfaceLockMaster;
import trax.aero.pojo.EmployeeInfo;
import trax.aero.utils.DataSourceClient;


public class EmployeeInfoData {
	
	private Connection con;
    private EntityManagerFactory factory;
    private EntityManager em;
    private String executed = "OK";
    
 static Logger logger = LogManager.getLogger("EmployeeInfo_I01");
    
 public EmployeeInfoData() {
        factory = Persistence.createEntityManagerFactory("TraxESD");
        em = factory.createEntityManager();
        
        try {
            if (this.con == null || this.con.isClosed()) {
                this.con = DataSourceClient.getConnection();
                logger.info("The connection was established successfully with status: " + String.valueOf(!this.con.isClosed()));
            }        
        } catch (SQLException | CustomizeHandledException e) {
        	EmployeeInfoController.addError(e.toString());
            logger.severe(e.toString());
        } catch (Exception e) {
        	EmployeeInfoController.addError(e.toString());
            logger.severe(e.toString());
        }
    }
    
    public Connection getCon() {
        return con;
    }
    
    // Formatter to assign 00:00 when time-stamp is missing
    private static final DateTimeFormatter CSV_FMT = new DateTimeFormatterBuilder()
        .appendPattern("M/d/yyyy")
        .optionalStart().appendPattern(" H:mm").optionalEnd()
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .toFormatter();
    
    /**
     * Searches for an employee in the database and takes appropriate action
     * 
     * This method checks if an employee exists by relation code and then calls either
     * updateEmployee or insertEmployee based on the search result.
     * 
     * @param e The EmployeeInfo object containing employee details
     * @return A string indicating the result of the operation ("OK" or error message)
     */  	 
   	public String findEmployee(EmployeeInfo e) {
         executed = "OK";
         
         // Check if minimum required fields are provided
         if (!checkMinValue(e)) {
             executed = "Cannot find Employee due to missing required fields: employeeId or relationCode";
             logger.severe(executed);
             EmployeeInfoController.addError(executed);
             return executed;
         }
         
         try {
             // Log the relation code we're checking for debugging purposes
             logger.info("Checking if employee exists with relationCode: " + e.getRelationCode());
             
             // Query to check if the employee exists in relation_master table
             String queryStr = "SELECT COUNT(*) FROM relation_master " +
                              "WHERE relation_code = ? AND relation_transaction = 'EMPLOYEE'";
             
             PreparedStatement ps = con.prepareStatement(queryStr);
             ps.setString(1, e.getRelationCode());
             
             ResultSet rs = ps.executeQuery();
             int count = 0;
             
             if (rs.next()) {
                 count = rs.getInt(1);
             }
             
             rs.close();
             ps.close();
             
             // Log the count for debugging purposes
             logger.info("Found " + count + " existing records for relationCode: " + e.getRelationCode());
             
             // If employee exists, update the record, otherwise insert a new one
             if (count > 0) {
                 logger.info("Employee with relationCode: " + e.getRelationCode() + " found. Proceeding to update.");
                 return updateEmployee(e);
             } else {
                 logger.info("Employee with relationCode: " + e.getRelationCode() + " not found. Proceeding to insert.");
                 return insertEmployee(e);
             }
             
         } catch (Exception ex) {
             executed = "Error finding employee: " + ex.toString();
             logger.severe(executed);
             EmployeeInfoController.addError(executed);
             return executed;
         }
   	
   }
    
    /**
     * Inserts a new employee record into the database
     * 
     * This method performs the following operations:
     * 1. Validates that required fields are present
     * 2. Inserts employee data into the relation_master table
     * 3. If skill information is provided, calls insertSkill method
     * 
     * @param e The EmployeeInfo object containing employee details
     * @return A string indicating the result of the operation ("OK" or error message)
     */
    public String insertEmployee(EmployeeInfo e) {
        executed = "OK";
        
        // Validate required fields
        if (!checkMinValue(e)) {
        	executed = "Cannot insert Employee due to missing required fields: employeeId or relationCode";
            logger.severe(executed);
            EmployeeInfoController.addError(executed);
            return executed;
        }
        
        EntityTransaction transaction = null;
        
        try {
        	
        	LocalDateTime birthLdt = (e.getBirthdate() != null && !e.getBirthdate().isEmpty())
                    ? LocalDateTime.parse(e.getBirthdate(), CSV_FMT)
                    : null;
                LocalDate birthDate = birthLdt != null ? birthLdt.toLocalDate() : null;
                LocalDateTime hiredLdt = (e.getDateHired() != null && !e.getDateHired().isEmpty())
                    ? LocalDateTime.parse(e.getDateHired(), CSV_FMT)
                    : null;
                LocalDateTime termLdt = (e.getDateTerminated() != null && !e.getDateTerminated().isEmpty())
                    ? LocalDateTime.parse(e.getDateTerminated(), CSV_FMT)
                    : null;
                LocalDateTime creatLdt = (e.getCreatedDate() != null && !e.getCreatedDate().isEmpty())
                    ? LocalDateTime.parse(e.getCreatedDate(), CSV_FMT)
                    : null;
            
            // Double check if the employee already exists before trying to insert
            String checkQuery = "SELECT COUNT(*) FROM relation_master WHERE relation_code = ? AND relation_transaction = 'EMPLOYEE'";
            PreparedStatement ps = con.prepareStatement(checkQuery);
            ps.setString(1, e.getRelationCode());
            
            ResultSet rs = ps.executeQuery();
            int count = 0;
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            rs.close();
            ps.close();
            
            // If employee already exists, update instead of insert
            if (count > 0) {
                logger.warning("Employee with relationCode: " + e.getRelationCode() + " already exists. Redirecting to update.");
                return updateEmployee(e);
            }
            
            // Begin transaction
            transaction = em.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            
            // Prepare the SQL insert query with named parameters
            Query insertQuery = em.createNativeQuery(
            		"INSERT INTO relation_master (" +
            	            " relation_code, relation_transaction, name, employee_id, first_name, last_name," +
            	            " related_location, position, department, division," +
            	            " mail_phone, mail_email, date_of_birth, date_hired, date_terminated," +
            	            " gst_gl_company, cost_center, status, created_by, created_date," +
            	            " modified_by, modified_date, allow_issue_to) " +
            	            "VALUES (" +
            	            " :relationCode, 'EMPLOYEE', :fullName, :employeeId, :firstName, :lastName," +
            	            " :relatedLocation, :position, :department, :division," +
            	            " :mailPhone, :mailEmail, :birthdate, :dateHired, :dateTerminated," +
            	            " :companyName, :costCenter, :status, 'TRAX_IFACE', :createdDate," +
            	            " 'TRAX_IFACE', SYSDATE, 'YES')"
            	        );
            
            // Set parameters, handling null values appropriately
            insertQuery.setParameter("relationCode", e.getRelationCode());
            insertQuery.setParameter("fullName", e.getFullName() != null ? e.getFullName() : "");
            insertQuery.setParameter("employeeId", e.getEmployeeId());
            insertQuery.setParameter("firstName", e.getFirstName() != null ? e.getFirstName() : "");
            insertQuery.setParameter("lastName", e.getLastName() != null ? e.getLastName() : "");
            insertQuery.setParameter("relatedLocation", e.getRelatedLocation() != null ? e.getRelatedLocation() : "");
            insertQuery.setParameter("position", e.getPosition() != null ? e.getPosition() : "");
            insertQuery.setParameter("department", e.getDepartment() != null ? e.getDepartment() : "");
            //insertQuery.setParameter("departmentDescription", e.getDepartmentDescription() != null ? e.getDepartmentDescription() : "");
            insertQuery.setParameter("division", e.getDivision() != null ? e.getDivision() : "");
            //insertQuery.setParameter("divisionDescription", e.getDivisionDescription() != null ? e.getDivisionDescription() : "");
            insertQuery.setParameter("mailPhone", e.getMailPhone() != null ? e.getMailPhone() : "");
            insertQuery.setParameter("mailEmail", e.getMailEmail() != null ? e.getMailEmail() : "");
            insertQuery.setParameter("companyName", e.getCompanyName() != null ? e.getCompanyName() : "");
            insertQuery.setParameter("costCenter", e.getCostCode() != null ? e.getCostCode() : "");
            // Convert status code (1=ACTIVE, other=INACTIVE) or default to ACTIVE
            insertQuery.setParameter("status",
            	    e.getStatus() != null
            	        ? (e.getStatus().equals("1") ? "ACTIVE" : "INACTIVE")
            	        : "ACTIVE"
            	);
            
            
            // Date binds with TemporalType overload for nulls
            if (birthDate != null) {
                insertQuery.setParameter("birthdate", Date.valueOf(birthDate));
            } else {
                insertQuery.setParameter("birthdate", (java.util.Date) null, TemporalType.DATE);
            }

            if (hiredLdt != null) {
                insertQuery.setParameter("dateHired", Timestamp.valueOf(hiredLdt));
            } else {
                insertQuery.setParameter("dateHired", (java.util.Date) null, TemporalType.TIMESTAMP);
            }

            if (termLdt != null) {
                insertQuery.setParameter("dateTerminated", Timestamp.valueOf(termLdt));
            } else {
                insertQuery.setParameter("dateTerminated", (java.util.Date) null, TemporalType.TIMESTAMP);
            }

            if (creatLdt != null) {
                insertQuery.setParameter("createdDate", Timestamp.valueOf(creatLdt));
            } else {
                insertQuery.setParameter("createdDate", (java.util.Date) null, TemporalType.TIMESTAMP);
            }
            
            // Execute the insert query
            int rowsAffected = insertQuery.executeUpdate();
            
            // Commit the transaction
            transaction.commit();
            
            logger.info("Successfully inserted employee: " + e.getEmployeeId() + " with relation code: " + e.getRelationCode() + ". Rows affected: " + rowsAffected);
            
            // If skill information is provided, insert it
            if (e.getSkill() != null && !e.getSkill().isEmpty()) {
                insertSkill(e);
            }
            
        } catch (Exception ex) {
            // Rollback transaction in case of error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            
            // Check if it's a unique constraint violation
            if (ex.toString().contains("unique constraint") || 
                ex.toString().contains("ORA-00001") || 
                ex.toString().contains("P_RELATION_MASTER")) {
                logger.warning("Attempted to insert employee with relationCode: " + e.getRelationCode() + " that already exists. Trying to update instead.");
                return updateEmployee(e);
            }
            
            executed = "Error inserting employee: " + ex.toString();
            logger.severe(executed);
            EmployeeInfoController.addError(executed);
        }
        
        return executed;
    }
    
    /**
     * Updates an existing employee record in the database
     * 
     * This method:
     * 1. Validates that required fields are present
     * 2. Checks if the employee exists, if not, calls insertEmployee
     * 3. Updates the employee data in relation_master table
     * 4. If skill information is provided, calls insertSkill method
     * 
     * @param e The EmployeeInfo object containing updated employee details
     * @return A string indicating the result of the operation ("OK" or error message)
     */
    public String updateEmployee(EmployeeInfo e) {
    	executed = "OK";
        
        // Validate required fields
        if (!checkMinValue(e)) {
            executed = "Cannot update Employee due to missing required fields: employeeId or relationCode";
            logger.severe(executed);
            EmployeeInfoController.addError(executed);
            return executed;
        }
        
        EntityTransaction transaction = null;
        
        try {
        	
        	// Parse dates from CSV
        	LocalDateTime birthLdt = (e.getBirthdate() != null && !e.getBirthdate().isEmpty())
                    ? LocalDateTime.parse(e.getBirthdate(), CSV_FMT)
                    : null;
                LocalDate birthDate = birthLdt != null ? birthLdt.toLocalDate() : null;
                LocalDateTime hiredLdt = (e.getDateHired() != null && !e.getDateHired().isEmpty())
                    ? LocalDateTime.parse(e.getDateHired(), CSV_FMT)
                    : null;
                LocalDateTime termLdt = (e.getDateTerminated() != null && !e.getDateTerminated().isEmpty())
                    ? LocalDateTime.parse(e.getDateTerminated(), CSV_FMT)
                    : null;
                LocalDateTime modLdt = (e.getModifiedDate() != null && !e.getModifiedDate().isEmpty())
                    ? LocalDateTime.parse(e.getModifiedDate(), CSV_FMT)
                    : null;
            
            
            // Verify the employee exists in the database
            String checkQuery = "SELECT COUNT(*) FROM relation_master WHERE relation_code = ? AND relation_transaction = 'EMPLOYEE'";
            PreparedStatement ps = con.prepareStatement(checkQuery);
            ps.setString(1, e.getRelationCode());
            
            ResultSet rs = ps.executeQuery();
            int count = 0;
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            rs.close();
            ps.close();
            
            // If employee doesn't exist, insert instead of update
            if (count == 0) {
                logger.warning("Employee not found with relationCode: " + e.getRelationCode() + ". Proceeding to insert.");
                return insertEmployee(e);
            }
            
            // Begin transaction
            transaction = em.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            
            // Prepare the SQL update query with named parameters
            Query updateQuery = em.createNativeQuery(
            		 "UPDATE relation_master SET " +
            	                " name = :fullName, first_name = :firstName, last_name = :lastName, " +
            	                " related_location = :relatedLocation, position = :position, " +
            	                " department = :department, division = :division, " +
            	                " mail_phone = :mailPhone, mail_email = :mailEmail, " +
            	                " date_of_birth = :birthdate, date_hired = :dateHired, date_terminated = :dateTerminated, " +
            	                " gst_gl_company = :companyName, cost_center = :costCode, " +
            	                " status = :status, modified_by = 'TRAX_IFACE', modified_date = :modifiedDate, " +
            	                " allow_issue_to = 'YES' " +
            	                "WHERE relation_code = :relationCode AND relation_transaction = 'EMPLOYEE'"
            	            );
            
            // Set parameters, handling null values appropriately
            updateQuery.setParameter("relationCode", e.getRelationCode());
            updateQuery.setParameter("fullName", e.getFullName() != null ? e.getFullName() : "");
            updateQuery.setParameter("firstName", e.getFirstName() != null ? e.getFirstName() : "");
            updateQuery.setParameter("lastName", e.getLastName() != null ? e.getLastName() : "");
            updateQuery.setParameter("relatedLocation", e.getRelatedLocation() != null ? e.getRelatedLocation() : "");
            updateQuery.setParameter("position", e.getPosition() != null ? e.getPosition() : "");
            updateQuery.setParameter("department", e.getDepartment() != null ? e.getDepartment() : "");
            //updateQuery.setParameter("departmentDescription", e.getDepartmentDescription() != null ? e.getDepartmentDescription() : "");
            updateQuery.setParameter("division", e.getDivision() != null ? e.getDivision() : "");
            //updateQuery.setParameter("divisionDescription", e.getDivisionDescription() != null ? e.getDivisionDescription() : "");
            updateQuery.setParameter("mailPhone", e.getMailPhone() != null ? e.getMailPhone() : "");
            updateQuery.setParameter("mailEmail", e.getMailEmail() != null ? e.getMailEmail() : "");
            updateQuery.setParameter("companyName", e.getCompanyName() != null ? e.getCompanyName() : "");
            updateQuery.setParameter("costCode", e.getCostCode() != null ? e.getCostCode() : "");
            // Convert status code (1=ACTIVE, other=INACTIVE) or default to ACTIVE
            updateQuery.setParameter("status",
            	    e.getStatus() != null
        	        ? (e.getStatus().equals("1") ? "ACTIVE" : "INACTIVE")
        	        : "ACTIVE"
        	);
            //updateQuery.setParameter("profile", e.getProfile() != null ? e.getProfile() : "");
            
            // Date binds with TemporalType overload for nulls
            if (birthDate != null) {
            	updateQuery.setParameter("birthdate", Date.valueOf(birthDate));
            } else {
            	updateQuery.setParameter("birthdate", (java.util.Date) null, TemporalType.DATE);
            }

            if (hiredLdt != null) {
            	updateQuery.setParameter("dateHired", Timestamp.valueOf(hiredLdt));
            } else {
            	updateQuery.setParameter("dateHired", (java.util.Date) null, TemporalType.TIMESTAMP);
            }

            if (termLdt != null) {
            	updateQuery.setParameter("dateTerminated", Timestamp.valueOf(termLdt));
            } else {
            	updateQuery.setParameter("dateTerminated", (java.util.Date) null, TemporalType.TIMESTAMP);
            }

            if (modLdt != null) {
                updateQuery.setParameter("modifiedDate", Timestamp.valueOf(modLdt));
            } else {
                updateQuery.setParameter("modifiedDate", (java.util.Date) null, TemporalType.TIMESTAMP);
            }
           
            
            // Execute the update query
            int rowsAffected = updateQuery.executeUpdate();
            
            // Commit the transaction
            transaction.commit();
            
            logger.info("Successfully updated employee: " + e.getEmployeeId() + " with relation code: " + e.getRelationCode() + ". Rows affected: " + rowsAffected);
            
            // If skill information is provided, insert it
            if (e.getSkill() != null && !e.getSkill().isEmpty()) {
                insertSkill(e);
            }
            
        } catch (Exception ex) {
            // Rollback transaction in case of error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            executed = "Error updating employee: " + ex.toString();
            logger.severe(executed);
            EmployeeInfoController.addError(executed);
        }
        
        return executed;
   	
   }
    
    /**
     * Inserts skill information for an employee
     * 
     * This method:
     * 1. Validates that required fields are present (relationCode and skill)
     * 2. Calls insertSkillMaster to ensure the skill exists in skill_master table
     * 3. Checks if the employee already has the skill
     * 4. Inserts the skill data into employee_skill table if not already present
     * 
     * @param e The EmployeeInfo object containing employee and skill details
     * @return A string indicating the result of the operation ("OK" or error message)
     */
    public String insertSkill(EmployeeInfo e) {
    	    executed = "OK";
    	    
    	    // Validate required fields
    	    if (e.getRelationCode() == null || e.getRelationCode().isEmpty() || 
    	        e.getSkill() == null || e.getSkill().isEmpty()) {
    	        executed = "Cannot insert Skill: relationCode and skill cannot be null or empty";
    	        logger.severe(executed);
    	        EmployeeInfoController.addError(executed);
    	        return executed;
    	    }
    	    
    	    // First ensure the skill exists in skill_master table
    	    String skillMasterResult = insertSkillMaster(e);
    	    
    	    if (!skillMasterResult.equals("OK")) {
    	        return skillMasterResult;
    	    }
    	    
    	    try {
    	        // Check if employee already has this skill
    	        String checkQuery = "SELECT COUNT(*) FROM employee_skill " +
    	                           "WHERE employee = ? AND skill = ?";
    	        
    	        PreparedStatement ps = con.prepareStatement(checkQuery);
    	        ps.setString(1, e.getRelationCode());
    	        ps.setString(2, e.getSkill());
    	        
    	        ResultSet rs = ps.executeQuery();
    	        int count = 0;
    	        
    	        if (rs.next()) {
    	            count = rs.getInt(1);
    	        }
    	        
    	        rs.close();
    	        ps.close();
    	        
    	        // If skill already exists for this employee, return a warning
    	        if (count > 0) {
    	            String message = "Skill " + e.getSkill() + " already exists for employee with relationCode " + e.getRelationCode() + ". No insertion needed.";
    	            logger.info(message);
    	            
    	            return executed; // Return "OK" instead of an error message
    	        }
    	        
    	        // If skill doesn't exist for this employee, proceed with insertion
    	        EntityTransaction transaction = null;
    	        
    	        try {
    	            // Begin transaction
    	            transaction = em.getTransaction();
    	            if (!transaction.isActive()) {
    	                transaction.begin();
    	            }
    	            
    	            // Prepare the SQL insert query with named parameters
    	            Query insertQuery = em.createNativeQuery(
    	                "INSERT INTO employee_skill (" +
    	                "  employee, skill, created_by, created_date, modified_by, modified_date, AC_TYPE, AC_SERIES" +
    	                ") VALUES (" +
    	                "  :relationCode, :skillCode, 'TRAX_IFACE', SYSDATE, 'TRAX_IFACE', SYSDATE, '          ', '          ' " +
    	                ")"
    	            );
    	            
    	            insertQuery.setParameter("relationCode", e.getRelationCode() != null ? e.getRelationCode() : "");
    	            insertQuery.setParameter("skillCode", e.getSkill() != null ? e.getSkill() : "");
    	            
    	            // Execute the insert query
    	            int rowsAffected = insertQuery.executeUpdate();
    	            
    	            // Commit the transaction
    	            transaction.commit();
    	            
    	            logger.info("Successfully inserted skill: " + e.getSkill() + " for employee with relation code: " + 
    	                       e.getRelationCode() + ". Rows affected: " + rowsAffected);
    	            
    	        } catch (Exception ex) {
    	            // Rollback transaction in case of error
    	            if (transaction != null && transaction.isActive()) {
    	                transaction.rollback();
    	            }
    	            executed = "Error inserting skill: " + ex.toString();
    	            logger.severe(executed);
    	            EmployeeInfoController.addError(executed);
    	        }
    	        
    	    } catch (Exception ex) {
    	        executed = "Error checking for existing skill: " + ex.toString();
    	        logger.severe(executed);
    	        EmployeeInfoController.addError(executed);
    	    }
    	    
    	    return executed;
    	}
    
    /**
     * Inserts a skill into the skill_master table if it doesn't already exist
     * 
     * This method:
     * 1. Validates that the skill is not null or empty
     * 2. Checks if the skill already exists in skill_master table
     * 3. Inserts the skill into skill_master table if it doesn't exist
     * 
     * @param e The EmployeeInfo object containing skill details
     * @return A string indicating the result of the operation ("OK" or error message)
     */
    public String insertSkillMaster(EmployeeInfo e) {
    	  executed = "OK";
    	    
    	  // Validate required field
    	  if (e.getSkill() == null || e.getSkill().isEmpty()) {
    	      executed = "Cannot insert SkillMaster: skill cannot be null or empty";
    	      logger.severe(executed);
    	      EmployeeInfoController.addError(executed);
    	      return executed;
    	  }
    	    
    	  try {
    	      // Check if skill already exists in skill_master table
    	      String checkQuery = "SELECT COUNT(*) FROM skill_master WHERE skill = ?";
    	        
    	      PreparedStatement ps = con.prepareStatement(checkQuery);
    	      ps.setString(1, e.getSkill());
    	        
    	      ResultSet rs = ps.executeQuery();
    	      int count = 0;
    	        
    	      if (rs.next()) {
    	          count = rs.getInt(1);
    	      }
    	        
    	      rs.close();
    	      ps.close();
    	        
    	      // If skill already exists in skill_master, no need to insert
    	      if (count > 0) {
    	          logger.info("Skill " + e.getSkill() + " already exists in SKILL_MASTER table. No insertion needed.");
    	          return executed;
    	      }
    	        
    	      // If skill doesn't exist, proceed with insertion
    	      EntityTransaction transaction = null;
    	        
    	      try {
    	          // Begin transaction
    	          transaction = em.getTransaction();
    	          if (!transaction.isActive()) {
    	              transaction.begin();
    	          }
    	            
    	          // Prepare the SQL insert query with named parameters
    	          // Default values are set for MECHANIC, INSPECTOR, ETOPS, and DEFECT
    	          Query insertQuery = em.createNativeQuery(
    	              "INSERT INTO skill_master (" +
    	              "  skill, skill_description, status, created_by, created_date, modified_by, modified_date, MECHANIC, INSPECTOR, ETOPS, DEFECT" +
    	              ") VALUES (" +
    	              "  :skillCode, :skillDescription, 'ACTIVE', 'TRAX_IFACE', SYSDATE, 'TRAX_IFACE', SYSDATE, 'Y', 'Y', 'N', 'N'" +
    	              ")"
    	          );
    	            
    	          insertQuery.setParameter("skillCode", e.getSkill());
    	          insertQuery.setParameter("skillDescription", e.getSkillDescription() != null ? e.getSkillDescription() : e.getSkill());
    	            
    	          // Execute the insert query
    	          int rowsAffected = insertQuery.executeUpdate();
    	            
    	          // Commit the transaction
    	          transaction.commit();
    	            
    	          logger.info("Successfully inserted skill: " + e.getSkill() + " into SKILL_MASTER table. Rows affected: " + rowsAffected);
    	            
    	      } catch (Exception ex) {
    	          // Rollback transaction in case of error
    	          if (transaction != null && transaction.isActive()) {
    	              transaction.rollback();
    	          }
    	          executed = "Error inserting skill in SKILL_MASTER: " + ex.toString();
    	          logger.severe(executed);
    	          EmployeeInfoController.addError(executed);
    	      }
    	        
    	  } catch (Exception ex) {
    	      executed = "Error checking for existing skill in SKILL_MASTER: " + ex.toString();
    	      logger.severe(executed);
    	      EmployeeInfoController.addError(executed);
    	  }
    	    
    	  return executed;
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



    /**
     * Validates that the minimum required fields are present in the EmployeeInfo object
     * 
     * @param e The EmployeeInfo object to validate
     * @return true if all required fields are present, false otherwise
     */
    private boolean checkMinValue(EmployeeInfo e) {
        if (e.getEmployeeId() == null || e.getEmployeeId().isEmpty()) {
        	EmployeeInfoController.addError("Cannot insert/update Employee: " + e.getEmployeeId() + " due to ERROR EmployeeID");
            return false;
        }

        if (e.getRelationCode() == null || e.getRelationCode().isEmpty()) {
        	EmployeeInfoController.addError("Cannot insert/update Employee: " + e.getRelationCode() + " due to ERROR StaffNumber");
            return false;
        }


        return true;
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