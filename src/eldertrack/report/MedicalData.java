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
	private static String notes;
	static SQLObject so = new SQLObject();

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		try {
			ResultSet rsLoadID = so.getResultSet("SELECT id, name, date FROM et_elderly_checkup ORDER BY id,name,date");
						
			int doneId=0;
			
			while(rsLoadID.next()) {
				int loadId = rsLoadID.getInt("id");
				if (doneId!=loadId){
					RetrieveCheckUp(loadId);
					doneId=loadId;
				}
			}	
				
		} catch (SQLException e) {
			e.printStackTrace();	
			}
		}

	
	public static void RetrieveCheckUp(int id) throws SQLException, IOException, ClassNotFoundException{
		PreparedStatement statement = so.getPreparedStatementWithKey("SELECT checkup,name,date,checktime,id FROM et_elderly_checkup WHERE id = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
				
		@SuppressWarnings("unused")
		ResultSet rsTmp = so.getResultSet("SELECT * FROM et_reportTemp" );
		PreparedStatement statementInsertTmp = so.getPreparedStatementWithKey
				("INSERT INTO et_reportTemp (name,date,checktime,temp,blood,heart,sugar,eye,ear,notes,id) values(?,?,?,?,?,?,?,?,?,?,?)");
		
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
			notes=checking.getElderNotes();
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
			statementInsertTmp.setString(10, notes);
			statementInsertTmp.setInt(11, id);
			statementInsertTmp.executeUpdate();
			
			name=null;
			date=null;
			checktime=null;
			temp=0;
			blood=0;
			heart=0;
			sugar=0;
			eye=false;
			ear=false;
			notes=null;
		} 
	}
		
	
}
