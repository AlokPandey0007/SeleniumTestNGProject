package NAGPSelenium.Tests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import NAGPSelenium.Base.TestCaseBase;
import NAGPSelenium.Helpers.TestListeners;
import NAGPSelenium.Helpers.TestRetry;
import NAGPSelenium.Pages.RedBusHomePage;
import NAGPSelenium.Pages.RedRailHomePage;
import NAGPSelenium.Utils.PropertyReader;

@Listeners(TestListeners.class)

public class RedBusHomeTest extends TestCaseBase{
	
	RedBusHomePage busTicketHomePage;
	PropertyReader testData; 
	RedRailHomePage railHomePage;
	
	public RedBusHomeTest()
	{
		
		testData=new PropertyReader("/src/test/java/NAGPSelenium/TestData/RedBusHomeTestData.Properties");
	}
	
	@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity"})
	public void VerifyBusTicketHomePageTitle()
	{
		test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
	
		busTicketHomePage=new RedBusHomePage(client.get());
		
		
		
		String actualTitle=busTicketHomePage.getTitle();
		String expectedTitle=testData.ReadProperty("PageTitle");
		test.get().log(Status.INFO, String.format("Expected title of the page is %s", expectedTitle));
		test.get().log(Status.INFO, String.format("Actual title of the page is %s", actualTitle));
		
		test.get().log(Status.INFO, "Verifying the Page title");
		AssertJUnit.assertEquals(actualTitle,expectedTitle );
	
		
	}
	
	@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity"})
	public void verifyThatBookTrainTicketButtonTakesToTrainBookingScreen()
	{
		test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
		
		busTicketHomePage=new RedBusHomePage(client.get());
		railHomePage=new RedRailHomePage(client.get());
		busTicketHomePage.clickOnBookTrainTicketButton();
		test.get().log(Status.INFO, "Clicking on the help button.");
		
		String actualTitle=railHomePage.getTitle();
		test.get().log(Status.INFO, "Actual page title is _ "+actualTitle);
		
		String expectedTitle=testData.ReadProperty("RailRedPageTitle");
		test.get().log(Status.INFO, "Expected page title is _ "+expectedTitle);
		
		AssertJUnit.assertEquals(actualTitle,expectedTitle );
		
		
	}	
	
	@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity"})
	public void verifyThatHelpButtonTakesToRedCareScreen()
	{
		test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
		
		busTicketHomePage=new RedBusHomePage(client.get());
		railHomePage=new RedRailHomePage(client.get());
		
		busTicketHomePage.clickOnBookTrainTicketButton();
		test.get().log(Status.INFO, "Clicking on Book Train Ticket button.");
		
		String actualTitle=client.get().getTitle();
		test.get().log(Status.INFO, "Actual page title is _ "+actualTitle);
		
		String expectedTitle=testData.ReadProperty("RailRedPageTitle");
		test.get().log(Status.INFO, "Expected page title is _ "+expectedTitle);
		
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}
	

	
	
	

}
