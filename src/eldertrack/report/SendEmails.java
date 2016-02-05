package eldertrack.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import eldertrack.db.SQLObject;

public class SendEmails {
private static SQLObject so = new SQLObject();
	
	static Date dNow = new Date( );
    static SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
    
    	
    	public static void main(String[] args){
    	 	   try {
    	 			ResultSet rsLoadID = so.getResultSet("SELECT id FROM et_report");
    	 						    				
    				while(rsLoadID.next()) {
    					int loadId = rsLoadID.getInt("id");
    					SendFiles(loadId);
    					}
    				
    	 		} catch (SQLException | ClassNotFoundException | IOException e) {
    	 			e.printStackTrace();	
    	 			}
    	 		
    	    }
    	
    	
    	public static void SendFiles (int id) throws SQLException, IOException, ClassNotFoundException {
    		
    		PreparedStatement statement = so.getPreparedStatementWithKey("SELECT report FROM et_report WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rsBlob = statement.executeQuery();
			rsBlob.next();
			
			PreparedStatement statement2 = so.getPreparedStatementWithKey("SELECT id,name,email FROM et_elderly WHERE id = ?");
			statement2.setInt(1, id);
			ResultSet rsElder = statement2.executeQuery();
			rsElder.next();
    		
					
   			
    			String email = rsElder.getString("email");
        		String name = rsElder.getString("name");
    			
    	    		try{
    	    			String to = email;
    	    			String from = "elderhome@gmail.com";
    	    			Properties properties = System.getProperties();
    	    			properties.setProperty("mail.smtp.host", "localhost");
    	    			properties.put("mail.smtp.port", "25");

    	    			Session session = Session.getDefaultInstance(properties);

    	    			MimeMessage message = new MimeMessage(session);

    		         
    	    			message.setFrom(new InternetAddress(from));
    	    			message.addRecipient(Message.RecipientType.TO,
    		                                        new InternetAddress(to));
    	    			message.setSubject(ft.format(dNow) +" Report: " +name);

    	    			BodyPart messageBodyPart = new MimeBodyPart();
    	    			Multipart multipart = new MimeMultipart();

    	    			messageBodyPart.setText
    	    			("Dear Family of " +name+","
    	    					+ "\n"
    	    					+ "\nPlease find attached " +name +"'s report for " +ft.format(dNow) +"."
    	    					+ "\n"
    	    					+ "\nThank you."
    	    					+ "\n"
    	    					+ "\n- Sent via ElderTrack Suite -");
    	    			multipart.addBodyPart(messageBodyPart);
    	    			
    	    			messageBodyPart = new MimeBodyPart();
    	    			String filename = ft.format(dNow)+" Report.pdf";	    			
    	   				    	    			
    	    			ByteArrayInputStream isGetBlob = new ByteArrayInputStream(rsBlob.getBytes(1));  
    	    			DataSource sourceBlob = new ByteArrayDataSource(isGetBlob, "application/pdf");
    	    			messageBodyPart.setDataHandler(new DataHandler(sourceBlob));
    	    			messageBodyPart.setFileName(filename);
    	    			
    	    			multipart.addBodyPart(messageBodyPart);
    	    			
    	    			message.setContent(multipart);
    	    			
    	    			Transport.send(message);
    	   				
    	    			}catch (MessagingException mex) {
    	    				mex.printStackTrace();
    	    			}    			
    			}
    		}
    	
    