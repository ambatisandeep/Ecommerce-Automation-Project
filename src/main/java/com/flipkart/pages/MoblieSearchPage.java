package com.flipkart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.base.TestBase;
import com.flipkart.utilitesPage.Testutil;

public class MoblieSearchPage extends TestBase {

	Logger logger =Logger.getLogger(MoblieSearchPage.class);

	public linksAndButtons buttonsObj;

	public Testutil testUtil;

	public MoblieSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		buttonsObj = new linksAndButtons(driver);
	}
	
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

		static @FindBy(xpath = "//*[@id='container']/div/div/following::label/div[@class='_1p7h2j']")
		WebElement compareCheckBox;

		static @FindBy(xpath = "//*[@id='container']/div/div/following::span//*[contains(text(),'COMPARE')]")
		WebElement compareButton;

	}



	//Clicking search bar & passing product parameter
	public String searchProducts(String Product) {
		linksAndButtons.searchBar.click();
		logger.info("Clicked Product Search Bar");

		linksAndButtons.searchBar.sendKeys(Product);
		logger.info("Search Product " + Product);

		linksAndButtons.searchButton.click();
		logger.info("Search Button Clicked");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		String actualTitle = ;
		logger.info(driver.getTitle());

		return Product;
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
			logger.info(ram + " Ram Selected ");

		}
		String actualTitle = driver.getTitle();
		logger.info(actualTitle);
		return ram;
	}

	//Selection of Brands
	public String brandSelection (String brandName) {

		String brandVisibleString = "//*[contains(text(),'6 GB')]/following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";
		try {
			WebElement brandVisibleElement = driver.findElement(By.xpath(brandVisibleString));
			Testutil.explicitWait(driver, brandVisibleElement, 10);
			boolean flag = brandVisibleElement.isDisplayed();
			logger.info("Element Displayed " + flag);
			boolean flip = brandVisibleElement.isEnabled();
			logger.info("Element Displayed " + flip); 
			if(brandVisibleElement.isDisplayed() && brandVisibleElement.isEnabled())
				brandVisibleElement.click();
			logger.info(brandName + " brand mobiles selected ");

		}catch (NoSuchElementException e) {

			linksAndButtons.searchBrandBar.click();
			logger.info(" Clicked Mobile Search Bar ");

			linksAndButtons.searchBrandBar.clear();

			linksAndButtons.searchBrandBar.sendKeys(brandName);
			logger.info(" Searching " + brandName);

			String brandMoreString = "//*[@placeholder='Search Brand']//following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";
			WebElement brandMoreElement = driver.findElement(By.xpath(brandMoreString));
			Testutil.explicitWait(driver, brandMoreElement, 5);
			brandMoreElement.isDisplayed();
			brandMoreElement.isEnabled();
			brandMoreElement.click();
			logger.info(brandName + " Brand Selected");
		}

		return brandName;

	}

	//Selecting Preferred Mobile
	public String selectMobile(String mobileName) {
		driver.findElement(By.xpath("//*[contains(text(),'"+mobileName+"')]")).click();
		logger.info("Clicked "+ mobileName);
		logger.info("Switched to New Tab");
		//		Assert.assertEquals(actualTitle, "Vivo Z1Pro ( 64 GB Storage, 4 GB RAM ) Online at Best Price On Flipkart.com");
		return mobileName;
	}

	public void compareMobile(String compareBrandOne,String compareProductOne) {
		
		String actualTitle=driver.getTitle();
		logger.info(actualTitle);

		linksAndButtons.compareCheckBox.click();
		logger.info("Compare CheckBox Clicked");

		linksAndButtons.compareButton.click();
		logger.info("Compare CheckBox Clicked");

		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement chooseBrandSearch1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/div/following::div[contains(text(),'Choose Brand')][1]")));

		//linksAndButtons.chooseBrandSearch1.click();
		Testutil.mouseHover(driver, chooseBrandSearch1);
		logger.info("chooseBrandSearch1 Clicked");

		WebElement chooseBrand1 = driver.findElement(By.xpath("//*[@id='container']/div/div/following::div[contains(text(),'Choose Brand')][1]//following::div[@title='"+compareBrandOne+"'][1]"));
		Testutil.mouseHover(driver, chooseBrand1);
		logger.info("Compare Brand Selected");

		WebElement chooseProductSearch1=driver.findElement(By.xpath("//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div[1]"));
//		WebElement chooseProductSearch1 =driver.findElement(By.xpath("//*[@id='container']/div/div/following::div[contains(text(),'Choose a Product')][1]"));
		Testutil.mouseHover(driver, chooseProductSearch1);
		logger.info("Choose Product Search Bar");
		
		WebDriverWait wait1 = new WebDriverWait(driver,30);
		WebElement chooseProduct1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/div/following::div[@title='"+compareProductOne+"']")));
		
		//WebElement chooseProduct1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div[2]/div/div[19]")));
		//js.executeScript("arguments[0].scrollIntoView(true);", chooseProduct1);
		Testutil.mouseHover(driver, chooseProduct1);
		logger.info("Product Selected");
	}

}
