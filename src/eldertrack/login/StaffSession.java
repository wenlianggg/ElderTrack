package eldertrack.login;

import java.sql.*;

import eldertrack.db.SQLObject;

import org.apache.commons.codec.digest.DigestUtils;

public class StaffSession{
	private int staffid;
	private String username;
	private String firstname;
	private String lastname;
	private boolean exists = false;
	private boolean passwordcorrect = false;
	private int accesslevel = 0;
	
	StaffSession(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		// If user does not exist, throw NoSuchUserException
		ResultSet rs = null;
		SQLObject so;
		try {
			so = new SQLObject();
			// Get ResultSet
			rs = so.getResultSet("SELECT * FROM et_staff WHERE username = ?", username.toLowerCase());
			// If ResultSet is null, it means that no such user is found
			if (rs == null || rs.next() == false) {
				throw new NoSuchUserException();
			}
			this.exists = true;
			// Compare and verify strings
			System.out.println("User Input: " + DigestUtils.sha512Hex(new String(password)) + " vs " + rs.getString("password"));
			if (rs.getString("password").equals(DigestUtils.sha512Hex(new String(password)))) {
				this.staffid = rs.getInt("staffid");
				this.accesslevel = rs.getInt("accesslevel");
				passwordcorrect = true;
			} else {
				throw new WrongPasswordException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAuthenticated() {
		if(accesslevel > 0 && passwordcorrect == true)
			return true;
		else if (passwordcorrect == true) {
			System.out.println("Password incorrect!");
			return false;
		} else if (accesslevel < 0) {
			System.out.println("Password accepted, access not authorized!");
			return false;
		} else
			return false;
	}
	
	public boolean isUserFound() {
		return this.exists;
	}
	
	public int getStaffid() {
		return this.staffid;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getFullName() {
		return this.firstname + this.lastname;
	}
}
