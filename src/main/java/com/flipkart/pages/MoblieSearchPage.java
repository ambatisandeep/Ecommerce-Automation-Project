package com.flipkart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.LogFile;
import com.flipkart.base.TestBase;
import com.flipkart.utilitesPage.Testutil;


public class MoblieSearchPage extends TestBase {
	
	Logger logger =Logger.getLogger(MoblieSearchPage.class);
	
	public linksAndButtons buttonsObj;
	
	public Testutil testUtil;

	public MoblieSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		buttonsObj = new linksAndButtons(driver);
		testUtil = new Testutil();
	}
//commit
	public linksAndButtons getbuttonsObj() {
		return buttonsObj;
	}


	public static class linksAndButtons{

		public linksAndButtons(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		static	@FindBy(xpath = "//*[@class='LM6RPg']")
		WebElement searchBar;

		static	@FindBy(xpath = "//*[@class='vh79eN']")
		WebElement searchButton;

		static @FindBy(xpath = "//*[@class='opNt-w _2Tprpw']")
		WebElement moreBrandsButton;

		static @FindBy(xpath = "//*[@placeholder='Search Brand']")
		WebElement searchBrandBar;

	}



	//Clicking search bar & passing product parameter
	public void searchProducts(String Product) throws InterruptedException  {
		linksAndButtons.searchBar.click();
		logger.info("Clicked Product Search Bar");
		
		linksAndButtons.searchBar.sendKeys(Product);
		logger.info("Search Product " + Product);
		
		linksAndButtons.searchButton.click();
		logger.info("Search Button Clicked");
//		Thread.sleep(2000);
	} 

	//Selection of Ram Types
	public String ramSelection (String ram) {
		//
		String ram6GB = "//*[contains(text(),'"+ram+" & Above')]/preceding::div[@class='_1p7h2j']";
		String ramGB = "//*[contains(text(),'6 GB')]/following::div[@title='"+
				ram+"']/div/div/label/div[@class='_1p7h2j']";

		if(ram.equals("6 GB")){
			driver.findElement(By.xpath(ram6GB)).click();
			logger.info("6 GB and Above Ram Selected");
		}else {
			driver.findElement(By.xpath(ramGB)).click();
			logger.info(ram + " Selected ");

		}
		return ram;
	}

	//Selection of Brands
	public  String brandSelection (String brandName) throws InterruptedException{

		String brandVisibleString = "//*[contains(text(),'6 GB')]/following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";

		WebElement brandVisibleElement = driver.findElement(By.xpath(brandVisibleString));
		brandVisibleElement.isDisplayed();
		brandVisibleElement.isEnabled();
		if(brandVisibleElement.isDisplayed() && brandVisibleElement.isEnabled()){
			brandVisibleElement.click();
			logger.info(brandName + " brand mobiles selected ");
			LogFile.witeLog(brandName + " brand mobiles selected ");
			} 
		else {
			linksAndButtons.searchBrandBar.click();
			logger.info("clicked Mobile Search Bar");
			
			linksAndButtons.searchBrandBar.clear();
			
			linksAndButtons.searchBrandBar.sendKeys(brandName);
			logger.info("Searching" + brandName);

			String brandMoreString = "//*[@placeholder='Search Brand']//following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";
			WebElement brandMoreElement = driver.findElement(By.xpath(brandMoreString));
			testUtil.explicitWait(driver, brandMoreElement, 5);
			brandMoreElement.isDisplayed();
			System.out.println("Element Displayed");
			brandMoreElement.isEnabled();
			brandMoreElement.click();
			System.out.println("Element Displayed" + brandName);
		}
		return brandName;
	}
	
	public void selectMobile(String mobileName,int tabNum) {
		driver.findElement(By.xpath("//*[contains(text(),'"+mobileName+"')]")).click();
		testUtil.switchWindow(tabNum);
		driver.close();
	}

}
