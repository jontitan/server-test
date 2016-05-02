package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.*;

public class IntegrationTest {

	protected Client client;
	protected static final String URL = "http://localhost:8080";
	protected static final String TOD_API = "/api/timeOfDay";
	protected static final String CREATE_API = "/api/movie/create";
	protected static final String UPDATE_API = "/api/movie/update";
	protected static final String DELETE_API = "/api/movie/delete";
	protected static final String LIST_API = "/api/movie/list";
	protected static final String SEARCH_API = "/api/movie/search";
	protected static final String IDS_API = "/api/movie/ids";

	@Before
	public void setUp() throws Exception {
		client = ClientBuilder.newClient();
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void timeOfDayTest() throws ParseException{
		Response rs = client.target(URL+TOD_API).request().get();
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(rs.readEntity(String.class));

		assertEquals(200, rs.getStatus());
		assertEquals(8, json.get("time").toString().length());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createListIdsDeleteTest() throws ParseException {
		WebTarget target; 
		Response rs;
		JSONParser parser = new JSONParser();
		JSONObject jsonResponse;
		int[] ids = new int[10];

		// Find out initial number of movies in DB
		target = client.target(URL+LIST_API);
		rs = target.request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		int originalSize = ((JSONArray)jsonResponse.get("entries")).size();

		// Create 10 new movies
		target = client.target(URL+CREATE_API);	
		for(int i = 0; i < 10; ++i){
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("name", "MovieName" + i);
			jsonRequest.put("genre", "MovieGenre" + i);
			jsonRequest.put("year", "200" + i);
			jsonRequest.put("rating", i + "." + i);
			rs = target.request().put(Entity.json(jsonRequest));
			assertEquals(200, rs.getStatus());
			jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
			ids[i] = Math.toIntExact((long) jsonResponse.get("id"));
		}

		// List all movies and verify there are 10 more
		target = client.target(URL+LIST_API);
		rs = target.request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		JSONArray entries = (JSONArray)jsonResponse.get("entries");
		assertEquals(10+originalSize, entries.size());

		// Verify number of ids matches number of movies in the DB
		target = client.target(URL+IDS_API);
		rs = target.request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		int numIds = ((JSONArray)jsonResponse.get("ids")).size();
		assertEquals(10+originalSize, numIds);

		// Delete the 10 movies just created
		for(int id : ids){
			target = client.target(URL+DELETE_API);
			rs = target.queryParam("id", id).request().delete();
			assertEquals(200, rs.getStatus());
		}

		// Verified the number of movies is back to original
		target = client.target(URL+LIST_API);
		rs = target.request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		assertEquals(originalSize, ((JSONArray)jsonResponse.get("entries")).size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createSearchUpdateTest() throws ParseException {
		WebTarget target;
		Response rs;
		JSONParser parser = new JSONParser();
		JSONObject jsonResponse;
		JSONObject movieEntry;
		JSONObject jsonRequest = new JSONObject();

		// Create an entry
		jsonRequest.put("name", "MovieName");
		jsonRequest.put("genre", "MovieGenre");
		jsonRequest.put("year", "2012");
		jsonRequest.put("rating", "8.8");
		target = client.target(URL+CREATE_API);
		rs = target.request().put(Entity.json(jsonRequest));
		assertEquals(200, rs.getStatus());
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		int id = Math.toIntExact((long) jsonResponse.get("id"));

		// Verify the entry just created
		target = client.target(URL+SEARCH_API);
		rs = target.queryParam("id", id).request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		movieEntry = (JSONObject) jsonResponse.get("movieEntry");
		assertEquals("MovieName", movieEntry.get("name"));
		assertEquals("MovieGenre", movieEntry.get("genre"));
		assertEquals(2012, Math.toIntExact((long)movieEntry.get("year")));
		assertEquals(8.8, movieEntry.get("rating"));

		// Update the movie entry
		jsonRequest.put("name", "MovieNameUpdated");
		jsonRequest.put("genre", "MovieGenreUpdated");
		jsonRequest.put("year", "2016");
		jsonRequest.put("rating", "100");
		target = client.target(URL+UPDATE_API);
		rs = target.queryParam("id", id).request().post(Entity.json(jsonRequest));
		assertEquals(200, rs.getStatus());

		// Verify the entry just created
		target = client.target(URL+SEARCH_API);
		rs = target.queryParam("id", id).request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		movieEntry = (JSONObject) jsonResponse.get("movieEntry");
		assertEquals("MovieNameUpdated", movieEntry.get("name"));
		assertEquals("MovieGenreUpdated", movieEntry.get("genre"));
		assertEquals(2016, Math.toIntExact((long)movieEntry.get("year")));
		assertEquals(100.0, movieEntry.get("rating"));

		// Delete the test data
		target = client.target(URL+DELETE_API);
		rs = target.queryParam("id", id).request().delete();
		assertEquals(200, rs.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void badInputCreateTest() throws ParseException{
		WebTarget target;
		Response rs;
		JSONObject jsonRequest = new JSONObject();

		// Fail to create an entry with empty JSON
		target = client.target(URL+CREATE_API);
		rs = target.request().put(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Fail to create an entry with non numeric year
		jsonRequest.put("name", "MovieName");
		jsonRequest.put("genre", "MovieGenre");
		jsonRequest.put("year", "abc");
		jsonRequest.put("rating", "8.8");
		target = client.target(URL+CREATE_API);
		rs = target.request().put(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Fail to create an entry with non numeric rating
		jsonRequest.put("name", "MovieName");
		jsonRequest.put("genre", "MovieGenre");
		jsonRequest.put("year", "2012");
		jsonRequest.put("rating", "abc");
		target = client.target(URL+CREATE_API);
		rs = target.request().put(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void badInputUpdateTest() throws ParseException{
		WebTarget target;
		Response rs;
		JSONObject jsonRequest = new JSONObject();

		// Bad Id
		int badId = generateBadId();
		target = client.target(URL+UPDATE_API);
		rs = target.queryParam("id", badId).request().post(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Empty Id
		target = client.target(URL+UPDATE_API);
		rs = target.request().post(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Fail to update an entry with empty JSON
		target = client.target(URL+UPDATE_API);
		rs = target.request().post(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Fail to create an entry with non numeric year
		jsonRequest.put("name", "MovieName");
		jsonRequest.put("genre", "MovieGenre");
		jsonRequest.put("year", "abc");
		jsonRequest.put("rating", "8.8");
		target = client.target(URL+UPDATE_API);
		rs = target.request().post(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());

		// Fail to create an entry with non numeric rating
		jsonRequest.put("name", "MovieName");
		jsonRequest.put("genre", "MovieGenre");
		jsonRequest.put("year", "2012");
		jsonRequest.put("rating", "abc");
		target = client.target(URL+UPDATE_API);
		rs = target.request().post(Entity.json(jsonRequest));
		assertEquals(400, rs.getStatus());
	}

	@Test
	public void badInputDeleteTest() throws ParseException{
		WebTarget target;
		Response rs;

		// Bad Id
		int badId = generateBadId();
		target = client.target(URL+DELETE_API);
		rs = target.queryParam("id", badId).request().delete();
		assertEquals(400, rs.getStatus());

		// Empty Id
		target = client.target(URL+DELETE_API);
		rs = target.request().delete();
		assertEquals(400, rs.getStatus());
	}

	private int generateBadId() throws ParseException{
		Random r = new Random();
		int result = r.nextInt();
		WebTarget target; 
		Response rs;
		JSONParser parser = new JSONParser();
		JSONObject jsonResponse;

		target = client.target(URL+IDS_API);
		rs = target.request().get();
		jsonResponse = (JSONObject) parser.parse(rs.readEntity(String.class));
		HashSet<Integer> ids = new HashSet<Integer>();
		for(Object o : (JSONArray)jsonResponse.get("ids")){
			ids.add(Math.toIntExact((long) o));
		}
		while(ids.contains(result)){
			result = r.nextInt();
		}
		return result;
	}

}
