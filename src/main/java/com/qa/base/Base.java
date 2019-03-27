package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {
	
	public Properties prop;
	public int response_status_code_200=200;
	public int response_status_code_500=500;
	public int response_status_code_400=400;
	public int response_status_code_401=401;
	public int response_status_code_201=201;
	//public int response_status_code_200=200;
	
	public Base() {
			
		
		try {
			

			 prop = new Properties();
			FileInputStream ip;
			ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
	}
	
	

}
