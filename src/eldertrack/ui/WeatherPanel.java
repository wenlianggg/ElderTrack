package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eldertrack.weather.Weather;
import eldertrack.weather.WeatherTools;

public class WeatherPanel extends JPanel {
	private static final long serialVersionUID = -30875959185202451L;
	JLabel lblWeatherText;
	JLabel lblWeatherLine1;
	JLabel lblWeatherLine2;
	JLabel lblWeatherLine3;
	JLabel lblWeatherLine4;
	WeatherPanel() {
		setBounds(794, 671, 200, 100);
		setBackground(new Color(173, 255, 47));
		setLayout(null);
		
		JLabel lblWeatherText = new JLabel("Weather");
		lblWeatherText.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblWeatherText.setBounds(5, 0, 194, 25);
		
		JLabel lblWeatherLine1 = new JLabel("No Information Obtained");
		lblWeatherLine1.setBounds(10, 25, 177, 14);
		
		JLabel lblWeatherLine2 = new JLabel("Web API Did Not Respond Timely");
		lblWeatherLine2.setBounds(10, 40, 177, 14);
		
		JLabel lblWeatherLine3 = new JLabel("");
		lblWeatherLine3.setBounds(10, 55, 177, 14);
		
		JLabel lblWeatherLine4 = new JLabel("");
		lblWeatherLine4.setBounds(10, 70, 177, 14);
		
		Weather weather = WeatherTools.getWeather();
		if (weather!=null) {
			lblWeatherLine1.setText(weather.getTemperature() + "°C on " + weather.getTimeString() );
			lblWeatherLine2.setText("Condition: " + weather.getSummary());
			lblWeatherLine3.setText("Precip: " + weather.getPrecip() + "% Wind Speed: " + weather.getWindSpeed() + "m/s");
			lblWeatherLine4.setText("Air Pressure: " + weather.getAirPressure() + "hPa");
		}
		
		add(lblWeatherText);
		add(lblWeatherLine1);
		add(lblWeatherLine2);
		add(lblWeatherLine3);
		add(lblWeatherLine4);

	}
}
