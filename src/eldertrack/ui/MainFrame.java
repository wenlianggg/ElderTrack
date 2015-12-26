package eldertrack.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.EtchedBorder;

import eldertrack.login.StaffSession;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    final static String LOGINPANEL = "Login Panel";
    final static String MEDICATIONPANEL = "Medication Panel";
    final static String DIETPANEL = "Diet Panel";
    final static String MGMTPANEL = "Management Panel";
    final static String MENUPANEL = "Main Menu Panel";
    private JPanel MasterPane;
    private static LoginPanel LoginPanel;
    private DietPanel DietPanel;
    private MedPanel MedPanel;
    private MgmtPanel MgmtPanel;
    private MainMenuPanel MainMenu;
    static JPanel CardsPanel;
	StaffSession session;  
	// JFrame (MainFrame) > Normal JPanel (MasterPane) > CardLayout JPanel (MainPanel) > Feature Panels (LoginPanel)
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Change look to native Windows / OS X / Linux
					MainFrame frame = new MainFrame();
					frame.setVisible(true); // Set the main frame as visible
					showWeatherPanel(frame.MasterPane);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame!
	 */
	
	public MainFrame() {
		this.setTitle("ElderTrack Toolkit - ITP192-03 Team 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 810);
		setResizable(false);
		
		MasterPane = new JPanel();
		MasterPane.setBorder(null);
		MasterPane.setLayout(null);
		setContentPane(MasterPane);
		

		
		LoginPanel = new LoginPanel();
		LoginPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		DietPanel = new DietPanel();
		MedPanel = new MedPanel();
		MgmtPanel = new MgmtPanel();
		MainMenu = new MainMenuPanel();
		
		CardsPanel = new JPanel(new CardLayout());
		MasterPane.add(CardsPanel);
		CardsPanel.add(LoginPanel, LOGINPANEL);
		CardsPanel.add(DietPanel, DIETPANEL);
		CardsPanel.add(MedPanel, MEDICATIONPANEL);
		CardsPanel.add(MgmtPanel, MGMTPANEL);
		CardsPanel.add(MainMenu, MENUPANEL);
		CardsPanel.setLocation(0, 0);
		CardsPanel.setSize(994, 671);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, LOGINPANEL);
		
		
		/**
		 * COMBO BOX FOR TEST GUI
		 */
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
		        JComboBox<?> jcb = (JComboBox<?>) evt.getSource();
		        CardLayout cl = (CardLayout) CardsPanel.getLayout();
		        cl.show(CardsPanel, jcb.getSelectedItem().toString());
			}
		});
		comboBox.setSize(174, 26);
		comboBox.setLocation(10, 682);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {LOGINPANEL, MGMTPANEL, MEDICATIONPANEL, DIETPANEL, MENUPANEL}));
		comboBox.setSelectedIndex(0);
		MasterPane.add(comboBox);
	}
	
	private static void showWeatherPanel(JPanel MasterPane) {
		JPanel WeatherPanel = new WeatherPanel();
		WeatherPanel.setLocation(790, 671);
		MasterPane.add(WeatherPanel);
	}
	
}
