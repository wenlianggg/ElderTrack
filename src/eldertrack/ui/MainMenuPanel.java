package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import eldertrack.login.StaffSession;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class MainMenuPanel extends JPanel {
	private static final long serialVersionUID = 4235134532452345324L;
	StaffSession session;
	JLabel lblEldertrackLogin;
	JPasswordField passwordField;
	JButton loginButton;
	String loginMessage = "Outcome Undefined";
	private JButton btnMedTrack;
	private JButton btnDietManagement;
	private JButton btnReportGeneration;
	private JButton btnStaffManagement;
	
	MainMenuPanel() {
		setBackground(SystemColor.control);
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		lblEldertrackLogin = new JLabel("Welcome to ElderTrack!");
		lblEldertrackLogin.setBounds(10, 0, 754, 54);
		lblEldertrackLogin.setForeground(SystemColor.textHighlight);
		lblEldertrackLogin.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblEldertrackLogin);
		
		btnMedTrack = new JButton("Medication Tracking");
		btnMedTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.MEDICATIONPANEL);
			}
		});
		btnMedTrack.setBounds(10, 95, 242, 120);
		add(btnMedTrack);
		
		btnDietManagement = new JButton("Diet Management");
		btnDietManagement.setBounds(10, 230, 242, 120);
		btnDietManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.DIETPANEL);
			}
		});
		add(btnDietManagement);
		
		btnReportGeneration = new JButton("Report Generation");
		btnReportGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.REPORTPANEL);
			}
		});
		btnReportGeneration.setBounds(10, 361, 242, 120);
		add(btnReportGeneration);
		
		btnStaffManagement = new JButton("Staff/Elderly Management");
		btnStaffManagement.setBounds(10, 492, 242, 120);
		btnStaffManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.MGMTPANEL);
			}
		});
		add(btnStaffManagement);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(262, 95, 723, 128);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEldertrackWelcomePage = new JLabel("ElderTrack Welcome Page");
		lblEldertrackWelcomePage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEldertrackWelcomePage.setBounds(10, 11, 247, 31);
		panel.add(lblEldertrackWelcomePage);
		
		JLabel lblLoggedInAs = new JLabel("You are logged in as:");
		lblLoggedInAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoggedInAs.setBounds(10, 45, 289, 21);
		panel.add(lblLoggedInAs);
		
		JLabel lblYouLastLogged = new JLabel("You last logged in on:");
		lblYouLastLogged.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouLastLogged.setBounds(10, 95, 289, 21);
		panel.add(lblYouLastLogged);
		
		JLabel lblNric = new JLabel("Your NRIC:");
		lblNric.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNric.setBounds(10, 70, 289, 21);
		panel.add(lblNric);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 150, 556, 190);
		panel.add(textArea);
		
		JButton btnNewButton = new JButton("Sign Out");
		btnNewButton.setBounds(585, 12, 128, 36);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getInstance().endCurrentSession();
			}
		});
		
		JLabel lblYourNotes = new JLabel("Sticky Notes:");
		lblYourNotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourNotes.setBounds(262, 248, 120, 25);
		add(lblYourNotes);
		
		JTextArea txtarea_stickynotes = new JTextArea();
		txtarea_stickynotes.setBackground(new Color(230, 230, 250));
		txtarea_stickynotes.setBounds(262, 278, 723, 189);
		add(txtarea_stickynotes);
		
		JButton btnSaveNotes = new JButton("Save Notes");
		btnSaveNotes.setBounds(855, 474, 130, 47);
		add(btnSaveNotes);
	}
}
