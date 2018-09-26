package com.example.demo.exceptions;

import com.example.demo.models.Transaction;

/**
 * @author a.boumera
 * 
 */  
public class UnauthorizedNullTransaction extends RuntimeException {
	
	private static final String MESSAGE = "Transaction passed to the method must be not null";

    public UnauthorizedNullTransaction() {
        super(MESSAGE);
    }
}
