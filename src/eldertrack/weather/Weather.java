package eldertrack.weather;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Weather {
	private long time;
	private String summary;
	private double precip;
	private double temperature;
	private double windspeed;
	private double airpressure;
	
	Weather(Object time, Object summary, Object precip, Object temperature, Object windspeed, Object airpressure) {
		this.time = (long)time;
		this.summary = summary.toString();
		this.precip = (double)precip;
		this.temperature = (double)temperature;
		this.windspeed = (double)windspeed;
		this.airpressure = (double)airpressure;
		System.out.println(this.airpressure + " " + this.summary);
	}
	
	String getTimeString() {
        Date date = new Date(this.time * 1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        String formatted = format.format(date);
        return formatted;
	}
	
	String getSummary() {
		return this.summary;
	}
	
	double getPrecip() {
		return this.precip;
	}
	
	double getTemperature() {
		return this.temperature;
	}
	
	double getWindSpeed() {
		return this.windspeed;
	}
	
	double getAirPressure() {
		return this.airpressure;
	}
}
