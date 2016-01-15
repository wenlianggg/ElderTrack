package eldertrack.ui;



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
	private JTextField BedField;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextPane txtSummary;
	private int counter;
	static final SQLObject so = new SQLObject();
	private static final long serialVersionUID = -1155434751690765910L;


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
		lblSummary.setBounds(391, 95, 113, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);

		txtSummary = new JTextPane();
		txtSummary.setEditable(false);
		JScrollPane scrollSummary = new JScrollPane(txtSummary);
		scrollSummary.setBounds(391, 138, 424, 68);
		add(scrollSummary);

		//Checking 

		JLabel lblTemperature = new JLabel("Temperature: ");
		lblTemperature.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTemperature.setBounds(120, 309, 130, 30);
		add(lblTemperature);

		JTextField TempField = new JTextField();
		TempField.setBounds(260, 317, 90, 25);
		TempField.setColumns(10);
		add(TempField);

		JLabel lblBlood= new JLabel("Blood Pressure: ");
		lblBlood.setBounds(120, 350, 142, 30);
		add(lblBlood);
		lblBlood.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JTextField BloodField= new JTextField();
		BloodField.setColumns(10);
		BloodField.setBounds(260, 356, 90, 25);
		add(BloodField);

		JLabel lblHeartRate = new JLabel("Heart Rate: ");
		lblHeartRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeartRate.setBounds(120, 391, 130, 30);
		add(lblHeartRate);

		JTextField HeartField = new JTextField();
		HeartField.setBounds(260, 398, 90, 25);
		HeartField.setColumns(10);
		add(HeartField);

		JLabel lblSugarLv= new JLabel("Sugar Level: ");
		lblSugarLv.setBounds(120, 431, 112, 30);
		lblSugarLv.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		add(lblSugarLv);

		JTextField SugarField=new JTextField();
		SugarField.setBounds(260, 437, 90, 25);
		SugarField.setColumns(10);
		add(SugarField);

		JLabel lblEye= new JLabel("Eye Infection:");
		lblEye.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEye.setBounds(120, 472, 130, 30);
		add(lblEye);

		JComboBox<String> comboEye = new JComboBox<String>();
		comboEye.setBounds(260, 482, 90, 20);
		comboEye.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEye);

		JLabel lblEar= new JLabel("Ear Infection:");
		lblEar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEar.setBounds(120, 513, 130, 30);
		add(lblEar);

		JComboBox<String> comboEar = new JComboBox<String>();
		comboEar.setBounds(260, 523, 90, 20);
		comboEar.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { " ","Yes", "No" }));
		add(comboEar);


		JLabel lblAddition= new JLabel("Additional Notes: ");
		lblAddition.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAddition.setBounds(393, 309, 151, 30);
		add(lblAddition);

		JTextPane textAddition = new JTextPane();
		textAddition.setBounds(393, 350, 422, 149);
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
		counter=0;
		try {
			String output=MedCheckSearchPanel.getCheckSelect();

			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1, output);
			stmt.executeQuery();
			rs = stmt.getResultSet();

			while(rs.next()){
				ElderData data=new ElderData();
				// calculate age
				java.sql.Date birthDate=rs.getDate("dob");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String text = df.format(birthDate);
				String year=text.substring(0, 4);
				String month=text.substring(5,7);
				String day=text.substring(8,10);

				data.setElderBed(rs.getInt("bed"));
				data.setElderID(rs.getInt("id"));
				data.setElderName(rs.getString("name"));
				data.setElderAge(ElderData.getAge(year,month,day));
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
		btnSaveQuit.setBounds(391, 512, 150, 35);
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
					CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
					mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
				}


			}
		});

		JButton btnNextElder = new JButton("Next Elderly");
		btnNextElder.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNextElder.setBounds(665, 512, 150, 35);
		add(btnNextElder);


		btnNextElder.addActionListener(new ActionListener() {

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




						CheckUpObject.StoreCheckUp(NameField.getText(),DosageList.get(counter).getElderID(),reportDate,checkElder);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					try{
						String comments=textAddition.getText();
						CheckUpObject.StoreComments(DosageList.get(counter).getElderID(),comments);
					}
					catch (SQLException e1) {

						e1.printStackTrace();
					}

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

		BedField.setText(Integer.toString(DosageList.get(counter).getElderBed()));
		NameField.setText(DosageList.get(counter).getElderName());
		AgeField.setText(Integer.toString(DosageList.get(counter).getElderAge()));
		GenderField.setText(DosageList.get(counter).getElderGender());
		txtSummary.setText(commentsList.get(counter));
	}
}

