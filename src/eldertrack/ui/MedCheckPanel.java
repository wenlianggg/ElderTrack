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

public class MedCheckPanel extends JPanel {


	private static final long serialVersionUID = -1155434751690765910L;


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
				
		JTextPane txtpnHadSomeCough = new JTextPane();
		txtpnHadSomeCough.setBounds(391, 138, 424, 68);
		txtpnHadSomeCough.setEditable(false);
		add(txtpnHadSomeCough);
		
		//Checking 
		JTabbedPane CheckTabbed=new JTabbedPane();
		
		CheckTabbed.setBounds(120, 253, 695, 300);
		add(CheckTabbed);
		
	
		
		JPanel GeneralCheck=new JPanel();
		
		
		CheckTabbed.add(GeneralCheck);
		
		
		}
	}

