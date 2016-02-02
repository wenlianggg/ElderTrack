package eldertrack.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;

import eldertrack.login.AccessLevel;

public class DietSection extends JPanel {
	private static final long serialVersionUID = 1L;
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
	DietSection() {
		dietMainPanel = new DietMainPanel();
		dietAddPanel = new DietAddPanel();
		dietModifyPanel = new DietModifyPanel();
		if(isAdmin())
			dietMenuPanel = new DietMenuPanel();
		
		CardsPanel = new JPanel(new CardLayout());
		CardsPanel.add(dietMainPanel, DMAINPANEL);
		CardsPanel.add(dietAddPanel, DADDPANEL);
		CardsPanel.add(dietModifyPanel, DMODPANEL);
		if(isAdmin())
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
	
	private boolean isAdmin() {
		AccessLevel al = MainFrame.getInstance().getSessionInstance().getAccessLevel();
		if (al.equals(AccessLevel.MANAGER) || al.equals(AccessLevel.ADMIN)) {
			return true;
		} else {
			return false;
		}
	}

}
