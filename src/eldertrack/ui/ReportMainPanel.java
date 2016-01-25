package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;
import eldertrack.medical.CheckUpObject;
import eldertrack.misc.TableHelper;
import eldertrack.report.CreatePdf;
import eldertrack.report.MedicalData;
import eldertrack.report.SendEmails;

public class ReportMainPanel extends JPanel {
	private static SQLObject so = new SQLObject();
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblReportLabel;
	JLabel lblSelectElderly;
	private JTable elderDataTable;
	private JTextField searchField;
	private JButton btnSearch;

	CardLayout parentCards;

	Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
	
	// Constructor
	ReportMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(elderDataTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);

		DefaultTableModel allEldersData;
		allEldersData = TableHelper.getElderlyBasic("");
		elderDataTable = new JTable(allEldersData);
		elderDataTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(elderDataTable);
		
		MedicalData elderList = new MedicalData();
		
		lblReportLabel = new JLabel("ElderTrack Report Generation");
		lblReportLabel.setForeground(SystemColor.textHighlight);
		lblReportLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblReportLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select An Elderly");
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSelectElderly.setBounds(10, 57, 290, 31);
		
		add(lblReportLabel);
		add(lblSelectElderly);
		
		searchField = new JTextField();
		searchField.setBounds(56, 97, 165, 25);
		add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elderDataTable.setModel(TableHelper.getElderlyBasic("%" + searchField.getText() + "%"));
				elderDataTable.getColumnModel().getColumn(0).setPreferredWidth(36);
			}
		});
		btnSearch.setBounds(228, 99, 65, 23);
		add(btnSearch);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 97, 47, 22);
		add(lblSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(310, 97, 665, 468);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblOverview = new JLabel("Report Overview");
		lblOverview.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblOverview.setBounds(310, 59, 665, 31);
		add(lblOverview);
		
		//
		JLabel lblName = new JLabel("Elderly Name: ");
		lblName.setForeground(new Color(0, 128, 128));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(10, 11, 156, 37);
		panel.add(lblName);
		
		JLabel lblInfoName = new JLabel("");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(163, 11, 219, 37);
		panel.add(lblInfoName);
		//
		JLabel lblId = new JLabel("ElderID: ");
		lblId.setBounds(10, 55, 55, 14);
		panel.add(lblId);
		
		JLabel lblElderid = new JLabel("");
		lblElderid.setBounds(59, 54, 257, 16);
		panel.add(lblElderid);
		//
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(10, 95, 31, 14);
		panel.add(lblAge);
		
		JLabel lblElderAge = new JLabel("");
		lblElderAge.setBounds(42, 94, 257, 16);
		panel.add(lblElderAge);
		//
		JLabel lblRmNum = new JLabel("Room Number: ");
		lblRmNum.setBounds(10, 75, 96, 14);
		panel.add(lblRmNum);
		
		JLabel lblRoomNumber = new JLabel("");
		lblRoomNumber.setBounds(102, 75, 257, 16);
		panel.add(lblRoomNumber);
		//
		JLabel lblNric = new JLabel("NRIC: ");
		lblNric.setBounds(10, 115, 37, 14);
		panel.add(lblNric);
		
		JLabel lblNRIC = new JLabel("");
		lblNRIC.setBounds(52, 114, 257, 16);
		panel.add(lblNRIC);
		//
		JSeparator separator = new JSeparator();
		separator.setBounds(3, 139, 659, 3);
		panel.add(separator);
		
		JLabel lblReportForThisMonth = new JLabel("Report for: " +ft.format(dNow));
		lblReportForThisMonth.setForeground(new Color(0, 128, 128));
		lblReportForThisMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReportForThisMonth.setBounds(10, 145, 358, 29);
		panel.add(lblReportForThisMonth);
		
		JLabel lblMedicalHistoryaverage = new JLabel("Medical History (Average of past month)");
		lblMedicalHistoryaverage.setForeground(new Color(0, 128, 128));
		lblMedicalHistoryaverage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMedicalHistoryaverage.setBounds(10, 171, 285, 29);
		panel.add(lblMedicalHistoryaverage);
		
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(10, 203, 96, 14);
		panel.add(lblTemperature);
		
		JLabel lblBloodPressure = new JLabel("Blood Pressure:");
		lblBloodPressure.setBounds(10, 223, 99, 14);
		panel.add(lblBloodPressure);
		
		JLabel lblHeartRate = new JLabel("Heart Rate:");
		lblHeartRate.setBounds(10, 243, 96, 14);
		panel.add(lblHeartRate);
		
		JLabel lblSugarLevel = new JLabel("Sugar Level:");
		lblSugarLevel.setBounds(10, 263, 96, 14);
		panel.add(lblSugarLevel);
		
		JLabel lblEyeInfect = new JLabel("Eye Infection: ");
		lblEyeInfect.setBounds(10, 347, 96, 14);
		panel.add(lblEyeInfect);
		
		JLabel lblEarInfect = new JLabel("Ear Infection: ");
		lblEarInfect.setBounds(10, 367, 96, 14);
		panel.add(lblEarInfect);
		
		JTextArea txtrTemp = new JTextArea();
		txtrTemp.setText("");
		txtrTemp.setEditable(false);
		txtrTemp.setWrapStyleWord(true);
		txtrTemp.setLineWrap(true);
		txtrTemp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrTemp.setBackground(SystemColor.menu);
		txtrTemp.setBounds(121, 199, 178, 18);
		panel.add(txtrTemp);
		
		JTextArea txtrBP = new JTextArea();
		txtrBP.setText("");
		txtrBP.setEditable(false);
		txtrBP.setWrapStyleWord(true);
		txtrBP.setLineWrap(true);
		txtrBP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrBP.setBackground(SystemColor.menu);
		txtrBP.setBounds(121, 219, 178, 18);
		panel.add(txtrBP);
		
		JTextArea txtrHeartRate = new JTextArea();
		txtrHeartRate.setText("");
		txtrHeartRate.setEditable(false);
		txtrHeartRate.setWrapStyleWord(true);
		txtrHeartRate.setLineWrap(true);
		txtrHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrHeartRate.setBackground(SystemColor.menu);
		txtrHeartRate.setBounds(121, 239, 178, 18);
		panel.add(txtrHeartRate);		
		
		JTextArea txtrSugarLevel = new JTextArea();
		txtrSugarLevel.setText("");
		txtrSugarLevel.setWrapStyleWord(true);
		txtrSugarLevel.setLineWrap(true);
		txtrSugarLevel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrSugarLevel.setEditable(false);
		txtrSugarLevel.setBackground(SystemColor.menu);
		txtrSugarLevel.setBounds(121, 259, 178, 18);
		panel.add(txtrSugarLevel);
		
		JTextArea txtrEye = new JTextArea();
		txtrEye.setText("");
		txtrEye.setWrapStyleWord(true);
		txtrEye.setLineWrap(true);
		txtrEye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrEye.setEditable(false);
		txtrEye.setBackground(SystemColor.menu);
		txtrEye.setBounds(121, 343, 178, 18);
		panel.add(txtrEye);
		
		JTextArea txtrEar = new JTextArea();
		txtrEar.setText("");
		txtrEar.setWrapStyleWord(true);
		txtrEar.setLineWrap(true);
		txtrEar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrEar.setEditable(false);
		txtrEar.setBackground(SystemColor.menu);
		txtrEar.setBounds(121, 363, 178, 18);
		panel.add(txtrEar);
		
		JLabel lblComments = new JLabel("Comments (if any):");
		lblComments.setForeground(new Color(0, 128, 128));
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComments.setBounds(343, 171, 285, 29);
		panel.add(lblComments);
		
		JTextArea txtrComment1 = new JTextArea();
		txtrComment1.setText("");
		txtrComment1.setEditable(false);
		txtrComment1.setWrapStyleWord(true);
		txtrComment1.setLineWrap(true);
		txtrComment1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment1.setBackground(SystemColor.menu);
		txtrComment1.setBounds(343, 202, 257, 18);
		panel.add(txtrComment1);
		
		JTextArea txtComment2 = new JTextArea();
		txtComment2.setText("");
		txtComment2.setEditable(false);
		txtComment2.setWrapStyleWord(true);
		txtComment2.setLineWrap(true);
		txtComment2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtComment2.setBackground(SystemColor.menu);
		txtComment2.setBounds(343, 222, 257, 18);
		panel.add(txtComment2);
		
		JTextArea txtrComment3 = new JTextArea();
		txtrComment3.setEditable(false);
		txtrComment3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment3.setBackground(SystemColor.menu);
		txtrComment3.setWrapStyleWord(true);
		txtrComment3.setLineWrap(true);
		txtrComment3.setText("");
		txtrComment3.setBounds(343, 242, 257, 18);
		panel.add(txtrComment3);
		
		JTextArea txtrComment4 = new JTextArea();
		txtrComment4.setWrapStyleWord(true);
		txtrComment4.setText("");
		txtrComment4.setLineWrap(true);
		txtrComment4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment4.setEditable(false);
		txtrComment4.setBackground(SystemColor.menu);
		txtrComment4.setBounds(343, 262, 257, 18);
		panel.add(txtrComment4);
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setForeground(new Color(0, 128, 128));
		lblAdditionalComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdditionalComments.setBounds(345, 314, 285, 29);
		panel.add(lblAdditionalComments);
		
		JTextArea txtrAddComment = new JTextArea();
		txtrAddComment.setText("");
		txtrAddComment.setBackground(SystemColor.text);
		txtrAddComment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrAddComment.setLineWrap(true);
		txtrAddComment.setWrapStyleWord(true);
		txtrAddComment.setBounds(345, 343, 297, 99);
		panel.add(txtrAddComment);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(309, 576, 215, 83);
		add(btnSaveChanges);
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Save changes?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
					
// -------------------->
					ArrayList<String> comments = new ArrayList<String>();
					for (int i=0; i<elderList.size(); i++){
						comments.add("");
					}
					
					int index = Integer.parseInt(lblElderid.getText());
					// delete indexed null entry
					comments.add(index-1, txtrAddComment.getText());

                	JOptionPane.showMessageDialog(null, "Changes saved.");
                	txtrAddComment.setText("");
                	
                }
			}
		});
		
		JButton btnDiscardChanges = new JButton("Discard Changes");
		btnDiscardChanges.setBounds(534, 576, 215, 83);
		add(btnDiscardChanges);
		btnDiscardChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Discard changes?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                	txtrAddComment.setText("");
                	JOptionPane.showMessageDialog(null, "Changes discarded.");
                }
			}
		});
		
		JButton btnSendAllReports = new JButton("Send All Reports");
		btnSendAllReports.setBounds(759, 576, 216, 29);
		add(btnSendAllReports);
		btnSendAllReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Send all reports?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                	File f = new File(ft.format(dNow)+" Report.pdf");
                	if(f.exists() && !f.isDirectory()) { 
                		JOptionPane.showMessageDialog(null, "Reports have already been sent.");
                	}
                	else {
                		
                		@SuppressWarnings("unused")
                		CreatePdf pdfs = new CreatePdf();
                		@SuppressWarnings("unused")
                		SendEmails emails = new SendEmails();   
                    	
                		             	
                    	JOptionPane.showMessageDialog(null, "Reports sent.");
                	}
                }
			}
		});
		
		JButton btnViewInMgmt = new JButton("View Elderly in Management Panel");
		btnViewInMgmt.setBounds(759, 611, 216, 45);
		add(btnViewInMgmt);
		
		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		});

//////////////////////////////////////
	
	elderDataTable.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			try{
				int row = elderDataTable.getSelectedRow();
				String table_clicked = (elderDataTable.getModel().getValueAt(row, 0).toString());
				String sql = "SELECT * FROM et_elderly WHERE id=?";
				ResultSet rs = so.getResultSet(sql, table_clicked);
				
				while(rs.next()){
					String add1 = rs.getString("id");
					lblElderid.setText(add1);
					
					String add2 = rs.getString("name");
					lblInfoName.setText(add2);
							
					Date dob = rs.getDate("dob");
					LocalDate doblocaldate = LocalDateTime.ofInstant
						(Instant.ofEpochMilli(dob.getTime()), ZoneId.systemDefault()).toLocalDate();
					Integer age = (int) doblocaldate.until(LocalDate.now(), ChronoUnit.YEARS);
					String add3 = Integer.toString(age);
					lblElderAge.setText(add3);
					
					String add4 = rs.getString("room");
					lblRoomNumber.setText(add4);
					
					String add5 = rs.getString("nric");
					lblNRIC.setText(add5);
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
	}
}
/////////////////////////////////////////
	
	