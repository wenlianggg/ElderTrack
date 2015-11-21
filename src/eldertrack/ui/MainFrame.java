package eldertrack.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import eldertrack.login.LoginProcessor;

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
		JLabel lblEldertrack = new JLabel("ElderTrack Login");
		lblEldertrack.setForeground(SystemColor.textHighlight);
		lblEldertrack.setBounds(10, 0, 754, 54);
		lblEldertrack.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		contentPane.add(lblEldertrack);
		
		// Label for Password:
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(200, 144, 46, 22);
		contentPane.add(lblLogin);
		
		// Login field
		JTextArea loginField = new JTextArea();
		loginField.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		loginField.setBackground(Color.LIGHT_GRAY);
		loginField.setBounds(200, 167, 400, 33);
		contentPane.add(loginField);
		
		// Password field
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(200, 232, 400, 33);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(200, 211, 66, 22);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		// On login button pressed, 
		// Check login details by calling method from eldertrack.login.LoginProcessor class file
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean loginResult = LoginProcessor.loginCheck(loginField.getText(), passwordField.getPassword());
				if (loginResult) {
					loginMessage = "Login successful!";
				} else {
					loginMessage = "Login failed!";
				}
				JOptionPane.showMessageDialog(null, loginMessage);
			}
		});
		btnNewButton.setBounds(495, 287, 105, 33);
		contentPane.add(btnNewButton);
	}
}
