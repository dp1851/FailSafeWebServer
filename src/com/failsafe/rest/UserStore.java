package com.failsafe.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * User Store
 * 
 * @author Dev Patel
 *
 */
public class UserStore {
	
	private static final String FILE_PATH = System.getProperty("user.home") + File.separator + "failsafe.txt";
	private static UserStore _instance = null;
	private Map<String, User> users = new HashMap<String, User>();

	/**
	 * 
	 * @throws IOException
	 */
	private UserStore() throws IOException {
		this.initialize();
	}
	
	/**
	 * Return singleton instance
	 * @return
	 */
	public static synchronized UserStore getInstance() throws IOException {
		if(_instance == null) {
			synchronized(UserStore.class) {
				if(_instance == null) {
					_instance = new UserStore();
				}
			}
		}
		return _instance;
	}
	
	/**
	 * Initialize User Manager by reading from file
	 */
	private void initialize() throws IOException {
		
		// Initialize User Store
		File f = new File(FILE_PATH);
		if(!f.exists()) {
			f.createNewFile();
		}
		
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(f);
			while(scanner.hasNext()) {
				String line = scanner.next();
				if(line != null && line.trim() != "") {
					StringTokenizer st = new StringTokenizer(line,",");
					if(st.countTokens() == 3) {
						String id = st.nextToken();
						String firstName = st.nextToken();
						String lastName = st.nextToken();
						
						User user = new User(id, firstName, lastName);
						users.put(id, user);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, User> getUsers() {
		return users;
	}
	
	
	/**
	 * Returns true if user is authenticated and false other wise
	 * @param id
	 * @return
	 */
	public boolean authenticate(String id) {
		return users.containsKey(id);
	}
	
	/**
	 * Returns true if addition of user is successful. If user already exists, user will not be added
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean addUser(String id, String firstName, String lastName) throws Exception {
		PrintWriter pw = null;
		try
		{
			if(!users.containsKey(id)) {
				User user = new User(id, firstName, lastName);
				users.put(id, user);
				
				//Persist file
				pw = new PrintWriter(new FileOutputStream(new File(FILE_PATH), true));
				pw.append(id+","+firstName+","+lastName+"\r\n");
				pw.flush();
				pw.close();
				return true;
			}
			return false;
		}
		catch(Exception e) {
			pw.close();
			throw new Exception ("User ["+id+"] already exists");
		}
	}
	
	/**
	 * Returns true if remove user is successful
	 * @param id
	 * @return
	 */
	public boolean removeUser(String id) throws Exception {
		if(users.containsKey(id)) {
			users.remove(id);
			
			File file = new File(FILE_PATH);
		    List<String> out = Files.lines(file.toPath())
		                        .filter(line -> !line.contains(id))
		                        .collect(Collectors.toList());
		    Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		    
		    return true;
		}

		throw new Exception ("User ["+id+"] does not exist");
	}

}
