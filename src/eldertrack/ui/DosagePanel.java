package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class DosagePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DosagePanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dosage");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 10, 145, 50);
		add(lblNewLabel);
		
		

	}

}
