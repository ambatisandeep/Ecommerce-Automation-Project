package com.flipkart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
		
		static @FindBy(xpath = "//*[contains(text(),'Compare')]/preceding::div[@class='_1p7h2j']")
		WebElement compareCheckBox;
		
		static @FindBy(xpath = "//*[@class='G934d8']/span")
		WebElement compareButton;
		
		static @FindBy(xpath = "//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/div[2]/input")
		WebElement chooseBrandSearch1;
		
		static @FindBy(xpath = "//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/div[2]/div/div[2]")
		WebElement chooseBrand1;
		
		static @FindBy(xpath = "//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/span")
		WebElement chooseProductSearch1;
		
		static @FindBy(xpath = "//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div[2]/div/div[4]")
		WebElement chooseProduct1;
		
	}



	//Clicking search bar & passing product parameter
	public String searchProducts(String Product) {
		linksAndButtons.searchBar.click();
		logger.info("Clicked Product Search Bar");

		linksAndButtons.searchBar.sendKeys(Product);
		logger.info("Search Product " + Product);

		linksAndButtons.searchButton.click();
		logger.info("Search Button Clicked");
		
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
		return ram;
	}

	//Selection of Brands
	public  String brandSelection (String brandName) {

		String brandVisibleString = "//*[contains(text(),'6 GB')]/following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";
		try {
			WebElement brandVisibleElement = driver.findElement(By.xpath(brandVisibleString));
			testUtil.explicitWait(driver, brandVisibleElement, 10);
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
			testUtil.explicitWait(driver, brandMoreElement, 5);
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
		String actualTitle=driver.getTitle();
		logger.info(actualTitle);
//		Assert.assertEquals(actualTitle, "Vivo Z1Pro ( 64 GB Storage, 4 GB RAM ) Online at Best Price On Flipkart.com");
		return mobileName;
	}
	
	public void compareMobile() {
		linksAndButtons.chooseBrandSearch1.click();
		linksAndButtons.chooseBrandSearch1.clear();
		linksAndButtons.chooseBrand1.sendKeys("Realme");
		WebElement chooseBrand1 = driver.findElement(By.xpath("//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/div[2]/div/div[2]"));
		testUtil.explicitWait(driver,chooseBrand1, 10);
		chooseBrand1.click();
		
		linksAndButtons.chooseProductSearch1.click();
		linksAndButtons.chooseProductSearch1.clear();
		linksAndButtons.chooseProductSearch1.sendKeys("Realme");
		WebElement chooseProduct1 = driver.findElement(By.xpath("//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div[2]/div/div[4]"));
		testUtil.explicitWait(driver, chooseProduct1, 10);
		chooseProduct1.click();
		
		
	
	}

}
