package eldertrack.management;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import eldertrack.db.SQLObject;

public class ManagementObject {
	private static SQLObject so = new SQLObject();
	public final static String ADD_ELDERLY = "INSERT INTO et_elderly (name, dob, nric, gender, room, address, bed, contact, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public final static String REMOVE_ELDERLY = "DELETE FROM et_elderly WHERE id=?";
	public final static String REMOVE_STAFF = "DELETE FROM et_staff WHERE staffid=?";
	public final static String GET_ELDERLY_SAME_ROOM = "SELECT bed FROM et_elderly WHERE room=? AND id NOT IN (?)";
	public final static String GET_ELDERLY_NRICS = "SELECT nric FROM et_elderly";
	public final static String UPDATE_STAFF_PASSWORD = "UPDATE et_staff SET firstname=?, lastname=?, dob=?, nric=?, password=?, accesslevel=? WHERE staffid=?";
	public final static String UPDATE_STAFF_NO_PASSWORD = "UPDATE et_staff SET firstname=?, lastname=?, dob=?, nric=?, accesslevel=? WHERE staffid=?";
	public final static String ADD_STAFF = "INSERT INTO et_staff (username, password, firstname, lastname, nric, dob, accesslevel, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public final static String GET_ALL_STAFF = "SELECT * FROM et_staff";
	public final static String UPDATE_ELDERLY = "UPDATE et_elderly SET name=?, dob=?, nric=?, gender=?, room=?, bed=?, contact=?, address=?, email=? WHERE id=?";
	
	// Add a staff member to the database
	public static void executeAddStaff(String staffBirthString, String staffUsername, String securePassword, String staffFirstName, String staffLastName, String staffNric, int accessLevel, String salt) throws SQLException{
		PreparedStatement ps1 = so.getPreparedStatement(ADD_STAFF);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate staffDob = LocalDate.parse(staffBirthString, formatter);
		ps1.setString(1, staffUsername);
		ps1.setString(2, securePassword);
		ps1.setString(3, staffFirstName);
		ps1.setString(4, staffLastName);
		ps1.setString(5, staffNric);
		ps1.setDate(6, java.sql.Date.valueOf(staffDob));
		ps1.setInt(7, accessLevel);
		ps1.setString(8, salt);
		ps1.executeUpdate();
	}
	
	// Add an elderly to the database
	public static void executeAddElderly(String birthString, String name, String nric, String gender, String room, String address, int bed, String contact, String email) throws SQLException{
		PreparedStatement ps = so.getPreparedStatement(ADD_ELDERLY);
		LocalDate dob = ManagementObject.toLocalDate(birthString);
		ps.setString(1, name);
		ps.setDate(2, java.sql.Date.valueOf(dob));
		ps.setString(3,nric);
		ps.setString(4, gender);
		ps.setString(5, room);
		ps.setString(6, address);
		ps.setInt(7, bed);
		ps.setString(8, contact);
		ps.setString(9, email);
		ps.executeUpdate();
	}
	
	// Retrieve all elderly NRICS
	public static ResultSet retrieveElderlyNrics() throws SQLException{
		PreparedStatement ps = so.getPreparedStatement(GET_ELDERLY_NRICS);
		ResultSet checkNric = ps.executeQuery();
		return checkNric;
	}
	
	// Retrieve elderly in the same room
	public static ResultSet retrieveElderlySameRoom(String room, String id) throws SQLException{
		PreparedStatement ps1 = so.getPreparedStatement(GET_ELDERLY_SAME_ROOM);
		ps1.setString(1, room);
		ps1.setString(2, id);
		ResultSet checkBed = ps1.executeQuery();
		return checkBed;
	}
		
	// Get all staff info
	public static ResultSet getAllStaff() throws SQLException{
		PreparedStatement ps = so.getPreparedStatement(GET_ALL_STAFF);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// Execute a staff update with password
	public static void executeStaffUpdateWithPassword(String birthString, String firstName, String lastName, String nric, String securePassword, int accessLevel, String staffId) throws SQLException{
		PreparedStatement ps1 = so.getPreparedStatement(UPDATE_STAFF_PASSWORD);	
		LocalDate dob = ManagementObject.toLocalDate(birthString);
		ps1.setString(1, firstName);
		ps1.setString(2, lastName);
		ps1.setDate(3, java.sql.Date.valueOf(dob));
		ps1.setString(4, nric);
		ps1.setString(5, securePassword);
		ps1.setInt(6, accessLevel);
		ps1.setString(7, staffId);
		ps1.executeUpdate();
	}
	
	// Execute a staff update without a password
	public static void executeStaffUpdateNoPassword(String birthString, String firstName, String lastName, String nric, String staffId, int accessLevel) throws SQLException{
		PreparedStatement ps1 = so.getPreparedStatement(UPDATE_STAFF_NO_PASSWORD);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate dob = LocalDate.parse(birthString, formatter);
		ps1.setString(1, firstName);
		ps1.setString(2, lastName);
		ps1.setDate(3, java.sql.Date.valueOf(dob));
		ps1.setString(4, nric);
		ps1.setInt(5, accessLevel);
		ps1.setString(6, staffId);
		ps1.executeUpdate();
	}
	
	// Execute an elderly update
	public static void executeElderlyUpdate(String name, String nric, String gender, String room, String bed, String contact, String address, String id, String birthString, String email) throws SQLException{
		PreparedStatement ps = so.getPreparedStatement(UPDATE_ELDERLY);
		LocalDate dob = ManagementObject.toLocalDate(birthString);
		ps.setString(1, name);
		ps.setDate(2, java.sql.Date.valueOf(dob));
		ps.setString(3, nric);
		ps.setString(4, gender);
		ps.setString(5, room);
		ps.setInt(6, Integer.parseInt(bed));
		ps.setString(7, contact);
		ps.setString(8, address);
		ps.setString(9, email);
		ps.setInt(10, Integer.parseInt(id));
		ps.executeUpdate();
	}
	
	// Retrieve staff NRIC from database
	public static ResultSet retrieveStaffNrics(String staffId) throws SQLException{
		PreparedStatement ps = so.getPreparedStatement("SELECT nric FROM et_staff WHERE staffid NOT IN(?)");
		ps.setString(1, staffId);
		ResultSet check = ps.executeQuery();
		return check;
	}
	
	// Remove any person
	public static void removePerson(int id, String query){
		try {
			PreparedStatement ps1 = so.getPreparedStatement(query);
			ps1.setInt(1, id);
			ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Check for invalid entries
		public static boolean invalidEntries(boolean dupeBed, boolean dupeNric, boolean validNric, boolean validPhoneNo, String bed, boolean validEmail){
			if(dupeBed == true){
				JOptionPane.showMessageDialog(null, "There are duplicate bed numbers! Please check your entries!");
				return true;
			}else if(Integer.parseInt(bed) > 10){
				JOptionPane.showMessageDialog(null, "The bed number cannot exceed 10!");
				return true;
			}else if(dupeNric == true){
				JOptionPane.showMessageDialog(null, "There are duplicate NRICS! Please check your entries");
				return true;
			}else if(validNric == false){
				JOptionPane.showMessageDialog(null, "The NRIC entered is not valid! Please check your entry!");
				return true;
			}else if(validPhoneNo == false){
				JOptionPane.showMessageDialog(null, "The phone number given is invalid! Please check your entry!");
				return true;
			}else if(validEmail == false){
				JOptionPane.showMessageDialog(null, "That is an invalid email format!");
				return true;
			}else{
				return false;
			}
		}
		
	// Conversion of string into a LocalDate data type
	public static LocalDate toLocalDate(String birthString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate dob = LocalDate.parse(birthString, formatter);
		return dob;
	}
	
	// Calculation of age from birthday
	public static String calculateAge(ResultSet rs){
		Calendar birthdate = Calendar.getInstance();
		try {
			birthdate.setTime(rs.getDate("dob"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar today = Calendar.getInstance();
		int years = today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);{  
		if (today.get(Calendar.MONTH) < birthdate.get(Calendar.MONTH)) {
			  years--;  
			} else if (today.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)
			    && today.get(Calendar.DAY_OF_MONTH) < birthdate.get(Calendar.DAY_OF_MONTH)) {
			  years--;  
			}
		
		// Return age value
		return Integer.toString(years);
			
		}
	}
	
	// Check for empty fields for elderly
	public static boolean elderlyEmptyFields(String name, String birthString, String nric, String gender, String room , String address , String bedString, String contact, String email){
		if(name.equals("") || birthString.equals("") || nric.equals("") || gender.equals("") || room.equals("") || address.equals("") || bedString.equals("") || contact.equals("") || email.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	// Check for empty fields for Staff
	public static boolean staffEmptyFields(String firstName, String lastName, String nric, String birthString){
		if(firstName.equals("") || lastName.equals("") || nric.equals("") || birthString.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	// Password Encryption
	public static byte[] toBytes(char[] chars) {
	    CharBuffer charBuffer = CharBuffer.wrap(chars);
	    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
	    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	    return bytes;
	}
	
	public static String saltGenerator(){
		// Generating of salt value
				SecureRandom sr = new SecureRandom();
				byte[] salt = new byte[32];
				sr.nextBytes(salt);
				
		// Converting salt value to encoded string then printing it
				String encoded = Base64.encodeBase64String(salt);
				
		// Return the salt value
				return encoded;
	}
	
	
	public static String passwordHasher(byte[] basePw, String pwSalt){
		// Obtain base password
		byte[] base = basePw;
		// Obtain password salt
		byte[] salt = Base64.decodeBase64(pwSalt);
		// Apply salt to base password
		String result = DigestUtils.sha512Hex(ArrayUtils.addAll(base,salt));
		
		return result;
	}
	
	
	// Retrieve salt of selected staff
	public static String getUserSalt(String staffId) throws SQLException{
		PreparedStatement getSalt = so.getPreparedStatement("SELECT * FROM et_staff WHERE staffid=?");
		getSalt.setString(1, staffId);
		ResultSet userSalt = getSalt.executeQuery();
		userSalt.next();
		String salt = userSalt.getString("salt");
		return salt;
	}
	
	
	// Check duplicate NRICS
	public static boolean checkDuplicateNrics(String nric, ResultSet rs){
		boolean result = false;
		ResultSet check = rs;
		
		try{
			while(check.next()){
				if(check.getString("nric") == nric){
					result = true;
					break;
				}else
					result = false;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		
		return result;
	}
	
	// Check duplicate beds
	public static boolean checkDuplicateBeds(int bed, ResultSet rs){
		boolean result = false;
		ResultSet check = rs;
		try {
			while(check.next()){
				if(check.getInt("bed") == bed){
					result = true;
					break;
				}else
					result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Check for valid phone numbers
	public static boolean checkValidPhoneNo(String no){
		if(Character.getNumericValue(no.charAt(0)) != 6 && Character.getNumericValue(no.charAt(0)) != 8 && Character.getNumericValue(no.charAt(0)) != 9){
			JOptionPane.showMessageDialog(null, "The phone number is invalid!");
			return false;
		}else if(no.length() != 8){
			JOptionPane.showMessageDialog(null, "The phone number contains more than 8 numbers!");
			return false;
		}else
			return true;
	}
	
}
