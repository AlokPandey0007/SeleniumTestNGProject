package NAGPSelenium.Helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer {

	private int retryCount = 0;
	private static final int maxRetryCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		
	
	
		TestLogger.info("Test case " + result.getName() + " failed  going for retry.");
		if (retryCount < maxRetryCount) {
			retryCount++;
			TestLogger.info("Retrying " + result.getName() + " for " + retryCount + " times");
			return true;
		}
		return false;
	
	
	}

}
