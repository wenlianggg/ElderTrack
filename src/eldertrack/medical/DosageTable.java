package eldertrack.medical;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

public class DosageTable extends AbstractTableModel {


	private static final long serialVersionUID = -5565944268398825729L;

	static Object rowData[][] = {
			{ "", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE}, };

		String columnNames[] = { "Description", "Prescription", "Medication Type", "Dosage","Feed"};
	  public int getColumnCount() {
	    return columnNames.length;
	  }
	
	  	
	  public static Object[][] getData(){
		return rowData;
	  }
	  	  public String getColumnName(int column) {
	    return columnNames[column];
	  }

	  public int getRowCount() {
	    return rowData.length;
	  }

	  public Object getValueAt(int row, int column) {
	    return rowData[row][column];
	  }
	  
	  public Class getColumnClass(int column) {
		  return (getValueAt(0, column).getClass());
	  }

	  
	  public void setValueAt(Object value, int row, int column) {
	    rowData[row][column] = value;
	  }

	  public boolean isCellEditable(int row, int column) {
	    return (column != 0);
	  }
	  
	  public static DefaultTableModel getElderlyFromQuery(String search) throws SQLException {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			SQLObject so = new SQLObject();
			return buildTableModel(so.getResultSet("SELECT id, name, room FROM et_elderly WHERE name LIKE ?", search));
		}
		
		// Method from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
		public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		    ResultSetMetaData metaData = rs.getMetaData();
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    columnNames.add("Description");
		    columnNames.add("Prescription");
		    columnNames.add("Medication Type");
		    columnNames.add("Dosage");
		    
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    // database print all IDs
		    
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
				private static final long serialVersionUID = 4234183862785566645L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		           return false;
		        }
		    };
		    return dtm;
		}
		
		// Debug-able main method
		public static void main(String[] args) throws SQLException {
		    JTable toDoTable = new JTable(getElderlyFromQuery(""));
		    JScrollPane jpane = new JScrollPane(toDoTable);
		    JPanel panel = new JPanel();
		    JFrame frame = new JFrame();
		    panel.add(jpane);
		    frame.getContentPane().add(new JScrollPane(panel));
		    frame.setVisible(true);
		}

	  
}
