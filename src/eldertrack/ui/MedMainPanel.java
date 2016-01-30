package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MedMainPanel extends JPanel {

	private static final long serialVersionUID = -2955963900083964862L;
	JTabbedPane TabbedPanel=new JTabbedPane();
	
	
	public MedMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		JLabel lblMedPanelLbl = new JLabel("ElderTrack Suite");
		lblMedPanelLbl.setForeground(new Color(51, 153, 255));
		lblMedPanelLbl.setBounds(10, 0, 280, 54);
		lblMedPanelLbl.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblMedPanelLbl);   
		
		JLabel label = new JLabel("Utilities for Nursing Homes");
		label.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(300, 20, 262, 30);
		add(label);
		
		
		TabbedPanel.setBounds(10, 85, 975, 510);
		TabbedPanel.setFont( new Font( "Segoe UI", Font.BOLD|Font.ITALIC, 20 ) );
		
		
		
		TabbedPanel.add("Dosage",new MedDosageSearchPanel());
		TabbedPanel.add("Check-Up",new MedCheckSearchPanel());
		TabbedPanel.add("Manage",new MedManageSearchPanel());
		add(TabbedPanel);
		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		});
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
		
		

		
	}
}
