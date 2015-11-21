package eldertrack.login;

public class LoginUser{
	String username;
	char[] password;
	String firstname;
	String lastname;
	boolean accesslevel;
	
	LoginUser(String username, char[] password) throws WrongPasswordException, NoSuchUserException {
		// If username does not exist, throw NoSuchUserException
		if (username.contains("wrong")) {
			throw new NoSuchUserException();
		// If password is wrong, throw WrongPasswordException
		} else if (password.equals("wrong")) {
			throw new WrongPasswordException();
		}
	}
}
