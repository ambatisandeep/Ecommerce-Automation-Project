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
import com.flipkart.utilitesPage.Testutil;


public class MobileSearchPageTest extends TestBase {

	Logger logger =Logger.getLogger(MobileSearchPageTest.class);
	MoblieSearchPage page;
	Testutil testUtil;

	public MobileSearchPageTest() {
		super();
	}


	@BeforeMethod
	public void setUp() {
		intialization();
		page = new MoblieSearchPage(driver);
		//Closing Popup
		driver.findElement(By.xpath("//*[@class='_2AkmmA _29YdH8']")).click();
		logger.info("Pop Up closed");
	}

	@Test(priority=1)
	public void validatingUrlTest() {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	@Test(priority=2,groups = {"MobileSelection"})
	@Parameters("Product")
	public void searchProductsTest(String Product) {
		//Searching Product
		page.searchProducts(Product);
	}
	

	@Test(priority=3,dependsOnGroups = {"MobileSelection"})
	@Parameters({"Product","ram"})
	//Ram Selection
	public void ramSelectionTest(String Product ,String ram) {
		searchProductsTest(Product);
		page.ramSelection(ram);
	}
	
	@Test(priority=4,dependsOnGroups = {"MobileSelection"})
	@Parameters({"Product","ram","brandName"})
	public void brandSelectionTest (String Product ,String ram,String brandName) {
		//Brand Selection	
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
	}
	
	@Test(priority=5,dependsOnGroups = {"MobileSelection"})
	@Parameters({"Product","ram","brandName","mobileName"})
	public void selectMobileTest (String Product ,String ram,String brandName,String mobileName) {
		//Specified Mobile Selection	
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);
//		testUtil.switchWindow(tabNum);
	}
	
	

	@Test(priority=6,dependsOnGroups = {"MobileSelection"})
	@Parameters({"Product","ram","brandName","mobileName"})
	public void compareMobileTest (String Product ,String ram,String brandName,String mobileName) throws InterruptedException {
		//Compare Mobile
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);
		page.compareMobile();
	}

	@AfterMethod
	public void tearDown() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}


}
