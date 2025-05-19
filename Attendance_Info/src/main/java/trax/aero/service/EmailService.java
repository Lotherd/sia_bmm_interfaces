package trax.aero.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import trax.aero.controller.AttendanceInfoController;
import trax.aero.logger.LogManager;
import trax.aero.pojo.Import;

public class EmailService {

    private static final Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    
    /**
     * Send error notification email
     * 
     * @param clockData The clock data that caused the error
     */
    public void sendErrorNotification(Import clockData) {
        try {
            logger.info("Sending error notification email");
            
            String fromEmail = System.getProperty("fromEmail");
            String host = System.getProperty("fromHost");
            String port = System.getProperty("fromPort");
            
            final String toEmail = System.getProperty("AttendaceInfo_toEmail");
            
            ArrayList<String> emailsList = new ArrayList<>(Arrays.asList(toEmail.split(",")));
            
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(Integer.valueOf(port));
            email.setFrom(fromEmail);
            email.setSubject("Import Clock On Off interface error");
            
            for (String recipient : emailsList) {
                email.addTo(recipient);
            }
            
            String seqNo = clockData.getMessage().getHumanica().getSeqNo().toString();
            
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Import Clock On Off interface has encountered an issue.\n");
            emailBody.append("SEQ NO ").append(seqNo).append("\n\n");
            emailBody.append("Issues found at:\n");
            emailBody.append(AttendanceInfoController.getErrors());
            
            email.setMsg(emailBody.toString());
            
            email.send();
            
            logger.info("Error notification email sent");
            
            // Clear errors after sending
            AttendanceInfoController.clearErrors();
            
        } catch (Exception e) {
            logger.severe("Error sending notification email: " + e.getMessage());
        }
    }
}