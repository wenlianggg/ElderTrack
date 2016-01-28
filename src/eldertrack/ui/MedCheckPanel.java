package eldertrack.ui;



import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;

import eldertrack.db.SQLObject;
import eldertrack.medical.*;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class MedCheckPanel extends JPanel {

	static final SQLObject so = new SQLObject();
	private static final long serialVersionUID = -1155434751690765910L;

	private JTextField BedField;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextPane txtSummary;
	private int counter;
	private int numofElder;


	public MedCheckPanel(){

		setLayout(null);


		JLabel lblNewLabel = new JLabel("Check Up");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 210, 41);
		add(lblNewLabel);

		JLabel lblBed = new JLabel("BED:");
		lblBed.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBed.setForeground(new Color(0, 128, 128));
		lblBed.setBounds(120, 98, 70, 30);
		add(lblBed);

		BedField = new JTextField();
		BedField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		BedField.setBounds(200, 100, 181, 30);
		BedField.setEditable(false);
		add(BedField);
		BedField.setColumns(10);

		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setForeground(new Color(0, 128, 128));
		lblName.setBounds(120, 139, 70, 30);
		add(lblName);

		NameField = new JTextField();
		NameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		NameField.setBounds(200, 139, 181, 30);
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);

		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setForeground(new Color(0, 128, 128));
		lblAge.setBounds(120, 177, 70, 30);
		add(lblAge);

		AgeField= new JTextField();
		AgeField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		AgeField.setBounds(200, 177, 181, 30);
		AgeField.setEditable(false);
		add(AgeField);
		AgeField.setColumns(10);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setForeground(new Color(0, 128, 128));
		lblGender.setBounds(120, 215, 70, 30);
		add(lblGender);

		GenderField= new JTextField();
		GenderField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GenderField.setColumns(10);
		GenderField.setBounds(200, 215, 181, 30);
		GenderField.setEditable(false);
		add(GenderField);

		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(422, 94, 113, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);

		txtSummary = new JTextPane();
		txtSummary.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSummary.setEditable(false);
		JScrollPane scrollSummary = new JScrollPane(txtSummary);
		scrollSummary.setBounds(422, 137, 424, 77);
		add(scrollSummary);

		//Checking 

		NumberFormat intformat = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(intformat);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setCommitsOnValidEdit(true);

		NumberFormat doubleformat = NumberFormat.getNumberInstance();
		doubleformat.setGroupingUsed(false);
		doubleformat.setGroupingUsed(true);// or add the group chars to the filter
		doubleformat.setMaximumIntegerDigits(10);
		doubleformat.setMaximumFractionDigits(2);
		doubleformat.setMinimumFractionDigits(1);
		doubleformat.setRoundingMode(RoundingMode.HALF_UP);

		JLabel lblTemperature = new JLabel("Temperature: ");
		lblTemperature.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTemperature.setBounds(120, 284, 130, 30);
		add(lblTemperature);

		JFormattedTextField TempField = new JFormattedTextField(doubleformat);
		TempField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		TempField.setBounds(260, 292, 70, 25);
		TempField.setColumns(10);
		add(TempField);

		JLabel lblTempUnit = new JLabel("\u00b0C");
		lblTempUnit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTempUnit.setBounds(331, 284, 46, 30);
		add(lblTempUnit);

		JLabel lblBlood= new JLabel("Blood Pressure: ");
		lblBlood.setBounds(120, 325, 142, 30);
		add(lblBlood);
		lblBlood.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JFormattedTextField  BloodField= new JFormattedTextField (doubleformat);
		BloodField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		BloodField.setColumns(10);
		BloodField.setBounds(260, 331, 70, 25);
		add(BloodField);

		JLabel lblBloodUnit = new JLabel("mmHg");
		lblBloodUnit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBloodUnit.setBounds(331, 325, 60, 30);
		add(lblBloodUnit);

		JLabel lblHeartRate = new JLabel("Heart Rate: ");
		lblHeartRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeartRate.setBounds(120, 366, 130, 30);
		add(lblHeartRate);

		JFormattedTextField HeartField = new JFormattedTextField(formatter);
		HeartField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		HeartField.setBounds(260, 373, 70, 25);
		HeartField.setColumns(10);
		add(HeartField);

		JLabel lblHeartUnit = new JLabel("bpm");
		lblHeartUnit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeartUnit.setBounds(331, 366, 60, 30);
		add(lblHeartUnit);

		JLabel lblSugarLv= new JLabel("Sugar Level: ");
		lblSugarLv.setBounds(120, 406, 112, 30);
		lblSugarLv.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		add(lblSugarLv);

		JFormattedTextField SugarField=new JFormattedTextField(doubleformat);
		SugarField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		SugarField.setBounds(260, 412, 70, 25);
		SugarField.setColumns(10);
		add(SugarField);

		JLabel lblSugarUnit = new JLabel("mmol/L");
		lblSugarUnit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSugarUnit.setBounds(331, 406, 70, 30);
		add(lblSugarUnit);

		JLabel lblEye= new JLabel("Eye Infection:");
		lblEye.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEye.setBounds(120, 447, 130, 30);
		add(lblEye);

		JComboBox<String> comboEye = new JComboBox<String>();
		comboEye.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		comboEye.setBounds(260, 457, 121, 20);
		comboEye.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEye);

		JLabel lblEar= new JLabel("Ear Infection:");
		lblEar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEar.setBounds(120, 488, 130, 30);
		add(lblEar);

		JComboBox<String> comboEar = new JComboBox<String>();
		comboEar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		comboEar.setBounds(260, 498, 121, 20);
		comboEar.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEar);


		JLabel lblAddition= new JLabel("Additional Notes: ");
		lblAddition.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAddition.setBounds(424, 283, 151, 30);
		add(lblAddition);

		JTextPane textAddition = new JTextPane();
		textAddition.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textAddition.setBounds(424, 324, 422, 184);
		add(textAddition);

		//Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String reportDate=dateFormat.format(date);
		//Date

		//DataBase
		SQLObject so = new SQLObject();
		ArrayList<ElderData> CheckList=new ArrayList<ElderData>();
		ArrayList<String> commentsList=new ArrayList<String>();
		ElderData data=new ElderData();
		String output=MedCheckSearchPanel.getCheckSelect();
		String checkupTime=MedCheckSearchPanel.getCheckTimeSelect();
		ResultSet rs;
		counter=0;
		numofElder=0;
		try {


			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1, output);
			stmt.executeQuery();
			rs = stmt.getResultSet();

			while(rs.next()){

				if(checkupTime.equalsIgnoreCase("morning")){
					if(rs.getInt("morningcheck")==0){
						data=ElderData.getElderInformation(rs);
						CheckList.add(data);
						numofElder++;
					}

				}
				else if(checkupTime.equalsIgnoreCase("afternoon")){
					if(rs.getInt("afternooncheck")==0){
						data=ElderData.getElderInformation(rs);
						CheckList.add(data);
						numofElder++;
					}
				}
				else if(checkupTime.equalsIgnoreCase("noon")){
					if(rs.getInt("nooncheck")==0){
						data=ElderData.getElderInformation(rs);
						CheckList.add(data);
						numofElder++;
					}
				}

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		try {
			rs = so.getResultSet("SELECT checkupsummary FROM et_elderly");
			while(rs.next()){
				commentsList.add(rs.getString("checkupsummary"));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		DisplayInformation(CheckList,commentsList, counter);

		JButton btnSaveQuit = new JButton("Save And Quit");
		btnSaveQuit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnSaveQuit.setBounds(422, 519, 150, 35);
		add(btnSaveQuit);

		btnSaveQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				boolean valid = CheckUpObject.validValues(TempField.getText(), BloodField.getText(), HeartField.getText(), SugarField.getText());
				if(valid == false){
					JOptionPane.showMessageDialog(null, "Either fields are empty or values are invalid. Please check your entries again!");
				}
				else{
					CheckUpObject checkElder=new CheckUpObject();	
					checkElder.setElderTemp(Double.parseDouble(TempField.getText()));
					checkElder.setElderBlood(Integer.parseInt(BloodField.getText()));
					checkElder.setElderHeart(Integer.parseInt(HeartField.getText()));
					checkElder.setElderSugar(Integer.parseInt(SugarField.getText()));
					if(comboEye.getSelectedItem().toString().equals("Yes")){
						checkElder.setElderEye(true);
					}
					else{
						checkElder.setElderEye(false);
					}
					if(comboEar.getSelectedItem().toString().equals("Yes")){
						checkElder.setElderEar(true);
					}
					else{
						checkElder.setElderEar(false);
					}

					CheckUpObject.StoreCheckUp(NameField.getText(),CheckList.get(counter).getElderID(),reportDate,checkElder,MedCheckSearchPanel.getCheckTimeSelect());
					CheckUpObject.UpdateCheckUpTaken(CheckList.get(counter).getElderID(),MedCheckSearchPanel.getCheckTimeSelect(),so);
					CheckUpObject.StoreComments(CheckList.get(counter).getElderID(),textAddition.getText());
					JOptionPane.showMessageDialog(null, "Check up is successful");



					CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
					mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);

				}

			}
		});

		JButton btnNextElder = new JButton("Next Elderly");
		btnNextElder.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNextElder.setBounds(696, 519, 150, 35);
		add(btnNextElder);

		JLabel lblElderLeft = new JLabel("Number of Elderly left:   "+numofElder);
		lblElderLeft.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblElderLeft.setBounds(663, 222, 181, 20);
		add(lblElderLeft);


		btnNextElder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numofElder--;
				// Validation of values
				boolean valid = CheckUpObject.validValues(TempField.getText(), BloodField.getText(), HeartField.getText(), SugarField.getText());
				if(valid == false){
					JOptionPane.showMessageDialog(null, "Either fields are empty or values are invalid. Please check your entries again!");
				}
				else{
					if(counter+1!=CheckList.size()){
						CheckUpObject checkElder=new CheckUpObject();
						checkElder.setElderTemp(Double.parseDouble(TempField.getText()));
						checkElder.setElderBlood(Double.parseDouble(BloodField.getText()));
						checkElder.setElderHeart(Integer.parseInt(HeartField.getText()));
						checkElder.setElderSugar(Double.parseDouble(SugarField.getText()));
						if(comboEye.getSelectedItem().toString().equals("Yes")){
							checkElder.setElderEye(true);
						}
						else{
							checkElder.setElderEye(false);
						}
						if(comboEar.getSelectedItem().toString().equals("Yes")){
							checkElder.setElderEar(true);
						}
						else{
							checkElder.setElderEar(false);
						}
						CheckUpObject.StoreCheckUp(NameField.getText(),CheckList.get(counter).getElderID(),reportDate,checkElder,MedCheckSearchPanel.getCheckTimeSelect());
						CheckUpObject.UpdateCheckUpTaken(CheckList.get(counter).getElderID(),MedCheckSearchPanel.getCheckTimeSelect(),so);
						CheckUpObject.StoreComments(CheckList.get(counter).getElderID(),textAddition.getText());
						counter++;
						DisplayInformation(CheckList,commentsList,counter);
						lblElderLeft.setText("Number of Elderly left:"+numofElder);
						JOptionPane.showMessageDialog(null, "Check up is successful");
						TempField.setText(null);
						BloodField.setText(null);
						HeartField.setText(null);
						SugarField.setText(null);
						comboEye.setSelectedItem(null);
						comboEar.setSelectedItem(null);
					}
					else{
						CheckUpObject checkElder=new CheckUpObject();
						checkElder.setElderTemp(Double.parseDouble(TempField.getText()));
						checkElder.setElderBlood(Integer.parseInt(BloodField.getText()));
						checkElder.setElderHeart(Integer.parseInt(HeartField.getText()));
						checkElder.setElderSugar(Integer.parseInt(SugarField.getText()));
						if(comboEye.getSelectedItem().toString().equals("Yes")){
							checkElder.setElderEye(true);
						}
						else{
							checkElder.setElderEye(false);
						}
						if(comboEar.getSelectedItem().toString().equals("Yes")){
							checkElder.setElderEar(true);
						}
						else{
							checkElder.setElderEar(false);
						}
						CheckUpObject.StoreCheckUp(NameField.getText(),CheckList.get(counter).getElderID(),reportDate,checkElder,MedCheckSearchPanel.getCheckTimeSelect());
						CheckUpObject.UpdateCheckUpTaken(CheckList.get(counter).getElderID(),MedCheckSearchPanel.getCheckTimeSelect(),so);
						CheckUpObject.StoreComments(CheckList.get(counter).getElderID(),textAddition.getText());

						JOptionPane.showMessageDialog(null, "Check up is successful");
						TempField.setText(null);
						BloodField.setText(null);
						HeartField.setText(null);
						SugarField.setText(null);
						comboEye.setSelectedItem(null);
						comboEar.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Check Up has been completed");	
						CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
						mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
					}
				}


			}
		});


	}

	public void DisplayInformation(ArrayList<ElderData> DosageList,ArrayList<String> commentsList, int counter){
		BedField.setText(Integer.toString(DosageList.get(counter).getElderBed()));
		NameField.setText(DosageList.get(counter).getElderName());
		AgeField.setText(Integer.toString(DosageList.get(counter).getElderAge()));
		GenderField.setText(DosageList.get(counter).getElderGender());
		txtSummary.setText(commentsList.get(counter));
	}
}