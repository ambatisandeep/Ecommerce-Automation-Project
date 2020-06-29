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

import io.qameta.allure.Story;

public class MoblieSearchPage extends TestBase {
	
	public String ramValue;
	public String brandValue;
	public String mobileNameValue;
	public String chooseBrand1Value;
	public String chooseProduct1Value;

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
	@Story("Search Product Test")
	public String searchProducts(String Product) {

		linksAndButtons.searchBar.click();
		logger.info("clicked Product Search Bar");

		linksAndButtons.searchBar.sendKeys(Product);
		logger.info("Search Product -> " + Product);

		linksAndButtons.searchButton.click();
		logger.info("Search Button clicked");

		logger.info(driver.getTitle());

		return Product;
	} 


	//Selection of Ram Types
	@Story("Ram Selection Test")
	public String ramSelection (String ram) {

		if(ram.equals("6 GB")){
			WebElement getRamTitle = driver.findElement(By.xpath("//*[@class='_1p7h2j']/ancestor::div[@title='"+ram+" & Above']"));
			ramValue = getRamTitle.getAttribute("title");			
			WebElement ram6GB = driver.findElement(By.xpath("//*[contains(text(),'"+ram+" & Above')]/preceding::div[@class='_1p7h2j']"));
			ram6GB.click();
			logger.info("Ram Selected -> " + ramValue);
		}
		else {

			WebElement getRamTitle = driver.findElement(By.xpath("//*[@class='_1p7h2j']/ancestor::div[@title='"+ram+"']"));
			ramValue = getRamTitle.getAttribute("title");
			WebElement ramGB = driver.findElement(By.xpath("//*[contains(text(),'6 GB')]/following::div[@title='"+ram+"']/div/div/label/div[@class='_1p7h2j']"));
			ramGB.click();
			logger.info("Ram Selected -> " + ramValue);

		}
		return ramValue;
	}

	//Selection of Brands
	@Story("Brand Selection Test")
	public String brandSelection (String brandName) {

		String brandVisibleString = "//*[contains(text(),'6 GB')]/following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']";
		try {
			WebElement getBrandTitle = driver.findElement(By.xpath("//*[@class='_1p7h2j']/ancestor::div[@title='"+brandName+"']"));
			brandValue = getBrandTitle.getAttribute("title");
			logger.info(brandValue);
			
			WebElement brandVisibleElement = driver.findElement(By.xpath(brandVisibleString));
			Testutil.explicitWait(driver, brandVisibleElement, 2);
			if(brandVisibleElement.isDisplayed() && brandVisibleElement.isEnabled()) {
				brandVisibleElement.click();
			}
		}catch (NoSuchElementException e) {

			if(ramValue.equals("6 GB & Above")){
				WebElement more = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[2]/div/div[1]/div/div[1]/div/section[5]/div[2]/div[2]/span"));
				more.click();
				
				WebElement getBrandTitle = driver.findElement(By.xpath("//*[@class='_1p7h2j']/ancestor::div[@title='"+brandName+"']"));
				brandValue = getBrandTitle.getAttribute("title");
				logger.info(brandValue);
				
				WebElement brandVisibleElement = driver.findElement(By.xpath(brandVisibleString));
				
				Testutil.explicitWait(driver, brandVisibleElement, 2);
				if(brandVisibleElement.isDisplayed() && brandVisibleElement.isEnabled())
					brandVisibleElement.click();
			}else {
				linksAndButtons.searchBrandBar.click();
				logger.info("clicked mobile search bar ");

				linksAndButtons.searchBrandBar.clear();

				linksAndButtons.searchBrandBar.sendKeys(brandName);
				logger.info("Searching Brand -> " + brandName);
				
				WebElement getBrandTitle = driver.findElement(By.xpath("//*[@class='_1p7h2j']/ancestor::div[@title='"+brandName+"']"));
				brandValue = getBrandTitle.getAttribute("title");
				logger.info(brandValue);

				WebElement brandMoreElement = driver.findElement(By.xpath("//*[@placeholder='Search Brand']//following::div[@title='"+brandName+"']/div/div/label/div[@class='_1p7h2j']"));
				Testutil.explicitWait(driver, brandMoreElement, 5);
				brandMoreElement.isDisplayed();
				brandMoreElement.isEnabled();
				brandMoreElement.click();
				logger.info("Brand Selected  -> " + brandName);
			}
		}

		return brandName;

	}


	//Selecting Preferred Mobile
	@Story("Mobile Selection Test")
	public String selectMobile(String mobileName) {
		WebElement mobileSelection = driver.findElement(By.xpath("//*[contains(text(),'"+mobileName+"')]"));
		logger.info(mobileSelection);
		mobileNameValue = mobileSelection.getAttribute("text");
		mobileSelection.click();
		logger.info("clicked "+ mobileName);
		return mobileName;
	}
	
	@Story("Mobile Compare Test")
	public void compareMobile(String compareBrandOne,String compareProductOne) {

		linksAndButtons.compareCheckBox.click();
		logger.info("Compare CheckBox clicked");

		linksAndButtons.compareButton.click();
		logger.info("Compare Button clicked");

		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement chooseBrandSearch1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/div/following::div[contains(text(),'Choose Brand')][1]")));

		//linksAndButtons.chooseBrandSearch1.click();
		Testutil.mouseHover(driver, chooseBrandSearch1);
		logger.info("chooseBrandSearch1 clicked");
		
		WebElement chooseBrand1 = driver.findElement(By.xpath("(//*[@placeholder=\"Choose Brand\"]//following::div[@title='"+compareBrandOne+"'])[1]"));
		chooseBrand1Value = chooseBrand1.getAttribute("title");
		logger.info(chooseBrand1Value);
		Testutil.mouseHover(driver, chooseBrand1);
		logger.info("Selected Compare Brand -> " + compareBrandOne);

		WebElement chooseProductSearch1=driver.findElement(By.xpath("//*[@id='fk-compare-page']/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div[1]"));
		Testutil.mouseHover(driver, chooseProductSearch1);
		logger.info("Choose Product Search Bar");

		WebDriverWait wait1 = new WebDriverWait(driver,30);
		WebElement chooseProduct1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/div/following::div[@title='"+compareProductOne+"']")));
		chooseProduct1Value=chooseProduct1.getAttribute("title");
		Testutil.mouseHover(driver, chooseProduct1);
		logger.info("Selected Compare Product -> " + chooseProduct1Value );
	}


}
