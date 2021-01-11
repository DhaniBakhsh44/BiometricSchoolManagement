/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author dhani
 */
public class IDCard extends Component implements Printable{
    
    
    	
	beans.StudentRegistrationBean stdBean;
	public IDCard(beans.StudentRegistrationBean stdBean){
            this.stdBean=stdBean;
		
	}
	
	public int print(Graphics g, PageFormat pageFormat, int page) {
	
		try{
			
			int x=(int)pageFormat.getImageableX();
			int y=(int)pageFormat.getImageableY();

			int width=(int)pageFormat.getImageableWidth();
			int height=(int)pageFormat.getImageableHeight();
			
			ImageIcon ic=new ImageIcon(new java.io.File("img/card.jpg").getAbsolutePath());
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(Color.black);
			
			int w = ic.getIconWidth();
			int h = ic.getIconHeight();
			Image image=ic.getImage();
			g.drawImage(image,0, -50,1140, 1250, 0, 0, 1024, 1320, null);
			
			Font font = new Font ("Times new roman", Font.ITALIC, 19);
			g2d.setFont(font);
			
			int a=180,b=150;
			g2d.drawString(stdBean.getStudentName(),a,b);
			g2d.drawString(stdBean.getRollNo()+"",520,120);
			b+=55;
			g2d.drawString(stdBean.getRollNo()+"",a,b);
			b+=55;
			Date d=new Date();
			g2d.drawString(d.getDate()+"-"+d.getMonth()+"-2021",a,b);
                        
   //****************** ID CARD BACK SIDE CODE************************                     
			
			font = new Font ("Consolas", Font.PLAIN, 19);
			b=583;a=18;
			g2d.drawString(stdBean.getFatherName()+"".toUpperCase(),a,b);
			b+=35;;
			g2d.drawString(stdBean.getSurname()+"".toUpperCase(),a,b);
			b+=35;;
			g2d.drawString("B+"+"",a,b);
			g2d.drawString(stdBean.getMobileNo()+"".toUpperCase(),270,b);
			b+=35+2;;
			font = new Font ("Times new roman", Font.ITALIC, 14);
			g2d.drawString(stdBean.getAddress()+"".toUpperCase(),a,b);
			
			font = new Font ("Times new roman", Font.BOLD, 22);
			g2d.setFont(font);
			
			g2d.setColor(Color.RED);
			
//			a=35;b=760;
//			String cn[]=getCnicCh(stdBean.getMobileNo());
//			for(int i=0,j=0;i<cn.length+2;i++){
//				if(i==6 || i==cn.length){
//					a+=40;
//				}else{
//					g2d.drawString(cn[j++]+"",a,b);
//					a+=40;
//				}
//			}
			
			BufferedImage qr=QRCodeGenerator.getQRCode(""+stdBean.getRollNo());
			ImageIcon qrCode=new ImageIcon(qr);
			g.drawImage(qrCode.getImage(),476,467,94,76,null);
			
		}catch(Exception ee){
			ee.printStackTrace();
			
		}
		
		return this.PAGE_EXISTS;
	}//end method
	
	String[] getCnicCh(String cnic){
		String d="",sym="";
		for(int i=0;i<cnic.length();i++){
			if(cnic.charAt(i)!='-'){
				d+=sym+cnic.charAt(i)+"";
				sym=":";
			};
		}
			
		return d.split(":");
	}
	
    
}
