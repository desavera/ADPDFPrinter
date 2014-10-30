package antena.printer;

import java.io.*; 
import java.util.*; 
import java.awt.image.BufferedImage;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.*;
import org.apache.pdfbox.exceptions.*;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;


public class ADPDFCreator {



  public static ByteArrayOutputStream createPDF(InputStream inputFront,InputStream inputBack,ADFont fontID,Vector<String> textList) throws IOException, COSVisitorException {

	 PDDocument document;
	 PDPage page;
	 PDFont font;
	 PDPageContentStream contentStream;
	 PDJpeg front;
	 PDJpeg back;

	 BufferedImage buffFront;
	 BufferedImage resizedFront;
       	 BufferedImage buffBack;
       	 BufferedImage resizedBack;
	 ByteArrayOutputStream output = new ByteArrayOutputStream(); 

 	 // Creating Document
  	 document = new PDDocument();

       	 page = new PDPage();

       	 // Adding page to document
       	 document.addPage(page); 

       	 // Adding FONT to document TODO use the Font enum...
       	 font = PDType1Font.HELVETICA;           
	
	 if (inputFront != null) { 

		buffFront = ImageIO.read(inputFront);
       	 	resizedFront = Scalr.resize(buffFront, 460);
       	 	front = new PDJpeg(document, resizedFront);
	 }

	 if (inputBack != null) {

       	 	buffBack = ImageIO.read(inputBack);
       	 	resizedBack = Scalr.resize(buffBack, 460); 
       	 	back = new PDJpeg(document, resizedBack);
	 }


       	 // Next we start a new content stream which will "hold" the to be created content.
       	 contentStream = new PDPageContentStream(document, page);                

       	 // Let's define the content stream
       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(10, 770);
       	 contentStream.drawString("Amount: $1.00");
       	 contentStream.endText();

       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(200, 770);
       	 contentStream.drawString("Sequence Number: 123456789");
       	 contentStream.endText();

       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(10, 760);
       	 contentStream.drawString("Account: 123456789");
       	 contentStream.endText();

       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(200, 760);
       	 contentStream.drawString("Captura Date: 04/25/2011");
       	 contentStream.endText();

       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(10, 750);
       	 contentStream.drawString("Bank Number: 123456789");
       	 contentStream.endText();

       	 contentStream.beginText();
       	 contentStream.setFont(font, 8);
       	 contentStream.moveTextPositionByAmount(200, 750);
       	 contentStream.drawString("Check Number: 123456789");
       	 contentStream.endText();            

       	 // Let's close the content stream       
       	 contentStream.close();

    	 // Finally Let's save the PDF
    	 document.save(output);
    	 document.close();

    	 return output;
    }

}
