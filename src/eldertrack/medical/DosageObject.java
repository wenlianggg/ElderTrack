package eldertrack.medical;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import eldertrack.db.SQLObject;

public class DosageObject implements java.io.Serializable{

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
	
	//database
	public static Connection getConnection() throws Exception {
	    String driver = "org.gjt.mm.mysql.Driver";
	    String url = "jdbc:mysql://eldertrackdb.ctfjtggc5l0j.ap-southeast-1.rds.amazonaws.com:3306/eldertrack";
		String dbuser = "eldertrackadmin";
		String dbpw = "eldertrack4321";
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url, dbuser, dbpw);
	    return conn;
	  }
	public void insertIntoEmp(Connection con) {
	    DosageObject dos1 = new DosageObject("Head","For Head", "Tablet","10");
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(dos1);
	      // serialize the dosage object into a byte array
	      byte[] dosageAsBytes = baos.toByteArray();
	      PreparedStatement pstmt = con.prepareStatement("INSERT INTO medication (dos1) VALUES(?)");
	      ByteArrayInputStream bais = new ByteArrayInputStream(dosageAsBytes);
	      // bind our byte array  to the dos column
	      pstmt.setBinaryStream(1,bais, dosageAsBytes.length);
	      pstmt.executeUpdate();
	      con.commit(); 
	      pstmt.close();
	      }
	    catch(Exception e) {
	      e.printStackTrace();
	      }
	    }  

	  public  static void main (String []args) {
	    boolean found;
	    Connection con=null;
	    try {
	    	con=getConnection();
	    	System.out.println("conn=" + con);
	      Statement stmt = con.createStatement();
	      ResultSet rs = 
	         stmt.executeQuery("SELECT medication FROM et_elderly");
	      // loop through the result set
	      while (rs.next()) {
	         // fetch the serialized object to a byte array
	         byte[] st = (byte[])rs.getObject(1);
	         //   or  byte[] st = rs.getBytes(1);
	         //   or  Blob aBlob = rs.getBlob(1);
	         //       byte[] st = aBlob.getBytes(0, (int) aBlob.length());
	         ByteArrayInputStream baip = new ByteArrayInputStream(st);
	         ObjectInputStream ois =  new ObjectInputStream(baip);
	         // re-create the object
	         DosageObject dos2 = (DosageObject)ois.readObject();
	         // display the result for demonstration
	         System.out.println(dos2.getMedDescrip());
	         }
	      stmt.close();
	      rs.close();
	      }
	    catch(Exception e) {
	      e.printStackTrace();
	      }
	    }
	 


}
