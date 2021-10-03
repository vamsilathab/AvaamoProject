package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dataProvider.ExcelDataProvider;
import factory.BuildFactory;
import static utility.AppUtils.*;

public class WalletHubHomePage { 
	

	public String getApplicationTitle() {
		return getDriver().getTitle();
	}
	
	public void userLogin() {
		getWebElement(LOGIN).click();		
	}
	
	public void enterEmail(String email) {
		getWebElement(EMAIL).clear();
		getWebElement(EMAIL).sendKeys(email);	
	}
	
	public void enterPassword(String password) {
		getWebElement(PASSWORD).clear();
		getWebElement(PASSWORD).sendKeys(password);	
	}
	
	public boolean login(String email, String password, String firstName) {
		
		enterEmail(email);
		enterPassword(password);
		getWebElement(LOGIN).click();
		getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[normalize-space(text()) = '"+firstName+"']")));
		return getWebElement("//*[normalize-space(text()) = '"+firstName+"']").isDisplayed();		
	}
	
	public boolean goToReview() {
		getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(REVIEW)));
		getWebElement(REVIEW).click();
		return getWebElement(RATING_BUTTON).isDisplayed();
	}
	
	public void provideUserRating() throws InterruptedException {
		if(goToReview()) {
			getActions().moveToElement(getWebElement(RATING_FOUR)).build().perform();
			sleep(2000); // wait to see the rating selection is made and stars are highlighted.
			getActions().moveToElement(getWebElement(RATING_FOUR)).click().build().perform();
			
		}
	}
	
}
