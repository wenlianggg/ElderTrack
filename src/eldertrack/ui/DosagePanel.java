package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import eldertrack.diet.TableHelper;
import javax.swing.JButton;

public class DosagePanel extends JPanel {

	private static final long serialVersionUID = 1726776874636177464L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable DosageTable;
	/**
	 * Create the panel.
	 */
	public DosagePanel() {
	
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dosage");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 10, 145, 50);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setBounds(70, 95, 61, 27);
		add(lblName);
		
		textField = new JTextField();
		textField.setBounds(151, 103, 145, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAge.setBounds(70, 166, 50, 27);
		add(lblAge);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 174, 145, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGender.setBounds(70, 133, 81, 22);
		add(lblGender);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 139, 145, 19);
		add(textField_2);
		
		JScrollPane scrollPane =  	new JScrollPane();
		scrollPane.setBounds(119, 240, 694, 135);
		add(scrollPane,BorderLayout.CENTER);
		
		DosageTable = new JTable();
		scrollPane.setViewportView(DosageTable);
		DosageTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Description", "Prescription", "Dosage"
			}
		));
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		btnSaveAndQuit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSaveAndQuit.setBounds(151, 413, 177, 27);
		add(btnSaveAndQuit);
		
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		
		JButton btnNextEldery = new JButton("Next Eldery");
		btnNextEldery.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNextEldery.setBounds(607, 413, 177, 27);
		add(btnNextEldery);
		
		
		
		
		

	}
}
