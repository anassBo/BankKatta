package com.example.demo.models;
import java.io.Serializable;
import java.util.Optional;

//as there is no persistence
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 */
import com.example.demo.constant.Currency;

/**
 * @author a.boumera
 *
 */
/*//@Entity  
@Table(name = "account")*/
public class Account implements Serializable {

	
	private static final long serialVersionUID = 1L;

	/* @Id
    @GeneratedValue
    @Column(name = "ID")*/
	private Long id;

	//@Column(nullable = false)
	private String name;

	private Double balance;

	private Currency currency;

	private Long clientId;

	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClienId(Long clientId) {
		this.clientId = clientId;
	}

}
