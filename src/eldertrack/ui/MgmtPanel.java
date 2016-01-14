package eldertrack.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	private JTextField staffIdValue;
	private JTextField staffFirstNameValue;
	private JTextField staffLastNameValue;
	private JTextField elderlyRoomValue;
	private JTextField elderlyAddressValue;
	private JTextField staffDobValue;
	private JTextField setPasswordValue;
	private JTextField elderlySearchField;
	private JTextField staffSearchField;
	MgmtPanel() {
		
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		JPanel staffManagementPanel = new JPanel();
		staffManagementPanel.setBounds(511, 79, 448, 341);
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
		
		JLabel staffId = new JLabel("STAFF ID");
		staffId.setFont(new Font("Calibri", Font.PLAIN, 24));
		staffId.setBounds(19, 30, 123, 25);
		staffManagementPanel.add(staffId);
		
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
		
		staffIdValue = new JTextField();
		staffIdValue.setColumns(10);
		staffIdValue.setBounds(180, 30, 116, 22);
		staffManagementPanel.add(staffIdValue);
		
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
		staffAgeValue.setBounds(180, 150, 56, 16);
		staffManagementPanel.add(staffAgeValue);
		
		JLabel setPassword = new JLabel("SET PW");
		setPassword.setFont(new Font("Calibri", Font.PLAIN, 24));
		setPassword.setBounds(19, 180, 109, 25);
		staffManagementPanel.add(setPassword);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_1.setBounds(152, 180, 23, 25);
		staffManagementPanel.add(label_1);
		
		setPasswordValue = new JTextField();
		setPasswordValue.setColumns(10);
		setPasswordValue.setBounds(180, 180, 116, 22);
		staffManagementPanel.add(setPasswordValue);
		
		JPanel elderlyManagementPanel = new JPanel();
		elderlyManagementPanel.setBounds(511, 79, 448, 327);
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
		lblEManagementLbl = new JLabel("ElderTrack Management");
		lblEManagementLbl.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblEManagementLbl.setBounds(26, 5, 430, 61);
		add(lblEManagementLbl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 79, 470, 445);
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
		
		scrollPane.setViewportView(elderlyTable);
		
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
			
			JPanel panel_2 = new JPanel();
			panel_2.setVisible(true);
			panel_2.setBounds(511, 498, 453, 25);
			add(panel_2);
			panel_2.setLayout(null);
			
			JButton elderlySave = new JButton("Save Changes");
			elderlySave.setBounds(0, 0, 132, 25);
			panel_2.add(elderlySave);
			
			JButton elderlyDiscardChanges = new JButton("Discard Changes");
			elderlyDiscardChanges.setBounds(165, 0, 132, 25);
			panel_2.add(elderlyDiscardChanges);
			
			JButton elderlyRemove = new JButton("Remove Selected");
			elderlyRemove.setBounds(321, 0, 132, 25);
			panel_2.add(elderlyRemove);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(511, 498, 453, 25);
			add(panel_3);
			panel_3.setVisible(false);
			panel_3.setLayout(null);
			
			JButton staffSave = new JButton("Save Changes");
			staffSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			staffSave.setBounds(0, 0, 132, 25);
			panel_3.add(staffSave);
			
			JButton staffDiscardChanges = new JButton("Discard Changes");
			staffDiscardChanges.setBounds(165, 0, 132, 25);
			panel_3.add(staffDiscardChanges);
			
			JButton staffRemove = new JButton("Remove Selected");
			staffRemove.setBounds(321, 0, 132, 25);
			panel_3.add(staffRemove);
			
			JButton addElderly = new JButton("Add New Elderly");
			addElderly.setBounds(511, 458, 132, 25);
			add(addElderly);
			
			JButton clearFields = new JButton("Clear All Fields");
			clearFields.setBounds(832, 458, 132, 25);
			add(clearFields);
			
			JButton addStaff = new JButton("Add New Staff");
			addStaff.setBounds(511, 458, 132, 25);
			add(addStaff);
			
			JButton elderlySearchBtn = new JButton("Search");
			elderlySearchBtn.setBounds(156, 535, 73, 25);
			add(elderlySearchBtn);
			
			elderlySearchField = new JTextField();
			elderlySearchField.setBounds(30, 536, 120, 22);
			add(elderlySearchField);
			elderlySearchField.setColumns(10);
			
			JButton staffSearchBtn = new JButton("Search");
			staffSearchBtn.setBounds(156, 535, 73, 25);
			add(staffSearchBtn);
			
			staffSearchField = new JTextField();
			staffSearchField.setColumns(10);
			staffSearchField.setBounds(30, 536, 120, 22);
			add(staffSearchField);
			
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
			
			elderlyDiscardChanges.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "Are you sure you want to discard changes?");
				}
			}
			);
			
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
								
								PreparedStatement ps = so.getPreparedStatement("UPDATE et_elderly SET name=?, dob=?, nric=?, gender=?, room=?, address=? WHERE id=?");
								ps.setString(1, name);
								ps.setDate(2, java.sql.Date.valueOf(localDOB));
								ps.setString(3, nric);
								ps.setString(4, gender);
								ps.setString(5, room);
								ps.setString(6, address);
								ps.setInt(7, Integer.parseInt(id));
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "Update successfully completed!");
								
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
			
			tabbedPane.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    if (elderlyManagementPanel.isVisible()) {
			    	staffManagementPanel.setVisible(true);
			    	elderlyManagementPanel.setVisible(false);
			    	panel_3.setVisible(true);
			    	panel_2.setVisible(false);
			    	addElderly.setVisible(false);
			    	addStaff.setVisible(true);
			    	staffSearchBtn.setVisible(true);
			    	elderlySearchBtn.setVisible(false);
			    } else {
			    	staffManagementPanel.setVisible(false);
			    	elderlyManagementPanel.setVisible(true);
			    	panel_3.setVisible(false);
			    	panel_2.setVisible(true);
			    	addElderly.setVisible(true);
			    	addStaff.setVisible(false);
			    	staffSearchBtn.setVisible(false);
			    	elderlySearchBtn.setVisible(true);
			    }
			    }
			});
			
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
			
			addElderly.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add?");
					if (dialogResult == JOptionPane.YES_OPTION){
						try{
						PreparedStatement ps = so.getPreparedStatement("INSERT INTO et_elderly (name, dob, nric, gender, room, address) VALUES (?, ?, ?, ?, ?, ?)");
						String name = elderlyNameValue.getText();
						String birthString = elderlyDobValue.getText();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
						LocalDate dob = LocalDate.parse(birthString, formatter);
						String nric = elderlyNricValue.getText();
						String gender = elderlyGenderValue.getText();
						String room = elderlyRoomValue.getText();
						String  address = elderlyAddressValue.getText();
						
						ps.setString(1, name);
						ps.setDate(2, java.sql.Date.valueOf(dob));
						ps.setString(3,nric);
						ps.setString(4, gender);
						ps.setString(5, room);
						ps.setString(6, address);
						ps.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Person has successfully been added to database!");
						
						try{
							elderlyTable.setModel(ElderlyTableHelper.getElderlyFromQuery(""));
							setColumnWidths();
						}catch(Exception e4){
							e4.printStackTrace();
						}
						
						}catch(Exception e3){
							JOptionPane.showMessageDialog(null, e3);
						}
						
					}
				}
			});
			
			clearFields.addMouseListener(new MouseAdapter() {
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
					
					//Clear Staff Fields
					staffAgeValue.setText("");
					staffFirstNameValue.setText("");
					staffLastNameValue.setText("");
					staffDobValue.setText("");
					
				}
			});
			
	}
	private void setColumnWidths(){
		elderlyTable.getTableHeader().setResizingAllowed(false);
		elderlyTable.getTableHeader().setReorderingAllowed(false);
		elderlyTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		elderlyTable.getColumnModel().getColumn(0).setMaxWidth(35);
		elderlyTable.getColumnModel().getColumn(1).setPreferredWidth(135);
		elderlyTable.getColumnModel().getColumn(1).setMaxWidth(145);
		elderlyTable.getColumnModel().getColumn(2).setPreferredWidth(115);
		elderlyTable.getColumnModel().getColumn(2).setMaxWidth(125);
		elderlyTable.getColumnModel().getColumn(3).setPreferredWidth(55);
		elderlyTable.getColumnModel().getColumn(3).setMaxWidth(70);
	}
}
