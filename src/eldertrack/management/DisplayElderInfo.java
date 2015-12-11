package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLConnect;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		Connection c = null;
		ResultSet rs = SQLConnect.getResultSet("SELECT * FROM et_elderly");
		try{
			
		}catch (Exception e){
			
		}
		
	}
}
