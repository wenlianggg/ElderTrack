package eldertrack.login;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import eldertrack.db.SQLObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

public class StaffSession{
	private static HashMap<Integer, String> namemap = null;
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
	SQLObject so = new SQLObject();
	
	StaffSession(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		ResultSet rs = null;
		try {
			so = new SQLObject();
			rs = so.getResultSet("SELECT * FROM et_staff WHERE username = ?", username.toLowerCase());
			/**
			 * CHECK IF THE USER EXISTS
			 */
			if (rs == null || rs.next() == false) {
				NoSuchUserException up = new NoSuchUserException();
				throw up;
			}
			this.exists = true;
			
			/**
			 * START VERIFYING IF USER LOGIN IS VALID
			 */
			String hashed = DigestUtils.sha512Hex(ArrayUtils.addAll(toBytes(password), Base64.decodeBase64(rs.getString("salt"))));
			if (rs.getString("password").equals(hashed)) {
					/**
					 * EXECUTES ONLY IF THE PASSWORD IS CORRECT
					 */
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
				/**
				 * EXECUTES IF THE PASSWORD IS WRONG
				 */
				WrongPasswordException away = new WrongPasswordException();
				throw away;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private byte[] toBytes(char[] chars) {
	    CharBuffer charBuffer = CharBuffer.wrap(chars);
	    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
	    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	    return bytes;
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
	
	public String getLastLoginTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss, zz");
		return sdf.format(this.lastlogin);
	}
	
	public String getFullName() {
		return this.firstname + " " + this.lastname;
	}
	
	public static String nameFromID(Integer id) {
		if (id == 0)
			return "Nobody";
		if (namemap == null) {
			namemap = new HashMap<Integer, String>();
			try {
				PreparedStatement ps = new SQLObject().getPreparedStatement("SELECT staffid, firstname, lastname FROM et_staff");
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					namemap.put(rs.getInt("staffid"), rs.getString("firstname") + " " + rs.getString("lastname"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String name = namemap.get(id);
		if (name == null)
			return "Not Found, ID: " + id.toString();
		else
			return name;
	}
	
	public static void nullMap() {
		namemap = null;
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
}
