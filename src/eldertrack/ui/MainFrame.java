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

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private String loginMessage;
	private JPasswordField passwordField;

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
	 * Create the frame.
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
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 136, 400, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Label for Password:
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 54, 38, 19);
		panel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		// Login field
		JTextField loginField = new JTextField();
		loginField.setBounds(10, 84, 380, 32);
		panel.add(loginField);
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 145, 66, 19);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		// Password field
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 175, 380, 30);
		panel.add(passwordField);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(296, 237, 94, 42);
		panel.add(loginButton);
		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check if login fields are null, if not, process login.
				if (!loginField.getText().equals("") && !(passwordField.getPassword().length == 0)) {
					StaffSession session = SessionTools.createSession(loginField.getText(), passwordField.getPassword());
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
