package eldertrack.db;

import java.sql.*;
import java.util.ArrayList;

@Deprecated
public class SQLConnect {
	static Connection c = null;

	// Takes in a statement and a query
	public static ResultSet getResultSet(String statement, String query) throws SQLException {
		SQLObject db = new SQLObject();
		ResultSet rs = null;
		rs = db.getResultSet(statement, query);
		return rs;
	}
	
	// Takes in a statement and many queries
	public static ResultSet getResultSet(String statement, ArrayList<String> queries) throws SQLException{
		SQLObject db = new SQLObject();
		ResultSet rs = null;
		rs = db.getResultSet(statement, queries);
		return rs;
	}
	
	// Takes in a statement
	public static ResultSet getResultSet(String statement) throws SQLException {
		SQLObject db = new SQLObject();
		ResultSet rs = null;
		rs = db.getResultSet(statement);
		return rs;
	}
	
	public static void main(String args[]) {
    try {	
    	ResultSet rs = getResultSet("SELECT * FROM et_staff;");
      	while (rs.next()) {
			System.out.println("\tID = " + rs.getInt("staffid") );
			System.out.println("\tUSERNAME = " + rs.getString("username") );
			System.out.println("\tSALTED HASH = " + rs.getString("password"));
			System.out.println("\tSALT VALUE = " + rs.getString("salt"));
			System.out.println("\tPERSON NAME = " + rs.getString("firstname") + " " + rs.getString("lastname"));
			System.out.println();
      	}
      rs.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
}
