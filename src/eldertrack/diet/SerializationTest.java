package eldertrack.diet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationTest {

		public static void main(String [] args) {
			Meals e = new Meals();
			try {
				FileOutputStream fileOut = new FileOutputStream("C:/meals.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(e);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in C:/meals.ser");
			} catch(IOException i) {
				i.printStackTrace();
			}
		}

}
