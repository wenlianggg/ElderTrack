package eldertrack.misc;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class GenerateEncodedSalt {
	public static void main(String[] args) {
		
		// Generating of salt value
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[32];
		sr.nextBytes(salt);
		
		// Converting salt value to encoded string then printing it
		for (int i = 0; i < salt.length; i++)
			System.out.print(salt[i]);
		System.out.println();
		String encoded = Base64.encodeBase64String(salt);
		
		// Decoding it back to byte form
		byte[] decoded = new byte[32];
		decoded = Base64.decodeBase64(encoded);
		for (int i = 0; i < decoded.length; i++)
			System.out.print(decoded[i]);
		System.out.println();
		
		// Printing out resultant values
		String rencoded = Base64.encodeBase64String(decoded);
		System.out.println(rencoded);
		System.out.println(encoded);
		
		// Check if the values match
		System.out.println("Match: " + encoded.equals(rencoded));
		
	}
}
