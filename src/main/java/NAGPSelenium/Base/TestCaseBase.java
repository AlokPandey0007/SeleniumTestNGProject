package NAGPSelenium.Base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.*;

import NAGPSelenium.Helpers.TestListeners;
import NAGPSelenium.Helpers.TestLogger;
import NAGPSelenium.Helpers.TestReport;
import NAGPSelenium.Utils.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import NAGPSelenium.Utils.PropertyReader;

@Listeners(TestListeners.class)
public class TestCaseBase {

	protected WebDriver driver;

	public static ThreadLocal<SeleniumClient> client = new ThreadLocal<SeleniumClient>();

	public static ExtentReports extent;

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	public static TestContext testContext=new TestContext();

	public TestCaseBase() {
		
	}

	@BeforeSuite
	public void BeforeSuite() {
		
		TestLogger.info("Creating extent report instance");
		extent = new TestReport().GetReporter();
	}

	@BeforeMethod
	public void BeforeMethod(ITestContext testcontext) {
		try {
			TestLogger.info("Opening Browser");
			client.set(new SeleniumClient());
			String url = PropertyReader.ReadConfiguration("Url");

			TestLogger.info("Entering Url " + url);
			client.get().NavigateTo(url);
		} catch (Exception e) {
			TestLogger.error(e.getMessage());
		}
	}

	@AfterMethod
	public void AfterMethod(ITestResult result) {
		client.get().quit();
	}


	@AfterSuite
	public void AfterSuite() {

		extent.flush();
	}

}
