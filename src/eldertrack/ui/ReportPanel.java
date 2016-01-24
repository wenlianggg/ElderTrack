package eldertrack.ui;


import javax.swing.JPanel;
import java.awt.CardLayout;

public class ReportPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
    final static String RMAINPANEL = "Report Main Panel";
	static JPanel CardsPanel;
	
	// Constructor
	ReportPanel() {
		JPanel ReportMainPanel = new ReportMainPanel();
		
		CardsPanel = new JPanel(new CardLayout());
		CardsPanel.add(ReportMainPanel, RMAINPANEL);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, RMAINPANEL);
		
		setLayout(null);
		CardsPanel.setBounds(0, 0, 994, 671);
		add(CardsPanel);
	}
}