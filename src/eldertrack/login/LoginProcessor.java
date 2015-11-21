package eldertrack.login;

public class LoginProcessor {
	public static boolean loginCheck(String username, char[] passarray) {
		LoginUser user = null;
		try {
			user = new LoginUser(username, passarray);
		} catch (NoSuchUserException e) {
			System.out.println("No such user found!");
			return false;
		} catch (WrongPasswordException e) {
			System.out.println("Wrong password entered!");
			return false;
		}
		System.out.println("No errors logging in, successful!");
		return true;
	}
}
