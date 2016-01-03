package eldertrack.diet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class SerializationTest {
	static final String SQL_WRITE = "UPDATE et_elderly SET diet = ? WHERE id = ?";
	static final String SQL_READ = "SELECT diet FROM et_elderly WHERE id = ?";
	static final SQLObject so = new SQLObject();

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		//serialize();
		deserialize();
	}
	
	  public static void serialize() throws SQLException {
		  Meals m = new Meals();
		  m.addTestMeal();
		  PreparedStatement ps = so.getPreparedStatementWithKey(SQL_WRITE);
		  ps.setObject(1, m);
		  ps.setInt(2, 1);
		  ps.executeUpdate();
	  }
	
	public static void deserialize() throws SQLException, ClassNotFoundException, IOException {
	    PreparedStatement ps = so.getPreparedStatementWithKey(SQL_READ);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();
		rs.next();
	    ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes(1));
	    ObjectInputStream is = new ObjectInputStream(in);
		Meals m = (Meals) is.readObject();
		System.out.println("Deserialized Meal...");
		System.out.println("Date and Time: " + m.datetime.get(0).getTime());
		System.out.println("Meal Name: " + m.mealname.get(0));
		System.out.print("Meal Property: ");
		m.mealprop.get(0).print();
		System.out.print("Nutrition: ");
		m.nutrition.get(0).print();
	}

}
