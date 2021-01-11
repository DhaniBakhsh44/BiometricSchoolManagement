package DBManager;

import beans.ExamListDetailBean;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
	Title: 		DatabaseManager.java
	Description:	Auto-Generated Controller class for Model And View
	Contact:	0300-0090822
	Email:		younissabbasi@gmail.com	
	@Author:	Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class DatabaseManager{			
	static java.sql.Connection con;
        static java.sql.Connection mySqlCon;

	/****************************************************** Connection Block *********************************************************/		
	static{
		try{       // MySQL Connection...
			Class.forName("com.mysql.jdbc.Driver");
			mySqlCon = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","");
                         
                        //Access Connection.... 
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = java.sql.DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\dhani\\Documents\\NetBeansProjects\\BiometricSchoolManagementSystem\\03-Feb-20\\school.accdb","","");
                        System.out.println("Connection Established!!!");
		
                }catch(java.sql.SQLException sqle){
			sqle.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"Connection Not Established due to "+sqle.getMessage()+"");
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"Connection Not Established due to "+cnfe.getMessage()+"");
		}	}//End of conneciton static block		
 	
	/*********************************************************************************************************************************/	
    	
	/******************************************************** Add Methods ************************************************************/		  
  


public static void getData(){  
  java.sql.Statement st=null;
  java.sql.ResultSet rs=null;
    try{
        st=mySqlCon.createStatement();
        String query="Select * from student";
        st.execute(query);
        rs=st.getResultSet();
        
        while(rs.next()){
           System.out.println("Dhani"+rs.getInt("roll_no"));
           System.out.println(rs.getString("student_name"));
           System.out.println(rs.getString("father_name"));
        }
        
  }catch(Exception e){
        e.printStackTrace();
  }
}



//  public static void main(String args[]){
//    try{
//     // pasteSchemes();
//      java.util.Vector<beans.SchemeBean> v=getSchemes();
//      for(int i=0;i<v.size();i++)
//        System.out.println(v.elementAt(i).getSchemeName());
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//  }
    
  public static int pasteSchemes()throws java.sql.SQLException{
    java.sql.Statement st=null;

		try{
			st = con.createStatement();
			String query = "INSERT INTO scheme(class_id,scheme_name,status,last_update) select c.class_id,c.class_name,1,'08-Feb-20' from classes c";

			int row=st.executeUpdate(query);
      
			return row;
		}finally{
			st.close();
		}
  }//End of pasteSchemes method
  
  public static int addAttendance(beans.AttendanceBean attendanceBean)throws java.sql.SQLException{
  	java.sql.Statement st=null;
        java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO attendance(remarks, status, term"
				+", class_id, attendance_date,scheme_id) VALUES('"+attendanceBean.getRemarks()+"', "+attendanceBean.getStatus()+", "+attendanceBean.getTerm()+""
				+", "+attendanceBean.getClassId()+", '"+attendanceBean.getAttendanceDate()+"',"+attendanceBean.getSchemId()+")";
                        mySqlSt.executeUpdate(query);
                        return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addAttendance(AttendanceBean attendanceBean) method

	public static int addAttendanceDetail(beans.AttendanceDetailBean attendanceDetailBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
                    mySqlSt=mySqlCon.createStatement();
			st = con.createStatement();
			String query = "INSERT INTO attendance_detail(class_id, roll_no, status"
				+", remarks, term, attendance_date,scheme_id) VALUES("+attendanceDetailBean.getClassId()+", "+attendanceDetailBean.getRollNo()+", "+attendanceDetailBean.getStatus()+""
				+", '"+attendanceDetailBean.getRemarks()+"', "+attendanceDetailBean.getTerm()+", '"+attendanceDetailBean.getAttendanceDate()+"' ,"+attendanceDetailBean.getSchemeId()+")";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addAttendanceDetail(AttendanceDetailBean attendanceDetailBean) method

	public static int addClasses(beans.ClassesBean classesBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;
                java.sql.ResultSet rs=null;
		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
                        
			String query = "INSERT INTO classes(class_name, status, remarks"
				+", class_no, section) VALUES('"+classesBean.getClassName()+"', '"+classesBean.getStatus()+"', '"+classesBean.getRemarks()+"'"
				+", '"+classesBean.getClassNo()+"', '"+classesBean.getSection()+"')";
                               
                                //Acesse Execution
                         int rows=st.executeUpdate(query);
                         
                         // get Max Class id for MySQL Database
                         String maxClassId="SELECT MAX(class_id)as class_id from classes";
                         st.execute(maxClassId);
                         rs=st.getResultSet();
                         
                         while(rs.next()){
                         int MAX_CLASS_ID=rs.getInt("class_id");
                         query = "INSERT INTO classes(class_id,class_name, status, remarks"
				+", class_no, section) VALUES( "+MAX_CLASS_ID+",'"+classesBean.getClassName()+"', '"+classesBean.getStatus()+"', '"+classesBean.getRemarks()+"'"
				+", '"+classesBean.getClassNo()+"', '"+classesBean.getSection()+"')";
                         ///MySql Execuption
                             mySqlSt.executeUpdate(query); 
                         }
    			return rows;
		}finally{
			st.close();
		}

	}//End of addClasses(ClassesBean classesBean) method

	public static int addCourse(beans.CourseBean courseBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO course(course_title, min_marks, max_marks"
				+", subject_type, status, remarks"
				+", course_keyword, class_id,scheme_id) VALUES('"+courseBean.getCourseTitle()+"', "+courseBean.getMinMarks()+", "+courseBean.getMaxMarks()+""
				+", '"+courseBean.getSubjectType()+"', "+courseBean.getStatus()+", '"+courseBean.getRemarks()+"'"
				+", '"+courseBean.getCourseKeyword()+"', "+courseBean.getClassId()+","+courseBean.getSchemeId()+")";
                                int row=st.executeUpdate(query);
                       
                        //Get Max course_id
                        String maxCourseNo="SELECT MAX(course_no)as course_no from course";
                        st.execute(maxCourseNo);
                        ResultSet rs=st.getResultSet();
                        if(rs.next()){
                        int COURSE_NO=rs.getInt("course_no");
                        
                        query = "INSERT INTO course( course_no,course_title, min_marks, max_marks"
				+", subject_type, status, remarks"
				+", course_keyword, class_id,scheme_id) VALUES( "+COURSE_NO+",'"+courseBean.getCourseTitle()+"', "+courseBean.getMinMarks()+", "+courseBean.getMaxMarks()+""
				+", '"+courseBean.getSubjectType()+"', "+courseBean.getStatus()+", '"+courseBean.getRemarks()+"'"
				+", '"+courseBean.getCourseKeyword()+"', "+courseBean.getClassId()+","+courseBean.getSchemeId()+")";
                                mySqlSt.executeUpdate(query);
                        }
                            
			return row;
                                
		}finally{
			st.close();
		}

	}//End of addCourse(CourseBean courseBean) method

	public static int addExamList(beans.ExamListBean examListBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO exam_list(class_id, term, type"
				+", status, remarks, el_year"
				+", el_month,scheme_id) VALUES("+examListBean.getClassId()+", "+examListBean.getTerm()+", '"+examListBean.getType()+"'"
				+", "+examListBean.getStatus()+", '"+examListBean.getRemarks()+"', "+examListBean.getSlYear()+""
				+", "+examListBean.getSlMonth()+","+examListBean.getSchemeId()+ ")";
                      
			 
                         int rows=st.executeUpdate(query);
    
                    //------Insert DATA INTO MYSQL------------------------------     
                           ResultSet rs=st.executeQuery("select max(exam_list)as exam_list_id from exam_list");
                        
                           if(rs.next()){
                             int EXAM_LIST=rs.getInt("exam_list_id");
                             	query = "INSERT INTO exam_list(exam_list,class_id, term, type"
				+", status, remarks, el_year"
				+", el_month,scheme_id) VALUES("+EXAM_LIST+" ,"+examListBean.getClassId()+", "+examListBean.getTerm()+", '"+examListBean.getType()+"'"
				+", "+examListBean.getStatus()+", '"+examListBean.getRemarks()+"', "+examListBean.getSlYear()+""
				+", "+examListBean.getSlMonth()+","+examListBean.getSchemeId()+ ")";
                                mySqlSt.executeUpdate(query);
                    //----------------------------------------------------------            
                         }
                         return rows;
		}finally{
			st.close();
		}

	}//End of addExamList(ExamListBean examListBean) method

	public static int addExamListDetail(beans.ExamListDetailBean examListDetailBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO exam_list_detail(term, exam_list, roll_no"
				+", status, class_id, remarks,scheme_id) VALUES("+examListDetailBean.getTerm()+", "+examListDetailBean.getExamList()+", "+examListDetailBean.getRollNo()+""
				+", "+examListDetailBean.getStatus()+", "+examListDetailBean.getClassId()+", '"+examListDetailBean.getRemarks()+"',"+examListDetailBean.getSchemeId()+")";
                            mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addExamListDetail(ExamListDetailBean examListDetailBean) method

	public static int addLedgerDetail(beans.LedgerDetailBean ledgerDetailBean)throws java.sql.SQLException{

		java.sql.Statement st=null;

		try{
			st = con.createStatement();
			String query = "INSERT INTO ledger_detail(term, seat_list, roll_no"
				+", status, remarks, class_id"
				+", scheme_id, marks_obtain, total_marks"
				+", rank, grade, date_of_declare) VALUES("+ledgerDetailBean.getTerm()+", "+ledgerDetailBean.getSeatList()+", "+ledgerDetailBean.getRollNo()+""
				+", "+ledgerDetailBean.getStatus()+", '"+ledgerDetailBean.getRemarks()+"', "+ledgerDetailBean.getClassId()+""
				+", "+ledgerDetailBean.getSchemeId()+", "+ledgerDetailBean.getMarksObtain()+", "+ledgerDetailBean.getTotalMarks()+""
				+", '"+ledgerDetailBean.getRank()+"', '"+ledgerDetailBean.getGrade()+"', '"+ledgerDetailBean.getDateOfDeclare()+"')";

			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addLedgerDetail(LedgerDetailBean ledgerDetailBean) method

	public static int addMarksheet(beans.MarksheetBean marksheetBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO marksheet(exam_list, roll_no, seat_no"
				+", total_marks, obtain_marks, percentage"
				+", grade, rank, marks_in_words"
				+", position, total_days, present_days"
				+", status, remarks, class_id"
				+", term, date_of_declaration,scheme_id) VALUES("+marksheetBean.getExamList()+", "+marksheetBean.getRollNo()+", "+marksheetBean.getSeatNo()+""
				+", "+marksheetBean.getTotalMarks()+", "+marksheetBean.getObtainMarks()+", "+marksheetBean.getPercentage()+""
				+", '"+marksheetBean.getGrade()+"', '"+marksheetBean.getRank()+"', '"+marksheetBean.getMarksInWords()+"'"
				+", '"+marksheetBean.getPosition()+"', "+marksheetBean.getTotalDays()+", "+marksheetBean.getPresentDays()+""
				+", "+marksheetBean.getStatus()+", '"+marksheetBean.getRemarks()+"', "+marksheetBean.getClassId()+""
				+", "+marksheetBean.getTerm()+", '"+marksheetBean.getDateOfDeclaration()+"' ,"+marksheetBean.getSchemeId()+")";
                               
                       int rows=st.executeUpdate(query);
                       
                //-------------------DATA INSERT INTO MYSQL DATABASE ------------------------------
                      
                       try{
                       int marksheetId=getRecentMarksheetId();
                        query = "INSERT INTO marksheet(marksheet_id,exam_list, roll_no, seat_no"
				+", total_marks, obtain_marks, percentage"
				+", grade, rank, marks_in_words"
				+", position, total_days, present_days"
				+", status, remarks, class_id"
				+", term, date_of_declaration,scheme_id) VALUES( "+marksheetId+","+marksheetBean.getExamList()+", "+marksheetBean.getRollNo()+", "+marksheetBean.getSeatNo()+""
				+", "+marksheetBean.getTotalMarks()+", "+marksheetBean.getObtainMarks()+", "+marksheetBean.getPercentage()+""
				+", '"+marksheetBean.getGrade()+"', '"+marksheetBean.getRank()+"', '"+marksheetBean.getMarksInWords()+"'"
				+", '"+marksheetBean.getPosition()+"', "+marksheetBean.getTotalDays()+", "+marksheetBean.getPresentDays()+""
				+", "+marksheetBean.getStatus()+", '"+marksheetBean.getRemarks()+"', "+marksheetBean.getClassId()+""
				+", "+marksheetBean.getTerm()+", '"+marksheetBean.getDateOfDeclaration()+"' ,"+marksheetBean.getSchemeId()+")";
                                mySqlSt.executeUpdate(query);
                //--------------------------------------------END--------------------------------------       
                       
                       }catch(Exception e){
                           e.printStackTrace();
                       }
			return rows;
                                
		}finally{
			st.close();
		}

	}//End of addMarksheet(MarksheetBean marksheetBean) method

	public static int addScheme(beans.SchemeBean schemeBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;
                java.sql.ResultSet rs=null;

		try{
                    st = con.createStatement();
                    String query="insert into scheme (class_id,scheme_name,status)values"+"("+schemeBean.getClassId()+",'"+schemeBean.getSchemeName()+"',"+schemeBean.getStatus()+")";
                    int row=st.executeUpdate(query);
                        
                        String maxSchemeId="SELECT MAX(scheme_id) as scheme_id  from scheme";
                        st.execute(maxSchemeId);
                        rs=st.getResultSet();
                        
                     if(rs.next()){
                     int MAX_SCHEME_ID=rs.getInt("scheme_id");
                     query="insert into scheme (scheme_id,class_id,scheme_name,status)values"+"("+MAX_SCHEME_ID+","+schemeBean.getClassId()+",'"+schemeBean.getSchemeName()+"',"+schemeBean.getStatus()+")";
                       mySqlSt=mySqlCon.createStatement();
                      mySqlSt.execute(query);
                     
                     }
                return row;
		}finally{
			st.close();
		}
                

	}//End of addScheme(SchemeBean schemeBean) method

	public static int addStudentClass(beans.StudentClassBean studentClassBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO student_class(remarks, class_id, roll_no"
				+", status,scheme_id) VALUES('"+studentClassBean.getRemarks()+"', "+studentClassBean.getClassId()+", "+studentClassBean.getRollNo()+""
				+", "+studentClassBean.getStatus()+","+studentClassBean.getSchemeId() +")";
                        mySqlSt.executeUpdate(query);

			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addStudentClass(StudentClassBean studentClassBean) method

	public static int addStudentRegistration(beans.StudentRegistrationBean studentRegistrationBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO student(student_name, father_name, surname"
				+", gender, mother_name, date_of_birth"
				+", place_of_birth, guardian_name, guardian_occupation"
				+", phone_no, mobile_no, class_admission"
				+", last_attendent_school, admission_date, admission_fees"
				+", status, remarks, finger_template"
				+", finger_image, profile_image, previous_roll_no"
				+", entry_test_marks, entry_test_remarks, address) VALUES('"+studentRegistrationBean.getStudentName()+"', '"+studentRegistrationBean.getFatherName()+"', '"+studentRegistrationBean.getSurname()+"'"
				+", '"+studentRegistrationBean.getGender()+"', '"+studentRegistrationBean.getMotherName()+"', '"+studentRegistrationBean.getDateOfBirth()+"'"
				+", '"+studentRegistrationBean.getPlaceOfBirth()+"', '"+studentRegistrationBean.getGuardianName()+"', '"+studentRegistrationBean.getGuardianOccupation()+"'"
				+", '"+studentRegistrationBean.getPhoneNo()+"', '"+studentRegistrationBean.getMobileNo()+"', '"+studentRegistrationBean.getClassAdmission()+"'"
				+", '"+studentRegistrationBean.getLastAttendentSchool()+"', '"+studentRegistrationBean.getAdmissionDate()+"', "+studentRegistrationBean.getAdmissionFees()+""
				+", "+studentRegistrationBean.getStatus()+", '"+studentRegistrationBean.getRemarks()+"', '"+studentRegistrationBean.getFingerTemplate()+"'"
				+", '"+studentRegistrationBean.getFingerImage()+"', '"+studentRegistrationBean.getProfileImage()+"', "+studentRegistrationBean.getPreviousRollNo()+""
				+", "+studentRegistrationBean.getEntryTestMarks()+", '"+studentRegistrationBean.getEntryTestRemarks()+"', '"+studentRegistrationBean.getAddress()+"')";
                                    
                        //System.out.println(query);
			int row=st.executeUpdate(query);
                       
                        if(row>0){
                            ResultSet rs=st.executeQuery("select max(roll_no) as max_id from student");
                            if(rs.next()){
                        //------------------BELOW CODE FOR INSERT DATA IN MYSQL DATABASE-------------------------        
                                int ROLL_NO=rs.getInt("max_id");
                                 query = "INSERT INTO student(roll_no,student_name, father_name, surname"
				+", gender, mother_name, date_of_birth"
				+", place_of_birth, guardian_name, guardian_occupation"
				+", phone_no, mobile_no, class_admission"
				+", last_attendent_school, admission_date, admission_fees"
				+", status, remarks, finger_template"
				+", finger_image, profile_image, previous_roll_no"
				+", entry_test_marks, entry_test_remarks, address) VALUES( "+ROLL_NO+", '"+studentRegistrationBean.getStudentName()+"', '"+studentRegistrationBean.getFatherName()+"', '"+studentRegistrationBean.getSurname()+"'"
				+", '"+studentRegistrationBean.getGender()+"', '"+studentRegistrationBean.getMotherName()+"', '"+studentRegistrationBean.getDateOfBirth()+"'"
				+", '"+studentRegistrationBean.getPlaceOfBirth()+"', '"+studentRegistrationBean.getGuardianName()+"', '"+studentRegistrationBean.getGuardianOccupation()+"'"
				+", '"+studentRegistrationBean.getPhoneNo()+"', '"+studentRegistrationBean.getMobileNo()+"', '"+studentRegistrationBean.getClassAdmission()+"'"
				+", '"+studentRegistrationBean.getLastAttendentSchool()+"', '"+studentRegistrationBean.getAdmissionDate()+"', "+studentRegistrationBean.getAdmissionFees()+""
				+", "+studentRegistrationBean.getStatus()+", '"+studentRegistrationBean.getRemarks()+"', '"+studentRegistrationBean.getFingerTemplate()+"'"
				+", '"+studentRegistrationBean.getFingerImage()+"', '"+studentRegistrationBean.getProfileImage()+"', "+studentRegistrationBean.getPreviousRollNo()+""
				+", "+studentRegistrationBean.getEntryTestMarks()+", '"+studentRegistrationBean.getEntryTestRemarks()+"', '"+studentRegistrationBean.getAddress()+"')";
                                mySqlSt.executeUpdate(query);
                        //-----------------------------------------------------       
                                
                                
                                
                                
                                //ROLL NO return for create img folder
                                return ROLL_NO;
                            }
                                
                                
                        }
                                
		}finally{
			st.close();
		}
            return 0;
	}//End of addStudentRegistration(StudentRegistrationBean studentRegistrationBean) method

	public static int addStudentTerm(beans.StudentTermBean studentTermBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
                        mySqlSt=mySqlCon.createStatement();
			st = con.createStatement();
			String query = "INSERT INTO student_term(term, roll_no, remarks"
				+", class_id, status,scheme_id) VALUES("+studentTermBean.getTerm()+", "+studentTermBean.getRollNo()+", '"+studentTermBean.getRemarks()+"'"
				+", "+studentTermBean.getClassId()+", "+studentTermBean.getStatus()+","+studentTermBean.getSchemeId()+ ")";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addStudentTerm(StudentTermBean studentTermBean) method

	public static int addSubjectResult(beans.SubjectResultBean subjectResultBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO subject_result(course_no, obtained_marks, max_marks"
				+", min_marks, percentage, status"
				+", remarks, marksheet_id, class_id"
				+", term, exam_list, roll_no,scheme_id) VALUES("+subjectResultBean.getCourseNo()+", "+subjectResultBean.getObtainedMarks()+", "+subjectResultBean.getMaxMarks()+""
				+", "+subjectResultBean.getMinMarks()+", "+subjectResultBean.getPercentage()+", "+subjectResultBean.getStatus()+""
				+", '"+subjectResultBean.getRemarks()+"', "+subjectResultBean.getMarksheetId()+", "+subjectResultBean.getClassId()+""
				+", "+subjectResultBean.getTerm()+","+subjectResultBean.getExamList()+", "+subjectResultBean.getRollNo()+","+subjectResultBean.getScheme_id()+")";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addSubjectResult(SubjectResultBean subjectResultBean) method

	public static int addTerm(beans.TermBean termBean)throws java.sql.SQLException{

		java.sql.Statement st=null;
                java.sql.Statement mySqlSt=null;

		try{
			st = con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query = "INSERT INTO term(class_id, remarks, term"
				+", term_month, status,scheme_id) VALUES("+termBean.getClassId()+", '"+termBean.getRemarks()+"', "+termBean.getTerm()+""
				+", "+termBean.getTermMonth()+", "+termBean.getStatus()+","+termBean.getSchemeId()+")";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}

	}//End of addTerm(TermBean termBean) method
    	
	/*********************************************************************************************************************************/	 
    	
	/******************************************************** Get Methods ************************************************************/		  
    public static beans.AttendanceBean getAttendanceByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceBean attendanceBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				attendanceBean = new beans.AttendanceBean();
				attendanceBean.setRemarks(rs.getString("remarks"));
				attendanceBean.setStatus(rs.getInt("status"));
				attendanceBean.setTerm(rs.getInt("term"));
				attendanceBean.setClassId(rs.getInt("class_id"));
				attendanceBean.setAttendanceDate(rs.getString("attendance_date"));
			}

			return attendanceBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceByTerm(int term) method

	public static Vector getAttendanceBySchemeId(int schemeId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceBean attendanceBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance WHERE status=1 AND scheme_id="+schemeId+";";
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();

			while(rs.next()){
				attendanceBean = new beans.AttendanceBean();
				attendanceBean.setRemarks(rs.getString("remarks"));
				attendanceBean.setStatus(rs.getInt("status"));
				attendanceBean.setTerm(rs.getInt("term"));
				attendanceBean.setClassId(rs.getInt("class_id"));
				attendanceBean.setAttendanceDate(rs.getString("attendance_date"));
                                attendanceBean.setSchemId(rs.getInt("scheme_id"));
                                v.addElement(attendanceBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceByClassId(int classId) method

	public static beans.AttendanceBean getAttendanceByAttendanceDate(java.sql.Timestamp attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceBean attendanceBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance WHERE status=1 AND attendance_date='"+attendanceDate+"';";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				attendanceBean = new beans.AttendanceBean();
				attendanceBean.setRemarks(rs.getString("remarks"));
				attendanceBean.setStatus(rs.getInt("status"));
				attendanceBean.setTerm(rs.getInt("term"));
				attendanceBean.setClassId(rs.getInt("class_id"));
				attendanceBean.setAttendanceDate(rs.getString("attendance_date"));
			}

			return attendanceBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceByAttendanceDate(java.sql.Timestamp attendanceDate) method

	public static java.util.Vector<beans.AttendanceBean> getAttendances()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.AttendanceBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.AttendanceBean>();

			while(rs.next()){
				beans.AttendanceBean attendanceBean = new beans.AttendanceBean();

				attendanceBean.setRemarks(rs.getString("remarks"));
				attendanceBean.setStatus(rs.getInt("status"));
				attendanceBean.setTerm(rs.getInt("term"));
				attendanceBean.setClassId(rs.getInt("class_id"));
				attendanceBean.setAttendanceDate(rs.getString("attendance_date"));

				v.addElement(attendanceBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendances() method

	public static beans.AttendanceDetailBean getAttendanceDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceDetailBean attendanceDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance_detail WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				attendanceDetailBean = new beans.AttendanceDetailBean();
				attendanceDetailBean.setClassId(rs.getInt("class_id"));
				attendanceDetailBean.setRollNo(rs.getInt("roll_no"));
				attendanceDetailBean.setStatus(rs.getInt("status"));
				attendanceDetailBean.setRemarks(rs.getString("remarks"));
				attendanceDetailBean.setTerm(rs.getInt("term"));
				attendanceDetailBean.setAttendanceDate(rs.getString("attendance_date"));
			}

			return attendanceDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceDetailByClassId(int classId) method

	public static beans.AttendanceDetailBean getAttendanceDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceDetailBean attendanceDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance_detail WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				attendanceDetailBean = new beans.AttendanceDetailBean();
				attendanceDetailBean.setClassId(rs.getInt("class_id"));
				attendanceDetailBean.setRollNo(rs.getInt("roll_no"));
				attendanceDetailBean.setStatus(rs.getInt("status"));
				attendanceDetailBean.setRemarks(rs.getString("remarks"));
				attendanceDetailBean.setTerm(rs.getInt("term"));
				attendanceDetailBean.setAttendanceDate(rs.getString("attendance_date"));
			}

			return attendanceDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceDetailByRollNo(int rollNo) method

	public static beans.AttendanceDetailBean getAttendanceDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceDetailBean attendanceDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance_detail WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				attendanceDetailBean = new beans.AttendanceDetailBean();
				attendanceDetailBean.setClassId(rs.getInt("class_id"));
				attendanceDetailBean.setRollNo(rs.getInt("roll_no"));
				attendanceDetailBean.setStatus(rs.getInt("status"));
				attendanceDetailBean.setRemarks(rs.getString("remarks"));
				attendanceDetailBean.setTerm(rs.getInt("term"));
				attendanceDetailBean.setAttendanceDate(rs.getString("attendance_date"));
			}

			return attendanceDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceDetailByTerm(int term) method

	public static Vector getAttendanceDetailByAttendanceDate(int schemeId ,String attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.AttendanceDetailBean attendanceDetailBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance_detail WHERE  scheme_id="+schemeId+" And attendance_date like '"+attendanceDate+"';";
                        System.out.println(query);
			st.execute(query);
			rs=st.getResultSet();
 
                        v=new Vector();
                    while(rs.next()){
				attendanceDetailBean = new beans.AttendanceDetailBean();
				attendanceDetailBean.setClassId(rs.getInt("class_id"));
				attendanceDetailBean.setRollNo(rs.getInt("roll_no"));
				attendanceDetailBean.setStatus(rs.getInt("status"));
				attendanceDetailBean.setRemarks(rs.getString("remarks"));
				attendanceDetailBean.setTerm(rs.getInt("term"));
				attendanceDetailBean.setAttendanceDate(rs.getString("attendance_date"));
                                v.addElement(attendanceDetailBean);
                                
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceDetailByAttendanceDate(java.sql.Timestamp attendanceDate) method

	public static java.util.Vector<beans.AttendanceDetailBean> getAttendanceDetails()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.AttendanceDetailBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM attendance_detail WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.AttendanceDetailBean>();

			while(rs.next()){
				beans.AttendanceDetailBean attendanceDetailBean = new beans.AttendanceDetailBean();

				attendanceDetailBean.setClassId(rs.getInt("class_id"));
				attendanceDetailBean.setRollNo(rs.getInt("roll_no"));
				attendanceDetailBean.setStatus(rs.getInt("status"));
				attendanceDetailBean.setRemarks(rs.getString("remarks"));
				attendanceDetailBean.setTerm(rs.getInt("term"));
				attendanceDetailBean.setAttendanceDate(rs.getString("attendance_date"));

				v.addElement(attendanceDetailBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getAttendanceDetails() method

	public static beans.ClassesBean getClassesByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ClassesBean classesBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM classes WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				classesBean = new beans.ClassesBean();
				classesBean.setClassId(rs.getInt("class_id"));
				classesBean.setClassName(rs.getString("class_name"));
				classesBean.setStatus(rs.getString("status"));
				classesBean.setRemarks(rs.getString("remarks"));
				classesBean.setClassNo(rs.getString("class_no"));
				classesBean.setSection(rs.getString("section"));
			}

			return classesBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getClassesByClassId(int classId) method

	public static java.util.Vector getClasses()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM classes WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector();

			while(rs.next()){
				beans.ClassesBean classesBean = new beans.ClassesBean();

				classesBean.setClassId(rs.getInt("class_id"));
				classesBean.setClassName(rs.getString("class_name"));
				classesBean.setStatus(rs.getString("status"));
				classesBean.setRemarks(rs.getString("remarks"));
				classesBean.setClassNo(rs.getString("class_no"));
				classesBean.setSection(rs.getString("section"));

				v.addElement(classesBean);
			}

			return v;

		}finally{
			st.close();
                        rs.close();
		}
	}//End of getClasses() method

	public static beans.CourseBean getCourseByCourseNo(int courseNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.CourseBean courseBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM course WHERE status=1 AND course_no="+courseNo+";";
			st.execute(query);
			rs=st.getResultSet();

			if(rs.next()){
				courseBean = new beans.CourseBean();
				courseBean.setCourseTitle(rs.getString("course_title"));
				courseBean.setMinMarks(rs.getInt("min_marks"));
				courseBean.setMaxMarks(rs.getInt("max_marks"));
				courseBean.setSubjectType(rs.getString("subject_type"));
				courseBean.setStatus(rs.getInt("status"));
				courseBean.setRemarks(rs.getString("remarks"));
				courseBean.setCourseNo(rs.getInt("course_no"));
				courseBean.setCourseKeyword(rs.getString("course_keyword"));
				courseBean.setClassId(rs.getInt("class_id"));
			}
			return courseBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getCourseByCourseNo(int courseNo) method

	public static java.util.Vector<beans.CourseBean> getCoursesByClassId(int classId,int scheme_id)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.CourseBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM course WHERE status=1 AND class_id="+classId+" And scheme_id="+scheme_id+";";
			st.execute(query);
                        System.out.println(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.CourseBean>();

			while(rs.next()){
				beans.CourseBean courseBean = new beans.CourseBean();

				courseBean.setCourseTitle(rs.getString("course_title"));
				courseBean.setMinMarks(rs.getInt("min_marks"));
				courseBean.setMaxMarks(rs.getInt("max_marks"));
				courseBean.setSubjectType(rs.getString("subject_type"));
				courseBean.setStatus(rs.getInt("status"));
				courseBean.setRemarks(rs.getString("remarks"));
				courseBean.setCourseNo(rs.getInt("course_no"));
				courseBean.setCourseKeyword(rs.getString("course_keyword"));
				courseBean.setClassId(rs.getInt("class_id"));

				v.addElement(courseBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getCourses() method

	public static java.util.Vector<beans.CourseBean> getCourses()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.CourseBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM course WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.CourseBean>();

			while(rs.next()){
				beans.CourseBean courseBean = new beans.CourseBean();

				courseBean.setCourseTitle(rs.getString("course_title"));
				courseBean.setMinMarks(rs.getInt("min_marks"));
				courseBean.setMaxMarks(rs.getInt("max_marks"));
				courseBean.setSubjectType(rs.getString("subject_type"));
				courseBean.setStatus(rs.getInt("status"));
				courseBean.setRemarks(rs.getString("remarks"));
				courseBean.setCourseNo(rs.getInt("course_no"));
				courseBean.setCourseKeyword(rs.getString("course_keyword"));
				courseBean.setClassId(rs.getInt("class_id"));

				v.addElement(courseBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getCourses() method

	public static beans.ExamListBean getExamListByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListBean examListBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list WHERE status=1 AND exam_list="+examList+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListBean = new beans.ExamListBean();
				examListBean.setExamList(rs.getInt("exam_list"));
				examListBean.setClassId(rs.getInt("class_id"));
				examListBean.setTerm(rs.getInt("term"));
				examListBean.setType(rs.getString("type"));
				examListBean.setStatus(rs.getInt("status"));
				examListBean.setRemarks(rs.getString("remarks"));
				examListBean.setSlYear(rs.getInt("el_year"));
				examListBean.setSlMonth(rs.getInt("el_month"));
			}

			return examListBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListByExamList(int examList) method

	public static Vector getExamListByClassId(int classId, int schemeId ,int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListBean examListBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list WHERE status=1 AND class_id="+classId+" And scheme_id="+schemeId+" And term="+term+";";
                        System.out.println(query);
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();

			while(rs.next()){
				examListBean = new beans.ExamListBean();
				examListBean.setExamList(rs.getInt("exam_list"));
				examListBean.setClassId(rs.getInt("class_id"));
				examListBean.setTerm(rs.getInt("term"));
				examListBean.setType(rs.getString("type"));
				examListBean.setStatus(rs.getInt("status"));
				examListBean.setRemarks(rs.getString("remarks"));
				examListBean.setSlYear(rs.getInt("el_year"));
				examListBean.setSlMonth(rs.getInt("el_month"));
                                v.addElement(examListBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListByClassId(int classId) method

	public static beans.ExamListBean getExamListByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListBean examListBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListBean = new beans.ExamListBean();
				examListBean.setExamList(rs.getInt("exam_list"));
				examListBean.setClassId(rs.getInt("class_id"));
				examListBean.setTerm(rs.getInt("term"));
				examListBean.setType(rs.getString("type"));
				examListBean.setStatus(rs.getInt("status"));
				examListBean.setRemarks(rs.getString("remarks"));
				examListBean.setSlYear(rs.getInt("sl_year"));
				examListBean.setSlMonth(rs.getInt("sl_month"));
			}

			return examListBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListByTerm(int term) method

	public static java.util.Vector<beans.ExamListBean> getExamLists()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.ExamListBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.ExamListBean>();

			while(rs.next()){
				beans.ExamListBean examListBean = new beans.ExamListBean();

				examListBean.setExamList(rs.getInt("exam_list"));
				examListBean.setClassId(rs.getInt("class_id"));
				examListBean.setTerm(rs.getInt("term"));
				examListBean.setType(rs.getString("type"));
				examListBean.setStatus(rs.getInt("status"));
				examListBean.setRemarks(rs.getString("remarks"));
				examListBean.setSlYear(rs.getInt("sl_year"));
				examListBean.setSlMonth(rs.getInt("sl_month"));

				v.addElement(examListBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamLists() method

	public static beans.ExamListDetailBean getExamListDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListDetailBean examListDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list_detail WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListDetailBean = new beans.ExamListDetailBean();
				examListDetailBean.setTerm(rs.getInt("term"));
				examListDetailBean.setExamList(rs.getInt("exam_list"));
				examListDetailBean.setRollNo(rs.getInt("roll_no"));
				examListDetailBean.setStatus(rs.getInt("status"));
				examListDetailBean.setClassId(rs.getInt("class_id"));
				examListDetailBean.setRemarks(rs.getString("remarks"));
			}

			return examListDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListDetailByTerm(int term) method

	public static beans.ExamListDetailBean getExamListDetailByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListDetailBean examListDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list_detail WHERE status=1 AND exam_list="+examList+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListDetailBean = new beans.ExamListDetailBean();
				examListDetailBean.setTerm(rs.getInt("term"));
				examListDetailBean.setExamList(rs.getInt("exam_list"));
				examListDetailBean.setRollNo(rs.getInt("roll_no"));
				examListDetailBean.setStatus(rs.getInt("status"));
				examListDetailBean.setClassId(rs.getInt("class_id"));
				examListDetailBean.setRemarks(rs.getString("remarks"));
			}

			return examListDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListDetailByExamList(int examList) method

	public static beans.ExamListDetailBean getExamListDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListDetailBean examListDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list_detail WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListDetailBean = new beans.ExamListDetailBean();
				examListDetailBean.setTerm(rs.getInt("term"));
				examListDetailBean.setExamList(rs.getInt("exam_list"));
				examListDetailBean.setRollNo(rs.getInt("roll_no"));
				examListDetailBean.setStatus(rs.getInt("status"));
				examListDetailBean.setClassId(rs.getInt("class_id"));
				examListDetailBean.setRemarks(rs.getString("remarks"));
			}

			return examListDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListDetailByRollNo(int rollNo) method

	public static beans.ExamListDetailBean getExamListDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.ExamListDetailBean examListDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list_detail WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				examListDetailBean = new beans.ExamListDetailBean();
				examListDetailBean.setTerm(rs.getInt("term"));
				examListDetailBean.setExamList(rs.getInt("exam_list"));
				examListDetailBean.setRollNo(rs.getInt("roll_no"));
				examListDetailBean.setStatus(rs.getInt("status"));
				examListDetailBean.setClassId(rs.getInt("class_id"));
				examListDetailBean.setRemarks(rs.getString("remarks"));
			}

			return examListDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListDetailByClassId(int classId) method

	public static java.util.Vector<beans.ExamListDetailBean> getExamListDetails(int classId,int schemeId,int term,int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.ExamListDetailBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM exam_list_detail WHERE status=1 And class_id="+classId+" And scheme_id="+schemeId+" And term="+term+" And exam_list="+examList;
			st.execute(query);
			rs=st.getResultSet();
                           System.out.println(query);
			v = new java.util.Vector<beans.ExamListDetailBean>();

			while(rs.next()){
				beans.ExamListDetailBean examListDetailBean = new beans.ExamListDetailBean();
                                examListDetailBean.setSchemeId(rs.getInt("scheme_id"));
				examListDetailBean.setTerm(rs.getInt("term"));
				examListDetailBean.setExamList(rs.getInt("exam_list"));
				examListDetailBean.setRollNo(rs.getInt("roll_no"));
				examListDetailBean.setStatus(rs.getInt("status"));
				examListDetailBean.setClassId(rs.getInt("class_id"));
				examListDetailBean.setRemarks(rs.getString("remarks"));
				v.addElement(examListDetailBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListDetails() method

	public static beans.LedgerDetailBean getLedgerDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.LedgerDetailBean ledgerDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				ledgerDetailBean = new beans.LedgerDetailBean();
				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));
			}

			return ledgerDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetailByTerm(int term) method

	public static beans.LedgerDetailBean getLedgerDetailBySeatList(int seatList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.LedgerDetailBean ledgerDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1 AND seat_list="+seatList+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				ledgerDetailBean = new beans.LedgerDetailBean();
				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));
			}

			return ledgerDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetailBySeatList(int seatList) method

	public static beans.LedgerDetailBean getLedgerDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.LedgerDetailBean ledgerDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				ledgerDetailBean = new beans.LedgerDetailBean();
				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));
			}

			return ledgerDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetailByRollNo(int rollNo) method

	public static beans.LedgerDetailBean getLedgerDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.LedgerDetailBean ledgerDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				ledgerDetailBean = new beans.LedgerDetailBean();
				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));
			}

			return ledgerDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetailByClassId(int classId) method

	public static beans.LedgerDetailBean getLedgerDetailBySchemeId(int schemeId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.LedgerDetailBean ledgerDetailBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1 AND scheme_id="+schemeId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				ledgerDetailBean = new beans.LedgerDetailBean();
				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));
			}

			return ledgerDetailBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetailBySchemeId(int schemeId) method

	public static java.util.Vector<beans.LedgerDetailBean> getLedgerDetails()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.LedgerDetailBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM ledger_detail WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.LedgerDetailBean>();

			while(rs.next()){
				beans.LedgerDetailBean ledgerDetailBean = new beans.LedgerDetailBean();

				ledgerDetailBean.setTerm(rs.getInt("term"));
				ledgerDetailBean.setSeatList(rs.getInt("seat_list"));
				ledgerDetailBean.setRollNo(rs.getInt("roll_no"));
				ledgerDetailBean.setStatus(rs.getInt("status"));
				ledgerDetailBean.setRemarks(rs.getString("remarks"));
				ledgerDetailBean.setClassId(rs.getInt("class_id"));
				ledgerDetailBean.setSchemeId(rs.getInt("scheme_id"));
				ledgerDetailBean.setSeatNo(rs.getInt("seat_no"));
				ledgerDetailBean.setMarksObtain(rs.getInt("marks_obtain"));
				ledgerDetailBean.setTotalMarks(rs.getInt("total_marks"));
				ledgerDetailBean.setRank(rs.getString("rank"));
				ledgerDetailBean.setGrade(rs.getString("grade"));
				ledgerDetailBean.setDateOfDeclare(rs.getTimestamp("date_of_declare"));

				v.addElement(ledgerDetailBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getLedgerDetails() method

	public static beans.MarksheetBean getMarksheetByMarsheetId(int marsheetId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.MarksheetBean marksheetBean = null;
                String query="SELECT * FROM marksheet WHERE status=1 AND marksheet_id="+marsheetId+";";
                 System.out.println(query);

		try{
			st=con.createStatement();
			
			st.execute(query);
			rs=st.getResultSet(); 
 

			if(rs.next()){
				marksheetBean = new beans.MarksheetBean();
				marksheetBean.setMarsheetId(rs.getInt("marksheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
                                marksheetBean.setSchemeId(rs.getInt("scheme_id"));
				//marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
			}

			return marksheetBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheetByMarsheetId(int marsheetId) method

	public static beans.MarksheetBean getMarksheetByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.MarksheetBean marksheetBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM marksheet WHERE status=1 AND exam_list="+examList+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				marksheetBean = new beans.MarksheetBean();
				marksheetBean.setMarsheetId(rs.getInt("marsheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
				marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
			}

			return marksheetBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheetByExamList(int examList) method

	public static beans.MarksheetBean getMarksheetByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.MarksheetBean marksheetBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM marksheet WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				marksheetBean = new beans.MarksheetBean();
				marksheetBean.setMarsheetId(rs.getInt("marsheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
				marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
			}

			return marksheetBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheetByRollNo(int rollNo) method

	public static beans.MarksheetBean getMarksheetByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.MarksheetBean marksheetBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM marksheet WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				marksheetBean = new beans.MarksheetBean();
				marksheetBean.setMarsheetId(rs.getInt("marsheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
				marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
			}

			return marksheetBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheetByClassId(int classId) method

	public static beans.MarksheetBean getMarksheetByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.MarksheetBean marksheetBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM marksheet WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				marksheetBean = new beans.MarksheetBean();
				marksheetBean.setMarsheetId(rs.getInt("marsheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
				marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
			}

			return marksheetBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheetByTerm(int term) method

	public static java.util.Vector<beans.MarksheetBean> getMarksheets(int classId,int schemeId,int term,int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.MarksheetBean> v = null;
                String query="SELECT * FROM marksheet WHERE status=1 And class_id="+classId+" And scheme_id="+schemeId+" And term="+term+" And exam_list="+examList+"";
                 System.out.println(query);

		try{
			st=con.createStatement();
			
			st.execute(query);
			rs=st.getResultSet();
                    
			v = new java.util.Vector<beans.MarksheetBean>();

			while(rs.next()){
				beans.MarksheetBean marksheetBean = new beans.MarksheetBean();

				marksheetBean.setMarsheetId(rs.getInt("marksheet_id"));
				marksheetBean.setExamList(rs.getInt("exam_list"));
				marksheetBean.setRollNo(rs.getInt("roll_no"));
				marksheetBean.setSeatNo(rs.getInt("seat_no"));
				marksheetBean.setTotalMarks(rs.getInt("total_marks"));
				marksheetBean.setObtainMarks(rs.getInt("obtain_marks"));
				marksheetBean.setPercentage(rs.getInt("percentage"));
				marksheetBean.setGrade(rs.getString("grade"));
				marksheetBean.setRank(rs.getString("rank"));
				marksheetBean.setMarksInWords(rs.getString("marks_in_words"));
				marksheetBean.setPosition(rs.getString("position"));
				marksheetBean.setTotalDays(rs.getInt("total_days"));
				marksheetBean.setPresentDays(rs.getInt("present_days"));
				marksheetBean.setStatus(rs.getInt("status"));
				marksheetBean.setRemarks(rs.getString("remarks"));
				marksheetBean.setClassId(rs.getInt("class_id"));
				marksheetBean.setTerm(rs.getInt("term"));
				//marksheetBean.setDateOfDeclaration(rs.getTimestamp("date_of_declaration"));
                                marksheetBean.setMarsheetId(rs.getInt("marksheet_id"));
				v.addElement(marksheetBean);
			}
			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getMarksheets() method

	public static Vector getSchemeByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SchemeBean schemeBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM scheme WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();

			while(rs.next()){
				schemeBean = new beans.SchemeBean();
                                schemeBean.setSchemeId(rs.getInt("scheme_id"));
				schemeBean.setMaxMarks(rs.getInt("max_marks"));
				schemeBean.setMinMarks(rs.getInt("min_marks"));
				schemeBean.setRemarks(rs.getString("remarks"));
				schemeBean.setStatus(rs.getInt("status"));
				schemeBean.setClassId(rs.getInt("class_id"));
				schemeBean.setLastUpdate(rs.getString("last_update"));
				schemeBean.setSchemeName(rs.getString("scheme_name"));
                                v.addElement(schemeBean);
			}
                        return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSchemeByClassId(int classId) method

	public static java.util.Vector<beans.SchemeBean> getSchemes()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.SchemeBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM scheme WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.SchemeBean>();

			while(rs.next()){
				beans.SchemeBean schemeBean = new beans.SchemeBean();
                                
                                schemeBean.setSchemeId(rs.getInt("scheme_id"));
				schemeBean.setMaxMarks(rs.getInt("max_marks"));
				schemeBean.setMinMarks(rs.getInt("min_marks"));
				schemeBean.setRemarks(rs.getString("remarks"));
				schemeBean.setStatus(rs.getInt("status"));
				schemeBean.setClassId(rs.getInt("class_id"));
				schemeBean.setLastUpdate(rs.getString("last_update"));
				schemeBean.setSchemeName(rs.getString("scheme_name"));

				v.addElement(schemeBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getSchemes() method

	public static Vector getStudentClassByClassId(int classId,int schemeId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentClassBean studentClassBean = null;
                java.util.Vector v = null;    
		try{
			st=con.createStatement();
			String query="SELECT * FROM student_class WHERE status=1 AND class_id="+classId+" And scheme_id="+schemeId;
			st.execute(query);
			rs=st.getResultSet();
                        
                        v = new java.util.Vector();    
			while(rs.next()){
				studentClassBean = new beans.StudentClassBean();
                                studentClassBean.setSchemeId(rs.getInt("scheme_id"));
				studentClassBean.setRemarks(rs.getString("remarks"));
				studentClassBean.setClassId(rs.getInt("class_id"));
				studentClassBean.setRollNo(rs.getInt("roll_no"));
				studentClassBean.setStatus(rs.getInt("status"));
                                v.addElement(studentClassBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentClassByClassId(int classId) method

	public static beans.StudentClassBean getStudentClassByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentClassBean studentClassBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_class WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				studentClassBean = new beans.StudentClassBean();
				studentClassBean.setRemarks(rs.getString("remarks"));
				studentClassBean.setClassId(rs.getInt("class_id"));
				studentClassBean.setRollNo(rs.getInt("roll_no"));
				studentClassBean.setStatus(rs.getInt("status"));
			}

			return studentClassBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentClassByRollNo(int rollNo) method

	public static java.util.Vector<beans.StudentClassBean> getStudentClass()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.StudentClassBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_class WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.StudentClassBean>();

			while(rs.next()){
				beans.StudentClassBean studentClassBean = new beans.StudentClassBean();

				studentClassBean.setRemarks(rs.getString("remarks"));
				studentClassBean.setClassId(rs.getInt("class_id"));
				studentClassBean.setRollNo(rs.getInt("roll_no"));
				studentClassBean.setStatus(rs.getInt("status"));

				v.addElement(studentClassBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentClass() method

	public static beans.StudentRegistrationBean getStudentRegistrationByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentRegistrationBean studentRegistrationBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				studentRegistrationBean = new beans.StudentRegistrationBean();
				studentRegistrationBean.setStudentName(rs.getString("student_name"));
				studentRegistrationBean.setFatherName(rs.getString("father_name"));
				studentRegistrationBean.setSurname(rs.getString("surname"));
				studentRegistrationBean.setGender(rs.getString("gender"));
				studentRegistrationBean.setMotherName(rs.getString("mother_name"));
				studentRegistrationBean.setDateOfBirth(rs.getString("date_of_birth"));
				studentRegistrationBean.setPlaceOfBirth(rs.getString("place_of_birth"));
				studentRegistrationBean.setGuardianName(rs.getString("guardian_name"));
				studentRegistrationBean.setGuardianOccupation(rs.getString("guardian_occupation"));
				studentRegistrationBean.setPhoneNo(rs.getString("phone_no"));
				studentRegistrationBean.setMobileNo(rs.getString("mobile_no"));
				studentRegistrationBean.setClassAdmission(rs.getString("class_admission"));
				studentRegistrationBean.setLastAttendentSchool(rs.getString("last_attendent_school"));
				studentRegistrationBean.setAdmissionDate(rs.getString("admission_date"));
				studentRegistrationBean.setAdmissionFees(rs.getInt("admission_fees"));
				studentRegistrationBean.setStatus(rs.getInt("status"));
				studentRegistrationBean.setRemarks(rs.getString("remarks"));
				studentRegistrationBean.setFingerTemplate(rs.getString("finger_template"));
				studentRegistrationBean.setFingerImage(rs.getString("finger_image"));
				studentRegistrationBean.setProfileImage(rs.getString("profile_image"));
				studentRegistrationBean.setPreviousRollNo(rs.getInt("previous_roll_no"));
				studentRegistrationBean.setRollNo(rs.getInt("roll_no"));
				studentRegistrationBean.setEntryTestMarks(rs.getInt("entry_test_marks"));
				studentRegistrationBean.setEntryTestRemarks(rs.getString("entry_test_remarks"));
				studentRegistrationBean.setAddress(rs.getString("address"));
			}

			return studentRegistrationBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentRegistrationByRollNo(int rollNo) method

	public static java.util.Vector getStudentRegistrations()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.StudentRegistrationBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.StudentRegistrationBean>();

			while(rs.next()){
				beans.StudentRegistrationBean studentRegistrationBean = new beans.StudentRegistrationBean();

				studentRegistrationBean.setStudentName(rs.getString("student_name"));
				studentRegistrationBean.setFatherName(rs.getString("father_name"));
				studentRegistrationBean.setSurname(rs.getString("surname"));
				studentRegistrationBean.setGender(rs.getString("gender"));
				studentRegistrationBean.setMotherName(rs.getString("mother_name"));
				studentRegistrationBean.setDateOfBirth(rs.getString("date_of_birth"));
				studentRegistrationBean.setPlaceOfBirth(rs.getString("place_of_birth"));
				studentRegistrationBean.setGuardianName(rs.getString("guardian_name"));
				studentRegistrationBean.setGuardianOccupation(rs.getString("guardian_occupation"));
				studentRegistrationBean.setPhoneNo(rs.getString("phone_no"));
				studentRegistrationBean.setMobileNo(rs.getString("mobile_no"));
				studentRegistrationBean.setClassAdmission(rs.getString("class_admission"));
				studentRegistrationBean.setLastAttendentSchool(rs.getString("last_attendent_school"));
				studentRegistrationBean.setAdmissionDate(rs.getString("admission_date"));
				studentRegistrationBean.setAdmissionFees(rs.getInt("admission_fees"));
				studentRegistrationBean.setStatus(rs.getInt("status"));
				studentRegistrationBean.setRemarks(rs.getString("remarks"));
				studentRegistrationBean.setFingerTemplate(rs.getString("finger_template"));
				studentRegistrationBean.setFingerImage(rs.getString("finger_image"));
				studentRegistrationBean.setProfileImage(rs.getString("profile_image"));
				studentRegistrationBean.setPreviousRollNo(rs.getInt("previous_roll_no"));
				studentRegistrationBean.setRollNo(rs.getInt("roll_no"));
				studentRegistrationBean.setEntryTestMarks(rs.getInt("entry_test_marks"));
				studentRegistrationBean.setEntryTestRemarks(rs.getString("entry_test_remarks"));
				studentRegistrationBean.setAddress(rs.getString("address"));

				v.addElement(studentRegistrationBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentRegistrations() method

	public static beans.StudentTermBean getStudentTermByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentTermBean studentTermBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_term WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				studentTermBean = new beans.StudentTermBean();
				studentTermBean.setTerm(rs.getInt("term"));
				studentTermBean.setRollNo(rs.getInt("roll_no"));
				studentTermBean.setRemarks(rs.getString("remarks"));
				studentTermBean.setClassId(rs.getInt("class_id"));
				studentTermBean.setStatus(rs.getInt("status"));
			}

			return studentTermBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentTermByTerm(int term) method

	public static beans.StudentTermBean getStudentTermByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentTermBean studentTermBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_term WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				studentTermBean = new beans.StudentTermBean();
				studentTermBean.setTerm(rs.getInt("term"));
				studentTermBean.setRollNo(rs.getInt("roll_no"));
				studentTermBean.setRemarks(rs.getString("remarks"));
				studentTermBean.setClassId(rs.getInt("class_id"));
				studentTermBean.setStatus(rs.getInt("status"));
			}

			return studentTermBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentTermByRollNo(int rollNo) method

	public static beans.StudentTermBean getStudentTermByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.StudentTermBean studentTermBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_term WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				studentTermBean = new beans.StudentTermBean();
				studentTermBean.setTerm(rs.getInt("term"));
				studentTermBean.setRollNo(rs.getInt("roll_no"));
				studentTermBean.setRemarks(rs.getString("remarks"));
				studentTermBean.setClassId(rs.getInt("class_id"));
				studentTermBean.setStatus(rs.getInt("status"));
			}

			return studentTermBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentTermByClassId(int classId) method

	public static java.util.Vector<beans.StudentTermBean> getStudentTerms(int classId,int schemeId ,int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.StudentTermBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM student_term WHERE status=1 And class_id= "+classId +" And scheme_id="+schemeId+" And term="+term+";"; 
			st.execute(query);
			rs=st.getResultSet();
                        System.out.println(query);

			v = new java.util.Vector<beans.StudentTermBean>();

			while(rs.next()){
				beans.StudentTermBean studentTermBean = new beans.StudentTermBean();

				studentTermBean.setTerm(rs.getInt("term"));
				studentTermBean.setRollNo(rs.getInt("roll_no"));
				studentTermBean.setRemarks(rs.getString("remarks"));
				studentTermBean.setClassId(rs.getInt("class_id"));
				studentTermBean.setStatus(rs.getInt("status"));
                                studentTermBean.setSchemeId(rs.getInt("scheme_id"));

				v.addElement(studentTermBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getStudentTerms() method

	public static java.util.Vector<beans.SubjectResultBean> getSubjectResultsByCourseNo(int courseNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.SubjectResultBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND course_no="+courseNo+";";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.SubjectResultBean>();

			while(rs.next()){
				beans.SubjectResultBean subjectResultBean = new beans.SubjectResultBean();

				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));

				v.addElement(subjectResultBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResults() method

	public static Vector getSubjectResultByMarksheetId(int marksheetId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND marksheet_id="+marksheetId+";";
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();
                        System.out.println(query);
 

			while(rs.next()){
				subjectResultBean = new beans.SubjectResultBean();
				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));
                                subjectResultBean.setScheme_id(rs.getInt("scheme_id"));
                                v.addElement(subjectResultBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResultByMarksheetId(int marksheetId) method

	public static beans.SubjectResultBean getSubjectResultByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND class_id="+classId+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				subjectResultBean = new beans.SubjectResultBean();
				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));
			}

			return subjectResultBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResultByClassId(int classId) method

	public static beans.SubjectResultBean getSubjectResultByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				subjectResultBean = new beans.SubjectResultBean();
				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));
			}

			return subjectResultBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResultByTerm(int term) method

	public static beans.SubjectResultBean getSubjectResultByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND exam_list="+examList+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				subjectResultBean = new beans.SubjectResultBean();
				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));
			}

			return subjectResultBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResultByExamList(int examList) method

	public static beans.SubjectResultBean getSubjectResultByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1 AND roll_no="+rollNo+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				subjectResultBean = new beans.SubjectResultBean();
				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));
			}

			return subjectResultBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResultByRollNo(int rollNo) method

	public static java.util.Vector<beans.SubjectResultBean> getSubjectResults()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.SubjectResultBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM subject_result WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.SubjectResultBean>();

			while(rs.next()){
				beans.SubjectResultBean subjectResultBean = new beans.SubjectResultBean();

				subjectResultBean.setCourseNo(rs.getInt("course_no"));
				subjectResultBean.setObtainedMarks(rs.getInt("obtained_marks"));
				subjectResultBean.setMaxMarks(rs.getInt("max_marks"));
				subjectResultBean.setMinMarks(rs.getInt("min_marks"));
				subjectResultBean.setPercentage(rs.getInt("percentage"));
				subjectResultBean.setStatus(rs.getInt("status"));
				subjectResultBean.setRemarks(rs.getString("remarks"));
				subjectResultBean.setMarksheetId(rs.getInt("marksheet_id"));
				subjectResultBean.setClassId(rs.getInt("class_id"));
				subjectResultBean.setTerm(rs.getInt("term"));
				subjectResultBean.setExamList(rs.getInt("exam_list"));
				subjectResultBean.setRollNo(rs.getInt("roll_no"));

				v.addElement(subjectResultBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getSubjectResults() method

	public static Vector getTermByClassId(int classId ,int schemeId)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.TermBean termBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM term WHERE status=1 AND class_id="+classId+" And scheme_id="+schemeId+";";
                        System.out.println(query);
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();
 

			while(rs.next()){
				termBean = new beans.TermBean();
				termBean.setClassId(rs.getInt("class_id"));
                                termBean.setSchemeId(rs.getInt("scheme_id"));
				termBean.setRemarks(rs.getString("remarks"));
				termBean.setTerm(rs.getInt("term"));
				termBean.setTermMonth(rs.getInt("term_month"));
				termBean.setStatus(rs.getInt("status"));
                                v.addElement(termBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getTermByClassId(int classId) method

	public static beans.TermBean getTermByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.TermBean termBean = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM term WHERE status=1 AND term="+term+";";
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				termBean = new beans.TermBean();
				termBean.setClassId(rs.getInt("class_id"));
				termBean.setRemarks(rs.getString("remarks"));
				termBean.setTerm(rs.getInt("term"));
				termBean.setTermMonth(rs.getInt("term_month"));
				termBean.setStatus(rs.getInt("status"));
			}

			return termBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getTermByTerm(int term) method

	public static java.util.Vector<beans.TermBean> getTerms()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		java.util.Vector<beans.TermBean> v = null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM term WHERE status=1";
			st.execute(query);
			rs=st.getResultSet();

			v = new java.util.Vector<beans.TermBean>();

			while(rs.next()){
				beans.TermBean termBean = new beans.TermBean();

				termBean.setClassId(rs.getInt("class_id"));
				termBean.setRemarks(rs.getString("remarks"));
				termBean.setTerm(rs.getInt("term"));
				termBean.setTermMonth(rs.getInt("term_month"));
				termBean.setStatus(rs.getInt("status"));

				v.addElement(termBean);
			}

			return v;

		}finally{
			st.close();
			rs.close();
		}
	}//End of getTerms() method
    	
	/*********************************************************************************************************************************/
	
	/****************************************************** Update Methods ***********************************************************/		
    public static int updateAttendanceByTerm(beans.AttendanceBean attendanceBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET remarks = '"+attendanceBean.getRemarks()+"', SET status = "+attendanceBean.getStatus()+", SET class_id = "+attendanceBean.getClassId()+", "+
				"SET attendance_date = '"+attendanceBean.getAttendanceDate()+"',  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceByTerm(beans.AttendanceBean attendanceBean, int term) method

	public static int updateAttendanceByClassId(beans.AttendanceBean attendanceBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET remarks = '"+attendanceBean.getRemarks()+"', SET status = "+attendanceBean.getStatus()+", "+
				"SET term = "+attendanceBean.getTerm()+", "+
				"SET attendance_date = '"+attendanceBean.getAttendanceDate()+"',  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceByClassId(beans.AttendanceBean attendanceBean, int classId) method

	public static int updateAttendanceByAttendanceDate(beans.AttendanceBean attendanceBean, java.sql.Timestamp attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET remarks = '"+attendanceBean.getRemarks()+"', SET status = "+attendanceBean.getStatus()+", "+
				"SET term = "+attendanceBean.getTerm()+", SET class_id = "+attendanceBean.getClassId()+",  WHERE attendance_date='"+attendanceDate+"';";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceByAttendanceDate(beans.AttendanceBean attendanceBean, java.sql.Timestamp attendanceDate) method

	public static int updateAttendanceDetailByClassId(beans.AttendanceDetailBean attendanceDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET roll_no = "+attendanceDetailBean.getRollNo()+", "+
				"SET status = "+attendanceDetailBean.getStatus()+", SET remarks = '"+attendanceDetailBean.getRemarks()+"', "+
				"SET term = "+attendanceDetailBean.getTerm()+", SET attendance_date = '"+attendanceDetailBean.getAttendanceDate()+"',  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceDetailByClassId(beans.AttendanceDetailBean attendanceDetailBean, int classId) method

	public static int updateAttendanceDetailByRollNo(beans.AttendanceDetailBean attendanceDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
                        
//			String query="UPDATE attendance_detail SET class_id = "+attendanceDetailBean.getClassId()+", "+
//				" status = "+attendanceDetailBean.getStatus()+",  remarks = '"+attendanceDetailBean.getRemarks()+"', "+
//				" term = "+attendanceDetailBean.getTerm()+", attendance_date = '"+attendanceDetailBean.getAttendanceDate()+
//                                "', scheme_id="+attendanceDetailBean.getSchemeId() +" WHERE  class_id="+attendanceDetailBean.getClassId()
//                                +"And scheme_id="+attendanceDetailBean.getSchemeId()+" And term="+attendanceDetailBean.getTerm()+" And attendacen_date="+attendanceDetailBean.getAttendanceDate()+
//                                " And roll_no="+rollNo+";";
                        
                    String query ="UPDATE attendance_detail SET  status = "+attendanceDetailBean.getStatus()+" WHERE  class_id="+attendanceDetailBean.getClassId()+" And scheme_id="+attendanceDetailBean.getSchemeId()+"  And term="+attendanceDetailBean.getTerm() +"   And attendance_date='"+attendanceDetailBean.getAttendanceDate()+"' And roll_no="+rollNo;    
                          mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceDetailByRollNo(beans.AttendanceDetailBean attendanceDetailBean, int rollNo) method

	public static int updateAttendanceDetailByTerm(beans.AttendanceDetailBean attendanceDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET class_id = "+attendanceDetailBean.getClassId()+", SET roll_no = "+attendanceDetailBean.getRollNo()+", "+
				"SET status = "+attendanceDetailBean.getStatus()+", SET remarks = '"+attendanceDetailBean.getRemarks()+"', SET attendance_date = '"+attendanceDetailBean.getAttendanceDate()+"',  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceDetailByTerm(beans.AttendanceDetailBean attendanceDetailBean, int term) method

	public static int updateAttendanceDetailByAttendanceDate(beans.AttendanceDetailBean attendanceDetailBean, java.sql.Timestamp attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET class_id = "+attendanceDetailBean.getClassId()+", roll_no = "+attendanceDetailBean.getRollNo()+""+
				", status = "+attendanceDetailBean.getStatus()+", remarks = '"+attendanceDetailBean.getRemarks()+"'"+
				", term ="+attendanceDetailBean.getTerm()+", scheme_id="+ attendanceDetailBean.getSchemeId()+ " WHERE attendance_date='"+attendanceDate+"' ;";
			System.out.println(query);
                        return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateAttendanceDetailByAttendanceDate(beans.AttendanceDetailBean attendanceDetailBean, java.sql.Timestamp attendanceDate) method

	public static int updateClassesByClassId(beans.ClassesBean classesBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="UPDATE classes SET class_name = '"+classesBean.getClassName()+"', "+
				"status = '"+classesBean.getStatus()+"', remarks = '"+classesBean.getRemarks()+"', "+
				"class_no = '"+classesBean.getClassNo()+"', section = '"+classesBean.getSection()+"'  WHERE class_id="+classId+";";
			System.out.println(query);
                        mySqlSt.executeUpdate(query);
                        return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateClassesByClassId(beans.ClassesBean classesBean, int classId) method

	public static int updateCourseByCourseNo(beans.CourseBean courseBean, int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt = null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="UPDATE course SET course_title = '"+courseBean.getCourseTitle()+"',  min_marks = "+courseBean.getMinMarks()+", "+
				"max_marks = "+courseBean.getMaxMarks()+", subject_type = '"+courseBean.getSubjectType()+"', "+
				"status = "+courseBean.getStatus()+", remarks = '"+courseBean.getRemarks()+"', course_keyword = '"+courseBean.getCourseKeyword()+"', "+
				"class_id = "+courseBean.getClassId()+"  WHERE course_no="+courseNo+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateCourseByCourseNo(beans.CourseBean courseBean, int courseNo) method

	public static int updateCourseByClassId(beans.CourseBean courseBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE course SET course_title = '"+courseBean.getCourseTitle()+"', min_marks = "+courseBean.getMinMarks()+", "+
				"max_marks = "+courseBean.getMaxMarks()+", subject_type = '"+courseBean.getSubjectType()+"', "+
				"status = "+courseBean.getStatus()+", remarks = '"+courseBean.getRemarks()+"', course_keyword = '"+courseBean.getCourseKeyword()+"' WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateCourseByClassId(beans.CourseBean courseBean, int classId) method

	public static int updateExamListByExamList(beans.ExamListBean examListBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list SET class_id = "+examListBean.getClassId()+", "+
				"term = "+examListBean.getTerm()+", type = '"+examListBean.getType()+"', "+
				"status = "+examListBean.getStatus()+", remarks = '"+examListBean.getRemarks()+"', "+
				"sl_year = "+examListBean.getSlYear()+", sl_month = "+examListBean.getSlMonth()+" WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListByExamList(beans.ExamListBean examListBean, int examList) method

	public static int updateExamListByClassId(beans.ExamListBean examListBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list "+
				"SET term = "+examListBean.getTerm()+", type = '"+examListBean.getType()+"', "+
				"status = "+examListBean.getStatus()+", remarks = '"+examListBean.getRemarks()+"', "+
				"sl_year = "+examListBean.getSlYear()+", sl_month = "+examListBean.getSlMonth()+"  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListByClassId(beans.ExamListBean examListBean, int classId) method

	public static int updateExamListByTerm(beans.ExamListBean examListBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list SET class_id = "+examListBean.getClassId()+", SET type = '"+examListBean.getType()+"', "+
				"status = "+examListBean.getStatus()+", remarks = '"+examListBean.getRemarks()+"', "+
				"sl_year = "+examListBean.getSlYear()+", sl_month = "+examListBean.getSlMonth()+"  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListByTerm(beans.ExamListBean examListBean, int term) method

	public static int updateExamListDetailByTerm(beans.ExamListDetailBean examListDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET exam_list = "+examListDetailBean.getExamList()+", "+
				"roll_no = "+examListDetailBean.getRollNo()+", status = "+examListDetailBean.getStatus()+", "+
				"class_id = "+examListDetailBean.getClassId()+", remarks = '"+examListDetailBean.getRemarks()+"' WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListDetailByTerm(beans.ExamListDetailBean examListDetailBean, int term) method

	public static int updateExamListDetailByExamList(beans.ExamListDetailBean examListDetailBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET term = "+examListDetailBean.getTerm()+", "+
				"roll_no = "+examListDetailBean.getRollNo()+", status = "+examListDetailBean.getStatus()+", "+
				"class_id = "+examListDetailBean.getClassId()+", remarks = '"+examListDetailBean.getRemarks()+"' WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListDetailByExamList(beans.ExamListDetailBean examListDetailBean, int examList) method

	public static int updateExamListDetailByRollNo(beans.ExamListDetailBean examListDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET term = "+examListDetailBean.getTerm()+", SET exam_list = "+examListDetailBean.getExamList()+", SET status = "+examListDetailBean.getStatus()+", "+
				"class_id = "+examListDetailBean.getClassId()+", remarks = '"+examListDetailBean.getRemarks()+"' WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListDetailByRollNo(beans.ExamListDetailBean examListDetailBean, int rollNo) method

	public static int updateExamListDetailByClassId(beans.ExamListDetailBean examListDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET term = "+examListDetailBean.getTerm()+", exam_list = "+examListDetailBean.getExamList()+", "+
				"roll_no = "+examListDetailBean.getRollNo()+", status = "+examListDetailBean.getStatus()+", remarks = '"+examListDetailBean.getRemarks()+"' WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateExamListDetailByClassId(beans.ExamListDetailBean examListDetailBean, int classId) method

	public static int updateLedgerDetailByTerm(beans.LedgerDetailBean ledgerDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET seat_list = "+ledgerDetailBean.getSeatList()+", "+
				"roll_no = "+ledgerDetailBean.getRollNo()+", status = "+ledgerDetailBean.getStatus()+", "+
				"remarks = '"+ledgerDetailBean.getRemarks()+"', class_id = "+ledgerDetailBean.getClassId()+", "+
				"scheme_id = "+ledgerDetailBean.getSchemeId()+", "+
				"marks_obtain = "+ledgerDetailBean.getMarksObtain()+", total_marks = "+ledgerDetailBean.getTotalMarks()+", "+
				"rank = '"+ledgerDetailBean.getRank()+"', grade = '"+ledgerDetailBean.getGrade()+"', "+
				"date_of_declare = '"+ledgerDetailBean.getDateOfDeclare()+"' WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateLedgerDetailByTerm(beans.LedgerDetailBean ledgerDetailBean, int term) method

	public static int updateLedgerDetailBySeatList(beans.LedgerDetailBean ledgerDetailBean, int seatList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET term = "+ledgerDetailBean.getTerm()+", "+
				"roll_no = "+ledgerDetailBean.getRollNo()+", status = "+ledgerDetailBean.getStatus()+", "+
				"remarks = '"+ledgerDetailBean.getRemarks()+"', class_id = "+ledgerDetailBean.getClassId()+", "+
				"scheme_id = "+ledgerDetailBean.getSchemeId()+", "+
				"marks_obtain = "+ledgerDetailBean.getMarksObtain()+", total_marks = "+ledgerDetailBean.getTotalMarks()+", "+
				"rank = '"+ledgerDetailBean.getRank()+"', grade = '"+ledgerDetailBean.getGrade()+"', "+
				"date_of_declare = '"+ledgerDetailBean.getDateOfDeclare()+"' WHERE seat_list="+seatList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateLedgerDetailBySeatList(beans.LedgerDetailBean ledgerDetailBean, int seatList) method

	public static int updateLedgerDetailByRollNo(beans.LedgerDetailBean ledgerDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET term = "+ledgerDetailBean.getTerm()+", seat_list = "+ledgerDetailBean.getSeatList()+", status = "+ledgerDetailBean.getStatus()+", "+
				"remarks = '"+ledgerDetailBean.getRemarks()+"', class_id = "+ledgerDetailBean.getClassId()+", "+
				"scheme_id = "+ledgerDetailBean.getSchemeId()+", "+
				"marks_obtain = "+ledgerDetailBean.getMarksObtain()+", total_marks = "+ledgerDetailBean.getTotalMarks()+", "+
				"rank = '"+ledgerDetailBean.getRank()+"', grade = '"+ledgerDetailBean.getGrade()+"', "+
				"date_of_declare = '"+ledgerDetailBean.getDateOfDeclare()+"'  WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateLedgerDetailByRollNo(beans.LedgerDetailBean ledgerDetailBean, int rollNo) method

	public static int updateLedgerDetailByClassId(beans.LedgerDetailBean ledgerDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET term = "+ledgerDetailBean.getTerm()+", seat_list = "+ledgerDetailBean.getSeatList()+", "+
				"roll_no = "+ledgerDetailBean.getRollNo()+", status = "+ledgerDetailBean.getStatus()+", "+
				"remarks = '"+ledgerDetailBean.getRemarks()+"', "+
				"scheme_id = "+ledgerDetailBean.getSchemeId()+", "+
				"marks_obtain = "+ledgerDetailBean.getMarksObtain()+", total_marks = "+ledgerDetailBean.getTotalMarks()+", "+
				"rank = '"+ledgerDetailBean.getRank()+"', grade = '"+ledgerDetailBean.getGrade()+"', "+
				"date_of_declare = '"+ledgerDetailBean.getDateOfDeclare()+"' WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateLedgerDetailByClassId(beans.LedgerDetailBean ledgerDetailBean, int classId) method

	public static int updateLedgerDetailBySchemeId(beans.LedgerDetailBean ledgerDetailBean, int schemeId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET term = "+ledgerDetailBean.getTerm()+", seat_list = "+ledgerDetailBean.getSeatList()+", "+
				"roll_no = "+ledgerDetailBean.getRollNo()+", status = "+ledgerDetailBean.getStatus()+", "+
				"remarks = '"+ledgerDetailBean.getRemarks()+"', class_id = "+ledgerDetailBean.getClassId()+", "+
				"marks_obtain = "+ledgerDetailBean.getMarksObtain()+", total_marks = "+ledgerDetailBean.getTotalMarks()+", "+
				"rank = '"+ledgerDetailBean.getRank()+"', grade = '"+ledgerDetailBean.getGrade()+"', "+
				"date_of_declare = '"+ledgerDetailBean.getDateOfDeclare()+"' WHERE scheme_id="+schemeId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateLedgerDetailBySchemeId(beans.LedgerDetailBean ledgerDetailBean, int schemeId) method

	public static int updateMarksheetByMarsheetId(beans.MarksheetBean marksheetBean, int marsheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET exam_list = "+marksheetBean.getExamList()+", "+
				"roll_no = "+marksheetBean.getRollNo()+", seat_no = "+marksheetBean.getSeatNo()+", "+
				"total_marks = "+marksheetBean.getTotalMarks()+", obtain_marks = "+marksheetBean.getObtainMarks()+", "+
				"percentage = "+marksheetBean.getPercentage()+", grade = '"+marksheetBean.getGrade()+"', "+
				"rank = '"+marksheetBean.getRank()+"', marks_in_words = '"+marksheetBean.getMarksInWords()+"', "+
				"position = '"+marksheetBean.getPosition()+"', total_days = "+marksheetBean.getTotalDays()+", "+
				"present_days = "+marksheetBean.getPresentDays()+", status = "+marksheetBean.getStatus()+", "+
				"remarks = '"+marksheetBean.getRemarks()+"', class_id = "+marksheetBean.getClassId()+", "+
				"term = "+marksheetBean.getTerm()+", date_of_declaration = '"+marksheetBean.getDateOfDeclaration()+"' WHERE marsheet_id="+marsheetId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateMarksheetByMarsheetId(beans.MarksheetBean marksheetBean, int marsheetId) method

	public static int updateMarksheetByExamList(beans.MarksheetBean marksheetBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet "+
				"SET roll_no = "+marksheetBean.getRollNo()+", SET seat_no = "+marksheetBean.getSeatNo()+", "+
				"SET total_marks = "+marksheetBean.getTotalMarks()+", SET obtain_marks = "+marksheetBean.getObtainMarks()+", "+
				"SET percentage = "+marksheetBean.getPercentage()+", SET grade = '"+marksheetBean.getGrade()+"', "+
				"SET rank = '"+marksheetBean.getRank()+"', SET marks_in_words = '"+marksheetBean.getMarksInWords()+"', "+
				"SET position = '"+marksheetBean.getPosition()+"', SET total_days = "+marksheetBean.getTotalDays()+", "+
				"SET present_days = "+marksheetBean.getPresentDays()+", SET status = "+marksheetBean.getStatus()+", "+
				"SET remarks = '"+marksheetBean.getRemarks()+"', SET class_id = "+marksheetBean.getClassId()+", "+
				"SET term = "+marksheetBean.getTerm()+", SET date_of_declaration = '"+marksheetBean.getDateOfDeclaration()+"',  WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateMarksheetByExamList(beans.MarksheetBean marksheetBean, int examList) method

	public static int updateMarksheetByRollNo(beans.MarksheetBean marksheetBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET exam_list = "+marksheetBean.getExamList()+", SET seat_no = "+marksheetBean.getSeatNo()+", "+
				"SET total_marks = "+marksheetBean.getTotalMarks()+", SET obtain_marks = "+marksheetBean.getObtainMarks()+", "+
				"SET percentage = "+marksheetBean.getPercentage()+", SET grade = '"+marksheetBean.getGrade()+"', "+
				"SET rank = '"+marksheetBean.getRank()+"', SET marks_in_words = '"+marksheetBean.getMarksInWords()+"', "+
				"SET position = '"+marksheetBean.getPosition()+"', SET total_days = "+marksheetBean.getTotalDays()+", "+
				"SET present_days = "+marksheetBean.getPresentDays()+", SET status = "+marksheetBean.getStatus()+", "+
				"SET remarks = '"+marksheetBean.getRemarks()+"', SET class_id = "+marksheetBean.getClassId()+", "+
				"SET term = "+marksheetBean.getTerm()+", SET date_of_declaration = '"+marksheetBean.getDateOfDeclaration()+"',  WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateMarksheetByRollNo(beans.MarksheetBean marksheetBean, int rollNo) method

	public static int updateMarksheetByClassId(beans.MarksheetBean marksheetBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET exam_list = "+marksheetBean.getExamList()+", "+
				"SET roll_no = "+marksheetBean.getRollNo()+", SET seat_no = "+marksheetBean.getSeatNo()+", "+
				"SET total_marks = "+marksheetBean.getTotalMarks()+", SET obtain_marks = "+marksheetBean.getObtainMarks()+", "+
				"SET percentage = "+marksheetBean.getPercentage()+", SET grade = '"+marksheetBean.getGrade()+"', "+
				"SET rank = '"+marksheetBean.getRank()+"', SET marks_in_words = '"+marksheetBean.getMarksInWords()+"', "+
				"SET position = '"+marksheetBean.getPosition()+"', SET total_days = "+marksheetBean.getTotalDays()+", "+
				"SET present_days = "+marksheetBean.getPresentDays()+", SET status = "+marksheetBean.getStatus()+", "+
				"SET remarks = '"+marksheetBean.getRemarks()+"', "+
				"SET term = "+marksheetBean.getTerm()+", SET date_of_declaration = '"+marksheetBean.getDateOfDeclaration()+"',  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateMarksheetByClassId(beans.MarksheetBean marksheetBean, int classId) method

	public static int updateMarksheetByTerm(beans.MarksheetBean marksheetBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET exam_list = "+marksheetBean.getExamList()+", "+
				"SET roll_no = "+marksheetBean.getRollNo()+", SET seat_no = "+marksheetBean.getSeatNo()+", "+
				"SET total_marks = "+marksheetBean.getTotalMarks()+", SET obtain_marks = "+marksheetBean.getObtainMarks()+", "+
				"SET percentage = "+marksheetBean.getPercentage()+", SET grade = '"+marksheetBean.getGrade()+"', "+
				"SET rank = '"+marksheetBean.getRank()+"', SET marks_in_words = '"+marksheetBean.getMarksInWords()+"', "+
				"SET position = '"+marksheetBean.getPosition()+"', SET total_days = "+marksheetBean.getTotalDays()+", "+
				"SET present_days = "+marksheetBean.getPresentDays()+", SET status = "+marksheetBean.getStatus()+", "+
				"SET remarks = '"+marksheetBean.getRemarks()+"', SET class_id = "+marksheetBean.getClassId()+", SET date_of_declaration = '"+marksheetBean.getDateOfDeclaration()+"',  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateMarksheetByTerm(beans.MarksheetBean marksheetBean, int term) method

	public static int updateSchemeByClassId(beans.SchemeBean schemeBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;
		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="UPDATE scheme SET max_marks = "+schemeBean.getMaxMarks()+", min_marks = "+schemeBean.getMinMarks()+", "+
				"remarks = '"+schemeBean.getRemarks()+"', status = "+schemeBean.getStatus()+", last_update = '"+schemeBean.getLastUpdate()+"', "+
				"scheme_name = '"+schemeBean.getSchemeName()+"' WHERE class_id="+classId+" AND scheme_id="+schemeBean.getSchemeId();
			mySqlSt.execute(query);
                        return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSchemeByClassId(beans.SchemeBean schemeBean, int classId) method

	public static int updateStudentClassByClassId(beans.StudentClassBean studentClassBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_class SET remarks = '"+studentClassBean.getRemarks()+"', "+
				"roll_no = "+studentClassBean.getRollNo()+", status = "+studentClassBean.getStatus()+" WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentClassByClassId(beans.StudentClassBean studentClassBean, int classId) method

	public static int updateStudentClassByRollNo(beans.StudentClassBean studentClassBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_class SET remarks = '"+studentClassBean.getRemarks()+"', SET class_id = "+studentClassBean.getClassId()+", SET status = "+studentClassBean.getStatus()+",  WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentClassByRollNo(beans.StudentClassBean studentClassBean, int rollNo) method

	public static int updateStudentRegistrationByRollNo(beans.StudentRegistrationBean studentRegistrationBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt = null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="UPDATE student SET student_name = '"+studentRegistrationBean.getStudentName()+"', father_name = '"+studentRegistrationBean.getFatherName()+"', "+
				"surname = '"+studentRegistrationBean.getSurname()+"', gender = '"+studentRegistrationBean.getGender()+"', "+
				"mother_name = '"+studentRegistrationBean.getMotherName()+"', date_of_birth = '"+studentRegistrationBean.getDateOfBirth()+"', "+
				"place_of_birth = '"+studentRegistrationBean.getPlaceOfBirth()+"', guardian_name = '"+studentRegistrationBean.getGuardianName()+"', "+
				"guardian_occupation = '"+studentRegistrationBean.getGuardianOccupation()+"', phone_no = '"+studentRegistrationBean.getPhoneNo()+"', "+
				"mobile_no = '"+studentRegistrationBean.getMobileNo()+"', class_admission = '"+studentRegistrationBean.getClassAdmission()+"', "+
				"last_attendent_school = '"+studentRegistrationBean.getLastAttendentSchool()+"', admission_date = '"+studentRegistrationBean.getAdmissionDate()+"', "+
				"admission_fees = "+studentRegistrationBean.getAdmissionFees()+", status = "+studentRegistrationBean.getStatus()+", "+
				"remarks = '"+studentRegistrationBean.getRemarks()+"', finger_template = '"+studentRegistrationBean.getFingerTemplate()+"', "+
				"finger_image = '"+studentRegistrationBean.getFingerImage()+"', profile_image = '"+studentRegistrationBean.getProfileImage()+"', "+
				"previous_roll_no = "+studentRegistrationBean.getPreviousRollNo()+", "+
				"entry_test_marks = "+studentRegistrationBean.getEntryTestMarks()+", entry_test_remarks = '"+studentRegistrationBean.getEntryTestRemarks()+"', "+
				"address = '"+studentRegistrationBean.getAddress()+"' WHERE roll_no="+rollNo+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentRegistrationByRollNo(beans.StudentRegistrationBean studentRegistrationBean, int rollNo) method

	public static int updateStudentTermByTerm(beans.StudentTermBean studentTermBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET roll_no = "+studentTermBean.getRollNo()+", "+
				"SET remarks = '"+studentTermBean.getRemarks()+"', SET class_id = "+studentTermBean.getClassId()+", "+
				"SET status = "+studentTermBean.getStatus()+",  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentTermByTerm(beans.StudentTermBean studentTermBean, int term) method

	public static int updateStudentTermByRollNo(beans.StudentTermBean studentTermBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET term = "+studentTermBean.getTerm()+", "+
				"SET remarks = '"+studentTermBean.getRemarks()+"', SET class_id = "+studentTermBean.getClassId()+", "+
				"SET status = "+studentTermBean.getStatus()+",  WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentTermByRollNo(beans.StudentTermBean studentTermBean, int rollNo) method

	public static int updateStudentTermByClassId(beans.StudentTermBean studentTermBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET term = "+studentTermBean.getTerm()+", SET roll_no = "+studentTermBean.getRollNo()+", "+
				"SET remarks = '"+studentTermBean.getRemarks()+"', "+
				"SET status = "+studentTermBean.getStatus()+",  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateStudentTermByClassId(beans.StudentTermBean studentTermBean, int classId) method

	public static int updateSubjectResultByCourseNo(beans.SubjectResultBean subjectResultBean, int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', SET marksheet_id = "+subjectResultBean.getMarksheetId()+", "+
				"SET class_id = "+subjectResultBean.getClassId()+", SET term = "+subjectResultBean.getTerm()+", "+
				"SET exam_list = "+subjectResultBean.getExamList()+", SET roll_no = "+subjectResultBean.getRollNo()+",  WHERE course_no="+courseNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByCourseNo(beans.SubjectResultBean subjectResultBean, int courseNo) method

	public static int updateSubjectResultByMarksheetId(beans.SubjectResultBean subjectResultBean, int marksheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET course_no = "+subjectResultBean.getCourseNo()+", SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', "+
				"SET class_id = "+subjectResultBean.getClassId()+", SET term = "+subjectResultBean.getTerm()+", "+
				"SET exam_list = "+subjectResultBean.getExamList()+", SET roll_no = "+subjectResultBean.getRollNo()+",  WHERE marksheet_id="+marksheetId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByMarksheetId(beans.SubjectResultBean subjectResultBean, int marksheetId) method

	public static int updateSubjectResultByClassId(beans.SubjectResultBean subjectResultBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET course_no = "+subjectResultBean.getCourseNo()+", SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', SET marksheet_id = "+subjectResultBean.getMarksheetId()+", SET term = "+subjectResultBean.getTerm()+", "+
				"SET exam_list = "+subjectResultBean.getExamList()+", SET roll_no = "+subjectResultBean.getRollNo()+",  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByClassId(beans.SubjectResultBean subjectResultBean, int classId) method

	public static int updateSubjectResultByTerm(beans.SubjectResultBean subjectResultBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET course_no = "+subjectResultBean.getCourseNo()+", SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', SET marksheet_id = "+subjectResultBean.getMarksheetId()+", "+
				"SET class_id = "+subjectResultBean.getClassId()+", "+
				"SET exam_list = "+subjectResultBean.getExamList()+", SET roll_no = "+subjectResultBean.getRollNo()+",  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByTerm(beans.SubjectResultBean subjectResultBean, int term) method

	public static int updateSubjectResultByExamList(beans.SubjectResultBean subjectResultBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET course_no = "+subjectResultBean.getCourseNo()+", SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', SET marksheet_id = "+subjectResultBean.getMarksheetId()+", "+
				"SET class_id = "+subjectResultBean.getClassId()+", SET term = "+subjectResultBean.getTerm()+", SET roll_no = "+subjectResultBean.getRollNo()+",  WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByExamList(beans.SubjectResultBean subjectResultBean, int examList) method

	public static int updateSubjectResultByRollNo(beans.SubjectResultBean subjectResultBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET course_no = "+subjectResultBean.getCourseNo()+", SET obtained_marks = "+subjectResultBean.getObtainedMarks()+", "+
				"SET max_marks = "+subjectResultBean.getMaxMarks()+", SET min_marks = "+subjectResultBean.getMinMarks()+", "+
				"SET percentage = "+subjectResultBean.getPercentage()+", SET status = "+subjectResultBean.getStatus()+", "+
				"SET remarks = '"+subjectResultBean.getRemarks()+"', SET marksheet_id = "+subjectResultBean.getMarksheetId()+", "+
				"SET class_id = "+subjectResultBean.getClassId()+", SET term = "+subjectResultBean.getTerm()+", "+
				"SET exam_list = "+subjectResultBean.getExamList()+",  WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateSubjectResultByRollNo(beans.SubjectResultBean subjectResultBean, int rollNo) method

	public static int updateTermByClassId(beans.TermBean termBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE term SET remarks = '"+termBean.getRemarks()+"', "+
				"SET term = "+termBean.getTerm()+", SET term_month = "+termBean.getTermMonth()+", "+
				"SET status = "+termBean.getStatus()+",  WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateTermByClassId(beans.TermBean termBean, int classId) method

	public static int updateTermByTerm(beans.TermBean termBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE term SET class_id = "+termBean.getClassId()+", SET remarks = '"+termBean.getRemarks()+"', SET term_month = "+termBean.getTermMonth()+", "+
				"SET status = "+termBean.getStatus()+",  WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of updateTermByTerm(beans.TermBean termBean, int term) method
	
	/*********************************************************************************************************************************/	 

	/***************************************************  StatusDelete Methods *******************************************************/		
    public static int deleteStatusAttendanceByTerm(beans.AttendanceBean attendanceBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceByTerm(beans.AttendanceBean attendanceBean, int term) method

	public static int deleteStatusAttendanceByClassId(beans.AttendanceBean attendanceBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceByClassId(beans.AttendanceBean attendanceBean, int classId) method

	public static int deleteStatusAttendanceByAttendanceDate(beans.AttendanceBean attendanceBean, java.sql.Timestamp attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance SET status=0 WHERE attendance_date='"+attendanceDate+"';";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceByAttendanceDate(beans.AttendanceBean attendanceBean, java.sql.Timestamp attendanceDate) method

	public static int deleteStatusAttendanceDetailByClassId(beans.AttendanceDetailBean attendanceDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceDetailByClassId(beans.AttendanceDetailBean attendanceDetailBean, int classId) method

	public static int deleteStatusAttendanceDetailByRollNo(beans.AttendanceDetailBean attendanceDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceDetailByRollNo(beans.AttendanceDetailBean attendanceDetailBean, int rollNo) method

	public static int deleteStatusAttendanceDetailByTerm(beans.AttendanceDetailBean attendanceDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceDetailByTerm(beans.AttendanceDetailBean attendanceDetailBean, int term) method

	public static int deleteStatusAttendanceDetailByAttendanceDate(beans.AttendanceDetailBean attendanceDetailBean, java.sql.Timestamp attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE attendance_detail SET status=0 WHERE attendance_date='"+attendanceDate+"';";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusAttendanceDetailByAttendanceDate(beans.AttendanceDetailBean attendanceDetailBean, java.sql.Timestamp attendanceDate) method

	public static int deleteStatusClassesByClassId(beans.ClassesBean classesBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE classes SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusClassesByClassId(beans.ClassesBean classesBean, int classId) method

	public static int deleteStatusCourseByCourseNo(beans.CourseBean courseBean, int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE course SET status=0 WHERE course_no="+courseNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusCourseByCourseNo(beans.CourseBean courseBean, int courseNo) method

	public static int deleteStatusCourseByClassId(beans.CourseBean courseBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE course SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusCourseByClassId(beans.CourseBean courseBean, int classId) method

	public static int deleteStatusExamListByExamList(beans.ExamListBean examListBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list SET status=0 WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListByExamList(beans.ExamListBean examListBean, int examList) method

	public static int deleteStatusExamListByClassId(beans.ExamListBean examListBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListByClassId(beans.ExamListBean examListBean, int classId) method

	public static int deleteStatusExamListByTerm(beans.ExamListBean examListBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListByTerm(beans.ExamListBean examListBean, int term) method

	public static int deleteStatusExamListDetailByTerm(beans.ExamListDetailBean examListDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListDetailByTerm(beans.ExamListDetailBean examListDetailBean, int term) method

	public static int deleteStatusExamListDetailByExamList(beans.ExamListDetailBean examListDetailBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET status=0 WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListDetailByExamList(beans.ExamListDetailBean examListDetailBean, int examList) method

	public static int deleteStatusExamListDetailByRollNo(beans.ExamListDetailBean examListDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListDetailByRollNo(beans.ExamListDetailBean examListDetailBean, int rollNo) method

	public static int deleteStatusExamListDetailByClassId(beans.ExamListDetailBean examListDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE exam_list_detail SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusExamListDetailByClassId(beans.ExamListDetailBean examListDetailBean, int classId) method

	public static int deleteStatusLedgerDetailByTerm(beans.LedgerDetailBean ledgerDetailBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusLedgerDetailByTerm(beans.LedgerDetailBean ledgerDetailBean, int term) method

	public static int deleteStatusLedgerDetailBySeatList(beans.LedgerDetailBean ledgerDetailBean, int seatList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET status=0 WHERE seat_list="+seatList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusLedgerDetailBySeatList(beans.LedgerDetailBean ledgerDetailBean, int seatList) method

	public static int deleteStatusLedgerDetailByRollNo(beans.LedgerDetailBean ledgerDetailBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusLedgerDetailByRollNo(beans.LedgerDetailBean ledgerDetailBean, int rollNo) method

	public static int deleteStatusLedgerDetailByClassId(beans.LedgerDetailBean ledgerDetailBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusLedgerDetailByClassId(beans.LedgerDetailBean ledgerDetailBean, int classId) method

	public static int deleteStatusLedgerDetailBySchemeId(beans.LedgerDetailBean ledgerDetailBean, int schemeId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE ledger_detail SET status=0 WHERE scheme_id="+schemeId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusLedgerDetailBySchemeId(beans.LedgerDetailBean ledgerDetailBean, int schemeId) method

	public static int deleteStatusMarksheetByMarsheetId(beans.MarksheetBean marksheetBean, int marsheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET status=0 WHERE marsheet_id="+marsheetId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusMarksheetByMarsheetId(beans.MarksheetBean marksheetBean, int marsheetId) method

	public static int deleteStatusMarksheetByExamList(beans.MarksheetBean marksheetBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET status=0 WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusMarksheetByExamList(beans.MarksheetBean marksheetBean, int examList) method

	public static int deleteStatusMarksheetByRollNo(beans.MarksheetBean marksheetBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusMarksheetByRollNo(beans.MarksheetBean marksheetBean, int rollNo) method

	public static int deleteStatusMarksheetByClassId(beans.MarksheetBean marksheetBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusMarksheetByClassId(beans.MarksheetBean marksheetBean, int classId) method

	public static int deleteStatusMarksheetByTerm(beans.MarksheetBean marksheetBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE marksheet SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusMarksheetByTerm(beans.MarksheetBean marksheetBean, int term) method

	public static int deleteStatusSchemeByClassId(beans.SchemeBean schemeBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE scheme SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSchemeByClassId(beans.SchemeBean schemeBean, int classId) method

	public static int deleteStatusStudentClassByClassId(beans.StudentClassBean studentClassBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_class SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentClassByClassId(beans.StudentClassBean studentClassBean, int classId) method

	public static int deleteStatusStudentClassByRollNo(beans.StudentClassBean studentClassBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_class SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentClassByRollNo(beans.StudentClassBean studentClassBean, int rollNo) method

	public static int deleteStatusStudentRegistrationByRollNo(beans.StudentRegistrationBean studentRegistrationBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_registration SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentRegistrationByRollNo(beans.StudentRegistrationBean studentRegistrationBean, int rollNo) method

	public static int deleteStatusStudentTermByTerm(beans.StudentTermBean studentTermBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentTermByTerm(beans.StudentTermBean studentTermBean, int term) method

	public static int deleteStatusStudentTermByRollNo(beans.StudentTermBean studentTermBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentTermByRollNo(beans.StudentTermBean studentTermBean, int rollNo) method

	public static int deleteStatusStudentTermByClassId(beans.StudentTermBean studentTermBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE student_term SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusStudentTermByClassId(beans.StudentTermBean studentTermBean, int classId) method

	public static int deleteStatusSubjectResultByCourseNo(beans.SubjectResultBean subjectResultBean, int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE course_no="+courseNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByCourseNo(beans.SubjectResultBean subjectResultBean, int courseNo) method

	public static int deleteStatusSubjectResultByMarksheetId(beans.SubjectResultBean subjectResultBean, int marksheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE marksheet_id="+marksheetId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByMarksheetId(beans.SubjectResultBean subjectResultBean, int marksheetId) method

	public static int deleteStatusSubjectResultByClassId(beans.SubjectResultBean subjectResultBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByClassId(beans.SubjectResultBean subjectResultBean, int classId) method

	public static int deleteStatusSubjectResultByTerm(beans.SubjectResultBean subjectResultBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByTerm(beans.SubjectResultBean subjectResultBean, int term) method

	public static int deleteStatusSubjectResultByExamList(beans.SubjectResultBean subjectResultBean, int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByExamList(beans.SubjectResultBean subjectResultBean, int examList) method

	public static int deleteStatusSubjectResultByRollNo(beans.SubjectResultBean subjectResultBean, int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE subject_result SET status=0 WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusSubjectResultByRollNo(beans.SubjectResultBean subjectResultBean, int rollNo) method

	public static int deleteStatusTermByClassId(beans.TermBean termBean, int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE term SET status=0 WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusTermByClassId(beans.TermBean termBean, int classId) method

	public static int deleteStatusTermByTerm(beans.TermBean termBean, int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="UPDATE term SET status=0 WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStatusTermByTerm(beans.TermBean termBean, int term) method
	
	/*********************************************************************************************************************************/	 
	
	/******************************************************* Delete Methods **********************************************************/		
    public static int deleteAttendanceByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM attendance WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceByTerm(int term) method

	public static int deleteAttendanceByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM attendance WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceByClassId(int classId) method

	public static int deleteAttendanceByAttendanceDate(int schemeId, String attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
                        mySqlSt=mySqlCon.createStatement();
			st=con.createStatement();
			String query="DELETE FROM attendance WHERE  scheme_id ="+schemeId+" And attendance_date='"+attendanceDate+"';";
                        System.out.println(query);
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceByAttendanceDate(java.sql.Timestamp attendanceDate) method

	public static int deleteAttendanceDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM attendance_detail WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceDetailByClassId(int classId) method

	public static int deleteAttendanceDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM attendance_detail WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceDetailByRollNo(int rollNo) method

	public static int deleteAttendanceDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM attendance_detail WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteAttendanceDetailByTerm(int term) method

	public static int deleteAttendanceDetailByAttendanceDate(int schemeId,String attendanceDate)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt = null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM attendance_detail WHERE  scheme_id= "+schemeId+" And attendance_date='"+attendanceDate+"'";
                        System.out.println(query);
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
		if(st!=null)st.close();
		}
	}//End of deleteAttendanceDetailByAttendanceDate(java.sql.Timestamp attendanceDate) method

	public static int deleteClassesByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;
		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM classes WHERE class_id="+classId+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteClassesByClassId(int classId) method

	public static int deleteCourseByCourseNo(int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
                        mySqlSt=mySqlCon.createStatement();
			st=con.createStatement();
			String query="DELETE FROM course WHERE course_no="+courseNo+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteCourseByCourseNo(int courseNo) method

	public static int deleteCourseByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM course WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteCourseByClassId(int classId) method

	public static int deleteExamListByExamList (int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM exam_list WHERE exam_list="+examList+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListByExamList(int examList) method

	public static int deleteExamListDetail(ExamListDetailBean  listDetailBean)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM exam_list_detail where class_id="+listDetailBean.getClassId()+" And scheme_id="+listDetailBean.getSchemeId()+
                       " And term="+listDetailBean.getTerm()+" And exam_list="+listDetailBean.getExamList()+" And roll_no="+listDetailBean.getRollNo();
                        System.out.println(query);
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListByClassId(int classId) method

	public static int deleteExamListByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM exam_list WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListByTerm(int term) method

	public static int deleteExamListDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM exam_list_detail WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListDetailByTerm(int term) method

	public static int deleteExamListDetailByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM exam_list_detail WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListDetailByExamList(int examList) method

	public static int deleteExamListDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM exam_list_detail WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListDetailByRollNo(int rollNo) method

	public static int deleteExamListDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM exam_list_detail WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteExamListDetailByClassId(int classId) method

	public static int deleteLedgerDetailByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM ledger_detail WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteLedgerDetailByTerm(int term) method

	public static int deleteLedgerDetailBySeatList(int seatList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM ledger_detail WHERE seat_list="+seatList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteLedgerDetailBySeatList(int seatList) method

	public static int deleteLedgerDetailByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM ledger_detail WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteLedgerDetailByRollNo(int rollNo) method

	public static int deleteLedgerDetailByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM ledger_detail WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteLedgerDetailByClassId(int classId) method

	public static int deleteLedgerDetailBySchemeId(int schemeId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM ledger_detail WHERE scheme_id="+schemeId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteLedgerDetailBySchemeId(int schemeId) method

	public static int deleteMarksheetByMarsheetId(int marsheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM marksheet WHERE marksheet_id="+marsheetId+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteMarksheetByMarsheetId(int marsheetId) method

	public static int deleteMarksheetByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM marksheet WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteMarksheetByExamList(int examList) method

	public static int deleteMarksheetByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM marksheet WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteMarksheetByRollNo(int rollNo) method

	public static int deleteMarksheetByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM marksheet WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteMarksheetByClassId(int classId) method

	public static int deleteMarksheetByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM marksheet WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteMarksheetByTerm(int term) method

	public static int deleteSchemeBySchemeId(int schemeId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM scheme WHERE scheme_id="+schemeId+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSchemeByClassId(int classId) method

	public static int deleteStudentClassByClassId(int schemeId,int roll_no)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM student_class WHERE scheme_id="+schemeId+" And roll_no="+roll_no+";";
                        mySqlSt.executeUpdate(query);
 			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStudentClassByClassId(int classId) method

	public static int deleteStudentClassByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM student_class WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStudentClassByRollNo(int rollNo) method

	public static int deleteStudentRegistrationByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM student WHERE roll_no="+rollNo+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStudentRegistrationByRollNo(int rollNo) method

	public static int deleteStudentTermByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM student_term WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStudentTermByTerm(int term) method

	public static int deleteStudentTermByRollNo(int schemeId,int term,int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM student_term WHERE roll_no="+rollNo+" And term="+term+" And scheme_id="+schemeId;
                        System.out.println(query);
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query); 
		}finally{
			st.close();
		}
	}//End of deleteStudentTermByRollNo(int rollNo) method

	public static int deleteStudentTermByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM student_term WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteStudentTermByClassId(int classId) method

	public static int deleteSubjectResultByCourseNo(int courseNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM subject_result WHERE course_no="+courseNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByCourseNo(int courseNo) method

	public static int deleteSubjectResultByMarksheetId(int marksheetId)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM subject_result WHERE marksheet_id="+marksheetId+";";
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByMarksheetId(int marksheetId) method

	public static int deleteSubjectResultByClassId(int classId)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM subject_result WHERE class_id="+classId+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByClassId(int classId) method

	public static int deleteSubjectResultByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM subject_result WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByTerm(int term) method

	public static int deleteSubjectResultByExamList(int examList)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM subject_result WHERE exam_list="+examList+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByExamList(int examList) method

	public static int deleteSubjectResultByRollNo(int rollNo)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM subject_result WHERE roll_no="+rollNo+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteSubjectResultByRollNo(int rollNo) method

	public static int deleteTermBySchemeId(int schemeId,int term)throws java.sql.SQLException{
		java.sql.Statement st = null;
                java.sql.Statement mySqlSt=null;

		try{
			st=con.createStatement();
                        mySqlSt=mySqlCon.createStatement();
			String query="DELETE FROM term WHERE  scheme_id="+schemeId+" And term="+term;
                        mySqlSt.executeUpdate(query);
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteTermByClassId(int classId) method

	public static int deleteTermByTerm(int term)throws java.sql.SQLException{
		java.sql.Statement st = null;

		try{
			st=con.createStatement();
			String query="DELETE FROM term WHERE term="+term+";";
			return st.executeUpdate(query);
		}finally{
			st.close();
		}
	}//End of deleteTermByTerm(int term) method
        
  //**************CODE BY DHANI********************      
        
   public static Vector getAllScheme()throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SchemeBean schemeBean = null;
                Vector v=null;

		try{
			st=con.createStatement();
			String query="SELECT * FROM scheme WHERE status=1 order by class_id";
                        System.out.println(query);
			st.execute(query);
			rs=st.getResultSet();
                        v=new Vector();
 

			while(rs.next()){
                                schemeBean=new beans.SchemeBean();
                                schemeBean.setSchemeId(rs.getInt("scheme_id"));
                                schemeBean.setClassId(rs.getInt("class_id"));
                                schemeBean.setSchemeName(rs.getString("scheme_name"));
                                schemeBean.setMaxMarks(rs.getInt("max_marks"));
                                schemeBean.setMinMarks(rs.getInt("min_marks"));
                                schemeBean.setStatus(rs.getInt("status"));
                                schemeBean.setRemarks(rs.getString("remarks"));
                                v.addElement(schemeBean);
			}

			return v;
		}finally{
			st.close();
			rs.close();
		}
	}
   public static boolean checkStudentInExamListDetails(ExamListDetailBean listDetailBean)throws Exception{
       String query="select * from exam_list_detail where class_id="+listDetailBean.getClassId()+" And scheme_id="+listDetailBean.getSchemeId()+
               " And term="+listDetailBean.getTerm()+" And exam_list="+listDetailBean.getExamList()+" And roll_no="+listDetailBean.getRollNo();
         java.sql.Statement st=null;
         java.sql.ResultSet rs=null;
         System.out.println(query);
         try{
             st=con.createStatement();
             st.execute(query);
             rs=st.getResultSet();
          
             
             if(rs.next()){
                 System.out.println(rs.getString("class_id"));
                 System.out.println(rs.getString("term"));
                 System.out.println(rs.getString("roll_no"));
                 return true;
                 
             }else
                 return false;
          
         }finally{
             st.close();
             rs.close();
         }
                      
   }
   public static boolean checkStudentClass(beans.StudentClassBean stdClassBean) throws Exception{
       String query="select * from student_class where scheme_id="+stdClassBean.getSchemeId()+" And roll_no="+stdClassBean.getRollNo();
       java.sql.Statement st=null;
       java.sql.ResultSet rs=null;
       System.out.println(query);
       try{
           st=con.createStatement();
           st.execute(query);
           rs=st.getResultSet();
           
           if(rs.next()){
               System.out.println(rs.getInt("roll_no"));
               return true;
           }
           return false;
       }finally{
           if(st!=null)st.close();
           if(rs!=null)rs.close();
       }
   }
   public static  int getRecentMarksheetId() throws Exception{
     
       java.sql.Statement st=null;
       java.sql.ResultSet rs=null;
       
      try{
         String query="SELECT MAX (marksheet_id) as marksheet_id from marksheet";
          st=con.createStatement();
          st.execute(query);
          rs=st.getResultSet();
        System.out.println(query);
          if(rs.next()){
              return rs.getInt("marksheet_id");
          }
      }finally{
          st.close();
          rs.close();
      }
            return 0;
       
   }
   
   
   public static beans.SchemeBean getSchemeBySchemeId(int scheme_Id)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SchemeBean schemeBean = null;
                String query="SELECT * FROM scheme WHERE status=1 AND scheme_id="+scheme_Id+";";
                System.out.println(query);

		try{
			st=con.createStatement();
			
			st.execute(query);
			rs=st.getResultSet();
 

			if(rs.next()){
				schemeBean = new beans.SchemeBean();
                                schemeBean.setClassId(rs.getInt("class_id"));
                                schemeBean.setSchemeId(rs.getInt("scheme_id"));
                                schemeBean.setSchemeName(rs.getString("scheme_name"));
                                schemeBean.setStatus(rs.getInt("status"));
				
			}

			return schemeBean;
		}finally{
			st.close();
			rs.close();
		}
	}//End of getExamListByExamList(int examList) method
   
       
        public static beans.SubjectResultBean getSubjectInf(int marksheet_id)throws java.sql.SQLException{
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		beans.SubjectResultBean subjectResultBean = null;
                String query="SELECT SUM(max_marks) as max, SUM(min_marks) as min, " +
                "SUM(obtained_marks) as obtain , ROUND(SUM(obtained_marks)*100/SUM(max_marks),2) as percentage " +
                "FROM subject_result where marksheet_id="+marksheet_id;
                System.out.println(query);

		try{
			st=con.createStatement();
              
                        st.execute(query);
                        rs=st.getResultSet();

			if(rs.next()){
                            subjectResultBean=new beans.SubjectResultBean();
                            subjectResultBean.setMinMarks(rs.getInt("min"));
                            subjectResultBean.setMaxMarks(rs.getInt("max"));
                            subjectResultBean.setObtainedMarks(rs.getInt("obtain"));
                            subjectResultBean.setPercentage(rs.getInt("percentage"));  
			}

			return subjectResultBean;
		}finally{
			if(st!=null)st.close();
			if(rs!=null)rs.close();
		}
	}//End of getExamListByExamList(int examList) method

	
	/*********************************************************************************************************************************/
}//End of DatabaseManager class
