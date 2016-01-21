package eldertrack.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.codec.digest.DigestUtils;

import eldertrack.db.SQLObject;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import eldertrack.management.*;
import eldertrack.misc.NRICUtils;
import javax.swing.JPasswordField;

public class MgmtPanel extends JPanel {
	private static SQLObject so = new SQLObject();
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblEManagementLbl;
	private JTable elderlyTable;
	private JTable staffTable;
	private JTextField elderlyNameValue;
	private JTextField elderlyDobValue;
	private JTextField elderlyNricValue;
	private JTextField elderlyGenderValue;
	private JTextField staffFirstNameValue;
	private JTextField staffLastNameValue;
	private JTextField elderlyRoomValue;
	private JTextField elderlyAddressValue;
	private JTextField staffDobValue;
	private JTextField elderlySearchField;
	private JTextField staffSearchField;
	private JTextField elderlyBedValue;
	private JTextField staffNricValue;
	private JTextField elderlyContactValue;
	private JPasswordField staffSetPasswordValue;
	private JTextField editableUsernameValue;
	MgmtPanel() {
		
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		JPanel staffManagementPanel = new JPanel();
		staffManagementPanel.setBounds(625, 79, 350, 340);
		add(staffManagementPanel);
		staffManagementPanel.setVisible(false);
		staffManagementPanel.setLayout(null);
		
		JLabel staffFirstName = new JLabel("FIRST NAME");
		staffFirstName.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffFirstName.setBounds(19, 60, 123, 25);
		staffManagementPanel.add(staffFirstName);
		
		JLabel staffLastName = new JLabel("LAST NAME");
		staffLastName.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffLastName.setBounds(19, 90, 124, 25);
		staffManagementPanel.add(staffLastName);
		
		JLabel staffId_1 = new JLabel("STAFF ID");
		staffId_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffId_1.setBounds(19, 30, 123, 25);
		staffManagementPanel.add(staffId_1);
		
		JLabel staffDob = new JLabel("DOB");
		staffDob.setBounds(19, 120, 109, 25);
		staffManagementPanel.add(staffDob);
		staffDob.setFont(new Font("Calibri", Font.PLAIN, 24));
		
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
		
		staffDobValue = new JTextField();
		staffDobValue.setBounds(180, 120, 116, 22);
		staffManagementPanel.add(staffDobValue);
		staffDobValue.setColumns(10);
		
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
		
		JLabel staffAgeValue = new JLabel("");
		staffAgeValue.setBounds(180, 150, 116, 22);
		staffManagementPanel.add(staffAgeValue);
		
		JLabel staffNric = new JLabel("NRIC");
		staffNric.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffNric.setBounds(19, 180, 109, 25);
		staffManagementPanel.add(staffNric);
		
		JLabel label_7 = new JLabel(":");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_7.setBounds(152, 180, 23, 25);
		staffManagementPanel.add(label_7);
		
		staffNricValue = new JTextField();
		staffNricValue.setColumns(10);
		staffNricValue.setBounds(180, 180, 116, 22);
		staffManagementPanel.add(staffNricValue);
		
		JLabel staffIdValue = new JLabel("");
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
		
		JLabel staffAccessLevelValue = new JLabel("");
		staffAccessLevelValue.setBounds(180, 210, 116, 22);
		staffManagementPanel.add(staffAccessLevelValue);
		
		JLabel staffUsername = new JLabel("USERNAME");
		staffUsername.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffUsername.setBounds(19, 270, 123, 25);
		staffManagementPanel.add(staffUsername);
		
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
		
		JLabel uneditableUsernameValue = new JLabel("");
		uneditableUsernameValue.setBounds(180, 270, 116, 22);
		uneditableUsernameValue.setVisible(true);
		staffManagementPanel.add(uneditableUsernameValue);
		
		JPanel elderlyManagementPanel = new JPanel();
		elderlyManagementPanel.setBounds(625, 79, 340, 327);
		add(elderlyManagementPanel);
		elderlyManagementPanel.setLayout(null);
		
		JLabel elderlyName = new JLabel("NAME");
		elderlyName.setBounds(19, 60, 109, 25);
		elderlyManagementPanel.add(elderlyName);
		elderlyName.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		JLabel elderlyDob = new JLabel("DOB");
		elderlyDob.setBounds(18, 90, 109, 25);
		elderlyManagementPanel.add(elderlyDob);
		elderlyDob.setFont(new Font("Calibri", Font.PLAIN, 24));
		
			JLabel elderlyId = new JLabel("ELDER ID");
			elderlyId.setBounds(19, 30, 109, 25);
			elderlyManagementPanel.add(elderlyId);
			elderlyId.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel elderlyNric = new JLabel("NRIC");
			elderlyNric.setBounds(18, 120, 109, 25);
			elderlyManagementPanel.add(elderlyNric);
			elderlyNric.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel elderlyGender = new JLabel("GENDER");
			elderlyGender.setBounds(19, 150, 121, 25);
			elderlyManagementPanel.add(elderlyGender);
			elderlyGender.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_3 = new JLabel(":");
			label_3.setBounds(152, 30, 23, 25);
			elderlyManagementPanel.add(label_3);
			label_3.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_4 = new JLabel(":");
			label_4.setBounds(152, 60, 23, 25);
			elderlyManagementPanel.add(label_4);
			label_4.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_5 = new JLabel(":");
			label_5.setBounds(152, 90, 23, 25);
			elderlyManagementPanel.add(label_5);
			label_5.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_6 = new JLabel(":");
			label_6.setBounds(153, 120, 23, 25);
			elderlyManagementPanel.add(label_6);
			label_6.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_8 = new JLabel(":");
			label_8.setBounds(153, 150, 23, 25);
			elderlyManagementPanel.add(label_8);
			label_8.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			elderlyNameValue = new JTextField();
			elderlyNameValue.setBounds(180, 60, 116, 22);
			elderlyManagementPanel.add(elderlyNameValue);
			elderlyNameValue.setColumns(10);
			
			elderlyDobValue = new JTextField();
			elderlyDobValue.setBounds(180, 90, 116, 22);
			elderlyManagementPanel.add(elderlyDobValue);
			elderlyDobValue.setColumns(10);
			
			elderlyNricValue = new JTextField();
			elderlyNricValue.setBounds(180, 120, 116, 22);
			elderlyManagementPanel.add(elderlyNricValue);
			elderlyNricValue.setColumns(10);
			
			elderlyGenderValue = new JTextField();
			elderlyGenderValue.setBounds(179, 150, 116, 22);
			elderlyManagementPanel.add(elderlyGenderValue);
			elderlyGenderValue.setColumns(10);
			
			JLabel elderlyAge = new JLabel("AGE");
			elderlyAge.setFont(new Font("Calibri", Font.PLAIN, 24));
			elderlyAge.setBounds(19, 180, 121, 25);
			elderlyManagementPanel.add(elderlyAge);
			
			JLabel label_16 = new JLabel(":");
			label_16.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_16.setBounds(152, 180, 23, 25);
			elderlyManagementPanel.add(label_16);
			
			JLabel elderlyRoom = new JLabel("ROOM");
			elderlyRoom.setFont(new Font("Calibri", Font.PLAIN, 24));
			elderlyRoom.setBounds(19, 210, 121, 25);
			elderlyManagementPanel.add(elderlyRoom);
			
			JLabel label_18 = new JLabel(":");
			label_18.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_18.setBounds(152, 210, 23, 25);
			elderlyManagementPanel.add(label_18);
			
			elderlyRoomValue = new JTextField();
			elderlyRoomValue.setColumns(10);
			elderlyRoomValue.setBounds(179, 210, 116, 22);
			elderlyManagementPanel.add(elderlyRoomValue);
			
			JLabel elderlyAddress = new JLabel("ADDRESS");
			elderlyAddress.setFont(new Font("Calibri", Font.PLAIN, 24));
			elderlyAddress.setBounds(19, 240, 121, 25);
			elderlyManagementPanel.add(elderlyAddress);
			
			JLabel label_20 = new JLabel(":");
			label_20.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_20.setBounds(152, 240, 23, 25);
			elderlyManagementPanel.add(label_20);
			
			elderlyAddressValue = new JTextField();
			elderlyAddressValue.setColumns(10);
			elderlyAddressValue.setBounds(179, 240, 116, 22);
			elderlyManagementPanel.add(elderlyAddressValue);
			
			JLabel elderlyIdValue = new JLabel("");
			elderlyIdValue.setBounds(180, 30, 56, 16);
			elderlyManagementPanel.add(elderlyIdValue);
			
			JLabel elderlyAgeValue = new JLabel("");
			elderlyAgeValue.setBounds(180, 180, 56, 16);
			elderlyManagementPanel.add(elderlyAgeValue);
			
			JLabel elderlyBed = new JLabel("BED NO");
			elderlyBed.setFont(new Font("Calibri", Font.PLAIN, 24));
			elderlyBed.setBounds(19, 270, 121, 25);
			elderlyManagementPanel.add(elderlyBed);
			
			JLabel label_2 = new JLabel(":");
			label_2.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_2.setBounds(152, 270, 23, 25);
			elderlyManagementPanel.add(label_2);
			
			elderlyBedValue = new JTextField();
			elderlyBedValue.setColumns(10);
			elderlyBedValue.setBounds(179, 270, 116, 22);
			elderlyManagementPanel.add(elderlyBedValue);
			
			JLabel elderlyContact = new JLabel("N.O.K. NO");
			elderlyContact.setFont(new Font("Calibri", Font.PLAIN, 24));
			elderlyContact.setBounds(19, 300, 121, 25);
			elderlyManagementPanel.add(elderlyContact);
			
			JLabel label_9 = new JLabel(":");
			label_9.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_9.setBounds(152, 300, 23, 25);
			elderlyManagementPanel.add(label_9);
			
			elderlyContactValue = new JTextField();
			elderlyContactValue.setColumns(10);
			elderlyContactValue.setBounds(179, 300, 116, 22);
			elderlyManagementPanel.add(elderlyContactValue);
		lblEManagementLbl = new JLabel("ElderTrack Management");
		lblEManagementLbl.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblEManagementLbl.setBounds(26, 5, 430, 61);
		add(lblEManagementLbl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 79, 584, 445);
		add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Elderly", null, scrollPane, null);
		
		
		DefaultTableModel allEldersData;
		try {
			allEldersData = ElderlyTableHelper.getElderlyFromQuery("");
			elderlyTable = new JTable(allEldersData);
			setColumnWidths();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane scrollPane2 = new JScrollPane();
		tabbedPane.addTab("Staff", null, scrollPane2, null);
		
		DefaultTableModel allStaffData;
		try {
			allStaffData = StaffTableHelper.getStaffFromQuery("");
			staffTable = new JTable(allStaffData);
			setColumnWidths();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		scrollPane2.setViewportView(staffTable);
		
		scrollPane.setViewportView(elderlyTable);
		
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
						
						String add3 = rs.getString("dob");
						elderlyDobValue.setText(add3);
						
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
						
						//Calculation of age from date of birth
						Calendar birthdate = Calendar.getInstance();
						birthdate.setTime(rs.getDate("dob"));
						Calendar today = Calendar.getInstance();
						int years = today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);  
						if (today.get(Calendar.MONTH) < birthdate.get(Calendar.MONTH)) {
							  years--;  
							} else if (today.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)
							    && today.get(Calendar.DAY_OF_MONTH) < birthdate.get(Calendar.DAY_OF_MONTH)) {
							  years--;  
							}
						elderlyAgeValue.setText(Integer.toString(years));
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
			
			// Add this to each panel
			JButton btnMainMenu = new JButton("Back to Main Menu");
			btnMainMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
					parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
				}
			});
			btnMainMenu.setBounds(820, 15, 139, 40);
			add(btnMainMenu);
			
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
			
			
			addStaff.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(addStaff.isVisible() == true){
						confirmAddStaff.setVisible(true);
						addStaff.setVisible(false);
						staffSave.setVisible(false);
						uneditableUsernameValue.setVisible(false);
						editableUsernameValue.setVisible(true);
						cancelAddStaff.setVisible(true);
						
						staffAgeValue.setText("");
						staffFirstNameValue.setText("");
						staffLastNameValue.setText("");
						staffDobValue.setText("");
						staffIdValue.setText("");
						staffAgeValue.setText("");
						staffNricValue.setText("");
						staffAccessLevelValue.setText("");
						uneditableUsernameValue.setText("");
						editableUsernameValue.setText("");
						staffSetPasswordValue.setText("");
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
						
						elderlyAgeValue.setText("");
						elderlyNameValue.setText("");
						elderlyIdValue.setText("");
						elderlyNricValue.setText("");
						elderlyAddressValue.setText("");
						elderlyRoomValue.setText("");
						elderlyGenderValue.setText("");
						elderlyDobValue.setText("");
						elderlyBedValue.setText("");
						elderlyContactValue.setText("");
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
						String birthString = elderlyDobValue.getText();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
						LocalDate dob = LocalDate.parse(birthString, formatter);
						String nric = elderlyNricValue.getText();
						String gender = elderlyGenderValue.getText();
						String room = elderlyRoomValue.getText();
						String  address = elderlyAddressValue.getText();
						String bedString = elderlyBedValue.getText();
						String contact = elderlyContactValue.getText();
						int bed = Integer.parseInt(bedString);
						
						PreparedStatement ps1 = so.getPreparedStatement("SELECT bed, nric FROM et_elderly WHERE room=?");
						ps1.setString(1, room);
						ResultSet check = ps1.executeQuery();
						
						//Check for duplicate beds and exceeding bed limit
						boolean dupeBed = checkDuplicateBeds(bed, check);
						boolean dupeNric = checkDuplicateNrics(nric, check);
						boolean validNric = NRICUtils.validate(nric);
						boolean validPhoneNo = checkValidPhoneNo(contact);
					
						if(dupeBed == true){
							JOptionPane.showMessageDialog(null,"There are duplicate beds! Please check your entries!");
						}else if(bed > 10){
							JOptionPane.showMessageDialog(null,"The bed number cannot exceed 10!");
						}else if(dupeNric == true){
							JOptionPane.showMessageDialog(null, "There are duplicate NRICs! Please check your entries!");
						}else if(validNric == false){
							JOptionPane.showMessageDialog(null, "The NRIC entered is not valid! Please check your entry!");
						}else if(validPhoneNo == false){
							JOptionPane.showConfirmDialog(null, "Please check your entries again!");
						}
						else{
						PreparedStatement ps = so.getPreparedStatement("INSERT INTO et_elderly (name, dob, nric, gender, room, address, bed, contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
						ps.setString(1, name);
						ps.setDate(2, java.sql.Date.valueOf(dob));
						ps.setString(3,nric);
						ps.setString(4, gender);
						ps.setString(5, room);
						ps.setString(6, address);
						ps.setInt(7, bed);
						ps.setString(8, contact);
						ps.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Person has successfully been added to database!");
						}
						try{
							elderlyTable.setModel(ElderlyTableHelper.getElderlyFromQuery(""));
							setColumnWidths();
						}catch(Exception e4){
							e4.printStackTrace();
						}
						
						}catch(Exception e3){
							JOptionPane.showMessageDialog(null, e3);
						}
						
						if(addElderly.isVisible() == false){
							confirmAddElderly.setVisible(false);
							addElderly.setVisible(true);
							elderlySave.setVisible(true);
							cancelAddElderly.setVisible(false);
						}
						
						elderlyAgeValue.setText("");
						elderlyNameValue.setText("");
						elderlyIdValue.setText("");
						elderlyNricValue.setText("");
						elderlyAddressValue.setText("");
						elderlyRoomValue.setText("");
						elderlyGenderValue.setText("");
						elderlyDobValue.setText("");
						elderlyBedValue.setText("");
						elderlyContactValue.setText("");
						
					}
				}
			});
			staffSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			
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
						
						staffAgeValue.setText("");
						staffFirstNameValue.setText("");
						staffLastNameValue.setText("");
						staffDobValue.setText("");
						staffIdValue.setText("");
						staffAgeValue.setText("");
						staffNricValue.setText("");
						staffAccessLevelValue.setText("");
						uneditableUsernameValue.setText("");
						editableUsernameValue.setText("");
						staffSetPasswordValue.setText("");
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
						
						elderlyAgeValue.setText("");
						elderlyNameValue.setText("");
						elderlyIdValue.setText("");
						elderlyNricValue.setText("");
						elderlyAddressValue.setText("");
						elderlyRoomValue.setText("");
						elderlyGenderValue.setText("");
						elderlyDobValue.setText("");
						elderlyBedValue.setText("");
						elderlyContactValue.setText("");
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
							String staffBirthString = staffDobValue.getText();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
							LocalDate staffDob = LocalDate.parse(staffBirthString, formatter);
							String staffUsername = editableUsernameValue.getText();
							char[] pwCharArr = staffSetPasswordValue.getPassword();
							String password = DigestUtils.sha512Hex(new String(pwCharArr));
							
							PreparedStatement ps = so.getPreparedStatement("SELECT * FROM et_staff");
							ResultSet rs = ps.executeQuery();
							
							boolean dupeNric = checkDuplicateNrics(staffNric,rs);
							boolean validNric = NRICUtils.validate(staffNric);
							
							if(dupeNric == true){
								JOptionPane.showMessageDialog(null,"There are duplicate NRICS! Please check your entries!");
							}else if(validNric == false){
								JOptionPane.showMessageDialog(null, "That is not a valid NRIC! Please check your entry!");
							}else{
								PreparedStatement ps1 = so.getPreparedStatement("INSERT INTO et_staff (username, firstname, lastname, nric, dob) VALUES (?, ?, ?, ?, ?)");
								ps1.setString(1, staffUsername);
								ps1.setString(2, staffFirstName);
								ps1.setString(3, staffLastName);
								ps1.setString(4, staffNric);
								ps1.setDate(5, java.sql.Date.valueOf(staffDob));
								ps1.executeUpdate();
								JOptionPane.showMessageDialog(null,"New staff has been successfully added!");
							}
							
						}catch(SQLException e){
							e.printStackTrace();
						}
						
						if(confirmAddStaff.isVisible() == true){
							confirmAddStaff.setVisible(false);
							addStaff.setVisible(true);
							staffSave.setVisible(true);
							uneditableUsernameValue.setVisible(true);
							editableUsernameValue.setVisible(false);
							cancelAddStaff.setVisible(false);
							
							staffAgeValue.setText("");
							staffFirstNameValue.setText("");
							staffLastNameValue.setText("");
							staffDobValue.setText("");
							staffIdValue.setText("");
							staffAgeValue.setText("");
							staffNricValue.setText("");
							staffAccessLevelValue.setText("");
							uneditableUsernameValue.setText("");
							editableUsernameValue.setText("");
							staffSetPasswordValue.setText("");
						}
					
					}
				}
			});
			
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
									String birthString = staffDobValue.getText();
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
									LocalDate dob = LocalDate.parse(birthString, formatter);
									
									PreparedStatement ps = so.getPreparedStatement("SELECT nric FROM et_staff WHERE staffid NOT IN(?)");
									ps.setString(1, staffId);
									ResultSet check = ps.executeQuery();
									
									boolean dupeNric = checkDuplicateNrics(nric, check);
									boolean validNric = NRICUtils.validate(nric);
									
									if(dupeNric == true){
										JOptionPane.showMessageDialog(null, "There are duplicate NRICS! Please check your entries");
									}else if (validNric == false){
										JOptionPane.showMessageDialog(null, "That is not a valid NRIC! Please check your entry!");
									}else{
										PreparedStatement ps1 = so.getPreparedStatement("UPDATE et_staff SET firstname=?, lastname=?, dob=?, nric=? WHERE staffid=?");
										ps1.setString(1, firstName);
										ps1.setString(2, lastName);
										ps1.setDate(3, java.sql.Date.valueOf(dob));
										ps1.setString(4, nric);
										ps1.setString(5, staffId);
										ps1.executeUpdate();
										JOptionPane.showMessageDialog(null, "Data has been succesfully saved!");
										
										try{
											staffTable.setModel(StaffTableHelper.getStaffFromQuery(""));
											setColumnWidths();
										}catch(SQLException e){
											e.printStackTrace();
										}
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
								String birthString = elderlyDobValue.getText();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
								LocalDate localDOB = LocalDate.parse(birthString, formatter);
								String nric = elderlyNricValue.getText();
								String gender = elderlyGenderValue.getText();
								String room = elderlyRoomValue.getText();
								String address = elderlyAddressValue.getText();
								String bed = elderlyBedValue.getText();
								String contact = elderlyContactValue.getText();
								
								PreparedStatement ps1 = so.getPreparedStatement("SELECT bed, nric FROM et_elderly WHERE room=? AND id NOT IN (?)");
								ps1.setString(1, room);
								ps1.setString(2, id);
								ResultSet check = ps1.executeQuery();
								
								//Check for duplicate beds and exceeding bed limit
								boolean dupeBed = checkDuplicateBeds(Integer.parseInt(bed), check);
								boolean dupeNric = checkDuplicateNrics(nric, check);
								boolean validNric = NRICUtils.validate(nric);
								boolean validPhoneNo = checkValidPhoneNo(contact);
								
								if(dupeBed == true){
									JOptionPane.showMessageDialog(null, "There are duplicate bed numbers! Please check your entries!");
								}else if(Integer.parseInt(bed) > 10){
									JOptionPane.showMessageDialog(null, "The bed number cannot exceed 10!");
								}else if(dupeNric == true){
									JOptionPane.showMessageDialog(null, "There are duplicate NRICS! Please check your entries");
								}else if(validNric == false){
									JOptionPane.showMessageDialog(null, "The NRIC entered is not valid! Please check your entry!");
								}else if(validPhoneNo == false){
									JOptionPane.showMessageDialog(null, "Please check your entries again!");
								}
								else{
									PreparedStatement ps = so.getPreparedStatement("UPDATE et_elderly SET name=?, dob=?, nric=?, gender=?, room=?, bed=?, contact=?, address=? WHERE id=?");
									ps.setString(1, name);
									ps.setDate(2, java.sql.Date.valueOf(localDOB));
									ps.setString(3, nric);
									ps.setString(4, gender);
									ps.setString(5, room);
									ps.setInt(6, Integer.parseInt(bed));
									ps.setString(7, contact);
									ps.setString(8, address);
									ps.setInt(9, Integer.parseInt(id));
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null, "Update successfully completed!");	
								}	
																	
								try{
									elderlyTable.setModel(ElderlyTableHelper.getElderlyFromQuery(""));
									setColumnWidths();
								}catch(Exception e4){
									e4.printStackTrace();
								}
								
							}catch(Exception e1){
								JOptionPane.showMessageDialog(null,e1);	
							}
						}
							
				}
			});
			
				elderlyRemove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected person?");
						if (dialogResult == JOptionPane.YES_OPTION){
							try{
								PreparedStatement ps1 = so.getPreparedStatement("DELETE FROM et_elderly WHERE id=?");
								int id = Integer.parseInt(elderlyIdValue.getText());
								ps1.setInt(1, id);
								ps1.executeUpdate();
								JOptionPane.showMessageDialog(null, "Removal sucessfully completed!");
								
								try{
									elderlyTable.setModel(ElderlyTableHelper.getElderlyFromQuery(""));
									setColumnWidths();
								}catch(Exception e4){
									e4.printStackTrace();
								}
								
								
								
							}catch(Exception e2){
								JOptionPane.showMessageDialog(null,e2);	
							}
						}
					}
				});
			
			elderlySearchBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						elderlyTable.setModel(ElderlyTableHelper.getElderlyFromQuery("%" + elderlySearchField.getText() + "%"));
						setColumnWidths();
					} catch (SQLException e1) {
						e1.printStackTrace();
					};
				}
			});
			
			staffSearchBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						staffTable.setModel(StaffTableHelper.getStaffFromQuery("%" + staffSearchField.getText() + "%"));
						setColumnWidths();
					} catch (SQLException e1) {
						e1.printStackTrace();
					};
				}
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
			    } else {
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
							
							String add4 = rs1.getString("dob");
							staffDobValue.setText(add4);
							
							String add5 = rs1.getString("nric");
							staffNricValue.setText(add5);
							
							String add6 = rs1.getString("accesslevel");
							staffAccessLevelValue.setText(add6);
							
							String add7 = rs1.getString("username");
							uneditableUsernameValue.setText(add7);
							
							Calendar birthdate = Calendar.getInstance();
							birthdate.setTime(rs1.getDate("dob"));
							Calendar today = Calendar.getInstance();
							int years = today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);  
							if (today.get(Calendar.MONTH) < birthdate.get(Calendar.MONTH)) {
								  years--;  
								} else if (today.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)
								    && today.get(Calendar.DAY_OF_MONTH) < birthdate.get(Calendar.DAY_OF_MONTH)) {
								  years--;  
								}
							staffAgeValue.setText(Integer.toString(years));
							
						}
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			
			discardChanges.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//Clear Elderly Fields
					elderlyAgeValue.setText("");
					elderlyNameValue.setText("");
					elderlyIdValue.setText("");
					elderlyNricValue.setText("");
					elderlyAddressValue.setText("");
					elderlyRoomValue.setText("");
					elderlyGenderValue.setText("");
					elderlyDobValue.setText("");
					elderlyBedValue.setText("");
					elderlyContactValue.setText("");
					
					//Clear Staff Fields
					staffAgeValue.setText("");
					staffFirstNameValue.setText("");
					staffLastNameValue.setText("");
					staffDobValue.setText("");
					staffIdValue.setText("");
					staffAgeValue.setText("");
					staffNricValue.setText("");
					staffAccessLevelValue.setText("");
					uneditableUsernameValue.setText("");
					editableUsernameValue.setText("");
					staffSetPasswordValue.setText("");
					
				}
			});
			
			staffRemove.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected staff member?");
					if(dialogResult == JOptionPane.YES_OPTION){
					try{
						PreparedStatement ps = so.getPreparedStatement("DELETE FROM et_staff WHERE staffid=?");
						int staffid = Integer.parseInt(staffIdValue.getText());
						ps.setInt(1, staffid);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Staff member has been sucessfully removed!");
					}catch(SQLException e){
						e.printStackTrace();
					}
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
	
	private boolean checkDuplicateBeds(int bed, ResultSet rs){
		boolean result = false;
		ResultSet check = rs;
		
		
		try {
			while(check.next()){
				if(check.getInt("bed") == bed){
					result = true;
					break;
				}else
					result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean checkDuplicateNrics(String nric, ResultSet rs){
		boolean result = false;
		ResultSet check = rs;
		
		try{
			while(check.next()){
				if(check.getString("nric") == nric){
					result = true;
					break;
				}else
					result = false;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		
		return result;
		
		}
	
	private boolean checkValidPhoneNo(String no){
			if(Character.getNumericValue(no.charAt(0)) != 6 && Character.getNumericValue(no.charAt(0)) != 8 && Character.getNumericValue(no.charAt(0)) != 9){
				JOptionPane.showMessageDialog(null, "The phone number is invalid!");
				return false;
			}else if(no.length() != 8){
				JOptionPane.showMessageDialog(null, "The phone number contains more than 8 numbers!");
				return false;
			}else
				return true;
		}
	}
