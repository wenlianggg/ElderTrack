package eldertrack.diet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Meals implements java.io.Serializable {
	private static final long serialVersionUID = 1001L;
	private ArrayList<String> mealname;
	private ArrayList<Nutrition> nutrition;
	private ArrayList<MealProperties> mealprop;
	
	// Constructor that creates an empty Meals entry
	public Meals() {
		mealname = new ArrayList<String>();
		nutrition = new ArrayList<Nutrition>();
		mealprop = new ArrayList<MealProperties>();
	}
	
	// Constructor that takes in one meal
	public Meals(Date datetime, String mealname, Nutrition nutrition, MealProperties mealprop) {
		this();
		addMeal(datetime, mealname, nutrition, mealprop);
	}
	
	// Method to add a meal
	public Meals addMeal(Date datetime, String mealname, Nutrition nutrition, MealProperties mealprop) {
		if (datetime != null && mealname != null && nutrition != null && mealprop != null && !mealname.equals("")) {
			this.mealname.add(mealname);
			this.nutrition.add(nutrition);
			this.mealprop.add(mealprop);
		} else if (mealname.isEmpty()) {
			throw new IllegalArgumentException("Meal Name cannot be empty!");
		} else {
			throw new IllegalArgumentException("Illegal arguments when trying to add meals");
		}
		return this;
	}
	
	Meals addTestMeal() {
		mealname.add("Test Meal");
		mealname.add("Test Meal 2");
		nutrition.add(new Nutrition());
		nutrition.add(new Nutrition());
		mealprop.add(new MealProperties());
		mealprop.add(new MealProperties());
		return this;
	}
	
	public Nutrition getNutritionToday() {
		ArrayList<Nutrition> selected = new ArrayList<Nutrition>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date today = c.getTime();
		// Iterating through MealProperties
		for(int i = 0; i < mealprop.size(); i++)
			if(mealprop.get(i).getCreated().after(today)) {
				System.out.println(i);
				selected.add(nutrition.get(i));
			}
		Nutrition totaln = new Nutrition();
		for (int i = 0; i < nutrition.size(); i++)
			totaln.add(selected.get(i));
		return totaln;
	}
	
	
	public String getMealName(int i) {
		return mealname.get(i);
	}
	
	public Nutrition getNutrition(int i) {
		return nutrition.get(i);
	}
	
	public MealProperties getMealProperties(int i) {
		return mealprop.get(i);
	}
	
	public ArrayList<String> getMealName() {
		return this.mealname;
	}
	
	public ArrayList<Nutrition> getNutrition() {
		return this.nutrition;
	}
	
	public ArrayList<MealProperties> getMealProperties() {
		return this.mealprop;
	}
}
