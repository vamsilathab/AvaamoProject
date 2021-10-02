package utility;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import driverFactory.DriverBase;


public class AppUtils extends DriverBase implements LocatorUtils {
	protected static final Logger logger = Logger.getLogger(AppUtils.class);
	public static final String browser = "chrome"; //hardcoded browser for testing
			
					
	 public static WebDriver getDriver () {
	        return DriverBase.getDriver(browser);
	   }
	 
	 public static WebDriverWait getWebDriverWait (int time) {
	        return new WebDriverWait(getDriver(browser), time);
       }
	    
    public static WebDriverWait getWebDriverWait () {
        return new WebDriverWait(getDriver(browser), 60);
    }

    public static WebElement getWebElement (String primaryLocator) {
        try {
            return findElement(primaryLocator);
        } catch (NoSuchElementException e) {
            logger.error("Element with locators not found : " + primaryLocator);
            }
        throw new NoSuchElementException("Element with locators not found : " + primaryLocator);
    }
	
	private static WebElement findElement (String locator) {
	
        Map<By, String> locatorsMap = new LinkedHashMap<>();
        locatorsMap.put(By.cssSelector("[data-auto-id='" + locator + "']"), "dataAutoID");
        locatorsMap.put(By.cssSelector(locator), "cssSelector");
        locatorsMap.put(By.xpath(locator), "xpath");
        locatorsMap.put(By.id(locator), "id");
        locatorsMap.put(By.className(locator), "className");
        locatorsMap.put(By.linkText(locator), "linkText");

        for (Map.Entry<By, String> entry : locatorsMap.entrySet()) {
            try {
                WebElement element = getDriver().findElement(entry.getKey());
               // System.out.println("Fetching element with the locator : " + locator + " by --> " + entry.getValue());
                return element;
            } catch (NoSuchElementException ignored) {
            }
        }
        throw new NoSuchElementException("Element with locator not found : " + locator);
    }
	
	public static boolean verifyElementPresence(String locator) {
		return getWebElement(locator).isDisplayed() && getWebElement(locator).isEnabled();
	}
	
	public String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }
	
	public static String selectBy(WebElement locator) {
		Select select = new Select(locator);		
		return select.getFirstSelectedOption().getText().trim();
	}
	
}
