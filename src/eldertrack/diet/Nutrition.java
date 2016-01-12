package eldertrack.diet;

public class Nutrition implements java.io.Serializable {
	private static final long serialVersionUID = 3000L;
	private int calRda;
	private int calories;
	private int fat;
	private int carbs;
	private int iron;
	private int sodium;
	private int potassium;
	private int calcium;
	private int phosporous;
	private int vita;
	private int vitc;
	private int vitd;
	private int vite;
	private int vitk;
	Nutrition() {
		// TODO: Remove this comment from this constructor
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

	public int getSodium() {
		return sodium;
	}

	public int getPotassium() {
		return potassium;
	}

	public int getCalcium() {
		return calcium;
	}

	public int getPhosporous() {
		return phosporous;
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

	public int getVitk() {
		return vitk;
	}
	
	public Nutrition add(Nutrition n) {
		this.calRda+=n.calRda;
		this.calories+=n.calories;
		this.fat+=n.fat;
		this.carbs+=n.carbs;
		this.iron+=n.iron;
		this.sodium+=n.sodium;
		this.potassium+=n.potassium;
		this.calcium+=n.calcium;
		this.phosporous+=n.phosporous;
		this.vita+=n.vita;
		this.vitc+=n.vitc;
		this.vitd+=n.vitd;
		this.vite+=n.vite;
		this.vitk+=n.vitk;
		return this;
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
