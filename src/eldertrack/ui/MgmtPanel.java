package eldertrack.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class MgmtPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblEManagementLbl;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	MgmtPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		lblEManagementLbl = new JLabel("ElderTrack Elderly Management");
		lblEManagementLbl.setBounds(407, 5, 181, 16);
		add(lblEManagementLbl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 79, 415, 445);
		add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "NRIC", "Name", "Age", "Room No."
			}
		));
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Save Changes");
		button.setBounds(474, 499, 132, 25);
		add(button);
		
		JButton button_1 = new JButton("Discard Changes");
		button_1.setBounds(638, 499, 132, 25);
		add(button_1);
		
		JButton button_2 = new JButton("Remove Elderly");
		button_2.setBounds(795, 499, 132, 25);
		add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(479, 79, 448, 396);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("NRIC");
		label.setBounds(19, 64, 109, 25);
		panel.add(label);
		label.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		JLabel label_1 = new JLabel("NAME");
		label_1.setBounds(18, 102, 109, 25);
		panel.add(label_1);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setBounds(19, 27, 109, 25);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_2 = new JLabel("AGE");
			label_2.setBounds(18, 141, 109, 25);
			panel.add(label_2);
			label_2.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_7 = new JLabel("ROOM ID");
			label_7.setBounds(19, 179, 121, 25);
			panel.add(label_7);
			label_7.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_3 = new JLabel(":");
			label_3.setBounds(152, 29, 23, 25);
			panel.add(label_3);
			label_3.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_4 = new JLabel(":");
			label_4.setBounds(152, 66, 23, 25);
			panel.add(label_4);
			label_4.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_5 = new JLabel(":");
			label_5.setBounds(152, 103, 23, 25);
			panel.add(label_5);
			label_5.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_6 = new JLabel(":");
			label_6.setBounds(153, 141, 23, 25);
			panel.add(label_6);
			label_6.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			JLabel label_8 = new JLabel(":");
			label_8.setBounds(153, 179, 23, 25);
			panel.add(label_8);
			label_8.setFont(new Font("Calibri", Font.PLAIN, 24));
			
			textField = new JTextField();
			textField.setBounds(179, 29, 116, 22);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setBounds(180, 67, 116, 22);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setBounds(180, 105, 116, 22);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setBounds(180, 143, 116, 22);
			panel.add(textField_3);
			textField_3.setColumns(10);
			
			textField_4 = new JTextField();
			textField_4.setBounds(181, 179, 116, 22);
			panel.add(textField_4);
			textField_4.setColumns(10);
			
			JButton btnNewButton = new JButton("Back");
			btnNewButton.setBounds(29, 569, 97, 25);
			add(btnNewButton);
	}
}
