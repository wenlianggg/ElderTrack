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
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JTextPane SummaryDosagePane;
	private JTable toDoTable;
	private JScrollPane scrollDosagePane ;
	private JButton btnNextEldery;

	private int counter;
	private int numofElder;

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

		SummaryDosagePane = new JTextPane();
		SummaryDosagePane.setBounds(449, 128, 366, 99);
		SummaryDosagePane.setEditable(false);
		add(SummaryDosagePane);

		// Display of information
		SQLObject so = new SQLObject();
		ArrayList<ElderData> DosageList=new ArrayList<ElderData>();
		ElderData data=new ElderData();
		ResultSet rs;
		String dosageTime=MedDosageSearchPanel.getDosageTimeSelect();
		String roomNum=MedDosageSearchPanel.getDosageRoom();
		counter=0;
		numofElder = 0;
		try {	
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1, roomNum);
			stmt.executeQuery();
			rs = stmt.getResultSet();

			while(rs.next()){
				if(dosageTime.equalsIgnoreCase("Morning")){
					if(rs.getBlob("morningdosage") !=null){
						if(rs.getInt("morningtaken")==0){
							// calculate the age
							data=ElderData.getElderInformation(rs);
							DosageList.add(data);
							numofElder++;
						}
					}
				}
				else if (dosageTime.equalsIgnoreCase("afternoon")){
					if(rs.getBlob("afternoondosage") !=null){
						if(rs.getInt("afternoontaken")==0){
							// calculate the age
							data=ElderData.getElderInformation(rs);
							DosageList.add(data);	
							numofElder++;

						}
					}
				}
				else if (dosageTime.equalsIgnoreCase("noon")){
					if(rs.getBlob("noondosage") !=null){
						if(rs.getInt("noontaken")==0){
							// calculate the age
							data=ElderData.getElderInformation(rs);
							DosageList.add(data);	
							numofElder++;
						}
					}
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		DisplayInformation(DosageList, counter);
		// DosageTable
		try {
			toDoTable = new JTable( DosageTableHelper.getElderlyFromQueryDos(dosageTime,DosageList.get(counter).getElderName()));
			scrollDosagePane = new JScrollPane(toDoTable);
			scrollDosagePane.setBounds(121, 257, 694, 120);
			add(scrollDosagePane, BorderLayout.CENTER);
			TableColumn feedingCol=toDoTable.getColumnModel().getColumn(4);
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("Not Feed");
			comboBox.addItem("Feed");
			feedingCol.setCellEditor(new DefaultCellEditor(comboBox));
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

		btnNextEldery = new JButton("Next Eldery");
		btnNextEldery.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNextEldery.setBounds(638, 436, 177, 27);
		add(btnNextEldery);

		JLabel lblElderLeft = new JLabel("Number of Elderly Left: "+numofElder);
		lblElderLeft.setBounds(670, 377, 145, 14);
		add(lblElderLeft);


		btnNextEldery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int feed=0;
				counter++;
				numofElder--;

				if(counter<DosageList.size()){
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to procced?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						for(int k=0;k<toDoTable.getRowCount();k++){
							Object o=toDoTable.getValueAt(k, 4);
							if(o=="Feed"){
								feed++;
							}
						}
						if(feed==toDoTable.getRowCount()){

							DosageObject.UpdateDosageTaken(DosageList.get(counter-1).getElderID(),dosageTime);
							DisplayInformation(DosageList, counter);
							try {
								// make new table
								toDoTable.setModel(DosageTableHelper.getElderlyFromQueryDos(dosageTime,DosageList.get(counter).getElderName()));
								TableColumn feedingCol=toDoTable.getColumnModel().getColumn(4);
								JComboBox<String> comboBox = new JComboBox<String>();
								comboBox.addItem("Not Feed");
								comboBox.addItem("Feed");
								feedingCol.setCellEditor(new DefaultCellEditor(comboBox));

							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(null, "Record Has Been Saved");
							lblElderLeft.setText("Number of Elderly Left: "+numofElder);
						}
						else{
							JOptionPane.showMessageDialog(null, "Please check if you have filled in the required fields");

						}
					}
				}
				else{
					DosageObject.UpdateDosageTaken(DosageList.get(counter-1).getElderID(),dosageTime);
					JOptionPane.showMessageDialog(null, "Dosage has been completed");
					CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
					mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
				}
			}
		});

	}

	public void DisplayInformation(ArrayList<ElderData> DosageList, int counter){
		BedField.setText(String.valueOf(DosageList.get(counter).getElderBed()));
		NameField.setText(DosageList.get(counter).getElderName());
		AgeField.setText(Integer.toString(DosageList.get(counter).getElderAge()));
		GenderField.setText(DosageList.get(counter).getElderGender());
		SummaryDosagePane.setText(DosageList.get(counter).getElderDosageSummary());
	}
}