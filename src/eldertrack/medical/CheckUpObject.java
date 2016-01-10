package eldertrack.medical;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import eldertrack.db.SQLObject;

public class CheckUpObject  implements Serializable {
	
private double elderTemp;
private int elderBlood;
private int elderHeart;
private int elderSugar;
private boolean elderEye =false;
private boolean elderEar =false;
private String elderDate;
static final SQLObject so = new SQLObject();

	public CheckUpObject(){
	}
	public CheckUpObject(double elderTemp, int elderBlood, int elderHeart,int elderSugar, boolean elderEye, boolean elderEar) {
		this.elderTemp = elderTemp;
		this.elderBlood = elderBlood;
		this.elderHeart = elderHeart;
		this.elderSugar=elderSugar;
		this.elderEye = elderEye;
		this.elderEar = elderEar;
	}

	public int getElderSugar() {
		return elderSugar;
	}
	public void setElderSugar(int elderSugar) {
		this.elderSugar = elderSugar;
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
	
	public static  void  StoreCheckUp(String name, String elderDate, int id,CheckUpObject checkup) throws SQLException{
		PreparedStatement statement = so.getPreparedStatementWithKey("insert into et_elderly_checkup(id,name,date)"+"values(?,?,?)");
		statement.setInt(1, id);
		statement.setString(2,name);
		statement.setString(3, elderDate);
		statement.executeUpdate();
		
		PreparedStatement statementBlob = so.getPreparedStatementWithKey("UPDATE et_elderly_checkup SET checkup = ? WHERE id = ?");
		
		statementBlob.setObject(1, checkup);
		statementBlob.setInt(2, id);
		statementBlob.executeUpdate();
	}
	
	
	public static void RetrieveCheckUp(int id) throws SQLException, IOException, ClassNotFoundException{
		ResultSet rs = so.getResultSet("SELECT name,date FROM et_elderly_checkup " );
		
		while(rs.next()){
			String name=rs.getString("name");
			String date=rs.getString("date");
			System.out.println(name);
			System.out.println(date);
		}
		
		PreparedStatement statement = so.getPreparedStatementWithKey("SELECT checkup FROM et_elderly_checkup WHERE id = ?");
		statement.setInt(1, id);
		ResultSet rs1 = statement.executeQuery();
		rs1.next();
		ByteArrayInputStream in = new ByteArrayInputStream(rs1.getBytes(1));
		ObjectInputStream is = new ObjectInputStream(in);
		Object checkingBlob =(Object) is.readObject();
		CheckUpObject checking=(CheckUpObject) checkingBlob;
		checking.view();
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String reportDate=dateFormat.format(date);
		int id=1;
		//CheckUpObject checking =new CheckUpObject(41,43,41,false,false);
		//StoreCheckUp("Ang Siew Fong",reportDate,id, checking);
		
		RetrieveCheckUp(id);
	}
}
