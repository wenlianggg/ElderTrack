package eldertrack.misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

public class TableHelper {
	static SQLObject so = new SQLObject();
	
	/**
	 * Get basic information of elderly using optional search string
	 * @param search - Search Query
	 * @return DefaultTableModel - Search Results in a DefaultTableModel
	 */
	public static DefaultTableModel getElderlyBasic(String search) {
		ResultSet rs = null;
		boolean editable = false;
		String[] columns = {"ID", "Elderly Name", "Room Number"};
		try {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			rs = so.getResultSet("SELECT id, name, room FROM et_elderly WHERE name LIKE ?", search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getModel(rs, columns, editable);
	}
	
	/**
	 * Get detailed information of elderly using optional search string
	 * @param search - Search Query
	 * @return DefaultTableModel - Search Results in a DefaultTableModel
	 */
	public static DefaultTableModel getElderlyDetailed(String search) {
		ResultSet rs = null;
		boolean editable = false;
		String[] columns = {"ID", "NAME", "DOB", "NRIC", "G", "R", "B", "ADDRESS"};
		try {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			rs = so.getResultSet("SELECT id, name, dob, nric, gender, room, bed, address FROM et_elderly WHERE name LIKE ?", search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getModel(rs, columns, editable);
	}
	
	/**
	 * Get detailed information of staff using optional search string
	 * @param search - Search Query
	 * @return DefaultTableModel - Search Results in a DefaultTableModel
	 */
	public static DefaultTableModel getStaff(String search) {
		ResultSet rs = null;
		boolean editable = false;
		String[] columns = {"Staff ID", "First Name", "Last Name", "Date of Birth", "NRIC"};
		try {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			rs = so.getResultSet("SELECT staffid, firstname, lastname, dob, nric FROM et_staff WHERE firstname LIKE ?", search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getModel(rs, columns, editable);
	}
	
	/**
	 * Get meals in a table using optional search string
	 * @param search - Search Query
	 * @return DefaultTableModel - Search Results in a DefaultTableModel
	 */
	public static DefaultTableModel getMeals(String search) {
		ResultSet rs = null;
		boolean editable = false;
		String[] columns = {"ID", "Category", "Name", "Halal?"};
		try {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			rs = so.getResultSet("SELECT itemid, category, name, halal FROM et_menu WHERE name LIKE ?", search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getModel(rs, columns, editable);
	}
	
	/**
	 * @param rs - ResultSet
	 * @param colnames - String array of column names
	 * @param editable - Whether the cells should be editable
	 * @return DefaultTableModel
	 */
	private static DefaultTableModel getModel(ResultSet rs, String[] colnames, boolean editable) {
	    Vector<String> columnNames = new Vector<String>();
	    DefaultTableModel dtm = null;
		int columnCount = colnames.length;
		for(int i = 0; i < columnCount; i++)
		 	columnNames.add(colnames[i]);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {
			while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
	    if (!editable) {
	    	 dtm = new DefaultTableModel(data, columnNames) {
	    		 // Overriding cell editable
				private static final long serialVersionUID = 123L;
		        public boolean isCellEditable(int row, int column) {
		           return false;
		        }
		    };
	    } else {
	    	dtm = new DefaultTableModel(data, columnNames);
	    }
	    return dtm;
	}
	
	public static SQLObject getSQLInstance() {
		return so;
	}
	

	public static void main(String[] args) throws SQLException {
	    JTable toDoTable = new JTable(getElderlyBasic(""));
	    JScrollPane jpane = new JScrollPane(toDoTable);
	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setBounds(100,100,500,500);
	    panel.add(jpane);
	    frame.getContentPane().add(new JScrollPane(panel));
	    frame.setVisible(true);
	}
}
