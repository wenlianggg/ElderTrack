package eldertrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLObject {
	private Connection con;
	private PreparedStatement prpstmt;
	private String url = "jdbc:mysql://eldertrackdb.ctfjtggc5l0j.ap-southeast-1.rds.amazonaws.com:3306/eldertrack";
	private String dbuser = "eldertrackadmin";
	private String dbpw = "eldertrack4321";

	public SQLObject() {
		try { 
			Class.forName("org.gjt.mm.mysql.Driver"); 
			System.out.println("Driver Successfully Found."); 
			con = DriverManager.getConnection(url, dbuser, dbpw); 
			System.out.println("Successfully connected to " + url); 
		} catch (ClassNotFoundException e) { 
			System.out.println("Driver Not Found, exiting.."); 
			e.printStackTrace();
		} catch (java.sql.SQLException e) { 
			System.out.println("Failed to connect to "+ url); 
			e.printStackTrace();
		}
	}
	
	/********************************************************
	 * Method Name : testDriver
	 * Input Parameter : nil 
	 * Purpose : To test if the driver is properly installed
	 * Return :nil
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
	 * Return : resultSet (records from the query)
	 ************************************************************/
	public ResultSet readRequest(String dbQuery) {
		ResultSet rs = null;
		System.out.println("DB Query: " + dbQuery);
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
	 * Method Name : updateRequest 
	 * Input Parameter : String (database query) 
	 * Purpose : Execute update using the database query 
	 * Return : int (count is 1 if successful)
	 ***********************************************************/
	public int updateRequest(String dbQuery) {
		int count = 0;
		System.out.println("DB Query: " + dbQuery);
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
	 * Return : Prepared Statement
	 ***********************************************************/
	public PreparedStatement getPreparedStatementWithKey(String dbQuery) {
		PreparedStatement pstmt = null;
		System.out.println("DB prepare statement: " + dbQuery);
		try {
			// create a statement object
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
	 * Return : Prepared Statement
	 ***********************************************************/
	public PreparedStatement getPreparedStatement(String dbQuery) {
		PreparedStatement pstmt = null;
		System.out.println("DB prepare statement: " + dbQuery);
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
	 * Return : nil
	 **********************************************************/
	public void terminate() {
		// close connection
		try {
			con.close();
			System.out.println("Connection is closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] arg)throws Exception{
		SQLObject db = new SQLObject();
		ResultSet rs = db.getResultSet("SELECT * FROM et_staff;");
		while (rs.next()) {
			System.out.println("\tID = " + rs.getInt("staffid") );
			System.out.println("\tUSERNAME = " + rs.getString("username") );
			System.out.println("\tPASSWORD = " + rs.getString("password"));
			System.out.println("\tPERSON NAME = " + rs.getString("firstname") + " " + rs.getString("lastname"));
		}
		db.terminate();
	}
	
	public ResultSet getResultSet(String statement, String query) throws SQLException {
		ArrayList<String> qarray = new ArrayList<String>();
		qarray.add(query);
		return getResultSet(statement, qarray);
	}
	
	// Takes in a statement and many queries
	public ResultSet getResultSet(String statement, ArrayList<String> queries) throws SQLException{
		PreparedStatement prpstmt = con.prepareStatement(statement);
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
		con.setAutoCommit(false);
		PreparedStatement prpstmt = con.prepareStatement(statement);
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
		con.setAutoCommit(false);
		PreparedStatement prpstmt = con.prepareStatement(statement);
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
		con.setAutoCommit(false);
		PreparedStatement prpstmt = con.prepareStatement(statement);
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
}
