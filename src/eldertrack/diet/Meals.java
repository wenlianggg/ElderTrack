package eldertrack.diet;

import java.util.ArrayList;
import java.util.Date;

public class Meals implements java.io.Serializable {
	private static final long serialVersionUID = 1001L;
	private ArrayList<String> mealname;
	private ArrayList<Nutrition> nutrition;
	private ArrayList<MealProperties> mealprop;
	public Meals() {
		mealname = new ArrayList<String>();
		nutrition = new ArrayList<Nutrition>();
		mealprop = new ArrayList<MealProperties>();
	}
	
	public Meals(Date datetime, String mealname, Nutrition nutrition, MealProperties mealprop) {
		this();
		addMeal(datetime, mealname, nutrition, mealprop);
	}
	
	public Meals addMeal(Date datetime, String mealname, Nutrition nutrition, MealProperties mealprop) {
		this.mealname.add(mealname);
		this.nutrition.add(nutrition);
		this.mealprop.add(mealprop);
		return this;
	}
	
	Meals addTestMeal() {
		mealname.add("Test Meal");
		nutrition.add(new Nutrition());
		mealprop.add(new MealProperties());
		return this;
	}
	
	
	// Getters and Mutator
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
	
	public ArrayList<MealProperties> setMealProperties() {
		return this.mealprop;
	}
	
	public ArrayList<String> setMealName() {
		return this.mealname;
	}
	
	public ArrayList<Nutrition> setNutrition() {
		return this.nutrition;
	}
	
	public ArrayList<MealProperties> getMealProperties() {
		return this.mealprop;
	}
}
