package eldertrack.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class StaffManagementPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public StaffManagementPanel() {
		setBounds(479, 79, 448, 396);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 448, 396);
		add(panel);
		
		JLabel label = new JLabel("STAFF ID");
		label.setFont(new Font("Calibri", Font.PLAIN, 24));
		label.setBounds(19, 64, 123, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("FIRST NAME");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_1.setBounds(18, 102, 124, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("LAST NAME");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_2.setBounds(19, 27, 123, 25);
		panel.add(label_2);
		
		JLabel label_5 = new JLabel(":");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_5.setBounds(152, 27, 23, 25);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel(":");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_6.setBounds(152, 66, 23, 25);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 24));
		label_7.setBounds(152, 103, 23, 25);
		panel.add(label_7);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(180, 29, 116, 22);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 67, 116, 22);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 105, 116, 22);
		panel.add(textField_2);
	}
}
