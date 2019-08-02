package com.failsafe.rest;

import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * Failsafe Authentication Service
 * 
 * @author Dev Patel
 *
 */
@Path("/failsafe")
public class FailsafeService {
	
	private UserManager userManager = new UserManager();
	
	/**
	 * Returns true if user is authenticated and false other wise
	 * @param id
	 * @return
	 */
	@POST
	@Path("/authenticate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String authenticate(@PathParam("id") String id) {
		boolean result = false;
		String message = "";
		try {
			System.out.println("Authenticating ID["+id+"]");
			result = userManager.authenticate(id);
			
			ArduinoConnector ard = ArduinoConnector.getInstance();
			if(result) {
				ard.loginSucces();
			}
			message = "[{\"authenticate\":\"" + result + "\"}]";
		}
		catch(Exception e) {
			e.printStackTrace();
			message = "[{\"authenticate\":\"" + result + "\"}]";
		}
		System.out.println("User ID[" + id + "], Login Success[" + result + "]");
		System.out.println("JSON Message: " + message);
		return message;
	}
	
	/**
	 * Returns true if addition of user is successful. If user already exists, user will not be added
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	
	@POST
	@Path("/add/{id}_{firstName}_{lastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser(@PathParam("id") String id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {

		String message = "";
		try {
			System.out.println("Adding User:"+id);
			userManager.addUser(id, firstName, lastName);
			User user = userManager.getUsers().get(id);
			if(user != null) {
				message += "{\"id\":\"" + user.getId() + "\",\"firstName\":\"" + user.getFirstName() + 
						"\",\"lastName\":\"" + user.getLastName() + "\"},";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//message = "<html><body>" + e.getMessage() + "</body></html>";
		}
		return message;
	}
	
	
	/**
	 * Returns true if remove user is successful
	 * @param id
	 * @return
	 */
	
	@POST
	@Path("/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeUser(@PathParam("id") String id) {
		String message = "";
		try {
			System.out.println("Removing User:"+id);
			User user = userManager.getUsers().get(id);
			userManager.removeUser(id);
			if(user != null) {
				message += "{\"id\":\"" + user.getId() + "\",\"firstName\":\"" + user.getFirstName() + 
						"\",\"lastName\":\"" + user.getLastName() + "\"},";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			message = "";
		}
		return message;
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/show")
	@Produces(MediaType.APPLICATION_JSON)
	public String showUsers() {
		String message = "[";
		try {
			System.out.println("Retrieving Users");
			Map<String, User> users = userManager.getUsers();
			Iterator<String> userIt = users.keySet().iterator();
			while(userIt.hasNext()) {
				String id= userIt.next();
				User user = users.get(id);
				if(user != null) {
					message += "{\"id\":\"" + user.getId() + "\",\"firstName\":\"" + user.getFirstName() + 
							"\",\"lastName\":\"" + user.getLastName() + "\"},";
					
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//message = "<html><body>" + e.getMessage() + "</body></html>";
		}
		
		message = message.substring(0, message.length() - 1);
		
		message += "]";
		System.out.println("Users["+message+"]");
		return message;
	}
	
	
	
}
