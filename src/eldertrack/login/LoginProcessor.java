package eldertrack.login;

public class LoginProcessor {
	public static boolean loginCheck(String username, char[] passarray) {
		String password = new String(passarray);
		if (username.equals(password))
			return true;
		else
			return false;
	}
}
