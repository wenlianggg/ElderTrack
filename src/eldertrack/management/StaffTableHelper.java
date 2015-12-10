package eldertrack.management;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLConnect;

public class StaffTableHelper {
	public static DefaultTableModel getStaffFromQuery(String search) throws SQLException {
		search = (search.equalsIgnoreCase("")) ? "%" : search;
		return buildTableModel(SQLConnect.getResultSet("SELECT staffid, firstname, lastname FROM et_staff WHERE firstname LIKE ?", search));
	}
	
	// Method from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    columnNames.add("Staff ID");
	    columnNames.add("First Name");
	    columnNames.add("Last Name");
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    return new DefaultTableModel(data, columnNames);
	}
	
	// Debug-able main method
	public static void main(String[] args) throws SQLException {
	    JTable toDoTable = new JTable(getStaffFromQuery(""));
	    JScrollPane jpane = new JScrollPane(toDoTable);
	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    panel.add(jpane);
	    frame.getContentPane().add(new JScrollPane(panel));
	    frame.setVisible(true);
	}
}
