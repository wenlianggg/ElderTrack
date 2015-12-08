package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

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
		
		MedDosageSearchPanel medDosageSearchPanel = new MedDosageSearchPanel();
		medDosageSearchPanel.setBackground(UIManager.getColor("TabbedPane.light"));
		TabbedPanel.add("Dosage", medDosageSearchPanel);
		TabbedPanel.add("Check-Up",new MedCheckPanel());
		add(TabbedPanel);
		
	}

}
