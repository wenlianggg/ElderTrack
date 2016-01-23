package eldertrack.ui;


import java.awt.Font;
import java.awt.SystemColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTextField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;

import eldertrack.db.SQLObject;
import eldertrack.diet.*;
import eldertrack.management.ElderlyTableHelper;
import eldertrack.misc.TableHelper;
import eldertrack.report.CreatePdf;
import eldertrack.report.SendEmails;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

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

	/*	JScrollPane tableScrollPane = new JScrollPane(elderDataTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);

		DefaultTableModel allEldersData;
		try {
			allEldersData = ElderlyTableHelper.getElderlyFromQuery("");
			elderDataTable = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		elderDataTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(elderDataTable);*/
		
		
		
		JScrollPane tableScrollPane = new JScrollPane(elderDataTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);
		DefaultTableModel allEldersData = TableHelper.getElderlyBasic("");
		elderDataTable = new JTable(allEldersData);
		elderDataTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		    	presentData(elderDataTable.getValueAt(elderDataTable.getSelectedRow(), 0).toString());
		    }
		});
		elderDataTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(elderDataTable);
		
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
		JLabel lblElderName = new JLabel("Elderly Name: ");
		lblElderName.setForeground(new Color(0, 128, 128));
		lblElderName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblElderName.setBounds(10, 11, 156, 37);
		panel.add(lblElderName);
		
		JLabel name = new JLabel("");
		name.setForeground(new Color(0, 128, 128));
		name.setFont(new Font("Tahoma", Font.PLAIN, 24));
		name.setBounds(163, 11, 219, 37);
		panel.add(name);
		//
		JLabel lblElderId = new JLabel("ElderID: ");
		lblElderId.setBounds(10, 55, 55, 14);
		panel.add(lblElderId);
		
		JLabel id = new JLabel("");
		id.setBounds(59, 54, 257, 16);
		panel.add(id);
		//
		JLabel lblElderAge = new JLabel("Age: ");
		lblElderAge.setBounds(10, 95, 31, 14);
		panel.add(lblElderAge);
		
		JLabel age = new JLabel("");
		age.setBounds(42, 94, 257, 16);
		panel.add(age);
		//
		JLabel lblRmNum = new JLabel("Room Number: ");
		lblRmNum.setBounds(10, 75, 96, 14);
		panel.add(lblRmNum);
		
		JLabel rmNum = new JLabel("");
		rmNum.setBounds(102, 75, 257, 16);
		panel.add(rmNum);
		//
		JLabel lblNric = new JLabel("NRIC: ");
		lblNric.setBounds(10, 115, 37, 14);
		panel.add(lblNric);
		
		JLabel nric = new JLabel("");
		nric.setBounds(52, 114, 257, 16);
		panel.add(nric);
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
		lblMedicalHistoryaverage.setBounds(10, 171, 358, 29);
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
		lblEyeInfect.setBounds(10, 283, 96, 14);
		panel.add(lblEyeInfect);
		
		JLabel lblEarInfect = new JLabel("Ear Infection: ");
		lblEarInfect.setBounds(10, 303, 96, 14);
		panel.add(lblEarInfect);
		
		JTextArea txtrTemp = new JTextArea();
		txtrTemp.setEditable(false);
		txtrTemp.setText("-");
		txtrTemp.setWrapStyleWord(true);
		txtrTemp.setLineWrap(true);
		txtrTemp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrTemp.setBackground(SystemColor.menu);
		txtrTemp.setBounds(121, 199, 178, 18);
		panel.add(txtrTemp);
		
		JTextArea txtrBP = new JTextArea();
		txtrBP.setEditable(false);
		txtrBP.setWrapStyleWord(true);
		txtrBP.setText("-");
		txtrBP.setLineWrap(true);
		txtrBP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrBP.setBackground(SystemColor.menu);
		txtrBP.setBounds(121, 219, 178, 18);
		panel.add(txtrBP);
		
		JTextArea txtrHeartRate = new JTextArea();
		txtrHeartRate.setEditable(false);
		txtrHeartRate.setWrapStyleWord(true);
		txtrHeartRate.setText("-");
		txtrHeartRate.setLineWrap(true);
		txtrHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrHeartRate.setBackground(SystemColor.menu);
		txtrHeartRate.setBounds(121, 239, 178, 18);
		panel.add(txtrHeartRate);		
		
		JTextArea txtrSugarLevel = new JTextArea();
		txtrSugarLevel.setWrapStyleWord(true);
		txtrSugarLevel.setText("-");
		txtrSugarLevel.setLineWrap(true);
		txtrSugarLevel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrSugarLevel.setEditable(false);
		txtrSugarLevel.setBackground(SystemColor.menu);
		txtrSugarLevel.setBounds(121, 259, 178, 18);
		panel.add(txtrSugarLevel);
		
		JTextArea txtrEye = new JTextArea();
		txtrEye.setWrapStyleWord(true);
		txtrEye.setText("-");
		txtrEye.setLineWrap(true);
		txtrEye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrEye.setEditable(false);
		txtrEye.setBackground(SystemColor.menu);
		txtrEye.setBounds(121, 279, 178, 18);
		panel.add(txtrEye);
		
		JTextArea txtrEar = new JTextArea();
		txtrEar.setWrapStyleWord(true);
		txtrEar.setText("-");
		txtrEar.setLineWrap(true);
		txtrEar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrEar.setEditable(false);
		txtrEar.setBackground(SystemColor.menu);
		txtrEar.setBounds(121, 299, 178, 18);
		panel.add(txtrEar);
		
		JLabel lblComments = new JLabel("Comments (if any):");
		lblComments.setForeground(new Color(0, 128, 128));
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComments.setBounds(343, 171, 285, 29);
		panel.add(lblComments);
		
		JTextArea txtrComment1 = new JTextArea();
		txtrComment1.setEditable(false);
		txtrComment1.setWrapStyleWord(true);
		txtrComment1.setText("-");
		txtrComment1.setLineWrap(true);
		txtrComment1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment1.setBackground(SystemColor.menu);
		txtrComment1.setBounds(343, 202, 257, 18);
		panel.add(txtrComment1);
		
		JTextArea txtComment2 = new JTextArea();
		txtComment2.setEditable(false);
		txtComment2.setWrapStyleWord(true);
		txtComment2.setText("-");
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
		txtrComment3.setText("-");
		txtrComment3.setBounds(343, 242, 257, 18);
		panel.add(txtrComment3);
		
		JTextArea txtrComment4 = new JTextArea();
		txtrComment4.setWrapStyleWord(true);
		txtrComment4.setText("-");
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
		txtrAddComment.setText("NIL");
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
					
                	String addComments = txtrAddComment.getText();
// -------------------->
					ArrayList<String> comments = new ArrayList<String>();
					int index = Integer.parseInt(id.getText());
					comments.add(index, addComments);

                	JOptionPane.showMessageDialog(null, "Changes saved.");
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
					id.setText(add1);
					
					String add2 = rs.getString("name");
					name.setText(add2);
					
					String add3 = rs.getString("age");
					age.setText(add3);
					
					String add4 = rs.getString("room");
					rmNum.setText(add4);
					
					String add5 = rs.getString("nric");
					nric.setText(add5);
				}
				
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
	}
/////////////////////////////////////////
		
	/*	ArrayList<String> elderList = new ArrayList<String>();
		for (int i=0; i<elderList.size(); i++){
			
		}		
		ArrayList<String> pdfFiles = new ArrayList<String>(); */
		
};