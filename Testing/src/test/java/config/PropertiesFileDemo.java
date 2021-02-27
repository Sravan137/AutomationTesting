package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import login.signupDemo;

public class PropertiesFileDemo {

	static Properties file = new Properties();
	static String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getProperties();
		setProperties();
		getProperties();
	}

	public static void getProperties() {
		try {
			InputStream input = new FileInputStream(projectPath+"/src/test/java/config/config.properties");
			file.load(input);
			String browser = file.getProperty("browser");
			System.out.println("Browser in config file is - "+browser);
			signupDemo.browserName = browser;
		}catch(Exception e) {
			System.out.println("message is -"+e.getMessage());
			System.out.println("cause is -"+e.getCause());
			e.getStackTrace();
		}
	}

	public static void setProperties() {
		try {
			OutputStream output = new FileOutputStream(projectPath+"/src/test/java/config/config.properties");
			file.setProperty("browser","chrome");
			file.setProperty("result", "pass");
			file.store(output, null);
		}catch(Exception e) {
			System.out.println("message is -"+e.getMessage());
			System.out.println("cause is -"+e.getCause());
			e.getStackTrace();
		}
	}

}
