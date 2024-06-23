package NAGPSelenium.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import NAGPSelenium.Helpers.TestLogger;
import NAGPSelenium.Utils.GeneralUtilities;

public class SeleniumElement {

	LocatorFactory locatorFactory;

	private WebElement _webElement;
	WebDriver _driver;

	private String _locatorName;

	public SeleniumElement(String locatorFile, String locatorName, WebDriver driver) {
		try {
			locatorFactory = new LocatorFactory(locatorFile, locatorName);
			_driver = driver;
			this._locatorName = locatorName;
			set_webElement(locatorFactory.getLocator());
		} catch (Exception e) {
			TestLogger.error("There is some issue with locator. Please check locator name or locator value.");
			TestLogger.error(e.getMessage());
		
		}
	}

	public SeleniumElement(String locatorFile, String locatorName, WebDriver driver, String placeHolderValue) {

		try {
			locatorFactory = new LocatorFactory(locatorFile, locatorName, placeHolderValue);
			_driver = driver;
			this._locatorName = locatorName;
			set_webElement(locatorFactory.getLocator());
		} catch (Exception e) {
			TestLogger.error("There is some issue with locator. Please check locator name or locator value.");
			
		}

	}

	public WebElement get_webElement() {
		return _webElement;
	}

	public void set_webElement(By by) {

		this._webElement = _driver.findElement(by);
	}

	public void SendKeys(String value) {
		TestLogger.debug(String.format("Entering data %s in field %s", value, _locatorName));
		_webElement.sendKeys(value);
	}

	public void Click() {
		TestLogger.debug(String.format("Clicking on field %s", _locatorName));
		_webElement.click();
	}

	public void clear() {
		TestLogger.debug(String.format("Clearing field %s", _locatorName));
		_webElement.clear();
	}

	public String getText() {
		String text = _webElement.getText();
		TestLogger.debug(String.format("Getting text from element %s is %s", _locatorName, text));
		return text;
	}

	public boolean isDisplayed() {
		return _webElement.isDisplayed();
	}

	public boolean isEnabled() {
		return _webElement.isEnabled();
	}

	public boolean isSelected() {
		return _webElement.isSelected();
	}
	
	public void javaScriptInput(String value)
	{
		JavascriptExecutor js=(JavascriptExecutor)_driver;
		js.executeScript("arguments[0].value = arguments[1];", _webElement,value);
	}
	
	public void javaScriptClick()
	{
		JavascriptExecutor js=(JavascriptExecutor)_driver;
		js.executeScript("arguments[0].click() ;", _webElement);
	}
	

}
