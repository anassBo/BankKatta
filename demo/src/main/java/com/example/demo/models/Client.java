/**
 * 
 */
package com.example.demo.models;

import java.io.Serializable;
import java.util.Optional;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;*/

/**
 * @author a.boumera
 *
 */
/*@Entity
@Table(name = "client")*/
public class Client implements Serializable {

	
	private static final long serialVersionUID = 1L;

	/*@Id
    @GeneratedValue
    @Column(name = "ID")*/
	private Long id;

	// @Column(nullable = false)
	private String firstName;

	private String lastName;

	protected Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//use of optional instead of null. this could be of whidespread use in this project
	public Optional<String> getFirstName() {
		return Optional.ofNullable(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Optional<String> getLastName() {
		return Optional.ofNullable(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
