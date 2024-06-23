package NAGPSelenium.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.aventstack.extentreports.Status;

import NAGPSelenium.Base.TestCaseBase;
import NAGPSelenium.Helpers.TestRetry;
import NAGPSelenium.Pages.RedBusHomePage;
import NAGPSelenium.Pages.CancelTicketPage;
import NAGPSelenium.Utils.PropertyReader;

public class CancelTicketTest extends TestCaseBase{
	
	
	PropertyReader testData; 
	RedBusHomePage redBusHomePage;
	CancelTicketPage cancelTicketPage;


		
		public CancelTicketTest()
		{
			
			testData=new PropertyReader("/src/test/java/NAGPSelenium/TestData/CancelTicketTestData.Properties");
		}
		
		@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity"})
		public void verifyThatUserCannotFetchHisTicketWithInvalidDetails()
		{
			test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
			
			redBusHomePage=new RedBusHomePage(client.get());
			cancelTicketPage=new CancelTicketPage(client.get());
			
			
			redBusHomePage.clickOnAccountDropDown();
			test.get().log(Status.INFO, "Clicked on Account dropdown");
			
			redBusHomePage.clickCancelTicket();
			test.get().log(Status.INFO, "Clicked on Show My Ticket option");
			
			
			String ticketNumber=testData.ReadProperty("TicketNumber");
			cancelTicketPage.enterTicketNumber(ticketNumber);
			test.get().log(Status.INFO, "Entered ticket number - "+ticketNumber);
		
			String mobileNumber=testData.ReadProperty("MobileNumber");
			cancelTicketPage.enterMobileNumber(mobileNumber);
			test.get().log(Status.INFO, "Entered mobile number - "+mobileNumber);
			
			cancelTicketPage.clickSelectPassenger();
			
			String expectedMessage=testData.ReadProperty("ExpectedErrorMessage");
			String actualMessage=cancelTicketPage.getErrorMessage();
			
			test.get().log(Status.INFO, String.format("Actual error message is %s", actualMessage));
			test.get().log(Status.INFO, String.format("Expected error message is %s", expectedMessage));
			
			AssertJUnit.assertEquals(actualMessage, expectedMessage);
		}

}
