package eldertrack.report;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.JOptionPane;

import eldertrack.db.SQLObject;

public class SendEmails {
private static SQLObject so = new SQLObject();
	
	Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
    {
    	try{
    		String sql = "SELECT email, name FROM et_elderly";
    		ResultSet rs = so.getResultSet(sql);
		
    		while(rs.next()){
    			String email = rs.getString("email");
    			String name = rs.getString("name");
    			
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
    	    					+ "\nRegards,"
    	    					+ "\nElder Home Name");
    	    			multipart.addBodyPart(messageBodyPart);
    	    			
    	    			
    	   				PreparedStatement statement2 = so.getPreparedStatementWithKey("SELECT report FROM et_report WHERE name = ?");
    	   				statement2.setString(1, rs.getString("name"));
    	   				ResultSet rsBlob = statement2.executeQuery();
    	   				rsBlob.next();
    	    			
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
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1);
			}
		System.out.println("Emails sent successfully.");
    }
}