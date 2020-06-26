package com.flipkart.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.MoblieSearchPage;
import com.flipkart.utilitesPage.Testutil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


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
	public Object[][] getProjectData() throws Exception{
		Object data[][]=Testutil.getTestData(sheetName);
		return data;
	}

	@Test
	@Severity(SeverityLevel.CRITICAL)
	public void validatingUrlTest() {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	//Searching Product
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getProjectData")
	public void searchProductsTest(String Product) {
		page.searchProducts(Product);
	}

	//Ram Selection
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProjectData")
	public void ramSelectionTest(String Product ,String ram) {
		page.searchProducts(Product);
		page.ramSelection(ram);
		String actuallRamVale = page.ramValue;
		Assert.assertEquals(actuallRamVale, ram);
	}

	//Brand Selection
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getProjectData")
	public void brandSelectionTest (String Product ,String ram,String brandName) {

		page.searchProducts(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		String actualBrandValue = page.brandValue;
		Assert.assertEquals(actualBrandValue, brandName);
	}

	//Specified Mobile Selection
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProjectData")
	public void selectMobileTest (String Product ,String ram,String brandName,String mobileName) {

		page.searchProducts(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);
		String actualSelectedMobileValue = page.mobileNameValue;
		Assert.assertEquals(actualSelectedMobileValue, mobileName);

	}

	//Compare Mobile
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getProjectData")
	public void compareMobileTest (String Product ,String ram,String brandName,String mobileName,String compareBrandOne,String compareProductOne) throws InterruptedException {
		
		page.searchProducts(Product);
		page.ramSelection(ram);
		page.brandSelection(brandName);
		page.selectMobile(mobileName);
	
		Testutil.getRequiredWindow( driver, "Nokia");
		logger.info(driver.getTitle());
		page.compareMobile(compareBrandOne,compareProductOne);

		String actualCompareBrandValue = page.chooseBrand1Value;
		Assert.assertEquals(actualCompareBrandValue, compareBrandOne);

		String actualCompareProductValue = page.chooseProduct1Value;
		Assert.assertEquals(actualCompareProductValue, compareProductOne);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
