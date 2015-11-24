package eldertrack.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import eldertrack.login.SessionTools;
import eldertrack.login.StaffSession;
import eldertrack.weather.Weather;
import eldertrack.weather.WeatherTools;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1;
    final static String LOGINPANEL = "Login Panel";
    final static String MEDICATIONPANEL = "Medication Panel";
    final static String DIETPANEL = "Diet Panel";
    final static String MGMTPANEL = "Management Panel";
    
	private JPanel MasterPane;
	private String loginMessage;
	private JPasswordField passwordField;
	private StaffSession session;

	// JFrame (MainFrame) > Normal JPanel (MasterPane) > CardLayout JPanel (MainPanel) > Feature Panels (LoginPanel)
	
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
		this.setTitle("ElderTrack Toolkit - ITP192-03 Team 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setResizable(false);
		
		MasterPane = new JPanel();
		MasterPane.setBorder(null);
		setContentPane(MasterPane);
		MasterPane.setLayout(null);

		JPanel LoginPanel = new JPanel();
		LoginPanel.setBounds(0, 0, 995, 670);
		MasterPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(298, 209, 66, 32);
		LoginPanel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JTextField loginField = new JTextField();
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setBounds(298, 249, 400, 32);
		LoginPanel.add(loginField);
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(300, 303, 101, 19);
		LoginPanel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your login password that you were assigned");
		passwordField.setBounds(298, 330, 400, 30);
		LoginPanel.add(passwordField);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginButton.setBounds(604, 392, 94, 42);
		LoginPanel.add(loginButton);
		
		JLabel lblEldertrack = new JLabel("ElderTrack Login");
		lblEldertrack.setBounds(10, 0, 754, 54);
		LoginPanel.add(lblEldertrack);
		lblEldertrack.setForeground(SystemColor.textHighlight);
		lblEldertrack.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		JPanel DietPanel = new JPanel();
		DietPanel.setBounds(0, 0, 995, 670);
		MasterPane.add(DietPanel);
		DietPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDietLabel = new JLabel("ElderTrack Diet Management");
		DietPanel.add(lblDietLabel);
		
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setSize(174, 26);
		comboBox.setLocation(10, 682);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {DIETPANEL, LOGINPANEL,}));
		comboBox.setSelectedIndex(0);
		MasterPane.add(comboBox);
		
		JPanel MainPanel = new JPanel(new CardLayout()); // MAIN PANEL
		MasterPane.add(MainPanel);
		MainPanel.setLocation(0, 0);
		MainPanel.setSize(994, 671);
		MainPanel.setLayout(null);
		CardLayout cl = (CardLayout) MainPanel.getLayout();

		JPanel WeatherPanel = new JPanel();
		WeatherPanel.setBounds(794, 671, 200, 100);
		MasterPane.add(WeatherPanel);
		WeatherPanel.setBackground(new Color(173, 255, 47));
		WeatherPanel.setLayout(null);
		
		JLabel lblWeatherText = new JLabel("Weather");
		lblWeatherText.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblWeatherText.setBounds(5, 0, 194, 25);
		WeatherPanel.add(lblWeatherText);
		
		JLabel lblWeatherLine1 = new JLabel("No Information Obtained");
		lblWeatherLine1.setBounds(10, 25, 177, 14);
		WeatherPanel.add(lblWeatherLine1);
		
		JLabel lblWeatherLine2 = new JLabel("Web API Did Not Respond Timely");
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
		
		JButton btnSwitchCards = new JButton("Switch Cards");
		btnSwitchCards.setBounds(10, 636, 120, 23);
		MasterPane.add(btnSwitchCards);
		
				
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


		
		/**
		 * Action Listeners
		 */
	}
}
