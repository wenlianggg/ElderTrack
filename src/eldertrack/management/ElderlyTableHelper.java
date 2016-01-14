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

import eldertrack.db.SQLObject;


public class ElderlyTableHelper {
	public static SQLObject so = new SQLObject();
	public static DefaultTableModel getElderlyFromQuery(String search) throws SQLException {
		search = (search.equalsIgnoreCase("")) ? "%" : search;
		return buildTableModel(so.getResultSet("SELECT id, name, dob, nric, gender, room, bed, address FROM et_elderly WHERE name LIKE ?", search));
	}
	
	// Method from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    columnNames.add("ID");
	    columnNames.add("NAME");
	    columnNames.add("DOB");
	    columnNames.add("NRIC");
	    columnNames.add("GENDER");
	    columnNames.add("ROOM");
	    columnNames.add("BED");
	    columnNames.add("ADDRESS");
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
	    JTable toDoTable = new JTable(getElderlyFromQuery(""));
	    JScrollPane jpane = new JScrollPane(toDoTable);
	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    panel.add(jpane);
	    frame.getContentPane().add(new JScrollPane(panel));
	    frame.setVisible(true);
	}
}
