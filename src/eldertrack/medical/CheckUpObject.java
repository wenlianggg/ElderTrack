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
	private String elderNotes;
	
	private String elderDate;


	public CheckUpObject(){

	}

	public CheckUpObject(double elderTemp, double elderBlood, int elderHeart,double elderSugar, boolean elderEye, boolean elderEar) {
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
	public String getElderNotes() {
		return elderNotes;
	}

	public void setElderNotes(String elderNotes) {
		this.elderNotes = elderNotes;
	}

	public void view(){
		System.out.println(getElderTemp());
		System.out.println(getElderBlood());
		System.out.println(getElderHeart());
		System.out.println(isElderEye());
		System.out.println(isElderEar());
		System.out.println(getElderNotes());
	}


	/*
	 * Method:  ResetCheckUp(SQLObject so
	 * Purpose: Check if there is a need to reset check up
	 * Return: void
	 */	
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
				e.printStackTrace();
			}
		}
	}

	/*
	 * Method:  checkupValid(String roomNum,String timing,SQLObject so)
	 * Purpose: Check if there is a need to do check up for this room
	 * Return: Boolean
	 */	
	public static Boolean checkupValid(String roomNum,String timeOfDay,SQLObject so){
		ResultSet rs = null;
		int totalElder=0;
		int checked=0;
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1,roomNum);
			stmt.executeQuery();
			rs = stmt.getResultSet();
			while(rs.next()){
				if(timeOfDay.equalsIgnoreCase("morning")){
					if(rs.getInt("morningcheck")!=0){
						checked++;
					}
					totalElder++;
				}
				else if(timeOfDay.equalsIgnoreCase("afternoon")){
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

	/*
	 * Method: UpdateCheckUpTaken(int id,String timing,SQLObject so)
	 * Purpose: Check if there is a need to do check up for this room
	 * Return: void
	 */	
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

	/*
	 * Method: StoreCheckUp(String name,int elderID, String elderDate,CheckUpObject checkup,String checktime)
	 * Purpose: Store check up variables as an object into database
	 * Return: void
	 */	
	public static void StoreCheckUp(String name,int elderID, String elderDate,CheckUpObject checkup,String checktime){
		SQLObject so = new SQLObject();
		PreparedStatement statement = so.getPreparedStatementWithKey("INSERT into et_elderly_checkup(id,name,date,checktime)"+"values(?,?,?,?)");
		try {
			statement.setInt(1, elderID);
			statement.setString(2,name);
			statement.setString(3, elderDate);
			statement.setString(4, checktime);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		PreparedStatement statementBlob = so.getPreparedStatementWithKey("UPDATE et_elderly_checkup SET checkup = ? WHERE id = ?");
		try {
			statementBlob.setObject(1, checkup);
			statementBlob.setInt(2, elderID);
			statementBlob.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method: StoreComments(int id,String comments)
	 * Purpose: Store comments into database
	 * Return: void
	 */	
	public static void StoreComments(int id,String comments){
		SQLObject so = new SQLObject();
		PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET checkupsummary=? WHERE id=?");
		try {
			statement.setString(1, comments);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method: validValues(String temp, String bloodPressure, String heartRate, String sugarLvl)
	 * Purpose: Check if values input are valid
	 * Return: boolean
	 */	
	public static boolean CheckUpValidationInput(CheckUpObject validationChecking,String eyeValid,String earValid){
		if(validationChecking.getElderTemp() < 26 || validationChecking.getElderTemp() > 50 || validationChecking.getElderTemp() == 0){
			return false;
		}
		else if(validationChecking.getElderBlood() < 70 || validationChecking.getElderBlood() > 190 || validationChecking.getElderBlood() == 0){
			return false;
		}
		else if(validationChecking.getElderHeart()< 80 || validationChecking.getElderHeart() > 200 ||  validationChecking.getElderHeart() == 0){
			return false;
		}
		else if(validationChecking.getElderSugar() < 2.6 || validationChecking.getElderSugar() > 21.1 || validationChecking.getElderSugar() == 0){
			return false;
		}
		else if(eyeValid.equalsIgnoreCase(" ")){
			return false;
		}
		else if(earValid.equalsIgnoreCase(" ")){
			return false;
		}
		else{
			return true;
		}
	}

	// for report
	public static void RetrieveCheckUp(int id) throws SQLException, IOException, ClassNotFoundException{
		SQLObject so = new SQLObject();
		CheckUpObject checking=null;
		String name=null;
		String date=null;
		PreparedStatement statement = so.getPreparedStatementWithKey("SELECT name,date FROM et_elderly_checkup WHERE id = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			name=rs.getString("name");
			date=rs.getString("date");
		}
		PreparedStatement statement1 = so.getPreparedStatementWithKey("SELECT checkup FROM et_elderly_checkup WHERE id = ?");
		statement1.setInt(1, id);
		ResultSet rs1 = statement1.executeQuery();
		while(rs1.next()){
			ByteArrayInputStream in = new ByteArrayInputStream(rs1.getBytes(1));
			ObjectInputStream is = new ObjectInputStream(in);
			Object checkingBlob =(Object) is.readObject();
			checking=(CheckUpObject) checkingBlob;
		}
		System.out.println(name);
		System.out.println(date);
		checking.view();
	}


	// debug mode
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//	Date date = new Date();
		//	String reportDate=dateFormat.format(date);
		//	int id=1;
		//CheckUpObject checking =new CheckUpObject(41,43,41,false,false);
		//StoreCheckUp("Ang Siew Fong",reportDate,id, checking);
		
		//RetrieveCheckUp(5);
	}

}
