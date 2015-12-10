package eldertrack.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import eldertrack.db.SQLConnect;
import eldertrack.diet.TableHelper;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import eldertrack.management.*;

public class MgmtPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblEManagementLbl;
	private JTable elderlyTable;
	private JTable staffTable;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	MgmtPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblEManagementLbl = new JLabel("ElderTrack Management");
		lblEManagementLbl.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblEManagementLbl.setBounds(26, 5, 430, 61);
		add(lblEManagementLbl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 79, 415, 445);
		add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Elderly", null, scrollPane, null);
		
		DefaultTableModel allEldersData;
		try {
			allEldersData = ElderlyTableHelper.getElderlyFromQuery("");
			elderlyTable = new JTable(allEldersData);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		scrollPane2.setViewportView(staffTable);
		
		JButton button = new JButton("Save Changes");
		
		button.setBounds(474, 499, 132, 25);
		add(button);
		
		JButton button_1 = new JButton("Discard Changes");

		button_1.setBounds(638, 499, 132, 25);
		add(button_1);
		
		JButton button_2 = new JButton("Remove Selected");
		button_2.setBounds(795, 499, 132, 25);
		add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(479, 79, 448, 396);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("NAME");
		label.setBounds(19, 64, 109, 25);
		panel.add(label);
		label.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		JLabel label_1 = new JLabel("DOB");
		label_1.setBounds(18, 102, 109, 25);
		panel.add(label_1);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setBounds(19, 27, 109, 25);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_2 = new JLabel("NRIC");
			label_2.setBounds(18, 141, 109, 25);
			panel.add(label_2);
			label_2.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_7 = new JLabel("GENDER");
			label_7.setBounds(19, 179, 121, 25);
			panel.add(label_7);
			label_7.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_3 = new JLabel(":");
			label_3.setBounds(152, 29, 23, 25);
			panel.add(label_3);
			label_3.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_4 = new JLabel(":");
			label_4.setBounds(152, 66, 23, 25);
			panel.add(label_4);
			label_4.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_5 = new JLabel(":");
			label_5.setBounds(152, 103, 23, 25);
			panel.add(label_5);
			label_5.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_6 = new JLabel(":");
			label_6.setBounds(153, 141, 23, 25);
			panel.add(label_6);
			label_6.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_8 = new JLabel(":");
			label_8.setBounds(153, 179, 23, 25);
			panel.add(label_8);
			label_8.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			textField = new JTextField();
			textField.setBounds(179, 29, 116, 22);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setBounds(180, 67, 116, 22);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setBounds(180, 105, 116, 22);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setBounds(180, 143, 116, 22);
			panel.add(textField_3);
			textField_3.setColumns(10);
			
			textField_4 = new JTextField();
			textField_4.setBounds(179, 181, 116, 22);
			panel.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel label_15 = new JLabel("AGE");
			label_15.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_15.setBounds(19, 212, 121, 25);
			panel.add(label_15);
			
			JLabel label_16 = new JLabel(":");
			label_16.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_16.setBounds(152, 212, 23, 25);
			panel.add(label_16);
			
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(179, 214, 116, 22);
			panel.add(textField_8);
			
			JLabel label_17 = new JLabel("ROOM");
			label_17.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_17.setBounds(19, 247, 121, 25);
			panel.add(label_17);
			
			JLabel label_18 = new JLabel(":");
			label_18.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_18.setBounds(152, 247, 23, 25);
			panel.add(label_18);
			
			textField_9 = new JTextField();
			textField_9.setColumns(10);
			textField_9.setBounds(179, 249, 116, 22);
			panel.add(textField_9);
			
			JLabel label_19 = new JLabel("ADDRESS");
			label_19.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_19.setBounds(19, 282, 121, 25);
			panel.add(label_19);
			
			JLabel label_20 = new JLabel(":");
			label_20.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_20.setBounds(152, 282, 23, 25);
			panel.add(label_20);
			
			textField_10 = new JTextField();
			textField_10.setColumns(10);
			textField_10.setBounds(179, 284, 116, 22);
			panel.add(textField_10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(479, 79, 448, 396);
			add(panel_1);
			panel_1.setVisible(false);
			panel_1.setLayout(null);
			
			JLabel label_9 = new JLabel("FIRST NAME");
			label_9.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_9.setBounds(19, 64, 123, 25);
			panel_1.add(label_9);
			
			JLabel label_10 = new JLabel("LAST NAME");
			label_10.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_10.setBounds(18, 102, 124, 25);
			panel_1.add(label_10);
			
			JLabel label_11 = new JLabel("STAFF ID");
			label_11.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_11.setBounds(19, 27, 123, 25);
			panel_1.add(label_11);
			
			JLabel label_12 = new JLabel(":");
			label_12.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_12.setBounds(152, 27, 23, 25);
			panel_1.add(label_12);
			
			JLabel label_13 = new JLabel(":");
			label_13.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_13.setBounds(152, 66, 23, 25);
			panel_1.add(label_13);
			
			JLabel label_14 = new JLabel(":");
			label_14.setFont(new Font("Calibri", Font.PLAIN, 24));
			label_14.setBounds(152, 103, 23, 25);
			panel_1.add(label_14);
			
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(180, 29, 116, 22);
			panel_1.add(textField_5);
			
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(180, 67, 116, 22);
			panel_1.add(textField_6);
			
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(180, 105, 116, 22);
			panel_1.add(textField_7);
			
			JButton btnNewButton = new JButton("Back");
			btnNewButton.setBounds(29, 569, 97, 25);
			add(btnNewButton);
			
			//Event Listeners
			tabbedPane.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    if (panel.isVisible()) {
			    	panel_1.setVisible(true);
			    	panel.setVisible(false);
			    } else {
			    	panel_1.setVisible(false);
			    	panel.setVisible(true);
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
						ResultSet rs = SQLConnect.getResultSet(sql, table_clicked);
						
						while(rs.next()){
							String add1 = rs.getString("id");
							textField.setText(add1);
							
							String add2 = rs.getString("name");
							textField_1.setText(add2);
							
							String add3 = rs.getString("dob");
							textField_2.setText(add3);
							
							String add4 = rs.getString("nric");
							textField_3.setText(add4);
							
							String add5 = rs.getString("gender");
							textField_4.setText(add5);
							
							String add6 = rs.getString("age");
							textField_8.setText(add6);
							
							String add7 = rs.getString("room");
							textField_9.setText(add7);
							
							String add8 = rs.getString("address");
							textField_10.setText(add8);
							
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
						ResultSet rs1 = SQLConnect.getResultSet(sql1, table_clicked1);
						
						while(rs1.next()){
							String add1 = rs1.getString("staffid");
							textField_5.setText(add1);
							
							String add2 = rs1.getString("firstname");
							textField_6.setText(add2);
							
							String add3 = rs1.getString("lastname");
							textField_7.setText(add3);
						}
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane pane = new JOptionPane();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the data?");
				}
			});
			
			button_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane pane = new JOptionPane();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard changes?");
				}
			});
			
			button_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane pane = new JOptionPane();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected person?");
				}
			});
	}
}
