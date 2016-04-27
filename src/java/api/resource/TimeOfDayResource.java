package api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.LocalTime;

import api.representation.TimeOfDayRepresentation;

@Path("/api/timeOfDay")
@Produces(MediaType.APPLICATION_JSON)
public class TimeOfDayResource {

	@GET
	public TimeOfDayRepresentation getTimeOfDay(){
		LocalTime lt = new LocalTime();
		return new TimeOfDayRepresentation(lt);
	}
}
