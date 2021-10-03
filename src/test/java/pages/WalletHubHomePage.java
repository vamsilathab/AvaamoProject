package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import dataProvider.ExcelDataProvider;
import utility.AppUtils;

public class BlazeDemoPage extends AppUtils { 
	

	public String getApplicationTitle() {
		return AppUtils.getDriver().getTitle();
	}
	
	public String getSource() {
		return AppUtils.selectBy(getWebElement(departureCity));
	}
	
	public String getDestination() {
		return AppUtils.selectBy(getWebElement(destinationCity));
	}
	
	public String getBlazeDemoPageTitle() {
		return AppUtils.getWebElement(welcomePage).getText();
	}
	
	public boolean verifyFlightButton() {
		return AppUtils.verifyElementPresence(findFlights);
	}
	
	public void clickFlightButton() {
		 AppUtils.getWebElement(findFlights).click();
		 
	}
}
