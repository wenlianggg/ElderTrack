package eldertrack.diet;

import java.util.ArrayList;
import java.util.Date;

public class Meals implements java.io.Serializable {
	private static final long serialVersionUID = 1000L;
	ArrayList<Date> datetime = new ArrayList<Date>();
	ArrayList<String> mealname = new ArrayList<String>();
	ArrayList<Nutrition> nutrition = new ArrayList<Nutrition>();
	ArrayList<MealProperties> mealprop = new ArrayList<MealProperties>();
	public Meals() {	
	}
	
	void addTestMeal() {
		datetime.add(new Date());
		mealname.add("Test Meal");
		nutrition.add(new Nutrition());
		mealprop.add(new MealProperties());
	}
}
