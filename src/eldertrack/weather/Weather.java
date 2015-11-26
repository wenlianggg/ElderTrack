package eldertrack.weather;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Weather {
	private long epochtime;
	private String summary;
	private double precip;
	private double temperature;
	private double windspeed;
	private double airpressure;
	private static DecimalFormat df = new DecimalFormat("####.0");
	
	Weather(Object time, Object summary, double precip, double temperature, double windspeed, double airpressure) {
		this.epochtime = (long)time;
		this.summary = summary.toString();
		this.precip = precip;
		this.temperature = temperature;
		this.windspeed = windspeed;
		this.airpressure = airpressure;
		System.out.println(this.getSummary() + " - " + this.getAirPressure() + " " + this.getPrecip() + "% " + this.getWindSpeed() + "m/s"); // Test
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
	
	public String getPrecip() {
		return df.format(this.precip*100);
	}
	
	public String getTemperature() {
		return df.format(this.temperature);
	}
	
	public String getWindSpeed() {
		return df.format(this.windspeed);
	}
	
	public String getAirPressure() {
		return df.format(this.airpressure);
	}
}
