package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.UIManager;

import eldertrack.db.SQLObject;
import eldertrack.medical.DosageObject;
import eldertrack.medical.ElderData;

import javax.swing.JTextPane;
// bugs

// have not done the time reset yet, will do on ltr date

public class MedDosageSearchPanel extends JPanel {

	private static final long serialVersionUID = -2593071831861718177L;
	private static 	SQLObject so = new SQLObject();
	private JComboBox<String> roomcombobox;
	private JComboBox<String> timeCombobox;
	private JTextPane txtpnOverview;
	public static String roomselected;
	public static String timeselected;
	private ElderData summaryDetails;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MedDosageSearchPanel() {

		setBounds(5, 5, 975, 510);
		setLayout(null);

		DosageObject.ResetDosage(so);

		JLabel lblDosageTrackingSystem = new JLabel("Dosage Tracking System");
		lblDosageTrackingSystem.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
		lblDosageTrackingSystem.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblDosageTrackingSystem.setBounds(25, 25, 349, 41);
		add(lblDosageTrackingSystem);


		JLabel lblRoomNumber = new JLabel("Room Number: ");
		lblRoomNumber.setBounds(90, 117, 151, 41);
		lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRoomNumber.setForeground(new Color(0, 128, 128));
		add(lblRoomNumber);

		//processing for room numbers

		List<String> roomNumberList=new ArrayList<String>();
		try {
			ResultSet rs;
			PreparedStatement statement2 = so.getPreparedStatementWithKey("SELECT distinct room FROM et_elderly");
			rs = statement2.executeQuery();
			roomNumberList.add(" ");
			while(rs.next()){
				roomNumberList.add(Integer.toString(rs.getInt("room")));
			}

		} catch (SQLException e2) {

			e2.printStackTrace();
		}
		roomcombobox = new JComboBox<String>();
		roomcombobox.setBackground(UIManager.getColor("TextField.highlight"));
		roomcombobox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		roomcombobox.setBounds(277, 124, 125, 31);
		roomcombobox.setModel(new DefaultComboBoxModel(roomNumberList.toArray()));
		add(roomcombobox);

		roomcombobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				summaryDetails=ElderData.UpdateOverview(roomcombobox.getSelectedItem().toString(),timeCombobox.getSelectedItem().toString(),so);
				if(summaryDetails!=null){
					txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: "+summaryDetails.getElderRoomNumber() +"\r\nTotal number of elderly: "+summaryDetails.getElderNum() +"\r\nTotal Male elderly: " +summaryDetails.getElderNumMale() 
					+"\r\nTotal Female elderly:" +summaryDetails.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+summaryDetails.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+summaryDetails.getElderNumDosageNotNeeded());
				}
				else{
					txtpnOverview.setText("Dosage Time: \r\nRoom Number: \r\nTotal number of elderly: \r\nTotal Male elderly: \r\nTotal Female elderly: \r\nTotal number of elderly that needs dosage: \r\nTotal number of elderly that don't need dosage: ");
				}
			}
		});

		JLabel lblTime = new JLabel("Time: ");
		lblTime.setBounds(90, 177, 80, 41);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTime.setForeground(new Color(0, 128, 128));
		add(lblTime);

		timeCombobox = new JComboBox<String>();
		timeCombobox.setBackground(UIManager.getColor("TextField.highlight"));
		timeCombobox.setBounds(277, 183, 125, 31);
		timeCombobox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		timeCombobox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"Morning","Afternoon", "Noon" }));
		add(timeCombobox);
		timeCombobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				summaryDetails=ElderData.UpdateOverview(roomcombobox.getSelectedItem().toString(),timeCombobox.getSelectedItem().toString(),so);
				txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: "+summaryDetails.getElderRoomNumber() +"\r\nTotal number of elderly: "+summaryDetails.getElderNum() +"\r\nTotal Male elderly: " +summaryDetails.getElderNumMale() 
				+"\r\nTotal Female elderly:" +summaryDetails.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+summaryDetails.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+summaryDetails.getElderNumDosageNotNeeded());
			}
		});

		JLabel lblOverview = new JLabel("Overview:");
		lblOverview.setForeground(new Color(0, 128, 128));
		lblOverview.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOverview.setBounds(551, 117, 151, 41);
		add(lblOverview);

		txtpnOverview = new JTextPane();
		txtpnOverview.setText("Dosage Time: \r\nRoom Number: \r\nTotal number of elderly: \r\nTotal Male elderly: \r\nTotal Female elderly: \r\nTotal number of elderly that needs dosage: \r\nTotal number of elderly that don't need dosage: ");
		txtpnOverview.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnOverview.setEditable(false);
		txtpnOverview.setBounds(551, 155, 339, 226);
		add(txtpnOverview);

		JButton btnGetDosage = new JButton("Get Dosage");
		btnGetDosage.setBounds(90, 256, 312, 31);
		btnGetDosage.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(btnGetDosage);


		// testing for choosing room options

		btnGetDosage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DosageObject.checkDosageNeeded(summaryDetails)==true){
					setDosageRoom(roomcombobox.getSelectedItem().toString());
					setDosageTimeSelect(timeCombobox.getSelectedItem().toString());
					if(DosageObject.checkDosageValid(roomcombobox.getSelectedItem().toString(),timeCombobox.getSelectedItem().toString(),so)==false){
						JOptionPane.showMessageDialog(null, "Dosage has already been done!");
					}
					else{
						JPanel gottenDosage=new MedDosagePanel();
						MedPanel.MedCardPanel.add(gottenDosage,MedPanel.MDOSPANEL);
						CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
						mainCards.show(MedPanel.MedCardPanel, MedPanel.MDOSPANEL);
					}
				}

			}
		});
	}

	public void setDosageRoom(String selectDosageRoom){
		roomselected=selectDosageRoom;
	}
	public static String getDosageRoom(){
		return roomselected;
	}

	public void setDosageTimeSelect(String selectDosageTime){
		timeselected=selectDosageTime;
	}
	public static String getDosageTimeSelect(){
		return timeselected;
	}
}
