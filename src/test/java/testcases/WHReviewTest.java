package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BuildFactory;
import pages.WHHomePage;
import pages.WHReviewPage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;

public class WHReviewTest {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeMethod
	public void setUp() {
		report = new ExtentReports("./Reports/NewReport.html", true);
		logger = report.startTest("Driver to launch browser");
		driver = AppUtils.getDriver();
		logger.log(LogStatus.PASS, "Browser launched");
	}
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Wallethub Test Insurance Portal is loaded successfully")
	public void verifyTestInsurancePageStatusCode() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		WHHomePage wp=new WHHomePage();	
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getApplicationUrl()), 200, "Alert! Page is broken");	
		logger.log(LogStatus.INFO, "Test_insurance_company page Test!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	@Test(groups = {"FUNCTIONAL"}, priority=2, description="Verify user is able to login to WalletHub portal")
	public void verifyLogin() throws Exception {
		WHHomePage wp=new WHHomePage();
		wp.userLogin();
		Boolean loginStatus = wp.login(BuildFactory.ConfigObject().getEmail(), BuildFactory.ConfigObject().getDecodedValue(), BuildFactory.ConfigObject().getFirstName());
		Assert.assertTrue(loginStatus, "Alert! Login Unsuccessful");	
		logger.log(LogStatus.INFO, "Login validation!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	@Test(groups = {"FUNCTIONAL"}, priority=3, description="Verify user is able to navigate to review section and provide rating")
	public void validateReview() throws Exception {
		WHHomePage wp=new WHHomePage();
		WHReviewPage rp=new WHReviewPage();	
		Assert.assertTrue(wp.goToReview(), "Alert! Navigation to review section failed");
		wp.provideUserRating();
		logger.log(LogStatus.INFO, "Review section validation!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	
	@Test(groups = {"FUNCTIONAL"}, priority=4, description="Verify review page header")
	public void verifyReviewPageHeader() throws Exception {
		WHReviewPage rp=new WHReviewPage();	
		Assert.assertTrue(rp.getReviewPageHeader(), "Alert! Review page not loaded successfully");	
		logger.log(LogStatus.INFO, "Review page navigation validation!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	//added invocationCount as selected value is getting reset after first click.
	 @Test(groups = {"FUNCTIONAL"}, priority=5, invocationCount = 2, description="Verify user is able to pick insurance company from dropdown section")
	public void validateDropdown() throws Exception {
		 WHReviewPage rp=new WHReviewPage();
		 //we can alternatively iterate and pass all the value via dataProvider to make it dynamic.
		 String option = "Health Insurance";
		 rp.selectDrodown(option);
		 logger.log(LogStatus.INFO, "Login validation!");
		 logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
		}
	 
	 @Test(groups = {"FUNCTIONAL"}, priority=6, description="Verify if user entered the text minimum of 200 char in review section")
		public void verifyReviewContent() throws Exception {
			 WHReviewPage rp=new WHReviewPage();
			 Assert.assertTrue(rp.inputReviewContent(BuildFactory.ConfigObject().getReviewText()));
			 logger.log(LogStatus.INFO, "Review Content validation!");
			 logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
			}
	 
	 @Test(groups = {"FUNCTIONAL"}, priority=7, description="Verify the review submitted by user")
		public void verifySubmittedReview() throws Exception {
			 WHReviewPage rp=new WHReviewPage();
			 WHHomePage wp=new WHHomePage();
			 rp.submitReview();
			 wp.verifySubmittedReview(BuildFactory.ConfigObject().getUserName());
			 logger.log(LogStatus.INFO, "Verify submitted review ");
			 logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
			}
	

	  @AfterTest public void closeTest() throws IOException {
	  AppUtils.quitDriver(); }
	 
}
