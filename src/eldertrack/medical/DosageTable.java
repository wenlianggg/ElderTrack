package eldertrack.medical;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
 
/** 
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class DosageTable extends JPanel {
    private boolean DEBUG = false;
    static int counter=1;
    public DosageTable() {
        super(new GridLayout(1,0));
 
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Description",
                                        "Prescription",
                                        "Medication Type",
                                        "Dosage",
                                        "Checked"};
        private Object[][] data = {
        		{ "", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE},
				{"", "", "", "",Boolean.FALSE}, };
 
        public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
 
            data[row][col] = value;
            fireTableCellUpdated(row, col);
 
            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
 
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
        public DefaultTableModel getElderlyFromQueryDos(String search) throws SQLException {
    		search = (search.equalsIgnoreCase("")) ? "%" : search;
    		SQLObject so = new SQLObject();
    		return (DefaultTableModel) buildTableModel(so.getResultSet("SELECT medication FROM et_elderly WHERE name LIKE ?", search));
    	}
    	// Method from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset

    	@SuppressWarnings("unchecked")
    	public AbstractTableModel buildTableModel(ResultSet rs) throws SQLException {
    		ArrayList<DosageObject> retrieveDosBlob = null;
    		ArrayList<ArrayList> storeList=new ArrayList<ArrayList>();
    		SQLObject so = new SQLObject();
    		try {
    			while(rs.next()){
    				//testing column can remove any time
    				ResultSetMetaData metaData = rs.getMetaData();

    				System.out.println(metaData.getColumnCount());

    				PreparedStatement statement = so.getPreparedStatementWithKey("SELECT medication FROM et_elderly WHERE id = ?");
    				
					statement.setInt(1, counter);

    				ResultSet rs1 = statement.executeQuery();
    				rs1.next();
    				ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
    				ObjectInputStream is;
    				is = new ObjectInputStream(in);
    				retrieveDosBlob = (ArrayList<DosageObject>) is.readObject();
    				storeList.add(retrieveDosBlob);
    				counter++;
    			}
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
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
    		// database print all IDs
    		// vector due to table



    		ArrayList<DosageObject> nowList=storeList.get(0);

    		for(int k=0;k<nowList.size();k++){
    			DosageObject dosing=nowList.get(k);
    			Vector<Object> vector = new Vector<Object>();
    			vector.add(dosing.getMedDescrip());
    			vector.add(dosing.getMedPrescrip());
    			vector.add(dosing.getMedType());
    			vector.add(dosing.getMedDosage());
    			vector.add(Boolean.FALSE);
    			data.add(vector);

    		}


    		

    		DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
    			private static final long serialVersionUID = 4234183862785566645L;
    			
    			@Override
                public Class getColumnClass(int columnNames) {
                    switch (columnNames) {
                        case 0:
                            return String.class;
                        case 1:
                            return String.class;
                        case 2:
                            return Integer.class;
                        case 3:
                            return Double.class;
                        default:
                            return Boolean.class;
                    }
                }
    					
    			
    			@Override
    			public boolean isCellEditable(int row, int column) {
    				return false;
    			}
    			
    			
    			
    		};
    		return dtm;
    	}
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        DosageTable newContentPane = new DosageTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}