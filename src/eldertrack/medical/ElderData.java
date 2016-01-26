package eldertrack.medical;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import eldertrack.db.SQLObject;

public class ElderData{
	// common stuff
	private int elderBed;
	private String elderName;
	private int elderID;
	private int elderAge;
	private String elderGender;
	private String elderDosageSummary;
	
	// check up stuff
	private int elderNum;
	private int elderRoomNumber;
	private int elderNumMale;
	private int elderNumFemale;
	private int elderNumDosageNeeded;
	private int elderNumDosageNotNeeded;

	public ElderData(){

	}

	public ElderData(int elderNum, int elderNumMale, int elderNumFemale,int elderNumDosageNeeded,int elderNumDosageNotNeeded) {
		this.elderNum = elderNum;
		this.elderNumMale = elderNumMale;
		this.elderNumFemale = elderNumFemale;
		this.elderNumDosageNeeded=elderNumDosageNeeded;
		this.elderNumDosageNotNeeded=elderNumDosageNotNeeded;
	}

	public ElderData(int elderBed,String elderName,int elderID, int elderAge, String elderGender) {
		this.elderBed=elderBed;
		this.elderName = elderName;
		this.elderID=elderID;
		this.elderAge = elderAge;
		this.elderGender = elderGender;
	}
	public int getElderBed() {
		return elderBed;
	}

	public void setElderBed(int elderBed) {
		this.elderBed = elderBed;
	}

	public String getElderName() {
		return elderName;
	}

	public void setElderName(String elderName) {
		this.elderName = elderName;
	}

	public int getElderID() {
		return elderID;
	}

	public void setElderID(int elderID) {
		this.elderID = elderID;
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

	
	public String getElderDosageSummary() {
		return elderDosageSummary;
	}

	public void setElderDosageSummary(String elderDosageSummary) {
		this.elderDosageSummary = elderDosageSummary;
	}

	public int getElderNum() {
		return elderNum;
	}

	
	public int getElderRoomNumber() {
		return elderRoomNumber;
	}

	public void setElderRoomNumber(int elderRoomNumber) {
		this.elderRoomNumber = elderRoomNumber;
	}

	public void setElderNum(int elderNum) {
		this.elderNum += elderNum;
	}

	public int getElderNumMale() {
		return elderNumMale;
	}

	public void setElderNumMale(int elderNumMale) {
		this.elderNumMale += elderNumMale;
	}

	public int getElderNumFemale() {
		return elderNumFemale;
	}

	public void setElderNumFemale(int elderNumFemale) {
		this.elderNumFemale += elderNumFemale;
	}

	public int getElderNumDosageNeeded() {
		return elderNumDosageNeeded;
	}

	public void setElderNumDosageNeeded(int elderNumDosageNeeded) {
		this.elderNumDosageNeeded += elderNumDosageNeeded;
	}

	public int getElderNumDosageNotNeeded() {
		return elderNumDosageNotNeeded;
	}

	public void setElderNumDosageNotNeeded(int elderNumDosageNotNeeded) {
		this.elderNumDosageNotNeeded += elderNumDosageNotNeeded;
	}
	
	public void print(){
		System.out.println("ElderBed: "+getElderBed());
		System.out.println("Name: "+getElderName());
		System.out.println("Age: "+getElderAge());
		System.out.println("Gender: "+getElderGender());

	}
	
	public static void UpdateDosageSummary(SQLObject so,String dosageSummary,int id){
		try {
			PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET dosagesummary=? WHERE id=? ");
			statement.setString(1, dosageSummary);
			statement.setInt(2,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static String ManagementSummary(ArrayList<ElderData> manageSummary){
		StringBuilder stringBuilder = new StringBuilder();
		for(int k=0;k<manageSummary.size();k++){	
			stringBuilder.append("\r\n============================================================================ \r\nRoom Number: "+manageSummary.get(k).getElderRoomNumber() +"\r\nTotal number of elderly:"+manageSummary.get(k).getElderNum() 
			+"\r\nTotal Male elderly: " +manageSummary.get(k).getElderNumMale() +"\r\nTotal Female elderly:" +manageSummary.get(k).getElderNumFemale());
		}
		String finalString=stringBuilder.toString();
		return finalString;
	}
	public static ElderData updateManagementSummary(int roomNum,SQLObject so){
		ElderData data=new ElderData();
		try {
			ResultSet rs;
			PreparedStatement statement = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room=? ");
			statement.setInt(1, roomNum);
			rs = statement.executeQuery();
			data.setElderRoomNumber(roomNum);
			while(rs.next()){
				String gender=rs.getString("gender");
				data.setElderNum(1);
				if(gender.equalsIgnoreCase("m")){
					data.setElderNumMale(1);
				}
				else{
					data.setElderNumFemale(1);
				}
			}		
		} catch (SQLException e2) {

			e2.printStackTrace();
		}
		return data;
	}
	
	
	public static ElderData UpdateOverview(String roomNum,String time, SQLObject so ){
		ResultSet rs;
		if(!roomNum.equalsIgnoreCase(" ")){
		ElderData summaryData = new ElderData();
		summaryData.setElderRoomNumber(Integer.parseInt(roomNum));
		try {
			PreparedStatement statement = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room=? ");
			statement.setInt(1, Integer.parseInt(roomNum));
			rs = statement.executeQuery();
			while(rs.next()){
				String gender=rs.getString("gender");
				summaryData.setElderNum(1);
				if(gender.equalsIgnoreCase("m")){
					summaryData.setElderNumMale(1);
				}
				else{
					summaryData.setElderNumFemale(1);
				}
				if(time.equalsIgnoreCase("morning")){
					if(rs.getBlob("morningdosage")!= null){
						summaryData.setElderNumDosageNeeded(1);
					}
					else{
						summaryData.setElderNumDosageNotNeeded(1);
					}
				}
				else if(time.equalsIgnoreCase("afternoon")){
					if(rs.getBlob("afternoondosage")!= null){
						summaryData.setElderNumDosageNeeded(1);
					}
					else{
						summaryData.setElderNumDosageNotNeeded(1);
					}
				}
				else{
					if(rs.getBlob("noondosage")!= null){
						summaryData.setElderNumDosageNeeded(1);
					}
					else{
						summaryData.setElderNumDosageNotNeeded(1);
					}
				}
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return summaryData;
		}
		else{
			return null;
		}
		
	}
	
	public static ElderData getElderInformation(ResultSet rs){
		ElderData dataInfo=new ElderData();
		try{
			java.sql.Date reportDate=rs.getDate("dob");
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String text = df.format(reportDate);
			String year=text.substring(0, 4);
			String month=text.substring(5,7);
			String day=text.substring(8,10);

			// setting the information
			dataInfo.setElderID(rs.getInt("id"));
			dataInfo.setElderBed(rs.getInt("bed"));
			dataInfo.setElderName(rs.getString("name"));
			dataInfo.setElderAge(ElderData.getAge(year,month,day));
			dataInfo.setElderGender(rs.getString("gender"));
			dataInfo.setElderDosageSummary(rs.getString("dosagesummary"));
		}catch (SQLException e1) {
			e1.printStackTrace();
		}

		return dataInfo;

	}

	public static int getAge(String year, String month, String day)
	{
		Calendar calDOB = Calendar.getInstance();
		calDOB.set( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day) );

		Calendar calNow = Calendar.getInstance();
		calNow.setTime(new java.util.Date());

		int ageYr = (calNow.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR));

		int ageMo = (calNow.get(Calendar.MONTH) - calDOB.get(Calendar.MONTH));
		if (ageMo < 0){
			ageYr--;
		}
		return ageYr;
	}



	//debuger
	public static void main(String[] args) throws SQLException {
		SQLObject so = new SQLObject();
		ResultSet rs=so.getResultSet("SELECT * FROM et_elderly");
		ArrayList<ElderData> DosageList=new ArrayList<ElderData>();

		try {
			while(rs.next()){
				ElderData data=new ElderData();
				java.sql.Date reportDate=rs.getDate("dob");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String text = df.format(reportDate);
				String year=text.substring(0, 4);
				String month=text.substring(5,7);
				String day=text.substring(8,10);
				data.setElderBed(rs.getInt("bed"));
				data.setElderID(rs.getInt("id"));
				data.setElderName(rs.getString("name"));
				data.setElderAge(getAge(year,month,day));
				data.setElderGender(rs.getString("gender"));
				data.setElderDosageSummary(rs.getString("dosagesummary"));
				DosageList.add(data);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for(int i=0;i<DosageList.size();i++){
			DosageList.get(i).print();
			System.out.println();
		}
	}




}



