package eldertrack.medical;

import javax.swing.table.AbstractTableModel;

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

//	  public Class getColumnClass(int column) {
//	    return (getValueAt(0, column).getClass());
//	  }
// Not used and causing warnings, sorry for my OCD :( -WL
	  
	  public void setValueAt(Object value, int row, int column) {
	    rowData[row][column] = value;
	  }

	  public boolean isCellEditable(int row, int column) {
	    return (column != 0);
	  }
	  
	 

	  
}
