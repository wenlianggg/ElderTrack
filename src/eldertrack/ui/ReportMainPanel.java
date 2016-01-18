package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import eldertrack.diet.*;
import eldertrack.misc.TableHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class ReportMainPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable table;
	private JTextField searchField;
	private JButton btnSearch;
	CardLayout parentCards;
	
	// Constructor
	ReportMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);
		DefaultTableModel allEldersData = TableHelper.getElderlyBasic("");
		table = new JTable(allEldersData);
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(table);
		
		lblDietLabel = new JLabel("ElderTrack Report Generation");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select An Elderly");
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSelectElderly.setBounds(10, 57, 290, 31);
		
		add(lblDietLabel);
		add(lblSelectElderly);
		
		searchField = new JTextField();
		searchField.setBounds(56, 97, 165, 25);
		add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(TableHelper.getElderlyBasic("%" + searchField.getText() + "%"));
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
		
		JLabel lblElderid = new JLabel("ElderID:");
		lblElderid.setBounds(10, 55, 209, 14);
		panel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Elderly Name");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 11, 645, 37);
		panel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(10, 95, 209, 14);
		panel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(10, 75, 209, 14);
		panel.add(lblRoomNumber);
		
		JLabel lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(10, 115, 209, 14);
		panel.add(lblNric);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(3, 139, 659, 3);
		panel.add(separator);
		
		JLabel lblReportForThisMonth = new JLabel("Report for: November 2015");
		lblReportForThisMonth.setForeground(new Color(0, 128, 128));
		lblReportForThisMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReportForThisMonth.setBounds(10, 145, 358, 29);
		panel.add(lblReportForThisMonth);
		
		JLabel lblBloodPressure = new JLabel("Blood Pressure:");
		lblBloodPressure.setBounds(10, 204, 75, 14);
		panel.add(lblBloodPressure);
		
		JLabel lblGlucoseLevel = new JLabel("Glucose Level: ");
		lblGlucoseLevel.setBounds(10, 224, 75, 14);
		panel.add(lblGlucoseLevel);
		
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(10, 244, 75, 14);
		panel.add(lblTemperature);
		
		JLabel lblMedicalHistoryaverage = new JLabel("Medical History (Average of past month)");
		lblMedicalHistoryaverage.setForeground(new Color(0, 128, 128));
		lblMedicalHistoryaverage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMedicalHistoryaverage.setBounds(10, 171, 358, 29);
		panel.add(lblMedicalHistoryaverage);
		
		JLabel lblComments = new JLabel("Comments (if any):");
		lblComments.setForeground(new Color(0, 128, 128));
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComments.setBounds(343, 171, 285, 29);
		panel.add(lblComments);
		
		JTextArea txtArea_comment1 = new JTextArea();
		txtArea_comment1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtArea_comment1.setBackground(SystemColor.menu);
		txtArea_comment1.setEditable(false);
		txtArea_comment1.setWrapStyleWord(true);
		txtArea_comment1.setLineWrap(true);
		txtArea_comment1.setText("21/11: Patient complained of pains and aches, was given massage to ease pain");
		txtArea_comment1.setBounds(343, 242, 257, 37);
		panel.add(txtArea_comment1);
		
		JTextArea txtrPatientWas = new JTextArea();
		txtrPatientWas.setWrapStyleWord(true);
		txtrPatientWas.setText("13/11: Patient was active");
		txtrPatientWas.setLineWrap(true);
		txtrPatientWas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrPatientWas.setEditable(false);
		txtrPatientWas.setBackground(SystemColor.menu);
		txtrPatientWas.setBounds(343, 202, 257, 18);
		panel.add(txtrPatientWas);
		
		JTextArea txtrPatientInteracted = new JTextArea();
		txtrPatientInteracted.setWrapStyleWord(true);
		txtrPatientInteracted.setText("15/11: Patient interacted well with other patients");
		txtrPatientInteracted.setLineWrap(true);
		txtrPatientInteracted.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrPatientInteracted.setEditable(false);
		txtrPatientInteracted.setBackground(SystemColor.menu);
		txtrPatientInteracted.setBounds(343, 222, 257, 18);
		panel.add(txtrPatientInteracted);
		
		JTextArea txtrPatientRefused = new JTextArea();
		txtrPatientRefused.setWrapStyleWord(true);
		txtrPatientRefused.setText("30/11: Patient refused to eat, was put on IV drip");
		txtrPatientRefused.setLineWrap(true);
		txtrPatientRefused.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrPatientRefused.setEditable(false);
		txtrPatientRefused.setBackground(SystemColor.menu);
		txtrPatientRefused.setBounds(343, 274, 257, 18);
		panel.add(txtrPatientRefused);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("100/60");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(86, 202, 178, 18);
		panel.add(textArea);
		
		JTextArea txtrDegCelcius = new JTextArea();
		txtrDegCelcius.setWrapStyleWord(true);
		txtrDegCelcius.setText("36.9 deg Celcius");
		txtrDegCelcius.setLineWrap(true);
		txtrDegCelcius.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrDegCelcius.setEditable(false);
		txtrDegCelcius.setBackground(SystemColor.menu);
		txtrDegCelcius.setBounds(86, 242, 178, 18);
		panel.add(txtrDegCelcius);
		
		JTextArea txtrgmol = new JTextArea();
		txtrgmol.setWrapStyleWord(true);
		txtrgmol.setText("1.5g/mol");
		txtrgmol.setLineWrap(true);
		txtrgmol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrgmol.setEditable(false);
		txtrgmol.setBackground(SystemColor.menu);
		txtrgmol.setBounds(86, 222, 178, 18);
		panel.add(txtrgmol);
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setForeground(new Color(0, 128, 128));
		lblAdditionalComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdditionalComments.setBounds(343, 316, 285, 29);
		panel.add(lblAdditionalComments);
		
		JTextArea txtrNil = new JTextArea();
		txtrNil.setText("NIL");
		txtrNil.setBackground(SystemColor.menu);
		txtrNil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrNil.setLineWrap(true);
		txtrNil.setEditable(false);
		txtrNil.setWrapStyleWord(true);
		txtrNil.setBounds(343, 345, 297, 99);
		panel.add(txtrNil);
		
		JLabel lblReviewInfo = new JLabel("Review Report");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(310, 59, 665, 31);
		add(lblReviewInfo);
		

		
		JButton btnAddMeal = new JButton("Send All Reports");
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel dietAddPanel = new DietAddPanel();
				dietAddPanel.setVisible(true);
			}
		});
		btnAddMeal.setBounds(309, 576, 215, 82);
		add(btnAddMeal);
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		       // CardLayout parentCards = (CardLayout) ReportPanel.CardsPanel.getLayout();
		       // parentCards.show(ReportPanel.CardsPanel, ReportPanel.DADDPANEL);
			}
		});
		
		JButton btnModifyMeals = new JButton("Edit Report");
		btnModifyMeals.setBounds(534, 576, 215, 83);
		add(btnModifyMeals);
		btnModifyMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        //CardLayout parentCards = (CardLayout) ReportPanel.CardsPanel.getLayout();
		        //parentCards.show(ReportPanel.CardsPanel, ReportPanel.DMODPANEL);
			}
		});
		
		JButton btnMenuManagement = new JButton("Send Individual Report");
		btnMenuManagement.setBounds(759, 576, 216, 29);
		add(btnMenuManagement);
		btnMenuManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        //CardLayout parentCards = (CardLayout) ReportPanel.CardsPanel.getLayout();
		        //parentCards.show(ReportPanel.CardsPanel, ReportPanel.DMENUPANEL);
			}
		});
		
		JButton btnViewInMgmt = new JButton("View Elderly in Management Panel");
		btnViewInMgmt.setBounds(759, 611, 216, 45);
		add(btnViewInMgmt);
	}
}