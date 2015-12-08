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

import eldertrack.login.SessionTools;
import eldertrack.login.StaffSession;
import javax.swing.JTextPane;

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
		
		
		JTextPane txtpnWer = new JTextPane();
		txtpnWer.setText("General Background Information:\r\n\r\nYour Name:\r\nLast Logged In:");
		txtpnWer.setBounds(312, 95, 643, 386);
		add(txtpnWer);
	}
}
