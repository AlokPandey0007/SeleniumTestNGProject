package NAGPSelenium.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import NAGPSelenium.Helpers.TestLogger;
import NAGPSelenium.Utils.PropertyReader;

public class SeleniumClient {

	private WebDriver _driver;

	public WebDriver get_driver() {
		return _driver;
	}

	private WebElement element;

	public SeleniumClient() {
		_driver = new DriverFactory(PropertyReader.ReadConfiguration("Browser")).GetDriver();

	}

	public SeleniumElement Element(String locatorFile, String locatorName) {
		return new SeleniumElement(locatorFile, locatorName, _driver);
	}
	
	public SeleniumElement Element(String locatorFile, String locatorName,String placeHolderValue) {
		return new SeleniumElement(locatorFile, locatorName, _driver, placeHolderValue);
	}

	public SeleniumElements Elements(String locatorFile, String locatorName) {
		return new SeleniumElements(locatorFile, locatorName, _driver);
	}

	public String getTitle() {

		return _driver.getTitle();
	}

	public void NavigateTo(String url) {
		TestLogger.info("Navigating to Url - " + url);
		_driver.get(url);
	}

	public void quit() {
		TestLogger.info("Quitting browser");
		_driver.quit();
	}

	public void close() {
		TestLogger.info("Closing browser");
		_driver.close();
	}

}
