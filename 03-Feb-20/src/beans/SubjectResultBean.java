package beans;

/**
	Title: 			SubjectResultBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class SubjectResultBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int courseNo;
	private int obtainedMarks;
	private int maxMarks;
	private int minMarks;
	private int percentage;
	private int status;
	private String remarks;
	private int marksheetId;
	private int classId;
	private int term;
	private int examList;
	private int rollNo;

    public int getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(int scheme_id) {
        this.scheme_id = scheme_id;
    }
        private int scheme_id;
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setCourseNo(int courseNo){
		this.courseNo = courseNo;
	}//End of setCourseNo(int) method

	public void setObtainedMarks(int obtainedMarks){
		this.obtainedMarks = obtainedMarks;
	}//End of setObtainedMarks(int) method

	public void setMaxMarks(int maxMarks){
		this.maxMarks = maxMarks;
	}//End of setMaxMarks(int) method

	public void setMinMarks(int minMarks){
		this.minMarks = minMarks;
	}//End of setMinMarks(int) method

	public void setPercentage(int percentage){
		this.percentage = percentage;
	}//End of setPercentage(int) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setMarksheetId(int marksheetId){
		this.marksheetId = marksheetId;
	}//End of setMarksheetId(int) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setExamList(int examList){
		this.examList = examList;
	}//End of setExamList(int) method

	public void setRollNo(int rollNo){
		this.rollNo = rollNo;
	}//End of setRollNo(int) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getCourseNo(){
		return courseNo;
	}//End of getCourseNo() method

	public int getObtainedMarks(){
		return obtainedMarks;
	}//End of getObtainedMarks() method

	public int getMaxMarks(){
		return maxMarks;
	}//End of getMaxMarks() method

	public int getMinMarks(){
		return minMarks;
	}//End of getMinMarks() method

	public int getPercentage(){
		return percentage;
	}//End of getPercentage() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getMarksheetId(){
		return marksheetId;
	}//End of getMarksheetId() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public int getTerm(){
		return term;
	}//End of getTerm() method

	public int getExamList(){
		return examList;
	}//End of getExamList() method

	public int getRollNo(){
		return rollNo;
	}//End of getRollNo() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return obtainedMarks+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of SubjectResultBean
