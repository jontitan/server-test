package server.db;

public class MovieEntry {

	private String name;
	private String genre;
	private int year;
	private double rating;
	
	public MovieEntry(String name, String genre, int year, double rating) {
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	} 
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(name);
		sb.append("\n");
		sb.append("Genre: ");
		sb.append(genre);
		sb.append("\n");
		sb.append("Year: ");
		sb.append(year);
		sb.append("\n");
		sb.append("Rating: ");
		sb.append(rating);
		sb.append("\n");
		return sb.toString();
	}
}
