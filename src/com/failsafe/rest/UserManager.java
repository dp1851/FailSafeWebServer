package com.failsafe.rest;

import java.util.Map;

/**
 * User management functions
 * 
 * @author Dev Patel
 *
 */
public class UserManager {
	
	/**
	 * Default constructor
	 */
	public UserManager() {
	}
	
	
	/**
	 * Returns true if user is authenticated and false other wise
	 * @param id
	 * @return
	 */
	public boolean authenticate(String id) throws Exception {
		return UserStore.getInstance().authenticate(id);
	}
	
	/**
	 * Returns true if addition of user is successful. If user already exists, user will not be added
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean addUser(String id, String firstName, String lastName) throws Exception {
		return UserStore.getInstance().addUser(id, firstName, lastName);
	}
	
	/**
	 * Returns true if remove user is successful
	 * @param id
	 * @return
	 */
	public boolean removeUser(String id) throws Exception {
		return UserStore.getInstance().removeUser(id);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, User> getUsers() throws Exception {
		return UserStore.getInstance().getUsers();
	}

}
