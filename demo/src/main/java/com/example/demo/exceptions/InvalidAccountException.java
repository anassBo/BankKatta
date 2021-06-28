package com.example.demo.exceptions;

import com.example.demo.models.Transaction;

/**
 * @author a.boumera
 * 
 */  
public class InvalidAccountException extends RuntimeException {
	
	private static final String MESSAGE = "invalid account id %s";

    public InvalidAccountException(String accountId) {
        super(String.format(MESSAGE, accountId));
    }
}
