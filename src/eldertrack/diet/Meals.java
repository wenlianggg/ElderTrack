package eldertrack.diet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import java.util.Calendar;
import java.util.Date;

public class Meals implements java.io.Serializable {
	private static final long serialVersionUID = 1002;
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
	
	public Nutrition getNutritionToday() {
		ArrayList<Nutrition> selected = new ArrayList<Nutrition>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date today = c.getTime();
		// Loop through MealProperties and find where today ends
		for(int i = 0; i < mealprop.size(); i++)
			if(mealprop.get(i).getCreated().after(today)) {
				System.out.println(i);
				selected.add(nutrition.get(i));
			}
		Nutrition totaln = new Nutrition();
		for (int i = 0; i < nutrition.size() - 1; i++)
			totaln.add(selected.get(i));
		return totaln;
	}
	
	public DefaultTableModel getTableModel() {
		
		Vector<Vector<Object>> mealvector = new Vector<Vector<Object>>();
		Vector<String> columns = new Vector<String>();
		Vector<String> createdvector = new Vector<String>();
		Vector<String> idvector = new Vector<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM - HH:mm");
		// Simple ID Counting
		for (int i = 0; i < this.mealprop.size(); i++)
			idvector.add(Integer.toString(i));
		// Store created date into Vector<String>
		for (MealProperties mp : this.mealprop)
			createdvector.add(sdf.format(mp.getCreated()));
		columns.add("ID");
		columns.add("Time");
		columns.add("Meal");
		mealvector.add(new Vector<>(idvector));
		mealvector.add(new Vector<>(createdvector));
		mealvector.add(new Vector<>(this.mealname));
		mealvector = transpose(mealvector);
		DefaultTableModel dtm = new DefaultTableModel(mealvector, columns);
		return dtm;
	}
	
	public String getMealName(int i) {
		return mealname.get(i);
	}
	
	public Nutrition getNutrition(int i) {
		return nutrition.get(i);
	}
	
	public void setNutrition(int i, Nutrition n) {
		nutrition.set(i, n);
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

	Meals addTestMeal() {
		mealname.add("Test Meal");
		nutrition.add(new Nutrition());
		mealprop.add(new MealProperties());
		return this;
	}
	
	boolean isValid() {
		if ( (this.mealname.size() == this.mealprop.size()) && (this.nutrition.size() == this.mealprop.size()) ) {
			return true;
		} else {
			return fix();
		}
	}
	
	boolean fix() {
		if (this.mealname == null || this.mealprop == null || this.nutrition == null) {
			throw new NullPointerException();
		} else {
			int smallestsize = this.mealname.size();
			if (smallestsize < this.mealprop.size())
				smallestsize = this.mealprop.size();
			if (smallestsize < this.nutrition.size())
				smallestsize = this.nutrition.size();
			do {
				if(this.mealprop.size() > smallestsize)
					this.mealprop.remove(this.mealprop.size()-1);
				if(this.mealname.size() > smallestsize)
					this.mealname.remove(this.mealname.size()-1);
				if(this.nutrition.size() > smallestsize)
					this.nutrition.remove(this.nutrition.size()-1);
			} while ( (this.mealname.size() != this.mealprop.size()) || (this.nutrition.size() != this.mealprop.size()) );
			return true;
		}
	}

	// Vector Transposition
	private static <T> Vector<Vector<T>> transpose(Vector<Vector<T>> table) {
		Vector<Vector<T>> ret = new Vector<Vector<T>>();
	    final int N = table.get(0).size();
	    for (int i = 0; i < N; i++) {
	    	Vector<T> col = new Vector<T>();
	        for (Vector<T> row : table) {
	            col.add(row.get(i));
	        }
	        ret.add(col);
	    }
	    return ret;
	}
}
