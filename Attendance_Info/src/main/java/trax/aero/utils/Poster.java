package trax.aero.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import trax.aero.logger.LogManager;
import trax.aero.pojo.Item;
import trax.aero.pojo.Task;
/**
 * Utility class for posting data to external services
 */
public class Poster {

    private Logger logger = LogManager.getLogger("AttendaceInfo_I03");
    private String body = null;
    
    /**
     * Post task data to external service
     * 
     * @param data Task data to post
     * @param URL Destination URL
     * @return true if successful, false otherwise
     */
    public boolean postTask(Task data, String URL) {
        Client client = null;
        Response response = null;

        try {
            String url = URL;

            if (url == null) {
                return false;
            }

            if (url.startsWith("https")) {
                client = getRestSSLClient(MediaType.APPLICATION_JSON, null);
            } else {
                client = getRestHttpClient(MediaType.APPLICATION_JSON, null);
            }

            WebTarget webTarget = client.target(url);
            
            Builder builder = webTarget.request();
            builder = builder.header("Content-type", MediaType.APPLICATION_JSON);
            builder = builder.header("Accept", MediaType.APPLICATION_JSON);
            
            logger.info("POSTING Task: " + data.getTaskCard() + " to URL: " + url);
            body = null;
            
            response = builder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
            body = response.getEntity().toString();
            
            logger.info("Response: " + response.getStatus() + " Response Body: " + body);
            
            if (response.getStatus() == 200 || response.getStatus() == 202) {
                return true;
            }
            
            body = null;
            return false;
            
        } catch (Exception exc) {
            logger.severe("Error posting task: " + exc.toString());
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
     * Post item data to external service
     * 
     * @param data Item data to post
     * @param URL Destination URL
     * @return true if successful, false otherwise
     */
    public boolean postItem(Item data, String URL) {
        Client client = null;
        Response response = null;

        try {
            String url = URL;

            if (url == null) {
                return false;
            }

            if (url.startsWith("https")) {
                client = getRestSSLClient(MediaType.APPLICATION_JSON, null);
            } else {
                client = getRestHttpClient(MediaType.APPLICATION_JSON, null);
            }

            WebTarget webTarget = client.target(url);
            
            Builder builder = webTarget.request();
            builder = builder.header("Content-type", MediaType.APPLICATION_JSON);
            builder = builder.header("Accept", MediaType.APPLICATION_JSON);
            
            logger.info("POSTING Item: " + data.getTaskCard() + " to URL: " + url);
            body = null;
            
            response = builder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
            body = response.getEntity().toString();
            
            logger.info("Response: " + response.getStatus() + " Response Body: " + body);
            
            if (response.getStatus() == 200 || response.getStatus() == 202) {
                return true;
            }
            
            body = null;
            return false;
            
        } catch (Exception exc) {
            logger.severe("Error posting item: " + exc.toString());
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
    private Client getRestSSLClient(String accept, String contentType) {
        Client client = null;
        try {
            ClientBuilder clientBuilder = ClientBuilder.newBuilder();
            clientBuilder = clientBuilder.sslContext(getSSLContext());
            clientBuilder = clientBuilder.hostnameVerifier(new TraxHostNameVerifier());
            client = clientBuilder.build();

            if (contentType != null) {
                client.property("Content-Type", contentType);
            }

            if (accept != null) {
                client.property("accept", accept);
            }
        } catch (Exception exc) {
            logger.severe("Error creating SSL client: " + exc.toString());
            exc.printStackTrace();
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
        } catch (NoSuchAlgorithmException exc) {
            logger.severe("NoSuchAlgorithmException: " + exc.toString());
            exc.printStackTrace();
        }
        try {
            TraxX509TrustManager trustMger = new TraxX509TrustManager();
            context.init(null, new TrustManager[] { trustMger }, new SecureRandom());
        } catch (KeyManagementException e) {
            logger.severe("KeyManagementException: " + e.toString());
            e.printStackTrace();
        }
        return context;
    }
    
    /**
     * Create non-SSL REST client
     */
    private Client getRestHttpClient(String accept, String contentType) {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            if (contentType != null) {
                client.property("Content-Type", contentType);
            }

            if (accept != null) {
                client.property("accept", accept);
            }
        } catch (Exception exc) {
            logger.severe("Error creating HTTP client: " + exc.toString());
            exc.printStackTrace();
        }
        return client;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}