package eldertrack.misc;

import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

public class PasswordSaltHasher {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Enter the base password");
		byte[] base = sc.nextLine().getBytes();
		System.out.println("Enter the generated salt");
		byte[] decoded = Base64.decodeBase64(sc.nextLine());
		String result = DigestUtils.sha512Hex(ArrayUtils.addAll(base,decoded));
		System.out.println();
		System.out.println(result);
	}
}
