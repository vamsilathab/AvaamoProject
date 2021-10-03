package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dataProvider.ExcelDataProvider;
import factory.BuildFactory;
import static utility.AppUtils.*;
import static utility.LocatorUtils.REVIEW_CONFIRMATION_MSG;

public class WHHomePage { 
	

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
		if(getWebElement(RATING_BUTTON).isDisplayed()) {
			getActions().moveToElement(getWebElement(RATING_FOUR)).build().perform();
			sleep(2000); // wait to see the rating is selected and stars are highlighted.
			getActions().moveToElement(getWebElement(RATING_FOUR)).click().build().perform();
			
		}
	}
	
	public boolean verifySubmittedReview(String username) throws InterruptedException {		
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebElement(CURRENT_REVIEW));
		Thread.sleep(2000);
		getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CURRENT_REVIEW)));
		return getWebElement(CURRENT_REVIEW).isDisplayed() && getWebElement("//span[text()='@"+username+"']").isDisplayed();
	}
	
}
