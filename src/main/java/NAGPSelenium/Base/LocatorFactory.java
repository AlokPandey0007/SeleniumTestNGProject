package NAGPSelenium.Base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import NAGPSelenium.Helpers.TestLogger;
import NAGPSelenium.Utils.GeneralUtilities;
import NAGPSelenium.Utils.PropertyReader;

public class LocatorFactory {

	String locatorName;

	PropertyReader properties;
	WebDriver _driver;
	private By Locator;

	public void setLocator(By locator) {
		Locator = locator;
	}

	public By getLocator() {
		return Locator;
	}

	public LocatorFactory(String locatorFile, String locatorName) {
		TestLogger.info(String.format("Looking for locator %s in file %s", locatorName, locatorFile));
		this.locatorName = locatorName;
		locatorFile = getAbsolutePathForLocatorFile(locatorFile);
		properties = new PropertyReader(locatorFile);
		try {
			getElement();
		} catch (Exception e) {
			TestLogger.error(e.getMessage());
		}

	}
	
	public LocatorFactory(String locatorFile, String locatorName,String placeHolderValue) {
		TestLogger.info(String.format("Looking for locator %s in file %s", locatorName, locatorFile));
		this.locatorName = locatorName;
		locatorFile = getAbsolutePathForLocatorFile(locatorFile);
		properties = new PropertyReader(locatorFile);
		try {
			getElement(placeHolderValue);
		} catch (Exception e) {
			TestLogger.error(e.getMessage());
		}

	}
	
	

	private Map<String, String> readAllLocators() {
		Properties prop = properties.GetProperty();
		Map<String, String> dictionary = new HashMap<>();

		// Populate the HashMap with locators
		for (String key : prop.stringPropertyNames()) {
			String value = prop.getProperty(key);
		
			dictionary.put(key, value);
		}
		return dictionary;
	}

	private void getElement() throws Exception {

		Map<String, String> locators = readAllLocators();
		By locator = null;
		try {
			String locatorValue = locators.get(locatorName);

			String locatorType = locatorName.split("_")[1].toLowerCase();

			switch (locatorType) {
			case "id":
				locator = By.id(locatorValue);
				break;
			case "linktext":
				locator = By.linkText(locatorValue);
				break;
			case "cssselector":
				locator = By.cssSelector(locatorValue);
				break;
			case "classname":
				locator = By.className(locatorValue);
				break;
			case "name":
				locator = By.name(locatorValue);
				break;
			case "partiallinktext":
				locator = By.partialLinkText(locatorValue);
				break;
			case "tagname":
				locator = By.tagName(locatorValue);
				break;
			case "xpath":
				locator = By.xpath(locatorValue);
				break;
			default:
				throw new Exception("Invalid Locator Type");

			}
		} catch (Exception e) {
			TestLogger.error(e.getMessage());
		}
		setLocator(locator);
	}
	
	private void getElement(String placeHolderValue) throws Exception {

		Map<String, String> locators = readAllLocators();
		By locator = null;
		try {
			String locatorValue = locators.get(locatorName);
			
			if(locatorValue.contains("textToReplace"))
			{
				locatorValue.replaceAll("textToReplace", placeHolderValue);
			}
			TestLogger.debug(locatorValue);
			String locatorType = locatorName.split("_")[1].toLowerCase();

			switch (locatorType) {
			case "id":
				locator = By.id(locatorValue);
				break;
			case "linktext":
				locator = By.linkText(locatorValue);
				break;
			case "cssselector":
				locator = By.cssSelector(locatorValue);
				break;
			case "classname":
				locator = By.className(locatorValue);
				break;
			case "name":
				locator = By.name(locatorValue);
				break;
			case "partiallinktext":
				locator = By.partialLinkText(locatorValue);
				break;
			case "tagname":
				locator = By.tagName(locatorValue);
				break;
			case "xpath":
				locator = By.xpath(locatorValue);
				break;
			default:
				throw new Exception("Invalid Locator Type");

			}
		} catch (Exception e) {
			TestLogger.error(e.getMessage());
		}
		setLocator(locator);
	}

	private String getAbsolutePathForLocatorFile(String locatorFile) {

		String filePath = "/src/test/java/SeleniumNAGP/ObjectRepository/" + locatorFile;

		return filePath;

	}

}
