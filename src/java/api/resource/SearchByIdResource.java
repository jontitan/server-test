package api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.representation.MovieEntryRepresentation;
import api.representation.MovieEntryResultRepresentation;
import server.MainApplication;

@Path("/api/movie/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchByIdResource extends MovieEntryResource {
	final static Logger logger = LoggerFactory.getLogger(SearchByIdResource.class);

	@GET
	public MovieEntryResultRepresentation updateEntry(@QueryParam("id") String id){
		int intId = 0;
		
		try{
			intId = Integer.parseInt(id);
		} catch (NumberFormatException nfe){
			throw new WebApplicationException(WRONG_ID, 400);
		}
		
		MovieEntryRepresentation mer ;
		if(MainApplication.imd.containsKey(intId)){
			mer = MainApplication.imd.getEntry(intId);
		} else {
			throw new WebApplicationException(WRONG_ID, 400);
		}
		
		logger.info("Movie Entry Searched. id: " + id + "\n" + mer);
		return new MovieEntryResultRepresentation(intId, mer);
	}
}
