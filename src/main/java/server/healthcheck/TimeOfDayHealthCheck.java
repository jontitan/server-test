package server.healthcheck;

import com.codahale.metrics.health.HealthCheck;

import api.representation.TimeOfDayRepresentation;
import api.resource.TimeOfDayResource;

public class TimeOfDayHealthCheck extends HealthCheck{
	
	@Override
	protected Result check() throws Exception
	{
		TimeOfDayResource todr = new TimeOfDayResource();
		TimeOfDayRepresentation todrp = todr.getTimeOfDay();
		String currentTime= todrp.getTime();
		if(currentTime.length() == 8) {
			return Result.healthy();
		} else {
			return Result.unhealthy("Wrong time returned: " + currentTime);
		}
	}
}
