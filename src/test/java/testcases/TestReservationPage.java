package testcases;

import org.testng.annotations.Test;
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
import pages.ReservationPage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;


public class TestReservationPage {
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
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Verify Flights Reservation Page is loaded successfully by validating status code")
	public void verifyFlightsReservationPageStatusCode() throws Exception {
		ReservationPage rp=new ReservationPage();
		rp.navigateToReservationPage();
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getFindFlightsUrl()), 200, "Page is broken");
		logger.log(LogStatus.PASS, "Flights Reservation Page is successfully loaded");
	}
	
	@Test(groups = {"CRITICAL"}, priority=2, description="Verify whether user has landed to Flights Reservation Page by validating the url and title")
	public void verifyFlightsReservationPage() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		BlazeDemoPage wp=new BlazeDemoPage();	
		String source = wp.getSource();
		String destination = wp.getDestination();
		ReservationPage rp=new ReservationPage();
		rp.navigateToReservationPage();
		Assert.assertEquals(rp.getCurrentURL(), BuildFactory.ConfigObject().getFindFlightsUrl(), "Mismatch in url");
		Assert.assertTrue((rp.getApplicationTitle().contains("reserve")), "Title mismatch identified!");
		Assert.assertEquals(rp.getReservationPageHeader(source, destination), "Flights from "+source+" to "+destination+":", "Mismatch in source and destination selection");
		logger.log(LogStatus.PASS, "User landed to Flights Reservation Page successfully");
	}
	
	@Test(groups = {"CRITICAL"}, priority=3, description="Verify whether user to navigate Purchase page by clicking on choose Flights")
	public void verifyChooseFlightButton() throws Exception {
		ReservationPage rp=new ReservationPage();	
		rp.navigateToReservationPage();	
		Assert.assertTrue(rp.verifyChooseFlightButton(), "Choose flight Button is present and enabled");	
		rp.clickChooseFlightButton();
		Assert.assertEquals(rp.getCurrentURL(), BuildFactory.ConfigObject().getPurchaseUrl(), "User not navigated to Purchase Page");
		logger.log(LogStatus.PASS, "User could successfully navigate to Purchase Page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
	}
	
	
	@AfterTest
	public void closeTest() throws IOException {
		AppUtils.quitDriver();
	}
	
	
}
