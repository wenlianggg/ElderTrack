package eldertrack.misc;

import java.text.DecimalFormat;

public class PhoneNumberGen {
	public static void main(String args[]) {
		System.out.println("Generating random numbers");
		int times = 100;
		DecimalFormat df = new DecimalFormat("0000000");
		for (int i = 0; i <times; i++) {
			double rand = Math.random();
			if (rand > 0.6) {
				System.out.println("8" + df.format(Math.random()*10000000));
			} else if (rand > 0.3) {
				System.out.println("9" + df.format(Math.random()*10000000));
			} else {
				System.out.println("6" + df.format(Math.random()*10000000));
			}
		}
	}
}
