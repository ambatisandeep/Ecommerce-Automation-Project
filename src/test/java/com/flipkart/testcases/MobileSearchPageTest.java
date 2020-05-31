package com.flipkart.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.MoblieSearchPage;


public class MobileSearchPageTest extends TestBase {
	
	@BeforeMethod
	public void setUp() {
		intialization();
		//Closing Popup
		driver.findElement(By.xpath("//*[@class='_2AkmmA _29YdH8']")).click();
		}

	@Test
	@Parameters({"Product","ram","brandName","mobileName"})
	public void TestCase (String Product, String ram, String brandName,String mobileName) throws InterruptedException {
		
		MoblieSearchPage page = new MoblieSearchPage(driver);

		//Searching Product
		page.searchProducts(Product);

		//Ram Selection
		page.ramSelection(ram);

		//Brand Selection		
		page.brandSelection(brandName);
		
		Thread.sleep(3000);

		//Selecting Specified Mobile
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
