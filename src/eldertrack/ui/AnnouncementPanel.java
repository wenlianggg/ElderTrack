package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JEditorPane;

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
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(258, 485, 106, 22);
		add(editorPane);
		
		JLabel lblNewLabel = new JLabel("Edit announcement text\r\n");
		lblNewLabel.setBounds(90, 485, 143, 22);
		add(lblNewLabel);
		
	}
}
