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
	static JTabbedPane TabbedPanel=new JTabbedPane();
	JLabel lblMedPanelLbl;
	
	
	public MedMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblMedPanelLbl = new JLabel("ElderTrack Medication");
		lblMedPanelLbl.setForeground(new Color(51, 153, 255));
		lblMedPanelLbl.setBounds(15, 15, 392, 54);
		lblMedPanelLbl.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(lblMedPanelLbl);    
		
		
		
		TabbedPanel.setBounds(10, 85, 975, 510);
		TabbedPanel.setFont( new Font( "Segoe UI", Font.BOLD|Font.ITALIC, 20 ) );
		
		
		
		TabbedPanel.add("Dosage",new MedDosageSearchPanel());
		TabbedPanel.add("Check-Up",new MedCheckSearchPanel());
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
