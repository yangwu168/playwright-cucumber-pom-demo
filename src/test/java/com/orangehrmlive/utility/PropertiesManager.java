package com.orangehrmlive.utility;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesManager {

	public static final Logger log = LogManager.getLogger(PropertiesManager.class);
	
	private String propertyFile;
	private Properties prop;
	private FileInputStream input;
	private FileOutputStream output;
	
	public PropertiesManager(String propertyFilePath) {
		try {
			if(propertyFilePath.length() > 0) {
				prop = new Properties();
				propertyFile = propertyFilePath;
			}
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue("Initializing properties object failed.", false);
		}
	}
	
	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertyFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue("Reading Java properties file failed.", false);
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (Exception e) {
					log.error("Error: ", e);
					assertTrue("Closing 'FileInputStream' object failed.", false);
				}
			}
		}
		return value;
	}
	
	public void setProperty(String key, String value) {
		try {
			output = new FileOutputStream(propertyFile);
			prop.setProperty(key, value);
			prop.store(output, null);
			log.info("Properties file created ---> " + propertyFile);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue("Initializing 'FileOutputStream' object failed.", false);
		} finally {
			try {
				if(output != null) {
					output.close();
				}
			} catch (Exception e) {
				log.error("Error: ", e);
				assertTrue("Closing 'FileOutputStream' object failed.", false);
			}
		}	
	}
	
	public void setProperty(String key, String value, String comments) {
		try {
			output = new FileOutputStream(propertyFile);
			prop.setProperty(key, value);
			prop.store(output, comments);
			log.info("Properties file created ---> " + propertyFile);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue("Initializing 'FileOutputStream' object failed.", false);
		} finally {
			try {
				if(output != null) {
					output.close();
				}
			} catch (Exception e) {
				log.error("Error: ", e);
				assertTrue("Closing 'FileOutputStream' object failed.", false);
			}
		}	
	}
	
	public static void main(String[] args) {
		PropertiesManager myReader = new PropertiesManager("src/test/resources/config.properties");
		String value1 = myReader.readProperty("sendEmail");
		log.info("Value1: " + value1);
		String value2 = myReader.readProperty("browserType");
		String value3 = myReader.readProperty("demoMode");
		String value4 = myReader.readProperty("isHeadless");
		String value5 = myReader.readProperty("isRemote");
		
		log.info("Value2: " + value2);
		log.info("Value3: " + value3);
		log.info("Value4: " + value4);
		log.info("Value5: " + value5);
		
		PropertiesManager myProperty = new PropertiesManager("src/test/resources/config.properties");
		myProperty.setProperty("firstName", "Frank");
		myProperty.setProperty("lastName", "Musabay");
	}
}
