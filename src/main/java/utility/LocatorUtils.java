package utility;

import org.openqa.selenium.By;

public interface LocatorUtils {
	
	
	//LOGIN SECTION 
	public static final String LOGIN = "//span[text()='Login']";
	public static final String EMAIL = "[id='email']";
	public static final String PASSWORD = "[id='password']";
	public static String username = "vamsi_ad";
	public static final String FIRST_NAME="//*[normalize-space(text()) = 'vamsi']";
	
	//REVIEW SECTION
	public static final String REVIEW = "//span[text()='Reviews']";
	public static final String RATING_BUTTON = "//*[normalize-space(text()) = 'Your Rating']/following-sibling::review-star";
	//can be made dynamic by fetching are ratings and pass the index on which rating to be selected.
	public static final String RATING_FOUR = "//*[normalize-space(text()) = 'Your Rating']/following-sibling::review-star//*[name()='svg'][4]";
	
	//REVIEW PAGE
	public static final String REVIEW_PAGE_HEADER = "//h4[text()='Test Insurance Company']";
	public static final String POLICY_DROPDOWN = "//div[contains(@class, 'dropdown')]//span[contains(text(),'Select')]";
	public static final String REVIEW_TEXT = "//textarea[contains(@placeholder,'Write your review')]";
	public static final String SUBMIT = "//div[text()='Submit']";
	public static final String REVIEW_CONFIRMATION_MSG ="//h4[text()='Your review has been posted.']";
	public static final String CONTINUE_BUTTON = "//div[text()='Continue']";
	
	//VERIFY REVIEW
	
	public static final String CURRENT_REVIEW = "//div[@itemprop='itemReviewed']/following-sibling::div//span[text()=' Your Review']";
	public static final String REVIEW_USER_ID = "//span[text()='@"+username+"']";
	
}
	
	
