package eldertrack.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;
import eldertrack.medical.CheckUpObject;

public class MedicalData implements Serializable{

	private static final long serialVersionUID = -4411264631392903848L;
	private static double temp;
	private static double blood;
	private static int heart;
	private static double sugar;
	private static boolean eye;
	private static boolean ear;
	private static String name;
	private static String date;
	private static String checktime;
	private static CheckUpObject checking;
	static SQLObject so = new SQLObject();
	private static String rsid;

	@SuppressWarnings("unused")
	public static void RetrieveCheckUp(int id) throws SQLException, IOException, ClassNotFoundException{
		PreparedStatement statement = so.getPreparedStatementWithKey("SELECT checkup,name,date,checktime,id FROM et_elderly_checkup WHERE id = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
				
		ResultSet rsTmp = so.getResultSet("SELECT * FROM et_reportTemp" );
		PreparedStatement statementInsertTmp = so.getPreparedStatementWithKey
				("INSERT INTO et_reportTemp (name,date,checktime,temperature,blood,heart,sugar,eye,ear,id) values(?,?,?,?,?,?,?,?,?,?)");
		
		while(rs.next()){
			PreparedStatement statement2 = so.getPreparedStatementWithKey("SELECT checkup,id FROM et_elderly_checkup WHERE id = ?");
			statement2.setInt(1, rs.getInt("id"));
			ResultSet rsBlob = statement2.executeQuery();
			rsBlob.next();
			
			ByteArrayInputStream in = new ByteArrayInputStream(rsBlob.getBytes(1));
			ObjectInputStream is = new ObjectInputStream(in);
			Object checkingBlob =(Object) is.readObject();
			checking=(CheckUpObject) checkingBlob;
			
			temp=checking.getElderTemp();
			blood=checking.getElderBlood();
			heart=checking.getElderHeart();
			sugar=checking.getElderSugar();
			eye=checking.isElderEye();
			ear=checking.isElderEar();			
			name=rs.getString("name");
			date=rs.getString("date");
			checktime=rs.getString("checktime");
				
			statementInsertTmp.setString(1, name);
			statementInsertTmp.setString(2, date);
			statementInsertTmp.setString(3, checktime);
			statementInsertTmp.setDouble(4, temp);
			statementInsertTmp.setDouble(5, blood);
			statementInsertTmp.setInt(6, heart);
			statementInsertTmp.setDouble(7, sugar);
			statementInsertTmp.setBoolean(8, eye);
			statementInsertTmp.setBoolean(9, ear);
			statementInsertTmp.setInt(10, id);
			statementInsertTmp.executeUpdate();
		} 
	}
	
	private static void CalAverage(int id) {
		ResultSet rsCalAvr = so.getResultSet("SELECT * FROM et_reportTemp ORDER BY name, id, date, checktime");
		PreparedStatement calAvr = so.getPreparedStatementWithKey("SELECT");
	}
		
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		try {
			ResultSet rsLoadID = so.getResultSet("SELECT id, name, date FROM et_elderly_checkup ORDER BY name,date");
						
			while(rsLoadID.next()) {
				int id = rsLoadID.getInt("id");
				RetrieveCheckUp(id);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();	
			}
		
		try {
			ResultSet rsCalAvr = so.getResultSet("SELECT * FROM et_reportTemp ORDER BY name, id, date, checktime");
						
			while(rsCalAvr.next()) {
				int id = rsCalAvr.getInt("id");
				CalAverage(id);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();	
			}
		
		System.out.println("Data processed");
		}

}
