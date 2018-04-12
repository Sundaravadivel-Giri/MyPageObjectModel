package supportLibraries;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IExecutionListener;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;

import Data.GlobalTestData;
import Data.ReadDataSheet;


public class Controller implements IExecutionListener
{
	public static RemoteWebDriver driver=null;
	
	
	//*********************************************************************//
	//# Function Name:            loadDriver
	//# Function Description:     Function to Trigger Particular Browser
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
	public void loadDriver(String browser,String driver_Path) 
	{
		try
		{
			if (browser.equals("firefox"))
			{
				driver=new FirefoxDriver();
			}	
			else if(browser.equals("iexplore"))
			{
				System.setProperty("webdriver.ie.driver", driver_Path+"\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if (browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", driver_Path+"\\chromedriver.exe");
				driver=new ChromeDriver();	
			}
			else
			{
				System.out.println("Invalid Browser, Please choose the relevant browser");
				System.exit(0);
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Trigger Particular Browser");
		}
	}
		
	//*********************************************************************//
	//# Function Name:            preConfig
	//# Function Description:     Function to Pre-SetUp
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
	@BeforeClass
	public void preConfig()
	{
		try
		{
			GlobalTestData.loadPropertyFiles();
			ReadDataSheet.loadExcelSheet();
			Report.startReport();
		}
		catch (IOException e)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured","  Pre-SetUp");
		}

	}
		
		
	//*********************************************************************//
	//# Function Name:            setUp
	//# Function Description:     Function to Load Browser
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
		@BeforeMethod
		public void setUp() 
		{
			try
			{
				loadDriver(GlobalTestData.get_GlobalData("browser"),GlobalTestData.get_GlobalData("driver_Path"));
			}
			catch (Exception E)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured","  Load Browser");
			}
		}
		
		//*********************************************************************//
		//# Function Name:            terminate
		//# Function Description:     Function to Terminate Browser
		//# Author Name:              Sundar
		//# Date:                     24-Dec-2016
		//*********************************************************************//
		@AfterMethod
		public void terminate() 
		{
			try
			{
				driver.quit();	
			}
			catch (Exception E)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured","  Terminate Browse");
			}
		}

		//*********************************************************************//
		//# Function Name:            terminateAll
		//# Function Description:     Function to Terminate EveryThing at the End Of All Test
		//# Author Name:              Sundar
		//# Date:                     24-Dec-2016
		//*********************************************************************//
		@AfterTest
		public void terminateAll()
		{
			try
			{
				Report.endReport();
			}
			catch (Exception E)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured","  terminateAll");
			}	
		}

		
		@Override
		public void onExecutionStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onExecutionFinish() {
			// TODO Auto-generated method stub
			
		}
}

