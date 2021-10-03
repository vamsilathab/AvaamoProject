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
import pages.WalletHubHomePage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;

public class WHHomePageTest {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	@BeforeMethod
	public void setUp() {
		report = new ExtentReports("./Reports/NewReport.html", true);
		logger = report.startTest("Driver to launch browser");
		driver = AppUtils.getDriver();
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		logger.log(LogStatus.PASS, "Browser launched");
	}
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Wallethub Test Insurance Portal is loaded successfully")
	public void verifyTravelAgencyPageStatusCode() throws Exception {
		WalletHubHomePage wp=new WalletHubHomePage();	
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getApplicationUrl()), 200, "Alert! Page is broken");	
		logger.log(LogStatus.INFO, "Test_insurance_company page Test!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	/*
	 * @Test(groups = {"CRITICAL"}, priority=2,
	 * description="Verify Wallethub Test Insurance Portal is loaded successfully")
	 * public void verifyUrlAndTitle() throws Exception { InsuranceHomePage wp=new
	 * InsuranceHomePage(); String currentUrl = wp.getCurrentURL().substring(0,
	 * wp.getCurrentURL().length()-"-13732055i".length()).trim();
	 * Assert.assertEquals(currentUrl,
	 * BuildFactory.ConfigObject().getApplicationUrl().trim(), "Mismatch in url");
	 * Assert.assertEquals(wp.getApplicationTitle(),
	 * "test insurance campany metatitle test", "Title mismatch identified!");
	 * logger.log(LogStatus.INFO, "Test_insurance_company page Test!");
	 * logger.log(LogStatus.INFO,
	 * logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation"))); }
	 */
	
	@Test(groups = {"CRITICAL"}, priority=2, description="Verify user is able to login to WalletHub portal")
	public void verifyLogin() throws Exception {
		WalletHubHomePage wp=new WalletHubHomePage();
		wp.userLogin();
		Boolean loginStatus = wp.login(BuildFactory.ConfigObject().getEmail(), BuildFactory.ConfigObject().getDecodedValue(), BuildFactory.ConfigObject().getFirstName());
		Assert.assertTrue(loginStatus, "Alert! Login Unsuccessful");	
		logger.log(LogStatus.INFO, "Login validation!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	@Test(groups = {"CRITICAL"}, priority=3, description="Verify user is able to navigate to review section and provide rating")
	public void validateReview() throws Exception {
		WalletHubHomePage wp=new WalletHubHomePage();
		Assert.assertTrue(wp.goToReview(), "Alert! Navigation to review section failed");
		wp.provideUserRating();
		Thread.sleep(5000);
		logger.log(LogStatus.INFO, "Login validation!");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	

	@AfterTest
	public void closeTest() throws IOException {
		AppUtils.quitDriver();
	}
}
