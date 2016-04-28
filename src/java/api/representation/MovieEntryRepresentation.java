package api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import server.db.MovieEntry;

public class MovieEntryRepresentation {
	private int id;
	private MovieEntry me;
	
	public MovieEntryRepresentation() {
		// Jackson deserialization
	}
	
	public MovieEntryRepresentation(int id, MovieEntry me) {
		this.id = id;
		this.me = me;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
	
	@JsonProperty
	public MovieEntry getMovieEntry() {
		return me;
	}
}
