package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eldertrack.login.StaffSession;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1260440593277672145L;
	StaffSession session;
	JLabel lblEldertrackLogin;
	JLabel lblLogin;
	JLabel lblPassword;
	JTextField loginField;
	JPasswordField passwordField;
	JButton loginButton;
	JButton forgetPassButton;
	String loginMessage = "Outcome Undefined";
	JProgressBar progressBar;
	private JLabel lblUtilitiesForNursing;
	
	LoginPanel() {
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		JPanel loginFieldsPanel = new JPanel();
		loginFieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		loginFieldsPanel.setBackground(new Color(255, 248, 220));
		loginFieldsPanel.setBounds(250, 221, 500, 239);
		add(loginFieldsPanel);
		loginFieldsPanel.setLayout(null);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 11, 480, 27);
		loginFieldsPanel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		loginField = new JTextField();
		loginField.setBounds(10, 39, 480, 27);
		loginField.setHorizontalAlignment(SwingConstants.LEFT);
		loginField.setColumns(3);
		loginFieldsPanel.add(loginField);
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 85, 480, 27);
		loginFieldsPanel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		passwordField.setBounds(10, 113, 480, 27);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setColumns(3);
		loginFieldsPanel.add(passwordField);
		passwordField.setToolTipText("Enter your login password that you were assigned");
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		JButton loginButton = new JButton("Login");
		loginButton.setForeground(new Color(0, 128, 0));
		loginButton.setBounds(10, 182, 480, 51);
		loginFieldsPanel.add(loginButton);
		loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		
		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		
		// Instantiate the listener
		ActionListener loginAction = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkLogin();
			}
		};
		
		// Add enter listener to buttons and text fields
		loginField.addActionListener(loginAction);
		passwordField.addActionListener(loginAction);
		loginButton.addActionListener(loginAction);
		
		lblEldertrackLogin = new JLabel("ElderTrack Suite");
		lblEldertrackLogin.setBounds(10, 0, 280, 54);
		add(lblEldertrackLogin);
		lblEldertrackLogin.setForeground(SystemColor.textHighlight);
		lblEldertrackLogin.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		
		lblUtilitiesForNursing = new JLabel("Utilities for Nursing Homes");
		lblUtilitiesForNursing.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblUtilitiesForNursing.setBounds(300, 20, 262, 30);
		add(lblUtilitiesForNursing);
		
		JButton btnBypassLogin = new JButton("Bypass Login");
		btnBypassLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginField.setText("testuser");
				passwordField.setText("eldertrackadmin");
				checkLogin();
			}
		});
		btnBypassLogin.setBounds(647, 471, 103, 35);
		add(btnBypassLogin);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setToolTipText("Login Progress");
		progressBar.setString("Login to begin loading");
		progressBar.setBounds(250, 471, 387, 35);
		progressBar.setStringPainted(true);
		add(progressBar);

	}
	
	// Check if login fields are null, if not, process login.
	private void checkLogin() {
		if (!loginField.getText().equals("") && !(passwordField.getPassword().length == 0)) {
			session = MainFrame.getInstance().setSessionInstance(StaffSession.createSession(loginField.getText(), passwordField.getPassword()));
			passwordField.setText("");
			if (session == null)
				JOptionPane.showMessageDialog(MainFrame.getInstance(), "Login failed, try again!");
			else if (session.isAuthenticated()) {
				progressBar.setValue(10);
				progressBar.update(progressBar.getGraphics());
				MainFrame.getInstance().constructPanels();
				CardLayout mainCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				mainCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		} else {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Fields cannot be empty!");
		}
	}
}
