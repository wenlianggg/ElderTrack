package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class MedManageSearchPanel extends JPanel {

	private static final long serialVersionUID = 4090792694881415463L;
	private JComboBox<String> roomComboBox;
	
	
	public MedManageSearchPanel() {
		setBounds(5, 5, 975, 510);
		setLayout(null);

		JLabel lblManage = new JLabel("Dosage Management");
		lblManage.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblManage.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblManage.setBounds(25, 25, 300, 41);
		add(lblManage);
		
		JLabel lblRoomNumber = new JLabel("Room Number: ");
		lblRoomNumber.setBounds(90, 117, 151, 41);
		lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRoomNumber.setForeground(new Color(0, 128, 128));
		add(lblRoomNumber);
		
		roomComboBox = new JComboBox<String>();
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
	}

}
