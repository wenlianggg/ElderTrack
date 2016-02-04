package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
import eldertrack.misc.TableHelper;
import eldertrack.report.CalculateAvr;
import eldertrack.report.CreatePdf;
import eldertrack.report.MedicalData;
import eldertrack.report.SendEmails;

public class ReportMainPanel extends JPanel {
	private static SQLObject so = new SQLObject();
	private static final long serialVersionUID = 4318548492960279050L;
	public static JLabel lblElderid;
	JLabel lblReportLabel;
	JLabel lblSelectElderly;
	private JTable elderDataTable;
	private JTextField searchField;
	private JButton btnSearch;

	CardLayout parentCards;

	Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
	String reportDate=dateFormat.format(date);
       
	ResultSet rsChkFile = so.getResultSet("SELECT report FROM et_report");
	protected String table_clicked;
	protected String add1;
	
	// Constructor
	ReportMainPanel() {
		String[] args = null;
		try {
			MedicalData.main(args);
			CalculateAvr.main(args);

		} catch (ClassNotFoundException | IOException e2) {
			e2.printStackTrace();
		}
		
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(elderDataTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);
		
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
		panel.setBounds(305, 97, 665, 468);
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
		
		JLabel lblMedicalHistorySum = new JLabel("Medical History (Average for Month): ");
		lblMedicalHistorySum.setForeground(new Color(0, 128, 128));
		lblMedicalHistorySum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMedicalHistorySum.setBounds(10, 171, 285, 29);
		panel.add(lblMedicalHistorySum);
		
		JLabel lblTemp = new JLabel("Temperature:");
		lblTemp.setBounds(10, 203, 96, 14);
		panel.add(lblTemp);
		
		JLabel lblBP = new JLabel("Blood Pressure:");
		lblBP.setBounds(10, 223, 99, 14);
		panel.add(lblBP);
		
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
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setForeground(new Color(0, 128, 128));
		lblAdditionalComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdditionalComments.setBounds(345, 314, 285, 29);
		panel.add(lblAdditionalComments);
		
		JTextArea txtrAddComment = new JTextArea();
		txtrAddComment.setToolTipText("");
		txtrAddComment.setText("");
		txtrAddComment.setBackground(SystemColor.text);
		txtrAddComment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrAddComment.setLineWrap(true);
		txtrAddComment.setWrapStyleWord(true);
		txtrAddComment.setBounds(345, 343, 297, 99);
		panel.add(txtrAddComment);
		
////////////////////////////
		
		DefaultTableModel allEldersData;
		allEldersData = TableHelper.getElderlyBasic("");
		elderDataTable = new JTable(allEldersData);
		elderDataTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		elderDataTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try{
					int row = elderDataTable.getSelectedRow();
					String table_clicked = (elderDataTable.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT * FROM et_elderly WHERE id=?";
					ResultSet rs = so.getResultSet(sql, table_clicked);	
					
					ResultSet rsAvr = so.getResultSet("SELECT * FROM et_reportAvr WHERE id=?", table_clicked);
					
				    
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
					while(rsAvr.next()){
						double add6 =rsAvr.getDouble("tempMAvr");
						txtrTemp.setText(add6 +"\u00b0C");
						
						double add7 =rsAvr.getDouble("bloodMAvr");
						txtrBP.setText(add7 +" mmHg");
						
						double add8 =rsAvr.getDouble("heartMAvr");
						txtrHeartRate.setText(add8 +" bpm");
						
						int add9 =rsAvr.getInt("sugarMAvr");
						txtrSugarLevel.setText(add9 +" mmol/L");
						
						@SuppressWarnings("unused")
						boolean add10 =rsAvr.getBoolean("eyeMAvr");
						String add10S = "";
						if (add10=true)
							add10S="Yes";
						else
							add10S="No";
						txtrEye.setText(add10S);
						
						@SuppressWarnings("unused")
						boolean add11 =rsAvr.getBoolean("earMAvr");
						String add11S = "";
						if (add11=true)
							add11S="Yes";
						else
							add11S="No";
						txtrEar.setText(add11S);
						
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Check up details not found, complete check ups first");
					}
				}
			});
		tableScrollPane.setViewportView(elderDataTable);
		
	///////////////////////////////////////////////////
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(309, 576, 215, 83);
		add(btnSaveChanges);
		btnSaveChanges.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Save changes?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                	String addComment = txtrAddComment.getText();
                	try {                							
                		PreparedStatement statementUpdate = so.getPreparedStatementWithKey
                				("UPDATE et_reportAvr SET addComment=? WHERE id=?");
                		statementUpdate.setString(1, addComment);
                		statementUpdate.setString(2, add1);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
                	
                	try {
						rsChkFile.absolute(1);
					
						Blob report = rsChkFile.getBlob("report");
						
						if(report!=null) { 
							JOptionPane.showMessageDialog(null, "Reports have already been sent.");
						}
						else {
							CreatePdf.main(args);
							SendEmails.main(args);
							JOptionPane.showMessageDialog(null, "Reports sent.");
						}
                	} catch (SQLException e) {
                		e.printStackTrace();
                	} 
             
                }
			}
		});
		
		JButton btnViewInMgmt = new JButton("View Elderly in Management");
		btnViewInMgmt.setBounds(759, 611, 216, 45);
		add(btnViewInMgmt);
		btnViewInMgmt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (elderDataTable.getSelectedRow() != -1) {
					Integer selectedelderly = Integer.parseInt(elderDataTable.getValueAt(elderDataTable.getSelectedRow(), 0).toString());
					CardLayout mainCards = (CardLayout) MainFrame.CardsPanel.getLayout();
					mainCards.show(MainFrame.CardsPanel, MainFrame.MGMTPANEL);
					JTable jtb = MainFrame.getInstance().getManagementPanel().getElderlyTable();
					jtb.getSelectionModel().setSelectionInterval(selectedelderly, selectedelderly);
				} else
					JOptionPane.showMessageDialog(null, "Please select an elderly before proceeding!");
			}
		});
		
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
		
	
	}
}	