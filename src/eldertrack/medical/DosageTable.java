package eldertrack.medical;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

public class DosageTable  {


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

}
