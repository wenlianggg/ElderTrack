package eldertrack.medical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eldertrack.db.SQLObject;

public class DosageData{
private String elderName;
private int elderAge;
private  String elderGender;
private DosageObject elderDosage;
	
public DosageData(){
	
}

public DosageData(String elderName, int elderAge, String elderGender, DosageObject elderDosage) {
	this.elderName = elderName;
	this.elderAge = elderAge;
	this.elderGender = elderGender;
	this.elderDosage = elderDosage;
}

public String getElderName() {
	return elderName;
}

public void setElderName(String elderName) {
	this.elderName = elderName;
}

public int getElderAge() {
	return elderAge;
}

public void setElderAge(int elderAge) {
	this.elderAge = elderAge;
}

public String getElderGender() {
	return elderGender;
}

public void setElderGender(String elderGender) {
	this.elderGender = elderGender;
}
public void print(){
	System.out.println("Name: "+getElderName());
	System.out.println("Age: "+getElderAge());
	System.out.println("Gender: "+getElderGender());
}

public static void main(String[] args) throws SQLException {
	SQLObject so = new SQLObject();
	ResultSet rs=so.getResultSet("SELECT * FROM et_elderly");
	ArrayList<DosageData> DosageList=new ArrayList<DosageData>();
	
	try {
		while(rs.next()){
		DosageData data=new DosageData();
		data.setElderName(rs.getString("name"));
		data.setElderAge(10);
		data.setElderGender(rs.getString("gender"));
		DosageList.add(data);
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	for(int i=0;i<DosageList.size();i++){
		DosageList.get(i).print();
	}
}




}



