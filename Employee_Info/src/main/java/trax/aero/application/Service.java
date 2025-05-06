package trax.aero.application;



import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import trax.aero.logger.LogManager;



@Path("/EmployeeInfoService")
public class Service {
	
	EntityManagerFactory factory;
	Logger logger = LogManager.getLogger("EmployeeInfo_I01");
	
	@GET
    @Path("/healthCheck")
    @Produces(MediaType.APPLICATION_XML + ";charset=UTF-8" )
    public Response healthCheck() 
    {    	
		logger.info("Healthy");
    	return Response.ok("Healthy",MediaType.APPLICATION_XML).build();
    }
}
