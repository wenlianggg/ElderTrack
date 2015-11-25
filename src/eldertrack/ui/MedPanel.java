package eldertrack.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MedPanel extends JPanel {
	JLabel lblMedPanelLbl;
	MedPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblMedPanelLbl = new JLabel("ElderTrack Medication Panel");
		add(lblMedPanelLbl);
	}
}
