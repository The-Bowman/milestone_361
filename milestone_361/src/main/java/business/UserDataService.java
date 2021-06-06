package business;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import beans.User;
import beans.ResponseDataModel;
import database.DatabaseAccess;

@RequestScoped
@Path("/users")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UserDataService {
	
	@GET
	@Path("/getuserj/{username}")
	@Produces(MediaType.APPLICATION_JSON) 
	public ResponseDataModel getAlbum(@PathParam("username") String username) {
		
		DatabaseAccess da = new DatabaseAccess();
		User temp = da.getUserByName(username);
		ResponseDataModel rdm = new ResponseDataModel(0, "", temp);
		
		
//		MusicManager mm = new MusicManager();		
//		Album temp = service.getAlbum(new Album(title, artist, year));
//		ResponseDataModel rdm = new ResponseDataModel(0, "", temp);
		return rdm;
	}

}
