package driverFactory;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverBase {
	private static final Logger logger = Logger.getLogger(DriverBase.class);
	 static WebDriver driver;
 
	 @BeforeMethod(alwaysRun = true)
	public static WebDriver getDriver(String browser) {
		 if(driver== null) {
			 if(browser.equalsIgnoreCase("Chrome")) {		
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}			
				else if(browser.equalsIgnoreCase("Firefox")) {			
					WebDriverManager.firefoxdriver().setup();
					driver=new FirefoxDriver();
				}			
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 }
				return driver;	
	 }
	 
	 @AfterMethod(alwaysRun = true)
	public static void quitDriver() {
		driver.quit();
	}
	
}
