package NAGPSelenium.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import NAGPSelenium.Base.TestCaseBase;
import NAGPSelenium.Helpers.TestReport;
import NAGPSelenium.Helpers.TestRetry;
import NAGPSelenium.Pages.LiveTrainStatusPage;
import NAGPSelenium.Pages.RedBusHomePage;
import NAGPSelenium.Pages.RedRailHomePage;
import NAGPSelenium.Utils.PropertyReader;

public class LiveTrainStatusTest extends TestCaseBase {
	
	PropertyReader testData; 
	RedBusHomePage redBusHomePage;
	RedRailHomePage redRailHomePage;
	LiveTrainStatusPage liveTrainStatusPage;
	


		
		public LiveTrainStatusTest()
		{
			
			testData=new PropertyReader("/src/test/java/NAGPSelenium/TestData/LiveTrainStatusTestData.Properties");
		}
		
		
		@Test(retryAnalyzer = TestRetry.class,groups= {"Sanity"})
		public void verifyUserCanCheckLiveTrainStatus()
		{
			String testName=new Object() {}.getClass().getEnclosingMethod().getName();
			test.set(extent.createTest(testName));
			
			redBusHomePage=new RedBusHomePage(client.get());
			redRailHomePage =new RedRailHomePage(client.get());
			liveTrainStatusPage=new LiveTrainStatusPage(client.get());
		
			
			
			
			redBusHomePage.clickTrainTicketTab();
			test.get().log(Status.INFO, "Clicked train ticket tab.");
			
			redRailHomePage.clickOnLiveTrainStatusRadioBtn();
			test.get().log(Status.INFO, "Clicked on live train status radio button.");
			
			String trainNumber=testData.ReadProperty("TrainNumber");
			liveTrainStatusPage.enterTrainNumber(trainNumber);
			test.get().log(Status.INFO, "Entered train number - "+trainNumber);
			
			
			liveTrainStatusPage.clickCheckStatusBtn();
			
			test.get().log(Status.INFO, "Clicked on check status button.");
			
		String pageHeader=	liveTrainStatusPage.getSearchResultPageHeader();
		
		test.get().log(Status.INFO, "Page header is - "+pageHeader);
		
		
		AssertJUnit.assertTrue(pageHeader.contains(trainNumber));
			
			
			
		}


}
