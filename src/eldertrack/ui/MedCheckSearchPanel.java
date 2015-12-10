package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedCheckSearchPanel extends JPanel {


	private static final long serialVersionUID = -1155434751690765910L;


	public MedCheckSearchPanel() {
		setBounds(5, 5, 975, 510);
		setLayout(null);
		
		JLabel lblCheckUpSystem = new JLabel("Check Up System");
		lblCheckUpSystem.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblCheckUpSystem.setBounds(25, 25, 232, 41);
		lblCheckUpSystem.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
		add(lblCheckUpSystem);
		
		
		JLabel lblRoomNumber = new JLabel("Room Number: ");
		lblRoomNumber.setBounds(90, 117, 151, 41);
		lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRoomNumber.setForeground(new Color(0, 128, 128));
		add(lblRoomNumber);
		
		JComboBox<String> roomComboBox = new JComboBox<String>();
		roomComboBox.setBackground(UIManager.getColor("TextField.highlight"));
		roomComboBox.setBounds(241, 124, 125, 31);
		roomComboBox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		roomComboBox.addItem("101");
		roomComboBox.addItem("102");
		roomComboBox.addItem("103");
		add(roomComboBox);
		
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setBounds(90, 177, 80, 41);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTime.setForeground(new Color(0, 128, 128));
		add(lblTime);
		
		
		JComboBox<String> timeComboBox = new JComboBox<String>();
		timeComboBox.setBackground(UIManager.getColor("TextField.highlight"));
		timeComboBox.setBounds(241, 184, 125, 31);
		timeComboBox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		timeComboBox.addItem("Morning");
		timeComboBox.addItem("Afternoon");
		timeComboBox.addItem("Night");
		add(timeComboBox);
		
		JLabel lblOverview = new JLabel("Overview:");
		lblOverview.setForeground(new Color(0, 128, 128));
		lblOverview.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOverview.setBounds(551, 117, 151, 41);
		add(lblOverview);
		
		JTextPane txtpnRoomNumber = new JTextPane();
		txtpnRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnRoomNumber.setText("Room Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~\r\nRecommandard Helper: ~\r\nAlert:\r\nPatient: ~, cough last time\r\n");
		txtpnRoomNumber.setBounds(551, 155, 339, 226);
		add(txtpnRoomNumber);
		
		JButton btnGetCheck = new JButton("Start Check-Up");
		btnGetCheck.setBounds(90, 256, 279, 31);
		btnGetCheck.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(btnGetCheck);
		btnGetCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel getCheck=new MedDosagePanel();
				getCheck.setVisible(true);
				
				
				
				CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
			    mainCards.show(MedPanel.MedCardPanel, MedPanel.MCHECKPANEL);
			}
		});
	}
}
