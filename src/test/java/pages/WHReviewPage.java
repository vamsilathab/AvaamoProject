package pages;

import static utility.AppUtils.*;
import static utility.LocatorUtils.REVIEW;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WHReviewPage { 
		
	public boolean getReviewPageHeader() {
		return getWebElement(REVIEW_PAGE_HEADER).isDisplayed();
	}
	
	public void selectDrodown(String option) {
		String POLICY_OPTIONS = "//span[@class='dropdown-selected']/following-sibling::ul[@role='listbox']/li[text()='"+option+"']";
		 getWebElement(POLICY_DROPDOWN).click();
		 getActions().moveToElement(getWebElement(POLICY_OPTIONS)).click().build().perform();	
		 getWebElement(REVIEW_TEXT).click();
	}
	
	public boolean inputReviewContent(String content) {
		if(content.toCharArray().length>=200) {
			getWebElement(REVIEW_TEXT).sendKeys(content);
		}		
		System.out.println("content count: "+getWebElement(REVIEW_TEXT_COUNT).getText());
		return !getWebElement(REVIEW_TEXT_COUNT).getText().isEmpty() && Integer.parseInt(getWebElement(REVIEW_TEXT_COUNT).getText())>=200;
	}
	
	
	public void submitReview() {
		getWebElement(SUBMIT).click();	
		getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(REVIEW_CONFIRMATION_MSG)));
		if(getWebElement(REVIEW_CONFIRMATION_MSG).isDisplayed())
		getWebElement(CONTINUE_BUTTON).click();
	
	}
	/* public boolean selectValueInDrodown(String option) {
		String POLICY_OPTIONS = "//span[@class='dropdown-selected']/following-sibling::ul[@role='listbox']/li[text()='"+option+"']";
		//getWebElement("//span[@class='dropdown-selected']/following-sibling::ul[@role='listbox']/li[text()='"+option+"'] | //div[contains(@class, 'dropdown')]//span[contains(text(),'Select')]").click();
		getActions().doubleClick(getWebElement("//div[@class='dropdown second opened']"));
		getActions().moveToElement(getWebElement(POLICY_OPTIONS)).click().build().perform();	
	    return getWebElement("//div[contains(@class, 'dropdown')]//span[contains(text(),'"+option+"')]").isDisplayed();
	} */
	
}
