package eldertrack.diet;

import eldertrack.db.SQLObject;

public class BlobViewer {
	private static final int ELDERID = 1;
	public static void main(String[] args) {
		SQLObject so = new SQLObject();
		Meals m =  SerializerSQL.retrieveMeals(ELDERID, so);
		for (int i = 0; i < m.getMealName().size(); i++) {
			System.out.println("Name: " + m.getMealName().get(i));
			System.out.println("Calories: " + m.getNutrition().get(i).getCalories());
			System.out.println("Carbs: " + m.getNutrition().get(i).getCarbs());
			System.out.println();
		}
	}

}
