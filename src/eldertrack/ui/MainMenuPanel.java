package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eldertrack.login.StaffSession;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class MainMenuPanel extends JPanel {
	private static final long serialVersionUID = 4235134532452345324L;
	private JLabel lblTitle;
	private JButton btnMedTrack;
	private JButton btnDietManagement;
	private JButton btnReportGeneration;
	private JButton btnStaffManagement;
	private JPanel detailsPanel;
	private JLabel lblEldertrackWelcomePage;
	private JLabel lblLoggedInAs;
	private JLabel lblLastLogin;
	private JLabel lblLoginNric;
	private JButton btnLogout;
	private JLabel lblYourNotes;
	private JTextArea txtarea_stickynotes;
	
	MainMenuPanel() {
		setBackground(SystemColor.control);
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		lblTitle = new JLabel("Welcome to ElderTrack!");
		lblTitle.setBounds(10, 0, 754, 54);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblTitle);
		
		btnMedTrack = new JButton("Medication Tracking");
		btnMedTrack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMedTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.MEDICATIONPANEL);
			}
		});
		btnMedTrack.setBounds(10, 95, 242, 120);
		add(btnMedTrack);
		
		btnDietManagement = new JButton("Diet Management");
		btnDietManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDietManagement.setBounds(10, 230, 242, 120);
		btnDietManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.DIETPANEL);
			}
		});
		add(btnDietManagement);
		
		btnReportGeneration = new JButton("Report Generation");
		btnReportGeneration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReportGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.REPORTPANEL);
			}
		});
		btnReportGeneration.setBounds(10, 361, 242, 120);
		add(btnReportGeneration);
		
		if(MainFrame.getInstance().isManagementShown()) {
			btnStaffManagement = new JButton("People Management");
			btnStaffManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnStaffManagement.setBounds(10, 492, 242, 120);
			btnStaffManagement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
			        parentCards.show(MainFrame.CardsPanel, MainFrame.MGMTPANEL);
				}
			});
			add(btnStaffManagement);
		}
		
		detailsPanel = new JPanel();
		detailsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		detailsPanel.setBounds(262, 95, 723, 128);
		add(detailsPanel);
		detailsPanel.setLayout(null);
		
		lblEldertrackWelcomePage = new JLabel("ElderTrack Welcome Page");
		lblEldertrackWelcomePage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEldertrackWelcomePage.setBounds(10, 11, 247, 31);
		detailsPanel.add(lblEldertrackWelcomePage);
		
		lblLoggedInAs = new JLabel("Logged In:");
		lblLoggedInAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoggedInAs.setBounds(10, 45, 467, 21);
		detailsPanel.add(lblLoggedInAs);
		
		lblLastLogin = new JLabel("Last Login:");
		lblLastLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastLogin.setBounds(10, 95, 467, 21);
		detailsPanel.add(lblLastLogin);
		
		lblLoginNric = new JLabel("Login NRIC: ");
		lblLoginNric.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginNric.setBounds(10, 70, 467, 21);
		detailsPanel.add(lblLoginNric);
		
		btnLogout = new JButton("Sign Out");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(585, 12, 128, 36);
		detailsPanel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getInstance().endCurrentSession();
			}
		});
		
		lblYourNotes = new JLabel("Sticky Notes:");
		lblYourNotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourNotes.setBounds(262, 230, 120, 25);
		add(lblYourNotes);
		
		txtarea_stickynotes = new JTextArea();
		txtarea_stickynotes.setFont(new Font("Arial", Font.PLAIN, 18));
		txtarea_stickynotes.setBackground(new Color(230, 230, 250));
		txtarea_stickynotes.setBounds(262, 258, 723, 296);
		add(txtarea_stickynotes);
		
		JButton btnSaveNotes = new JButton("Save Notes");
		btnSaveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().getSessionInstance().setNotes(txtarea_stickynotes.getText());
			}
		});
		btnSaveNotes.setBounds(855, 565, 130, 47);
		add(btnSaveNotes);
	}
	
	void fillDetails() {
		StaffSession session = MainFrame.getInstance().getSessionInstance();
		lblLoggedInAs.setText("Logged In: " + session.getFullName() + " [ID:" + session.getStaffid() + "]");
		lblLoginNric.setText("Login NRIC: " + session.getNric());
		lblLastLogin.setText("Last Login: " + session.getLastLoginTimeString());
		txtarea_stickynotes.setText(MainFrame.getInstance().getSessionInstance().getNotes());
	}
	
	void clearDetails() {
		lblLoggedInAs.setText("Logged In: ");
		lblLoginNric.setText("Login NRIC: ");
		lblLastLogin.setText("Last Login: ");
		txtarea_stickynotes.setText("");
	}
	

}
