package eldertrack.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MgmtPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblEManagementLbl;
	MgmtPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblEManagementLbl = new JLabel("ElderTrack Elderly Management");
		add(lblEManagementLbl);
	}
}
