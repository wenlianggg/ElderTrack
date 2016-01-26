package eldertrack.weather;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import eldertrack.misc.URLTools;

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
	
	public static Weather getWeather() {
		Weather weather = null;
		try {
		    String weatherjson = URLTools.readUrl("https://api.forecast.io/forecast/5a1a1e859c525d254a02e4944d0de102/1.379348,103.849876?exclude=minutely,hourly,daily,alerts,flags&units=si");
		    JSONObject json = (JSONObject)new JSONParser().parse(weatherjson);
		    JSONObject jsonc = (JSONObject)new JSONParser().parse(json.get("currently").toString()); 
		    weather = new Weather(
		    	jsonc.get("time"),
		    	jsonc.get("summary"),
		    	toDouble(jsonc.get("precipProbability")),
		    	toDouble(jsonc.get("temperature")),
		    	toDouble(jsonc.get("windSpeed")),
		    	toDouble(jsonc.get("pressure")));
			} catch (Exception e) {
				e.printStackTrace();
			}
	    return weather;   
	}
	
	// Method to cast a double onto Long or Double
	public static double toDouble(Object toCast) {
		if (toCast instanceof Long) {
			return Long.valueOf((long)toCast).doubleValue();
		} else {
			return round((double)toCast,2);
		}
	}
	
	// Method to round numbers
	public static double round(double value, int places) {
	    if (places < 0) 
	    	throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
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
