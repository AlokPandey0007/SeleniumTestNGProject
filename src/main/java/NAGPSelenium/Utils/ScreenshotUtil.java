package NAGPSelenium.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import NAGPSelenium.Helpers.TestLogger;

public class ScreenshotUtil {
	
	
	//this method takes driver instance and name of screenshot and returns the path where screenshot is created.
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		
		String path = null;
		TestLogger.trace("Capturing screenshot with name "+screenshotName);
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destPath = GeneralUtilities.GetProjectPath()+"/Screenshots/"+ screenshotName + ".png";
            File destination = new File(destPath);
            FileUtils.copyFile(source, destination);
            path=destPath;
        } catch (IOException e) {
        	TestLogger.error(e.getMessage());
        }
        return path;
    }

}
