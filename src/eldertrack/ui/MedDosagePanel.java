package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;

import javax.swing.UIManager;
import java.awt.Color;

public class MedDosagePanel extends JPanel {

	private static final long serialVersionUID = 1726776874636177464L;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextField SummaryField;
	private JTable DosageTable;
	
	
	
	public MedDosagePanel() {
	
		
		setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Dosage Tracker");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 213, 41);
		add(lblNewLabel);
		setLayout(null);
		// Name 
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setBounds(120, 95, 70, 30);
		add(lblName);
		
		NameField = new JTextField();
		NameField.setBounds(201, 100, 145, 30);
		add(NameField);
		NameField.setColumns(10);
		
		// Age
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setBounds(120, 133, 70, 30);
		add(lblAge);
		
		AgeField = new JTextField();
		AgeField.setBounds(201, 138, 145, 30);
		add(AgeField);
		AgeField.setColumns(10);
		
		// Gender
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setBounds(120, 171, 70, 30);
		add(lblGender);
		
		GenderField= new JTextField();
		GenderField.setColumns(10);
		GenderField.setBounds(201, 176, 145, 30);
		add(GenderField);
		
		// Summary
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(391, 95, 113, 30);
		add(lblSummary);
		
		SummaryField = new JTextField();
		SummaryField.setBounds(391, 138, 424, 68);
		add(SummaryField);
		SummaryField.setColumns(10);
		
		// DosageTable
		JScrollPane scrollPane =  new JScrollPane();
		scrollPane.setBounds(121, 278, 694, 106);
		add(scrollPane,BorderLayout.CENTER);
		
		DosageTable = new JTable();
		scrollPane.setViewportView(DosageTable);
		DosageTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Description", "Prescription", "Dosage"
			}
		));
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		btnSaveAndQuit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSaveAndQuit.setBounds(120, 436, 177, 27);
		add(btnSaveAndQuit);
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    
			    int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Save and Quit","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){

                JPanel getSearchDosage=new MedDosageSearchPanel ();
                getSearchDosage.setVisible(true);
        				
        		CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
        		mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
                        }
              
                    }
			
			});
			
		JButton btnNextEldery = new JButton("Next Eldery");
		btnNextEldery.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNextEldery.setBounds(638, 436, 177, 27);
		add(btnNextEldery);
		btnNextEldery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to procced?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){

                	JPanel getDosage=new MedDosagePanel();
    				getDosage.setVisible(true);

    				CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
    			    mainCards.show(MedPanel.MedCardPanel, MedPanel.MDOSPANEL);
                  }
			}
			
		});
	}
}
