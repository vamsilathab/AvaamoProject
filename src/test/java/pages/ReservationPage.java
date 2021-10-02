package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.AppUtils;

public class ReservationPage extends BlazeDemoPage {

	public void navigateToReservationPage() {
		//getWebDriverWait().until(ExpectedConditions.titleContains(title));
		verifyFlightButton();
		clickFlightButton();
	}
	
	public void navigateToReservationPage(String title) {
		getWebDriverWait().until(ExpectedConditions.titleContains(title));
		verifyFlightButton();
		clickFlightButton();
	}
		
	public String getReservationPageHeader(String source, String destintion) {
		return AppUtils.getWebElement("//h3[normalize-space()='Flights from "+source+" to "+destintion+":']").getText();
	}
		
	public boolean verifyChooseFlightButton() {
		return AppUtils.verifyElementPresence(chooseFlightButton);
	}
	
	public void clickChooseFlightButton() {
		 AppUtils.getWebElement(chooseFlightButton).click();
		 
	}
}
