package NAGPSelenium.Utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWaits {
	
	
	private static int WaitDuration=Integer.parseInt(PropertyReader.ReadConfiguration("ExplicitWait"));
	public void SeleniumWaits() {
	
	}
	
		public static void waitForElementPresent(WebDriver driver, By by) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDuration));
		     wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		
		public static void elementToBeClickable(WebDriver driver, WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDuration));
		     wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		public static void elementToBeSelected(WebDriver driver, WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDuration));
		     wait.until(ExpectedConditions.elementToBeSelected(element));
		}
		
		public static void visibilityOfElement(WebDriver driver, WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDuration));
		     wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		//This is to apply fluent wait on element. It return the WebElement.
		public static WebElement fluentWait(WebDriver driver,WebElement element)
		{
			Wait<WebDriver> wait = new FluentWait<>(driver)
			        .withTimeout(Duration.ofSeconds(WaitDuration))
			        .pollingEvery(Duration.ofSeconds(5))
			        .ignoring(NoSuchElementException.class)
			        .ignoring(StaleElementReferenceException.class);
					
			WebElement el = wait.until(x-> element);
			
			return el;
			
		}
		
		public static void textToBePresentInElement(WebDriver driver, WebElement element,String text) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDuration));
		     wait.until(ExpectedConditions.textToBePresentInElement(element,text));
		}
		
	
	

}
