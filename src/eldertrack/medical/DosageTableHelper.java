package eldertrack.medical;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import eldertrack.db.SQLObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class DosageTableHelper  {
	private static JTable toDoTable;
	private static JScrollPane jpane;
	private static int counter=1;
	public static DefaultTableModel getElderlyFromQueryDos(String timing,String position) throws SQLException {
		SQLObject so = new SQLObject();
		ResultSet rs = null;

		if(timing.equalsIgnoreCase("Morning")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT morningdosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		else if(timing.equalsIgnoreCase("Afternoon")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT afternoondosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		else if(timing.equalsIgnoreCase("Noon")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT noondosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		return (DefaultTableModel) buildTableModel(rs);
	}



	@SuppressWarnings("unchecked")
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		ArrayList<DosageObject> DosageList=null;


		System.out.println(rs);
		try {
			while(rs.next()){
				ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
				ObjectInputStream is = new ObjectInputStream(in);
				Object retrieveDosBlob =(Object) is.readObject();
				if(retrieveDosBlob instanceof  ArrayList<?>){
					DosageList=((ArrayList<DosageObject>) retrieveDosBlob);
				}
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// storing array list in an array list for future uses

		Vector<String> columnNames = new Vector<String>();

		columnNames.add("Description");
		columnNames.add("Prescription");
		columnNames.add("Medication Type");
		columnNames.add("Dosage");
		columnNames.add("Checked");

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int k=0;k<DosageList.size();k++){
			Vector<Object> vector = new Vector<Object>();
			vector.add(DosageList.get(k).getMedDescrip());
			vector.add(DosageList.get(k).getMedPrescrip());
			vector.add(DosageList.get(k).getMedType());
			vector.add(DosageList.get(k).getMedDosage());

			data.add(vector);
		}

		DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 4234183862785566645L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				  if (columnIndex < 4) {
		                return false;
		            } else {
		                return true;
		            }
			}
		};
		return dtm;
	}

// For Management
	public static DefaultTableModel getElderlyFromQueryManagement(String search) throws SQLException {
		search = (search.equalsIgnoreCase("")) ? "%" : search;
		SQLObject so = new SQLObject();
		return buildTableModelForManagement(so.getResultSet("SELECT id, name, gender, room, morningdosage,afternoondosage,noondosage FROM et_elderly WHERE name LIKE ?", search));
	}
	
	
	public static DefaultTableModel buildTableModelForManagement(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    columnNames.add("ID");
	    columnNames.add("NAME");
	    columnNames.add("GENDER");
	    columnNames.add("ROOM");
	    columnNames.add("MORNING");
	    columnNames.add("AFTERNOON");
	    columnNames.add("NOON");
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= 4; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        if(rs.getObject(5)!=null){
	        	vector.add("Added");
	        }
	        if(rs.getObject(6)!=null){
	        	vector.add("Added");
	        }
	        if(rs.getObject(7)!=null){
	        	vector.add("Added");
	        }
	        data.add(vector);
	    }
	    DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 4234183862785566645L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				  if (columnIndex < 4) {
		                return false;
		            } else {
		                return true;
		            }
			}
		};
	    return dtm;
	}
	public static DefaultTableModel getElderlyFromQueryManagementDos(String timing,String position) throws SQLException {
		SQLObject so = new SQLObject();
		ResultSet rs = null;

		if(timing.equalsIgnoreCase("Morning")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT morningdosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		else if(timing.equalsIgnoreCase("Afternoon")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT afternoondosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		else if(timing.equalsIgnoreCase("Noon")){
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT noondosage FROM et_elderly WHERE name = ?");
			stmt.setString(1,position);
			stmt.executeQuery();
			System.out.println(stmt);
			rs = stmt.getResultSet();
		}
		return (DefaultTableModel) buildTableModelManagementDos(rs);
	}



	@SuppressWarnings("unchecked")
	public static DefaultTableModel buildTableModelManagementDos(ResultSet rs) throws SQLException {
		ArrayList<DosageObject> DosageList=null;


		System.out.println(rs);
		try {
			while(rs.next()){
				ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
				ObjectInputStream is = new ObjectInputStream(in);
				Object retrieveDosBlob =(Object) is.readObject();
				if(retrieveDosBlob instanceof  ArrayList<?>){
					DosageList=((ArrayList<DosageObject>) retrieveDosBlob);
				}
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// storing array list in an array list for future uses

		Vector<String> columnNames = new Vector<String>();

		columnNames.add("Description");
		columnNames.add("Prescription");
		columnNames.add("Medication Type");
		columnNames.add("Dosage");
		columnNames.add("Selected");

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int k=0;k<DosageList.size();k++){
			Vector<Object> vector = new Vector<Object>();
			vector.add(DosageList.get(k).getMedDescrip());
			vector.add(DosageList.get(k).getMedPrescrip());
			vector.add(DosageList.get(k).getMedType());
			vector.add(DosageList.get(k).getMedDosage());

			data.add(vector);
		}

		DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 4234183862785566645L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				  if (columnIndex < 4) {
		                return false;
		            } else {
		                return true;
		            }
			}
		};
		return dtm;
	}
	// Debug-able main method
	public static void main(String[] args) throws SQLException {
		ArrayList<String> nameList= new ArrayList<String>();
		nameList.add("Lee Ching Chong");
		nameList.add("Lim Kuay Siak");
		nameList.add("Lee Ching Chong");

		toDoTable =new JTable(getElderlyFromQueryDos("morning",nameList.get(0)));
		

		String[] values = new String[] { "Not Feed", "Feed" };
		TableColumn col = toDoTable.getColumnModel().getColumn(4);
		col.setCellEditor(new MyComboBoxEditor(values));
		col.setCellRenderer(new MyComboBoxRenderer(values));

		jpane = new JScrollPane(toDoTable);
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 700, 543);
		panel.add(jpane);
		frame.getContentPane().add(new JScrollPane(panel));

		JButton btnNext = new JButton("Next");
		panel.add(btnNext);
		frame.setVisible(true);
		btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int notfeed=0;
				int feed=0;
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to procced?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				for(int k=0;k<toDoTable.getRowCount();k++){
					Object o=toDoTable.getValueAt(k, 4);
					if(o=="Not Feed" || o==null){
						notfeed++;
					}
					else{
						feed++;
					}
				}
				
				
				if(feed==toDoTable.getRowCount()){
				
				try {
					toDoTable.setModel(DosageTableHelper.getElderlyFromQueryDos("morning",nameList.get(counter)));
					String[] values = new String[] { "Not Feed", "Feed" };
					TableColumn col = toDoTable.getColumnModel().getColumn(4);
					col.setCellEditor(new MyComboBoxEditor(values));
					col.setCellRenderer(new MyComboBoxRenderer(values));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please check if you have filled in the required fields");
				}

				counter++;

			}
			}
		});

	}



}
@SuppressWarnings("rawtypes")
class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {

	private static final long serialVersionUID = 1319299961084034009L;

	@SuppressWarnings("unchecked")
	public MyComboBoxRenderer(String[] items) {
		super(items);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		setSelectedItem(value);
		return this;
	}
}

class MyComboBoxEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -1702063500403826596L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MyComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
