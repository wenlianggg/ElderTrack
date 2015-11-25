package eldertrack.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	DietPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		add(lblDietLabel);
	}
}
