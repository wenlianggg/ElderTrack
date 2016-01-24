package eldertrack.medical;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;
import eldertrack.login.StaffSession;
import eldertrack.ui.MainFrame;

public class DosageObject implements Serializable{

private static final long serialVersionUID = 5857426734879201401L;
	
	
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
	public void print(){
		System.out.println("Des: "+getMedDescrip());
		System.out.println("Pre: "+getMedPrescrip());
		System.out.println("Type: "+getMedType());
		System.out.println("Dos: "+getMedDosage());
	}
	
	public static void ResetDosage(SQLObject so){
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
				PreparedStatement stmt  = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningtaken = ?, afternoontaken = ?, noontaken = ?");
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
	
	
	public static JTable managementTableModel(JTable MainTable ){
		MainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		MainTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		MainTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		MainTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		MainTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		return MainTable;
	}
	
	
	public static Boolean checkDosageNeeded(ElderData summaryData){
		if(summaryData.getElderNumDosageNeeded()==0){
			JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
			return false;
		}
		else{
			return true;
		}
	}
	
	public static Boolean checkDosageValid(String roomNum,String timing,SQLObject so){
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
					if(rs.getInt("morningtaken")!=0){
						checked++;
					}
					totalElder++;
				}
				else if(timing.equalsIgnoreCase("afternoon")){
					if(rs.getInt("afternoontaken")!=0){
						checked++;
					}
					totalElder++;
				}

				else{
					if(rs.getInt("noontaken")!=0){
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
	public static void UpdateDosageTaken(int id,String timing){
		SQLObject so = new SQLObject();
		try {
			if(timing.equalsIgnoreCase("morning")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningtaken=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();

			}
			else if(timing.equalsIgnoreCase("afternoon")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET afternoontaken=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("noon")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET noontaken=?  WHERE id = ?");
				ps.setInt(1, 1);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setTableManage(String timing,int id,ArrayList<DosageObject> ManagementList,int taken){
		try {
			SQLObject so=new SQLObject();
			if(timing.equalsIgnoreCase("morning")){
				PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningdosage = ?, morningtaken = ? WHERE id = ?");
				statement.setObject(1, ManagementList);
				statement.setInt(2, taken);
				statement.setInt(3, id);
				statement.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("afternoon")){
				PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET afternoondosage = ? ,afternoontaken = ? WHERE id = ?");
				statement.setObject(1, ManagementList);
				statement.setInt(2, taken);
				statement.setInt(3, id);
				statement.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("noon")){
				PreparedStatement statement = so.getPreparedStatementWithKey("UPDATE et_elderly SET noondosage = ?, noontaken = ? WHERE id = ?");
				statement.setObject(1, ManagementList);
				statement.setInt(2, taken);
				statement.setInt(3, id);
				statement.executeUpdate();
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static ArrayList<DosageObject> updateProcessTable(JTable tablemodel){
		ArrayList<DosageObject> updateList=new ArrayList<DosageObject>();
		DosageObject updateDosage;
		for(int i=0;i<tablemodel.getRowCount();i++){
			updateDosage=new DosageObject();
			updateDosage.setMedDescrip((String) tablemodel.getModel().getValueAt(i,0));
			updateDosage.setMedPrescrip((String) tablemodel.getModel().getValueAt(i,1));
			updateDosage.setMedType((String) tablemodel.getModel().getValueAt(i,2));
			updateDosage.setMedDosage((String) tablemodel.getModel().getValueAt(i,3));
			updateList.add(updateDosage);
		}
		return updateList;
	}
	
	
	public static DefaultTableModel buildDefaultManageModel(){
		DefaultTableModel defModel;
		defModel=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
				);
		return defModel;
	}

}
