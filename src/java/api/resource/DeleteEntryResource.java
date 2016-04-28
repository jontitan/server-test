package api.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import api.representation.MovieEntryRepresentation;
import server.MainApplication;
import server.db.MovieEntry;

@Path("/api/movie/delete")
@Produces(MediaType.APPLICATION_JSON)
public class DeleteEntryResource {
	private final static String WRONG_ID = "Invalid input for id";
	
	@DELETE
	public MovieEntryRepresentation deleteEntry(@QueryParam("id") String id){	
		int intId = 0;
		try{
			intId = Integer.parseInt(id);
		} catch (NumberFormatException nfe){
			throw new WebApplicationException(WRONG_ID, 400);
		}
		MovieEntry me = MainApplication.imd.getEntry(intId);
		MainApplication.imd.deleteEntry(intId);
		return new MovieEntryRepresentation(intId, me);
	}
}
