/**
 * 
 */
package com.example.demo.models;

import java.io.Serializable;
import java.time.Instant;

import com.example.demo.constant.TransactionType;

/**
 * @author a.boumera
 *
 */
/*@Entity
@Table(name = "transaction")*/
public class Transaction implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
   /* @Id  
    @GeneratedValue
    @Column(name = "ID")*/
    private Long id;
    
   // @Column(nullable = false)
    private Long accountId;

	private Instant transacDate = Instant.now();
    
    private String description;
    
    private Double amount;
    
    private TransactionType transactionType;
    
    private Double currentBalance;
    
    /*
     * we can use lombok for getters and setters
     */
    
    public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
    public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction() {
        super();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Instant getTransacDate() {
        return transacDate;
    }
    
    public void setTransacDate(Instant transacDate) {
        this.transacDate = transacDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
    
}
