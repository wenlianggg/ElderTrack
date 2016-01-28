package eldertrack.management;

public class StaffPerson extends Person {
	private int accessLevel;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private char[] passChar;

	// Constructor
	public StaffPerson(String id, String birthString, String nric, String firstName, String lastName, int accessLevel, String username, char[] passChar, String password){
		super(id, birthString, nric);
		this.firstName = firstName;
		this.lastName = lastName;
		this.accessLevel = accessLevel;
		this.username = username;
		this.passChar = passChar;
		this.password = password;
	}

	public char[] getPassChar() {
		return passChar;
	}

	public void setPassChar(char[] passChar) {
		this.passChar = passChar;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
