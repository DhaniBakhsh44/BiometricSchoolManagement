/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import DBManager.DatabaseManager;
import beans.CourseBean;
import beans.ExamListBean;
import beans.MarksheetBean;
import beans.SchemeBean;
import beans.StudentRegistrationBean;
import beans.SubjectResultBean;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author dhani
 */
public class Document extends Component implements Printable{
        
    StudentRegistrationBean stdBean;
	Document( StudentRegistrationBean stdBean){
            this.stdBean=stdBean;
            
        }
    int markseet_id=0;
        
	
	public int print(Graphics g, PageFormat pageFormat, int page) {
		try{
                    Image image=new ImageIcon("a.jpg").getImage();
                    Graphics2D g2d = (Graphics2D) g;
			// g2d.translate(pageFormat.getImageableX (), pageFormat.getImageableY ());
                    g2d.setPaint(Color.black);
			
                    int w = image.getWidth(null);
                    int h = image.getHeight(null);
			
                int x=(int)pageFormat.getImageableX();
                int y=(int)pageFormat.getImageableY();

                 int width=(int)pageFormat.getImageableWidth();
                 int height=(int)pageFormat.getImageableHeight();
			
                g.drawImage(image,2740, 20,3508, 620, 0, 0, 3508, 2481, null);
			
                Font font = new Font ("Arial", Font.PLAIN,20);
		g2d.setFont(font);
            
                int y1=20;
                int yShift = 100;
                int headerRectHeight=15;
                int headerRectHeighta=40;
            
//******************HEADER INFORMATION******************           
            g2d.setFont(new Font("Arial Black",Font.PLAIN,14)); 
            g2d.drawString("BIOMETRIC SCHOOL MANAGEMENT SYSTEM",x+100,y+30);
            g2d.setFont(new Font("Arial Black",Font.PLAIN,14));
            String mark="Marks Certificate";
            g2d.drawString(mark,x+210,y+50);
            g.drawLine(x+211,y+52,x+341,y+52);
            g2d.drawRect(x, y,550,630);

  //**************STUDENTS INFORMATION******** 
          
               markseet_id=stdBean.getEntryTestMarks();  
                //JOptionPane.showMessageDialog(null,"Marksheet id"+markseet_id);
            MarksheetBean markBean=DatabaseManager.getMarksheetByMarsheetId(markseet_id);
            StudentRegistrationBean std=DatabaseManager.getStudentRegistrationByRollNo(markBean.getRollNo());
            ExamListBean examList=DatabaseManager.getExamListByExamList(markBean.getExamList());
            SchemeBean schemeBean=DatabaseManager.getSchemeBySchemeId(markBean.getSchemeId());
            SubjectResultBean subjectResultBean = DatabaseManager.getSubjectInf(markseet_id);// 
           
           
            
            
            g2d.setFont(new Font("Times New Roman",Font.BOLD,12));
            g2d.drawString("NAME: "+std.getStudentName(),x+5,y+150); 
            g2d.drawString("ROLL NO: "+std.getRollNo(),x+440,y+150);
            g2d.drawString("EXAM YEAR: "+examList.getSlYear(),x+440,y+165);
            g2d.drawString("CLASS: "+schemeBean.getSchemeName(),x+440,y+180);
            g2d.drawString("FATHER NAME: "+std.getFatherName(),x+5,y+165);
            g2d.drawString("SURNAME: "+std.getSurname(),x+5,y+180);
            
          
// **********************SUBJECT INFORMATION******************
            g2d.setFont(new Font("Times New Roman",Font.BOLD,12));
              g2d.drawRect(x+5, y+190,540,15);
              g2d.drawString("C.NO",x+6,y+202);
              g2d.drawString("SUBJECTS",x+70,y+202);
              g2d.setFont(new Font("Times New Roman",Font.BOLD,8));
              g2d.drawString("MAX.",x+300,y+197);
              g2d.drawString("MARKS.",x+300,y+203);
              g2d.drawString("MIN.",x+400,y+197);
              g2d.drawString("MARKS.",x+400,y+203);
              g2d.drawString("OBT.",x+500,y+197);
              g2d.drawString("MARKS.",x+500,y+203);
              
// ***************SUBJECT BOX**********************
           g2d.drawRect(x+5,y+190,540,400);   
            Vector v=DatabaseManager.getSubjectResultByMarksheetId(markseet_id);
             
                int inc_SUBJECT_LOCATION=0;
                int total_Marks=0;
                int obtain_Marks=0;
              for(int i=0; i<v.size(); i++){
             SubjectResultBean subBean=(SubjectResultBean)v.elementAt(i);
             CourseBean courseBean=DatabaseManager.getCourseByCourseNo(subBean.getCourseNo());
             total_Marks+=subBean.getMaxMarks();
             obtain_Marks+=subBean.getObtainedMarks();
//              JOptionPane.showMessageDialog(null,"Course No="+subBean.getCourseNo()+"\n Suject "+courseBean.getCourseTitle()
//              +"\n Obtain Mark"+subBean.getObtainedMarks()
//              );
//              
             g2d.setFont(new Font("Times New Roman",Font.PLAIN,12));
             g2d.drawString(""+subBean.getCourseNo(),x+12,(y+220+inc_SUBJECT_LOCATION));
             g2d.drawString(courseBean.getCourseTitle(),x+72,(y+220+inc_SUBJECT_LOCATION));
             g2d.drawString(""+subBean.getMaxMarks(),x+302,(y+220+inc_SUBJECT_LOCATION));
             g2d.drawString(""+subBean.getMinMarks(),x+402,(y+220+inc_SUBJECT_LOCATION));
             g2d.drawString(""+subBean.getObtainedMarks(),x+502,(y+220+inc_SUBJECT_LOCATION));
             inc_SUBJECT_LOCATION+=20;             
              }
              g2d.setFont(new Font("Times New Roman",Font.PLAIN,8));
              int percentage=(obtain_Marks*100)/total_Marks;
              g2d.drawRect(x+5,y+600,540,15);
              g2d.drawString("MARKS OBTAIN:"+subjectResultBean.getObtainedMarks()+"/"+subjectResultBean.getMaxMarks(),x+10,y+610);
              g2d.drawString("PERCENTAGE: "+subjectResultBean.getPercentage()+".0",x+150,y+610);
             
              int per=subjectResultBean.getPercentage();
              String grade="",result="";
              if(per>=33 && per<50)
                  grade="D";
              if(per>=50 && per<60)
                  grade="C";
               if(per>=60 && per<80)
                   grade="B";
               if(per>=80 && per<=100)
                   grade="A";
               if(per>=33)
                   result="PASS";
               else
                   result="FAIL";
              g2d.drawString("GRADE: "+grade,x+310,y+610);
              g2d.drawString("RESULT: "+result,x+450,y+610);
              
              
		}catch(Exception ee){
                    ee.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Error: "+ee.getMessage());
		}
		//b=false;
		return this.PAGE_EXISTS;
	}//end method
}