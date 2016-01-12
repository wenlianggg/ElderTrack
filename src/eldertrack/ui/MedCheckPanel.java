package eldertrack.ui;



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
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import eldertrack.db.SQLObject;
import eldertrack.medical.*;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class MedCheckPanel extends JPanel {
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextPane txtSummary;
	static final SQLObject so = new SQLObject();
	private static final long serialVersionUID = -1155434751690765910L;

	public MedCheckPanel(){

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

		NameField = new JTextField();
		NameField.setBounds(201, 100, 145, 30);
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);




		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setForeground(new Color(0, 128, 128));
		lblAge.setBounds(120, 133, 70, 30);
		add(lblAge);

		AgeField= new JTextField();
		AgeField.setBounds(201, 138, 145, 30);
		AgeField.setEditable(false);
		add(AgeField);
		AgeField.setColumns(10);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setForeground(new Color(0, 128, 128));
		lblGender.setBounds(120, 171, 70, 30);
		add(lblGender);

		GenderField= new JTextField();
		GenderField.setColumns(10);
		GenderField.setBounds(201, 176, 145, 30);
		GenderField.setEditable(false);
		add(GenderField);

		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(391, 95, 113, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);

		txtSummary = new JTextPane();
		JScrollPane scrollSummary = new JScrollPane(txtSummary);
		scrollSummary.setBounds(391, 138, 424, 68);
		add(scrollSummary);

		//Checking 

		JLabel lblTemperature = new JLabel("Temperature: ");
		lblTemperature.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTemperature.setBounds(120, 238, 130, 30);
		add(lblTemperature);

		JTextField TempField = new JTextField();
		TempField.setBounds(260, 246, 90, 25);
		TempField.setColumns(10);
		add(TempField);

		JLabel lblBlood= new JLabel("Blood Pressure: ");
		lblBlood.setBounds(120, 279, 142, 30);
		add(lblBlood);
		lblBlood.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JTextField BloodField= new JTextField();
		BloodField.setColumns(10);
		BloodField.setBounds(260, 285, 90, 25);
		add(BloodField);

		JLabel lblHeartRate = new JLabel("Heart Rate: ");
		lblHeartRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeartRate.setBounds(120, 320, 130, 30);
		add(lblHeartRate);

		JTextField HeartField = new JTextField();
		HeartField.setBounds(260, 327, 90, 25);
		HeartField.setColumns(10);
		add(HeartField);

		JLabel lblSugarLv= new JLabel("Sugar Level: ");
		lblSugarLv.setBounds(120, 360, 112, 30);
		lblSugarLv.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		add(lblSugarLv);

		JTextField SugarField=new JTextField();
		SugarField.setBounds(260, 366, 90, 25);
		SugarField.setColumns(10);
		add(SugarField);

		JLabel lblEye= new JLabel("Eye Infection:");
		lblEye.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEye.setBounds(120, 401, 130, 30);
		add(lblEye);

		JComboBox<String> comboEye = new JComboBox<String>();
		comboEye.setBounds(260, 411, 90, 20);
		comboEye.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEye);

		JLabel lblEar= new JLabel("Ear Infection:");
		lblEar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEar.setBounds(120, 442, 130, 30);
		add(lblEar);

		JComboBox<String> comboEar = new JComboBox<String>();
		comboEar.setBounds(260, 452, 90, 20);
		comboEar.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEar);


		JLabel lblAddition= new JLabel("Additional Notes: ");
		lblAddition.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAddition.setBounds(393, 238, 151, 30);
		add(lblAddition);

		JTextPane textAddition = new JTextPane();
		textAddition.setBounds(393, 279, 422, 149);
		add(textAddition);

		//Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String reportDate=dateFormat.format(date);
		//Date

		//DataBase
		SQLObject so = new SQLObject();
		ArrayList<ElderData> DosageList=new ArrayList<ElderData>();
		ArrayList<String> commentsList=new ArrayList<String>();
		ResultSet rs;
		int counter=0;
		try {
			rs = so.getResultSet("SELECT * FROM et_elderly");
			while(rs.next()){
				ElderData data=new ElderData();
				data.setElderID(rs.getString("elderid"));
				data.setElderName(rs.getString("name"));
				data.setElderAge(10);
				data.setElderGender(rs.getString("gender"));
				DosageList.add(data);
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		try {
			rs = so.getResultSet("SELECT elderlynotes FROM et_elderly");
			while(rs.next()){
				commentsList.add(rs.getString("elderlynotes"));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		DisplayInformation(DosageList,commentsList, counter);

		JButton btnSaveQuit = new JButton("Save And Quit");
		btnSaveQuit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnSaveQuit.setBounds(391, 441, 150, 35);
		add(btnSaveQuit);

		btnSaveQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(TempField.getText().equals("") ||  BloodField.getText().equals("") || 
						HeartField.getText().equals("") || SugarField.getText().equals("")|| 
						comboEye.getSelectedItem().toString().equals(" ") || comboEar.getSelectedItem().toString().equals(" ")
						){
					JOptionPane.showMessageDialog(null, "Please Check Again");
				}
				else{
					CheckUpObject checkElder=new CheckUpObject();
					checkElder.setElderTemp(Double.parseDouble(TempField.getText()));
					checkElder.setElderBlood(Integer.parseInt(BloodField.getText()));
					checkElder.setElderHeart(Integer.parseInt(HeartField.getText()));
					if(comboEye.getSelectedItem().toString().equals("Yes")){
						checkElder.setElderEye(true);
					}
					if(comboEar.getSelectedItem().toString().equals("Yes")){
						checkElder.setElderEar(true);
					}
					checkElder.setElderDate(reportDate);
					checkElder.view();
				}


			}
		});

		JButton btnNextElder = new JButton("Next Elderly");
		btnNextElder.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNextElder.setBounds(665, 441, 150, 35);
		add(btnNextElder);
		btnNextElder.addActionListener(new ActionListener() {
			int counter=0;
			int id=1;
			public void actionPerformed(ActionEvent e) {

				if(TempField.getText().equals("") ||  BloodField.getText().equals("") || 
						HeartField.getText().equals("") || SugarField.getText().equals("")|| 
						comboEye.getSelectedItem().toString().equals(" ") || comboEar.getSelectedItem().toString().equals(" ")
						){
					JOptionPane.showMessageDialog(null, "Please Check Again");
				}
				else{
					try {
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


						String name=NameField.getText();
						String elderid=DosageList.get(counter).getElderID();

						StoreCheckUp(name,elderid,reportDate, id,checkElder);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					try{
						String comments=textAddition.getText();

						StoreComments(id,comments);
					}
					catch (SQLException e1) {

						e1.printStackTrace();
					}
					id++;
					counter++;
					DisplayInformation(DosageList,commentsList,counter);
					JOptionPane.showMessageDialog(null, "Check up is successful");
					TempField.setText(null);
					BloodField.setText(null);
					HeartField.setText(null);
					SugarField.setText(null);
					comboEye.setSelectedItem(null);
					comboEar.setSelectedItem(null);
				}


			}
		});


	}
	public void DisplayInformation(ArrayList<ElderData> DosageList,ArrayList<String> commentsList, int counter){
		NameField.setText(DosageList.get(counter).getElderName());
		AgeField.setText("10");
		GenderField.setText(DosageList.get(counter).getElderGender());
		txtSummary.setText(commentsList.get(counter));
	}

	public static  void  StoreCheckUp(String name,String elderID, String elderDate, int id,CheckUpObject checkup) throws SQLException{
		PreparedStatement statement = so.getPreparedStatementWithKey("insert into et_elderly_checkup(id,elderid,name,date)"+"values(?,?,?,?)");
		statement.setInt(1, id);
		statement.setString(2, elderID);
		statement.setString(3,name);
		statement.setString(4, elderDate);
		statement.executeUpdate();

		PreparedStatement statementBlob = so.getPreparedStatementWithKey("UPDATE et_elderly_checkup SET checkup = ? WHERE id = ?");

		statementBlob.setObject(1, checkup);
		statementBlob.setInt(2, id);
		statementBlob.executeUpdate();
	}
	public static void StoreComments(int id,String comments) throws SQLException{
		PreparedStatement statement = so.getPreparedStatementWithKey("update et_elderly set elderlynotes=? where id=?");
		statement.setString(1, comments);
		statement.setInt(2, id);
		statement.executeUpdate();
	}
}

