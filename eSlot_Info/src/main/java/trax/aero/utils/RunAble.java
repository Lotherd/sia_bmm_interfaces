package trax.aero.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import trax.aero.controller.SlotsESlotController;

import trax.aero.data.SlotsESlotData;
import trax.aero.logger.LogManager;
import trax.aero.pojo.ESlotItem;

public class RunAble implements Runnable {
    
    //Variables
    SlotsESlotData data = null;
    static Logger logger = LogManager.getLogger("SlotsESlot_I31");
    String queueUrlFrom = System.getProperty("SlotsESlot_FromSQS");
    SqsClient sqsClient = null;
    ObjectMapper Obj = null;
    
    public RunAble() {
        data = new SlotsESlotData("EM");
        sqsClient = SqsClient.builder().build();
        Obj = new ObjectMapper();
    }
    
    private void process() {
        try {
            String exceuted = "OK";
            
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().maxNumberOfMessages(10).queueUrl(queueUrlFrom).build();
            
            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            
            for (Message m : messages) {
                try {
                    exceuted = "OK";
                    String body = m.body();
                    
                    body = body.replaceAll("\u200B", "").trim();
                    body = body.replaceAll("[\\p{Cf}]", "");
                    logger.info("Message Body: " + body);
                    ESlotItem request = new ESlotItem(); 
                    try {
                        request = Obj.readValue(body, ESlotItem.class);
                    } catch(Exception e) {
                        exceuted ="Parsing JSON ERROR";
                        logger.severe(exceuted);
                        logger.severe(e.toString());
                    }
                    if(request.getEslot().getOid() != null && !request.getEslot().getOid().isEmpty()) {
                        logger.info("Oid: " + request.getEslot().getOid()); 
                    }
                    
                    try {    
                        exceuted = data.slot(request);
                        if(exceuted == null || !exceuted.equalsIgnoreCase("OK")) {
                            exceuted = "Issue found";
                            throw new Exception("Issue found");
                        } else {
                            exceuted = "WO " + data.WO;
                        }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        SlotsESlotController.addError(e.toString());
                        SlotsESlotController.sendEmail(request);
                    }
                   finally {   
                       logger.info("finishing");
                   }
                } catch(Exception e) {
                    logger.severe(e.toString());
                }
                finally {
                    DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder().queueUrl(queueUrlFrom).receiptHandle(m.receiptHandle()).build();
                    sqsClient.deleteMessage(deleteMessageRequest);  
                }
            }     
        }
        catch(Throwable e) {
            e.printStackTrace();
            logger.severe(e.toString());
        }
    }
    
    public void run() {
        try {
            if(data.lockAvailable("I31")) {
                data.lockTable("I31");
                process();
                data.unlockTable("I31");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }        
    }
}