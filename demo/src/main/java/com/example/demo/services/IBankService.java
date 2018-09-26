/**
 * 
 */
package com.example.demo.services;

import com.example.demo.models.Transaction;

/**
 * @author a.boumera
 *
 */
public interface IBankService {

	boolean deposit(Transaction transaction);

	boolean withdrawl(Transaction transaction);

	String printStatement(String accountId);



	/*
	 * this extra method should be removed when enabling the persistence: it exists here to simulate the Database filling
	 */
	public void AddToCache();
}
