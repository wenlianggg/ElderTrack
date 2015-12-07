package eldertrack.ui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
    final static String DMAINPANEL = "Diet Main Panel";
	final static String DADDPANEL = "Diet Add Panel";
    final static String DMODPANEL = "Diet Modify Panel";
    final static String DMENUPANEL = "Diet Menu Panel";
	static JPanel CardsPanel;
	
	// Constructor
	DietPanel() {
		JPanel DietMainPanel = new DietMainPanel();
		JPanel DietAddPanel = new DietAddPanel();
		JPanel DietModifyPanel = new DietModifyPanel();
		JPanel DietMenuPanel = new DietMenuPanel();
		
		CardsPanel = new JPanel(new CardLayout());
		CardsPanel.add(DietMainPanel, DMAINPANEL);
		CardsPanel.add(DietAddPanel, DADDPANEL);
		CardsPanel.add(DietModifyPanel, DMODPANEL);
		CardsPanel.add(DietMenuPanel, DMENUPANEL);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, DMAINPANEL);
		
		setLayout(null);
		CardsPanel.setBounds(0, 0, 994, 671);
		add(CardsPanel);
	}
}