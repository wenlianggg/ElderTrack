package eldertrack.ui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class ReportModifyPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblReportLabel;
	
	// Constructor
	ReportModifyPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		lblReportLabel = new JLabel("ElderTrack Report Generator");
		lblReportLabel.setForeground(SystemColor.textHighlight);
		lblReportLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblReportLabel.setBounds(10, 0, 557, 54);
		
		add(lblReportLabel);
		
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personInfoPanel.setBounds(10, 59, 975, 86);
		add(personInfoPanel);
		personInfoPanel.setLayout(null);
		
		JLabel lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		personInfoPanel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Modifying Report For:");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 7, 645, 37);
		personInfoPanel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(8, 61, 175, 14);
		personInfoPanel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(195, 61, 209, 14);
		personInfoPanel.add(lblRoomNumber);
		
		JLabel lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(195, 44, 209, 14);
		personInfoPanel.add(lblNric);
		
		JButton btnBackToMain = new JButton("Back (Elderly View)");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
		        parentCards.show(DietSection.CardsPanel, DietSection.DMAINPANEL);
			}
		});
		
		JButton btnRemoveEntry = new JButton("Save Changes");
		btnRemoveEntry.setBounds(782, 156, 203, 109);
		add(btnRemoveEntry);
		
		JButton btnModifyEntry = new JButton("Discard Changes");
		btnModifyEntry.setBounds(782, 276, 203, 109);
		add(btnModifyEntry);
		btnBackToMain.setBounds(782, 611, 203, 48);
		add(btnBackToMain);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(null);
		nutriInfoPanel.setBounds(10, 156, 762, 503);
		add(nutriInfoPanel);
		nutriInfoPanel.setLayout(null);
		
		JLabel lblAddedByDdmmyy = new JLabel("Last Update: dd/mm/yy hh:mmAM by PERSON NAME");
		lblAddedByDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedByDdmmyy.setBounds(11, 431, 306, 20);
		nutriInfoPanel.add(lblAddedByDdmmyy);
		
		JLabel lblLastModifiedDdmmyy = new JLabel("Last Modified: dd/mm/yy hh:mmAM by PERSON NAME");
		lblLastModifiedDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastModifiedDdmmyy.setBounds(11, 451, 318, 20);
		nutriInfoPanel.add(lblLastModifiedDdmmyy);
		
		JLabel lblReportForMonth = new JLabel("Report for: Month Year");
		lblReportForMonth.setForeground(new Color(0, 128, 128));
		lblReportForMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReportForMonth.setBounds(11, 13, 358, 29);
		nutriInfoPanel.add(lblReportForMonth);
		
		JLabel label_1 = new JLabel("Medical History (Average of past month)");
		label_1.setForeground(new Color(0, 128, 128));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(11, 39, 358, 29);
		nutriInfoPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Comments (if any):");
		label_2.setForeground(new Color(0, 128, 128));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(344, 39, 285, 29);
		nutriInfoPanel.add(label_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("100/60");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(110, 71, 178, 18);
		nutriInfoPanel.add(textArea);
		
		JLabel label_3 = new JLabel("Blood Pressure:");
		label_3.setBounds(11, 72, 100, 14);
		nutriInfoPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Glucose Level: ");
		label_4.setBounds(11, 92, 100, 14);
		nutriInfoPanel.add(label_4);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setText("1.5g/mol");
		textArea_1.setLineWrap(true);
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(110, 91, 178, 18);
		nutriInfoPanel.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setWrapStyleWord(true);
		textArea_2.setText("36.9 deg Celcius");
		textArea_2.setLineWrap(true);
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea_2.setEditable(false);
		textArea_2.setBackground(Color.WHITE);
		textArea_2.setBounds(110, 111, 178, 18);
		nutriInfoPanel.add(textArea_2);
		
		JLabel label_5 = new JLabel("Temperature:");
		label_5.setBounds(11, 112, 100, 14);
		nutriInfoPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Additional Comments:");
		label_6.setForeground(new Color(0, 128, 128));
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(344, 204, 285, 29);
		nutriInfoPanel.add(label_6);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setWrapStyleWord(true);
		textArea_7.setText("NIL");
		textArea_7.setLineWrap(true);
		textArea_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea_7.setEditable(false);
		textArea_7.setBackground(Color.WHITE);
		textArea_7.setBounds(344, 233, 381, 197);
		nutriInfoPanel.add(textArea_7);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setBounds(344, 72, 50, 14);
		nutriInfoPanel.add(lblDate);
		
		JTextArea txtrComment = new JTextArea();
		txtrComment.setWrapStyleWord(true);
		txtrComment.setText("Comment");
		txtrComment.setLineWrap(true);
		txtrComment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment.setEditable(false);
		txtrComment.setBackground(Color.WHITE);
		txtrComment.setBounds(396, 71, 329, 18);
		nutriInfoPanel.add(txtrComment);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setBounds(344, 92, 50, 14);
		nutriInfoPanel.add(lblDate_1);
		
		JTextArea txtrComment_1 = new JTextArea();
		txtrComment_1.setWrapStyleWord(true);
		txtrComment_1.setText("Comment");
		txtrComment_1.setLineWrap(true);
		txtrComment_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment_1.setEditable(false);
		txtrComment_1.setBackground(Color.WHITE);
		txtrComment_1.setBounds(396, 91, 329, 18);
		nutriInfoPanel.add(txtrComment_1);
		
		JTextArea txtrComment_2 = new JTextArea();
		txtrComment_2.setWrapStyleWord(true);
		txtrComment_2.setText("Comment");
		txtrComment_2.setLineWrap(true);
		txtrComment_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrComment_2.setEditable(false);
		txtrComment_2.setBackground(Color.WHITE);
		txtrComment_2.setBounds(396, 111, 329, 18);
		nutriInfoPanel.add(txtrComment_2);
		
		JLabel label = new JLabel("Date");
		label.setBounds(344, 112, 100, 14);
		nutriInfoPanel.add(label);
		
		JButton btnSendAllReports = new JButton("Send All Reports");
		btnSendAllReports.setBounds(782, 548, 203, 48);
		add(btnSendAllReports);
		
		JButton btnSendIndividualReport = new JButton("Send Individual Report");
		btnSendIndividualReport.setBounds(782, 487, 203, 48);
		add(btnSendIndividualReport);

	}
}