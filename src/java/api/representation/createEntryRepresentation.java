package api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class createEntryRepresentation {
	private String name;
	private String genre;
	private int year;
	private double rating;
	private int id;
	
	public createEntryRepresentation(){
		// Jackson deserialization
	}

	public createEntryRepresentation(String name, String genre, int year, double rating, int id) {
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.rating = rating;
		this.id = id;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonProperty
	public String getGenre() {
		return genre;
	}
	
	@JsonProperty
	public int getYear() {
		return year;
	}
	 
	@JsonProperty
	public double getRating() {
		return rating;
	}

	@JsonProperty
	public int getId() {
		return id;
	}
}
