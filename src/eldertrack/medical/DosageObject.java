package eldertrack.medical;

import java.io.*;
import java.sql.*;



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
	public void print(){
		System.out.println("Des: "+getMedDescrip());
		System.out.println("Pre: "+getMedPrescrip());
		System.out.println("Type: "+getMedType());
		System.out.println("Dos: "+getMedDosage());
	}
	//database
	
		/*convert object into byte array*/
		private static byte[] convertObjectToByteArray(Object obj) throws IOException {
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
	        objectOutputStream.writeObject(obj);
	        return byteArrayOutputStream.toByteArray();
		}
		// Connection
		private static Connection getConnection() throws Exception {
            String driver = "org.gjt.mm.mysql.Driver";
        	String url = "jdbc:mysql://eldertrackdb.ctfjtggc5l0j.ap-southeast-1.rds.amazonaws.com:3306/eldertrack";
        	String dbuser = "eldertrackadmin";
        	String dbpw = "eldertrack4321";
            Class.forName(driver);
            
            Connection con = DriverManager.getConnection(url, dbuser, dbpw);
            return con;
		}
		 // This method will help to save java objects into database
		 private static long saveBlob(Connection con, Object javaObject2Persist) {

             byte[] byteArray = null;
             PreparedStatement preparedStatement = null;
             String SQLQUERY_TO_SAVE_JAVAOBJECT = "INSERT INTO et_elderly(object_name, java_object) VALUES (?, ?)";
             int persistObjectID = -1;
             try {
                 byteArray = convertObjectToByteArray(javaObject2Persist);
                 preparedStatement = con.prepareStatement(SQLQUERY_TO_SAVE_JAVAOBJECT,PreparedStatement.RETURN_GENERATED_KEYS);
                     preparedStatement.setString(1, javaObject2Persist.getClass().getName());
                     preparedStatement.setBytes(2, byteArray);
                     preparedStatement.executeUpdate();
                     
                     System.out.println("Query - "+ SQLQUERY_TO_SAVE_JAVAOBJECT+ " is successfully executed for Java object serialization ");
                     //Trying to get the Generated Key
                     ResultSet rs = preparedStatement.getGeneratedKeys();
                      if (rs.next()) {
                        persistObjectID = rs.getInt(1);
                        System.out.println("Object ID while saving the binary object is->" + persistObjectID);
                  }

               preparedStatement.close();
             } catch (SQLException e) {
                         e.printStackTrace();
             } catch (Exception e) {
                         e.printStackTrace();
             }
             return persistObjectID;
		 	}

			/** This method will help to read java objects from database*/               
			private static byte[] getBlob(Connection con, long objectId) {
			     String SQLQUERY_TO_READ_JAVAOBJECT= "SELECT java_object FROM persist_java_objects WHERE object_id = ?;";
			      PreparedStatement pstmt = null;
			      ResultSet resultSet = null;
			      Blob blob = null;
			      byte[] bytes = null;
			      try {
			         pstmt = con.prepareStatement(SQLQUERY_TO_READ_JAVAOBJECT);
			         System.out.println("Reading the saved Object from the database where the object Id is:->" + objectId);
			         pstmt.setLong(1, objectId);
			         
			         resultSet = pstmt.executeQuery();
			        while (resultSet.next()) {
			         blob = resultSet.getBlob(1);
			        }
			        bytes = blob.getBytes(1, (int) (blob.length()));
			      } catch (SQLException e) {
			          e.printStackTrace();
			      } catch (Exception e) {
			          e.printStackTrace();
			      }
			    return bytes;
			 }
		public static void main(String[] args) throws Exception {
			Connection con=null;
			con=getConnection();
			
		}

}
