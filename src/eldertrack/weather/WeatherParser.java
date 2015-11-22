package eldertrack.weather;

import java.util.List;


import eldertrack.misc.URLTools;

public class WeatherParser {

	public static void main(String[] args) throws Exception {

	    String json = URLTools.readUrl("https://api.forecast.io/forecast/5a1a1e859c525d254a02e4944d0de102/1.379348,103.849876?exclude=minutely,hourly,daily,alerts,flags");

	    Weather weather;
	}
}
