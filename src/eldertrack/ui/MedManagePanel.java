package eldertrack.ui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;
import eldertrack.medical.DosageTableHelper;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MedManagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318196195924759182L;
	private JTable eldertable;
	private JTextField ElderIDField;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextPane summaryPane;
	private JTable MorningTable;
	private JTable AfterNoonTable;
	private JTable NoonTable;
	private JTextField textField;
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

		eldertable = new JTable();
		DefaultTableModel allEldersData;
		try {
			allEldersData = DosageTableHelper.getElderlyFromQueryManagement("");
			eldertable = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		eldertable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		eldertable.getColumnModel().getColumn(0).setPreferredWidth(20);
		eldertable.getColumnModel().getColumn(2).setPreferredWidth(60);
		eldertable.getColumnModel().getColumn(3).setPreferredWidth(50);
		eldertable.getColumnModel().getColumn(5).setPreferredWidth(90);
		JScrollPane elderDataPane = new JScrollPane(eldertable);
		elderDataPane.setBounds(25, 126, 469, 504);
		add(elderDataPane);
		eldertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					SQLObject so=new SQLObject();
					int row = eldertable.getSelectedRow();
					String table_clicked = (eldertable.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT * FROM et_elderly WHERE id=?";
					
					ResultSet rs = so.getResultSet(sql, table_clicked);
					
					while(rs.next()){
						ElderIDField.setText(Integer.toString(rs.getInt("id")));
						NameField.setText(rs.getString("name"));
						
						GenderField.setText(rs.getString("gender"));
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			
			}
		});



		JLabel lblElderId = new JLabel("ELDER ID: ");
		lblElderId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblElderId.setBounds(525, 90, 85, 25);
		add(lblElderId);

		ElderIDField = new JTextField();
		ElderIDField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ElderIDField.setBounds(615, 93, 100, 20);
		add(ElderIDField);
		ElderIDField.setColumns(10);

		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblName.setBounds(525, 126, 80, 25);
		add(lblName);

		NameField = new JTextField();
		NameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		NameField.setBounds(615, 129, 100, 20);
		add(NameField);
		NameField.setColumns(10);

		JLabel lblGender = new JLabel("GENDER:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGender.setBounds(525, 162, 80, 25);
		add(lblGender);

		AgeField = new JTextField();
		AgeField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		AgeField.setBounds(615, 165, 100, 20);
		add(AgeField);
		AgeField.setColumns(10);

		JLabel lblAge = new JLabel("AGE:");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAge.setBounds(525, 198, 80, 25);
		add(lblAge);

		GenderField = new JTextField();
		GenderField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GenderField.setBounds(615, 201, 100, 20);
		add(GenderField);
		GenderField.setColumns(10);

		JLabel lblSummary = new JLabel("SUMMARY:");
		lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSummary.setBounds(741, 90, 100, 25);
		add(lblSummary);

		summaryPane = new JTextPane();
		summaryPane.setBounds(741, 126, 228, 97);
		add(summaryPane);

		JLabel lblDosage = new JLabel("DOSAGE:");
		lblDosage.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDosage.setBounds(525, 234, 80, 25);
		add(lblDosage);

		JLabel lblMorning = new JLabel("MORNING:");
		lblMorning.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMorning.setBounds(525, 270, 80, 20);
		add(lblMorning);

		JLabel lblAfternoon = new JLabel("AFTERNOON:");
		lblAfternoon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAfternoon.setBounds(525, 379, 100, 20);
		add(lblAfternoon);

		JLabel lblNoon = new JLabel("NOON:");
		lblNoon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNoon.setBounds(525, 486, 80, 20);
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
		MorningPane.setBounds(525, 291, 444, 86);
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
		AfterNoonPane.setBounds(525, 400, 444, 86);
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
		NoonPane.setBounds(525, 508, 444, 86);
		add(NoonPane);

		JButton btnRemoveMorning = new JButton("REMOVE");
		btnRemoveMorning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveMorning.setBounds(880, 272, 89, 20);
		add(btnRemoveMorning);

		JButton btnRemoveAfternoon = new JButton("REMOVE");
		btnRemoveAfternoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveAfternoon.setBounds(880, 382, 89, 20);
		add(btnRemoveAfternoon);

		JButton btnRemoveNoon = new JButton("REMOVE");
		btnRemoveNoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveNoon.setBounds(880, 489, 89, 20);
		add(btnRemoveNoon);

		JLabel lblSearch = new JLabel("SEARCH:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSearch.setBounds(25, 90, 80, 25);
		add(lblSearch);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setBounds(105, 90, 100, 25);
		add(textField);
		textField.setColumns(10);





	}
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		JPanel panel=new MedManagePanel();
		frame.setBounds(0, 0, 1000, 810);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
