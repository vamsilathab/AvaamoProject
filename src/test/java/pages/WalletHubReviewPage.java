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

public class WalletHubReviewPage { 
	

	public String getApplicationTitle() {
		return getDriver().getTitle();
	}
	
	public boolean getReviewPageHeader() {
		return getWebElement(REVIEW_PAGE_HEADER).isDisplayed();
	}
	
	public void provideUserRating() throws InterruptedException {
		if(goToReview()) {
			getActions().moveToElement(getWebElement(RATING_FOUR)).build().perform();
			sleep(2000); // wait to see the rating selection is made and stars are highlighted.
			getActions().moveToElement(getWebElement(RATING_FOUR)).click().build().perform();
			
		}
	}
	
}
