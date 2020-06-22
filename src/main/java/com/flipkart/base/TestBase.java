package com.flipkart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.flipkart.utilitesPage.FilePath;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	private static Logger logger  = Logger.getLogger(TestBase.class);
	//static final String log4jConfPath = "log4j.properties";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/flipkart/confiq/confiq.properties");
			prop.load(file);
			//BasicConfigurator.configure();
			PropertyConfigurator.configure(FilePath.getLog4jPath());

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void intialization() {
		String browserType=prop.getProperty("browser");
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			driver =new ChromeDriver();	
		}else {

			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		String url = prop.getProperty("url");
		driver.get(url);
		logger.info("Opened URL " + url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	
	
	
	
}