package beans;

/**
	Title: 			AttendanceBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class AttendanceBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private String remarks;
	private int status;
	private int term;
	private int classId;
	private String attendanceDate;	

    public int getSchemId() {
        return schemId;
    }

    public void setSchemId(int schemId) {
        this.schemId = schemId;
    }
        private int schemId;
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setAttendanceDate(String attendanceDate){
		this.attendanceDate = attendanceDate;
	}//End of setAttendanceDate(java.sql.Timestamp) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public int getTerm(){
		return term;
	}//End of getTerm() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public String getAttendanceDate(){
		return attendanceDate;
	}//End of getAttendanceDate() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return ""+attendanceDate;
	}
	
	/***********************************************************************************************/	 	 

}//End of AttendanceBean
