package FunctionalLibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Welcome_Page extends ElementsAction
{
	ReadDataSheet data=new ReadDataSheet();
	CaptureScreenShot captureScreenShot= new CaptureScreenShot();
	ElementsAction elementsAction=new ElementsAction();
	Controller remoteDriver=new  Controller();
	GeneralFunctions genFunctions= new GeneralFunctions();
	
	//*********************************************************************//
	//# Function Name:            verify_WelcomePage
	//# Function Description:     Function to Verify Welcome Owner Message
	//# Author Name:              Sundar
	//# Date:                     28-Dec-2016
	//*********************************************************************//
	public void verify_WelcomePage(String welcome_Message)
	{
		try
		{
			remoteDriver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String getText;
			WebElement OwnerTxtElement=elementsAction.chk_ElementPresent("", OR_Welcome_Page.ownerElement, "OwnerElement");
			if(OwnerTxtElement!=null)
			{
				getText=OwnerTxtElement.getText().trim();
				if(getText.contains(welcome_Message))
				{
					Report.updateLog("",LogStatus.PASS, "Check Owner Page", "Owner Page is displayed successfully with Message="+welcome_Message);
				}
				else 
				{
					Report.updateLog("",LogStatus.FAIL, "Check Owner Page", "Owner Page is not displayed successfully with Message="+welcome_Message);
				}
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Verify Welcome Owner Message");
		} 
	} 
			 
	//*********************************************************************//
	//# Function Name:            searchItem
	//# Function Description:     Function to perform Search
	//# Author Name:              Sundar
	//# Date:                     29-Dec-2016
	//*********************************************************************//
	public void searchItem(String SearchItem)
	{
		try
		{
			WebElement searchTxtBox=elementsAction.chk_ElementPresent("Perform_Search", OR_Welcome_Page.searchTxtBox, "Search Text Box");
			if(searchTxtBox!=null)
			{
				searchTxtBox.sendKeys(SearchItem);
			}
			WebElement searchBtn=elementsAction.chk_ElementPresent("Perform_Search", OR_Welcome_Page.searchBtn, "Search Text Box");
			if(searchBtn!=null)
			{
				WebElement srchBtnSvgTag=searchBtn.findElement(OR_ObjTypes.svg);
				if(srchBtnSvgTag!=null)
				{
					srchBtnSvgTag.click();
					elementsAction.CallMeToWait(5000);
				}
				else
				{
					Report.updateLog("Perform_Search", LogStatus.FAIL, "Check Search Button", "Search Button is not displayed");
				}
								 
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured","Perform Search");
		}
	}

}
