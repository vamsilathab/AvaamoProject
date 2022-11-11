package testcases;

import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import dataProvider.ExcelDataProvider;
import dataProvider.ExcelDataSetter;
import factory.BuildFactory;
import pages.IRAAIAgentPage;
import pojo.ExcelPojo;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;

public class IRAAIAgentTest {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;

	public static final String DIR = "./test-data/";
	public static final String[][] FORM_DATA = ExcelDataProvider.readTestData(DIR + "Testdata.xlsx", "fillform");
	public static final String[][] TEST_DATA = ExcelDataProvider.readTestData(DIR + "Testdata.xlsx", "data");
	public static final String[][] RENEW_POLICY_DATA = ExcelDataProvider.readTestData(DIR + "Testdata.xlsx", "renew_policy");
	private ExcelPojo form_input = ExcelDataSetter.dataSetterByKey(FORM_DATA).get("MALE_Often_4");
	private Map<String, ExcelPojo> msg_input = ExcelDataSetter.dataSetterForMsg(TEST_DATA);
	private ExcelPojo renewPolicyHeaders = ExcelDataSetter.renewPolicyData(RENEW_POLICY_DATA).get("renew header labels");
	private ExcelPojo renewPolicy = ExcelDataSetter.renewPolicyData(RENEW_POLICY_DATA).get("renew policy1");

	@BeforeTest
	public void setUp() {
		driver = AppUtils.getDriver();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/IRAReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeMethod
	public void setUpReport(Method method) {
		logger = extent.createTest(method.getName());
	}

	@Test(groups = { "CRITICAL" }, priority = 1, description = "Welcome Screen - Page Load test")
	public void verifyIRAAgentPageStatusCode() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getApplicationUrl()), 200,
				"Alert! Page is broken");
	}

	@Test(groups = { "FUNCTIONAL" }, dependsOnMethods = {
			"verifyIRAAgentPageStatusCode" }, priority = 2, description = "Verify if user is able to extract the text on Welcome Screen - Click on Notification, get that text ")
	public void verifyWelcomeMsg() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		ira.clickNotificationIcon();
		Assert.assertEquals(ira.getBannerText(), "Hello and welcome to IRA agent");
	}

	@Test(groups = { "FUNCTIONAL" }, dependsOnMethods = {
			"verifyWelcomeMsg" }, priority = 3, description = "Verify if user is able to get into BOT")
	public void verifyNavigationToBot() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		Assert.assertTrue(ira.getIntoBot(), "User failed to get into bot");
	}

	// passing input and output bot testdata directly to report. this needs further code re-factoring and Optimization
	@Test(groups = { "FUNCTIONAL" }, dependsOnMethods = {
			"verifyNavigationToBot" }, priority = 4, description = "Verify if user is able to recieve response from BOT")
	public void verifyBotResponse() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		SoftAssert sa = new SoftAssert();
		ira.clickKeyboard();
		// Captured expected responses to excel and validating them for test msgs,
		// alternately we can also connect to backend /or fetch the appropriate api response to compare the results
		sa.assertEquals(ira.typeMsg(msg_input.get("message0").getInput()).recieveResponse(msg_input.get("message0").getInput()), msg_input.get("message0").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message0").getInput() + " | Bot Reponse: " + msg_input.get("message0").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message1").getInput()).recieveResponse(msg_input.get("message1").getInput()),msg_input.get("message1").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message1").getInput() + " | Bot Reponse: "+ msg_input.get("message1").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message2").getInput()).recieveResponse(msg_input.get("message2").getInput()),msg_input.get("message2").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message2").getInput() + " | Bot Reponse: "+ msg_input.get("message2").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message3").getInput()).recieveResponse(msg_input.get("message3").getInput()),msg_input.get("message3").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message3").getInput() + " | Bot Reponse: "+ msg_input.get("message3").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message4").getInput()).recieveResponse(msg_input.get("message4").getInput()),msg_input.get("message4").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message4").getInput() + " | Bot Reponse: "+ msg_input.get("message4").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message5").getInput()).recieveResponse(msg_input.get("message5").getInput()),msg_input.get("message5").getOutput());
			logger.log(Status.INFO, "Bot Input : " + msg_input.get("message5").getInput() + " | Bot Reponse: "+ msg_input.get("message5").getOutput());
		sa.assertAll();
		ira.reset();
	}

	@Test(groups = { "FUNCTIONAL" }, dependsOnMethods = {
			"verifyBotResponse" }, priority = 5, description = "Verify if user is able to renew a motor policy")
	public void verifyRenewPolicy() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		LinkedHashMap<String, String> hp = new LinkedHashMap<String, String>();
		hp.put(renewPolicyHeaders.getPolicyNo(), renewPolicy.getPolicyNo());
		hp.put(renewPolicyHeaders.getRegistrationNo(), renewPolicy.getRegistrationNo());
		hp.put(renewPolicyHeaders.getMobileNo(), renewPolicy.getMobileNo());
		hp.put(renewPolicyHeaders.getEmail(), renewPolicy.getEmail());
		
		Assert.assertTrue(ira.renewPolicy(renewPolicy.getPolicyNo(), renewPolicy.getRegistrationNo(), renewPolicy.getMobileNo(), renewPolicy.getEmail()));
		// returns true if data for renewal is successfully entered
		Assert.assertTrue(ira.renewData(hp)); 
		// returns true if policy renewal is successful;
		ira.reset();
	}

	@Test(groups = {
			"FUNCTIONAL" }, priority = 6, description = "Verify if user is able to click on download motor policy")
	public void verifyDownloadMotorPolicy() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		Assert.assertEquals(ira.downloadMotorPolicy(), BuildFactory.ConfigObject().getDownloadUrl());
	}

	@Test(groups = { "FUNCTIONAL" }, priority = 7, description = "Verify if user is able fill and submit the form")
	public void validateFormSubmission() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		String response = ira.fillAndSubmitform(IRAAIAgentPage.TEST_MSG, form_input.getFirstName(), form_input.getAddress(), form_input.getGender(), form_input.getFreq(), form_input.getRating());
		Assert.assertEquals(response, "Submitted successfully");
		ira.reset();
	}

	@Test(groups = {
			"FUNCTIONAL" }, priority = 8, description = "Verify if user is able click on google and call links")
	public void validateGoogleCallLinks() throws Exception {
		IRAAIAgentPage ira = new IRAAIAgentPage();
		Assert.assertTrue(ira.clickGoogleAndCall(IRAAIAgentPage.NEW_MSG),
				"Either of the links, Google or Call is broken");
	}

	@AfterMethod
	public void logResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Helper.captuteScreenshot(driver, result.getName());
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass(result.getName() + " : Test Passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.pass(result.getName() + " : Test Skipped");
		}
	}

	@AfterTest
	public void close() throws IOException {
		AppUtils.quitDriver();
		extent.flush();
	}

}
