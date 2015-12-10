package eldertrack.weather;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import eldertrack.misc.URLTools;

public class WeatherTools {
	static Weather weather = null;

	public static Weather getWeather() {
		ExecutorService executor = Executors.newCachedThreadPool();
		Callable<Object> task = new Callable<Object>() {
		   public Object call() {
		      return weatherGetter();
		   }
		};
		Future<Object> future = executor.submit(task);
		try {
		   Object result = future.get(3, TimeUnit.SECONDS); 
		   return (Weather)result;
		} catch (TimeoutException ex) {
			return null;
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		} finally {
		   future.cancel(true);
		}
	}
	
	// Method to get and return the Weather object
	public static Weather weatherGetter() {
		Weather weather = null;
		try {
		    String weatherjson = URLTools.readUrl("https://api.forecast.io/forecast/5a1a1e859c525d254a02e4944d0de102/1.379348,103.849876?exclude=minutely,hourly,daily,alerts,flags&units=si");
		    JSONObject json = (JSONObject)new JSONParser().parse(weatherjson);
		    JSONObject jsonc = (JSONObject)new JSONParser().parse(json.get("currently").toString());
		    
		    weather = new Weather(jsonc.get("time"),
		    		jsonc.get("summary"),
		    		doubleCaster(jsonc.get("precipProbability")),
		    		doubleCaster(jsonc.get("temperature")),
		    		doubleCaster(jsonc.get("windSpeed")),
		    		doubleCaster(jsonc.get("pressure")));
		    return weather;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method to cast a double onto Long or Double
	public static double doubleCaster(Object toCast) {
		System.out.println(toCast.getClass().getName());
		if (toCast instanceof Long) {
			return Long.valueOf((long)toCast).doubleValue();
		} else {
			return round((double)toCast,2);
		}
	}
	
	// Method to round numbers
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
