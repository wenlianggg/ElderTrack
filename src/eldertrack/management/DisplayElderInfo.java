package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLObject;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		Connection c = null;
		SQLObject wanker = new SQLObject();
		try{
			ResultSet rs = wanker.getResultSet("SELECT * FROM et_elderly");
		}catch (Exception e){
			
		}
		
	}
}
