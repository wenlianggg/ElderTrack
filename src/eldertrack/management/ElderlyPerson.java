package eldertrack.management;

public class ElderlyPerson extends Person{
	private String room;
	private String address;
	private String bed;
	private String contact;
	private String email;
	private String fullName;
	
	// Constructor
	public ElderlyPerson(String id, String birthString, String nric, String gender, String fullName, String room, String bed, String address, String contact, String email){
		super(id, birthString, nric, gender);
		this.fullName = fullName;
		this.room = room;
		this.bed = bed;
		this.address = address;
		this.contact = contact;
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	// Getters and setters
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
