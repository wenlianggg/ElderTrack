package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import eldertrack.db.SQLObject;
import eldertrack.medical.CheckUpObject;
import eldertrack.medical.ElderData;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
//bugs
//have not done the time reset yet, will do on ltr date
public class MedCheckSearchPanel extends JPanel {


	private static final long serialVersionUID = -1155434751690765910L;
	private JComboBox<String> roomComboBox;
	private JComboBox<String> timeComboBox;
	private JTextPane txtpnCheckOverview;

	private static SQLObject so = new SQLObject();
	public static String selected;
	public static String timeselected;
	private ElderData summaryCheckDetails;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MedCheckSearchPanel() {
		setBounds(5, 5, 975, 510);
		setLayout(null);

		

		JLabel lblCheckUpSystem = new JLabel("Check Up System");
		lblCheckUpSystem.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblCheckUpSystem.setBounds(25, 25, 232, 41);
		lblCheckUpSystem.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
		add(lblCheckUpSystem);


		JLabel lblRoomNumber = new JLabel("Room Number: ");
		lblRoomNumber.setBounds(90, 117, 151, 41);
		lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRoomNumber.setForeground(new Color(0, 128, 128));
		add(lblRoomNumber);

		//processing for room numbers

		List<String> roomNumberList=new ArrayList<String>();
		try {
			ResultSet rs;
			PreparedStatement statement2 = so.getPreparedStatementWithKey("SELECT distinct room FROM et_elderly ");
			rs = statement2.executeQuery();
			roomNumberList.add(" ");
			while(rs.next()){
				roomNumberList.add(Integer.toString(rs.getInt("room")));
			}

		} catch (SQLException e2) {

			e2.printStackTrace();
		}

		roomComboBox = new JComboBox<String>();
		roomComboBox.setBackground(UIManager.getColor("TextField.highlight"));
		roomComboBox.setBounds(277, 124, 125, 31);
		roomComboBox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		roomComboBox.setModel(new DefaultComboBoxModel(roomNumberList.toArray()));
		add(roomComboBox);

		roomComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				summaryCheckDetails=ElderData.UpdateOverview(roomComboBox.getSelectedItem().toString(),timeComboBox.getSelectedItem().toString(),so);
				if(summaryCheckDetails!=null){
					txtpnCheckOverview.setText("Check Up Time: "+timeComboBox.getSelectedItem().toString() + "\r\nRoom Number: "+summaryCheckDetails.getElderRoomNumber() 
					+"\r\nTotal number of elderly: "+summaryCheckDetails.getElderNum() +"\r\nTotal Male elderly: " +summaryCheckDetails.getElderNumMale() 
					+"\r\nTotal Female elderly:" +summaryCheckDetails.getElderNumFemale()+"\r\nCheck up done:" +summaryCheckDetails.getElderCheckUpDone()
					+"\r\nCheck up not done:" +summaryCheckDetails.getElderCheckUpNotDone());
				}
				else{
					txtpnCheckOverview.setText("Check Up Time: \r\nRoom Number: \r\nTotal number of elderly: \r\nTotal Male elderly: \r\nTotal Female elderly: \r\nCheck up done: \r\nCheck up not done:");
				}
			}
		});

		JLabel lblTime = new JLabel("Time: ");
		lblTime.setBounds(90, 177, 80, 41);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTime.setForeground(new Color(0, 128, 128));
		add(lblTime);


		timeComboBox = new JComboBox<String>();
		timeComboBox.setBackground(UIManager.getColor("TextField.highlight"));
		timeComboBox.setBounds(277, 184, 125, 31);
		timeComboBox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		timeComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"Morning","Afternoon","Noon" }));
		add(timeComboBox);

		timeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				summaryCheckDetails=ElderData.UpdateOverview(roomComboBox.getSelectedItem().toString(),timeComboBox.getSelectedItem().toString(),so);
				txtpnCheckOverview.setText("Check Up Time: "+timeComboBox.getSelectedItem().toString() + "\r\nRoom Number: "+summaryCheckDetails.getElderRoomNumber() 
				+"\r\nTotal number of elderly: "+summaryCheckDetails.getElderNum() +"\r\nTotal Male elderly: " +summaryCheckDetails.getElderNumMale() 
				+"\r\nTotal Female elderly:" +summaryCheckDetails.getElderNumFemale()+"\r\nCheck up done:" +summaryCheckDetails.getElderCheckUpDone()
				+"\r\nCheck up not done:" +summaryCheckDetails.getElderCheckUpNotDone());
			}
		});

		JLabel lblOverview = new JLabel("Overview:");
		lblOverview.setForeground(new Color(0, 128, 128));
		lblOverview.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOverview.setBounds(551, 117, 151, 41);
		add(lblOverview);

		txtpnCheckOverview = new JTextPane();
		txtpnCheckOverview.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnCheckOverview.setText("Check Up Time: \r\nRoom Number: \r\nTotal number of elderly: \r\nTotal Male elderly: \r\nTotal Female elderly: \r\nCheck up done: \r\nCheck up not done:");
		txtpnCheckOverview.setBounds(551, 155, 339, 226);
		add(txtpnCheckOverview);

		JButton btnGetCheck = new JButton("Start Check-Up");
		btnGetCheck.setBounds(90, 256, 312, 31);
		btnGetCheck.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(btnGetCheck);
		btnGetCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCheckSelect(roomComboBox.getSelectedItem().toString());
				setCheckTimeSelect(timeComboBox.getSelectedItem().toString());
				if(!roomComboBox.getSelectedItem().toString().equalsIgnoreCase(" ")){
					if(CheckUpObject.checkupValid(roomComboBox.getSelectedItem().toString(),timeComboBox.getSelectedItem().toString(),so)==false){
						JOptionPane.showMessageDialog(null, "Check up has already been done!");
					}
					else{
						JPanel gottenCheckup=new MedCheckPanel();
						MedPanel.MedCardPanel.add(gottenCheckup,MedPanel.MCHECKPANEL);

						CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
						mainCards.show(MedPanel.MedCardPanel, MedPanel.MCHECKPANEL);
						roomComboBox.setSelectedItem(" ");
					}
				}

				else{
					JOptionPane.showMessageDialog(null, "Please select a room number");
				}
			}
		});

	}

	public void setCheckSelect(String select){
		selected=select;
	}
	public static  String getCheckSelect(){
		return selected;
	}
	public void setCheckTimeSelect(String selectDosageTime){
		timeselected=selectDosageTime;
	}
	public static String getCheckTimeSelect(){
		return timeselected;
	}
}
