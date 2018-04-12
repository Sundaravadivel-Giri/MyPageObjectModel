package FunctionalLibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Data.ReadDataSheet;
import ObjectRepostries.OR_ObjTypes;
import ObjectRepostries.OR_Welcome_Page;
import supportLibraries.ElementsAction;
import supportLibraries.CaptureScreenShot;
import supportLibraries.Controller;
import supportLibraries.Report;

public class GeneralFunctions extends ElementsAction{
	
	ReadDataSheet data=new ReadDataSheet();
	CaptureScreenShot captureScreenShot= new CaptureScreenShot();
	Controller remoteDriver=new  Controller();
	ElementsAction elementsAction=new ElementsAction();
	
	//*********************************************************************//
	//# Function Name:            hitURL
	//# Function Description:     Function To Hit URL 
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public void hitURL(String url)
	{
		try
		{
			remoteDriver.driver.get(url);
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured","In Hitting URL");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            verify_Page_URL
	//# Function Description:     Function to verify Page URL
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public void verify_Page_URL(String url, String PageName)
	{
		try
		{
			String getPageUrl=remoteDriver.driver.getCurrentUrl();
			if(getPageUrl.contains(url))
			{
				Report.updateLog("LoginTest", LogStatus.PASS, "Verify "+PageName+" Page", "Page is successfully displayed");
			}
			else
			{
				Report.updateLog("LoginTest", LogStatus.FAIL, "Verify "+PageName+" Page", "Page is not successfully displayed");
				remoteDriver.driver.close();
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," In Verifying URL");
		}
	}
}
