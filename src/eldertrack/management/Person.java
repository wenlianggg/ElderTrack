package eldertrack.management;

public class Person {
	private String id;
	private String birthString;
	private String nric;
	
	// Constructor
	Person(String id, String birthString, String nric){
		this.id = id;
		this.birthString = birthString;
		this.nric = nric;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBirthString() {
		return birthString;
	}

	public void setBirthString(String birthString) {
		this.birthString = birthString;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}
	
}
