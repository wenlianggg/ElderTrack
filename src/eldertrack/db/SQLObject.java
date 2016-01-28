package eldertrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLObject {
	private static int DEBUG_LEVEL = 1;
	/************************************
	 * Debug Levels
	 * ---------------------
	 * 0 - Show no SQL Information in Console
	 * 1 - Show source of SQL in Console
	 * 2 - Show query executed in Console
	 ************************************/
	private Connection con;
	private String url = "jdbc:mysql://eldertrackdb.ctfjtggc5l0j.ap-southeast-1.rds.amazonaws.com:3306/eldertrack";
	private String dbuser = "eldertrackadmin";
	private String dbpw = "eldertrack4321";

	public SQLObject() {
		try { 
			Class.forName("org.gjt.mm.mysql.Driver");
			if (LDBConfig.useLocal)
				con = DriverManager.getConnection(LDBConfig.url, LDBConfig.dbuser, LDBConfig.dbpw);
			else
				con = DriverManager.getConnection(url, dbuser, dbpw); 
			if (DEBUG_LEVEL >= 1)
				System.out.println("DB Connection Initiated | SOURCE: " + new Exception().getStackTrace()[1].getClassName());
		} catch (ClassNotFoundException e) { 
			System.out.println("Driver Not Found!"); 
			e.printStackTrace();
			try {
			Thread.sleep(30);
			} catch (InterruptedException e1) {
				e.printStackTrace();
			}
			System.out.println("Exiting!");
			System.exit(1);
		} catch (java.sql.SQLException e) { 
			System.out.println("Failed to connect to: " + url);
			e.printStackTrace();
			try {
			Thread.sleep(30);
			} catch (InterruptedException e1) {
				e.printStackTrace();
			}
			System.out.println("Exiting!");
			System.exit(1);
		}
	}
	
	/********************************************************
	 * Method Name : testDriver
	 * Input Parameter : nil 
	 * Purpose : To test if the driver is properly installed
	 * Returns : void
	 *******************************************************/
	public void testDriver() throws Exception { 
		System.out.println("Testing Driver... "); 
	try { 
		Class.forName("org.gjt.mm.mysql.Driver"); 
		System.out.println("\tDriver Successfully Found."); 
	} catch (ClassNotFoundException e) { 
		System.out.println("\tDriver Not Found, exiting.."); 
			throw(e); 
		} 
	}
	
	/************************************************************
	 * Method Name : readRequest 
	 * Input Parameter : String (database query) 
	 * Purpose : Obtain the result set from the database query 
	 * Returns : resultSet (records from the query)
	 ************************************************************/
	public ResultSet getResultSet(String dbQuery) {
		ResultSet rs = null;
		if (DEBUG_LEVEL >= 2)
			System.out.println("DB Query | " + dbQuery);
		try {
			// create a statement object
			Statement stmt = con.createStatement();
			// execute an SQL query and get the result
			rs = stmt.executeQuery(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/***********************************************************
	 * Method Name : executeUpdate 
	 * Input Parameter : String (database query) 
	 * Purpose : Execute update using the database query 
	 * Return : int (count is 1 if successful)
	 ***********************************************************/
	public int executeUpdate(String dbQuery) {
		int count = 0;
		if (DEBUG_LEVEL >= 2)
			System.out.println("DB Query | " + dbQuery);
		try {
			// create a statement object
			Statement stmt = con.createStatement();
			// execute an SQL query and get the result
			count = stmt.executeUpdate(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/***********************************************************
	 * Method Name : getPreparedStatement 
	 * Input Parameter : String (database query) 
	 * Purpose : Gets Prepared Statement using the database query 
	 * Returns : Prepared Statement
	 ***********************************************************/
	public PreparedStatement getPreparedStatementWithKey(String dbQuery) {
		PreparedStatement pstmt = null;
		try {
			// create a statement object
			if (DEBUG_LEVEL >= 2)
				System.out.println("DB Preparing Statement \t| " + dbQuery);
			pstmt = con.prepareStatement(dbQuery,Statement.RETURN_GENERATED_KEYS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}	
	
	/***********************************************************
	 * Method Name : getPreparedStatement 
	 * Input Parameter : String (database query) 
	 * Purpose : Gets Prepared Statement using the database query 
	 * Returns : Prepared Statement
	 ***********************************************************/
	public PreparedStatement getPreparedStatement(String dbQuery) {
		PreparedStatement pstmt = null;
		System.out.println("DB Preparing Statement \t| " + dbQuery);
		try {
			// create a statement object
			pstmt = con.prepareStatement(dbQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	
	/***********************************************************
	 * Method Name : terminate 
	 * Input Parameter : nil 
	 * Purpose : Close database connection 
	 * Returns : void
	 ***********************************************************/
	public void terminate() {
		// close connection
		try {
			con.close();
			System.out.println("Connection is closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Takes in a statement and a query
	public ResultSet getResultSet(String statement, String toRetrieve) throws SQLException {
		PreparedStatement prpstmt = this.getPreparedStatementWithKey(statement);
		prpstmt.setString(1, toRetrieve);
		return prpstmt.executeQuery();
	}
	
	// Takes in a statement and many queries
	public ResultSet getResultSet(String statement, ArrayList<String> queries) throws SQLException{
		PreparedStatement prpstmt = this.getPreparedStatementWithKey(statement);
		if (queries.size() != 0)
			for(int i = 1; i <= queries.size(); i++) {
				prpstmt.setString(i, queries.get(i-1));
			}
		return prpstmt.executeQuery();
	}
	
	
	// Takes in a statement to update table
	public int executeUpdate(String statement, String toUpdate) throws SQLException {
		int rowchanges = -1;
		PreparedStatement prpstmt = this.getPreparedStatementWithKey(statement);
		rowchanges = prpstmt.executeUpdate();
		return rowchanges;
	}
	
	// Takes in a statement and many variables to update
	public int executeUpdate(String statement, ArrayList<String> variables) throws SQLException{
		int rowchanges = -1;
		PreparedStatement prpstmt = this.getPreparedStatementWithKey(statement);
		for(int i = 1; i <= variables.size(); i++) {
			prpstmt.setString(i, variables.get(i-1));
		}
		rowchanges = prpstmt.executeUpdate();
		return rowchanges;
	}
	
	public static void main(String[] arg)throws Exception{
		SQLObject so = new SQLObject();
		ResultSet rs = so.getPreparedStatement("SELECT * FROM et_staff;").executeQuery();
		while (rs.next()) {
			System.out.println("\tID = " + rs.getInt("staffid") );
			System.out.println("\tUSERNAME = " + rs.getString("username") );
			System.out.println("\tPASSWORD = " + rs.getString("password"));
			System.out.println("\tPERSON NAME = " + rs.getString("firstname") + " " + rs.getString("lastname"));
		}
		so.terminate();
	}
}
