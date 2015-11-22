package eldertrack.login;

public class WrongPasswordException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WrongPasswordException() {
		this("Password was incorrect!");
	}
	
	public WrongPasswordException(String message) {
		super(message);
	}
}
