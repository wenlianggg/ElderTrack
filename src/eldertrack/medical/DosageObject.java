package eldertrack.medical;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eldertrack.db.SQLObject;

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
