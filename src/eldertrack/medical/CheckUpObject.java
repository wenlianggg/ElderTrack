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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import eldertrack.db.SQLObject;
import eldertrack.login.StaffSession;
import eldertrack.ui.MainFrame;

public class CheckUpObject  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4101958710431937395L;
	private double elderTemp;
	private double elderBlood;
	private int elderHeart;
	private double elderSugar;
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

	public double getElderSugar() {
		return elderSugar;
	}
	public void setElderSugar(double elderSugar) {
		this.elderSugar = elderSugar;
	}
	public double getElderTemp() {
		return elderTemp;
	}
	public void setElderTemp(double elderTemp) {
		this.elderTemp = elderTemp;
	}
	public double getElderBlood() {
		return elderBlood;
	}
	public void setElderBlood(double elderBlood) {
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
	
	public static void ResetCheckUp(SQLObject so){
		ResultSet rs;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date lastlogin = null;
		Date currectdate = new Date();
		StaffSession session = MainFrame.getInstance().getSessionInstance();
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT lastlogin FROM et_staff where staffid=?");
			stmt.setInt(1, session.getStaffid());
			stmt.executeQuery();
			rs = stmt.getResultSet();
			rs.next();
			lastlogin=rs.getTimestamp("lastlogin");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lastDate=dateFormat.format(lastlogin);
		String checkNowDate=dateFormat.format(currectdate);
		if(!lastDate.equals(checkNowDate)){
			try {
				PreparedStatement stmt  = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningcheck = ?, afternooncheck = ?, nooncheck = ?");
				stmt.setInt(1, 0);
				stmt.setInt(2, 0);
				stmt.setInt(3, 0);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Boolean CheckUpInputValidation(){
		
		
		
		
		
		return false;
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
	
	public static void StoreCheckUp(String name,int elderID, String elderDate,CheckUpObject checkup,String checktime) throws SQLException{
		SQLObject so = new SQLObject();
		PreparedStatement statement = so.getPreparedStatementWithKey("INSERT into et_elderly_checkup(id,name,date,checktime)"+"values(?,?,?,?)");
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
	
	// Methods for validation
	public static boolean validValues(String temp, String bloodPressure, String heartRate, String sugarLvl){
	
		if(Double.parseDouble(temp) < 0 || Double.parseDouble(temp) > 60 || temp.equals("")){
			return false;
		}else if(Double.parseDouble(bloodPressure) < 70 || Double.parseDouble(bloodPressure) > 190 || bloodPressure.equals("")){
			return false;
		}else if(Integer.parseInt(heartRate) < 80 || Integer.parseInt(heartRate) > 200 || heartRate.equals("")){
			return false;
		}else if(Double.parseDouble(sugarLvl) < 2.6 || Double.parseDouble(sugarLvl) > 21.1 || sugarLvl.equals("")){
			return false;
		}else{
			return true;
		}
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
