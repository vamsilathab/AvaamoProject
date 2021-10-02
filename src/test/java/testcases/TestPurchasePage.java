package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import org.testng.ITestResult;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BuildFactory;
import pages.BlazeDemoPage;
import pages.PurchasePage;
import pages.ReservationPage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;


public class TestPurchasePage {
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
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Purchase Page is loaded successfully by validating status code")
	public void verifyFlightsPurchasePageStatusCode() throws Exception {
		PurchasePage rp=new PurchasePage();
		rp.navigateToPurchasePage();
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getPurchaseUrl()), 200, "Page is broken");
		logger.log(LogStatus.PASS, "Purchase page is successfully loaded");
	}
	
	@Test(groups = {"CRITICAL"}, priority=2, description="Verify whether user has landed to Purchase Page by validating the url and title")
	public void verifyFlightsPurchasePage() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		PurchasePage rp=new PurchasePage();
		rp.navigateToPurchasePage();
		Assert.assertEquals(rp.getCurrentURL(), BuildFactory.ConfigObject().getPurchaseUrl(), "Mismatch in url");
		new SoftAssert().assertEquals((rp.getApplicationTitle().contains("Purchase")), "Title mismatch identified!");
		Assert.assertEquals(rp.getPurchasePageHeader(), "Your flight from TLV to SFO has been reserved.", "Mismatch in header");
		logger.log(LogStatus.PASS, "User landed to Purchase Page successfully");
	}
	
	@Test(groups = {"CRITICAL"}, priority=3, description="Verify whether user to navigate Purchase page by clicking on Purchase Flights")
	public void verifyChooseFlightButton() throws Exception {
		PurchasePage rp=new PurchasePage();
		rp.navigateToPurchasePage();	
		Assert.assertTrue(rp.verifyPurchaseFlightButton(), "Purchase flight Button is present and enabled");	
		rp.clickPurchaseFlightButton();
		Assert.assertEquals(rp.getCurrentURL(), BuildFactory.ConfigObject().getConfirmationUrl(), "User not navigated to Purchase Page");
		logger.log(LogStatus.PASS, "User could successfully navigate to Purchase Page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	
	@AfterTest
	public void closeTest() throws IOException {
		AppUtils.quitDriver();
	}
	
	
}
