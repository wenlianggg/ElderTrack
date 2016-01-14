package eldertrack.diet;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class SerializerSQL {
	
	static final String SQL_WRITE_DIET= "UPDATE et_elderly SET diet = ? WHERE id = ?";
	static final String SQL_WRITE_NUTRITION= "UPDATE et_menu SET nutrition = ? WHERE itemid = ?";

	static final String SQL_READ_DIET = "SELECT diet FROM et_elderly WHERE id = ?";
	static final String SQL_READ_NUTRITION = "SELECT nutrition FROM et_menu WHERE itemid = ?";

    private static ObjectInputStream is;
	public static boolean storeMeals(int id, Meals m, SQLObject so) {
			  PreparedStatement ps;
			  try {
				  ps = so.getPreparedStatementWithKey(SQL_WRITE_DIET);
				  ps.setObject(1, m);
				  ps.setInt(2, id);
				  ps.executeUpdate();
				  ps.close();
			  } catch (SQLException e) {
				  e.printStackTrace();
				  return false;
			  }	catch (Exception e) {
				  e.printStackTrace();
				  return false;
			  }
			  return true;
	}

	public static Meals retrieveMeals(int id, SQLObject so) {
	    PreparedStatement ps;
	    Meals m;
	    try {
		    ps = so.getPreparedStatementWithKey(SQL_READ_DIET);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			rs.next();
		    is = new ObjectInputStream(new ByteArrayInputStream(rs.getBytes(1)));
			m = (Meals) is.readObject();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	return null;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
		return m;
	}
	
	public static boolean storeNutrition(int id, Nutrition n, SQLObject so) {
		  PreparedStatement ps;
		  try {
			  ps = so.getPreparedStatementWithKey(SQL_WRITE_NUTRITION);
			  ps.setObject(1, n);
			  ps.setInt(2, id);
			  ps.executeUpdate();
			  ps.close();
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return false;
		  }	catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  return true;
}

	public static Nutrition retrieveNutrition(int id, SQLObject so) {
	  PreparedStatement ps;
	  Nutrition n;
	  try {
		    ps = so.getPreparedStatementWithKey(SQL_READ_NUTRITION);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			rs.next();
		    is = new ObjectInputStream(new ByteArrayInputStream(rs.getBytes(1)));
			n = (Nutrition) is.readObject();
	  } catch (SQLException e) {
	  	e.printStackTrace();
	  	return null;
	  } catch (Exception e) {
	  	e.printStackTrace();
	  	return null;
	  }
		return n;
	}
}
