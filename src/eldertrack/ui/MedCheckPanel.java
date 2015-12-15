package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.JButton;

public class MedCheckPanel extends JPanel {


	private static final long serialVersionUID = -1155434751690765910L;


	public MedCheckPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check Up");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 210, 41);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setForeground(new Color(0, 128, 128));
		lblName.setBounds(120, 95, 70, 30);
		add(lblName);
		
		JTextField NameField = new JTextField();
		NameField.setBounds(201, 100, 145, 30);
		NameField.setText("Roy Tang Qing Long");
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setForeground(new Color(0, 128, 128));
		lblAge.setBounds(120, 133, 70, 30);
		add(lblAge);
		
		JTextField AgeField = new JTextField();
		AgeField.setBounds(201, 138, 145, 30);
		AgeField.setEditable(false);
		AgeField.setText("69");
		add(AgeField);
		AgeField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setForeground(new Color(0, 128, 128));
		lblGender.setBounds(120, 171, 70, 30);
		add(lblGender);
				
		JTextField GenderField = new JTextField();
		GenderField.setColumns(10);
		GenderField.setBounds(201, 176, 145, 30);
		GenderField.setEditable(false);
		GenderField.setText("Male");
		add(GenderField);
			
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(391, 95, 113, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);
				
		JTextPane txtpnHadSomeCough = new JTextPane();
		txtpnHadSomeCough.setBounds(391, 138, 424, 68);
		txtpnHadSomeCough.setEditable(false);
		txtpnHadSomeCough.setText("Had some cough");
		add(txtpnHadSomeCough);
		
		JLabel lblSugarLevel = new JLabel("Sugar Level:");
		lblSugarLevel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSugarLevel.setBounds(120, 234, 86, 25);
		add(lblSugarLevel);
		
		
		JTextField textField = new JTextField();
		textField.setBounds(234, 239, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblBloodPressure = new JLabel("Blood Pressure:");
		lblBloodPressure.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblBloodPressure.setBounds(120, 270, 101, 25);
		add(lblBloodPressure);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(234, 275, 86, 20);
		add(textField_1);
		JLabel lblHeartRate = new JLabel("Heart Rate:");
		lblHeartRate.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblHeartRate.setBounds(120, 306, 86, 25);
		add(lblHeartRate);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(234, 311, 86, 20);
		add(textField_2);
		
		JLabel lblEarInfection = new JLabel("Ear Infection: ");
		lblEarInfection.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEarInfection.setBounds(120, 344, 101, 25);
		add(lblEarInfection);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(234, 349, 86, 20);
		add(textField_3);
		
		JLabel lblGeneralComments = new JLabel("General Comments:");
		lblGeneralComments.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblGeneralComments.setBounds(391, 234, 137, 25);
		add(lblGeneralComments);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(391, 270, 424, 99);
		add(textPane);
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		btnSaveAndQuit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnSaveAndQuit.setBounds(120, 408, 151, 23);
		add(btnSaveAndQuit);
		
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    
			    int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Save and Quit","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                		if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")){
                			
                			JOptionPane.showMessageDialog(null, "Please Check Again");
                		}
                		else{
                			
                			JOptionPane.showMessageDialog(null, "Record Has Been Saved");
                			JPanel getSearchDosage=new MedDosageSearchPanel ();
                            getSearchDosage.setVisible(true);
                     				
                     		CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
                     		mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
                		}
                        }
              
                    }
			
			});
		
		JButton btnNextElderly = new JButton("Next Elderly");
		btnNextElderly.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNextElderly.setBounds(664, 411, 151, 23);
		add(btnNextElderly);
		
		btnNextElderly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    
			    int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                		if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")){
                			
                			JOptionPane.showMessageDialog(null, "Please Check Again");
                		}
                		else{
                			
                			JOptionPane.showMessageDialog(null, "Record Has Been Saved");
                			JPanel getSearchDosage=new MedDosageSearchPanel ();
                            getSearchDosage.setVisible(true);
                     		textField.setText("");	
                     		textField_1.setText("");	
                     		textField_2.setText("");	
                     		textField_3.setText("");
                     		CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
                     		mainCards.show(MedPanel.MedCardPanel, MedPanel.MCHECKPANEL);
                		}
                        }
              
                    }
			
			});
		
		}
	}

