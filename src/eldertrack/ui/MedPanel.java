package eldertrack.ui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedPanel extends JPanel {
	private static final long serialVersionUID = 5062666526948201245L;
	JLabel lblMedPanelLbl;
	//Panel
	JTabbedPane TabbedPanel=new JTabbedPane();
	JPanel MedTab1=new JPanel();
	JPanel MedTab2=new JPanel();
	JLabel MedTab1Lab=new JLabel();
	JLabel MedTab2Lab=new JLabel();
	private JTextField textField;
	
	
	MedPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblMedPanelLbl = new JLabel("ElderTrack Medication");
		lblMedPanelLbl.setBounds(5, 5, 392, 54);
		lblMedPanelLbl.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		add(lblMedPanelLbl);    
		
		TabbedPanel.setBounds(10, 85, 975, 510);
		TabbedPanel.setFont( new Font( "Segoe UI", Font.BOLD|Font.ITALIC, 20 ) );
		
		MedTab2.setLayout(null);
		MedTab2Lab.setBounds(23, 5, 0, 0);
		MedTab2.add(MedTab2Lab);
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		
		MedTab1.setLayout(null);
		MedTab1Lab.setBounds(23, 5, 0, 0);
		MedTab1.add(MedTab1Lab);
		
		TabbedPanel.add("Dosage", MedTab1);
		int index=TabbedPanel.indexOfTab("Dosage");
		
		
		JLabel lblRoomNumber = new JLabel("Room Number: ");
		lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblRoomNumber.setBounds(300, 110, 212, 37);
		MedTab1.add(lblRoomNumber);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(537, 110, 129, 36);
		comboBox.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		comboBox.addItem("101");
		comboBox.addItem("102");
		comboBox.addItem("103");
		MedTab1.add(comboBox);
		
		
		
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblTime.setBounds(300, 182, 149, 37);
		MedTab1.add(lblTime);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont( new Font( "Segoe UI", Font.BOLD, 18 ) );
		textField.setText(dateFormat.format(cal.getTime()));
		textField.setBounds(568, 182, 98, 37);
		MedTab1.add(textField);
		textField.setColumns(10);
		
		JButton btnGetDosage = new JButton("Get Dosage");
		btnGetDosage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPanel.setComponentAt(index, new DosagePanel());
			}
		});
		
		btnGetDosage.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		btnGetDosage.setBounds(451, 260, 215, 49);
		MedTab1.add(btnGetDosage);
		TabbedPanel.add("Check-Up",MedTab2);
		add(TabbedPanel);
		
		
	}
}
