// SQLite Connection Tools (object version)
// By Wen Liang :D

package eldertrack.db;

import java.sql.*;
import java.util.ArrayList;

public class SQLObject {
	Connection c = null;
	PreparedStatement prpstmt = null;
	
	public SQLObject() throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
	}
	// Takes in a statement and a query
	public ResultSet getResultSet(String statement, String query) throws SQLException {
		ArrayList<String> qarray = new ArrayList<String>();
		qarray.add(query);
		return getResultSet(statement, qarray);
	}
	
	// Takes in a statement and many queries
	public ResultSet getResultSet(String statement, ArrayList<String> queries) throws SQLException{
		PreparedStatement prpstmt = c.prepareStatement(statement);
		if (queries.size() != 0)
			for(int i = 1; i <= queries.size(); i++) {
				System.out.println(queries.get(i-1));
				prpstmt.setString(i, queries.get(i-1));
			}
		this.prpstmt = prpstmt;
		return prpstmt.executeQuery();
	}
	
	// Takes in a statement to retrieve data table
	public ResultSet getResultSet(String statement) throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		this.prpstmt = prpstmt;
		return prpstmt.executeQuery();
	}
	
	// Takes in a prepared statement to retrieve data
	public ResultSet getResultSet(PreparedStatement prpstmt) throws SQLException {
		this.prpstmt = prpstmt;
		return this.prpstmt.executeQuery();
	}
	
	// Execute Prepared Statement from Object to get Result Set
	public ResultSet getResultSet() throws SQLException {
		if (this.prpstmt == null)
			return null;
		else
			return this.prpstmt.executeQuery();
	}
	
	
	// Takes in a statement to update table
	public int executeUpdate(String statement) throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		c.setAutoCommit(false);
		PreparedStatement prpstmt = c.prepareStatement(statement);
		this.prpstmt = prpstmt;
		return this.prpstmt.executeUpdate();
	}
	
	// Takes in a statement to update table
	public int executeUpdate(String statement, String toUpdate) throws SQLException {
		ArrayList<String> qarray = new ArrayList<String>();
		qarray.add(toUpdate);
		return executeUpdate(statement, qarray);
	}
	

	
	// Takes in a statement and many variables to update
	public int executeUpdate(String statement, ArrayList<String> variables) throws SQLException{
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
		this.prpstmt = prpstmt;
		return this.prpstmt.executeUpdate();
	}
	
	// Update using Prepared Statement within Object
	public int executeUpdate() throws SQLException {
		if (this.prpstmt == null)
			return 0;
		else
			return this.prpstmt.executeUpdate();
	}
	
	// Getter and mutator methods
	public Connection getC() {
		return c;
	}
	public void setC(Connection c) {
		this.c = c;
	}
	public PreparedStatement getPrpstmt() {
		return prpstmt;
	}
	public void setPrpstmt(PreparedStatement prpstmt) {
		this.prpstmt = prpstmt;
	}
	public void close() throws SQLException {
		c.close();
	}

	// Main method for testing purposes
	public static void main(String args[]) {
		try {
			SQLObject so = new SQLObject();
			ResultSet rs = so.getResultSet("SELECT * FROM et_staff;");
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
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
