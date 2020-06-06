package com.flipkart.utilitesPage;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.base.TestBase;

public class Testutil extends TestBase {


	public static void switchWindow(int tabNum) {
		ArrayList<String>newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(tabNum));

	}
	public static void switchWindow(int tabNum,WebDriver driver ) {
		ArrayList<String>newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(tabNum));

	}
	public static boolean getRequiredWindow(WebDriver driver,String requiredTitle ) {
		ArrayList<String>newTab = new ArrayList<String>(driver.getWindowHandles());
		for(int i=0;i<newTab.size();i++) {
			driver.switchTo().window(newTab.get(i));
			if(driver.getTitle().contains(requiredTitle))
				return true;
		}
		return false;

	}

	//Mobile page Explicit Wait method
	public static void explicitWait(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
	}

	public static void mouseHover(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}




}
