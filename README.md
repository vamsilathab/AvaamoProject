# AvaamoProject

Framework:
1.	All driver related common methods are defined under package – driverFactory
2.	All data providers property file and excel file readers are defined under package – dataProvider
					•			ConfigDataProvider – Will help in retrieving info from properties file
					•			ExcelDataProvider – Will help in retrieving info from excel file and returns in two-dimensional String array format.
					•			ExcelDataSetter – This class will pick the info from ExcelDataProvider and using POJO methods sets the data in a HashMap. This will help in achieving encapsulation mechanism, which helps in setting data dynamically. This map will be used in test methods to fetching input and expected output data without hardcoding.
3.	For common functionality which are required all the tests are maintained under Utility package.
					•			AppUtils.java - For common methods used by selenium 
					•			ApiAppUtils.java – For API methods
					•			Helper.java – CapturingTestScreenshot

POM: Execution
1.	Initiate tests from testNG.xml -> IRAAIAgentProject.xml
2.	All the tests are maintained under Test Class 
•		/AvaamoProject/src/test/java/testcases/IRAAIAgentTest.java
3.	Respective page related functional methods are maintained under Page class
•		/AvaamoProject/src/test/java/pages/IRAAIAgentPage.java
4.	And all the locators are maintained as constants in an interface
•		/AvaamoProject/src/main/java/utility/LocatorUtils.java
5.	All urls or configs are maintained under property file
•		/AvaamoProject/Config/Config.properties
6.	Testdata in 
•		/AvaamoProject/test-data/Testdata.xlsx
7.	For reporting we have used extent reports, it is generated as a html file. 
•		/AvaamoProject/Reports/IRAReport.html
8.	Captured screenshots for failed testcases will be attached to the report, can also found under  
•		/AvaamoProject/Screenshots
 
