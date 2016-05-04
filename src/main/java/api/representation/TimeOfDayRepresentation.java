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
		int hour = localTime.getHourOfDay();
		int minute = localTime.getMinuteOfHour();
		int second = localTime.getSecondOfMinute();
		String hourStr = "";
		String minuteStr = "";
		String secondStr = "";
		if(hour/10 == 0) {
			hourStr = "0" + hour;
		} else {
			hourStr = String.valueOf(hour);
		}
		if(minute/10 == 0) {
			minuteStr = "0" + minute;
		} else {
			minuteStr = String.valueOf(minute);
		}
		if(second/10 == 0) {
			secondStr = "0" + second;
		} else {
			secondStr = String.valueOf(second);
		}
		
        return  hourStr + ":" + minuteStr + ":" + secondStr;
    }
}
