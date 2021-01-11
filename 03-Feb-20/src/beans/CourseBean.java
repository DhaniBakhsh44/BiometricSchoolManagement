package beans;

/**
	Title: 			CourseBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class CourseBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private String courseTitle;
	private int minMarks;
	private int maxMarks;
	private String subjectType;
	private int status;
	private String remarks;
	private int courseNo;
	private String courseKeyword;
	private int classId;
         private int schemeId;
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setCourseTitle(String courseTitle){
		this.courseTitle = courseTitle;
	}//End of setCourseTitle(String) method

	public void setMinMarks(int minMarks){
		this.minMarks = minMarks;
	}//End of setMinMarks(int) method

	public void setMaxMarks(int maxMarks){
		this.maxMarks = maxMarks;
	}//End of setMaxMarks(int) method

	public void setSubjectType(String subjectType){
		this.subjectType = subjectType;
	}//End of setSubjectType(String) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(String) method

	public void setCourseNo(int courseNo){
		this.courseNo = courseNo;
	}//End of setCourseNo(int) method

	public void setCourseKeyword(String courseKeyword){
		this.courseKeyword = courseKeyword;
	}//End of setCourseKeyword(String) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public String getCourseTitle(){
		return courseTitle;
	}//End of getCourseTitle() method

	public int getMinMarks(){
		return minMarks;
	}//End of getMinMarks() method

	public int getMaxMarks(){
		return maxMarks;
	}//End of getMaxMarks() method

	public String getSubjectType(){
		return subjectType;
	}//End of getSubjectType() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getCourseNo(){
		return courseNo;
	}//End of getCourseNo() method

	public String getCourseKeyword(){
		return courseKeyword;
	}//End of getCourseKeyword() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method
        
        public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return courseTitle+"";
	}
	
	/***********************************************************************************************/	 	 

}//End of CourseBean
