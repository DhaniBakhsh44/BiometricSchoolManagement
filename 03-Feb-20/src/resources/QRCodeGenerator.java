package resources;

/**
 * Title:        Bank Challan Printing Service
 * Description:
 * Copyright:    Copyright (c) 2016
 * Company:      Examinations Wing, University of Sindh, Jamshoro
 * @author Vivekanand, Priyanka & Sachal Joyo
 * @version 1.0
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Hashtable;

//import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




public class QRCodeGenerator {
    public static BufferedImage getQRCode(String qrCode) {
			try{

            String myCodeText =qrCode;

            int size = 90;

            Hashtable hintMap = new Hashtable();//<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.black);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

//            String filePath ="QR-code.jpg";
//            String fileType = "jpg";
//            File myFile = new File(filePath);
//            ImageIO.write(image, fileType, myFile);

            System.out.println("\n\nYou have successfully created QR Code.");
            return image;

		}catch(Exception e){
		 e.printStackTrace();
		}
		return null;

    }//END METHOD
}//END CLASS



/*
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

    public static BufferedImage getQRCode(String rollNo){
        String myCodeText = rollNo;
        int size = 60;//100;
    try{
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
        int CrunchifyWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,BufferedImage.TYPE_INT_RGB);

        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
        graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }//end inner for
            }//end outer for

  //          ImageIO.write(image, fileType, myFile);
//            System.out.println("\n\nYou have successfully created QR Code. at: "+filePath);
            System.out.println("\n\nYou have successfully created QR Code.");
            return image;
       }catch(Exception e){
           e.printStackTrace();
           javax.swing.JOptionPane.showMessageDialog(null,"QR Code Generater Error: "+e);
       }
       return null;
           }//end method

}//end class


*/