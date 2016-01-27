package eldertrack.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;
import eldertrack.login.AccessLevel;
import eldertrack.management.ManagementObject;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import eldertrack.misc.NRICUtils;
import eldertrack.misc.TableHelper;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class MgmtPanel extends JPanel {
	private static SQLObject so = new SQLObject();
	private static final long serialVersionUID = 4318548492960279050L;
	private JLabel lblEManagementLbl;
	private JTable elderlyTable;
	private JTable staffTable;
	private JTextField elderlyNameValue;
	private JTextField elderlyNricValue;
	private JTextField elderlyGenderValue;
	private JTextField staffFirstNameValue;
	private JTextField staffLastNameValue;
	private JTextField elderlyRoomValue;
	private JTextField elderlyAddressValue;
	private JTextField elderlySearchField;
	private JTextField staffSearchField;
	private JTextField elderlyBedValue;
	private JTextField staffNricValue;
	private JTextField elderlyContactValue;
	private JPasswordField staffSetPasswordValue;
	private JTextField editableUsernameValue;
	private JTextField elderlyEmailValue;
	private JLabel elderlyAgeValue;
	private JLabel elderlyIdValue;
	private JComboBox<String> elderlyDay;
	private JComboBox<String> elderlyYear;
	private JComboBox<String> elderlyMonth;
	private JLabel staffAgeValue;
	private JComboBox<String> staffDay;
	private JComboBox<String> staffMonth;
	private JComboBox<String> staffYear;
	private JLabel staffIdValue;
	private JComboBox<String> editableAccessLevel;
	private JLabel uneditableUsernameValue;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	MgmtPanel() {
		ArrayList<String> years_tmp = new ArrayList<String>();
		years_tmp.add("");
        for(int years = 1900 ; years <= Calendar.getInstance().get(Calendar.YEAR);years++){
        	years_tmp.add(years+"");
       }
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		// Back to Main Menu Button
					JButton btnMainMenu = new JButton("Back to Main Menu");
					btnMainMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
							parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
						}
					});
					btnMainMenu.setBounds(820, 15, 139, 40);
					add(btnMainMenu);
					//
					
					JButton discardChanges = new JButton("Discard Changes");
					discardChanges.setBounds(800, 465, 132, 25);
					add(discardChanges);
					
					JButton elderlySearchBtn = new JButton("Search");
					elderlySearchBtn.setBounds(156, 534, 73, 25);
					add(elderlySearchBtn);
					
					elderlySearchField = new JTextField();
					elderlySearchField.setBounds(30, 535, 120, 22);
					add(elderlySearchField);
					elderlySearchField.setColumns(10);
					
					JButton staffSearchBtn = new JButton("Search");
					staffSearchBtn.setBounds(156, 535, 73, 25);
					add(staffSearchBtn);
					
					staffSearchField = new JTextField();
					staffSearchField.setColumns(10);
					staffSearchField.setBounds(30, 536, 120, 22);
					add(staffSearchField);
					
					JButton elderlyRemove = new JButton("Remove Selected");
					elderlyRemove.setBounds(476, 534, 132, 25);
					add(elderlyRemove);
					
					JButton elderlySave = new JButton("Save Changes");
					elderlySave.setBounds(625, 432, 132, 60);
					add(elderlySave);
					
					JButton staffSave = new JButton("Save Changes");		
					staffSave.setBounds(625, 432, 132, 60);
					staffSave.setVisible(false);
					add(staffSave);
					
					JButton staffRemove = new JButton("Remove Selected");
				
					staffRemove.setBounds(476, 534, 132, 25);
					add(staffRemove);
					
					JButton addElderly = new JButton("Add New Elderly");
					addElderly.setBounds(800, 432, 132, 25);
					add(addElderly);
					
					JButton confirmAddStaff = new JButton("Save New Staff");
					confirmAddStaff.setBounds(800, 432, 132, 25);
					confirmAddStaff.setVisible(false);
					add(confirmAddStaff);
					
					JButton cancelAddStaff = new JButton("Cancel");	
					cancelAddStaff.setBounds(625, 432, 132, 25);
					cancelAddStaff.setVisible(false);
					add(cancelAddStaff);
					
					JButton addStaff = new JButton("Add New Staff");	
					addStaff.setBounds(800, 432, 132, 25);
					addStaff.setVisible(false);
					add(addStaff);
					
					JButton confirmAddElderly = new JButton("Save New Elderly");
					confirmAddElderly.setBounds(800, 432, 132, 25);
					confirmAddElderly.setVisible(false);
					add(confirmAddElderly);
					
					JButton cancelAddElderly = new JButton("Cancel");
					cancelAddElderly.setBounds(625, 432, 132, 25);
					cancelAddElderly.setVisible(false);
					add(cancelAddElderly);
					
					JPanel elderlyManagementPanel = new JPanel();
					elderlyManagementPanel.setBounds(625, 79, 350, 340);
					add(elderlyManagementPanel);
					elderlyManagementPanel.setLayout(null);
					
					JLabel elderlyName = new JLabel("NAME");
					elderlyName.setBounds(19, 40, 109, 25);
					elderlyManagementPanel.add(elderlyName);
					elderlyName.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel elderlyDob = new JLabel("DOB");
					elderlyDob.setBounds(18, 70, 109, 25);
					elderlyManagementPanel.add(elderlyDob);
					elderlyDob.setFont(new Font("Calibri", Font.PLAIN, 24));
				
					JLabel elderlyId = new JLabel("ELDER ID");
					elderlyId.setBounds(19, 10, 109, 25);
					elderlyManagementPanel.add(elderlyId);
					elderlyId.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel elderlyNric = new JLabel("NRIC");
					elderlyNric.setBounds(18, 100, 109, 25);
					elderlyManagementPanel.add(elderlyNric);
					elderlyNric.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel elderlyGender = new JLabel("GENDER");
					elderlyGender.setBounds(19, 130, 121, 25);
					elderlyManagementPanel.add(elderlyGender);
					elderlyGender.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_3 = new JLabel(":");
					label_3.setBounds(152, 10, 23, 25);
					elderlyManagementPanel.add(label_3);
					label_3.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_4 = new JLabel(":");
					label_4.setBounds(152, 40, 23, 25);
					elderlyManagementPanel.add(label_4);
					label_4.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_5 = new JLabel(":");
					label_5.setBounds(152, 70, 23, 25);
					elderlyManagementPanel.add(label_5);
					label_5.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_6 = new JLabel(":");
					label_6.setBounds(153, 100, 23, 25);
					elderlyManagementPanel.add(label_6);
					label_6.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_8 = new JLabel(":");
					label_8.setBounds(153, 130, 23, 25);
					elderlyManagementPanel.add(label_8);
					label_8.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					elderlyNameValue = new JTextField();
					elderlyNameValue.setBounds(179, 40, 116, 22);
					elderlyManagementPanel.add(elderlyNameValue);
					elderlyNameValue.setColumns(10);
					
					elderlyNricValue = new JTextField();
					elderlyNricValue.setBounds(179, 100, 116, 22);
					elderlyManagementPanel.add(elderlyNricValue);
					elderlyNricValue.setColumns(10);
					
					elderlyGenderValue = new JTextField();
					elderlyGenderValue.setBounds(179, 130, 116, 22);
					elderlyManagementPanel.add(elderlyGenderValue);
					elderlyGenderValue.setColumns(10);
					
					JLabel elderlyAge = new JLabel("AGE");
					elderlyAge.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyAge.setBounds(19, 160, 121, 25);
					elderlyManagementPanel.add(elderlyAge);
					
					JLabel label_16 = new JLabel(":");
					label_16.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_16.setBounds(152, 160, 23, 25);
					elderlyManagementPanel.add(label_16);
					
					JLabel elderlyRoom = new JLabel("ROOM");
					elderlyRoom.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyRoom.setBounds(19, 190, 121, 25);
					elderlyManagementPanel.add(elderlyRoom);
					
					JLabel label_18 = new JLabel(":");
					label_18.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_18.setBounds(152, 190, 23, 25);
					elderlyManagementPanel.add(label_18);
					
					elderlyRoomValue = new JTextField();
					elderlyRoomValue.setColumns(10);
					elderlyRoomValue.setBounds(179, 190, 116, 22);
					elderlyManagementPanel.add(elderlyRoomValue);
					
					JLabel elderlyAddress = new JLabel("ADDRESS");
					elderlyAddress.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyAddress.setBounds(19, 220, 121, 25);
					elderlyManagementPanel.add(elderlyAddress);
					
					JLabel label_20 = new JLabel(":");
					label_20.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_20.setBounds(152, 220, 23, 25);
					elderlyManagementPanel.add(label_20);
					
					elderlyAddressValue = new JTextField();
					elderlyAddressValue.setColumns(10);
					elderlyAddressValue.setBounds(179, 220, 116, 22);
					elderlyManagementPanel.add(elderlyAddressValue);
					
					elderlyIdValue = new JLabel("");
					elderlyIdValue.setBounds(179, 10, 116, 22);
					elderlyManagementPanel.add(elderlyIdValue);
					
					elderlyAgeValue = new JLabel("");
					elderlyAgeValue.setBounds(180, 160, 116, 22);
					elderlyManagementPanel.add(elderlyAgeValue);
					
					JLabel elderlyBed = new JLabel("BED NO");
					elderlyBed.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyBed.setBounds(19, 250, 121, 25);
					elderlyManagementPanel.add(elderlyBed);
					
					JLabel label_2 = new JLabel(":");
					label_2.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_2.setBounds(152, 250, 23, 25);
					elderlyManagementPanel.add(label_2);
					
					elderlyBedValue = new JTextField();
					elderlyBedValue.setColumns(10);
					elderlyBedValue.setBounds(179, 250, 116, 22);
					elderlyManagementPanel.add(elderlyBedValue);
					
					JLabel elderlyContact = new JLabel("N.O.K. NO");
					elderlyContact.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyContact.setBounds(19, 280, 121, 25);
					elderlyManagementPanel.add(elderlyContact);
					
					JLabel label_9 = new JLabel(":");
					label_9.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_9.setBounds(152, 280, 23, 25);
					elderlyManagementPanel.add(label_9);
					
					elderlyContactValue = new JTextField();
					elderlyContactValue.setColumns(10);
					elderlyContactValue.setBounds(179, 280, 116, 22);
					elderlyManagementPanel.add(elderlyContactValue);
					
					elderlyDay = new JComboBox<String>();
					elderlyDay.setModel(new DefaultComboBoxModel<String>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					elderlyDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
					elderlyDay.setBounds(179, 70, 40, 22);
					elderlyManagementPanel.add(elderlyDay);
					
					elderlyMonth = new JComboBox<String>();
					elderlyMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
					elderlyMonth.setBounds(224, 70, 40, 22);
					elderlyManagementPanel.add(elderlyMonth);
				
					elderlyYear = new JComboBox(years_tmp.toArray());
					elderlyYear.setBounds(269, 70, 60, 22);
					elderlyManagementPanel.add(elderlyYear);
					
					JLabel elderlyEmail = new JLabel("N.O.K. EMAIL");
					elderlyEmail.setFont(new Font("Calibri", Font.PLAIN, 24));
					elderlyEmail.setBounds(19, 310, 130, 25);
					elderlyManagementPanel.add(elderlyEmail);
					
					JLabel label_15 = new JLabel(":");
					label_15.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_15.setBounds(152, 310, 23, 25);
					elderlyManagementPanel.add(label_15);
					
					elderlyEmailValue = new JTextField();
					elderlyEmailValue.setColumns(10);
					elderlyEmailValue.setBounds(179, 310, 116, 22);
					elderlyManagementPanel.add(elderlyEmailValue);
					
					JPanel staffManagementPanel = new JPanel();
					staffManagementPanel.setBounds(625, 79, 350, 340);
					add(staffManagementPanel);
					staffManagementPanel.setVisible(false);
					staffManagementPanel.setLayout(null);
					
					JLabel staffFirstName_1 = new JLabel("FIRST NAME");
					staffFirstName_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffFirstName_1.setBounds(19, 60, 123, 25);
					staffManagementPanel.add(staffFirstName_1);
					
					JLabel staffLastName_1 = new JLabel("LAST NAME");
					staffLastName_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffLastName_1.setBounds(19, 90, 124, 25);
					staffManagementPanel.add(staffLastName_1);
					
					JLabel staffId_1 = new JLabel("STAFF ID");
					staffId_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffId_1.setBounds(19, 30, 123, 25);
					staffManagementPanel.add(staffId_1);
					
					JLabel staffDob_1 = new JLabel("DOB");
					staffDob_1.setBounds(19, 120, 109, 25);
					staffManagementPanel.add(staffDob_1);
					staffDob_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					JLabel label_12 = new JLabel(":");
					label_12.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_12.setBounds(152, 30, 23, 25);
					staffManagementPanel.add(label_12);
					
					JLabel label_13 = new JLabel(":");
					label_13.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_13.setBounds(152, 60, 23, 25);
					staffManagementPanel.add(label_13);
					
					JLabel label_14 = new JLabel(":");
					label_14.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_14.setBounds(152, 90, 23, 25);
					staffManagementPanel.add(label_14);
					
					staffFirstNameValue = new JTextField();
					staffFirstNameValue.setColumns(10);
					staffFirstNameValue.setBounds(180, 60, 116, 22);
					staffManagementPanel.add(staffFirstNameValue);
					
					JLabel label_22 = new JLabel(":");
					label_22.setBounds(152, 120, 23, 25);
					staffManagementPanel.add(label_22);
					label_22.setFont(new Font("Calibri", Font.PLAIN, 24));
					
					staffLastNameValue = new JTextField();
					staffLastNameValue.setColumns(10);
					staffLastNameValue.setBounds(180, 90, 116, 22);
					staffManagementPanel.add(staffLastNameValue);
					
					JLabel staffAge = new JLabel("AGE");
					staffAge.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffAge.setBounds(19, 150, 109, 25);
					staffManagementPanel.add(staffAge);
					
					JLabel label = new JLabel(":");
					label.setFont(new Font("Calibri", Font.PLAIN, 24));
					label.setBounds(152, 150, 23, 25);
					staffManagementPanel.add(label);
					
					staffAgeValue = new JLabel("");
					staffAgeValue.setBounds(180, 150, 116, 22);
					staffManagementPanel.add(staffAgeValue);
					
					JLabel staffNric_1 = new JLabel("NRIC");
					staffNric_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffNric_1.setBounds(19, 180, 109, 25);
					staffManagementPanel.add(staffNric_1);
					
					JLabel label_7 = new JLabel(":");
					label_7.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_7.setBounds(152, 180, 23, 25);
					staffManagementPanel.add(label_7);
					
					staffNricValue = new JTextField();
					staffNricValue.setColumns(10);
					staffNricValue.setBounds(180, 180, 116, 22);
					staffManagementPanel.add(staffNricValue);
					
					staffIdValue = new JLabel("");
					staffIdValue.setBounds(180, 30, 116, 22);
					staffManagementPanel.add(staffIdValue);
					
					JLabel staffAccessLevel = new JLabel("ACCESS LVL");
					staffAccessLevel.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffAccessLevel.setBounds(19, 210, 123, 25);
					staffManagementPanel.add(staffAccessLevel);
					
					JLabel label_10 = new JLabel(":");
					label_10.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_10.setBounds(152, 210, 23, 25);
					staffManagementPanel.add(label_10);
					
					JLabel staffUsername_1 = new JLabel("USERNAME");
					staffUsername_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffUsername_1.setBounds(19, 270, 123, 25);
					staffManagementPanel.add(staffUsername_1);
					
					JLabel label_11 = new JLabel(":");
					label_11.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_11.setBounds(152, 240, 23, 25);
					staffManagementPanel.add(label_11);
					
					JLabel staffSetPassword = new JLabel("SET PW");
					staffSetPassword.setFont(new Font("Calibri", Font.PLAIN, 24));
					staffSetPassword.setBounds(19, 240, 109, 25);
					staffManagementPanel.add(staffSetPassword);
					
					staffSetPasswordValue = new JPasswordField();
					staffSetPasswordValue.setBounds(180, 240, 116, 22);
					staffManagementPanel.add(staffSetPasswordValue);
					
					JLabel label_1 = new JLabel(":");
					label_1.setFont(new Font("Calibri", Font.PLAIN, 24));
					label_1.setBounds(152, 270, 23, 25);
					staffManagementPanel.add(label_1);
					
					editableUsernameValue = new JTextField();
					editableUsernameValue.setColumns(10);
					editableUsernameValue.setBounds(180, 270, 116, 22);
					editableUsernameValue.setVisible(false);
					staffManagementPanel.add(editableUsernameValue);
					
					uneditableUsernameValue = new JLabel("");
					uneditableUsernameValue.setBounds(180, 270, 116, 22);
					uneditableUsernameValue.setVisible(true);
					staffManagementPanel.add(uneditableUsernameValue);
					
					editableAccessLevel = new JComboBox<String>();
					editableAccessLevel.setModel(new DefaultComboBoxModel<String>(new String[] {"No Access", "Staff", "Sr Staff", "Admin", "Manager"}));
					editableAccessLevel.setBounds(180, 210, 116, 22);
					staffManagementPanel.add(editableAccessLevel);
					
					staffDay = new JComboBox<String>();
					staffDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
					staffDay.setModel(new DefaultComboBoxModel<String>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					staffDay.setBounds(180, 120, 40, 22);
					staffManagementPanel.add(staffDay);
					
					staffYear = new JComboBox(years_tmp.toArray());
					staffYear.setBounds(270, 120, 60, 22);
					staffManagementPanel.add(staffYear);
					
					staffMonth = new JComboBox<String>();
					staffMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
					staffMonth.setBounds(225, 120, 40, 22);
					staffManagementPanel.add(staffMonth);
			
					lblEManagementLbl = new JLabel("ElderTrack Management");
					lblEManagementLbl.setForeground(SystemColor.textHighlight);
					lblEManagementLbl.setFont(new Font("Segoe UI", Font.ITALIC, 40));
					lblEManagementLbl.setBounds(26, 5, 430, 61);
					add(lblEManagementLbl);
		
					JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
					tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
					tabbedPane.setBounds(29, 79, 584, 445);
					add(tabbedPane);
					
					JScrollPane scrollPane = new JScrollPane();
					tabbedPane.addTab("Elderly", null, scrollPane, null);
					
					
					DefaultTableModel allEldersData;
					allEldersData = TableHelper.getElderlyDetailed("");
					elderlyTable = new JTable(allEldersData);
					setColumnWidths();
					
					JScrollPane scrollPane2 = new JScrollPane();
					tabbedPane.addTab("Staff", null, scrollPane2, null);
					
					DefaultTableModel allStaffData;
					allStaffData = TableHelper.getStaff("");
					staffTable = new JTable(allStaffData);
					setColumnWidths();
					
					scrollPane2.setViewportView(staffTable);
					scrollPane.setViewportView(elderlyTable);
					
					// Obtain access level from staff session
					AccessLevel CurrentAL = MainFrame.getInstance().getSessionInstance().getAccessLevel();
		
		elderlyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = elderlyTable.getSelectedRow();
					String table_clicked = (elderlyTable.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT * FROM et_elderly WHERE id=?";
					ResultSet rs = so.getResultSet(sql, table_clicked);
					
					while(rs.next()){
						String add1 = rs.getString("id");
						elderlyIdValue.setText(add1);
						
						String add2 = rs.getString("name");
						elderlyNameValue.setText(add2);
						
						setElderlyBirthday(rs.getString("dob").toCharArray());
						
						String add4 = rs.getString("nric");
						elderlyNricValue.setText(add4);
						
						String add5 = rs.getString("gender");
						elderlyGenderValue.setText(add5);
						
						String add7 = rs.getString("room");
						elderlyRoomValue.setText(add7);
						
						String add8 = rs.getString("address");
						elderlyAddressValue.setText(add8);
						
						int add9 = rs.getInt("bed");
						elderlyBedValue.setText(Integer.toString(add9));
						
						String add10 = rs.getString("contact");
						elderlyContactValue.setText(add10);
						
						String add11 = rs.getString("email");
						elderlyEmailValue.setText(add11);
												
						//Calculation of age from date of birth
						String age = ManagementObject.calculateAge(rs);
						elderlyAgeValue.setText(age);	
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
			
			addStaff.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(CurrentAL != AccessLevel.ADMIN || CurrentAL != AccessLevel.MANAGER){
						JOptionPane.showMessageDialog(null, "You do not have enough priveleges to add a new elderly!");
					}else if(addStaff.isVisible() == true){
						confirmAddStaff.setVisible(true);
						addStaff.setVisible(false);
						staffSave.setVisible(false);
						uneditableUsernameValue.setVisible(false);
						editableUsernameValue.setVisible(true);
						cancelAddStaff.setVisible(true);
						
						clearAllFields();
					}
				}
			});
			
			addElderly.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(addElderly.isVisible() == true){
						confirmAddElderly.setVisible(true);
						addElderly.setVisible(false);
						elderlySave.setVisible(false);
						cancelAddElderly.setVisible(true);
						
						clearAllFields();
					}
				}
			});
			
			
			confirmAddElderly.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add?");
					if (dialogResult == JOptionPane.YES_OPTION){
						try{
						String name = elderlyNameValue.getText();
						String birthString = (elderlyYear.getSelectedItem() + "-" + elderlyMonth.getSelectedItem() + "-" + elderlyDay.getSelectedItem());
						String nric = elderlyNricValue.getText();
						String gender = elderlyGenderValue.getText();
						String room = elderlyRoomValue.getText();
						String address = elderlyAddressValue.getText();
						String bedString = elderlyBedValue.getText();
						String contact = elderlyContactValue.getText();
						String email = elderlyEmailValue.getText();
						
						// Check for empty fields
						boolean empty = ManagementObject.elderlyEmptyFields(name, birthString, nric, gender, room, address, bedString, contact, email);
						
						if(empty == true){
							JOptionPane.showMessageDialog(null, "One or more of the fields are empty! Please check your entries!");
						}else{
						PreparedStatement ps1 = so.getPreparedStatement("SELECT bed, nric FROM et_elderly WHERE room=?");
						ps1.setString(1, room);
						ResultSet check = ps1.executeQuery();
						
						int bed = Integer.parseInt(bedString);
						boolean dupeBed = ManagementObject.checkDuplicateBeds(bed, check);
						boolean dupeNric = ManagementObject.checkDuplicateNrics(nric, check);
						boolean validNric = NRICUtils.validate(nric);
						boolean validPhoneNo = ManagementObject.checkValidPhoneNo(contact);
						boolean validEmail = eldertrack.misc.ValidateEmail.validateEmail(email);
						boolean invalid = ManagementObject.invalidEntries(dupeBed, dupeNric, validNric, validPhoneNo, bedString, validEmail);
					
						if(invalid == true){
							System.out.println("Invalid entries detected! Discontinuing update.");
						}
						else{
						ManagementObject.executeAddElderly(birthString, name, nric, gender, room, address, bed, contact, email);
						
						refreshElderly();
					
						//Clear used fields
						clearAllFields();
						
						JOptionPane.showMessageDialog(null, "Person has successfully been added to database!");
						}
						if(addElderly.isVisible() == false){
							confirmAddElderly.setVisible(false);
							addElderly.setVisible(true);
							elderlySave.setVisible(true);
							cancelAddElderly.setVisible(false);
								}
							}
						}catch(Exception e4){
							e4.printStackTrace();
						}
					}
				}
			}
	);
			
			cancelAddStaff.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(addStaff.isVisible() == false){
						confirmAddStaff.setVisible(false);
						addStaff.setVisible(true);
						staffSave.setVisible(true);
						uneditableUsernameValue.setVisible(true);
						editableUsernameValue.setVisible(false);
						cancelAddStaff.setVisible(false);
						
						clearAllFields();
					}
				}
			});
			
			cancelAddElderly.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(addElderly.isVisible() == false){
						confirmAddElderly.setVisible(false);
						addElderly.setVisible(true);
						elderlySave.setVisible(true);
						cancelAddElderly.setVisible(false);
						
						clearAllFields();
					}
				}
			});
			
			confirmAddStaff.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this staff?");
					if(dialogResult == JOptionPane.YES_OPTION){
						try{
							String staffFirstName = staffFirstNameValue.getText();
							String staffLastName = staffLastNameValue.getText();
							String staffNric = staffNricValue.getText();
							String staffBirthString = (staffYear.getSelectedItem() + "-" + staffMonth.getSelectedItem() + "-" + staffDay.getSelectedItem());
							String staffUsername = editableUsernameValue.getText();
							int accessLevel = editableAccessLevel.getSelectedIndex();
							
							if(staffSetPasswordValue.getPassword() == null || staffSetPasswordValue.getPassword().length == 0){
								JOptionPane.showMessageDialog(null, "The password field is empty!");
							}else{
								// Obtain char array
								char[] passChar = staffSetPasswordValue.getPassword();
								// Conversion from char array to byte array
								byte[] passByte = ManagementObject.toBytes(passChar);
								// Generate salt
								String salt = ManagementObject.saltGenerator();
								// Hashing password with salt...
								String securePassword = ManagementObject.passwordHasher(passByte, salt);
								
								boolean empty = ManagementObject.staffEmptyFields(staffFirstName, staffLastName, staffNric, staffBirthString);
								
							if(empty == true){
								JOptionPane.showMessageDialog(null, "One or more fields are empty! Please check your entries!");
								System.out.println("Empty fields detected! Discontinuing update.");
							}else{
							ResultSet rs = ManagementObject.getAllStaff();
							boolean dupeNric = ManagementObject.checkDuplicateNrics(staffNric,rs);
							boolean validNric = NRICUtils.validate(staffNric);
							
							if(dupeNric == true){
								JOptionPane.showMessageDialog(null,"There are duplicate NRICS! Please check your entries!");
							}else if(validNric == false){
								JOptionPane.showMessageDialog(null, "That is not a valid NRIC! Please check your entry!");
							}else{
								// Executes the update and adds staff member to the database
								ManagementObject.executeAddStaff(staffBirthString, staffUsername, securePassword, staffFirstName, staffLastName, staffNric, accessLevel, salt);
								JOptionPane.showMessageDialog(null,"New staff has been successfully added!");
								
								if(confirmAddStaff.isVisible() == true){
									confirmAddStaff.setVisible(false);
									addStaff.setVisible(true);
									staffSave.setVisible(true);
									uneditableUsernameValue.setVisible(true);
									editableUsernameValue.setVisible(false);
									cancelAddStaff.setVisible(false);
									editableAccessLevel.setSelectedIndex(0);
									
									clearAllFields();
							}
							
							refreshStaff();
										}
									}
								}
							}
							catch(SQLException e){
							e.printStackTrace();
						}
						
						}
					}
				}
			);
			
			staffSave.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the data?");
							if(dialogResult == JOptionPane.YES_OPTION){
								try{
									String staffId = staffIdValue.getText();
									String firstName = staffFirstNameValue.getText();
									String lastName = staffLastNameValue.getText();
									String nric = staffNricValue.getText();
									String birthString = (staffYear.getSelectedItem() + "-" + staffMonth.getSelectedItem() + "-" + staffDay.getSelectedItem());
									int accessLevel = editableAccessLevel.getSelectedIndex();
									boolean empty = ManagementObject.staffEmptyFields(firstName, lastName, nric, birthString);
									
									if(empty == true){
										JOptionPane.showMessageDialog(null, "One or more fields are empty! Please check your entires!");
									}else{
									ResultSet check = ManagementObject.retrieveStaffNrics(staffId);
									boolean dupeNric = ManagementObject.checkDuplicateNrics(nric, check);
									boolean validNric = NRICUtils.validate(nric);
									
									if(dupeNric == true){
										JOptionPane.showMessageDialog(null, "There are duplicate NRICS! Please check your entries");
									}else if (validNric == false){
										JOptionPane.showMessageDialog(null, "That is not a valid NRIC! Please check your entry!");
									}else if(staffSetPasswordValue.getPassword().length != 0){			
										
										// Password encryption
										char[] passChar = staffSetPasswordValue.getPassword();
										// Conversion from char array to byte array
										byte[] passByte = ManagementObject.toBytes(passChar);
										// Retrieval of salt of user
										String salt = ManagementObject.getUserSalt(staffId);
										// Hashing password with retrieved salt...
										String securePassword = ManagementObject.passwordHasher(passByte, salt);
									
										// Executes update with a new password
										ManagementObject.executeStaffUpdateWithPassword(birthString, firstName, lastName, nric, securePassword, accessLevel, staffId);
										JOptionPane.showMessageDialog(null, "Data has been succesfully saved!");
									}else{
										// Executes update without the password
										ManagementObject.executeStaffUpdateNoPassword(birthString, firstName, lastName, nric, staffId);
										JOptionPane.showMessageDialog(null, "Data has been succesfully saved!");
									}
											refreshStaff();
										}
									}catch(SQLException e){
									e.printStackTrace();
								}		
							}
				}
			});
			
			elderlySave.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the data?");
						if (dialogResult == JOptionPane.YES_OPTION){
							try{
								String id = elderlyIdValue.getText();
								String name = elderlyNameValue.getText();
								String birthString = (elderlyYear.getSelectedItem() + "-" + elderlyMonth.getSelectedItem() + "-" + elderlyDay.getSelectedItem());
								String nric = elderlyNricValue.getText();
								String gender = elderlyGenderValue.getText();
								String room = elderlyRoomValue.getText();
								String address = elderlyAddressValue.getText();
								String bed = elderlyBedValue.getText();
								String contact = elderlyContactValue.getText();
								String email = elderlyEmailValue.getText();
								
								boolean empty = ManagementObject.elderlyEmptyFields(name, birthString, nric, gender, room, address, bed, contact, email);
								if(empty == true){
									JOptionPane.showMessageDialog(null, "One or more of the fields are empty! Please check your entries!");
								}else{
									ResultSet checkBed = ManagementObject.retrieveElderlySameRoom(room, id);
									ResultSet checkNric = ManagementObject.retrieveElderlyNrics();
								
								//Check for duplicate beds and exceeding bed limit
								boolean dupeBed = ManagementObject.checkDuplicateBeds(Integer.parseInt(bed), checkBed);
								boolean dupeNric = ManagementObject.checkDuplicateNrics(nric, checkNric);
								boolean validNric = NRICUtils.validate(nric);
								boolean validPhoneNo = ManagementObject.checkValidPhoneNo(contact);
								boolean validEmail = eldertrack.misc.ValidateEmail.validateEmail(email);
								boolean invalidEntries = ManagementObject.invalidEntries(dupeBed, dupeNric, validNric, validPhoneNo, bed, validEmail);
								
								if(invalidEntries == true){
									System.out.println("There were invalid entries encountered. Discontinuing update.");
								}else{
									ManagementObject.executeElderlyUpdate(name, nric, gender, room, bed, contact, address, id, birthString, email);
									JOptionPane.showMessageDialog(null, "Update successfully completed!");	
									
									// Refresh table
									refreshElderly();

								}													
							}
								}catch(Exception e1){
								JOptionPane.showMessageDialog(null,e1);	
							}
								}
						}
				}
			);
			
				elderlyRemove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected person?");
						if (dialogResult == JOptionPane.YES_OPTION){
							try{
								String id = elderlyIdValue.getText();
								ManagementObject.removePerson(Integer.parseInt(id), ManagementObject.REMOVE_ELDERLY);
								JOptionPane.showMessageDialog(null, "Removal sucessfully completed!");
								// Refresh table	
								elderlyTable.setModel(TableHelper.getElderlyDetailed(""));
								setColumnWidths();
								}catch(Exception e4){
									e4.printStackTrace();
								}
						}
					}
				}
		);
			
			elderlySearchBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						elderlyTable.setModel(TableHelper.getElderlyDetailed("%" + elderlySearchField.getText() + "%"));
						setColumnWidths();
				}
			});
			
			staffSearchBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						staffTable.setModel(TableHelper.getStaff("%" + staffSearchField.getText() + "%"));
						setColumnWidths();				}
			});
			
			tabbedPane.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    if (elderlyManagementPanel.isVisible()) {
			    	//Panels
			    	staffManagementPanel.setVisible(true);
			    	elderlyManagementPanel.setVisible(false);
			    	//Add Buttons
			    	addElderly.setVisible(false);
			    	addStaff.setVisible(true);
			    	//Save Buttons
			    	staffSave.setVisible(true);
			    	elderlySave.setVisible(false);
			    	//Search Buttons
			    	staffSearchBtn.setVisible(true);
			    	elderlySearchBtn.setVisible(false);
			    	//Search Fields
			    	staffSearchField.setVisible(true);
			    	elderlySearchField.setVisible(false);
			    	//Remove Buttons
			    	staffRemove.setVisible(true);
			    	elderlyRemove.setVisible(false);
			    	//Cancel Buttons
			    	cancelAddStaff.setVisible(true);
			    	cancelAddElderly.setVisible(false);
			    }else{
			    	//Panels
			    	staffManagementPanel.setVisible(false);
			    	elderlyManagementPanel.setVisible(true);
			    	//Add Buttons
			    	addElderly.setVisible(true);
			    	addStaff.setVisible(false);
			    	//Save Buttons
			    	staffSave.setVisible(false);
			    	elderlySave.setVisible(true);
			    	//Search Buttons
			    	staffSearchBtn.setVisible(false);
			    	elderlySearchBtn.setVisible(true);
			    	//Search Fields
			    	staffSearchField.setVisible(false);
			    	elderlySearchField.setVisible(true);
			    	//Remove Buttons
			    	staffRemove.setVisible(false);
			    	elderlyRemove.setVisible(true);
			    	//Cancel Buttons
			    	cancelAddStaff.setVisible(false);
			    	cancelAddElderly.setVisible(true);
			    	}
			    }
			});
			
			staffTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try{
						int row1 = staffTable.getSelectedRow();
						String table_clicked1 = (staffTable.getModel().getValueAt(row1, 0).toString());
						String sql1 = "SELECT * FROM et_staff WHERE staffid=?";
						ResultSet rs1 = so.getResultSet(sql1, table_clicked1);
						
						while(rs1.next()){
							String add1 = rs1.getString("staffid");
							staffIdValue.setText(add1);
							
							String add2 = rs1.getString("firstname");
							staffFirstNameValue.setText(add2);
							
							String add3 = rs1.getString("lastname");
							staffLastNameValue.setText(add3);
							
							setStaffBirthday(rs1.getString("dob").toCharArray());
							
							String add5 = rs1.getString("nric");
							staffNricValue.setText(add5);
							
							int accessLevel = rs1.getInt("accesslevel");
							editableAccessLevel.setSelectedIndex(accessLevel);
							
							String add7 = rs1.getString("username");
							uneditableUsernameValue.setText(add7);
							
							String age = ManagementObject.calculateAge(rs1);
							staffAgeValue.setText(age);
							}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			
			discardChanges.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					clearAllFields();
				}
			});
			
			staffRemove.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected staff member?");
					if(dialogResult == JOptionPane.YES_OPTION){
						int staffid = Integer.parseInt(staffIdValue.getText());
						ManagementObject.removePerson(staffid, ManagementObject.REMOVE_STAFF);
						JOptionPane.showMessageDialog(null, "Staff member has been sucessfully removed!");
						staffTable.setModel(TableHelper.getStaff(""));
						setColumnWidths();
					}
			}
			});
			
	}
	
	//Methods	
	private void setColumnWidths(){
		elderlyTable.getTableHeader().setResizingAllowed(false);
		elderlyTable.getTableHeader().setReorderingAllowed(false);
		elderlyTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		elderlyTable.getColumnModel().getColumn(0).setMaxWidth(35);
		elderlyTable.getColumnModel().getColumn(1).setPreferredWidth(115);
		elderlyTable.getColumnModel().getColumn(1).setMaxWidth(130);
		elderlyTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		elderlyTable.getColumnModel().getColumn(2).setMaxWidth(90);
		elderlyTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		elderlyTable.getColumnModel().getColumn(3).setMaxWidth(90);
		elderlyTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		elderlyTable.getColumnModel().getColumn(4).setMaxWidth(30);
		elderlyTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		elderlyTable.getColumnModel().getColumn(5).setMaxWidth(35);
		elderlyTable.getColumnModel().getColumn(6).setPreferredWidth(25);
		elderlyTable.getColumnModel().getColumn(6).setMaxWidth(35);
	}
		
	private void refreshElderly(){
		elderlyTable.setModel(TableHelper.getElderlyDetailed(""));
		setColumnWidths();
	}
	
	private void refreshStaff(){
		staffTable.setModel(TableHelper.getStaff(""));
		setColumnWidths();
	}
	
	private void clearAllFields(){
		//Clear Elderly Fields
		elderlyAgeValue.setText("");
		elderlyNameValue.setText("");
		elderlyIdValue.setText("");
		elderlyNricValue.setText("");
		elderlyAddressValue.setText("");
		elderlyRoomValue.setText("");
		elderlyGenderValue.setText("");
		elderlyDay.setSelectedIndex(0);
		elderlyYear.setSelectedIndex(0);
		elderlyMonth.setSelectedIndex(0);
		elderlyBedValue.setText("");
		elderlyContactValue.setText("");
		elderlyEmailValue.setText("");
		
		//Clear Staff Fields
		staffAgeValue.setText("");
		staffFirstNameValue.setText("");
		staffLastNameValue.setText("");
		staffDay.setSelectedIndex(0);
		staffMonth.setSelectedIndex(0);
		staffYear.setSelectedIndex(0);
		staffIdValue.setText("");
		staffAgeValue.setText("");
		staffNricValue.setText("");
		uneditableUsernameValue.setText("");
		editableUsernameValue.setText("");
		staffSetPasswordValue.setText("");
		editableAccessLevel.setSelectedIndex(0);
	}
	
	private void setStaffBirthday(char[] dobChar){
		String day = (String.valueOf(dobChar[8]) + String.valueOf(dobChar[9]));
		String month = (String.valueOf(dobChar[5]) + String.valueOf(dobChar[6]));
		String year = (String.valueOf(dobChar[0]) + String.valueOf(dobChar[1]) + String.valueOf(dobChar[2]) + String.valueOf(dobChar[3]));
		for(int i = 0 ; i < staffDay.getItemCount(); i++){
			if(staffDay.getItemAt(i).equals(day)){
				staffDay.setSelectedIndex(i);
			}
		}
		
		for(int i = 0 ; i < staffMonth.getItemCount(); i++){
			if(staffMonth.getItemAt(i).equals(month)){
				staffMonth.setSelectedIndex(i);
			}
		}
		
		for(int i = 0 ; i < staffYear.getItemCount(); i++){
			if(staffYear.getItemAt(i).equals(year)){
				staffYear.setSelectedIndex(i);
			}
		}
	}
	
	private void setElderlyBirthday(char[] dobChar){
		String day = (String.valueOf(dobChar[8]) + String.valueOf(dobChar[9]));
		String month = (String.valueOf(dobChar[5]) + String.valueOf(dobChar[6]));
		String year = (String.valueOf(dobChar[0]) + String.valueOf(dobChar[1]) + String.valueOf(dobChar[2]) + String.valueOf(dobChar[3]));
		for(int i = 0 ; i < elderlyDay.getItemCount(); i++){
			if(elderlyDay.getItemAt(i).equals(day)){
				elderlyDay.setSelectedIndex(i);
			}
		}
		
		for(int i = 0 ; i < elderlyMonth.getItemCount(); i++){
			if(elderlyMonth.getItemAt(i).equals(month)){
				elderlyMonth.setSelectedIndex(i);
			}
		}
		
		for(int i = 0 ; i < elderlyYear.getItemCount(); i++){
			if(elderlyYear.getItemAt(i).equals(year)){
				elderlyYear.setSelectedIndex(i);
			}
		}
	}
}
