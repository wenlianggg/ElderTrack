package eldertrack.medical;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import eldertrack.db.SQLObject;

public class DosageSQL {
	
	static final SQLObject so = new SQLObject();

	public static void serializeDos(int id) throws SQLException {
		  ArrayList<DosageObject> DosingList=new ArrayList<DosageObject>();
		  
		  DosageObject dos1=new DosageObject("Head","For Head","Tablet","10");
		  DosageObject dos2=new DosageObject("Body","For Body","Tablet","20");
		  DosageObject dos3=new DosageObject("Toes","For Toes","Cream","30 Rubs");
		  
		  DosingList.add(dos1);
		  DosingList.add(dos2);
		  DosingList.add(dos3);
		  
		  PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET medication = ? WHERE id = ?");
		  statement.setObject(1, DosingList);
		  
		  statement.setInt(2, id);
		  statement.executeUpdate();
	  }
	
	
	@SuppressWarnings("unchecked")
	public static void deserializeDos() throws SQLException, ClassNotFoundException, IOException {
	    PreparedStatement statement = so.getPreparedStatementWithKey("SELECT medication FROM et_elderly WHERE id = ?");
	    statement.setInt(1, 1);
		ResultSet rs = statement.executeQuery();
		
		rs.next();
	    ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
	    ObjectInputStream is = new ObjectInputStream(in);
		Object retrieveDosBlob =(Object) is.readObject();
		ArrayList<DosageObject> dosing;
		if(retrieveDosBlob instanceof  ArrayList<?>){
			dosing=((ArrayList<DosageObject>) retrieveDosBlob);
		
		for(int i=0;i<dosing.size();i++){
		
		System.out.print("Dosage: ");
		dosing.get(i).print();
		}
		}
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		for(int k=1;k<9;k++){
			serializeDos(k);
		}
		deserializeDos();
	}
}
