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

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.UIManager;

import eldertrack.db.SQLObject;
import eldertrack.medical.ElderData;

import javax.swing.JTextPane;
// bugs
// the summary tab is abit buggy will fix on ltr date
// checking is not fully tested but should be working fine, need more test to be sure
// have not done the time reset yet, will do on ltr date



public class MedDosageSearchPanel extends JPanel {

	private static final long serialVersionUID = -2593071831861718177L;
	private JComboBox<String> roomcombobox;
	private JComboBox<String> timeCombobox;
	private JTextPane txtpnOverview;
	public static String roomselected;
	public static String timeselected;


	private ElderData room101=new ElderData();
	private ElderData room102=new ElderData();
	private ElderData room103=new ElderData();
	private ElderData room104=new ElderData();
	private ElderData room105=new ElderData();
	private ElderData room201=new ElderData();
	private ElderData room202=new ElderData();
	private ElderData room203=new ElderData();

	public MedDosageSearchPanel() {

		setBounds(5, 5, 975, 510);
		setLayout(null);


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


		roomcombobox = new JComboBox<String>();
		roomcombobox.setBackground(UIManager.getColor("TextField.highlight"));
		roomcombobox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		roomcombobox.setBounds(277, 124, 125, 31);
		roomcombobox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {" ","101","102","103","104","105","201","202","203" }));
		add(roomcombobox);

		roomcombobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updatesummary(roomcombobox.getSelectedItem().toString());
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
				updatesummary(roomcombobox.getSelectedItem().toString());
			}
		});


		JLabel lblOverview = new JLabel("Overview:");
		lblOverview.setForeground(new Color(0, 128, 128));
		lblOverview.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOverview.setBounds(551, 117, 151, 41);
		add(lblOverview);

		txtpnOverview = new JTextPane();
		txtpnOverview.setText("Dosage Time:\r\nRoom Number:\r\nTotal number of elderly:\r\nTotal Male elderly:\r\nTotal Female elderly:");
		txtpnOverview.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnOverview.setEditable(false);
		txtpnOverview.setBounds(551, 155, 339, 226);
		add(txtpnOverview);

		JButton btnGetDosage = new JButton("Get Dosage");
		btnGetDosage.setBounds(90, 256, 312, 31);
		btnGetDosage.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(btnGetDosage);

		// processing of summary for elderly
		ResultSet rs;
		try {
			SQLObject so = new SQLObject();
			PreparedStatement statement = so.getPreparedStatementWithKey("SELECT * FROM et_elderly ");
			rs = statement.executeQuery();
			while(rs.next()){
				String roomNum=rs.getString("room");
				String roomGender=rs.getString("gender");

				if(roomNum.equalsIgnoreCase("101")){
					room101.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room101.setElderNumMale(1);
					}
					else{
						room101.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room101.setElderNumDosageNeeded(1);
						}
						else{
							room101.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room101.setElderNumDosageNeeded(1);
						}
						else{
							room101.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room101.setElderNumDosageNeeded(1);
						}
						else{
							room101.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("102")){
					room102.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room102.setElderNumMale(1);
					}
					else{
						room102.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room102.setElderNumDosageNeeded(1);
						}
						else{
							room102.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room102.setElderNumDosageNeeded(1);
						}
						else{
							room102.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room102.setElderNumDosageNeeded(1);
						}
						else{
							room102.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("103")){
					room103.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room103.setElderNumMale(1);
					}
					else{
						room103.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room103.setElderNumDosageNeeded(1);
						}
						else{
							room103.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room103.setElderNumDosageNeeded(1);
						}
						else{
							room103.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room103.setElderNumDosageNeeded(1);
						}
						else{
							room101.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("104")){
					room104.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room104.setElderNumMale(1);
					}
					else{
						room104.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room104.setElderNumDosageNeeded(1);
						}
						else{
							room104.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room104.setElderNumDosageNeeded(1);
						}
						else{
							room104.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room104.setElderNumDosageNeeded(1);
						}
						else{
							room104.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("105")){
					room105.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room105.setElderNumMale(1);
					}
					else{
						room105.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room105.setElderNumDosageNeeded(1);
						}
						else{
							room105.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room105.setElderNumDosageNeeded(1);
						}
						else{
							room105.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room105.setElderNumDosageNeeded(1);
						}
						else{
							room105.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("201")){
					room201.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room201.setElderNumMale(1);
					}
					else{
						room201.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room201.setElderNumDosageNeeded(1);
						}
						else{
							room201.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room201.setElderNumDosageNeeded(1);
						}
						else{
							room201.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room201.setElderNumDosageNeeded(1);
						}
						else{
							room101.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("202")){
					room202.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room202.setElderNumMale(1);
					}
					else{
						room202.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room202.setElderNumDosageNeeded(1);
						}
						else{
							room202.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room202.setElderNumDosageNeeded(1);
						}
						else{
							room202.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room202.setElderNumDosageNeeded(1);
						}
						else{
							room202.setElderNumDosageNotNeeded(1);
						}
					}
				}
				else if(roomNum.equalsIgnoreCase("203")){
					room203.setElderNum(1);
					if(roomGender.equalsIgnoreCase("m")){
						room203.setElderNumMale(1);
					}
					else{
						room203.setElderNumFemale(1);
					}
					if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("morning")){
						if(rs.getBlob("morningdosage")!= null){
							room203.setElderNumDosageNeeded(1);
						}
						else{
							room203.setElderNumDosageNotNeeded(1);
						}
					}
					else if(timeCombobox.getSelectedItem().toString().equalsIgnoreCase("afternoon")){
						if(rs.getBlob("afternoondosage")!= null){
							room203.setElderNumDosageNeeded(1);
						}
						else{
							room203.setElderNumDosageNotNeeded(1);
						}
					}
					else{
						if(rs.getBlob("noondosage")!= null){
							room203.setElderNumDosageNeeded(1);
						}
						else{
							room203.setElderNumDosageNotNeeded(1);
						}
					}
				}
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		// testing for choosing room options


		btnGetDosage.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				if(checkDosageNeeded()==true){
					setDosageRoom(roomcombobox.getSelectedItem().toString());
					setDosageTimeSelect(timeCombobox.getSelectedItem().toString());

					if(checkDosageValid(roomcombobox.getSelectedItem().toString(),timeCombobox.getSelectedItem().toString())==false){
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
	public void updatesummary(String roomChoice){
		if(roomChoice.equalsIgnoreCase("101")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 101 \r\nTotal number of elderly: "+room101.getElderNum() +"\r\nTotal Male elderly: " +room101.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room101.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room101.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room101.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("102")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 102 \r\nTotal number of elderly: "+room102.getElderNum() +"\r\nTotal Male elderly: " +room102.getElderNumMale()
			+"\r\nTotal Female elderly:" +room102.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room102.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room102.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("103")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 103 \r\nTotal number of elderly: "+room103.getElderNum() +"\r\nTotal Male elderly: " +room103.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room103.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room103.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room103.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("104")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 104 \r\nTotal number of elderly: "+room104.getElderNum() +"\r\nTotal Male elderly: " +room104.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room104.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room104.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room104.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("105")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 105 \r\nTotal number of elderly: "+room105.getElderNum() +"\r\nTotal Male elderly: " +room105.getElderNumMale()
			+"\r\nTotal Female elderly:" +room105.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room105.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room105.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("201")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 201 \r\nTotal number of elderly: "+room201.getElderNum() +"\r\nTotal Male elderly: " +room201.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room201.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room201.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room201.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("202")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 202 \r\nTotal number of elderly: "+room202.getElderNum() +"\r\nTotal Male elderly: " +room202.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room202.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room202.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room202.getElderNumDosageNotNeeded());
		}
		else if (roomChoice.equalsIgnoreCase("203")){
			txtpnOverview.setText("Dosage Time: "+timeCombobox.getSelectedItem().toString() + "\r\nRoom Number: 203 \r\nTotal number of elderly: "+room203.getElderNum() +"\r\nTotal Male elderly: " +room203.getElderNumMale() 
			+"\r\nTotal Female elderly:" +room203.getElderNumFemale() +"\r\nTotal number of elderly that needs dosage: "+room203.getElderNumDosageNeeded() +"\r\nTotal number of elderly that don't need dosage: "+room203.getElderNumDosageNotNeeded());
		}

		else{
			txtpnOverview.setText("Dosage Time:\r\nRoom Number:\r\nTotal number of elderly:\r\nTotal Male elderly:\r\nTotal Female elderly:");
		}
	}
	public Boolean checkDosageNeeded(){
		int check=1;
	
		if(roomcombobox.getSelectedItem().toString().equals("101")){
			if(room101.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("102")){
			if(room102.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("103")){
			if(room103.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("104")){
			if(room104.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("105")){
			if(room105.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("201")){
			if(room201.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("202")){
			if(room202.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else if(roomcombobox.getSelectedItem().toString().equals("203")){
			if(room203.getElderNumDosageNeeded() ==0){
				JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
				check=0;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please check if you have filled in the required fields");
			check=0;
		}
		
		if(check==0){
			return false;
		}
		else
			return true;
		

	}
	public Boolean checkDosageValid(String roomNum,String timing){
		SQLObject so = new SQLObject();
		ResultSet rs = null;
		int totalElder=0;
		int checked=0;
		try {

			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1,roomNum);
			stmt.executeQuery();
			rs = stmt.getResultSet();

			while(rs.next()){
				if(timing.equalsIgnoreCase("morning")){
					if(rs.getInt("morningtaken")!=0){
						checked++;
					}
					totalElder++;
				}
				else if(timing.equalsIgnoreCase("afternoon")){
					if(rs.getInt("afternoontaken")!=0){
						checked++;
					}
					totalElder++;
				}

				else{
					if(rs.getInt("noontaken")!=0){
						checked++;
					}
					totalElder++;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(checked==totalElder){
			return false;
		}
		else{
			return true;
		}
	}
}
