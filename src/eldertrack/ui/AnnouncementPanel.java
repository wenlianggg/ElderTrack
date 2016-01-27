package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import eldertrack.db.SQLObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnnouncementPanel extends JPanel{
	private SQLObject so = new SQLObject();
	private static final long serialVersionUID = -8742307067990031379L;
	private JLabel AnnouncementManager;
	private String defaultText;
	private String fontText;
	private String newText;
	private MarqueePanel previewMarquee;
	private JPanel panel_1;
	
	AnnouncementPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		// Get current announcement text
		defaultText = getMarqueeText();
		
		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
		
		JButton eManagement = new JButton("Back to people management\r\n");
		eManagement.setBounds(26, 555, 307, 25);
		add(eManagement);
		
		AnnouncementManager = new JLabel("ElderTrack Announcement Settings");
		AnnouncementManager.setBounds(26, 5, 594, 61);
		AnnouncementManager.setForeground(SystemColor.textHighlight);
		AnnouncementManager.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(AnnouncementManager);
		
		JLabel announcementTextHeader = new JLabel("Edit announcement text :");
		announcementTextHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
		announcementTextHeader.setBounds(26, 361, 206, 22);
		add(announcementTextHeader);
		
		JTextField editAnnouncementText = new JTextField();
		editAnnouncementText.setToolTipText("");
		editAnnouncementText.setFont(new Font("Monospaced", Font.PLAIN, 13));
		editAnnouncementText.setBounds(26, 385, 933, 30);
		editAnnouncementText.setText(defaultText);
		add(editAnnouncementText);
		
		JButton btnNewButton = new JButton("Save Announcement Text");
		btnNewButton.setBounds(26, 428, 206, 25);
		add(btnNewButton);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(26, 120, 933, 225);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel announcementPreview = new JLabel("Announcement preview");
		announcementPreview.setFont(new Font("Tahoma", Font.PLAIN, 18));
		announcementPreview.setBounds(26, 99, 189, 22);
		add(announcementPreview);
		
		previewMarquee = new MarqueePanel(defaultText, 185);
		previewMarquee.setBounds(0, 100, 933, 29);
		previewMarquee.setFont("Tahoma", Font.BOLD, 18);
		panel_1.add(previewMarquee);
		previewMarquee.setBackground(new Color(0, 153, 255));
		previewMarquee.start();
		
		//// Event Handlers ////
		
		// Update text in database
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateMarqueeText(editAnnouncementText.getText());
			}
		});
		
		// Return to management panel
		eManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout parentCards = (CardLayout)MgmtSection.CardDeck.getLayout();
				parentCards.show(MgmtSection.CardDeck, MgmtSection.MGMTPANEL);
			}
		});
		
		// Return to main menu
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		});
		
		editAnnouncementText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Remove and Re-add marquee
				repaintMarquee(editAnnouncementText.getText());
				
				// Reload
				panel_1.repaint();
				panel_1.revalidate();
				previewMarquee.repaint();
				previewMarquee.revalidate();
			}
		});
		
	}
	
	// Methods
	
	private String getMarqueeText(){
		try{
		PreparedStatement ps = so.getPreparedStatement("SELECT * FROM et_scrollcfg");
		ResultSet config = ps.executeQuery();
		config.next();
		return config.getString("value");
		}catch(SQLException e){
			e.printStackTrace();
			return "";
		}
	}
	
	private void updateMarqueeText(String text){
		try{
		PreparedStatement ps = so.getPreparedStatement("UPDATE et_scrollcfg SET value=? WHERE cfg = 'text' ");
		ps.setString(1, text);
		ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		MainFrame.getInstance().setScrollText(text);
	}
	
	private void repaintMarquee(String newText){
		panel_1.remove(previewMarquee);
		previewMarquee = null;
		previewMarquee = new MarqueePanel(newText, 160);
		previewMarquee.setBounds(0, 100, 933, 29);
		previewMarquee.setFont("Tahoma", Font.BOLD, 18);
		panel_1.add(previewMarquee);
		previewMarquee.setBackground(new Color(0, 153, 255));
		previewMarquee.start();
	}
}
