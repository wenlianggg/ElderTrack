package eldertrack.weather;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import eldertrack.misc.URLTools;

public class WeatherTools {

	public static Weather getWeather() throws Exception {
		Weather weather = null;
		try {
	    String weatherjson = URLTools.readUrl("https://api.forecast.io/forecast/5a1a1e859c525d254a02e4944d0de102/1.379348,103.849876?exclude=minutely,hourly,daily,alerts,flags&units=si");
	    JSONObject json = (JSONObject)new JSONParser().parse(weatherjson);
	    System.out.println(json.get("currently").toString());
	    JSONObject jsonc = (JSONObject)new JSONParser().parse(json.get("currently").toString());
	    weather = new Weather(jsonc.get("time"), jsonc.get("summary"), jsonc.get("precipProbability"),
	    					jsonc.get("temperature"), jsonc.get("windSpeed"), jsonc.get("pressure"));
	    return weather;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
