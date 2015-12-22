package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MedCheckPanel extends JPanel {


	private static final long serialVersionUID = -1155434751690765910L;
	private JTextField textField;
	


	public MedCheckPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check Up");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 210, 41);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setForeground(new Color(0, 128, 128));
		lblName.setBounds(120, 95, 70, 30);
		add(lblName);
		
		JTextField NameField = new JTextField();
		NameField.setBounds(201, 100, 145, 30);
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setForeground(new Color(0, 128, 128));
		lblAge.setBounds(120, 133, 70, 30);
		add(lblAge);
		
		JTextField AgeField = new JTextField();
		AgeField.setBounds(201, 138, 145, 30);
		AgeField.setEditable(false);
		add(AgeField);
		AgeField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setForeground(new Color(0, 128, 128));
		lblGender.setBounds(120, 171, 70, 30);
		add(lblGender);
				
		JTextField GenderField = new JTextField();
		GenderField.setColumns(10);
		GenderField.setBounds(201, 176, 145, 30);
		GenderField.setEditable(false);
		add(GenderField);
			
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSummary.setBounds(391, 95, 113, 30);
		lblSummary.setForeground(new Color(0, 128, 128));
		add(lblSummary);
				
		JTextPane txtSummary = new JTextPane();
		txtSummary.setBounds(391, 138, 424, 68);
		txtSummary.setEditable(false);
		add(txtSummary);
		
		//Checking 
		JTabbedPane CheckTabbed=new JTabbedPane();
		
		CheckTabbed.setBounds(120, 253, 695, 300);
		add(CheckTabbed);
		
		JPanel GeneralCheck=new JPanel();
		GeneralCheck.setLayout(null);
		JLabel lblHeartRate = new JLabel("Heart Rate:");
		lblHeartRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeartRate.setBounds(29, 10, 90, 30);
		GeneralCheck.add(lblHeartRate);
		
		JTextField HeartField = new JTextField();
		HeartField.setBounds(148, 17, 90, 25);
		HeartField.setColumns(10);
		GeneralCheck.add(HeartField);
		
		JLabel lblEye= new JLabel("Eye Infection:");
		lblEye.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEye.setBounds(29, 92, 112, 30);
		GeneralCheck.add(lblEye);
		
		JComboBox comboEye = new JComboBox();
		comboEye.setBounds(148, 101, 90, 20);
		comboEye.addItem(" ");
		comboEye.addItem("Yes");
		comboEye.addItem("No");
		GeneralCheck.add(comboEye);
		
		JLabel lblEar= new JLabel("Ear Infection:");
		lblEar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEar.setBounds(29, 133, 112, 30);
		GeneralCheck.add(lblEar);
		
		JComboBox comboEar = new JComboBox();
		comboEar.setBounds(148, 142, 90, 20);
		comboEar.addItem(" ");
		comboEar.addItem("Yes");
		comboEar.addItem("No");
		GeneralCheck.add(comboEar);
		
		
		
		
		
		JLabel lblAddition= new JLabel("Additional Notes: ");
		lblAddition.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAddition.setBounds(298, 10, 151, 30);
		GeneralCheck.add(lblAddition);
		
		JTextPane textAddition = new JTextPane();
		textAddition.setBounds(298, 51, 357, 112);
		GeneralCheck.add(textAddition);
		
		JTabbedPane SpecialCheck=new JTabbedPane();
		SpecialCheck.setLayout(null);
		
	
		
		
		
		
		
		
		
		CheckTabbed.add(GeneralCheck,"Genearal");
		
		JLabel lblSugarLv= new JLabel("Sugar Level: ");
		lblSugarLv.setBounds(29, 51, 112, 30);
		GeneralCheck.add(lblSugarLv);
		lblSugarLv.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(148, 56, 90, 25);
		GeneralCheck.add(textField);
		
		
		
		
		CheckTabbed.add(SpecialCheck,"Specialised Checking");
		CheckTabbed.setFont( new Font( "Segoe UI", Font.BOLD|Font.ITALIC, 20 ) );
		
		}
	}

