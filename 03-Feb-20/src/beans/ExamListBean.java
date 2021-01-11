package beans;

/**
	Title: 			ExamListBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class ExamListBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int examList;
	private int classId;
	private int term;
	private String type;
	private int status;
	private String remarks;
	private int slYear;
	private int slMonth;

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
        private int schemeId;
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setExamList(int examList){
		this.examList = examList;
	}//End of setExamList(int) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setTerm(int term){
		this.term = term;
	}//End of setTerm(int) method

	public void setType(String type){
		this.type = type;
	}//End of setType(String) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setSlYear(int slYear){
		this.slYear = slYear;
	}//End of setSlYear(int) method

	public void setSlMonth(int slMonth){
		this.slMonth = slMonth;
	}//End of setSlMonth(int) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getExamList(){
		return examList;
	}//End of getExamList() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public int getTerm(){
		return term;
	}//End of getTerm() method

	public String getType(){
		return type;
	}//End of getType() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getSlYear(){
		return slYear;
	}//End of getSlYear() method

	public int getSlMonth(){
		return slMonth;
	}//End of getSlMonth() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return type+" "+slYear;
	}
	
	/***********************************************************************************************/	 	 

}//End of ExamListBean
