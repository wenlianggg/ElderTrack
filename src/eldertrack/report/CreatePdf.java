package eldertrack.report;

//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.mysql.jdbc.Blob;

import eldertrack.db.SQLObject;

public class CreatePdf{
	private static String name;
	private static String date;
	@SuppressWarnings("unused")
	private static String checktime;
	private static double tempM;
	private static double bloodM;
	private static double heartM;
	private static double sugarM;
	private static boolean eyeM;
	private static boolean earM;
	private static String eyeMString;
	private static String earMString;
	private static double tempA;
	private static double bloodA;
	private static double heartA;
	private static double sugarA;
	private static boolean eyeA;
	private static boolean earA;
	private static String earAString;
	private static String eyeAString;
	private static double tempN;
	private static double bloodN;
	private static double heartN;
	private static double sugarN;
	private static boolean eyeN;
	private static boolean earN;
	private static String eyeNString;
	private static String earNString;
	private static double tempDTot;
	private static double bloodDTot;
	private static double heartDTot;
	private static double sugarDTot;
	private static double tempMTot;
	private static double bloodMTot;
	private static double heartMTot;
	private static double sugarMTot;
	private static PdfPCell c12;
	private static PdfPCell c13;
	private static PdfPCell c14;
	private static PdfPCell c15;
	private static double tempMAvr;
	private static double bloodMAvr;
	private static double heartMAvr;
	private static double sugarMAvr;
	private static boolean eyeD;
	private static String eyeDString;
	private static boolean earD;
	private static String earDString;
	private static boolean eyeMAvr;
	private static PdfPCell c16;
	private static PdfPCell c17;
	private static boolean earMAvr;
	private static String eyeMAvrString;
	private static String earMAvrString;
	
	static Date dNow = new Date();
    static SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");

    static Calendar calendar = Calendar.getInstance();
    static java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
    
	static SQLObject so = new SQLObject();
    static ResultSet rsTmp = so.getResultSet("SELECT * FROM et_reportTemp ORDER BY name, date, checktime");
    static ResultSet rsAvr=so.getResultSet("SELECT * FROM et_reportAvr WHERE name=?");
    static PreparedStatement statementUpdateAvr = so.getPreparedStatementWithKey
    		("INSERT INTO et_reportAvr (name,id,date,tempMAvr,bloodMAvr,heartMAvr,sugarMAvr,eyeMAvr,earMAvr) values(?,?,?,?,?,?,?,?,?)");
    ResultSet rsReport = so.getResultSet("SELECT * FROM et_report");
    static PreparedStatement statementUpdateReport = so.getPreparedStatementWithKey
    		("INSERT INTO et_report (name,id,timestamp,report) values(?,?,?,?)");
    
	
/////////////////////////////////
    public static void CreateReport (int id) throws SQLException, IOException, ClassNotFoundException {
    	
		try{
			Document document = new Document();
			PdfWriter.getInstance(document,	
					new FileOutputStream(ft.format(dNow)+" Report.pdf"));
			document.open();
 
			Paragraph title1 = new Paragraph("Report for "+name +" for " +ft.format(dNow), 
					FontFactory.getFont(FontFactory.HELVETICA, 
							24, Font.BOLD, new CMYKColor(255, 230, 0,0)));
 		   
			Chapter chapter1 = new Chapter(title1, 1);
			chapter1.setNumberDepth(0);
 
			document.add(chapter1);
			Paragraph title11 = new Paragraph("Medical History", 
					FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
			Section section1 = chapter1.addSection(title11);
        
			Paragraph someSectionText = new Paragraph("Weekly Checkup: ", 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
			section1.add(someSectionText);
			
			while (rsTmp.next()){
				rsTmp.absolute(1);
		    	name=rsTmp.getString("name");
		    	date=rsTmp.getString("date");
		    	checktime=rsTmp.getString("checktime");
		    	tempA=rsTmp.getDouble("temp");
		    	bloodA=rsTmp.getDouble("blood");
		    	heartA=rsTmp.getDouble("heart");
		    	sugarA=rsTmp.getDouble("sugar");
		    	eyeA=rsTmp.getBoolean("eye");
		    	earA=rsTmp.getBoolean("ear");
		    	
		    	tempDTot+=tempA;
		    	bloodDTot+=bloodA;
		    	heartDTot+=heartA;
		    	sugarDTot+=sugarA;
		    	
		    	if (eyeA==true)
		    		eyeAString = "Yes";
		    	else
		    		eyeAString="No";
							
		    	if (earA==true)
		    		earAString = "Yes";
		    	else
		    		earAString="No";
		///////////////////////////			
				rsTmp.absolute(2);
				tempM=rsTmp.getDouble("temp");
				bloodM=rsTmp.getDouble("blood");
				heartM=rsTmp.getDouble("heart");
				sugarM=rsTmp.getDouble("sugar");
				eyeM=rsTmp.getBoolean("eye");
				earM=rsTmp.getBoolean("ear");
				if (eyeM==true)
					eyeMString = "Yes";
				else
					eyeMString="No";
					
				if (earM==true)
					earMString = "Yes";
				else
					earMString="No";
				
				tempDTot+=tempM;
		    	bloodDTot+=bloodM;
		    	heartDTot+=heartM;
		    	sugarDTot+=sugarM;
		///////////////////////////////		
				rsTmp.absolute(3);
				tempN=rsTmp.getDouble("temp");
				bloodN=rsTmp.getDouble("blood");
				heartN=rsTmp.getDouble("heart");
				sugarN=rsTmp.getDouble("sugar");
				eyeN=rsTmp.getBoolean("eye");
				earN=rsTmp.getBoolean("ear");
				if (eyeN==true)
					eyeNString = "Yes";
				else
					eyeNString="No";
				
				if (earN==true)
					earNString = "Yes";
				else
					earNString="No";	
				
				tempDTot+=tempN;
		    	bloodDTot+=bloodN;
		    	heartDTot+=heartN;
		    	sugarDTot+=sugarN;
		    	
		    	if (eyeA==true || eyeM==true || eyeN==true){
		    		eyeD=true;
		    		eyeDString="Yes";
		    	}
		    	else {
		    		eyeD=false;
		    		eyeDString="No";
		    	}
		    	
		    	if (earA==true || earM==true || earN==true){
		    		earD=true;
		    		earDString="Yes";
		    	}
		    	else {
		    		earD=false;
		    		earDString="No";
		    	}
		    	
		///////////////////////////
					
		    	someSectionText = new Paragraph(date, 
		    			FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
		    	section1.add(someSectionText);
 
		    	PdfPTable t1 = new PdfPTable(5);
		    	t1.setWidthPercentage(100);
		    	t1.setSpacingBefore(25); //above table
		    	t1.setSpacingAfter(25); //below table
			
		    	PdfPCell c11 = new PdfPCell(new Phrase("Time of Day",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		    	PdfPCell c12 = new PdfPCell(new Phrase("Temperature (deg Celcius)",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));  
		    	PdfPCell c13 = new PdfPCell(new Phrase("Blood Pressure",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		    	PdfPCell c14 = new PdfPCell(new Phrase("Heart Rate",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		    	PdfPCell c15 = new PdfPCell(new Phrase("Sugar Level",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		    	PdfPCell c16 = new PdfPCell(new Phrase("Eye Infection",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		    	PdfPCell c17 = new PdfPCell(new Phrase("Ear Infection",
		    			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
 			
		    	//Time of day
		    	t1.addCell(c11);
		    	t1.addCell("Morning");
		    	t1.addCell("Afternoon");
		    	t1.addCell("Night");	    
		    	t1.addCell("Average");
			
		    	//Temp
		    	t1.addCell(c12);
		    	t1.addCell(""+tempM);
		    	t1.addCell(""+tempA);
		    	t1.addCell(""+tempN);
		    	t1.addCell(""+(tempDTot/3));
			
		    	//BP
		    	t1.addCell(c13);
		    	t1.addCell(""+bloodM);
		    	t1.addCell(""+bloodA);
		    	t1.addCell(""+bloodN);
		    	t1.addCell(""+(bloodDTot/3));
			
		    	//Heart rate
		    	t1.addCell(c14);
		    	t1.addCell(""+heartM);
		    	t1.addCell(""+heartA);
		    	t1.addCell(""+heartN);
		    	t1.addCell(""+(heartDTot/3));
			
		    	//Sugar level
		    	t1.addCell(c15);
		    	t1.addCell(""+sugarM);
		    	t1.addCell(""+sugarA);
		    	t1.addCell(""+sugarN);
		    	t1.addCell(""+(sugarDTot/3));
			
		    	//Eye
		    	t1.addCell(c16);
		    	t1.addCell(""+eyeMString);
		    	t1.addCell(""+eyeAString);
		    	t1.addCell(""+eyeNString);
		    	t1.addCell(""+eyeDString);
		    	
		    	//ear
		    	t1.addCell(c17);
		    	t1.addCell(""+earMString);
		    	t1.addCell(""+earAString);
		    	t1.addCell(""+earNString);
		    	t1.addCell(""+earDString);
			
		    	section1.add(t1);
			
		    	tempMTot+=tempDTot;
		    	bloodMTot+=bloodDTot;
		    	heartMTot+=heartDTot;
		    	sugarMTot+=sugarDTot;
		    	
		    	if (eyeD==true){
		    		eyeMAvr=true;
		    		eyeMAvrString="Yes";
		    	}
		    	else{
		    		eyeMAvr=false;
		    		eyeMAvrString="No";
		    	}
		    	if (earD==true){
		    		earMAvr=true;
		    		earMAvrString="Yes";
		    	}
		    	else{
		    		earMAvr=false;
		    		earMAvrString="Yes";
		    	}
			}

/////////////////////
			
			tempMAvr=tempMTot/4;
			bloodMAvr=bloodMTot/4;
			heartMAvr=heartMTot/4;
			sugarMAvr=sugarMTot/4;
	
			someSectionText = new Paragraph("Average per Month: ", 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
			section1.add(someSectionText);
	
			PdfPTable t2 = new PdfPTable(2);
			t2.setWidthPercentage(100);
			t2.setSpacingBefore(25); //above table
			t2.setSpacingAfter(25); //below table
	
			t2.addCell(c12);
			t2.addCell(""+tempMAvr);
			
			t2.addCell(c13);
			t2.addCell(""+bloodMAvr);

			t2.addCell(c14);
			t2.addCell(""+heartMAvr);

			t2.addCell(c15);
			t2.addCell(""+sugarMAvr);
			
			t2.addCell(c16);
			t2.addCell(""+eyeMAvrString);
			
			t2.addCell(c17);
			t2.addCell(""+earMAvrString);
	
			section1.add(t2);
			
			statementUpdateAvr.setString(1, name);
			statementUpdateAvr.setInt(2, id);
			statementUpdateAvr.setString(3, date);
			statementUpdateAvr.setDouble(4,tempMAvr);
			statementUpdateAvr.setDouble(5, bloodMAvr);
			statementUpdateAvr.setDouble(6, heartMAvr);
			statementUpdateAvr.setDouble(7, sugarMAvr);
			statementUpdateAvr.setBoolean(8, eyeMAvr);
			statementUpdateAvr.setBoolean(9, earMAvr);
			statementUpdateAvr.executeUpdate();
			
////////////////////
  
			someSectionText = new Paragraph("Additional Comments: ", 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));

			section1.add(someSectionText);
			
			someSectionText = new Paragraph("" +rsAvr, 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
	
			document.add(section1);
  
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
    	}
		
		
/*
		PreparedStatement statementAddReport = so.getPreparedStatementWithKey("UPDATE et_reportAvr SET report = ? WHERE name=? id = ?");
		statementAddReport.setBlob(1, reportBlob);;
		statementAddReport.setString(2, name);
		statementAddReport.setInt(3, id);
		
		String filename = ft.format(dNow)+" Report.pdf";
		FileInputStream fIS = new FileInputStream((FileOutputStream)ft.format(dNow)+" Report.pdf");

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// do any output onto outStream you need
		ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
		// you can read back the output with inStream now
		

		pstUpdate.setBinaryStream(1, fIS, (int) ft.format(dNow)+" Report.pdf".length());
		                              
		pstUpdate.setString(2, rs.getString("id")); 

		pstUpdate.execute(); 
		
*/		
		
    }
    
   public static void main(String[] args){
	   try {
			ResultSet rsLoadID = so.getResultSet("SELECT id, name, date FROM et_reportTemp ORDER BY name,date");
						
			while(rsLoadID.next()) {
				int id = rsLoadID.getInt("id");
				CreateReport(id);
				
				statementUpdateReport.setString(1, name);
				statementUpdateReport.setInt(2, id);
			    statementUpdateReport.setTimestamp(3,timestamp);
		//	    statementUpdateReport.setBlob(4, reportBlob);
			    statementUpdateReport.executeUpdate();
			}
				
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();	
			}
		
   }
}