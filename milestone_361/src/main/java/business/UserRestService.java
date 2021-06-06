package business;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.LoggingInterceptor;
import beans.User;
import database.DatabaseAccessInterface;

@RequestScoped
@Path("/music")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
@Interceptors(LoggingInterceptor.class) 
public class UserRestService {
	
	@Inject
	DatabaseAccessInterface dai;
	
	@GET
	@Path("/getuserj/{username}")
	@Produces(MediaType.APPLICATION_JSON) 
	public String getUserJ(@PathParam("username") String username) {
		User temp = dai.getUserByName(username);
		return temp.toString();
		
	}
	
	@GET
	@Path("/getuserx/{username}")
	@Produces(MediaType.APPLICATION_XML) 
	public String getUserX(@PathParam("username") String username) {
		User temp = dai.getUserByName(username);
		return temp.toString();
		
	}
}
