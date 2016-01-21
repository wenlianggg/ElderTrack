package eldertrack.login;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import eldertrack.db.SQLObject;

import org.apache.commons.codec.digest.DigestUtils;

public class StaffSession{
	private int staffid = 0;
	private String username = "";
	private String firstname  = "";
	private String lastname  = "";
	private String nric  = "";
	private String stickynotes = "";
	private Date lastlogin;
	private boolean exists = false;
	private boolean passwordcorrect = false;
	private AccessLevel accesslevel = AccessLevel.NOACCESS;
	SQLObject so;
	
	StaffSession(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		// If user does not exist, throw NoSuchUserException
		ResultSet rs = null;
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
			if (rs.getString("password").equals(DigestUtils.sha512Hex(new String(password)))) {
				this.staffid = rs.getInt("staffid");
				switch(rs.getInt("accesslevel")) {
					case 1:
						this.accesslevel = AccessLevel.STAFF;
						break;
					case 2:
						this.accesslevel = AccessLevel.SRSTAFF;
						break;
					case 3:
						this.accesslevel = AccessLevel.ADMIN;
						break;
					case 4:
						this.accesslevel = AccessLevel.MANAGER;
						break;
					default:
						this.accesslevel = AccessLevel.NOACCESS;
						break;
				}
				this.firstname = rs.getString("firstname");
				this.lastname = rs.getString("lastname");
				this.nric = rs.getString("nric");
				this.stickynotes = rs.getString("staffnotes");
				passwordcorrect = true;
				Timestamp ts = rs.getTimestamp("lastlogin");
				if (ts != null)
					this.lastlogin = new Date(ts.getTime());
				else
					this.lastlogin = new Date();
				Date date = new Date();
				Timestamp timenow = new Timestamp(date.getTime());
				PreparedStatement ps = so.getPreparedStatement("UPDATE et_staff SET lastlogin=? WHERE staffid=?");
				ps.setTimestamp(1, timenow);
				ps.setInt(2, this.staffid);
				ps.executeUpdate();
			} else {
				throw new WrongPasswordException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static StaffSession createSession(String username, char[] passarray) {
		StaffSession session = null;
		try {
			session = new StaffSession(username, passarray);
		} catch (NoSuchUserException e) {
			System.out.println("No such user found!");
			return null;
		} catch (WrongPasswordException e) {
			System.out.println("Wrong password entered!");
			return null;
		}
		System.out.println("No errors logging in, successful!");
		return session;
	}
	
	public boolean isAuthenticated() {
		if(accesslevel != AccessLevel.NOACCESS && passwordcorrect == true)
			return true;
		else if (passwordcorrect != true) {
			System.out.println("Password incorrect!");
			return false;
		} else if (accesslevel == AccessLevel.NOACCESS) {
			System.out.println("Password accepted, access level not authorized!");
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
	
	public String getNric() {
		return this.nric;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getNotes() {
		return this.stickynotes;
	}
	
	public AccessLevel getAccessLevel() {
		return this.accesslevel;
	}
	
	public void setNotes(String text) {
		PreparedStatement ps = so.getPreparedStatement("UPDATE et_staff SET staffnotes=? WHERE staffid=?");
		try {
			ps.setString(1, text);
			ps.setInt(2, this.staffid);
			ps.executeUpdate();
			System.out.println("Saving staff sticky notes!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastLoginTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss, zz");
		return sdf.format(this.lastlogin);
	}
	
	public String getFullName() {
		return this.firstname + " " + this.lastname;
	}
}
