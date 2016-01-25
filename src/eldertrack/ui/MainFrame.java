package eldertrack.ui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.net.URL;
import java.awt.event.ItemEvent;
import javax.swing.border.EtchedBorder;

import eldertrack.login.AccessLevel;
import eldertrack.login.StaffSession;
import eldertrack.weather.Weather;
import java.awt.Color;

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
    private DietSection DietSection;
    private MedPanel MedPanel;
    private ReportMainPanel ReportPanel;
    private MgmtPanel MgmtPanel;
    private MainMenuPanel MainMenu;
	private WeatherPanel weatherPanel;
    static JPanel CardsPanel;
	JComboBox<String> comboBox;
	// Singleton Class Design
	private StaffSession session;
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
		this.setTitle("ElderTrack - Utilities For Nursing Homes (ITP192-03-Team 2)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 810);
		setResizable(false);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		URL url = MainFrame.class.getResource("/eldertrack/resources/icon.png");
		Image img = kit.createImage(url);
		setIconImage(img);
		
		MasterPane = new JPanel();
		MasterPane.setBorder(null);
		MasterPane.setLayout(null);
		setContentPane(MasterPane);
		
		weatherPanel = new WeatherPanel();

		weatherPanel.setLocation(790, 671);
		MasterPane.add(weatherPanel);
		Thread thrd = new Thread(weatherTask);
		thrd.start();
		
		LoginPanel = new LoginPanel();
		LoginPanel.setBorder(lBorder);
		
		CardsPanel = new JPanel(new CardLayout());
		MasterPane.add(CardsPanel);
		CardsPanel.add(LoginPanel, LOGINPANEL);

		CardsPanel.setLocation(0, 0);
		CardsPanel.setSize(994, 671);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, LOGINPANEL);
		
		String s = "Hello ElderTrack Implementers! This is a sample scrolling announcement text, support for querying will be added soon!";
		MarqueePanel marqueePanel = new MarqueePanel(s, 160);
		marqueePanel.setForeground(new Color(255, 255, 255));
		marqueePanel.setBackground(new Color(0, 153, 255));
		marqueePanel.setBounds(0, 752, 790, 29);
		marqueePanel.start();
		MasterPane.add(marqueePanel);

		
	}
	
	void constructPanels() {
		JProgressBar jpbar = LoginPanel.progressBar;
		System.out.println("--------------------- CONSTRUCTING ALL PANELS NOW! ---------------------");
		jpbar.setValue(25);
				
		// Initialize Diet Panel
		jpbar.setString("Initializing Diet Management...");
		jpbar.update(jpbar.getGraphics());
		DietSection = new DietSection();
		DietSection.setBorder(lBorder);
		CardsPanel.add(DietSection, DIETPANEL);
		jpbar.setValue(50);

		
		// Initialize Med Panel
		jpbar.setString("Initializing Medication...");
		jpbar.update(jpbar.getGraphics());
		MedPanel = new MedPanel();
		MedPanel.setBorder(lBorder);
		CardsPanel.add(MedPanel, MEDICATIONPANEL);
		jpbar.setValue(65);

		
		// Initialize Management Panel
		jpbar.setString("Initializing Management...");
		jpbar.update(jpbar.getGraphics());
		if(isManagementShown()) {
			MgmtPanel = new MgmtPanel();
			MgmtPanel.setBorder(lBorder);
			CardsPanel.add(MgmtPanel, MGMTPANEL);
		}
		jpbar.setValue(85);

		// Initialize Report Panel
		jpbar.setString("Initializing Report Generation...");
		jpbar.update(jpbar.getGraphics());
		ReportPanel = new ReportMainPanel();
		ReportPanel.setBorder(lBorder);
		CardsPanel.add(ReportPanel, REPORTPANEL);

		// Initialize Main Menu Panel
		jpbar.setString("Initializing Main Menu...");
		LoginPanel.progressBar.setValue(90);
		jpbar.update(jpbar.getGraphics());
		MainMenu = new MainMenuPanel();
		MainMenu.setBorder(lBorder);
		CardsPanel.add(MainMenu, MENUPANEL);
		jpbar.setValue(95);
		jpbar.update(jpbar.getGraphics());
		
		// Add menus to combo box
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
		jpbar.setValue(98);
		jpbar.update(jpbar.getGraphics());
	
		MasterPane.add(comboBox);
		MainMenu.fillDetails();
		jpbar.setValue(100);
		jpbar.update(jpbar.getGraphics());
	}
	
	void deconstructPanels() {
		System.out.println("--------------------- DECONSTRUCTING ALL PANELS NOW! ---------------------");
		comboBox.setVisible(false);
		CardsPanel.remove(DietSection);
		CardsPanel.remove(MedPanel);
		CardsPanel.remove(ReportPanel);
		if(isManagementShown())
			CardsPanel.remove(MgmtPanel);
		CardsPanel.remove(MainMenu);
		MasterPane.remove(comboBox);
		comboBox = null;
		DietSection = null;
		MedPanel = null;
		ReportPanel = null;
		MgmtPanel = null;
		MainMenu = null;
		LoginPanel.progressBar.setValue(0);
		LoginPanel.progressBar.setString("Login to begin loading!");
		LoginPanel.progressBar.update(LoginPanel.progressBar.getGraphics());
		System.out.println("Panels deconstructed!");
	}
	
	
	Runnable weatherTask = () -> {
		System.out.println("Obtaining data from URL on " + Thread.currentThread().getName());
		Weather weatherinfo = Weather.getWeather();
		weatherPanel.showWeatherInfo(weatherinfo);
	};
	
	
	// Singleton Class Design
	public static MainFrame getInstance() {
		return frame;
	}
	
	// For getting logged in user information
	public StaffSession getSessionInstance() {
		return session;
	}
	
	StaffSession setSessionInstance(StaffSession session) {
		this.session = session;
		return getInstance().getSessionInstance();
	}
	
	// Triggers on logout
	boolean endCurrentSession() {
			CardLayout cards = (CardLayout) MainFrame.CardsPanel.getLayout();
			cards.show(MainFrame.CardsPanel, MainFrame.LOGINPANEL);
			deconstructPanels();
			this.session = null;
			System.out.println("Successfully logged out!");
			return true;
	}
	
	boolean isManagementShown() {
		AccessLevel al = MainFrame.getInstance().getSessionInstance().getAccessLevel();
		if (al == AccessLevel.MANAGER || al == AccessLevel.ADMIN)
			return true;
		else
			return false;
	}
	
	MgmtPanel getManagementPanel() {
		return this.MgmtPanel;
	}
	
	DietSection getDietPanel() {
		return this.DietSection;
	}
}
