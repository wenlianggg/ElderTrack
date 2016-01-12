package eldertrack.ui;


import javax.swing.JPanel;
import java.awt.CardLayout;



public class MedPanel extends JPanel {
	private static final long serialVersionUID = 5062666526948201245L;
	
	static final String MMAINPANEL="Main Medical Panel";
	static final String MDOSSEARCHPANEL="Medical Dosage Search Panel";
	static final String MDOSPANEL="Medical Dosage Panel";
	static final String MCHECKSEARCHPANEL="Medical Check Up Search Panel";
	static final String MCHECKPANEL="Medical Check Up Panel";
	static JPanel MedCardPanel;
	CardLayout mainCards;
	
	MedPanel(){
		JPanel MedMainPanel = new MedMainPanel();
		JPanel MedDosageSearchPanel = new MedDosageSearchPanel();
		JPanel MedDosagePanel = new MedDosagePanel();
		JPanel MedCheckSearchPanel = new MedCheckSearchPanel();
		JPanel MedCheckPanel=new MedCheckPanel();
		
		
		MedCardPanel = new JPanel(new CardLayout());
		MedCardPanel.add(MedMainPanel, MMAINPANEL);
		MedCardPanel.add(MedDosageSearchPanel, MDOSSEARCHPANEL);
		MedCardPanel.add(MedDosagePanel, MDOSPANEL);
		MedCardPanel.add(MedCheckSearchPanel,MCHECKSEARCHPANEL);
		MedCardPanel.add(MedCheckPanel,MCHECKPANEL);
		
		((CardLayout)MedCardPanel.getLayout()).show(MedCardPanel, "Main Medical Panel");
		
		setLayout(null);
		MedCardPanel.setBounds(0, 0, 994, 671);
		add(MedCardPanel);
		
		
	}
}
