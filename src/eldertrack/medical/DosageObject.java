package eldertrack.medical;

import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

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
public static void main(String[] args) throws SQLException {
	SQLObject so = new SQLObject();
	ResultSet rs=so.getResultSet("SELECT * FROM et_elderly");
	try {
		while(rs.next()){
		
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
}
}
