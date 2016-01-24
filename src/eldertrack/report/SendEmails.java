package eldertrack.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class SendEmails {
	
	Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
    MedicalData elderList = new MedicalData();
    
    ArrayList<String> emailList = new ArrayList<String>();
    
    {
		try{
			String to = emailList;
			String from = "elderhome@gmail.com";
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", "localhost");
			properties.put("mail.smtp.port", "25");

			Session session = Session.getDefaultInstance(properties);

			MimeMessage message = new MimeMessage(session);

	         
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,
	                                        new InternetAddress(to));
			message.setSubject(ft.format(dNow) +" Report: ELDER NAME");

			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			messageBodyPart.setText
			("Dear Family of NAME,"
					+ "\n"
					+ "\nPlease find attached NAME's report for " +ft.format(dNow) +"."
					+ "\n"
					+ "\nThank you."
					+ "\n"
					+ "\nRegards,"
					+ "\nElder Home Name");
	         multipart.addBodyPart(messageBodyPart);
   
	         messageBodyPart = new MimeBodyPart();
	         String filename = ft.format(dNow)+" Report.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);

	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(multipart);

	         Transport.send(message);
	         
	         System.out.println("Emails sent successfully.");
	         }catch (MessagingException mex) {
	        	 mex.printStackTrace();
	        	 }
		}
    }
