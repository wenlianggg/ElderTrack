package eldertrack.misc;

public class ValidateEmail {
		   public static boolean validateEmail(String email){
		      String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      Boolean isAnEmail = email.matches(EMAIL_REGEX);
		      return isAnEmail;
		   }
	}
