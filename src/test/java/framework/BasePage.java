package framework;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import stepdefinition.SharedSD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

/**
 * Created by mohammadmuntakim
 */
public class BasePage {

 Actions a = new Actions(SharedSD.getDriver());

	// This is the most common wait function used in selenium
	/////////////////////////////////////////////////////////////return a single webelement
	public static WebElement webAction(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotFoundException.class)
				.ignoring(ElementNotVisibleException.class)
				.withMessage(
						"Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return element;
	}

	////////////////////////////////////////////////////////////////////////return multiple web elements
	public static List<WebElement> webActions(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotFoundException.class)
				.withMessage(
						      "Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");

		List<WebElement> elements = wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(locator);
			}
		});
		return elements;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////return a list of webelements
	public List<WebElement> listOfWebElements(By locator){

		return webActions(locator);
	}

	//////////////////////////////////////////////get page URL
	public String getURL(){

		return SharedSD.getDriver().getCurrentUrl();
	}

	/////////////////////////////////////click on an element
	public void clickOn(By locator) {
		webAction(locator).click();
	}

	//////////////////////////////////////////////////////////////////////get title
	public String getTitle(){ return SharedSD.getDriver().getTitle(); }


	///////////////////////////////////////setvalue(sendkeys to an element)
	public void setValue(By locator, String value) {
		webAction(locator).sendKeys(value);
	}

	////////////////////////////////////////////////////get text from an element
	public String getTextFromElement(By locator) {
		return webAction(locator).getText();
	}

	////////////////////////////////////////////////////////////////////check if element is displayed
	public boolean isElementDisplayed(By locator) {
		return webAction(locator).isDisplayed();
	}

	////////////////////////////////////////////////////////////////////////////////check if element is selected
	public boolean isElementSelected(By locator) {
		return webAction(locator).isSelected();
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////selecting from a dropdown
	public void selectFromDropdown(By locator, String dropdownText) {
		WebElement month = webAction(locator);
		Select selectMonth = new Select(month);
		//select element by visible text
		selectMonth.selectByVisibleText(dropdownText);
	}

	public void selectFromDropdown(By locator, int index) {
		WebElement month = webAction(locator);
		Select selectMonth = new Select(month);
		//select element by index
		selectMonth.selectByIndex(index);
	}

	public void selectFromDropdownByValue(By locator, String value) {
		WebElement month = webAction(locator);
		Select selectMonth = new Select(month);
		//select element by visible text
		selectMonth.selectByValue(value);
	}



	////////////////////////////////////////////////////////////////////////////////mouse actions
	public  void hoverOverElement(By locator){
		a.moveToElement(webAction(locator));
		}

     public void clickOnElementUsingMouse(By locator){

		a.moveToElement(webAction(locator)).click();
	 }

	 ///////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////date(return hour)

	public int returnHour(){

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh");
		String hour = sdf.format(d);
		int hour1 = Integer.parseInt(hour);
		/*
		int checkoutDate =  Integer.parseInt(day)+8;
        String checkout = String.valueOf(checkoutDate);
		 */
		return hour1;



	}

	////////////////////////////scroll an element into view and click

	public void scrollIntoViewAndClick(By locator){

		WebElement element = webAction(locator);
		((JavascriptExecutor)SharedSD.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element.click();

	}

}
