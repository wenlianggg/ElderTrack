package eldertrack.ui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MedManagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318196195924759182L;
	private JTable table;
	private JTextField ElderIDField;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTable MorningTable;
	private JTable AfterNoonTable;
	private JTable NoonTable;
	/**
	 * Create the panel.
	 */
	public MedManagePanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Management");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 210, 41);
		add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(25, 90, 408, 504);
		add(table);
		
		JLabel lblElderId = new JLabel("ELDER ID: ");
		lblElderId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblElderId.setBounds(493, 90, 85, 25);
		add(lblElderId);
		
		ElderIDField = new JTextField();
		ElderIDField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		ElderIDField.setBounds(670, 93, 100, 20);
		add(ElderIDField);
		ElderIDField.setColumns(10);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblName.setBounds(493, 126, 80, 25);
		add(lblName);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		NameField.setBounds(670, 132, 100, 20);
		add(NameField);
		NameField.setColumns(10);
		
		JLabel lblGender = new JLabel("GENDER:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGender.setBounds(493, 162, 80, 25);
		add(lblGender);
		
		AgeField = new JTextField();
		AgeField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		AgeField.setBounds(670, 168, 100, 20);
		add(AgeField);
		AgeField.setColumns(10);
		
		JLabel lblAge = new JLabel("AGE:");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAge.setBounds(493, 198, 80, 25);
		add(lblAge);
		
		GenderField = new JTextField();
		GenderField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GenderField.setBounds(670, 204, 100, 20);
		add(GenderField);
		GenderField.setColumns(10);
		
		JLabel lblDosage = new JLabel("DOSAGE:");
		lblDosage.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDosage.setBounds(493, 234, 80, 25);
		add(lblDosage);
		
		JLabel lblMorning = new JLabel("MORNING:");
		lblMorning.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMorning.setBounds(493, 270, 80, 20);
		add(lblMorning);
		
		JLabel lblAfternoon = new JLabel("AFTERNOON:");
		lblAfternoon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAfternoon.setBounds(493, 379, 100, 20);
		add(lblAfternoon);
		
		JLabel lblNoon = new JLabel("NOON:");
		lblNoon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNoon.setBounds(493, 486, 80, 20);
		add(lblNoon);
		
		MorningTable = new JTable();
		MorningTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Description", "Prescription", "Medication Type","Dosage"
			}
		));
		
		JScrollPane MorningPane = new JScrollPane(MorningTable);
		MorningPane.setBounds(493, 291, 444, 86);
		add(MorningPane);
		
		AfterNoonTable = new JTable();
		AfterNoonTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
			));
		
		JScrollPane AfterNoonPane = new JScrollPane(AfterNoonTable);
		AfterNoonPane.setBounds(493, 398, 444, 86);
		add(AfterNoonPane);
		
		NoonTable = new JTable();
		NoonTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Description", "Prescription", "Medication Type","Dosage"
			}
		));
		
		JScrollPane NoonPane = new JScrollPane(NoonTable);
		NoonPane.setBounds(493, 508, 444, 86);
		add(NoonPane);
		
		
		
	}
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		JPanel panel=new MedManagePanel();
		frame.setBounds(0, 0, 1000, 810);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
