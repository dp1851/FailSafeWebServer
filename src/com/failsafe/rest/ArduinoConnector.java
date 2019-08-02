package com.failsafe.rest;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author dines
 *
 */
public class ArduinoConnector {
	private static ArduinoConnector _instance = null;

	HttpURLConnection initConn = null;
	HttpURLConnection startConn = null;
	HttpURLConnection stopConn = null;
	
	/**
	 * 
	 */
	private ArduinoConnector() {
		try
		{
			URL initializeURL = new URL("http://172.20.10.6/digital/13/O");
			initConn = (HttpURLConnection) initializeURL.openConnection();
			initConn.setDoOutput(true);
			initConn.setRequestMethod("GET");
			initConn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = initConn.getOutputStream();
			os.write("".getBytes());
			os.flush();
			
			initConn.disconnect();
			
			System.out.println("Initialize Connection Response:"+initConn.getResponseCode());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArduinoConnector getInstance() {
		if(_instance == null) {
			synchronized(ArduinoConnector.class) {
				if(_instance == null) {
					_instance = new ArduinoConnector();
				}
			}
		}
		return _instance;
	}
	
	/**
	 * 
	 * @return
	 */
	public void loginSucces() {
		try
		{
			URL startURL = new URL("http://172.20.10.6/digital/13/1");
			startConn = (HttpURLConnection) startURL.openConnection();
			startConn.setDoOutput(true);
			startConn.setRequestMethod("GET");
			startConn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = startConn.getOutputStream();
			os.write("".getBytes());
			os.flush();
			startConn.disconnect();
			
			System.out.println("Start Light Connection Response:"+startConn.getResponseCode());
			
			 try {
		            Thread.sleep(2000);
		     } 
			 catch (InterruptedException ex) {
		            // TODO Auto-generated catch block
		            ex.printStackTrace();
		     }

			URL stopURL = new URL("http://172.20.10.6/digital/13/0");
			stopConn = (HttpURLConnection) stopURL.openConnection();
			stopConn.setDoOutput(true);
			stopConn.setRequestMethod("GET");
			stopConn.setRequestProperty("Content-Type", "application/json");
				
			OutputStream os2 = stopConn.getOutputStream();
			os2.write("".getBytes());
			os2.flush();
			
			
			stopConn.disconnect();
			
			System.out.println("Stop Light Connection Response:"+stopConn.getResponseCode());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
