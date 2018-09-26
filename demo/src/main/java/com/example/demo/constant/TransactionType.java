package com.example.demo.constant; 


/**
 * @author a.boumera
 * 
 */  
public enum TransactionType
{
	
	DEPOSIT("DEPOSIT"),
	WITHDRAWL("WITHDRAWL");
	
	
	private String code;
	
	
	/** Constructeur
	 * @param code
	 */
	private TransactionType(String code) {
		this.code =  code;
	}
	
	public String code() {		
		return code;
	}
	
	
	/** 
	* 
	*@return code
	*/
	public String toString() {
		return code;
	}

}
