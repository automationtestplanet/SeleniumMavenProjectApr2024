package org.openmrs.pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Utils {
	public static String propFilePath = System.getProperty("user.dir")
			+ "//src//main//resources//application.properties";

	public static Properties appProperties = new Properties();

	public static void loadProperties() {
		try {
			appProperties.load(new FileInputStream(new File(propFilePath)));
		} catch (Exception e) {
			System.out.println("Exception Occured while loading the application properties " + e.getCause());
		}
	}

	static {
		loadProperties();
	}

	public static void setProperty(String propName, String propVale) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(propFilePath));
			appProperties.setProperty(propName, propVale);
			appProperties.store(fos, "");
		} catch (Exception e) {
			System.out.println("Exception Occured while setting the application properties " + e.getCause());
		}

	}
}
