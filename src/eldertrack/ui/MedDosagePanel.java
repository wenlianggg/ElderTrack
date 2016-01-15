package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;

import javax.swing.UIManager;
import javax.swing.JTextPane;

import eldertrack.db.SQLObject;
import eldertrack.medical.*;
import javax.swing.SwingConstants;

public class MedDosagePanel extends JPanel {

	private static final long serialVersionUID = 1726776874636177464L;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextField BedField;
	private JTable toDoTable;
	private JScrollPane scrollPane ;
	private int counter;
	public MedDosagePanel() {


		setLayout(null);

		JLabel lblBed = new JLabel("BED:");
		lblBed.setForeground(new Color(0, 128, 128));
		lblBed.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblBed.setBounds(121, 85, 70, 30);
		add(lblBed);
		
		BedField = new JTextField();
		BedField.setText((String) null);
		BedField.setHorizontalAlignment(SwingConstants.CENTER);
		BedField.setEditable(false);
		BedField.setColumns(10);
		BedField.setBounds(221, 85, 145, 30);
		add(BedField);
		
		JLabel lblNewLabel = new JLabel("Dosage Tracker");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 213, 41);
		add(lblNewLabel);

		// Name 
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setForeground(new Color(0, 128, 128));
		lblName.setBounds(121, 121, 70, 30);
		add(lblName);

		NameField = new JTextField();
		NameField.setHorizontalAlignment(SwingConstants.CENTER);
		NameField.setBounds(221, 126, 145, 30);
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);

		// Age
		JLabel lblAge = new JLabel("AGE:");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setForeground(new Color(0, 128, 128));
		lblAge.setBounds(121, 159, 70, 30);
		add(lblAge);

		AgeField = new JTextField();
		AgeField.setHorizontalAlignment(SwingConstants.CENTER);
		AgeField.setBounds(221, 164, 145, 30);
		AgeField.setEditable(false);
		add(AgeField);
		AgeField.setColumns(10);

		// Gender
		JLabel lblGender = new JLabel("GENDER:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setForeground(new Color(0, 128, 128));
		lblGender.setBounds(121, 197, 94, 30);
		add(lblGender);

		GenderField= new JTextField();
		GenderField.setHorizontalAlignment(SwingConstants.CENTER);
		GenderField.setColumns(10);
		GenderField.setBounds(221, 202, 145, 30);
		GenderField.setEditable(false);
		add(GenderField);

		// Summary
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(449, 85, 366, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(449, 128, 366, 99);
		textPane.setEditable(false);
		add(textPane);

		// Display of information
		SQLObject so = new SQLObject();
		ArrayList<ElderData> DosageList=new ArrayList<ElderData>();
		ResultSet rs;
		counter=0;
		try {
			
			String output=MedDosageSearchPanel.getDosageSelect();
			
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1, output);
			stmt.executeQuery();
			rs = stmt.getResultSet();
			
			while(rs.next()){
				ElderData data=new ElderData();
				
				// calculate the age
				java.sql.Date reportDate=rs.getDate("dob");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String text = df.format(reportDate);
				String year=text.substring(0, 4);
				String month=text.substring(5,7);
				String day=text.substring(8,10);
				
				// setting the nessary information
				data.setElderBed(rs.getInt("bed"));
				data.setElderName(rs.getString("name"));
				data.setElderAge(ElderData.getAge(year,month,day));
				data.setElderGender(rs.getString("gender"));
				DosageList.add(data);
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		DisplayInformation(DosageList, counter);


		// DosageTable
		
	
		try {
			toDoTable = new JTable( DosageTableHelper.getElderlyFromQueryDos(DosageList.get(counter).getElderName()));
			scrollPane = new JScrollPane(toDoTable);
			scrollPane.setBounds(121, 257, 694, 120);
			add(scrollPane, BorderLayout.CENTER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    
	    
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		btnSaveAndQuit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSaveAndQuit.setBounds(120, 436, 177, 27);
		add(btnSaveAndQuit);
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Save and Quit","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){

					

					CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
					mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
				}

			}

		});

		JButton btnNextEldery = new JButton("Next Eldery");
		btnNextEldery.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNextEldery.setBounds(638, 436, 177, 27);
		add(btnNextEldery);
		
		JLabel lblElderLeft = new JLabel("Number of Elderly Left:");
		lblElderLeft.setBounds(670, 377, 145, 14);
		add(lblElderLeft);
		
		
		btnNextEldery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to procced?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					counter++;
					DisplayInformation(DosageList, counter);
					try {
						
						toDoTable.setModel(DosageTableHelper.getElderlyFromQueryDos(DosageList.get(counter).getElderName()));
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Record Has Been Saved");
					

				}
			}

		});
	}
	
	public void DisplayInformation(ArrayList<ElderData> DosageList, int counter){
		
		
		BedField.setText(String.valueOf(DosageList.get(counter).getElderBed()));
		NameField.setText(DosageList.get(counter).getElderName());
		AgeField.setText("10");
		GenderField.setText(DosageList.get(counter).getElderGender());
	}
	
}
