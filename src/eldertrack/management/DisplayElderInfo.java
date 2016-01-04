package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLObject;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		SQLObject so = new SQLObject();
		try{
			ResultSet rs = so.getResultSet("SELECT * FROM et_elderly");
			rs.next();
		}catch (Exception e){
			
		}
		
	}
}
