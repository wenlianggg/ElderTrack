package eldertrack.diet;

public class Nutrition implements java.io.Serializable {
	private static final long serialVersionUID = 3000L;
	int calories;
	int fat;
	int carbs;
	int iron;
	int sodium;
	int potassium;
	int calcium;
	int phosporous;
	int vita;
	int vitc;
	int vitd;
	int vite;
	int vitk;
	Nutrition() {
		// TODO: Remove this comment from this constructor
	}
	
	void print() {
		System.out.println();
		System.out.println("Calories: \t" + calories + " kcal");
		System.out.println("Fat: \t\t" + fat + " g");
		System.out.println("Carbs: \t\t" + carbs + " g");
		System.out.println("Iron: \t\t" + iron + " mg");
		System.out.println("Sodium: \t" + sodium + " mg");
		System.out.println("Potassium: \t" + potassium + " mg");
		System.out.println("Calcium: \t" + calcium + " mg");
		System.out.println("Phosporous: \t" + phosporous + " mg");
	}
}
