package eldertrack.misc;

import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class BedConflictChecker {
	private static final String SQL_STRING = "SELECT id,room,bed FROM et_elderly GROUP BY room,bed HAVING count(*) > 1";
	
	public static void main(String[] args) {
		SQLObject so = new SQLObject();
		ResultSet rs;
		try {
			rs = so.getResultSet(SQL_STRING);
			System.out.println("------------");
			if (rs != null)
				while(rs.next()) {
					System.out.println("Duplicate Bed Elder ID: " + rs.getInt("id"));
					System.out.println(rs.getString("room") + "-" + rs.getString("bed"));
					System.out.println("------------");
				} 
				else
					System.out.println("Null Result");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}
		
}
