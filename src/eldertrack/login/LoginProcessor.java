package eldertrack.login;

public class LoginProcessor {
	public static boolean loginCheck(String username, char[] passarray) {
		LoginUser lu = null;
		try {
			lu = new LoginUser(username, passarray);
		} catch (WrongPasswordException e) {
			return false;
		} catch (NoSuchUserException e) {
			return false;
		}
		return true;
	}
}
