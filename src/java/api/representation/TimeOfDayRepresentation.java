package api.representation;

import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeOfDayRepresentation {
	private LocalTime localTime;
	
	public TimeOfDayRepresentation(){
		 // Jackson deserialization
	}
	
	public TimeOfDayRepresentation(LocalTime localTime){
		this.localTime = localTime;
	}
	
	@JsonProperty
    public String getTime() {
        return  localTime.getHourOfDay() + ":" + localTime.getMinuteOfHour() + ":" + localTime.getSecondOfMinute();
    }
}
