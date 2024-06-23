package NAGPSelenium.Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NAGPSelenium.Base.SeleniumClient;
import NAGPSelenium.Utils.SeleniumWaits;

public class CancelTicketPage {
	
	SeleniumClient client;
	
	public final String locatorFile="CancelTicketObj.Properties";
	
	public CancelTicketPage(SeleniumClient client) {
		
		this.client = client;
	}
	
	public void enterTicketNumber(String ticketNumber)
	{
		
		String originalWindow = client.get_driver().getWindowHandle();

    
        // Wait for the new window to appear
        WebDriverWait wait = new WebDriverWait(client.get_driver(),Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new window
        for (String windowHandle : client.get_driver().getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                client.get_driver().switchTo().window(windowHandle);
                break;
            }
        }
		client.Element(locatorFile, "InputTicketNumber_xpath").SendKeys(ticketNumber);
		
	}
	
	public void enterMobileNumber(String mobileNumber)
	{
		client.Element(locatorFile, "InputMobileNumber_xpath").SendKeys(mobileNumber);;
	}
	
	public void clickSelectPassenger()
	{
		client.Element(locatorFile, "SelectPassenger_xpath").Click();
	}
	
	public String getErrorMessage()
	{
		return client.Element(locatorFile, "MsgError_xpath").getText();
	}
}
