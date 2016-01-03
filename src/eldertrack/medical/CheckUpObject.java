package eldertrack.medical;

public class CheckUpObject {
	
private double elderTemp;
private int elderBlood;
private int elderHeart;
private boolean elderEye =false;
private boolean elderEar =false;
private String elderDate;

	public CheckUpObject(){
	}
	public CheckUpObject(double elderTemp, int elderBlood, int elderHeart, boolean elderEye, boolean elderEar, String elderDate) {
		this.elderTemp = elderTemp;
		this.elderBlood = elderBlood;
		this.elderHeart = elderHeart;
		this.elderEye = elderEye;
		this.elderEar = elderEar;
		this.elderDate = elderDate;
	}

	public double getElderTemp() {
		return elderTemp;
	}
	public void setElderTemp(double elderTemp) {
		this.elderTemp = elderTemp;
	}
	public int getElderBlood() {
		return elderBlood;
	}
	public void setElderBlood(int elderBlood) {
		this.elderBlood = elderBlood;
	}
	public int getElderHeart() {
		return elderHeart;
	}
	public void setElderHeart(int elderHeart) {
		this.elderHeart = elderHeart;
	}
	public boolean isElderEye() {
		return elderEye;
	}
	public void setElderEye(boolean elderEye) {
		this.elderEye = elderEye;
	}
	public boolean isElderEar() {
		return elderEar;
	}
	public void setElderEar(boolean elderEar) {
		this.elderEar = elderEar;
	}
	public String getElderDate() {
		return elderDate;
	}
	public void setElderDate(String elderDate) {
		this.elderDate = elderDate;
	}
	
	public void view(){
		System.out.println(getElderTemp());
		System.out.println(getElderBlood());
		System.out.println(getElderHeart());
		System.out.println(isElderEye());
		System.out.println(isElderEar());
		System.out.println(getElderDate());
	}
}
