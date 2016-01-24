package eldertrack.medical;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import eldertrack.db.SQLObject;

public class CheckUpObject  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4101958710431937395L;
	private double elderTemp;
	private int elderBlood;
	private int elderHeart;
	private int elderSugar;
	private boolean elderEye =false;
	private boolean elderEar =false;
	private String elderDate;
	

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

	public static Boolean checkupValid(String roomNum,String timing,SQLObject so){
		ResultSet rs = null;
		int totalElder=0;
		int checked=0;
		try {

			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1,roomNum);
			stmt.executeQuery();
			rs = stmt.getResultSet();

			while(rs.next()){
				if(timing.equalsIgnoreCase("morning")){
					if(rs.getInt("morningcheck")!=0){
						checked++;
					}
					totalElder++;
				}
				else if(timing.equalsIgnoreCase("afternoon")){
					if(rs.getInt("afternooncheck")!=0){
						checked++;
					}
					totalElder++;
				}

				else{
					if(rs.getInt("nooncheck")!=0){
						checked++;
					}
					totalElder++;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(checked==totalElder){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static void UpdateCheckUpTaken(int id,String timing,SQLObject so) {
		try {
			if(timing.equalsIgnoreCase("morning")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningcheck=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();

			}
			else if(timing.equalsIgnoreCase("afternoon")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET afternooncheck=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("noon") ){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET nooncheck=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static  void  StoreCheckUp(String name,int elderID, String elderDate,CheckUpObject checkup,String checktime) throws SQLException{
		SQLObject so = new SQLObject();
		PreparedStatement statement = so.getPreparedStatementWithKey("insert into et_elderly_checkup(id,name,date,checktime)"+"values(?,?,?,?)");
		statement.setInt(1, elderID);
		statement.setString(2,name);
		statement.setString(3, elderDate);
		statement.setString(4, checktime);
		statement.executeUpdate();

		PreparedStatement statementBlob = so.getPreparedStatementWithKey("UPDATE et_elderly_checkup SET checkup = ? WHERE id = ?");
		statementBlob.setObject(1, checkup);
		statementBlob.setInt(2, elderID);
		statementBlob.executeUpdate();
	}
	
	public static void StoreComments(int id,String comments) throws SQLException{
		SQLObject so = new SQLObject();
		PreparedStatement statement = so.getPreparedStatementWithKey("update et_elderly set checkupsummary=? where id=?");
		statement.setString(1, comments);
		statement.setInt(2, id);
		statement.executeUpdate();
	}
	// for report
	public static void RetrieveCheckUp(int id) throws SQLException, IOException, ClassNotFoundException{
		SQLObject so = new SQLObject();
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
	
	// debug mode
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//	Date date = new Date();
		//	String reportDate=dateFormat.format(date);
		int id=1;
		//CheckUpObject checking =new CheckUpObject(41,43,41,false,false);
		//StoreCheckUp("Ang Siew Fong",reportDate,id, checking);

		RetrieveCheckUp(id);
	}
}
