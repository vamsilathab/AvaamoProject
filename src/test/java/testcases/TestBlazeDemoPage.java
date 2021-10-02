package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BuildFactory;
import pages.BlazeDemoPage;

import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;


public class TestBlazeDemoPage {
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
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Travel Agency Welcome Page is loaded successfully")
	public void verifyTravelAgencyPageStatusCode() throws Exception {
		BlazeDemoPage wp=new BlazeDemoPage();	
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getApplicationUrl()), 200, "Page is broken");
		logger.log(LogStatus.PASS, "Travel Agency page is successfully loaded");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	@Test(groups = {"CRITICAL"}, priority=2, description="verify whether user has landed to Travel Agency Welcome Page by validating the url and title")
	public void verifyWelcomePage() throws Exception {
		BlazeDemoPage wp=new BlazeDemoPage();
		Assert.assertEquals(wp.getCurrentURL(), BuildFactory.ConfigObject().getApplicationUrl(), "Mismatch in url");
		new SoftAssert().assertEquals(wp.getApplicationTitle(), "BlazeDemo", "Title mismatch identified!");
		Assert.assertEquals(wp.getBlazeDemoPageTitle(), "Welcome to the Simple Travel Agency!", "WelcomePage title mismatch identified!");
		logger.log(LogStatus.PASS, "User landed to Travel Agency Welcome Page successfully");
	
	}
	
	@Test(groups = {"CRITICAL"}, priority=3, description="verify whether user to navigate reservation page by clicking on findFlights")
	public void verifyFlightButton() throws Exception {
		BlazeDemoPage wp=new BlazeDemoPage();	
		Assert.assertTrue(wp.verifyFlightButton(), "Find Flights Button is present and enabled");	
		wp.clickFlightButton();
		Assert.assertEquals(wp.getCurrentURL(), BuildFactory.ConfigObject().getFindFlightsUrl(), "User not navigated to reservation page");
		logger.log(LogStatus.PASS, "User could successfully navigate to Reservatipon page");
	}
	
	@AfterTest
	public void closeTest() throws IOException {
		AppUtils.quitDriver();
	}
	
	
}
