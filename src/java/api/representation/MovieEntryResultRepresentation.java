package api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieEntryResultRepresentation {
	private int id;
	private MovieEntryRepresentation me;
	
	public MovieEntryResultRepresentation() {
		// Jackson deserialization
	}
	
	public MovieEntryResultRepresentation(int id, MovieEntryRepresentation me) {
		this.id = id;
		this.me = me;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
	
	@JsonProperty
	public MovieEntryRepresentation getMovieEntry() {
		return me;
	}
}
