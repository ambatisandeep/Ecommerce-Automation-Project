package com.flipkart.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.MoblieSearchPage;


public class MobileSearchPageTest extends TestBase {
	
	Logger logger =Logger.getLogger(MobileSearchPageTest.class);
	
	@BeforeMethod
	public void setUp() {
		intialization();
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		//Closing Popup
		driver.findElement(By.xpath("//*[@class='_2AkmmA _29YdH8']")).click();
		logger.info("Pop Up closed");
		}

	@Test
	@Parameters({"Product","ram","brandName","mobileName","tabNum"})
	public void TestCase (String Product, String ram, String brandName,String mobileName,int tabNum) throws InterruptedException {
		
		MoblieSearchPage page = new MoblieSearchPage(driver);

		//Searching Product
		page.searchProducts(Product);

		//Ram Selection
		page.ramSelection(ram);

		//Brand Selection		
		page.brandSelection(brandName);
		
		Thread.sleep(3000);

		//Selecting Specified Mobile
		page.selectMobile(mobileName, tabNum);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
