package eldertrack.medical;

import java.io.*;
import java.sql.*;

import eldertrack.db.SQLObject;

public class DosageSQL {
	
	static final SQLObject so = new SQLObject();
    private ObjectInputStream is;
    
    public boolean store(int id, DosageObject dos) {
		  PreparedStatement statement;
		  try {
			  statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET medication = ? WHERE id = ?");
			  statement.setObject(1, dos);
			  statement.setInt(2, id);
			  statement.executeUpdate();
			  statement.close();
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return false;
		  }	catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  return true;
    }
    public DosageObject retrieve(int id) {
	    PreparedStatement ps;
	    DosageObject dos;
	    try {
		    ps = so.getPreparedStatementWithKey("SELECT medication FROM et_elderly WHERE id = ?");
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			rs.next();
		    is = new ObjectInputStream(new ByteArrayInputStream(rs.getBytes(1)));
			dos = (DosageObject) is.readObject();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	return null;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
		return dos;
	}
	public static void serializeDos() throws SQLException {
		  DosageObject dos=new DosageObject("Head","For Head","Tablet","10");
		  PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET medication = ? WHERE id = ?");
		  statement.setObject(1, dos);
		  statement.setInt(2, 1);
		  statement.executeUpdate();
	  }
	public static void deserializeDos() throws SQLException, ClassNotFoundException, IOException {
	    PreparedStatement statement = so.getPreparedStatementWithKey("SELECT medication FROM et_elderly WHERE id = ?");
	    statement.setInt(1, 1);
		ResultSet rs = statement.executeQuery();
		
		rs.next();
	    ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
	    ObjectInputStream is = new ObjectInputStream(in);
		DosageObject dosing = (DosageObject) is.readObject();
		
		System.out.print("Dosage: ");
		dosing.print();
		
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		//serializeDos();
		deserializeDos();
	}
}
