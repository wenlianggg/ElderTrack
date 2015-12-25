package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLConnect;
import eldertrack.db.SQLObject;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		Connection c = null;
		SQLObject wanker = new SQLObject();
		ResultSet rs = wanker.getResultSet("SELECT * FROM et_elderly");
		try{
			
		}catch (Exception e){
			
		}
		
	}
}
