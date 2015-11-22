package eldertrack.login;

public class NoSuchUserException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoSuchUserException() {
		this("No such user was found!");
	}
	
	public NoSuchUserException(String message) {
		super(message);
	}
}
