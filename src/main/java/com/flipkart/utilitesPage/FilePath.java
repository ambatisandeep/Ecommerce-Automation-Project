package com.flipkart.utilitesPage;

import org.apache.log4j.xml.Log4jEntityResolver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FilePath {
private static  String log4jPath,propertyFilePath;
static {
	propertyFilePath = System.getProperty("user.dir")+ "/src/main/java/com/flipkart/confiq/confiq.properties";
	log4jPath="log4j.properties";
	
}
public static String getLog4jPath() {
	return log4jPath;
}
}
