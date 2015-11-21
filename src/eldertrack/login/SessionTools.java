package eldertrack.login;

public class SessionTools{
	public static StaffSession createSession(String username, char[] passarray) {
		StaffSession session = null;
		// Check if username or password is empty
		try {
			session = new StaffSession(username, passarray);
		} catch (NoSuchUserException e) {
			System.out.println("No such user found!");
			return null;
		} catch (WrongPasswordException e) {
			System.out.println("Wrong password entered!");
			return null;
		}
		System.out.println("No errors logging in, successful!");
		return session;
	}
}
