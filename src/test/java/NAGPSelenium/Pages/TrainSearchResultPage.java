package NAGPSelenium.Pages;

import NAGPSelenium.Base.SeleniumClient;

public class TrainSearchResultPage {
	
	SeleniumClient client;
	public final String locatorFile="TrainSearchResultObj.Properties";
	
	public TrainSearchResultPage(SeleniumClient client) {
		this.client = client;
	}
	
	public boolean isSearchResultDisplayed()
	{
		return client.Elements(locatorFile, "ResultCard_cssselector").get_webElement().size()>0 ? true : false ;
	}

}
