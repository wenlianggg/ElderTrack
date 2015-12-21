package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eldertrack.login.SessionTools;
import eldertrack.login.StaffSession;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ResetPassPanel extends JPanel {
	private static final long serialVersionUID = 1260440593277672145L;
	StaffSession session;
	JLabel lblEldertrackLogin;
	JLabel lblLogin;
	JLabel lblDob;
	JTextField loginField;
	JPasswordField passwordField;
	JButton contButton;
	String loginMessage = "Outcome Undefined";
	
	ResetPassPanel() {
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		JPanel loginFieldsPanel = new JPanel();
		loginFieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		loginFieldsPanel.setBackground(Color.WHITE);
		loginFieldsPanel.setBounds(250, 186, 500, 274);
		add(loginFieldsPanel);
		loginFieldsPanel.setLayout(new GridLayout(9, 0, 1, 1));
		
		lblLogin = new JLabel("Login:");
		loginFieldsPanel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		loginField = new JTextField();
		loginField.setColumns(3);
		loginFieldsPanel.add(loginField);
		loginField.setToolTipText("Enter your login username that you were assigned");
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		loginFieldsPanel.add(verticalStrut_1);
		
		lblDob = new JLabel("New Password:");
		loginFieldsPanel.add(lblDob);
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setColumns(3);
		loginFieldsPanel.add(passwordField);
		passwordField.setToolTipText("Enter date of birth");
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		loginFieldsPanel.add(verticalStrut);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		loginFieldsPanel.add(verticalStrut_2);
		
		JButton contButton_1 = new JButton("Continue");
		loginFieldsPanel.add(contButton_1);
		contButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		
				// On continue button pressed, 
				// Check user details by calling method from eldertrack.login.LoginProcessor class file
				contButton_1.addActionListener(new ActionListener() {
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
		
		lblEldertrackLogin = new JLabel("ElderTrack Authentication");
		lblEldertrackLogin.setBounds(10, 0, 441, 54);
		add(lblEldertrackLogin);
		lblEldertrackLogin.setForeground(SystemColor.textHighlight);
		lblEldertrackLogin.setFont(new Font("Segoe UI", Font.ITALIC, 40));
	}
}
