package trax.aero.application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import trax.aero.logger.LogManager;


@Path("/AttendaceInfoService")
public class Service {
	
	Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    private AttendanceInfoData dataService;
    private EmailService emailService;
    private ObjectMapper mapper;

    public Service() {
        dataService = new AttendanceInfoData();
        emailService = new EmailService();
        mapper = new ObjectMapper();
    }
	
	@GET
    @Path("/healthCheck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthCheck() 
    {    	
		logger.info("Healthy");
    	return Response.ok("Healthy",MediaType.APPLICATION_JSON).build();
    }
	
	/**
     * Endpoint for importing clock data from Humanica
     */
    @POST
    @Path("/import")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importClockData(String requestBody) {
        logger.info("Received import request");
        logger.info("Request body: " + requestBody);
        
        try {
            // Parse JSON to Import object
            Import clockData = mapper.readValue(requestBody, Import.class);
            
            if (clockData.getMessage() == null || clockData.getMessage().getHumanica() == null) {
                String errorMsg = "Invalid request structure: Missing required Message.HUMANICA data";
                logger.severe(errorMsg);
                AttendanceInfoController.addError(errorMsg);
                return Response.status(Status.BAD_REQUEST)
                    .entity("{\"status\": \"ERROR\", \"message\": \"" + errorMsg + "\"}")
                    .build();
            }
            
            logger.info("Processing import for SEQ_NO: " + clockData.getMessage().getHumanica().getSeqNo());
            
            // Process the data
            String result = dataService.processClockData(clockData);
            
            if (!"OK".equals(result)) {
                logger.warning("Processing returned warning: " + result);
                return Response.ok("{\"status\": \"WARNING\", \"message\": \"" + result + "\"}")
                    .build();
            }
            
            // Success
            return Response.ok("{\"status\": \"SUCCESS\", \"message\": \"Data processed successfully\"}")
                .build();
            
        } catch (JsonProcessingException e) {
            // JSON parsing error
            String errorMsg = "Invalid JSON format: " + e.getMessage();
            logger.severe(errorMsg);
            AttendanceInfoController.addError(errorMsg);
            return Response.status(Status.BAD_REQUEST)
                .entity("{\"status\": \"ERROR\", \"message\": \"" + errorMsg + "\"}")
                .build();
                
        } catch (AttendanceInfoException e) {
            // Business logic error
            logger.severe("Processing error: " + e.getMessage());
            AttendanceInfoController.addError(e.getMessage());
            return Response.status(Status.BAD_REQUEST)
                .entity("{\"status\": \"ERROR\", \"message\": \"" + e.getMessage() + "\"}")
                .build();
                
        } catch (Exception e) {
            // Unexpected error
            String errorMsg = "Unexpected error: " + e.getMessage();
            logger.severe(errorMsg);
            e.printStackTrace();
            AttendanceInfoController.addError(errorMsg);
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("{\"status\": \"ERROR\", \"message\": \"An unexpected error occurred\"}")
                .build();
        }
    }
	
    /**
     * Endpoint for receiving resend acknowledgments from Humanica
     */
    @POST
    @Path("/resendAck")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resendAcknowledgment(String requestBody) {
        logger.info("Received resend acknowledgment");
        logger.info("Request body: " + requestBody);
        
        try {
            // Parse JSON to Import object
            Import resendData = mapper.readValue(requestBody, Import.class);
            
            // Process acknowledgment (for logging purposes)
            if (resendData.getMessage() != null && 
                resendData.getMessage().getHumanica() != null && 
                resendData.getMessage().getHumanica().getSeqNo() != null) {
                
                logger.info("Received resend acknowledgment for SEQ_NO: " + 
                           resendData.getMessage().getHumanica().getSeqNo());
            } else {
                logger.warning("Received invalid resend acknowledgment structure");
            }
            
            // Always acknowledge receipt
            return Response.ok("{\"status\": \"SUCCESS\", \"message\": \"Acknowledgment received\"}")
                .build();
                
        } catch (Exception e) {
            String errorMsg = "Error processing resend acknowledgment: " + e.getMessage();
            logger.severe(errorMsg);
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("{\"status\": \"ERROR\", \"message\": \"" + errorMsg + "\"}")
                .build();
        }
    }
}