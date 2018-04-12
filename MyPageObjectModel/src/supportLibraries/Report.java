package supportLibraries;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report
{
	static Controller remoteDriver=new  Controller();
	CaptureScreenShot screenShot=new CaptureScreenShot();
	static ExtentReports extent;
	static ExtentTest logger;
	
	//*********************************************************************//
	//# Function Name:            startReport
	//# Function Description:     Function to Set path For Report
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public static void startReport()
	{	
		try
		{
			extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/MyOwnReport.html",true);
			extent.addSystemInfo("Author Name","Sundar")
			.addSystemInfo("Envornment","Windows10");
			extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Set path For Report");
		}
	}

	//*********************************************************************//
	//# Function Name:            updateLog
	//# Function Description:     Function to Print Report
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public static void updateLog(String testScriptName,LogStatus status ,String StepName, String StepDescription )
	{
		try 
		{
			logger=extent.startTest(testScriptName);
			logger.log(status, StepName, StepDescription);
			System.out.println(status+" StepName: "+StepName+" StepDescription: "+StepDescription);
			String imagePath=CaptureScreenShot.TakeScreenShot(remoteDriver.driver, StepDescription);
			logger.log(status, "ScreenShots: below"+logger.addScreenCapture(imagePath));
		}
		catch (Exception e)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," In Printing Report");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            getResult
	//# Function Description:     Function to Print Report For Failure due to Assertations
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	@AfterMethod
	public static void  getResult(ITestResult result)
	{
		try
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				logger.log(LogStatus.FAIL, "Exception Occured", result.getThrowable());
			}
			extent.endTest(logger);
		}
		catch (Exception e)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," In Printing Report for Failure due to Assertations");
		}
	}

	//*********************************************************************//
	//# Function Name:            endReport
	//# Function Description:     Function to Flush and close Report
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public static void endReport()
	{
		try
		{
			extent.flush();
			//extent.close();
		}
		catch (Exception e)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," In Flush and close Report");
		}
	}
}
