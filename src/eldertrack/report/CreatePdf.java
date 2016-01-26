package eldertrack.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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

public class CreatePdf{
	Date dNow = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("MMMM yyyy");
    {
		try{
			Document document = new Document();
	
			PdfWriter.getInstance(document,
					new FileOutputStream(ft.format(dNow)+" Report.pdf"));

			document.open();
  
			Paragraph title1 = new Paragraph("Report for Name for " +ft.format(dNow), 
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
  
			PdfPTable t1 = new PdfPTable(5);
			t1.setWidthPercentage(100);
			t1.setSpacingBefore(25); //above table
			t1.setSpacingAfter(25); //below table

			PdfPCell c11 = new PdfPCell(new Phrase("Date",
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
			PdfPCell c18 = new PdfPCell(new Phrase("Comments",
					FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
  			
			//Date
			t1.addCell(c11);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");	    
			t1.addCell(" ");
  	
			//Temp
			t1.addCell(c12);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
  	
			//BP
			t1.addCell(c13);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
  	
			//Heart rate
			t1.addCell(c14);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			
			//Sugar level
			t1.addCell(c15);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
  	
			//Eye
			t1.addCell(c16);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			
			//ear
			t1.addCell(c17);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
  	
			//comments
			t1.addCell(c18);
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
			t1.addCell(" ");
  
			section1.add(t1);
	
//////////////////////
	
			someSectionText = new Paragraph("Average Results: ", 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
			section1.add(someSectionText);
	
			PdfPTable t2 = new PdfPTable(2);
			t2.setWidthPercentage(100);
			t2.setSpacingBefore(25); //above table
			t2.setSpacingAfter(25); //below table
	
			t2.addCell(c12);
			t2.addCell(" ");
			
			t2.addCell(c13);
			t2.addCell(" ");

			t2.addCell(c14);
			t2.addCell(" ");

			t2.addCell(c15);
			t2.addCell(" ");
	
			section1.add(t2);
	
////////////////////
  
			someSectionText = new Paragraph("Additional Comments: ", 
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));

			section1.add(someSectionText);
	
			document.add(section1);
  
			document.close();
			}catch (DocumentException e) {
				e.printStackTrace();
				} 
		catch (FileNotFoundException e2) {
			e2.printStackTrace();
			}
		}
	}