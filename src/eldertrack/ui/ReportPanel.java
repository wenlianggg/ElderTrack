package eldertrack.ui;


import javax.swing.JPanel;

import eldertrack.db.SQLObject;

import java.awt.CardLayout;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
    final static String RMAINPANEL = "Report Main Panel";
	static JPanel CardsPanel;
	
	// Constructor
	ReportPanel() {
		SQLObject so = new SQLObject();
		PreparedStatement reset1 = so.getPreparedStatement("DELETE FROM et_reportTemp");
		PreparedStatement reset2 = so.getPreparedStatement("DELETE FROM et_report");
		PreparedStatement reset3 = so.getPreparedStatement("DELETE FROM et_reportAvr");
		try {
			reset1.execute();
			reset2.execute();
			reset3.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel ReportMainPanel = new ReportMainPanel();

		
		
		CardsPanel = new JPanel(new CardLayout());
		CardsPanel.add(ReportMainPanel, RMAINPANEL);
		((CardLayout)CardsPanel.getLayout()).show(CardsPanel, RMAINPANEL);
		
		setLayout(null);
		CardsPanel.setBounds(0, 0, 994, 671);
		add(CardsPanel);
	}
}