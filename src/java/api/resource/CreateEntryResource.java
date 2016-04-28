package api.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.representation.MovieEntryRepresentation;
import server.MainApplication;
import server.db.MovieEntry;

@Path("/api/movie/create")
@Produces(MediaType.APPLICATION_JSON)
public class CreateEntryResource {
	final static Logger logger = LoggerFactory.getLogger(CreateEntryResource.class);
	
	private final static String WRONG_NAME = "Invalid input for name";
	private final static String WRONG_GENRE = "Invalid input for genre";
	private final static String WRONG_YEAR = "Invalid input for year";
	private final static String WRONG_RATING = "Invalid input for rating";

	@POST
	public MovieEntryRepresentation createEntry(@QueryParam("name") String name,
												 @QueryParam("genre") String genre,
												 @QueryParam("year") String year,
												 @QueryParam("rating") String rating){
		if(name == null){
			throw new WebApplicationException(WRONG_NAME, 400);
		}
		
		if(genre == null){
			throw new WebApplicationException(WRONG_GENRE, 400);
		}
		
		int intYear = 0;
		try{
			intYear = Integer.parseInt(year);
		} catch (NumberFormatException nfe){
			throw new WebApplicationException(WRONG_YEAR, 400);
		}
		
		double doubleRating = 0;
		try{
			doubleRating = Double.parseDouble(rating);
		} catch (NumberFormatException nfe){
			throw new WebApplicationException(WRONG_RATING, 400);
		}
		
		MovieEntry me = new MovieEntry(name, genre, intYear, doubleRating);
		int id = MainApplication.imd.createEntry(me);
		logger.info("Movie Entry Added \n" + me.toString());
		
		return new MovieEntryRepresentation(id, me);
	}
}
