package pages;

import org.openqa.selenium.By;

import utility.AppUtils;

public class OrderConfirmationPage extends PurchasePage {

	public void navigateToOrderConfirmationPage() {
		navigateToPurchasePage();
		clickPurchaseFlightButton();
	}
		
	public String getOrderConfirmationPageHeader() {		
		return AppUtils.getWebElement(confirmationPageHeader).getText();
	}
		
	public String getOrderId() {
		String orderIdNum="";
		if( AppUtils.verifyElementPresence(orderId))
		{
			orderIdNum = AppUtils.getWebElement(orderId).getText();
		}
		return orderIdNum;
	}
	
}
