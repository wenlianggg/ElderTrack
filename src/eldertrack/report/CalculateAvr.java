package eldertrack.report;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class CalculateAvr{
	private static String name;
	private static String date;
	@SuppressWarnings("unused")
	private static String checktime;
	private static double temp;
	private static double blood;
	private static double heart;
	private static double sugar;
	private static boolean eye;
	private static boolean ear;
	private static double tempMTot;
	private static double bloodMTot;
	private static double heartMTot;
	private static double sugarMTot;
	private static double tempMAvr;
	private static double bloodMAvr;
	private static double heartMAvr;
	private static double sugarMAvr;
	private static boolean eyeMAvr;
	private static boolean earMAvr;
	private static int counterM=0;
    
    /////////////////////////////
    
	static SQLObject so = new SQLObject();    
    
    static PreparedStatement statementInsertAvr = so.getPreparedStatementWithKey
    		("INSERT INTO et_reportAvr (name,id,date,tempMAvr,bloodMAvr,heartMAvr,sugarMAvr,eyeMAvr,earMAvr,addComments) values(?,?,?,?,?,?,?,?,?,?)");
	
	///////////////////////////////////////

    
	public static void main(String[] args){
		   try {
				ResultSet rsLoadID = so.getResultSet("SELECT id, name, date FROM et_reportTemp ORDER BY name,date");
							
				int newId=0;
				
				while(rsLoadID.next()) {
					int loadId = rsLoadID.getInt("id");
					if (loadId!=newId){
						getValues(loadId);
						newId=loadId;
					}
				}	
					
			} catch (SQLException | ClassNotFoundException | IOException e) {
				e.printStackTrace();	
				}
			
	   }
	
/////////////////////////////////
    public static void getValues (int id) throws SQLException, IOException, ClassNotFoundException {
    	PreparedStatement statement = so.getPreparedStatementWithKey("SELECT * FROM et_reportTemp WHERE id = ?");
    	statement.setInt(1, id);
    	ResultSet rsTmp = statement.executeQuery();
    	
    	while (rsTmp.next()){
		    	name=rsTmp.getString("name");
		    	date=rsTmp.getString("date");
		    	checktime=rsTmp.getString("checktime");
		    	temp=rsTmp.getDouble("temp");
		    	blood=rsTmp.getDouble("blood");
		    	heart=rsTmp.getDouble("heart");
		    	sugar=rsTmp.getDouble("sugar");
		    	eye=rsTmp.getBoolean("eye");
		    	ear=rsTmp.getBoolean("ear");
		    	
		    	tempMTot+=temp;
		    	bloodMTot+=blood;
		    	heartMTot+=heart;
		    	sugarMTot+=sugar;
		    	
		    	if (eye==true){
		    		eyeMAvr=true;
		    	}
		    	else{
		    		eyeMAvr=false;
		    	}
		    	if (ear==true){
		    		earMAvr=true;
		    	}
		    	else{
		    		earMAvr=false;
		    	}		
		    	
		    	counterM++;
		    	
		    	calAvr(tempMTot, bloodMTot, heartMTot, sugarMTot, counterM);
		    	
		    	try {
		    		statementInsertAvr.setString(1, name);
		    		statementInsertAvr.setInt(2, id);
					statementInsertAvr.setString(3, date);
					statementInsertAvr.setDouble(4,tempMAvr);
					statementInsertAvr.setDouble(5, bloodMAvr);
					statementInsertAvr.setDouble(6, heartMAvr);
					statementInsertAvr.setDouble(7, sugarMAvr);
					statementInsertAvr.setBoolean(8, eyeMAvr);
					statementInsertAvr.setBoolean(9, earMAvr);
					statementInsertAvr.setString(10, "");
					statementInsertAvr.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		    	
		    	counterM=0;			
		    	tempMTot=0;
		    	bloodMTot=0;
		    	heartMTot=0;
		    	sugarMTot=0;
    	}
    }
/////////////////////
    	private static void calAvr(double tempMTot2, double bloodMTot2, double heartMTot2, double sugarMTot2, int counterM) {
	
    	tempMAvr=tempMTot/counterM;
    	bloodMAvr=bloodMTot/counterM;
    	heartMAvr=heartMTot/counterM;
    	sugarMAvr=sugarMTot/counterM;
    }
}