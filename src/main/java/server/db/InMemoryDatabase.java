package server.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import api.representation.MovieEntryRepresentation;

public class InMemoryDatabase {
	
	private HashMap<Integer, MovieEntryRepresentation> library;
	private HashSet<Integer> ids;
	
	public InMemoryDatabase(){
		library = new HashMap<Integer, MovieEntryRepresentation>();
		ids = new HashSet<Integer>();
	}
	
	public int createEntry(MovieEntryRepresentation me){
		int id = getNewId();
		library.put(id, me);
		return id;
	}
	
	public void updateEntry(int id, MovieEntryRepresentation me){
		library.put(id, me);
	}
	
	public void deleteEntry(int id){
		library.remove(id);
		ids.remove(id);
	}
	
	private int getNewId(){
		Random r = new Random();
		int nextId;
		do{
			nextId = r.nextInt();
		} while(!ids.add(nextId));
		return nextId;
	}

	public HashMap<Integer, MovieEntryRepresentation> getLibrary() {
		return library;
	}

	public HashSet<Integer> getIds() {
		return ids;
	}

	public MovieEntryRepresentation getEntry(int intId) {
		return library.get(intId);
	}

	public boolean containsKey(int key) {
		return library.containsKey(key);
	}
}
