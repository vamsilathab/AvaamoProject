package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataProvider.ExcelDataProvider;
import dataProvider.ExcelDataSetter;
import factory.BuildFactory;
import pages.IRAAIAgentPage;
import pojo.ExcelPojo;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;
import utility.LocatorUtils;

public class IRAAIAgentTest {
	/**
	 * 1) Welcome Screen - Click on Notification, get that text and get into the bot. 
	 * 2) Type any greetings message and fetch the response message 
	 * 3) Menu Options click on the “Start Over” button > Click on Download Motor Policy and Click on Download link 
	 * 4) Type "Test Bot" > Fill the Form and Submit 
	 * 5) Type "New Test" > Click on Links “Google” and close the web view. Click on call and Close the call popup 
	 * 6) verify answers for few questions
	 * 7) verify renew policy
	 * 8) perform negative testing
	 */
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
    public static final String DIR = "./test-data/";
	public static final String[][] FORM_DATA = ExcelDataProvider.readTestData(DIR + "Testdata.xlsx", "fillform");
	public static final String[][] TEST_DATA = ExcelDataProvider.readTestData(DIR + "Testdata.xlsx", "data");
	private ExcelPojo form_input = ExcelDataSetter.dataSetterByKey(FORM_DATA).get("MALE_Often_4");
	private Map<String, ExcelPojo> msg_input = ExcelDataSetter.dataSetterForMsg(TEST_DATA);

	@BeforeTest
	public void setUp() {
		report = new ExtentReports("./Reports/NewReport.html", true);
		logger = report.startTest("Driver to launch browser");
		driver = AppUtils.getDriver();
	}
	
	@Test(groups = {"CRITICAL"}, priority=1, description="Welcome Screen - Page Load test")
	public void verifyIRAAgentPageStatusCode() throws Exception {
		driver.get(BuildFactory.ConfigObject().getApplicationUrl());
		Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getApplicationUrl()), 200, "Alert! Page is broken");		
	}
	
	@Test(groups = {"FUNCTIONAL"}, dependsOnMethods= {"verifyIRAAgentPageStatusCode"}, priority=2, description="Verify if user is able to extract the text on Welcome Screen - Click on Notification, get that text ")
	public void verifyWelcomeMsg() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();	
		ira.clickNotificationIcon();
		Assert.assertEquals(ira.getBannerText(), "Hello and welcome to IRA agent");		
	}
	
	@Test(groups = {"FUNCTIONAL"}, dependsOnMethods= {"verifyWelcomeMsg"},  priority=3, description="Verify if user is able to get into BOT")
	public void verifyNavigationToBot() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();		
		Assert.assertTrue(ira.getIntoBot(), "User failed to get into bot");
	}
	
	@Test(groups = {"FUNCTIONAL"}, dependsOnMethods= {"verifyNavigationToBot"},  priority=4, description="Verify if user is able to recieve response from BOT")
	public void verifyBotResponse() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();	
		SoftAssert sa = new SoftAssert();
		ira.clickKeyboard();
		sa.assertEquals(ira.typeMsg(msg_input.get("message0").getInput()).recieveResponse(msg_input.get("message0").getInput()), msg_input.get("message0").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message1").getInput()).recieveResponse(msg_input.get("message1").getInput()), msg_input.get("message1").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message2").getInput()).recieveResponse(msg_input.get("message2").getInput()), msg_input.get("message2").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message3").getInput()).recieveResponse(msg_input.get("message3").getInput()), msg_input.get("message3").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message4").getInput()).recieveResponse(msg_input.get("message4").getInput()), msg_input.get("message4").getOutput());
		sa.assertEquals(ira.typeMsg(msg_input.get("message5").getInput()).recieveResponse(msg_input.get("message5").getInput()), msg_input.get("message5").getOutput());
		sa.assertAll();
	}
	
	@Test(groups = {"FUNCTIONAL"},  priority=5, description="Verify if user is able to click on download motor policy")
	public void verifyDownloadMotorPolicy() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();		
		Assert.assertEquals(ira.downloadMotorPolicy(), BuildFactory.ConfigObject().getDownloadUrl());
	}

	@Test(groups = {"FUNCTIONAL"},  priority=6, description="Verify if user is able fill and submit the form")
	public void validateFormSubmission() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();		
		String response = ira.fillAndSubmitform(ira.TEST_MSG, form_input.getFirstName(), form_input.getAddress(), form_input.getGender(), form_input.getFreq(), form_input.getRating());
		Assert.assertEquals(response, "Submitted successfully");
	}
	
	@Test(groups = {"FUNCTIONAL"},  priority=7, description="Verify if user is able click on google and call links")
	public void validateGoogleCallLinks() throws Exception {
		IRAAIAgentPage ira=new IRAAIAgentPage();			
		Assert.assertTrue(ira.clickGoogleAndCall(ira.NEW_MSG), "Either of the links, Google or Call is broken");
	}
	
	@AfterMethod
	public void logResult(ITestResult result) throws IOException {		
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.INFO, result.getName()+  " : Test Failed");
			logger.log(LogStatus.FAIL, logger.addScreenCapture(Helper.captuteScreenshot(driver, result.getName())));
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
			logger.log(LogStatus.PASS, result.getName()+ " : Test Passed");
	}
	
	@AfterTest 
	public void close() throws IOException {		
		AppUtils.quitDriver();
		report.endTest(logger);
		report.flush();
	}
	
}
