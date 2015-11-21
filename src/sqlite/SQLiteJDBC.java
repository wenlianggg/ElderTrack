package sqlite;

import java.sql.*;

public class SQLiteJDBC {
  public static void main(String args[])  {
    Connection c = null;
    Statement stmt = null;
    try {
      c = DriverManager.getConnection("jdbc:sqlite:db\\MySQLiteDB");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM et_staff;" );
      while (rs.next()) {
         int staffid = rs.getInt("staffid");
         String username = rs.getString("username");
         String password = rs.getString("password");
         String firstname = rs.getString("firstname");
         String lastname = rs.getString("lastname");
         System.out.println( "ID = " + staffid );
         System.out.println( "USERNAME = " + username );
         System.out.println( "PASSWORD = " + password );
         System.out.println( "NAME = " + firstname + " " + lastname);
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
}
