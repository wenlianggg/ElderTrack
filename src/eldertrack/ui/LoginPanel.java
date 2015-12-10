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
		
		lblEldertrackLogin = new JLabel("ElderTrack Authentication");
		lblEldertrackLogin.setBounds(10, 0, 754, 54);
		lblEldertrackLogin.setForeground(SystemColor.textHighlight);
		lblEldertrackLogin.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblEldertrackLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(272, 175, 449, 281);
		add(panel);
		panel.setLayout(null);
		
		loginField = new JTextField();
		loginField.setBounds(25, 61, 400, 32);
		panel.add(loginField);
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(25, 21, 66, 32);
		panel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 121, 101, 19);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(25, 148, 400, 30);
		panel.add(passwordField);
		passwordField.setToolTipText("Enter your login password that you were assigned");
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(25, 211, 400, 32);
		panel.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		

		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check if login fields are null, if not, process login.
				if (!loginField.getText().equals("") && !(passwordField.getPassword().length == 0)) {
					session = SessionTools.createSession(loginField.getText(), passwordField.getPassword());
					if (session == null) {
						loginMessage = "Login failed, Please Try Again";
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
	}
	
	StaffSession getStaffSession() {
		StaffSession gettableSession = this.session;
		return gettableSession;
	}
}
