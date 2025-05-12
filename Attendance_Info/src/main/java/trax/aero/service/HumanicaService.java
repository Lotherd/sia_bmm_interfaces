package trax.aero.service;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import trax.aero.logger.LogManager;
import trax.aero.pojo.Import;
import trax.aero.utils.TraxHostNameVerifier;
import trax.aero.utils.TraxX509TrustManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HumanicaService {

    private static final Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    
    private String resendUrl;
    private ObjectMapper mapper;
    
    /**
     * Constructor
     * 
     * @param resendUrl URL for sending resend requests
     */
    public HumanicaService(String resendUrl) {
        this.resendUrl = resendUrl;
        this.mapper = new ObjectMapper();
    }
    
    /**
     * Send a resend request to Humanica
     * 
     * @param resendRequest The resend request object
     * @return true if the request was successful, false otherwise
     */
    public boolean sendResendRequest(Import resendRequest) {
        Client client = null;
        Response response = null;
        
        try {
            logger.info("Sending resend request to Humanica");
            
            String requestBody = mapper.writeValueAsString(resendRequest);
            logger.info("Resend request body: " + requestBody);
            
            // Create HTTP client
            if (resendUrl.startsWith("https")) {
                client = getRestSSLClient();
            } else {
                client = getRestHttpClient();
            }
            
            WebTarget webTarget = client.target(resendUrl);
            
            response = webTarget.request()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
            
            String responseBody = response.getEntity().toString();
            logger.info("Resend request response status: " + response.getStatus());
            logger.info("Resend request response body: " + responseBody);
            
            return response.getStatus() == 200 || response.getStatus() == 202;
            
        } catch (Exception e) {
            logger.severe("Error sending resend request: " + e.getMessage());
            return false;
        } finally {
        	if (response != null) {
          		 try {
          		        String body = response.getEntity().toString();
          		    } catch (Exception e) {
          	        logger.warning("Issue to close: " + e.getMessage());
          	    }
          	}
            if (client != null) {
                client.close();
            }
        }
    }
    
    /**
     * Create SSL-enabled REST client
     */
    private Client getRestSSLClient() {
        Client client = null;
        
        try {
            SSLContext sslContext = getSSLContext();
            
            client = ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .hostnameVerifier(new TraxHostNameVerifier())
                .build();
                
            client.property("Content-Type", MediaType.APPLICATION_JSON);
            client.property("accept", MediaType.APPLICATION_JSON);
            
        } catch (Exception e) {
            logger.severe("Error creating SSL client: " + e.getMessage());
        }
        
        return client;
    }
    
    /**
     * Create non-SSL REST client
     */
    private Client getRestHttpClient() {
        Client client = null;
        
        try {
            client = ClientBuilder.newClient();
            client.property("Content-Type", MediaType.APPLICATION_JSON);
            client.property("accept", MediaType.APPLICATION_JSON);
            
        } catch (Exception e) {
            logger.severe("Error creating HTTP client: " + e.getMessage());
        }
        
        return client;
    }
    
    /**
     * Get SSL context with custom trust manager
     */
    private SSLContext getSSLContext() {
        SSLContext context = null;
        
        try {
            context = SSLContext.getInstance("SSL");
            TraxX509TrustManager trustManager = new TraxX509TrustManager();
            context.init(null, new TrustManager[] { trustManager }, new SecureRandom());
            
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.severe("Error creating SSL context: " + e.getMessage());
        }
        
        return context;
    }
}