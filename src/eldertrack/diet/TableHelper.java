package eldertrack.diet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

public class TableHelper {
	private static SQLObject so = new SQLObject();
	public static DefaultTableModel getElderlyFromQuery(String search) throws SQLException {
		search = (search.equalsIgnoreCase("")) ? "%" : search;
		String[] columns = {"ID", "Elderly Name", "Room Number"};
		ResultSet rs = so.getResultSet("SELECT id, name, room FROM et_elderly WHERE name LIKE ?", search);
		return getModel(rs, columns);
	}
	
	public static DefaultTableModel getMeals(String search) throws SQLException {
		search = (search.equalsIgnoreCase("")) ? "%" : search;
		String[] columns = {"ID", "Category", "Name", "Halal?"};
		ResultSet rs = so.getResultSet("SELECT itemid, category, name, halal FROM et_menu WHERE name LIKE ?", search);
		return getModel(rs, columns);
	}
	
	// Generate Default Table Model for Search String
	public static DefaultTableModel getModel(ResultSet rs, String[] colnames) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for(int i = 0; i < colnames.length; i++)
	    	columnNames.add(colnames[i]);
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 123L;
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
