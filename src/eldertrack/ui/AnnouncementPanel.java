package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class AnnouncementPanel extends JPanel{
	private static final long serialVersionUID = -8742307067990031379L;
	private JLabel AnnouncementManager;
	
	AnnouncementPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		AnnouncementManager = new JLabel("ElderTrack Announcement Settings\r\n");
		AnnouncementManager.setBounds(26, 5, 594, 61);
		AnnouncementManager.setForeground(SystemColor.textHighlight);
		AnnouncementManager.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		add(AnnouncementManager);
		
		JLabel lblNewLabel = new JLabel("Edit announcement text :\r\n\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(27, 483, 206, 22);
		add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(26, 517, 650, 61);
		add(textArea);
		
	}
}
