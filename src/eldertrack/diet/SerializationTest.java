package eldertrack.diet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {
	public static void main(String[] args) {
		serialize();
		deserialize();
	}
	
	public static void serialize()  {
		Meals m = new Meals();
		m.addTestMeal();
		try {
			new File(System.getProperty("user.home") + "/tmp").mkdir();
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/tmp/Meals.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in " + System.getProperty("user.home") + "\\tmp\\Meals.ser");
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public static void deserialize() {
		Meals m = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + "/tmp/Meals.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         m = (Meals) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Meal class not found");
	         c.printStackTrace();
	         return;
	      }
	      System.out.println("Deserialized Meal...");
	      System.out.println("Date and Time: " + m.datetime.get(0).getTime());
	      System.out.println("Meal Name: " + m.mealname.get(0));
	      System.out.print("Meal Property: ");
	      m.mealprop.get(0).print();
	      System.out.print("Nutrition: ");
	      m.nutrition.get(0).print();
	}

}
