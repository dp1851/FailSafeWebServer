package com.failsafe.rest;

/**
 * User Class
 * @author Dev Patel
 *
 */
public class User {
	
	private String id;
	private String firstName;
	private String lastName;
	
	/**
	 * 
	 * @param id
	 * @param fName
	 * @param lName
	 */
	public User (String id, String fName, String lName) {
		this.id = id;
		this.firstName = fName;
		this.lastName = lName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
