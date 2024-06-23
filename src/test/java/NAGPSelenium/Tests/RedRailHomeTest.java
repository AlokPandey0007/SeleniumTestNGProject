package NAGPSelenium.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import NAGPSelenium.Base.TestCaseBase;
import NAGPSelenium.Helpers.TestRetry;
import NAGPSelenium.Pages.PNRSearchResultPage;
import NAGPSelenium.Pages.RedBusHomePage;
import NAGPSelenium.Pages.RedRailHomePage;
import NAGPSelenium.Pages.TrainSearchResultPage;
import NAGPSelenium.Utils.PropertyReader;

public class RedRailHomeTest extends TestCaseBase{
	
PropertyReader testData; 
RedBusHomePage redBusHomePage;
RedRailHomePage redRailHomePage;
TrainSearchResultPage trainSearchResultPage;
PNRSearchResultPage pnrSearchResultPage;


	
	public RedRailHomeTest()
	{
		
		testData=new PropertyReader("/src/test/java/NAGPSelenium/TestData/RedRailHomeTestData.Properties");
	}

	
	@Test(groups= {"Sanity"},retryAnalyzer = TestRetry.class)
	public void verifyRedRailHomeTitle()
	{
	 
		test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
		
		test.get().log(Status.INFO, "Verifying title of the RedRail Tab");
		
		redBusHomePage=new RedBusHomePage(client.get());
		redBusHomePage.clickTrainTicketTab();
		redRailHomePage =new RedRailHomePage(client.get());
		
		String expectedTitle=testData.ReadProperty("PageTitle");
		String actualTitle=redRailHomePage.getTitle();
		
		test.get().log(Status.INFO, String.format("Expected page title is %s",expectedTitle));
		test.get().log(Status.INFO, String.format("Actual page title is %s",actualTitle));
		
		AssertJUnit.assertEquals(actualTitle,expectedTitle);
		
	}
	
	@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity","Regression"}) 
	public void verifyThatUserIsNotAbleToSearchWithIncorrectPNR()
	{

		test.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
	
		redBusHomePage=new RedBusHomePage(client.get());
		redRailHomePage =new RedRailHomePage(client.get());
		pnrSearchResultPage=new PNRSearchResultPage(client.get());
		
		redBusHomePage.clickTrainTicketTab();
		test.get().log(Status.INFO, "Clicked train ticket tab.");
		
		redRailHomePage.clickOnPNRRadioBtn();
		test.get().log(Status.INFO, "Clicked PNR radio button.");
		
		String PNR=testData.ReadProperty("PNR");
		
		redRailHomePage.enterPNRNumber(PNR);
		test.get().log(Status.INFO, String.format("Entered PNR number %s",PNR));
		
		redRailHomePage.clickOnCheckStatusBtn();
		test.get().log(Status.INFO, "Clicked on Check Status Button");
		
		String actualMessage=pnrSearchResultPage.getMessageWhenResultNotFound();
		test.get().log(Status.INFO, "Actual message is - "+actualMessage);
		
		String expectedMessage=testData.ReadProperty("NoResultMessage");
		test.get().log(Status.INFO, "Expected partial text is - "+expectedMessage);
		
		
		AssertJUnit.assertTrue(actualMessage.contains(expectedMessage));
		
		
		
	}
	
	
	@Test(dataProvider="TrainSearch",retryAnalyzer = TestRetry.class,groups= {"Regression"})
	public void verifyThatUserCanSearchTheTrains(String from,String to) throws InterruptedException
	{
		String testName=new Object() {}.getClass().getEnclosingMethod().getName();
		
		String screenShotAppender=testName.concat("_".concat(from.concat("_".concat(to))));
		testContext.set_attribute(testName+"_testData",screenShotAppender );
		
		
		test.set(extent.createTest(testName.concat("_".concat(from.concat("_".concat(to))))));
		
		test.get().log(Status.INFO, "Verify that user search trains.");
		redBusHomePage=new RedBusHomePage(client.get());
		redRailHomePage =new RedRailHomePage(client.get());
		trainSearchResultPage=new TrainSearchResultPage(client.get());
		
		redBusHomePage.clickTrainTicketTab();
		test.get().log(Status.INFO, "Clicked train ticket tab.");
		
		redRailHomePage.enterSource(from);
		test.get().log(Status.INFO, String.format("Enter value in From field - %s", from));
		
		redRailHomePage.enterDestination(to);
		test.get().log(Status.INFO, String.format("Enter value in To field - %s", from));
		
		redRailHomePage.clickSearch();
		test.get().log(Status.INFO, "Clicked on search button.");
		
		boolean isSearchResultDisplayed=trainSearchResultPage.isSearchResultDisplayed();
		
		AssertJUnit.assertTrue(isSearchResultDisplayed);
		
		
	}
	

	
	@DataProvider(name="TrainSearch")
	public Object[][] trainSearchDP()
	{
		return new Object[][] {
			
			{"Delhi","Chandigarh"},{"Chandigarh","Lucknow"}
		};
	}
}
