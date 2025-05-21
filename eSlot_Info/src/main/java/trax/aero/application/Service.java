package trax.aero.application;


import java.sql.SQLException;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import trax.aero.controller.SlotsAMISController;
import trax.aero.data.SlotsAMISData;
import trax.aero.logger.LogManager;



@Path("/SlotsAIMSService")
public class Service {
	
	Logger logger = LogManager.getLogger("SlotsAIMS_I31");
	
	
	@GET
    @Path("/healthCheck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthCheck() 
    {    	
		logger.info("Healthy");
    	return Response.ok("Healthy",MediaType.APPLICATION_JSON).build();
    }
	
	@GET
	@Path("/setOpsLine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setOpsLine(@QueryParam("site") String site, @QueryParam("opsLine") String opsLine,@QueryParam("email") String email )
	{
		SlotsAMISData data = new SlotsAMISData();
		
		String exceuted = "OK";
		                              
		try 
        {    		 
        	exceuted = data.setSite(site, opsLine, email);
		}
		catch(Exception e)
		{
			exceuted = e.toString();
			SlotsAMISController.addError(e.toString());
			SlotsAMISController.sendEmailService(exceuted);
		}
       finally 
       {   
    	   try 
			{
				if(data.getCon() != null && !data.getCon().isClosed())
					data.getCon().close();
			} 
			catch (SQLException e) 
			{ 
				exceuted = e.toString();
			}
    	   logger.info("finishing");
       }
	   return Response.ok(exceuted,MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/updateOpsLine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOpsLine(@QueryParam("site") String site, @QueryParam("opsLine") String opsLine)
	{
		SlotsAMISData data = new SlotsAMISData();
		
		String exceuted = "OK";
		                              
		try 
        {    		 
        	exceuted = data.updateOpsLine(site, opsLine);
		}
		catch(Exception e)
		{
			exceuted = e.toString();
			SlotsAMISController.addError(e.toString());
			SlotsAMISController.sendEmailService(exceuted);
		}
       finally 
       {   
    	   try 
			{
				if(data.getCon() != null && !data.getCon().isClosed())
					data.getCon().close();
			} 
			catch (SQLException e) 
			{ 
				exceuted = e.toString();
			}
    	   logger.info("finishing");
       }
	   return Response.ok(exceuted,MediaType.APPLICATION_JSON).build();
	}
	
	
	@GET
	@Path("/deleteOpsLine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOpsLine(@QueryParam("opsline") String opsline )
	{
		SlotsAMISData data = new SlotsAMISData();
		
		String exceuted = "OK";
		                              
		try 
        {    		 
        	data.deleteSite(opsline);
		}
		catch(Exception e)
		{
			exceuted = e.toString();
			logger.severe(e.toString());
			SlotsAMISController.addError(e.toString());
			SlotsAMISController.sendEmailService(exceuted);
		}
       finally 
       {   
    	   try 
			{
				if(data.getCon() != null && !data.getCon().isClosed())
					data.getCon().close();
			} 
			catch (SQLException e) 
			{ 
				exceuted = e.toString();
			}
    	   logger.info("finishing");
       }
	   return Response.ok(exceuted,MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getOpsLine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpsLine(@QueryParam("opsline") String opsline )
	{
		SlotsAMISData data = new SlotsAMISData();
		
		String exceuted = "OK";
		
		String group = null;
		                              
		try 
        {    		 
			group = data.getSite(opsline);
        	if(group == null ) {
        		exceuted = "Issue found";
        		throw new Exception("Issue found");
        	}
		}
		catch(Exception e)
		{
			SlotsAMISController.addError(e.toString());
			SlotsAMISController.sendEmailService(exceuted);
		}
       finally 
       {   
    	   try 
			{
				if(data.getCon() != null && !data.getCon().isClosed())
					data.getCon().close();
			} 
			catch (SQLException e) 
			{ 
				exceuted = e.toString();
			}
    	   logger.info("finishing");
       }
		
		
	   return Response.ok(group,MediaType.APPLICATION_JSON).build();
	}
	
}