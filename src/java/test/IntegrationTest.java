package test;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

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

	@Before
	public void setUp() throws Exception {
		client = ClientBuilder.newClient();
	}
	
	@After
    public void tearDown() throws Exception {
        client.close();
    }
	
//	@Test
//	public void timeOfDayTest() throws ParseException{
//		 Response rs = client.target(URL+TOD_API).request().get();
//		 JSONParser parser = new JSONParser();
//		 JSONObject json = (JSONObject) parser.parse(rs.readEntity(String.class));
//		 
//		 assertTrue("Status code: " + rs.getStatus(), rs.getStatus() == 200);
//		 assertTrue("Time: " + json.get("time"), json.get("time").toString().length() == 8);
//	}
	
//	@Test
//	public void createTenMovies() {
//		WebTarget target = client.target(URL+CREATE_API);
//		JSONObject jo = new JSONObject();
//		target = enterParam(target, "name", "MovieName0");
//		target = enterParam(target, "genre", "MovieGenre0");
//		target = enterParam(target, "year", "0");
//		target = enterParam(target, "rating", "0");
//		Response rs = target.request().post(Entity.json(jo));
//		
//		
//		assertTrue("Status code: " + rs.getStatus(), rs.getStatus() == 200);
//	}
	
	@Test
	public void createTenMovies() {
		WebTarget target = client.target(URL+CREATE_API);
		JSONObject jo = new JSONObject();
		jo.put("name", "MovieName0");
		jo.put("genre", "MovieGenre0");
		jo.put("year", "0");
		jo.put("rating", "0");
		Response rs = target.request(MediaType.APPLICATION_JSON).put(Entity.json(jo));
		
		System.out.println(rs.readEntity(String.class));
		
		assertTrue("Status code: " + rs.getStatus(), rs.getStatus() == 200);
	}
	
	private WebTarget enterParam(WebTarget target, String key, String value) {
		target = target.queryParam(key, value);
		return target;
	}
	
	

}
