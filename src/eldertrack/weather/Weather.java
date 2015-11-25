package eldertrack.weather;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Weather {
	private long epochtime;
	private String summary;
	private double precip;
	private double temperature;
	private double windspeed;
	private double airpressure;
	
	Weather(Object time, Object summary, double precip, double temperature, double windspeed, double airpressure) {
		this.epochtime = (long)time;
		this.summary = summary.toString();
		this.precip = (long)precip;
		this.temperature = (double)temperature;
		this.windspeed = (double)windspeed;
		this.airpressure = (double)airpressure;
		System.out.println(this.airpressure + " " + this.summary); // Test
	}
	
	public String getTimeString() {
        Date date = new Date(this.epochtime * 1000);
        DateFormat format = new SimpleDateFormat("dd MMM HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        String formatted = format.format(date);
        return formatted;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public double getPrecip() {
		return this.precip;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public double getWindSpeed() {
		return this.windspeed;
	}
	
	public double getAirPressure() {
		return this.airpressure;
	}
}
