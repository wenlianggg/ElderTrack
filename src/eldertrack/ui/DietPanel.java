package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	DietPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblSelectElderly = new JLabel("Select Elderly");

		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);

		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectElderly.setBounds(10, 65, 88, 23);
		
		add(lblSelectElderly);
		add(lblDietLabel);

	}
}
