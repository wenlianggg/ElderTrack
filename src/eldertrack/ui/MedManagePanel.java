package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import eldertrack.db.SQLObject;
import eldertrack.medical.DosageObject;
import eldertrack.medical.DosageTableHelper;
import eldertrack.medical.ElderData;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	private static final long serialVersionUID = -1318196195924759182L;
	private SQLObject so=new SQLObject();
	private JTable eldertable;
	private JTextField ElderIDField;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField GenderField;
	private JTextPane summaryPane;

	private DefaultTableModel allEldersData;
	private JScrollPane elderDataPane;

	private JTable MorningTable;
	private JTable AfterNoonTable;
	private JTable NoonTable;

	private DefaultTableModel Morningmodel;
	private DefaultTableModel Afternoonmodel;
	private DefaultTableModel Noonmodel;


	private JTextField SearchField;

	public MedManagePanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Dosage Management");
		lblNewLabel.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 25, 300, 41);
		add(lblNewLabel);


		try {
			allEldersData = DosageTableHelper.getElderlyFromQueryManagement("");
			eldertable = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		elderDataPane = new JScrollPane(DosageObject.managementTableModel(eldertable));
		elderDataPane.setBounds(25, 126, 469, 504);
		add(elderDataPane);
		eldertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
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
							Morningmodel=DosageTableHelper.getElderlyFromQueryManagementDos("morning",rs.getString("name"),so);
							MorningTable.setModel(Morningmodel);
							DosageTableHelper.AddManagementModel(MorningTable, so);
							DosageTableHelper.TableListen(MorningTable,so);
						}
						else{
							Morningmodel=DosageObject.buildDefaultManageModel();
							MorningTable.setModel(Morningmodel);
							DosageTableHelper.AddManagementModel(MorningTable, so);
							DosageTableHelper.TableListen(MorningTable,so);
						}
						if(rs.getBlob("afternoondosage")!=null){
							Afternoonmodel=DosageTableHelper.getElderlyFromQueryManagementDos("afternoon",rs.getString("name"),so);
							AfterNoonTable.setModel(Afternoonmodel);
							DosageTableHelper.AddManagementModel(AfterNoonTable, so);
							DosageTableHelper.TableListen(AfterNoonTable,so);
						}
						else{
							Afternoonmodel=DosageObject.buildDefaultManageModel();
							AfterNoonTable.setModel(Afternoonmodel);
							DosageTableHelper.AddManagementModel(AfterNoonTable, so);
							DosageTableHelper.TableListen(AfterNoonTable,so);
						}
						if(rs.getBlob("noondosage") !=null){
							Noonmodel=DosageTableHelper.getElderlyFromQueryManagementDos("noon",rs.getString("name"),so);
							NoonTable.setModel(Noonmodel);
							DosageTableHelper.AddManagementModel(NoonTable, so);
							DosageTableHelper.TableListen(NoonTable,so);
						}
						else{
							Noonmodel=DosageObject.buildDefaultManageModel();
							NoonTable.setModel(Noonmodel);
							DosageTableHelper.AddManagementModel(NoonTable, so);
							DosageTableHelper.TableListen(NoonTable,so);
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

		Morningmodel=new DefaultTableModel(
				new Object[][] {},
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
				Morningmodel.addRow(new Object [][] {{"", null,null}});
				Morningmodel.setValueAt("-Selection-", MorningTable.getRowCount()-1,0);
				DosageTableHelper.AddManagementModel(MorningTable, so);
			}
		});
		btnMorningAdd.setBounds(781, 272, 89, 20);
		add(btnMorningAdd);

		JButton btnRemoveMorning = new JButton("REMOVE");
		btnRemoveMorning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MorningTable.getSelectedRow()<0 || MorningTable.getSelectedColumn()==0){
					JOptionPane.showMessageDialog(null,"Select value To Remove");
				}
				else{
					Morningmodel.removeRow(MorningTable.getSelectedRow());
				}

			}
		});
		btnRemoveMorning.setBounds(880, 272, 89, 20);
		add(btnRemoveMorning);


		Afternoonmodel=new DefaultTableModel(
				new Object[][] {},
				new String[] {"Description", "Prescription", "Medication Type","Dosage"}
				);
		AfterNoonTable = new JTable(Afternoonmodel);
		JScrollPane AfterNoonPane = new JScrollPane(AfterNoonTable);
		AfterNoonPane.setBounds(525, 400, 444, 86);
		add(AfterNoonPane);

		JButton btnAfternoonAdd = new JButton("ADD");
		btnAfternoonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afternoonmodel.addRow(new Object [][] {{"", null,null}});
				Afternoonmodel.setValueAt("-Selection-", AfterNoonTable.getRowCount()-1,0);
				DosageTableHelper.AddManagementModel(AfterNoonTable, so);
			}
		});
		btnAfternoonAdd.setBounds(781, 382, 89, 20);
		add(btnAfternoonAdd);

		JButton btnRemoveAfternoon = new JButton("REMOVE");
		btnRemoveAfternoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AfterNoonTable.getSelectedRow()<0 || AfterNoonTable.getSelectedColumn()==0){
					JOptionPane.showMessageDialog(null,"Select value To Remove");
				}
				else{
					Afternoonmodel.removeRow(AfterNoonTable.getSelectedRow());
				}
			}
		});
		btnRemoveAfternoon.setBounds(880, 382, 89, 20);
		add(btnRemoveAfternoon);

		Noonmodel=new DefaultTableModel(
				new Object[][] {},
				new String[] {"Description", "Prescription", "Medication Type","Dosage"}
				);
		NoonTable = new JTable(Noonmodel);
		JScrollPane NoonPane = new JScrollPane(NoonTable);
		NoonPane.setBounds(525, 508, 444, 86);
		add(NoonPane);

		JButton btnNoonAdd = new JButton("ADD");
		btnNoonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Noonmodel.addRow(new Object [][] {{"", null,null}});
				Noonmodel.setValueAt("-Selection-", NoonTable.getRowCount()-1,0);
				DosageTableHelper.AddManagementModel(NoonTable, so);
			}
		});
		btnNoonAdd.setBounds(781, 489, 89, 20);
		add(btnNoonAdd);

		JButton btnRemoveNoon = new JButton("REMOVE");
		btnRemoveNoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NoonTable.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null,"Select value To Remove");
				}
				else{
					Noonmodel.removeRow(NoonTable.getSelectedRow());
				}
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
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					allEldersData = DosageTableHelper.getElderlyFromQueryManagement("%" + SearchField.getText() + "%");
					eldertable.setModel(allEldersData);
					DosageObject.managementTableModel(eldertable);

				} catch (SQLException e1) {
					e1.printStackTrace();
				};
			}
		});

		btnSearch.setBounds(213, 92, 89, 23);
		add(btnSearch);

		JButton btnSaveChange = new JButton("SAVE CHANGES");
		btnSaveChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!ElderIDField.getText().equalsIgnoreCase("")){
					ElderData.UpdateDosageSummary(so, summaryPane.getText(), Integer.parseInt(ElderIDField.getText()));
					
					if(Morningmodel.getRowCount()!=0){
						DosageObject.updateProcessTable(MorningTable);
						DosageObject.setTableManage("morning", Integer.parseInt(ElderIDField.getText()), DosageObject.updateProcessTable(MorningTable), 0);
					}
					else{
						DosageObject.setTableManage("morning", Integer.parseInt(ElderIDField.getText()),null, 1);
					}
					if(Afternoonmodel.getRowCount()!=0){
						DosageObject.updateProcessTable(AfterNoonTable);
						DosageObject.setTableManage("afternoon", Integer.parseInt(ElderIDField.getText()), DosageObject.updateProcessTable(AfterNoonTable), 0);
					}
					else{
						DosageObject.setTableManage("afternoon", Integer.parseInt(ElderIDField.getText()),null, 1);
					}
					if(Noonmodel.getRowCount()!=0){
						DosageObject.updateProcessTable(NoonTable);
						DosageObject.setTableManage("noon", Integer.parseInt(ElderIDField.getText()), DosageObject.updateProcessTable(NoonTable), 1);
					}
					// REFRESH MODEL
					try {
						allEldersData = DosageTableHelper.getElderlyFromQueryManagement("");
						eldertable.setModel(allEldersData);
						DosageObject.managementTableModel(eldertable);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Changes has been saved!");
				}
				else{
					JOptionPane.showMessageDialog(null, "No Changes!");
				}
			}
		});
		btnSaveChange.setBounds(828, 607, 140, 23);
		add(btnSaveChange);

		JButton btnExit = new JButton("Exit Management");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout mainCards = (CardLayout) MedPanel.MedCardPanel.getLayout();
				mainCards.show(MedPanel.MedCardPanel, MedPanel.MMAINPANEL);
			}
		});
		btnExit.setBounds(525, 607, 140, 23);
		add(btnExit);
	}
	
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		JPanel panel=new MedManagePanel();
		frame.setBounds(0, 0, 1000, 810);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
