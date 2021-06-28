/**
 * 
 */
package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.constant.TransactionType;
import com.example.demo.exceptions.UnauthorizedNullTransaction;
import com.example.demo.exceptions.InvalidAccountException;
import com.example.demo.models.Account;
import com.example.demo.models.Transaction;
import com.example.demo.services.IBankService;
import com.google.common.util.concurrent.AtomicDouble;

/**
 * @author a.boumera
 *
 */
@Service
public class BankServiceImpl implements IBankService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);

	//instead of DB persistence
	//key=accountId, value=balance
	//this map will be populated at the app start by the initComponent component
	private static Map<String, AtomicDouble> accountMap = new ConcurrentHashMap<>(); 

	//key=accountId, value=List<Transaction>
	private static Map<String, List<Transaction>> historyMap = new ConcurrentHashMap<>(); 

	@Override
	public boolean deposit(Transaction transaction) {
		boolean result=false;
		try {
			Objects.requireNonNull(transaction);
		}catch(NullPointerException ex) {
			throw new UnauthorizedNullTransaction();
		}

		Double amount = transaction.getAmount();
		if(transaction.getAccountId()!=null) {
			if(accountMap.containsKey(transaction.getAccountId().toString())) {
				//save new balance in accountMap
				Double newBalance = accountMap.get(transaction.getAccountId().toString()).addAndGet(amount);
				accountMap.put(transaction.getAccountId().toString(),new AtomicDouble(newBalance)) ;
			}else {
				throw new InvalidAccountException(transaction.getAccountId().toString());
			}
			if(historyMap.containsKey(transaction.getAccountId().toString())) {
				//save transaction in historyMap
				List<Transaction> transactionList = historyMap.get(transaction.getAccountId().toString()); 
				transaction.setTransactionType(TransactionType.DEPOSIT);
				//write the current balance at the date of this deposit
				transaction.setCurrentBalance(getAccount(transaction.getAccountId().toString()).getBalance());
				if(transactionList==null || transactionList.size()==0) {
					transactionList = new CopyOnWriteArrayList<>();
					transactionList.add(transaction);
					historyMap.put(transaction.getAccountId().toString(), transactionList);
				}else {
					transactionList.add(transaction);
				}
			}else {
				throw new InvalidAccountException(transaction.getAccountId().toString());
			}
		}

		for(Map.Entry m : historyMap.entrySet())
			((List<Transaction>) m.getValue()).stream().map(t->t.getCurrentBalance()).collect(Collectors.toList()).forEach(System.out::println);

		result = true;
		return result;
	}

	/*
	 * balance less than 0 are also allowed here in the withdrawl method it is a choice that can be prohibited later
	 * (non-Javadoc)
	 * @see com.example.demo.services.IBankService#withdrawl(com.example.demo.models.Transaction)
	 */
	@Override
	public boolean withdrawl(Transaction transaction) {
		boolean result=false;
		try {
			Objects.requireNonNull(transaction);
		}catch(NullPointerException ex) {
			throw new UnauthorizedNullTransaction();
		}

		Double amount = transaction.getAmount();
		if(transaction.getAccountId()!=null) {
			if(accountMap.containsKey(transaction.getAccountId().toString())) {
				//convert the amount to reflect that it is a withdrawl operation
				Double amountToWithdrawl = amount >=0 ? -amount: amount;
				Double newBalance = accountMap.get(transaction.getAccountId().toString()).addAndGet(amountToWithdrawl);
				accountMap.put(transaction.getAccountId().toString(),new AtomicDouble(newBalance)) ;
			}else {
				throw new InvalidAccountException(transaction.getAccountId().toString());
			}
			if(historyMap.containsKey(transaction.getAccountId().toString())) {
				//save transaction in historyMap
				List<Transaction> transactionList = historyMap.get(transaction.getAccountId().toString());
				transaction.setTransactionType(TransactionType.WITHDRAWL);
				//write the current balance at the date of this deposit
				transaction.setCurrentBalance(getAccount(transaction.getAccountId().toString()).getBalance());
				if(transactionList==null  || transactionList.size()==0) {
					transactionList = new CopyOnWriteArrayList<>();
					transactionList.add(transaction);
					historyMap.put(transaction.getAccountId().toString(), transactionList);
				}else {
					transactionList.add(transaction);
				}
			}else {
				throw new InvalidAccountException(transaction.getAccountId().toString());
			}
		}
		for(Map.Entry m : historyMap.entrySet())
			((List<Transaction>) m.getValue()).stream().map(t->t.getCurrentBalance()).collect(Collectors.toList()).forEach(System.out::println);

		result = true;

		return result;
	}
	@Override
	public String printStatement(String accountId) {
		if(accountId!=null) {
			StringBuffer sb = new StringBuffer();
			sb.append("date | amount | operation type | balance");
			sb.append(System.getProperty("line.separator"));
			List<Transaction> transactionList = historyMap.get(accountId);
			if(transactionList!=null && transactionList.size()!=0) {
				Iterator itr = transactionList.iterator();
				while (itr.hasNext()) {
					Transaction transaction = (Transaction)itr.next();
					sb.append(transaction.getTransacDate()).append(" | ").append(transaction.getAmount()).append(" | ").append(transaction.getTransactionType().toString()).append(" | ").append(transaction.getCurrentBalance());
					sb.append(System.getProperty("line.separator")); 
				}
			}
			return sb.toString();
		}
		throw new InvalidAccountException(accountId);
	}

	//private utility method
	private Account getAccount(String accountId) {
		if(accountId!=null && accountMap.containsKey(accountId)) {
			Account account = new Account();
			account.setBalance(accountMap.get(accountId).doubleValue());
			return account;
		}
		return null;
	}

	/*
	 * this method will be called by the singleton component BankStarter that will simulate populating the database with some accounts to feed the accountMap and historyMap caches
	 * this method should be dropped in a real project with persistence enabled
	 */
	public void addToCache() {
		//add some acountId to the cache to replace the DB content 
		accountMap.putIfAbsent("1", new AtomicDouble(100));
		accountMap.putIfAbsent("2", new AtomicDouble(200));
		accountMap.putIfAbsent("3", new AtomicDouble(300));
		historyMap.putIfAbsent("1", new ArrayList<>());
		historyMap.putIfAbsent("2", new ArrayList<>());
		historyMap.putIfAbsent("3", new ArrayList<>());
		LOGGER.info("initial map [{}]", accountMap);
		LOGGER.info("initial historymap [{}]", historyMap);
	}



}
