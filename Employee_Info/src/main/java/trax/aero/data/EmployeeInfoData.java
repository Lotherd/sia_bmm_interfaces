package trax.aero.data;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


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
        factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
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
    
    public String findEmployee(EmployeeInfo e) {
   	 executed = "OK";
   	 
   	 
		return executed;
   	
   }
    
    public String insertEmployee(EmployeeInfo e) {
    	 executed = "OK";
    	 
    	 
		return executed;
    	
    }
    
    public String updateEmployee(EmployeeInfo e) {
   	 executed = "OK";
	 
	 
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



    private boolean checkMinValue(EmployeeInfo e) {
        if (e.getEmployeeId() == null || e.getEmployeeId().isEmpty()) {
        	EmployeeInfoController.addError("Cannot insert/update Employee: " + e.getEmployeeId() + " due to ERROR StaffNumber");
            return false;
        }

        if (e.getRelationCode() == null || e.getRelationCode().isEmpty()) {
        	EmployeeInfoController.addError("Cannot insert/update Employee: " + e.getEmployeeId() + " due to ERROR AuthorizationNumber");
            return false;
        }


        return true;
    }

    // Delete generic data from the database
    private <T> void deleteData(T data) {
        try {
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.remove(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            executed = "deleteData encountered an Exception: " + e.toString();
            EmployeeInfoController.addError(executed);
            logger.severe(e.toString());
        }
    }
    
    private <T> void insertData( T data, String s) 
	{
		try 
		{	
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
				em.merge(data);
			em.getTransaction().commit();
		}catch (Exception e)
		{
			logger.severe(e.toString());
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
