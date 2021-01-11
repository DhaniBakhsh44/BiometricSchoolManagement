/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import jFrames.StudentClassFrame;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhani
 */
public class MarksheetPrintJob extends Thread {
    
     private List<beans.StudentRegistrationBean> stdList;
    
    public MarksheetPrintJob(List<beans.StudentRegistrationBean> stdList){
        this.stdList=stdList;
        start();
       
    }
    
    public void run(){
        Book book =new Book();
        PageFormat pageFormat=getPageFormat();
        for(beans.StudentRegistrationBean std: stdList)
            book.append(new Document(std), pageFormat);

        PrinterJob printJob=PrinterJob.getPrinterJob();
        printJob.setPageable(book);

        if(printJob.printDialog()) 
            try {
                printJob.print();
            } catch (PrinterException ex) {
                Logger.getLogger(StudentClassFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public interface PageSetupInterface {
	
	double PIXEL_PER_INCH=300;
	
	double PAPER_WIDTH=8.27*PIXEL_PER_INCH;
	double PAPER_HEIGHT=11.69*PIXEL_PER_INCH;

	double LEFT_MARGIN=0.1*PIXEL_PER_INCH;
	double RIGHT_MARGIN=0.1*PIXEL_PER_INCH;

	double TOP_MARGIN=0.1*PIXEL_PER_INCH;
	double BOTTOM_MARGIN=0.1*PIXEL_PER_INCH;

}//End of class

private static PageFormat getPageFormat(){
		Paper paper=new Paper();
		
		paper.setSize(PageSetupInterface.PAPER_WIDTH,PageSetupInterface.PAPER_HEIGHT);

		paper.setImageableArea(PageSetupInterface.LEFT_MARGIN,PageSetupInterface.TOP_MARGIN,
							paper.getWidth()-(PageSetupInterface.LEFT_MARGIN + PageSetupInterface.RIGHT_MARGIN),
							paper.getHeight()-(PageSetupInterface.TOP_MARGIN + PageSetupInterface.BOTTOM_MARGIN));
		
		PageFormat pageFormat = new PageFormat ();
		pageFormat.setPaper(paper);

		// pageFormat.setOrientation(PageFormat.LANDSCAPE);
		pageFormat.setOrientation (PageFormat.PORTRAIT);

		return pageFormat;
	}//End of PageFormat method

    
}
