package eldertrack.management;
import java.sql.*;

import eldertrack.db.SQLObject;

public class DisplayStaffInfo {
public static void main(String[] args) throws SQLException {
		SQLObject so = new SQLObject();
		ResultSet rs = so.getResultSet("SELECT * FROM et_staff");
		
		while(rs.next()){
		System.out.println("Name: "+rs.getString("firstname")+" " +rs.getString("lastname") );
		System.out.println("");
		}
	}
}
