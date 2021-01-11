package beans;

/**
	Title: 			SchemeBean.java
	Description:	Auto-Generated Model classes for View And Controller
	Contact:		0300-0090822
	Email:			younissabbasi@gmail.com	
	@Author:		Muhammad Younis Abbasi
 	
	Programmer: <It is a Machine which converts tea into code!!!
 */

public class SchemeBean{			
	
	/*******************************  Instance Variables Declaration *******************************/		
	private int maxMarks;
	private int minMarks;
	private String remarks;
	private int status;
	private int classId;

  
        private int schemeId;
	private String lastUpdate;
	private String schemeName;		
 	
	/***********************************************************************************************/	
    	
	/******************************************Setters**********************************************/		  
	public void setMaxMarks(int maxMarks){
		this.maxMarks = maxMarks;
	}//End of setMaxMarks(int) method

	public void setMinMarks(int minMarks){
		this.minMarks = minMarks;
	}//End of setMinMarks(int) method

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}//End of setRemarks(int) method

	public void setStatus(int status){
		this.status = status;
	}//End of setStatus(int) method

	public void setClassId(int classId){
		this.classId = classId;
	}//End of setClassId(int) method

	public void setLastUpdate(String  lastUpdate){
		this.lastUpdate = lastUpdate;
	}//End of setLastUpdate(java.sql.Timestamp) method

	public void setSchemeName(String schemeName){
		this.schemeName = schemeName;
	}//End of setSchemeName(String) method
        
        public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
    	
	/***********************************************************************************************/	 
    	
	/******************************************Getters**********************************************/		
	public int getMaxMarks(){
		return maxMarks;
	}//End of getMaxMarks() method

	public int getMinMarks(){
		return minMarks;
	}//End of getMinMarks() method

	public String getRemarks(){
		return remarks;
	}//End of getRemarks() method

	public int getStatus(){
		return status;
	}//End of getStatus() method

	public int getClassId(){
		return classId;
	}//End of getClassId() method

	public String getLastUpdate(){
		return lastUpdate;
	}//End of getLastUpdate() method

	public String getSchemeName(){
		return schemeName;
	}//End of getSchemeName() method
	
	/***********************************************************************************************/

	/************************************  toString Method ****************************************/		
	public String toString(){
		return schemeName;
	}
	
	/***********************************************************************************************/	 	 

}//End of SchemeBean
