package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLConnect;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		
		ResultSet rs = SQLConnect.getResultSet("SELECT * FROM et_elderly");
	
	}
}
