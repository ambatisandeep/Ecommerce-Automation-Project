package com.flipkart.utilitesPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	public static String TESTDATA_PATH = "Test_Data.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		File inputfile = new File(TESTDATA_PATH);
		FileInputStream file = null;
		try {
			file = new FileInputStream(inputfile);
		 System.out.println(inputfile.getAbsolutePath());
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// book = new XSSFWorkbook(file);
		} 
		//catch(invalid){
	//	}
		catch (Exception e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int row=0;row<sheet.getLastRowNum();row++) {
			for(int coulmn=0;coulmn<sheet.getRow(0).getLastCellNum();coulmn++) {
				data[row][coulmn]=sheet.getRow(row+1).getCell(coulmn).toString();
			}
		}
		return data;
	}


}
