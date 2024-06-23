package NAGPSelenium.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import NAGPSelenium.Base.SeleniumClient;
import NAGPSelenium.Helpers.TestLogger;
import NAGPSelenium.Utils.SeleniumWaits;

public class RedRailHomePage {
	
	SeleniumClient client;
	public final String locatorFile="RedRailHomeObj.Properties";
	
	public RedRailHomePage(SeleniumClient client) {
		this.client = client;
	}
	
	public String getTitle()
	{
		return client.getTitle();
	}
	
	public void clickOnPNRRadioBtn()
	{
		TestLogger.info("Clicking on PNR radio button");
		client.Element(locatorFile, "CheckPNRStatusradio_xpath").Click();
	}
	
	public void enterPNRNumber(String PNR)
	{
		TestLogger.info("Entering PNR number");
		 client.Element(locatorFile, "InputPNRTextField_xpath").SendKeys(PNR);
	}
	
	public void clickOnCheckStatusBtn()
	{
		TestLogger.info("Clicking on check Status button");
		 client.Element(locatorFile, "CheckPNRStatusBtn_xpath").Click();;
	}
	
	public void clickOnLiveTrainStatusRadioBtn()
	{
		TestLogger.info("Clicking on Live Train Status radio button");
		 client.Element(locatorFile, "LiveTrainStatusRadio_xpath").Click();;
	}
	

	
	public void enterSource(String from) throws InterruptedException
	{
		TestLogger.info("Entering value in From field.");
		client.Element(locatorFile, "From_id").clear();;
		client.Element(locatorFile, "From_id").SendKeys(from);
		Thread.sleep(1000);
		try {
			List<WebElement> elements=client.Elements(locatorFile, "StationSelect_xpath").get_webElement();
			
			for(WebElement el:elements)
			{
				if(el.getText().contains(from))
				{
					el.click();
					break;
				}
			}}catch(Exception e)
			{
				List<WebElement> elements=client.Elements(locatorFile, "StationSelect_xpath").get_webElement();
				
				for(WebElement el:elements)
				{
					if(el.getText().contains(from))
					{
						el.click();
						break;
					}
				}
			}
			
		
	
		
	}
	
	
	
	public void enterDestination(String to) throws InterruptedException
	{
		TestLogger.info("Entering value in To field.");
		client.Element(locatorFile, "To_id").clear();
		client.Element(locatorFile, "To_id").SendKeys(to);
		Thread.sleep(1000);
		try {
		List<WebElement> elements=client.Elements(locatorFile, "StationSelect_xpath").get_webElement();
		
		for(WebElement el:elements)
		{
			if(el.getText().contains(to))
			{
				el.click();
				break;
			}
		}}catch(Exception e)
		{
			List<WebElement> elements=client.Elements(locatorFile, "StationSelect_xpath").get_webElement();
			
			for(WebElement el:elements)
			{
				if(el.getText().contains(to))
				{
					el.click();
					break;
				}
			}
		}
		
	}
	
	public void clickSearch()
	{
		TestLogger.info("Clicking on search field.");
		client.Element(locatorFile, "SearchBtn_cssselector").Click();
	}

}
