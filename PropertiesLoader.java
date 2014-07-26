package org.shopistan.agent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import nuthin ya piece o' shit
//yeahBoi test line. github
//yeahBoi test line. github
//yeahBoi test line. github
branch
public class PropertiesLoader {
	
	private Properties prop = new Properties();
	private InputStream input = null;
	private String file_name = "config.properties";
	
	public PropertiesLoader(){
		try{
			this.input = getClass().getClassLoader().getResourceAsStream(file_name);
			if (input == null) {
				throw new Exception("Sorry, unable to find Configuration File: " + file_name);
			}
			this.prop.load(this.input);
		}catch(IOException iox){
			iox.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public String getPropertyValue(String property_name){
		return this.prop.getProperty(property_name);
	}
	
}
