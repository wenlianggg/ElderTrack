package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class MedManageSearchPanel extends JPanel {

	private static final long serialVersionUID = 4090792694881415463L;
	
	
	public MedManageSearchPanel() {
		setBounds(5, 5, 975, 510);
		setLayout(null);

		JLabel lblManage = new JLabel("Dosage Management");
		lblManage.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblManage.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblManage.setBounds(25, 25, 300, 41);
		add(lblManage);
		
		JLabel lblOverview = new JLabel("Overview:");
		lblOverview.setForeground(new Color(0, 128, 128));
		lblOverview.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOverview.setBounds(85, 107, 151, 41);
		add(lblOverview);
		
		JTextPane txtpnRoomNumber = new JTextPane();
		txtpnRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnRoomNumber.setText("Room Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~\r\n\r\nRoom Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~\r\n\r\nRoom Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~\r\n\r\nRoom Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~\r\n\r\nRoom Number: ~\r\nTotal Number of Elderly: ~\r\nTotal Number of Male: ~\r\nTotal Number of Female: ~");
		
		JScrollPane txtpnScroll=new JScrollPane(txtpnRoomNumber);
		txtpnScroll.setBounds(85, 145, 796, 226);
		add(txtpnScroll);
		
		
		
		JButton btnGetManagement = new JButton("Start Managing");
		btnGetManagement.setBounds(85, 395, 278, 31);
		btnGetManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(btnGetManagement);
		btnGetManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel gottenManage=new MedManagePanel();
				MedPanel.MedCardPanel.add(gottenManage,MedPanel.MMANAGEPANEL);
				
				CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
			    mainCards.show(MedPanel.MedCardPanel, MedPanel.MCHECKPANEL);
			}
		});
		
	}
	
}
