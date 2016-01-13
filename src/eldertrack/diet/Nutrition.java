package eldertrack.diet;

public class Nutrition implements java.io.Serializable {
	private static final long serialVersionUID = 3001L;
	private int calRda;
	private int calories;
	private int protein;
	private int fat;
	private int carbs;
	private int iron;
	private int vita;
	private int vitc;
	private int vitd;
	private int vite;
	Nutrition() {
		// TODO: Remove this comment from this constructor
	}
	
	public Nutrition(int calories, int protein, int fat, int carbs, int iron, int vita, int vitc, int vitd, int vite) {
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.carbs = carbs;
		this.iron = iron;
		this.vita = vita;
		this.vitc = vitc;
		this.vitd = vitd;
		this.vite = vite;
	}


	public int getCalRda() {
		return calRda;
	}

	public int getCalories() {
		return calories;
	}

	public int getFat() {
		return fat;
	}

	public int getCarbs() {
		return carbs;
	}

	public int getIron() {
		return iron;
	}

	public int getVita() {
		return vita;
	}

	public int getVitc() {
		return vitc;
	}

	public int getVitd() {
		return vitd;
	}

	public int getVite() {
		return vite;
	}
	
	public int getProtein() {
		return protein;
	}

	public Nutrition add(Nutrition n) {
		this.calRda+=n.calRda;
		this.calories+=n.calories;
		this.protein+=n.protein;
		this.fat+=n.fat;
		this.carbs+=n.carbs;
		this.iron+=n.iron;
		this.vita+=n.vita;
		this.vitc+=n.vitc;
		this.vitd+=n.vitd;
		this.vite+=n.vite;
		return this;
	}

	void print() {
		System.out.println();
		System.out.println("Calories: \t" + calories + " kcal");
		System.out.println("Fat: \t\t" + fat + " g");
		System.out.println("Protein: \t\t" + protein + " g");
		System.out.println("Carbs: \t\t" + carbs + " g");
		System.out.println("Iron: \t\t" + iron + " mg");
		System.out.println("Vitamin A: \t" + vita + " mg");
		System.out.println("Vitamin C: \t" + vitc + " mg");
		System.out.println("Vitamin D\t" + vitd + " mg");
		System.out.println("Vitamin E \t" + vite + " mg");
	}
}
