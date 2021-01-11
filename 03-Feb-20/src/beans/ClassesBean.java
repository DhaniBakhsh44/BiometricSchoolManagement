package beans;

/**
	Title: 			ClassesBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class ClassesBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int classId;
	private String className;
	private String status;
	private String remarks;
	private String classNo;
	private String section;		
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setClassName(String className){
		this.className = className;
	}//End of setClassName(String) method

	public void setStatus(String status){
		this.status = status;
	}//End of setStatus(String) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setClassNo(String classNo){
		this.classNo = classNo;
	}//End of setClassNo(String) method

	public void setSection(String section){
		this.section = section;
	}//End of setSection(String) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public String getClassName(){
		return className;
	}//End of getClassName() method

	public String getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public String getClassNo(){
		return classNo;
	}//End of getClassNo() method

	public String getSection(){
		return section;
	}//End of getSection() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return className+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of ClassesBean
