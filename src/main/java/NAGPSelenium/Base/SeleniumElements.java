package NAGPSelenium.Base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import NAGPSelenium.Utils.GeneralUtilities;

public class SeleniumElements {

	LocatorFactory locatorFactory;

	private List<WebElement> _webElement;

	WebDriver _driver;

	public SeleniumElements(String locatorFile, String locatorName, WebDriver driver) {

		locatorFactory = new LocatorFactory(locatorFile, locatorName);
		_driver = driver;
		set_webElement(locatorFactory.getLocator());

	}

	public List<WebElement> get_webElement() {
		return _webElement;
	}

	public void set_webElement(By by) {
		this._webElement = _driver.findElements(by);

	}

}
