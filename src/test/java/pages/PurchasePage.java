package pages;

import org.openqa.selenium.By;

import utility.AppUtils;

public class PurchasePage extends ReservationPage {

	public void navigateToPurchasePage() {
		navigateToReservationPage();
		clickChooseFlightButton();
	}
		
	public String getPurchasePageHeader() {
		return AppUtils.getWebElement(purchasePageHeader).getText();
	}
		
	public boolean verifyPurchaseFlightButton() {
		return AppUtils.verifyElementPresence(purchaseSubmitButton);
	}
	
	public void clickPurchaseFlightButton() {
		 AppUtils.getWebElement(purchaseSubmitButton).click();
		 
	}
}
