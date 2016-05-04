package api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.representation.ListIdsRepresentation;
import server.MainApplication;

@Path("/api/movie/ids")
@Produces(MediaType.APPLICATION_JSON)
public class ListIdsResource {
	
	@GET
	public ListIdsRepresentation listIds(){
		return new ListIdsRepresentation(MainApplication.imd);
	}
}
