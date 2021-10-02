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
import pages.OrderConfirmationPage;
import pages.ReservationPage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;


public class TestOrderConfirmationPage {
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
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Order Confirmation page is loaded successfully by validating status code")
	public void verifyFlightsOrderConfirmationPageStatusCode() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		OrderConfirmationPage rp=new OrderConfirmationPage();
		rp.navigateToOrderConfirmationPage();
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getConfirmationUrl()), 200, "Page is broken");
		logger.log(LogStatus.PASS, "Order Confirmation page is successfully loaded");
	}
	
	@Test(groups = {"CRITICAL"}, priority=2, description="Verify whether user has landed to Order Confirmation page by validating the url and title")
	public void verifyFlightsOrderConfirmationPage() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		OrderConfirmationPage rp=new OrderConfirmationPage();
		rp.navigateToOrderConfirmationPage();
		Assert.assertEquals(rp.getCurrentURL(), BuildFactory.ConfigObject().getConfirmationUrl(), "Mismatch in url");
		Assert.assertTrue((rp.getApplicationTitle().contains("Confirmation")), "Title mismatch identified!");
		new SoftAssert().assertEquals(rp.getOrderConfirmationPageHeader(), "Thank you for your purchase today!", "Mismatch in header");
		logger.log(LogStatus.PASS, "User landed to Order Confirmation page successfully");
	}
	
	@Test(groups = {"CRITICAL"}, priority=3, description="Verify whether user was able to make successful transaction by genration of orderId")
	public void verifyOrderIDGenerated() throws Exception {
		OrderConfirmationPage rp=new OrderConfirmationPage();
		rp.navigateToOrderConfirmationPage();	
		Assert.assertTrue(!rp.getOrderId().isEmpty(), "Order ID not generated");
		logger.log(LogStatus.PASS, "User could successfully make the booking");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	
	@AfterTest
	public void closeTest() throws IOException {
		AppUtils.quitDriver();
	}
	
	
}
