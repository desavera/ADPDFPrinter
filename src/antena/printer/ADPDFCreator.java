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



  public static ByteArrayOutputStream createPDF(InputStream header,ADFont fontID,Vector<String> textList) throws IOException, COSVisitorException {

	 PDDocument document;
	 PDPage page;
	 PDFont font;
	 PDPageContentStream contentStream;
	 PDJpeg front;

	 BufferedImage buffFront;
	 BufferedImage resizedFront;


	 ByteArrayOutputStream output = new ByteArrayOutputStream(); 

 	 // Creating Document
  	 document = new PDDocument();

       	 page = new PDPage();

       	 // Adding page to document
       	 document.addPage(page); 

       	 // Next we start a new content stream which will "hold" the to be created content.
       	 contentStream = new PDPageContentStream(document, page);                

       	 // Adding FONT to document TODO use the Font enum...
       	 font = PDType1Font.HELVETICA;           
	
	 if (header != null) { 

		buffFront = ImageIO.read(header);
       	 	//resizedFront = Scalr.resize(buffFront, 460);
       	 	//front = new PDJpeg(document, resizedFront);
       	 	front = new PDJpeg(document, buffFront);
	 	contentStream.drawImage(front, 10, 700);
	 }

	 int xSTART = 10;
	 int ySTART = 570;
	 int X_DELTA = 0;
	 int Y_DELTA = 20;

	 for (int i=0;i < textList.size();i++) {

       	 	// Let's define the content stream
       	 	contentStream.beginText();
       	 	contentStream.setFont(font, 8);
       	 	contentStream.moveTextPositionByAmount(xSTART + i*X_DELTA,ySTART - i*Y_DELTA);
       	 	contentStream.drawString(textList.elementAt(i));
       	 	contentStream.endText();
	 }

       	 // Let's close the content stream       
       	 contentStream.close();

    	 // Finally Let's save the PDF
    	 document.save(output);
    	 document.close();

    	 return output;
    }

}
