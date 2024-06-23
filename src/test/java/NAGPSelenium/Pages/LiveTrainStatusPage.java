package NAGPSelenium.Pages;

import org.openqa.selenium.devtools.SeleniumCdpConnection;

import NAGPSelenium.Base.SeleniumClient;
import NAGPSelenium.Utils.SeleniumWaits;

public class LiveTrainStatusPage {

	SeleniumClient client;

	// provide path of locator property file
	String locatorFile = "LiveTrainStatusObj.Properties";

	public LiveTrainStatusPage(SeleniumClient client) {
		this.client = client;
	}

	public void enterTrainNumber(String trainNumber) {
		client.Element(locatorFile, "inputTrainNumber_xpath").SendKeys(trainNumber);

		SeleniumWaits.textToBePresentInElement(client.get_driver(), client.Element(locatorFile, "selectTrainList_xpath").get_webElement(),trainNumber);
		client.Element(locatorFile, "selectTrainList_xpath").Click();
		
	}
	
	public void clickCheckStatusBtn()
	{
		client.Element(locatorFile, "CheckStatusBtn_xpath").Click();
	}
	
	public String getSearchResultPageHeader()
	{
	 return client.Element(locatorFile, "SearchResultPageHeader_xpath").getText();
	}

}
