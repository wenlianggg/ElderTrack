package eldertrack.login;

import java.sql.*;

import eldertrack.db.SQLConnect;
import org.apache.commons.codec.digest.DigestUtils;

public class StaffSession{
	private String username;
	private String hpassword;
	private String firstname;
	private String lastname;
	private boolean exists;
	private boolean accesslevel;
	private boolean passwordcorrect = false;
	
	StaffSession(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		hpassword = DigestUtils.sha512Hex(new String(password));
		// If username does not exist, throw NoSuchUserException
		ResultSet rs = null;
		try {
			// Get resultset
			rs = SQLConnect.getResultSet("SELECT * FROM et_staff WHERE username='" + username + "'");
			// If resultset is null, it means that no such user is found
			if (rs == null) {
				this.exists = false;
				throw new NoSuchUserException();
			}
			this.exists = true;
			// Compare and verify strings
			System.out.println("User Input: " + new String(password) + " vs " + rs.getString("password"));
			if (rs.getString("password").equals(new String(password))) {
				this.accesslevel = rs.getBoolean("accesslevel");
				passwordcorrect = true;
			} else {
				throw new WrongPasswordException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAuthenticated() {
		if(accesslevel == true && passwordcorrect == true)
			return true;
		else
			return false;
	}
	
	public boolean isUserFound() {
		return this.exists;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getFullName() {
		return this.firstname + this.lastname;
	}
}
