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
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

public class DosageTableHelper {
	 static final SQLObject so = new SQLObject();
	 
	  public static DefaultTableModel getElderlyFromQueryDos(String search) throws SQLException {
			search = (search.equalsIgnoreCase("")) ? "%" : search;
			return buildTableModel(so.getResultSet("SELECT medication FROM et_elderly WHERE name LIKE ?", search));
		}
		// Method from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	  
		@SuppressWarnings("unchecked")
		public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
			ArrayList<DosageObject> retrieveDosBlob = null;
			ArrayList<ArrayList> storeList=new ArrayList<ArrayList>();
			int counter=1;
			try {
				while(rs.next()){
				ResultSetMetaData metaData = rs.getMetaData();
			    System.out.println(metaData.getColumnCount());
			    PreparedStatement statement = so.getPreparedStatementWithKey("SELECT medication FROM et_elderly WHERE id = ?");
				statement.setInt(1, counter);
				ResultSet rs1 = statement.executeQuery();
				rs1.next();
				ByteArrayInputStream in = new ByteArrayInputStream(rs1.getBytes(1));
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
			for(int i=0;i<storeList.size();i++){
				ArrayList<DosageObject> nowList=storeList.get(i);
				System.out.println(i);
				for(int k=0;k<nowList.size();k++){
					nowList.get(k).print();
				}
			}
		    Vector<String> columnNames = new Vector<String>();
		    
		    columnNames.add("Description");
		    columnNames.add("Prescription");
		    columnNames.add("Medication Type");
		    columnNames.add("Dosage");
		    columnNames.add("Checked");
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    // database print all IDs
		    // vector due to table
		   
		        Vector<Object> vector = new Vector<Object>();
		        
		        for (int columnIndex = 1; columnIndex <= 5; columnIndex++) {
		        	for(int i=0;i<retrieveDosBlob.size();i++){
			            vector.add(retrieveDosBlob.get(i).getMedDescrip());
			            vector.add(retrieveDosBlob.get(i).getMedPrescrip());
			            vector.add(retrieveDosBlob.get(i).getMedType());
			            vector.add(retrieveDosBlob.get(i).getMedDosage());
			            vector.add(Boolean.FALSE);
			            
		        	}
		        	
		        }
		        data.add(vector);
		   
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
		    JTable toDoTable = new JTable(getElderlyFromQueryDos(""));
		    JScrollPane jpane = new JScrollPane(toDoTable);
		    JPanel panel = new JPanel();
		    JFrame frame = new JFrame();
		    frame.setBounds(0, 0, 700, 500);
		    panel.add(jpane);
		    frame.getContentPane().add(new JScrollPane(panel));
		    frame.setVisible(true);
		   
		}
}
