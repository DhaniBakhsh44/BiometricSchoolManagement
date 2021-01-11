package beans;

/**
	Title: 			MarksheetBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class MarksheetBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int marsheetId;
	private int examList;
	private int rollNo;
	private int seatNo;
	private int totalMarks;
	private int obtainMarks;
	private int percentage;
	private String grade;
	private String rank;
	private String marksInWords;
	private String position;
	private int totalDays;
	private int presentDays;
	private int status;
	private String remarks;
	private int classId;
	private int term;
	private java.sql.Timestamp dateOfDeclaration;
        private int schemeId;
        
    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
    
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setMarsheetId(int marsheetId){
		this.marsheetId = marsheetId;
	}//End of setMarsheetId(int) method

	public void setExamList(int examList){
		this.examList = examList;
	}//End of setExamList(int) method

	public void setRollNo(int rollNo){
		this.rollNo = rollNo;
	}//End of setRollNo(int) method

	public void setSeatNo(int seatNo){
		this.seatNo = seatNo;
	}//End of setSeatNo(int) method

	public void setTotalMarks(int totalMarks){
		this.totalMarks = totalMarks;
	}//End of setTotalMarks(int) method

	public void setObtainMarks(int obtainMarks){
		this.obtainMarks = obtainMarks;
	}//End of setObtainMarks(int) method

	public void setPercentage(int percentage){
		this.percentage = percentage;
	}//End of setPercentage(int) method

	public void setGrade(String grade){
		this.grade = grade;
	}//End of setGrade(String) method

	public void setRank(String rank){
		this.rank = rank;
	}//End of setRank(String) method

	public void setMarksInWords(String marksInWords){
		this.marksInWords = marksInWords;
	}//End of setMarksInWords(String) method

	public void setPosition(String position){
		this.position = position;
	}//End of setPosition(String) method

	public void setTotalDays(int totalDays){
		this.totalDays = totalDays;
	}//End of setTotalDays(int) method

	public void setPresentDays(int presentDays){
		this.presentDays = presentDays;
	}//End of setPresentDays(int) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setDateOfDeclaration(java.sql.Timestamp dateOfDeclaration){
		this.dateOfDeclaration = dateOfDeclaration;
	}//End of setDateOfDeclaration(java.sql.Timestamp) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getMarsheetId(){
		return marsheetId;
	}//End of getMarsheetId() method

	public int getExamList(){
		return examList;
	}//End of getExamList() method

	public int getRollNo(){
		return rollNo;
	}//End of getRollNo() method

	public int getSeatNo(){
		return seatNo;
	}//End of getSeatNo() method

	public int getTotalMarks(){
		return totalMarks;
	}//End of getTotalMarks() method

	public int getObtainMarks(){
		return obtainMarks;
	}//End of getObtainMarks() method

	public int getPercentage(){
		return percentage;
	}//End of getPercentage() method

	public String getGrade(){
		return grade;
	}//End of getGrade() method

	public String getRank(){
		return rank;
	}//End of getRank() method

	public String getMarksInWords(){
		return marksInWords;
	}//End of getMarksInWords() method

	public String getPosition(){
		return position;
	}//End of getPosition() method

	public int getTotalDays(){
		return totalDays;
	}//End of getTotalDays() method

	public int getPresentDays(){
		return presentDays;
	}//End of getPresentDays() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public int getTerm(){
		return term;
	}//End of getTerm() method

	public java.sql.Timestamp getDateOfDeclaration(){
		return dateOfDeclaration;
	}//End of getDateOfDeclaration() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return seatNo+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of MarksheetBean
