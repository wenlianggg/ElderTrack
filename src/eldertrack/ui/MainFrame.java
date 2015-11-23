package eldertrack.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import eldertrack.login.SessionTools;
import eldertrack.login.StaffSession;
import eldertrack.weather.Weather;
import eldertrack.weather.WeatherTools;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private String loginMessage;
	private JPasswordField passwordField;
	private StaffSession session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Make program look like windows software
					MainFrame frame = new MainFrame();
					frame.setVisible(true); // Set the main frame as visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame...
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		this.setTitle("ElderTrack Toolkit - ITP192-03 Team 2");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label for Login:
		JLabel lblEldertrack = new JLabel("ElderTrack Authentication");
		lblEldertrack.setForeground(SystemColor.textHighlight);
		lblEldertrack.setBounds(10, 0, 754, 54);
		lblEldertrack.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		contentPane.add(lblEldertrack);
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBounds(200, 136, 400, 290);
		contentPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		// Label for Password:
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 41, 66, 32);
		LoginPanel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		// Login field
		JTextField loginField = new JTextField();
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setBounds(10, 84, 380, 32);
		LoginPanel.add(loginField);
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 145, 101, 19);
		LoginPanel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		// Password field
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your login password that you were assigned");
		passwordField.setBounds(10, 175, 380, 30);
		LoginPanel.add(passwordField);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginButton.setBounds(296, 237, 94, 42);
		LoginPanel.add(loginButton);
		
		JPanel WeatherPanel = new JPanel();
		WeatherPanel.setBackground(new Color(173, 255, 47));
		WeatherPanel.setBounds(597, 483, 197, 88);
		contentPane.add(WeatherPanel);
		WeatherPanel.setLayout(null);
		
		JLabel lblWeatherText = new JLabel("Weather");
		lblWeatherText.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblWeatherText.setBounds(5, 0, 194, 25);
		WeatherPanel.add(lblWeatherText);
		
		JLabel lblWeatherLine1 = new JLabel("No Information Obtained");
		lblWeatherLine1.setBounds(10, 25, 177, 14);
		WeatherPanel.add(lblWeatherLine1);
		
		JLabel lblWeatherLine2 = new JLabel("API Did Not Respond Timely");
		lblWeatherLine2.setBounds(10, 40, 177, 14);
		WeatherPanel.add(lblWeatherLine2);
		
		JLabel lblWeatherLine3 = new JLabel("");
		lblWeatherLine3.setBounds(10, 55, 177, 14);
		WeatherPanel.add(lblWeatherLine3);
		
		JLabel lblWeatherLine4 = new JLabel("");
		lblWeatherLine4.setBounds(10, 70, 177, 14);
		WeatherPanel.add(lblWeatherLine4);
		
		Weather weather = WeatherTools.getWeather();
		if (weather!=null) {
			lblWeatherLine1.setText(weather.getTemperature() + "°C on " + weather.getTimeString() );
			lblWeatherLine2.setText("Condition: " + weather.getSummary());
			lblWeatherLine3.setText("Precip: " + weather.getPrecip()*100 + "% Wind Speed: " + weather.getWindSpeed() + "m/s");
			lblWeatherLine4.setText("Air Pressure: " + weather.getAirPressure() + "hPa");
		}
		
		
		
		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check if login fields are null, if not, process login.
				if (!loginField.getText().equals("") && !(passwordField.getPassword().length == 0)) {
					session = SessionTools.createSession(loginField.getText(), passwordField.getPassword());
					if (session == null) {
						loginMessage = "Login failed!";
					} else if (session.isAuthenticated()) {
						loginMessage = "Login successful!";
					}
					JOptionPane.showMessageDialog(null, loginMessage);
				} else {
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
				}
			}
		});
	}
}
