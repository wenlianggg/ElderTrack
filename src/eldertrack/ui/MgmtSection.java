package eldertrack.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MgmtSection extends JPanel{
	private static final long serialVersionUID = 1494179536490840807L;
	// Declare instance variables
	static JPanel CardDeck;
	private JPanel announcementPanel;
	private JPanel mgmtPanel;
	final static String MGMTPANEL = "Management Panel";
	final static String ANNOUNCEMENTPANEL = "Announcement Panel";
	
	// Constructor
	MgmtSection(){
		announcementPanel = new AnnouncementPanel();
		mgmtPanel = new MgmtPanel();
		
		// Create the cards
		CardDeck = new JPanel(new CardLayout());
		CardDeck.add(announcementPanel, ANNOUNCEMENTPANEL);
		CardDeck.add(mgmtPanel, MGMTPANEL);
		((CardLayout)CardDeck.getLayout()).show(CardDeck, MGMTPANEL);
		
		setLayout(null);
		CardDeck.setBounds(0, 0, 994, 671);
		add(CardDeck);
	}
	
	 public MgmtPanel getManagementPanel(){
		return (MgmtPanel)this.mgmtPanel;
	}
}
