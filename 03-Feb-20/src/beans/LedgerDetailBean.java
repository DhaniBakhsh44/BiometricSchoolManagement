package beans;

/**
	Title: 			LedgerDetailBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class LedgerDetailBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int term;
	private int seatList;
	private int rollNo;
	private int status;
	private String remarks;
	private int classId;
	private int schemeId;
	private int seatNo;
	private int marksObtain;
	private int totalMarks;
	private String rank;
	private String grade;
	private java.sql.Timestamp dateOfDeclare;		
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setSeatList(int seatList){
		this.seatList = seatList;
	}//End of setSeatList(int) method

	public void setRollNo(int rollNo){
		this.rollNo = rollNo;
	}//End of setRollNo(int) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setSchemeId(int schemeId){
		this.schemeId = schemeId;
	}//End of setSchemeId(int) method

	public void setSeatNo(int seatNo){
		this.seatNo = seatNo;
	}//End of setSeatNo(int) method

	public void setMarksObtain(int marksObtain){
		this.marksObtain = marksObtain;
	}//End of setMarksObtain(int) method

	public void setTotalMarks(int totalMarks){
		this.totalMarks = totalMarks;
	}//End of setTotalMarks(int) method

	public void setRank(String rank){
		this.rank = rank;
	}//End of setRank(String) method

	public void setGrade(String grade){
		this.grade = grade;
	}//End of setGrade(String) method

	public void setDateOfDeclare(java.sql.Timestamp dateOfDeclare){
		this.dateOfDeclare = dateOfDeclare;
	}//End of setDateOfDeclare(java.sql.Timestamp) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getTerm(){
		return term;
	}//End of getTerm() method

	public int getSeatList(){
		return seatList;
	}//End of getSeatList() method

	public int getRollNo(){
		return rollNo;
	}//End of getRollNo() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public int getSchemeId(){
		return schemeId;
	}//End of getSchemeId() method

	public int getSeatNo(){
		return seatNo;
	}//End of getSeatNo() method

	public int getMarksObtain(){
		return marksObtain;
	}//End of getMarksObtain() method

	public int getTotalMarks(){
		return totalMarks;
	}//End of getTotalMarks() method

	public String getRank(){
		return rank;
	}//End of getRank() method

	public String getGrade(){
		return grade;
	}//End of getGrade() method

	public java.sql.Timestamp getDateOfDeclare(){
		return dateOfDeclare;
	}//End of getDateOfDeclare() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return status+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of LedgerDetailBean
