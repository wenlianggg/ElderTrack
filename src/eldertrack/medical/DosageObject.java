package eldertrack.medical;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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


	/*
	 * Method: ResetDosage(SQLObject so)
	 * Purpose: To check if its a new day, if so reset the dosage tracker
	 * Return: void
	 */
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
			e.printStackTrace();
		}
		String lastDate=dateFormat.format(lastlogin);
		String checkNowDate=dateFormat.format(currectdate);
		if(!lastDate.equalsIgnoreCase(checkNowDate)){
			try {
				PreparedStatement stmt  = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningtaken = ?, afternoontaken = ?, noontaken = ?");
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
	 * Method: managementTableModel(JTable MainTable )
	 * Purpose: Passes in a table to set the model for the management table
	 * Return: JTable
	 */
	public static JTable managementTableModel(JTable MainTable ){
		MainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		MainTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		MainTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		MainTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		MainTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		return MainTable;
	}


	/*
	 * Method: checkDosageNeeded(ElderData summaryData)
	 * Purpose: To check if dosage is needed
	 * Return: Boolean
	 */
	public static Boolean checkDosageNeeded(ElderData summaryData){
		if(summaryData.getElderNumDosageNeeded()==0){
			JOptionPane.showMessageDialog(null, "There is no requirement to do Dosage Tracking for this room");
			return false;
		}
		else{
			return true;
		}
	}
	/*
	 * Method: checkDosageValid(String roomNum,String TimeOfDay,SQLObject so)
	 * Purpose: To if dosage is needed, has it been completed
	 * Return: Boolean
	 */
	public static Boolean checkDosageValid(String roomNum,String TimeOfDay,SQLObject so){
		ResultSet rs = null;
		int totalElder=0;
		int checkedDosage=0;
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT * FROM et_elderly WHERE room = ?");
			stmt.setString(1,roomNum);
			stmt.executeQuery();
			rs = stmt.getResultSet();
			while(rs.next()){
				if(TimeOfDay.equalsIgnoreCase("Morning")){
					if(rs.getInt("morningtaken")!=0){
						checkedDosage++;
						totalElder++;
					}
				}
				else if(TimeOfDay.equalsIgnoreCase("Afternoon")){
					if(rs.getInt("afternoontaken")!=0){
						checkedDosage++;
						totalElder++;
					}
				}
				else if(TimeOfDay.equalsIgnoreCase("Noon")){
					if(rs.getInt("noontaken")!=0){
						checkedDosage++;
						totalElder++;
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(checkedDosage==totalElder){
			return false;
		}
		else{
			return true;
		}
	}

	/*
	 * Method: UpdateDosageTaken(int id,String timing)
	 * Purpose: Update the database that dosage has been done
	 * Return: void
	 */	
	public static void UpdateDosageTaken(String name,String timing){
		SQLObject so = new SQLObject();
		try {
			if(timing.equalsIgnoreCase("morning")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET morningtaken=?  WHERE name = ?");
				ps.setInt(1, 1);
				ps.setString(2, name);
				ps.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("afternoon")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET afternoontaken=?  WHERE name = ?");
				ps.setInt(1, 1);
				ps.setString(2, name);
				ps.executeUpdate();
			}
			else if(timing.equalsIgnoreCase("noon")){
				PreparedStatement ps = so.getPreparedStatementWithKey("UPDATE et_elderly SET noontaken=?  WHERE name = ?");
				ps.setInt(1, 1);
				ps.setString(2, name);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method: setTableManage(String timing,int id,ArrayList<DosageObject> ManagementList,int taken)
	 * Purpose: Updates taken to 0 when new dosage is created
	 * Return: void
	 */	
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
			e.printStackTrace();
		}
	}

	/*
	 * Method: GetListOfTreatMent(SQLObject so)
	 * Purpose: Get the list of treatment from DB and store into a List<String>
	 * Return: List<String>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox<String> GetListOfTreatMent(SQLObject so){
		ResultSet rs;

		List<String> treatmentList=new ArrayList<String>();
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT DISTINCT treatment FROM et_medication");
			stmt.executeQuery();
			rs=stmt.getResultSet();
			treatmentList.add("-Selection-");
			while(rs.next()){
				treatmentList.add(rs.getString("treatment"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JComboBox<String> treatmentBox=new JComboBox<String>();
		treatmentBox.setModel(new DefaultComboBoxModel(treatmentList.toArray()));

		return treatmentBox;
	}


	/*
	 * Method: GetListOfMedication(SQLObject so,String treatment)
	 * Purpose: Get the list of medicine corresponding with the type of treatment selected from DB and store into a List<String>
	 * Return: List<String>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox<String> GetListOfMedication(SQLObject so,String treatment){
		ResultSet rs;
		List<String> medicationList=new ArrayList<String>();
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT medication FROM et_medication WHERE treatment=? ");
			stmt.setString(1, treatment);
			stmt.executeQuery();
			rs=stmt.getResultSet();
			medicationList.add("-Selection-");
			while(rs.next()){
				medicationList.add(rs.getString("medication"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JComboBox<String> medicationBox=new JComboBox<String>();
		medicationBox.setModel(new DefaultComboBoxModel(medicationList.toArray()));

		return medicationBox;
	}

	/*
	 * Method: GetListOfDosageLimit(SQLObject so,String treatment)
	 * Purpose: Get list of dosage limit corresponding to the treatment
	 * Return: List<String>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox<Double> GetListOfDosageLimit(SQLObject so,String treatment){
		ResultSet rs;
		List<Double> dosageLimitList=new ArrayList<Double>();
		double dosLimit = 0;
		double dosAmt=0.5;
		try {
			PreparedStatement stmt  = so.getPreparedStatementWithKey("SELECT DISTINCT treatment,dosagelimit FROM et_medication WHERE treatment=? ");
			stmt.setString(1, treatment);
			stmt.executeQuery();
			rs=stmt.getResultSet();
			rs.next();
			dosLimit=rs.getDouble("dosagelimit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(double k=50;k<dosLimit;k+=50){
			if(dosLimit/50>0){
				dosageLimitList.add(dosAmt);
			}
			dosAmt+=0.5;
		}
		JComboBox<Double> dosLimitBox=new JComboBox<Double>();
		dosLimitBox.setModel(new DefaultComboBoxModel(dosageLimitList.toArray()));
		return dosLimitBox;

	}

	/*
	 * Method: CheckDuplicateManagementTable(JTable tablemodel)
	 * Purpose: Check if there is any duplication in creating a new dosage
	 * Return: Boolean
	 */	
	public static Boolean CheckDuplicateManagementTable(JTable tablemodel){
		ArrayList<String> duplicationCheckList=new ArrayList<String>();
		Boolean result=true;
		for(int i=0;i<tablemodel.getRowCount();i++){
			if(!tablemodel.getModel().getValueAt(i,0).equals("-Selection-")){
				if(duplicationCheckList.contains((String) tablemodel.getModel().getValueAt(i,0))){
					result=false;
					break;
				}
				else{
					duplicationCheckList.add((String) tablemodel.getModel().getValueAt(i,0));
				}
			}
		}		
		return result;
	}

	/*
	 * Method: CheckNullValuesManagementTable(JTable tablemodel)
	 * Purpose: Check if there is any null values in the table
	 * Return: Boolean
	 */	
	public static Boolean CheckNullValuesManagementTable(JTable tablemodel){
		Boolean result=true;
		for(int i=0;i<tablemodel.getRowCount();i++){
			for(int k=0;k<tablemodel.getColumnCount();k++){
				if(tablemodel.getValueAt(i, k)==null||tablemodel.getValueAt(i, k).toString().equalsIgnoreCase("-Selection-")){
					result=false;
				}
				else{
					result=true;
				}
			}
		}	
		return result;
	}

	/*
	 * Method: updateProcessTable(JTable tablemodel)
	 * Purpose: Creates new dosage objects corresponding to table values
	 * Return: ArrayList<DosageObject>
	 */	
	public static ArrayList<DosageObject> updateProcessTable(JTable tablemodel){
		ArrayList<DosageObject> updateList=new ArrayList<DosageObject>();
		DosageObject updateDosage;
		for(int i=0;i<tablemodel.getRowCount();i++){
			if(!((String) tablemodel.getModel().getValueAt(i,0)).equalsIgnoreCase("-Selection-")){
				updateDosage=new DosageObject();
				updateDosage.setMedDescrip((String) tablemodel.getModel().getValueAt(i,0));
				updateDosage.setMedPrescrip((String) tablemodel.getModel().getValueAt(i,1));
				updateDosage.setMedType((String) tablemodel.getModel().getValueAt(i,2));
				updateDosage.setMedDosage(tablemodel.getModel().getValueAt(i,3).toString());
				updateList.add(updateDosage);
			}
		}
		return updateList;
	}

	/*
	 * Method: buildDefaultManageModel()
	 * Purpose: Builds the table for adding of new dosage
	 * Return: DefaultTableModel
	 */	
	public static DefaultTableModel buildDefaultManageModel(){
		DefaultTableModel defModel;
		defModel=new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Description", "Prescription", "Medication Type","Dosage"
				}
				);
		return defModel;
	}


	/*	Debugging
	 * public static void main(String[] args) {
		SQLObject so=new SQLObject();
		JComboBox<Double> comboBox =GetListOfDosageLimit(so,"Glucofin");
		ComboBoxModel model = comboBox.getModel();
        int size = model.getSize();
        for(int i=0;i<size;i++) {
            Object element = model.getElementAt(i);
            System.out.println("Element at " + i + " = " + element);
        }
	}*/
}
