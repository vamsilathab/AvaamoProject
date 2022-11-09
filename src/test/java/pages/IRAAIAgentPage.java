package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static utility.AppUtils.*;
import java.util.ArrayList;
import java.util.List;


public class IRAAIAgentPage { 

	//BOT SECTION 
		public static final String ICON = "//div[@aria-label='Test agent - IRA']/a[@class='avm-bot-avatar']";
		
		public static final String BANNER="//div[@class='showcase-banner']";
		public static final String NOTICATION_MSG = "//div[@class='welcome-message']/p";
		public static final String GET_STARTED="//div[@class='avm-bot-get-started']//a[text()='Get Started']";
		public static final String LOADER = "//div[@class='spinner-cont']/h5[text()='Loading']";
		
		//ENTER INTO BOT
		public static final String AVAAMO_FRAME="avaamoIframe";
		public static final String BOT_LAUNCHED = "//div[@id='bot-menu-container']";
		public static final String KEYBOARD="//div[@id='bot-menu-container']//button[@aria-label='Toggle Menu']";
		
		//GREETING MSG
		public static final String ENTER_TEXT="//textarea[@id='queryTextbox']";
		public static final String GREETINGS_MSG = "Good Day!";
		public static final String SEND_TEXT = "//button[text()='Send']";
		public static final String BOT_TEXT="//div[@aria-label='You sent, Text."+GREETINGS_MSG+"']/following-sibling::div//p";
		
		//STARTOVER BUTTON
		public static final String SWITCH_MENU="//button[@data-ele-name='switch_menu']";
		public static final String START_OVER="//a[text()='Start Over']";
		public static final String DOWNLOAD_MOTOR_POLICY	="//div[@class='default_card_link']/a[text()='Download Motor Policy']";
		public static final String DOWNLOAD_LINK="//a[contains(@href, 'policy-download') and text()='Download']";
		
		//TEST BOT - FILL FORM
		public static final String TEST_MSG = "Test Bot";
		public static final String FILL_FORM_SECTION = "//div[contains(@aria-label, 'Fill the below form')]";
		public static final String FILL_FORM_HEADER = "//div[text()='Fill the below form']";
		public static final String FULL_NAME_LABEL = "//div[@data-ele-name = 'Full name']";
		public static final String FULL_NAME_INPUT =  "//input[contains(@aria-label, 'Full name')]"; 
		public static final String ADDRESS_INPUT =  "//textarea[contains(@aria-label, 'Address')]"; 
		public static final String GENDER_SECTION ="//div[@data-ele-name='Gender']/following-sibling::*";
		public static final String MALE_INPUT =  "//div[@data-ele-name='Gender']/following-sibling::*/label[*[text()='Male']]"; 
		public static final String FEMALE_INPUT =  "///div[@data-ele-name='Gender']/following-sibling::*/label[*[text()='Female']]"; 
		public static final String GENDER_MALE = "//span[text()='Male']";
		public static final String GENDER_FEMALE = "//span[text()='Female']";
		public static final String SELECT_DROPDOWN="//div[contains(@data-ele-name, 'How often')]/following-sibling::*/input[@placeholder='Select']";
		
		// static locators
		public static final String OPTION_VERY_OFTEN = "//li[text()='Very Often']";
		public static final String OPTION_RARELY = "//li[text()='Rarely']";
		public static final String RATING_HEADER = "//div[@data-ele-name='Rate the experience']";
		public static final String RATING_5 ="//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='rating 5']";
		public static final String RATING_4 ="//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='rating 4']";
		public static final String RATING_3 ="//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='rating 3']";
		public static final String RATING_2 ="//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='rating 2']";
		public static final String RATING_1 ="//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='rating 1']";
		public static final String SUBMIT ="//button[@aria-label='Submit']";
		public static final String SUBMIT_SUCCESS ="//strong[text()='Submitted successfully']";
		
		//New Test
		public static final String NEW_MSG = "New Test";
		public static final String GOOGLE_LINK = "//a[@title='Google']";
		public static final String CLOSE_POPUP = "//button[@aria-label='close webview popup']";
		public static final String CALL_LINK = "//a[@title='Call']";
		
		//RENEW POLICY	
		public static final String POLICY_NUMBER_BOT_QUES ="//div[contains(@aria-label,'Can you help me with your 8-digit policy number?')]";
		public static final String VEHICLE_NUMBER_BOT_QUES ="//div[contains(@aria-label,'Can you help me with your vehicle registration number?')]";
		public static final String MOBILE_NUMBER_BOT_QUES ="//div[contains(@aria-label,'Can you help me with your registered phone number?')]";
		public static final String EMAIL_ID_BOT_QUES ="//div[contains(@aria-label,'Can you help me with your email address?')]";
		public static final String VERIFY_RENEW_POLICY_DETAILS = "//*[normalize-space(text())='Thanks for entering all the details. The details you have entered are:']";
		

	public String getApplicationTitle() {
		return getDriver().getTitle();
	}

	public boolean verifyIconPresence() {
		return getWebElement(ICON).isDisplayed();		
	}


	public void clickNotificationIcon() {
		if (verifyIconPresence())
			getWebElement(ICON).click();				
	}

	public String getBannerText() {
		if(getWebElement(BANNER).isDisplayed())
			return getWebElement(NOTICATION_MSG).getText();
		else
			return "Unable to extract Banner msg";
	}

	public boolean getIntoBot() {
		if(getWebElement(GET_STARTED).isEnabled())	{	
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getWebElement(GET_STARTED)));			
			getWebElement(GET_STARTED).click();
		}
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOADER)));
		getDriver().switchTo().frame(AVAAMO_FRAME);		
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(BOT_LAUNCHED)));			
		return getWebElement(BOT_LAUNCHED).isDisplayed();
	}

	public void clickKeyboard() {					
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(KEYBOARD)));
		getWebElement(KEYBOARD).click();			
	}

	public IRAAIAgentPage typeMsg(String msg) {			
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(ENTER_TEXT)));
		getWebElement(ENTER_TEXT).sendKeys(msg);
		getWebElement(SEND_TEXT).click();
		return this;

	}

	public String recieveResponse(String input) {	
		String BOT_RESPONSE = "//div[@aria-label='You sent, Text."+input+"']/following-sibling::div//p";
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(BOT_RESPONSE)));		
		return getWebElement(BOT_RESPONSE).getText();
	}

	public String downloadMotorPolicy() throws InterruptedException {	
		String newTabUrl;
		List<String> windows = new ArrayList<String>();
		String parentWindownId=getDriver().getWindowHandle();

		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(SWITCH_MENU)));
		getWebElement(SWITCH_MENU).click();	
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(START_OVER)));
		getWebElement(START_OVER).click();
		getWebElement(DOWNLOAD_MOTOR_POLICY).click();
		getWebElement(DOWNLOAD_LINK).click();
		getWebDriverWait().until(ExpectedConditions.numberOfwindowsToBe(2));
		for (String s :getDriver().getWindowHandles())
			windows.add(s);
			//Capturing new tab url 
		getDriver().switchTo().window(windows.get(1));
		newTabUrl =  getDriver().getCurrentUrl();
		getDriver().close();
			//switching back to parent Tab
		getDriver().switchTo().window(parentWindownId);

		return newTabUrl;
	}

	public String fillAndSubmitform(String msg, String name, String address, String gender, String freq, String rating) {
		getDriver().switchTo().frame(AVAAMO_FRAME);		
		typeMsg(msg);
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(FILL_FORM_SECTION)));
		getWebElement(FULL_NAME_INPUT).sendKeys(name);
		getWebElement(ADDRESS_INPUT).sendKeys(address);
		getWebElement("//span[text()='"+gender+"']").click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebElement(SELECT_DROPDOWN));
		getWebElement(SELECT_DROPDOWN).click();
		getWebElement("//li[text()='"+freq+"']").click();
		getWebElement("//div[@data-ele-name='Rate the experience']/following-sibling::div//label[@aria-label='"+rating+"']").click();	
		getWebElement(SUBMIT).click();
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUBMIT_SUCCESS)));

		return getWebElement(SUBMIT_SUCCESS).getText();	
	}
	
	public boolean clickGoogleAndCall(String msg) {
		List<String> windows = new ArrayList<String>();
		Boolean googlePopUp, callPopUp;
		String parentWindownId=getDriver().getWindowHandle();
		//getDriver().switchTo().frame(AVAAMO_FRAME);		
		typeMsg(msg);
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(GOOGLE_LINK)));
		getWebElement(GOOGLE_LINK).click();
		//as google popup doesnt launch any title or content body, verifying using close button
		googlePopUp = getWebElement(CLOSE_POPUP).isDisplayed();
		getWebElement(CLOSE_POPUP).click();
		getWebElement(CALL_LINK).click(); 
		for (String tabs : getDriver().getWindowHandles())
			windows.add(tabs);	
		getDriver().switchTo().window(windows.get(1));
		callPopUp = !(windows.get(1).equalsIgnoreCase(parentWindownId));		
		getDriver().close();		
		getDriver().switchTo().window(parentWindownId);
		//returns true if both the links are successful. 
		return googlePopUp && callPopUp;		
	}

	
}
