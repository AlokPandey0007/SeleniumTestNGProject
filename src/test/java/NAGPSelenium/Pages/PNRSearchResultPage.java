package NAGPSelenium.Pages;

import org.openqa.selenium.WebElement;

import NAGPSelenium.Base.SeleniumClient;
import NAGPSelenium.Utils.SeleniumWaits;

public class PNRSearchResultPage {
	
	SeleniumClient client;
	public final String locatorFile="PNRSearchResultObj.Properties";
	
	public PNRSearchResultPage(SeleniumClient client) {
		this.client = client;
	}
	
	public String getMessageWhenResultNotFound()
	{
		WebElement el=client.Element(locatorFile, "NoResultMessage_xpath").get_webElement();
		//SeleniumWaits.visibilityOfElement(client.get_driver(), el);
		
		return client.Element(locatorFile, "NoResultMessage_xpath").getText();
	}

}
