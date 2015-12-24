package eldertrack.medical;

public class DosageObject {
private String MedDescrip;
private String MedPrescrip;
private String MedType;
private String MedDosage;
	
	public DosageObject(){
		
	}
	public DosageObject(String medDescrip, String medPrescrip, String medType, String medDosage) {
		this.MedDescrip = medDescrip;
		this.MedPrescrip = medPrescrip;
		this.MedType = medType;
		this.MedDosage = medDosage;
	}
	public String getMedDescrip() {
		return MedDescrip;
	}
	public void setMedDescrip(String medDescrip) {
		this.MedDescrip = medDescrip;
	}
	public String getMedPrescrip() {
		return MedPrescrip;
	}
	public void setMedPrescrip(String medPrescrip) {
		this.MedPrescrip = medPrescrip;
	}
	public String getMedType() {
		return MedType;
	}
	public void setMedType(String medType) {
		this.MedType = medType;
	}
	public String getMedDosage() {
		return MedDosage;
	}
	public void setMedDosage(String medDosage) {
		this.MedDosage = medDosage;
	}
	
}
