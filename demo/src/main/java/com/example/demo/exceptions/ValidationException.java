package com.example.demo.exceptions;

import com.example.demo.models.Transaction;

/**
 * @author a.boumera
 * 
 */  
public class ValidationException extends RuntimeException {
	

    public ValidationException(String msg) {
        super(msg);
    }
}
