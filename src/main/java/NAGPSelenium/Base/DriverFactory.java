package NAGPSelenium.Base;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import NAGPSelenium.Helpers.TestLogger;

public class DriverFactory {

	private String _browser;

	private WebDriver driver;

	enum Browser {
		Chrome, Firefox, Safari
	}

	public DriverFactory(String browser) {
		this._browser = browser;
		DriverInit(browser);
	}

	public WebDriver GetDriver() {
		return driver;
	}

	public WebDriver DriverInit(String browser) {
		TestLogger.info(String.format("Creating instance of browser %s", browser));
		if (browser.equalsIgnoreCase(Browser.Chrome.toString())) {
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			options.setPageLoadTimeout(Duration.ofSeconds(15));
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase(Browser.Firefox.toString())) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("start-maximized");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setPageLoadTimeout(Duration.ofSeconds(15));

			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase(Browser.Safari.toString())) {
			SafariOptions options = new SafariOptions();
			options.setPageLoadTimeout(Duration.ofSeconds(15));
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new SafariDriver(options);
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

}
