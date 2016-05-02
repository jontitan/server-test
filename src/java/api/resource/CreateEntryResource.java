package api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

@Path("/api/movie/create")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateEntryResource extends MovieEntryResource {
	final static Logger logger = LoggerFactory.getLogger(CreateEntryResource.class);

//	@PUT
//	public MovieEntryResultRepresentation createEntry(@QueryParam("name") String name,
//												 @QueryParam("genre") String genre,
//												 @QueryParam("year") String year,
//												 @QueryParam("rating") String rating){
//		if(name == null){
//			throw new WebApplicationException(WRONG_NAME, 400);
//		}
//		
//		if(genre == null){
//			throw new WebApplicationException(WRONG_GENRE, 400);
//		}
//		
//		int intYear = 0;
//		try{
//			intYear = Integer.parseInt(year);
//		} catch (NumberFormatException nfe){
//			throw new WebApplicationException(WRONG_YEAR, 400);
//		}
//		
//		double doubleRating = 0;
//		try{
//			doubleRating = Double.parseDouble(rating);
//		} catch (NumberFormatException nfe){
//			throw new WebApplicationException(WRONG_RATING, 400);
//		}
//		
//		MovieEntry me = new MovieEntry(name, genre, intYear, doubleRating);
//		int id = MainApplication.imd.createEntry(me);
//		logger.info("Movie Entry Added. id: " + id +  "\n" + me.toString());
//		
//		return new MovieEntryResultRepresentation(id, me);
//	}
	
	@PUT
	public MovieEntryResultRepresentation createEntry(MovieEntryRepresentation me){
		int id = MainApplication.imd.createEntry(me);
		logger.info("Movie Entry Added. id: " + me);

		return new MovieEntryResultRepresentation(id, me);
	}
}
