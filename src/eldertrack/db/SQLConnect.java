// SQLite Connection Tools (static, non-object version)
// By Wen Liang :D

package eldertrack.db;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnect {
	static Connection c = null;
	// Takes in a statement and a query
	public static ResultSet getResultSet(String statement, String query) throws SQLException {
		ArrayList<String> qarray = new ArrayList<String>();
		qarray.add(query);
		return getResultSet(statement, qarray);
	}
	
	// Takes in a statement and many queries
	public static ResultSet getResultSet(String statement, ArrayList<String> queries) throws SQLException{
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		if (queries.size() != 0)
			for(int i = 1; i <= queries.size(); i++) {
				System.out.println(queries.get(i-1));
				prpstmt.setString(i, queries.get(i-1));
			}
		return prpstmt.executeQuery();
	}
	
	// Takes in a statement
	public static ResultSet getResultSet(String statement) throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		return prpstmt.executeQuery();
	}
	
	// Takes in a prepared statement to retrieve data
	public ResultSet getResultSet(PreparedStatement prpstmt) throws SQLException {
		return prpstmt.executeQuery();
	}
	
	// Takes in a statement to update table
	public static int executeUpdate(String statement) throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		return prpstmt.executeUpdate();
	}
	
	// Takes in a statement to update table
	public static int executeUpdate(String statement, String toUpdate) throws SQLException {
		ArrayList<String> qarray = new ArrayList<String>();
		qarray.add(toUpdate);
		return executeUpdate(statement, qarray);
	}
	
	// Takes in a statement and many variables to update
	public static int executeUpdate(String statement, ArrayList<String> variables) throws SQLException{
		int rowchanges = -1;
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		for(int i = 1; i <= variables.size(); i++) {
			System.out.println(variables.get(i-1));
			prpstmt.setString(i, variables.get(i-1));
		}
		rowchanges = prpstmt.executeUpdate();
		return rowchanges;
	}
	
	// Takes in a prepared statement to update table
	public int executeUpdate(PreparedStatement prpstmt) throws SQLException {
		return prpstmt.executeUpdate();
	}
	
	public static void main(String args[]) {
    Connection c = null;
    try {
      c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
      c.setAutoCommit(false);
      ResultSet rs = getResultSet("SELECT * FROM et_staff;");
      while (rs.next()) {
         int staffid = rs.getInt("staffid");
         String username = rs.getString("username");
         String password = rs.getString("password");
         String firstname = rs.getString("firstname");
         String lastname = rs.getString("lastname");
         System.out.println( "ID = " + staffid );
         System.out.println( "USERNAME = " + username );
         System.out.println( "PASSWORD = " + password );
         System.out.println( "PERSON NAME = " + firstname + " " + lastname);
         System.out.println("");
      }
      rs.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
}
