package com.flipkart.utilitesPage;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.base.TestBase;

public class Testutil extends TestBase {
	
public int tabNum;
	
	public void switchWindow(int tabNum) {
		ArrayList<String>newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(tabNum));
		
	}
	
	//Mobile page Explicit Wait method
	public void explicitWait(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
	}

	
	

}
