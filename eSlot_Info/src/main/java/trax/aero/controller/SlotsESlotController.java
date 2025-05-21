package trax.aero.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.GenericEntity;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import trax.aero.logger.LogManager;
import trax.aero.pojo.ESlotItem;

public class SlotsESlotController {

    static Logger logger = LogManager.getLogger("SlotsESlot_I31");
    EntityManagerFactory factory;
    private EntityManager em;
    static String errors = "";
    
    public SlotsESlotController() {
        factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
        em = factory.createEntityManager();
    }
    
    public static void addError(String error) {
        errors=errors.concat(error + System.lineSeparator()+ System.lineSeparator());
    }
    
    public static String getError() {
        return errors;
    }
    
    public static void sendEmail(ESlotItem item) {
        try {
            GenericEntity<ESlotItem> entity = new GenericEntity<ESlotItem>(item) {
            };
            String fromEmail = System.getProperty("fromEmail");
            String host = System.getProperty("fromHost");
            String port = System.getProperty("fromPort");
            
            final String toEmail = System.getProperty("SlotsESlot_toEmail");
            
            ArrayList<String>  emailsList = new ArrayList<String> (Arrays.asList(toEmail.split(",")));
            
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(Integer.valueOf(port));
            email.setFrom(fromEmail);
            email.setSubject("Slots eSlot interface ran into a Issue in SQS");
            for(String emails: emailsList) {
                email.addTo(emails);
            }
            email.setMsg("Slots eSlot interface " 
                    +" has encountered an issue. "    
                    + "OID " + item.getEslot().getOid() +"\n" 
                    + "Issues found at:\n"  
                    + errors);
            email.send();
        }
        catch(Exception e) {
            logger.severe(e.toString());
            logger.severe("Email not found");
        }
        finally {
            errors = "";
        }
    }
        
    public static void sendEmailService(String outcome) {
        try {
            String fromEmail = System.getProperty("fromEmail");
            String host = System.getProperty("fromHost");
            String port = System.getProperty("fromPort");
            String toEmail = System.getProperty("SlotsESlot_toEmail");
            ArrayList<String>  emailsList = new ArrayList<String>(Arrays.asList(toEmail.split(",")));
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(Integer.valueOf(port));
            email.setFrom(fromEmail);
            email.setSubject("Slots eSlot interface ran into a Issue in service");
            for(String emails: emailsList) {
                email.addTo(emails);
            }
            email.setMsg("Input" 
                     
                    +" has encountered an issue. "            
                    + "Enter records manually. "
                    + "Issues found at:\n"  
                    + errors);
            email.send();
        }
        catch(Exception e) {
            logger.severe(e.toString());
            logger.severe("Email not found");
        }
        finally {
            errors = "";
        }
    }
}