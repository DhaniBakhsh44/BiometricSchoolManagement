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
import javax.swing.JOptionPane;


/**
 *
 * @author dhani
 */
public class IDCardPrintJob extends Thread{
    private List<beans.StudentRegistrationBean> list;
    
    public IDCardPrintJob(List<beans.StudentRegistrationBean> list){
        this.list=list;
        start();
    }
    
    public void run(){
        Book book =new Book();
        PageFormat pageFormat=getPageFormat();
        for(beans.StudentRegistrationBean std: list)
            book.append(new IDCard(std), pageFormat);


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
	
	double PAPER_WIDTH=3.380*PIXEL_PER_INCH;		
	double PAPER_HEIGHT=2.200*2*PIXEL_PER_INCH;

	double LEFT_MARGIN=0.0;
	double RIGHT_MARGIN=0.0;

	double TOP_MARGIN=0.0;
	double BOTTOM_MARGIN=0.0;

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
