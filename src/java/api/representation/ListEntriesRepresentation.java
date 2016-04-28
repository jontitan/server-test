package api.representation;

import java.util.ArrayList;
import java.util.List;

import server.db.InMemoryDatabase;
import server.db.MovieEntry;

public class ListEntriesRepresentation {
	private List<MovieEntry> entries;

	public ListEntriesRepresentation(InMemoryDatabase imd) {
		this.entries = new ArrayList<MovieEntry>(imd.getLibrary().values());
	}

	public List<MovieEntry> getEntries() {
		return entries;
	}
}
