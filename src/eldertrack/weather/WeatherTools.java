package eldertrack.weather;

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
		/*
		 * Source for timeout method: http://stackoverflow.com/questions/1164301/how-do-i-call-some-blocking-method-with-a-timeout-in-java
		 */
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
		
	public static Weather weatherGetter() {
		Weather weather = null;
		try {
		    String weatherjson = URLTools.readUrl("https://api.forecast.io/forecast/5a1a1e859c525d254a02e4944d0de102/1.379348,103.849876?exclude=minutely,hourly,daily,alerts,flags&units=si");
		    JSONObject json = (JSONObject)new JSONParser().parse(weatherjson);
		    JSONObject jsonc = (JSONObject)new JSONParser().parse(json.get("currently").toString());
		    double precipProb = 0;
		    
		    if (jsonc.get("precipProb") instanceof Double) {
		    	precipProb = (double)jsonc.get("precipProb");
		    } else if (jsonc.get("precipProb") instanceof Long) {
		    	precipProb = 0;
		    }
		    
		    weather = new Weather(jsonc.get("time"),
		    		jsonc.get("summary"),
		    		precipProb,
		    		(double)jsonc.get("temperature"),
		    		(double)jsonc.get("windSpeed"),
		    		(double)jsonc.get("pressure"));
		    return weather;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
