package beans;

/**
	Title: 			ExamListDetailBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class ExamListDetailBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int term;
	private int examList;
	private int rollNo;
	private int status;
	private int classId;
	private String remarks;	

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
        private int schemeId;
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setExamList(int examList){
		this.examList = examList;
	}//End of setExamList(int) method

	public void setRollNo(int rollNo){
		this.rollNo = rollNo;
	}//End of setRollNo(int) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getTerm(){
		return term;
	}//End of getTerm() method

	public int getExamList(){
		return examList;
	}//End of getExamList() method

	public int getRollNo(){
		return rollNo;
	}//End of getRollNo() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return status+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of ExamListDetailBean