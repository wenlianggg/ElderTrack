package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AnnouncementPanel extends JPanel{
	private SQLObject so = new SQLObject();
	private static final long serialVersionUID = -8742307067990031379L;
	private String defaultText;
	private String fontTypeString;
	private int fontTypeValue;
	private String fontStyle;
	private MarqueePanel previewMarquee;
	private JPanel panel_1;
	private JLabel lblEManagementLbl;
	private JComboBox<String> fontTypeSelector;
	private JComboBox<String> fontStyleSelector;
	
	AnnouncementPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		getMarqueeConfig();
		
		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
		
		JButton eManagement = new JButton("Back to people management\r\n");
		eManagement.setBounds(26, 555, 307, 25);
		add(eManagement);
		
		lblEManagementLbl = new JLabel("ElderTrack Suite");
		lblEManagementLbl.setForeground(SystemColor.textHighlight);
		lblEManagementLbl.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblEManagementLbl.setBounds(10, 0, 280, 54);
		add(lblEManagementLbl);
		
		JLabel lblDietManagement = new JLabel("Management\r\n - Announcement Settings");
		lblDietManagement.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblDietManagement.setBounds(300, 20, 388, 30);
		add(lblDietManagement);
		
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
		btnNewButton.setBounds(26, 517, 206, 25);
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
		previewMarquee.setFont(fontStyle, fontTypeValue, 17);
		panel_1.add(previewMarquee);
		previewMarquee.setBackground(new Color(0, 153, 255));
		
		fontTypeSelector = new JComboBox<String>();
		fontTypeSelector.setModel(new DefaultComboBoxModel<String>(new String[] {"Plain", "Bold", "Italic"}));
		fontTypeSelector.setSelectedIndex(fontTypeValue);
		fontTypeSelector.setBounds(26, 450, 129, 22);
		add(fontTypeSelector);
		
		JLabel selectFontStyleLabel = new JLabel("Select font style\r\n");
		selectFontStyleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selectFontStyleLabel.setBounds(188, 428, 145, 22);
		add(selectFontStyleLabel);
		
		JLabel selectFontTypeLabel = new JLabel("Select font type\r\n\r\n");
		selectFontTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selectFontTypeLabel.setBounds(26, 428, 129, 22);
		add(selectFontTypeLabel);
		
		fontStyleSelector = new JComboBox<String>();
		fontStyleSelector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fontStyleSelector.setModel(new DefaultComboBoxModel<String>(new String[] {"Tahoma", "Segoe UI", "Times New Roman", "Roboto", "SansSerif", "Arial Rounded MT Bold", "Agency FB", "Bauhaus 93 Regular", "Bernard MT Condensed", "Fixedsys Regular"}));
		fontStyleSelector.setBounds(188, 450, 145, 22);
		setFontStyleIndex();
		add(fontStyleSelector);
		previewMarquee.start();
		
		//// Event Handlers ////
		
		// Update text in database
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateMarqueeText(editAnnouncementText.getText(), fontStyleSelector.getSelectedItem().toString(), fontTypeSelector.getSelectedItem().toString());
				getMarqueeConfig();
				repaintMarquee(defaultText,fontStyle, fontTypeValue);
				setFontStyleIndex();
				fontTypeSelector.setSelectedIndex(fontTypeValue);
				JOptionPane.showMessageDialog(null, "Announcement has been successfully updated!");
				
				MainFrame.getInstance().setScrollText();
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
				repaintMarquee(editAnnouncementText.getText(), fontStyleSelector.getSelectedItem().toString(), fontTypeSelector.getSelectedIndex());
				
				// Reload
				panel_1.repaint();
				panel_1.revalidate();
				previewMarquee.repaint();
				previewMarquee.revalidate();
			}
		});
		
		
		fontTypeSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Remove and Re-add marquee
				repaintMarquee(editAnnouncementText.getText(), fontStyleSelector.getSelectedItem().toString(), fontTypeSelector.getSelectedIndex());
			}
		});
		
		fontStyleSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				repaintMarquee(editAnnouncementText.getText(), fontStyleSelector.getSelectedItem().toString(), fontTypeSelector.getSelectedIndex());
			}
		});
	}
	
	//// Methods ////
	
	private void getMarqueeConfig(){
		try{
		PreparedStatement ps = so.getPreparedStatement("SELECT * FROM et_scrollcfg");
		ResultSet config = ps.executeQuery();
		config.next();
		// Get marquee text
		this.defaultText = config.getString("value");
		config.next();
		// Get font style
		this.fontStyle = config.getString("value");
		config.next();
		// Get font type
		this.fontTypeString = config.getString("value");
			if(fontTypeString.equalsIgnoreCase("plain")){
				fontTypeValue = Font.PLAIN;
			}else if(fontTypeString.equalsIgnoreCase("bold")){
				fontTypeValue = Font.BOLD;
			}else if(fontTypeString.equalsIgnoreCase("italic")){
				fontTypeValue = Font.ITALIC;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	private void updateMarqueeText(String text, String font, String fontType){
		// int fontValue = 0;
		
		try{
		PreparedStatement ps = so.getPreparedStatement("UPDATE et_scrollcfg SET value = CASE cfg WHEN 'text' THEN ? WHEN 'font' THEN ? WHEN 'fonttype' THEN ? END WHERE cfg IN('text', 'font', 'fonttype')");
		ps.setString(1, text);
		ps.setString(2, font);
		ps.setString(3, fontType);
		ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		/*
		if(fontType.equalsIgnoreCase("plain")){
			fontValue= Font.PLAIN;
		}else if(fontType.equalsIgnoreCase("bold")){
			fontValue = Font.BOLD;
		}else if(fontTypeString.equalsIgnoreCase("italic")){
			fontValue = Font.ITALIC;
		}
		*/
	}
	
	private void repaintMarquee(String newText, String fontStyleValue, int fontTypeValue){
		panel_1.remove(previewMarquee);
		previewMarquee = null;
		previewMarquee = new MarqueePanel(newText, 160);
		previewMarquee.setBounds(0, 100, 933, 29);
		previewMarquee.setFont(fontStyleValue, fontTypeValue, 17);
		panel_1.add(previewMarquee);
		previewMarquee.setBackground(new Color(0, 153, 255));
		previewMarquee.start();
	}
	
	private void setFontStyleIndex(){
		for(int i = 0 ; i < fontStyleSelector.getItemCount(); i++){
			if(fontStyleSelector.getItemAt(i).equals(fontStyle)){
				fontStyleSelector.setSelectedIndex(i);
			}
		}
	}
	
	public Font getFont(){
		return new Font(this.fontStyle, this.fontTypeValue, 17);
	}
	
	public String getSex(){
		return this.defaultText;
	}
}
