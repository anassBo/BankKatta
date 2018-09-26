package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.TransactionType;
import com.example.demo.exceptions.ValidationException;
import com.example.demo.models.Transaction;
import com.example.demo.services.IBankService;

/**
 * @author a.boumera
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DemoController {

	@Autowired
	IBankService bankService;

	@RequestMapping(value = "/deposit/{accountId}", method = RequestMethod.GET)
	public String deposit(@PathVariable("accountId") String accountId, @RequestParam(value="amount") Double amount) {
		if(accountId.chars().allMatch(Character::isDigit)) {
			Long longAccountId = Long.valueOf(accountId);
			Transaction tr = new Transaction();
			tr.setTransactionType(TransactionType.DEPOSIT);
			tr.setAccountId(longAccountId);
			tr.setAmount(amount);

			return bankService.deposit(tr)?"The amount "+amount+" was successfully credited to your account":"Failure";
		}
		throw new ValidationException("The account identifier must be numeric");
	}

	@RequestMapping(value = "/withdrawl/{accountId}", method = RequestMethod.GET)
	public String withdrawl(@PathVariable("accountId") Long accountId, @RequestParam(value="amount") Double amount) {
		Transaction tr = new Transaction();
		tr.setTransactionType(TransactionType.WITHDRAWL);
		tr.setAccountId(accountId);
		tr.setAmount(amount);

		return bankService.withdrawl(tr)?"The amount "+amount+" was successfully debited from your account":"Failure";
	}

	@RequestMapping(value = "/printStatement/{accountId}", method = RequestMethod.GET)
	public String printStatement(@PathVariable("accountId") Long accountId) {   	
		return bankService.printStatement(accountId.toString());
	}



}
