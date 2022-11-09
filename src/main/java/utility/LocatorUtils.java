package utility;

import org.openqa.selenium.By;

public interface LocatorUtils {

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
	
}
	
	
