package eldertrack.login;

import java.sql.*;

import eldertrack.db.SQLConnect;

public class LoginUser{
	String username;
	char[] password;
	String firstname;
	String lastname;
	boolean accesslevel;
	
	LoginUser(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		// If username does not exist, throw NoSuchUserException
		ResultSet rs = null;
		// Get user as resultset
		try {
			rs = SQLConnect.getResultSet("SELECT * FROM et_staff WHERE username='" + username + "'");
			if (rs.next() == false) {
				throw new NoSuchUserException();
			// If password is wrong, throw WrongPasswordException
			} else if (password.equals("wrong")) {
				throw new WrongPasswordException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
