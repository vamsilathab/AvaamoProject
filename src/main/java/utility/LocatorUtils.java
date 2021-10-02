package utility;

import org.openqa.selenium.By;

public interface LocatorUtils {
	//Welcome Page
	public static final String welcomePage = "//h1[text()='Welcome to the Simple Travel Agency!']";
	public static final By departureCityLoc = By.cssSelector("select[name='fromPort']");
	public static final By destinationCityLoc = By.cssSelector("select[name='toPort']");
	public static final String departureCity = "select[name='fromPort']";
	public static final String destinationCity = "select[name='toPort']";
	public static final String findFlights = "input[value='Find Flights']";

	//Reservation Page
	public static final String flightListHeader = "//h3[normalize-space()='Flights from Paris to New York:']";
	public static final String flightListHeaderRow = "//table/thead/tr";
	public static final String choose = "//table//th[text()='Choose']";
	public static final String flightNumber = "//table//th[text()='Flight #']";
	public static final String airline = "//table//th[text()='Airline']";
	public static final String departs = "//table//th[contains(text(),'Departs:')]";
	public static final String arrives = "//table//th[contains(text(),'Arrives:')]";
	public static final String price = "//table//th[text()='Price']";
	public static final String flightListBodyRow = "//table/tbody/tr";//For United Airlines row, it should be //table/tbody/tr[.//td[text()='United Airlines']]
	//hardcoded for time being - Replace the value of text and preceding sibling position dynamically
	public static final String chooseFlightButton = "//td[text()='United Airlines']/preceding-sibling::*[2]/input";

	//Purchase Page
	public static final String purchasePageHeader = "//h2[normalize-space()='Your flight from TLV to SFO has been reserved.']";
	public static final String airlineName = "//p[contains(text(),'Airline:')]";
	public static final String airlineFlightNumber = "//p[contains(text(),'Flight Number:')]";
	public static final String airlinePrice = "//p[contains(text(),'Price:')]";
	public static final String airlineFees = "//p[contains(text(),'Arbitrary Fees and Taxes:')]";
	public static final String totalCost = "//p[contains(normalize-space(),'Total Cost:')]";
	public static final String nameLabel = "//label[text()='Name']";
	public static final String addressLabel = "//label[text()='Address']";
	public static final String cityLabel = "//label[text()='City']";
	public static final String stateLabel = "//label[text()='State']";
	public static final String zipCodeLabel = "//label[text()='Zip Code']";
	public static final String cardTypeLabel = "//label[text()='Card Type']";
	public static final String creditCardNumberLabel = "//label[text()='Credit Card Number']";
	public static final String creditCardMonthLabel = "//label[text()='Month']";
	public static final String creditCardYearLabel = "//label[text()='Year']";
	public static final String nameOnCardLabel = "//label[text()='Name on Card']";
	public static final String rememberMeLabel = "//label[normalize-space()='Remember me']";
	public static final String nameField = "inputName";
	public static final String addressField = "address";
	public static final String cityField = "city";
	public static final String stateField = "state";
	public static final String zipCodeField = "zipCode";
	public static final String cardTypeField = "cardType";
	public static final String creditCardNumberField = "creditCardNumber";
	public static final String creditCardMonthField = "creditCardMonth";
	public static final String creditCardYearField = "creditCardYear";
	public static final String nameOnCardField = "nameOnCard";
	public static final String rememberMeCheckBox = "rememberMe";
	public static final String purchaseSubmitButton = "input[value='Purchase Flight']";

	
	// order page
	public static final String confirmationPageHeader = "//h1[normalize-space()='Thank you for your purchase today!']";
	public static final String orderId = "//table//td[text()='Id']/following-sibling::td";
	
}
