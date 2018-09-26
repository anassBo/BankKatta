package com.example.demo.constant; 


/**
 * @author a.boumera
 * 
 */  
public enum Currency
{

	EUR("EUR"),
	US("US");


	private String code;


	/** Constructeur
	 * @param code
	 */
	private Currency(String code) {
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
