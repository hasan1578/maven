package uk.co.rightmove.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import uk.co.rightmove.util.Testutil;
import uk.co.rightmove.util.WebEventListener;

public class TestBase {
	protected static WebDriver driver;
	private static Properties prop;
	private static EventFiringWebDriver e_driver;
	private static WebEventListener eventListener;
	private static final String BROWSER_CHROME = "chrome";
	private static final String BROWSER_FIREFOX = "firefox";
	
	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/main/java/uk/co/rightmove/config/config.properties";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(CONFIG_FILE_PATH);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Initializing the Page Object
	protected void initialization(String url, String browser) {

		//String browserName = prop.getProperty("browser");
		String browserName = browser;
		if (browserName.equals(BROWSER_CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals(BROWSER_FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Testutil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Testutil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		// driver.get(prop.getProperty("url"));
		System.out.println("URL is " + url);
		driver.get(url);

	}

	protected String validatePageTitle() {
		return driver.getTitle();
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

}
