/**
 * 
 */
package com.example.demo.integration;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.services.IBankService;


/**
 * @author a.boumera
 *
 */
/*
 * this singleton component should not be present in the final version of a real app
 * it just simulates the database content it will be executed at startup
 */
@Component
public class BankStarter {
	@Autowired
    private IBankService bankService;
	
	@PostConstruct
    public void init() {
        bankService.addToCache();
    }
	
	
}
