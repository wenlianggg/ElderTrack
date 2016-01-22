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
import eldertrack.medical.ElderData;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private JTextField SearchField;

	private DefaultTableModel Morningmodel;
	private DefaultTableModel Afternoonmodel;
	private DefaultTableModel Noonmodel;
	private boolean morningchange;
	private boolean afternoonchange;
	private boolean noonchange;
	/**
	 * Create the panel.
	 */
	public MedManagePanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Dosage Management");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 300, 41);
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
						java.sql.Date reportDate=rs.getDate("dob");
						DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						String text = df.format(reportDate);
						String year=text.substring(0, 4);
						String month=text.substring(5,7);
						String day=text.substring(8,10);
						AgeField.setText(Integer.toString(ElderData.getAge(year, month, day)));
						summaryPane.setText(rs.getString("dosagesummary"));
						if(rs.getBlob("morningdosage")!=null){
							Morningmodel=DosageTableHelper.getElderlyFromQueryManagementDos("morning",rs.getString("name"));
							MorningTable.setModel(Morningmodel);
						}
						else{
							MorningTable.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
											"Description", "Prescription", "Medication Type","Dosage"
									}
									));
						}
						if(rs.getBlob("afternoondosage")!=null){
							Afternoonmodel=DosageTableHelper.getElderlyFromQueryManagementDos("afternoon",rs.getString("name"));
							AfterNoonTable.setModel(Afternoonmodel);
						}
						else{
							AfterNoonTable.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
											"Description", "Prescription", "Medication Type","Dosage"
									}
									));
						}
						if(rs.getBlob("noondosage") !=null){
							Noonmodel=DosageTableHelper.getElderlyFromQueryManagementDos("noon",rs.getString("name"));
							NoonTable.setModel(Noonmodel);
						}
						else{
							NoonTable.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
											"Description", "Prescription", "Medication Type","Dosage"
									}
									));
						}

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
		ElderIDField.setEditable(false);
		add(ElderIDField);
		ElderIDField.setColumns(10);

		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblName.setBounds(525, 126, 80, 25);
		add(lblName);

		NameField = new JTextField();
		NameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		NameField.setBounds(615, 129, 100, 20);
		NameField.setEditable(false);
		add(NameField);
		NameField.setColumns(10);

		JLabel lblGender = new JLabel("GENDER:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGender.setBounds(525, 162, 80, 25);
		add(lblGender);

		GenderField = new JTextField();
		GenderField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GenderField.setBounds(615, 165, 100, 20);
		GenderField.setEditable(false);
		add(GenderField);
		GenderField.setColumns(10);

		JLabel lblAge = new JLabel("AGE:");
		lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAge.setBounds(525, 198, 80, 25);
		add(lblAge);

		AgeField = new JTextField();
		AgeField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		AgeField.setBounds(615, 201, 100, 20);
		AgeField.setEditable(false);
		add(AgeField);
		AgeField.setColumns(10);

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

		// Morning
		Morningmodel=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
				);
		MorningTable = new JTable(Morningmodel);


		JScrollPane MorningPane = new JScrollPane(MorningTable);
		MorningPane.setBounds(525, 291, 444, 86);
		add(MorningPane);

		JButton btnMorningAdd = new JButton("ADD");
		btnMorningAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Morningmodel.addRow(new Object [][] {{null, null,null}});
				Morningmodel.setValueAt(null,MorningTable.getRowCount()-1,0);
				morningchange=true;
			}
		});
		btnMorningAdd.setBounds(781, 272, 89, 20);
		add(btnMorningAdd);

		JButton btnRemoveMorning = new JButton("REMOVE");
		btnRemoveMorning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MorningTable.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null,"Select morning dosage to remove");
				}
				else{
					Morningmodel.removeRow(MorningTable.getSelectedRow());
				}
				morningchange=true;
			}
		});
		btnRemoveMorning.setBounds(880, 272, 89, 20);
		add(btnRemoveMorning);

		// Afternoon
		Afternoonmodel=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
				);
		AfterNoonTable = new JTable(Afternoonmodel);
		JScrollPane AfterNoonPane = new JScrollPane(AfterNoonTable);
		AfterNoonPane.setBounds(525, 400, 444, 86);
		add(AfterNoonPane);

		JButton btnAfternoonAdd = new JButton("ADD");
		btnAfternoonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afternoonmodel.addRow(new Object [][] {{null, null,null}});
				Afternoonmodel.setValueAt(null,AfterNoonTable.getRowCount()-1,0);
				afternoonchange=true;
			}
		});
		btnAfternoonAdd.setBounds(781, 382, 89, 20);
		add(btnAfternoonAdd);

		JButton btnRemoveAfternoon = new JButton("REMOVE");
		btnRemoveAfternoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AfterNoonTable.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null,"Select afternoon dosage to remove");
				}
				else{
					Afternoonmodel.removeRow(AfterNoonTable.getSelectedRow());
				}
				afternoonchange=true;
			}
		});
		btnRemoveAfternoon.setBounds(880, 382, 89, 20);
		add(btnRemoveAfternoon);

		// Noon
		Noonmodel=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
				);
		NoonTable = new JTable(Noonmodel);
		JScrollPane NoonPane = new JScrollPane(NoonTable);
		NoonPane.setBounds(525, 508, 444, 86);
		add(NoonPane);

		JButton btnNoonAdd = new JButton("ADD");
		btnNoonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Noonmodel.addRow(new Object [][] {{null, null,null}});
				Noonmodel.setValueAt(null,NoonTable.getRowCount()-1,0);
				noonchange=true;
			}
		});
		btnNoonAdd.setBounds(781, 489, 89, 20);
		add(btnNoonAdd);

		JButton btnRemoveNoon = new JButton("REMOVE");
		btnRemoveNoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NoonTable.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null,"Select noon dosage to remove");
				}
				else{
					Noonmodel.removeRow(NoonTable.getSelectedRow());
				}
				noonchange=true;
			}
		});
		btnRemoveNoon.setBounds(880, 489, 89, 20);
		add(btnRemoveNoon);




		JLabel lblSearch = new JLabel("SEARCH:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSearch.setBounds(25, 90, 80, 25);
		add(lblSearch);

		SearchField = new JTextField();
		SearchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		SearchField.setBounds(105, 90, 100, 25);
		add(SearchField);
		SearchField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(213, 92, 89, 23);
		add(btnSearch);

		JButton btnSaveChange = new JButton("SAVE CHANGES");
		btnSaveChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(morningchange==true || afternoonchange==true || noonchange==true){
					
				}
				else{
					JOptionPane.showMessageDialog(null,"There were no changes");
				}
				
				
			}
		});
		btnRemoveNoon.setBounds(880, 489, 89, 20);
		add(btnRemoveNoon);
		btnSaveChange.setBounds(840, 607, 128, 23);
		add(btnSaveChange);

	}
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		JPanel panel=new MedManagePanel();
		frame.setBounds(0, 0, 1000, 810);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
