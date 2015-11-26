package eldertrack.ui;

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

import eldertrack.login.SessionTools;
import eldertrack.login.StaffSession;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1260440593277672145L;
	StaffSession session;
	JLabel lblEldertrackLogin;
	JLabel lblLogin;
	JLabel lblPassword;
	JTextField loginField;
	JPasswordField passwordField;
	JButton loginButton;
	String loginMessage = "Outcome Undefined";
	
	LoginPanel() {
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		lblEldertrackLogin = new JLabel("ElderTrack Login");
		lblEldertrackLogin.setBounds(10, 0, 754, 54);
		lblEldertrackLogin.setForeground(SystemColor.textHighlight);
		lblEldertrackLogin.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(298, 209, 66, 32);
		add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(300, 303, 101, 19);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		loginField = new JTextField();
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setBounds(298, 249, 400, 32);
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your login password that you were assigned");
		passwordField.setBounds(298, 330, 400, 30);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginButton.setBounds(604, 392, 94, 42);
		

		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check if login fields are null, if not, process login.
				if (!loginField.getText().equals("") && !(passwordField.getPassword().length == 0)) {
					session = SessionTools.createSession(loginField.getText(), passwordField.getPassword());
					if (session == null) {
						loginMessage = "Login failed!";
						loginField.setText("");
						passwordField.setText("");
					} else if (session.isAuthenticated()) {
						loginMessage = "Login successful!";
					}
					JOptionPane.showMessageDialog(null, loginMessage);
				} else {
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
				}
			}
		});
		
		add(loginButton);
		add(loginField);
		add(passwordField);
		add(lblEldertrackLogin);
		add(lblPassword);
	}
	
	
}
