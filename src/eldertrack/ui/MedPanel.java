package eldertrack.ui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class MedPanel extends JPanel {
	private static final long serialVersionUID = 5062666526948201245L;
	JLabel lblMedPanelLbl;
	//Panel
	JTabbedPane TabbedPanel=new JTabbedPane();
	JPanel MedTab1=new JPanel();
	JPanel MedTab2=new JPanel();
	JLabel MedTab1Lab=new JLabel();
	JLabel MedTab2Lab=new JLabel();
	
	
	MedPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblMedPanelLbl = new JLabel("ElderTrack Medication");
		lblMedPanelLbl.setBounds(5, 5, 392, 54);
		lblMedPanelLbl.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		add(lblMedPanelLbl);    
		
		TabbedPanel.setBounds(10, 85, 975, 502);
		
		
		
		
		MedTab1.setLayout(null);
		MedTab1Lab.setBounds(23, 5, 0, 0);
		MedTab1.add(MedTab1Lab);
		
		MedTab2.setLayout(null);
		MedTab2Lab.setBounds(23, 5, 0, 0);
		MedTab2.add(MedTab2Lab);
		
		TabbedPanel.add("Dosage", MedTab1);
		TabbedPanel.add("Check-Up",MedTab2);
		add(TabbedPanel);
		
		
	}
}
