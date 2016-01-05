package eldertrack.diet;

import java.io.IOException;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class SerializationTest {
	static final String SQL_WRITE = "UPDATE et_elderly SET diet = ? WHERE id = ?";
	static final String SQL_READ = "SELECT diet FROM et_elderly WHERE id = ?";
	private static SQLObject so = new SQLObject();

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		serialize();
		deserialize();
	}
	
	  public static void serialize() throws SQLException {
		  Meals m = new Meals();
		  m.addTestMeal();
		  SerializerSQL.storeMeals(1, m, so);
	  }
	
	public static void deserialize() throws SQLException, ClassNotFoundException, IOException {
		Meals m = SerializerSQL.retrieveMeals(1, so);
		System.out.println("Deserialized Meal...");
		System.out.println("Meal Name: " + m.getMealName(0));
		System.out.print("Meal Property: ");
		m.getMealProperties(0).print();
		System.out.print("Nutrition: ");
		m.getNutrition(0).print();
	}

}
