package eldertrack.ui;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
    final static String DMAINPANEL = "Diet Main Panel";
	final static String DADDPANEL = "Diet Add Panel";
    final static String DMODPANEL = "Diet Modify Panel";
    final static String DMENUPANEL = "Diet Menu Panel";
	static JPanel CardsPanel;
	private JPanel dietMainPanel;
	private JPanel dietAddPanel;
	private JPanel dietModifyPanel;
	private JPanel dietMenuPanel;
	
	// Constructor
	DietPanel() {
		dietMainPanel = new DietMainPanel();
		dietAddPanel = new DietAddPanel();
		dietModifyPanel = new DietModifyPanel();
		dietMenuPanel = new DietMenuPanel();
		
		CardsPanel = new JPanel(new CardLayout());
		CardsPanel.add(dietMainPanel, DMAINPANEL);
		CardsPanel.add(dietAddPanel, DADDPANEL);
		CardsPanel.add(dietModifyPanel, DMODPANEL);
		CardsPanel.add(dietMenuPanel, DMENUPANEL);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, DMAINPANEL);
		
		setLayout(null);
		CardsPanel.setBounds(0, 0, 994, 671);
		add(CardsPanel);
	}
	
	DietAddPanel getDietAddPanel() {
		return (DietAddPanel) this.dietAddPanel;
	}
	
	DietModifyPanel getDietModifyPanel() {
		return (DietModifyPanel) this.dietModifyPanel;
	}
}