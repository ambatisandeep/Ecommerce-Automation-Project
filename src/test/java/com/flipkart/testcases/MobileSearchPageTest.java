package com.flipkart.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.utilitesPage.Testutil;


public class MobileSearchPageTest extends TestBase {

	Logger logger =Logger.getLogger(MobileSearchPageTest.class);
	MoblieSearchPage page;
	Testutil testUtil;
	String sheetName = "MobileSearch";

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
	
	@DataProvider
	public Object[][] getProjectData(){
		Object data[][]=Testutil.getTestData(sheetName);
		return data;
	}

	@Test
	public void validatingUrlTest() {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	//Searching Product
	@Test(dataProvider = "getProjectData")
	public void searchProductsTest(String Product) {
		page.searchProducts(Product);
	}

	//Ram Selection
	@Test(dataProvider = "getProjectData")
	public void ramSelectionTest(String Product ,String ram) {
		searchProductsTest(Product);
		page.ramSelection(ram);
	}

	//Brand Selection
	@Test(dataProvider = "getProjectData")
	public void brandSelectionTest (String Product ,String ram,String brandName) {
			
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
	}

	//Specified Mobile Selection
	@Test(dataProvider = "getProjectData")
	public void selectMobileTest (String Product ,String ram,String brandName,String mobileName) {
		
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);

	}
	
	//Compare Mobile
	@Test(dataProvider = "getProjectData")
	public void compareMobileTest (String Product ,String ram,String brandName,String mobileName,String compareBrandOne,String compareProductOne) throws InterruptedException {
		searchProductsTest(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);
		Testutil.getRequiredWindow( driver, "Nokia");
		logger.info(driver.getTitle());
		page.compareMobile(compareBrandOne,compareProductOne);
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
