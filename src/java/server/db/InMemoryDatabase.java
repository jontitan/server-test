package server.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class InMemoryDatabase {
	
	private HashMap<Integer, MovieEntry> library;
	private HashSet<Integer> ids;
	
	public InMemoryDatabase(){
		library = new HashMap<Integer, MovieEntry>();
		ids = new HashSet<Integer>();
	}
	
	public int createEntry(MovieEntry me){
		int id = getNewId();
		library.put(id, me);
		return id;
	}
	
	public void updateEntry(int id, MovieEntry me){
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
}
