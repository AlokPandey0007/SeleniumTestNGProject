package NAGPSelenium.Helpers;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Test;

import NAGPSelenium.Helpers.*;
import NAGPSelenium.Utils.ScreenshotUtil;
import NAGPSelenium.Base.TestCaseBase;

public class TestListeners extends TestCaseBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.get().pass("Test case passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		
		String screenshotFileName=result.getName() + "_Invalid";
		String contextKey=result.getName().concat("_testData");
		
		boolean hasAppender=testContext.get_attributes().containsKey(contextKey)	;
		if(hasAppender)		
		{
		
			screenshotFileName=(String)testContext.get_attribute(contextKey);
			TestLogger.info("Taking screenshot with name "+screenshotFileName);
		}
		
		if (result.getStatus() == ITestResult.FAILURE) {

			TestLogger.info("Test case " + result.getName() + " failed.");

			String path = ScreenshotUtil.captureScreenshot(client.get().get_driver(),screenshotFileName );
			test.get().fail("Test case failed. Please refer screenshot below");
			TestLogger.info("Adding screenshot to failed test case");
			test.get().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.get().log(Status.SKIP, "Test case has been either skipped OR Retried.");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
