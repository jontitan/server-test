package api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.representation.ListEntriesRepresentation;
import server.MainApplication;

@Path("/api/movie/list")
@Produces(MediaType.APPLICATION_JSON)
public class ListEntriesResource {
	
	@GET
	public ListEntriesRepresentation listEntries(){
		return new ListEntriesRepresentation(MainApplication.imd);
	}
}
