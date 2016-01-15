package eldertrack.misc;

import java.util.Scanner;

public class NRICUtils {
	public static boolean validate(String str) {
		if (str.length() != 9) 
			return false;
		str = str.toUpperCase();
		char[] icArray = str.toCharArray();
		int[] icnArray = new int[7];
		icnArray[0] = Character.getNumericValue(icArray[1]) * 2;
		icnArray[1] = Character.getNumericValue(icArray[2]) * 7;
		icnArray[2] = Character.getNumericValue(icArray[3]) * 6;
		icnArray[3] = Character.getNumericValue(icArray[4]) * 5;
		icnArray[4] = Character.getNumericValue(icArray[5]) * 4;
		icnArray[5] = Character.getNumericValue(icArray[6]) * 3;
		icnArray[6] = Character.getNumericValue(icArray[7]) * 2;
		int weight = 0;
		for(int i = 0; i < 7; i++) {
			weight += icnArray[i];
		}
		int offset = (icArray[0] == 'T'|| icArray[0] == 'G') ? 4:0;
		int temp = (offset + weight) % 11;
		char[] st = {'J','Z','I','H','G','F','E','D','C','B','A'};
		char[] fg = {'X','W','U','T','R','Q','P','N','M','L','K'};

		char theAlpha;
		if (icArray[0] == 'S' || icArray[0] == 'T') {
			theAlpha = st[temp]; 
		} else if (icArray[0] == 'F' || icArray[0] == 'G') {
			theAlpha = fg[temp];
		} else {
			theAlpha = '0';
		}
		return (icArray[8] == theAlpha);
	}
	
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		String str;
		do {
			System.out.print("Enter NRIC: ");
			str = sc.next();
			System.out.println(str.toUpperCase() + " is " + ((validate(str)) ? "" : "not ") + "valid");
		} while (!str.equals("end"));
		sc.close();
	}
}
