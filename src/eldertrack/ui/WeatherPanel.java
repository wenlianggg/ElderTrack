package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eldertrack.weather.Weather;

public class WeatherPanel extends JPanel {
	private static final long serialVersionUID = -30875959185202451L;
	JLabel lblWeatherText;
	JLabel lblWeatherLine1;
	JLabel lblWeatherLine2;
	JLabel lblWeatherLine3;
	JLabel lblWeatherLine4;
	JLabel lblWeatherLine5;
	WeatherPanel() {
		setBounds(804, 671, 204, 110);
		setBackground(new Color(60,60,60));
		setLayout(null);
		
		lblWeatherText = new JLabel("Weather");
		lblWeatherText.setForeground(new Color(0, 191, 255));
		lblWeatherText.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblWeatherText.setBounds(10, 0, 194, 25);
		
		lblWeatherLine1 = new JLabel("");
		lblWeatherLine1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWeatherLine1.setForeground(Color.WHITE);
		lblWeatherLine1.setBounds(20, 25, 177, 14);
		
		lblWeatherLine2 = new JLabel("Concurrently loading data...");
		lblWeatherLine2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWeatherLine2.setForeground(Color.WHITE);
		lblWeatherLine2.setBounds(20, 40, 177, 14);
		
		lblWeatherLine3 = new JLabel("");
		lblWeatherLine3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWeatherLine3.setForeground(Color.WHITE);
		lblWeatherLine3.setBounds(20, 55, 177, 14);
		
		lblWeatherLine4 = new JLabel("");
		lblWeatherLine4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWeatherLine4.setForeground(Color.WHITE);
		lblWeatherLine4.setBounds(20, 70, 177, 14);
		
		lblWeatherLine5 = new JLabel("");
		lblWeatherLine5.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblWeatherLine5.setForeground(new Color(192, 192, 192));
		lblWeatherLine5.setBounds(20, 85, 177, 14);
		
		add(lblWeatherText);
		add(lblWeatherLine1);
		add(lblWeatherLine2);
		add(lblWeatherLine3);
		add(lblWeatherLine4);
		add(lblWeatherLine5);

	}
	
	public void showWeatherInfo(Weather weather) {
		if (weather!=null) {
			lblWeatherLine1.setText("Condition: " + weather.getSummary());
			lblWeatherLine2.setText(weather.getTemperature() + "°C, " + "Wind Speed: " + weather.getWindSpeed() + "m/s");
			lblWeatherLine3.setText("Chance of Rain: " + weather.getPrecip() + "%");
			lblWeatherLine4.setText("Air Pressure: " + weather.getAirPressure() + "hPa");
			lblWeatherLine5.setText("Correct as at " + weather.getTimeString());
		}
	}
}
