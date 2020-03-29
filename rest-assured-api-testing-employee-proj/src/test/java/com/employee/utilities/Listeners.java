package com.employee.utilities;

	import org.testng.ITestContext;
	import org.testng.ITestResult;
	import org.testng.TestListenerAdapter;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class Listeners extends TestListenerAdapter{
		
		
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		public void onStart(ITestContext testContext)
		{
			htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Report.html");
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("Automation API Testing");
			htmlReporter.config().setTheme(Theme.DARK);
			
			extent=new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name","localhost");
			extent.setSystemInfo("Environment","UAT");
			extent.setSystemInfo("user","Tushar Ayare");	
		}
		
		
		public void onTestSuccess(ITestResult result)
		{
			test=extent.createTest(result.getName());
			test.log(Status.PASS, "Test Case is Passed--> " + result.getName());	
		}
		
		public void onTestFailure(ITestResult result)
		{
			test=extent.createTest(result.getName());
			test.log(Status.FAIL, "Test Case is Failed--> " + result.getName());	
			test.log(Status.FAIL, "Test Case is Failed--> " + result.getThrowable());
		}
		
		
		public void onTestSkipped(ITestResult result)
		{
			test=extent.createTest(result.getName());
			test.log(Status.SKIP, "Test Case is Skipped--> " + result.getName());	
		}
		
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
		
	}
