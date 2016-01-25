package eldertrack.medical;

import java.awt.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import eldertrack.db.SQLObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

public class DosageTableHelper  {
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
		return (DefaultTableModel) buildTableModelForDos(rs);
	}



	@SuppressWarnings("unchecked")
	public static DefaultTableModel buildTableModelForDos(ResultSet rs) throws SQLException {
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
			vector.add("Not Feed");
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
	    Vector<String> columnNames = new Vector<String>();

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
				  if (columnIndex < 7) {
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
		return buildTableModelManagementDos(rs);
	}



	@SuppressWarnings("unchecked")
	public static DefaultTableModel buildTableModelManagementDos(ResultSet rs) throws SQLException {
		ArrayList<DosageObject> DosageList=null;
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

		};
		return dtm;
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
