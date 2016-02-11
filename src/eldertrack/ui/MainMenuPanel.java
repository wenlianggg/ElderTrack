package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eldertrack.login.AccessLevel;
import eldertrack.login.StaffSession;
import eldertrack.report.CalculateAvr;
import eldertrack.report.MedicalData;

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
	private JLabel lblAccessLevel;
	private JLabel lbllastSavedOn;
	
	MainMenuPanel() {
		setBackground(SystemColor.control);
		setLayout(null);
		setBounds(0, 0, 995, 670);
		
		lblTitle = new JLabel("ElderTrack Suite");
		lblTitle.setBounds(10, 0, 280, 54);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblTitle);
		
		btnMedTrack = new JButton("Medication Tracking");
		btnMedTrack.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		btnMedTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.MEDICATIONPANEL);
			}
		});
		btnMedTrack.setBounds(10, 95, 242, 120);
		add(btnMedTrack);
		
		btnDietManagement = new JButton("Diet Tracking");
		btnDietManagement.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		btnDietManagement.setBounds(10, 230, 242, 120);
		btnDietManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.DIETPANEL);
			}
		});
		add(btnDietManagement);
		
		btnReportGeneration = new JButton("Report Generation");
		btnReportGeneration.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		btnReportGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = null;
				try {
					MedicalData.main(args);
					CalculateAvr.main(args);

				} catch (ClassNotFoundException | IOException e2) {
					e2.printStackTrace();
				}
		        CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
		        parentCards.show(MainFrame.CardsPanel, MainFrame.REPORTPANEL);
			}
		});
		btnReportGeneration.setBounds(10, 361, 242, 120);
		add(btnReportGeneration);
		
		if(MainFrame.getInstance().isManagementShown()) {
			btnStaffManagement = new JButton("Management");
			btnStaffManagement.setFont(new Font("Segoe UI", Font.PLAIN, 21));
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
		lblLoggedInAs.setBounds(10, 45, 408, 21);
		detailsPanel.add(lblLoggedInAs);
		
		lblLastLogin = new JLabel("Last Login:");
		lblLastLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastLogin.setBounds(10, 95, 408, 21);
		detailsPanel.add(lblLastLogin);
		
		lblLoginNric = new JLabel("Login NRIC: ");
		lblLoginNric.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginNric.setBounds(10, 70, 408, 21);
		detailsPanel.add(lblLoginNric);
		
		btnLogout = new JButton("Sign Out");
		btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnLogout.setBounds(585, 9, 128, 36);
		detailsPanel.add(btnLogout);
		
		lblAccessLevel = new JLabel("Access Level: ");
		lblAccessLevel.setBounds(585, 49, 128, 16);
		detailsPanel.add(lblAccessLevel);
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
		btnSaveNotes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSaveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainFrame.getInstance().getSessionInstance().setNotes(txtarea_stickynotes.getText()))
					updateStickyLastSaved();
			}
		});
		btnSaveNotes.setBounds(838, 565, 147, 47);
		add(btnSaveNotes);
		
		JLabel lblUtilitiesForNursing = new JLabel("Utilities for Nursing Homes");
		lblUtilitiesForNursing.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblUtilitiesForNursing.setBounds(300, 20, 262, 30);
		add(lblUtilitiesForNursing);
		
		lbllastSavedOn = new JLabel("(Last saved on --/--/---- --:--)");
		lbllastSavedOn.setBounds(801, 237, 184, 14);
		add(lbllastSavedOn);
	}
	
	void fillDetails() {
		StaffSession session = MainFrame.getInstance().getSessionInstance();
		AccessLevel al = session.getAccessLevel();
		String access;
		switch(al) {
			case MANAGER:
				access = "Manager";
				break;
			case ADMIN:
				access = "Admin";
				break;
			case SRSTAFF:
				access = "Senior Staff";
				break;
			case STAFF:
				access = "Staff";
				break;
			default:
				access = "Denied";
		}
		lblLoggedInAs.setText("Logged In: " + session.getFullName() + " [ID:" + session.getStaffid() + "]");
		lblLoginNric.setText("Login NRIC: " + session.getNric());
		lblLastLogin.setText("Last Login: " + session.getLastLoginTimeString());
		lblAccessLevel.setText("Access Level: " + access);
		txtarea_stickynotes.setText(MainFrame.getInstance().getSessionInstance().getNotes());
		lbllastSavedOn.setText("(Last saved on " + MainFrame.getInstance().getSessionInstance().getLastNoteTimeString() + ")");
	}
	
	void clearDetails() {
		lblLoggedInAs.setText("Logged In: ");
		lblLoginNric.setText("Login NRIC: ");
		lblLastLogin.setText("Last Login: ");
		txtarea_stickynotes.setText("");
	}
	
	void updateStickyLastSaved() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		lbllastSavedOn.setText("(Last saved on " + sdf.format(now) + ")");
	}
}
