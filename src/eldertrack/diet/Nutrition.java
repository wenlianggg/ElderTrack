package eldertrack.diet;

public class Nutrition implements java.io.Serializable {
	private static final long serialVersionUID = 3002;
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
	boolean halal;
	
	public Nutrition() {
		this.calories = 0;
		this.protein = 0;
		this.fat = 0;
		this.carbs = 0;
		this.iron = 0;
		this.vita = 0;
		this.vitc = 0;
		this.vitd = 0;
		this.vite = 0;
		halal = false;
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
	
	public boolean isHalal() {
		return halal;
	}

	public Nutrition setCalRda(int calRda) {
		this.calRda = calRda;
		return this;
	}

	public Nutrition setCalories(int calories) {
		this.calories = calories;
		return this;
	}

	public Nutrition setProtein(int protein) {
		this.protein = protein;
		return this;
	}

	public Nutrition setFat(int fat) {
		this.fat = fat;
		return this;
	}

	public Nutrition setCarbs(int carbs) {
		this.carbs = carbs;
		return this;
	}

	public Nutrition setIron(int iron) {
		this.iron = iron;
		return this;
	}

	public Nutrition setVita(int vita) {
		this.vita = vita;
		return this;
	}

	public Nutrition setVitc(int vitc) {
		this.vitc = vitc;
		return this;
	}

	public Nutrition setVitd(int vitd) {
		this.vitd = vitd;
		return this;
	}

	public Nutrition setVite(int vite) {
		this.vite = vite;
		return this;
	}

	public Nutrition setHalal(boolean halal) {
		this.halal = halal;
		return this;
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
