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
    private static final EtchedBorder lBorder = new EtchedBorder(EtchedBorder.LOWERED, null, null);
	final static String LOGINPANEL = "Login Panel";
    final static String MEDICATIONPANEL = "Medication Panel";
    final static String DIETPANEL = "Diet Panel";
    final static String MGMTPANEL = "Management Panel";
    final static String REPORTPANEL = "Report Panel";
    final static String MENUPANEL = "Main Menu Panel";
    private JPanel MasterPane;
    private LoginPanel LoginPanel;
    private DietPanel DietPanel;
    private MedPanel MedPanel;
    private ReportMainPanel ReportPanel;
    private MgmtPanel MgmtPanel;
    private MainMenuPanel MainMenu;
    static JPanel CardsPanel;
	JComboBox<String> comboBox;
	// Singleton Class Design
	private static StaffSession session;
	private static MainFrame frame;
	// JFrame (MainFrame) > Normal JPanel (MasterPane) > CardLayout JPanel (MainPanel) > Feature Panels (LoginPanel)
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Change look to native Windows / OS X / Linux
					frame = new MainFrame();
					frame.setVisible(true); // Set the main frame as visible
					frame.showWeatherPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame!
	 */
	
	private MainFrame() {
		this.setTitle("ElderTrack Toolkit - ITP192-03 Team 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 810);
		setResizable(false);
		
		MasterPane = new JPanel();
		MasterPane.setBorder(null);
		MasterPane.setLayout(null);
		setContentPane(MasterPane);
		
		LoginPanel = new LoginPanel();
		LoginPanel.setBorder(lBorder);
		
		CardsPanel = new JPanel(new CardLayout());
		MasterPane.add(CardsPanel);
		CardsPanel.add(LoginPanel, LOGINPANEL);

		CardsPanel.setLocation(0, 0);
		CardsPanel.setSize(994, 671);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, LOGINPANEL);
		



	}
	
	void constructPanels() {
		System.out.println("--------------------- CONSTRUCTING ALL PANELS NOW! ---------------------");
		DietPanel = new DietPanel();
		DietPanel.setBorder(lBorder);
		MedPanel = new MedPanel();
		MedPanel.setBorder(lBorder);
		MgmtPanel = new MgmtPanel();
		MgmtPanel.setBorder(lBorder);
		ReportPanel = new ReportMainPanel();
		ReportPanel.setBorder(lBorder);
		MainMenu = new MainMenuPanel();
		MainMenu.setBorder(lBorder);
		CardsPanel.add(DietPanel, DIETPANEL);
		CardsPanel.add(MedPanel, MEDICATIONPANEL);
		CardsPanel.add(ReportPanel, REPORTPANEL);
		CardsPanel.add(MgmtPanel, MGMTPANEL);
		CardsPanel.add(MainMenu, MENUPANEL);		
		comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
		        JComboBox<?> jcb = (JComboBox<?>) evt.getSource();
		        CardLayout cl = (CardLayout) CardsPanel.getLayout();
		        cl.show(CardsPanel, jcb.getSelectedItem().toString());
			}
		});
		comboBox.setSize(174, 26);
		comboBox.setLocation(10, 682);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {MENUPANEL, MGMTPANEL, MEDICATIONPANEL, DIETPANEL, REPORTPANEL}));
		comboBox.setSelectedIndex(0);
		MasterPane.add(comboBox);
		MainMenu.fillDetails();
	}
	
	void deconstructPanels() {
		System.out.println("--------------------- DECONSTRUCTING ALL PANELS NOW! ---------------------");
		comboBox.setVisible(false);
		CardsPanel.remove(DietPanel);
		CardsPanel.remove(MedPanel);
		CardsPanel.remove(ReportPanel);
		CardsPanel.remove(MgmtPanel);
		CardsPanel.remove(MainMenu);
		MasterPane.remove(comboBox);
		comboBox = null;
		DietPanel = null;
		MedPanel = null;
		ReportPanel = null;
		MgmtPanel = null;
		MainMenu = null;
		System.out.println("Panels deconstructed!");
	}
	
	private void showWeatherPanel() {
		JPanel WeatherPanel = new WeatherPanel();
		WeatherPanel.setLocation(790, 671);
		getInstance().MasterPane.add(WeatherPanel);
	}
	
	// Singleton Class Design
	public static MainFrame getInstance() {
		return frame;
	}
	
	// For getting logged in user information
	public StaffSession getSessionInstance() {
		return MainFrame.session;
	}
	
	StaffSession setSessionInstance(StaffSession session) {
		MainFrame.session = session;
		return MainFrame.session;
	}
	
	// Triggers on logout
	boolean endCurrentSession() {
		MainFrame.session = null;
		if (MainFrame.session == null) {
			CardLayout cards = (CardLayout) MainFrame.CardsPanel.getLayout();
			cards.show(MainFrame.CardsPanel, MainFrame.LOGINPANEL);
			deconstructPanels();
			System.out.println("Successfully logged out!");
			return true;
		} else {
			return false;
		}
	}
}
