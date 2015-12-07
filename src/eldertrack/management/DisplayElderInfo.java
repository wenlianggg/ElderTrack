package eldertrack.management;

import java.sql.*;

import eldertrack.db.SQLConnect;


public class DisplayElderInfo {
	public static void main(String[] args) throws SQLException {
		Connection c = null;
		
		try{
			//Establishing Connection to database
			c = DriverManager.getConnection("jdbc:sqlite:db\\SQLiteDB.db");
		    c.setAutoCommit(false);
			ResultSet rs = SQLConnect.getResultSet("SELECT * FROM et_elderly");
				while(rs.next()){
					//Retrieving data from database
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String nric = rs.getString("nric");
					String gender = rs.getString("gender");
					//Printing data from database
					System.out.println("ID: " + id);
					System.out.println("NAME: " + name);
					System.out.println("AGE: " + age);
					System.out.println("NRIC: " + nric);
					System.out.println("GENDER: " + gender);
					System.out.println("");
				}
				rs.close();
			    c.close();
			}catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
}
