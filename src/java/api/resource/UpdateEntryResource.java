package api.resource;

import java.util.HashSet;

import javax.ws.rs.PUT;
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

@Path("/api/movie/update")
@Produces(MediaType.APPLICATION_JSON)
public class UpdateEntryResource extends MovieEntryResource {
	final static Logger logger = LoggerFactory.getLogger(UpdateEntryResource.class);

	@PUT
	public MovieEntryRepresentation updateEntry(@QueryParam("id") String id,
												@QueryParam("name") String name,
												@QueryParam("genre") String genre,
												@QueryParam("year") String year,
												@QueryParam("rating") String rating){
		
		int intId = 0;
		int intYear = 0;
		double doubleRating = 0;
		
		try{
			intId = Integer.parseInt(id);
		} catch (NumberFormatException nfe){
			throw new WebApplicationException(WRONG_ID, 400);
		}
		
		MovieEntry me ;
		if(MainApplication.imd.containsKey(intId)){
			me = MainApplication.imd.getEntry(intId);
		} else {
			throw new WebApplicationException(WRONG_ID, 400);
		}
		
		HashSet<String> toBeUpdated = new HashSet<String>();
		
		if(name != null) {
			toBeUpdated.add("name");
			logger.info("Updating name to: " + name);
		}
		if(genre != null) {
			toBeUpdated.add("genre");
			logger.info("Updating genre to: " + genre);
		}
		if(year != null) {
			toBeUpdated.add("year");
			logger.info("Updating year to: " + year);
			try{
				intYear = Integer.parseInt(year);
			} catch (NumberFormatException nfe){
				throw new WebApplicationException(WRONG_YEAR, 400);
			}
		}
		if(rating != null) {
			toBeUpdated.add("rating");
			logger.info("Updating rating to: " + rating);
			try{
				doubleRating = Double.parseDouble(rating);
			} catch (NumberFormatException nfe){
				throw new WebApplicationException(WRONG_RATING, 400);
			}
		}
		
		if(toBeUpdated.size() == 0) {
			throw new WebApplicationException(WRONG_UPDATE, 400);
		}

		for(String s : toBeUpdated){
			switch(s){
			case "name":
				me.setName(name);
				break;
			case "genre":
				me.setGenre(genre);
				break;
			case "year":
				me.setYear(intYear);
				break;
			case "rating":
				me.setRating(doubleRating);
				break;
			}
		}
		
		MainApplication.imd.updateEntry(intId, me);
		logger.info("Movie Entry Updated. id: " + intId + "\n" + me.toString());
		
		return new MovieEntryRepresentation(intId, me);
	}
}
