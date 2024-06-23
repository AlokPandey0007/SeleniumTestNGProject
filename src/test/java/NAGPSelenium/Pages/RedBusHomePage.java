package NAGPSelenium.Pages;

import NAGPSelenium.Base.*;
import NAGPSelenium.Helpers.TestLogger;

public class RedBusHomePage {

	// WebDriver driver;
	SeleniumClient client;
	
	//provide path of locator property file
	String locatorFile="RedBusHomeObj.Properties";



	public RedBusHomePage(SeleniumClient client) {
		this.client = client;
	}

	public String getTitle() {
		return client.getTitle();
	}
	
	public void clickTrainTicketTab()
	{
		client.Element(locatorFile, "TrainTicketTab_id").Click();
		TestLogger.info("Clicked on train ticket tab");
	}

	public void clickOnBookTrainTicketButton()
	{
		TestLogger.info("Clicking on Book Train Ticket Button");
		client.Element(locatorFile, "LinkBookTrainticket_xpath").Click();
	}
	
	public void clickOnHelpButton()
	{
		TestLogger.info("Clicking on Book Train Ticket Button");
		client.Element(locatorFile, "LinkHelp_xpath").Click();
	}
	
	public void clickOnAccountDropDown()
	{
	TestLogger.info("Clicking on Account Dropdrown");	
	client.Element(locatorFile, "AccountDropDown_id").Click();
	}
	
	public void clickCancelTicket()
	{
		TestLogger.info("Clicking on Cancel Ticket");	
		client.Element(locatorFile, "CancelTicket_id").Click();
	}
	
	
	
}
