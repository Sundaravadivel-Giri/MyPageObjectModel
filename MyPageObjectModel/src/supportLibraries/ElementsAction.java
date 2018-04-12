package supportLibraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import ObjectRepostries.OR_ObjTypes;

public class ElementsAction {
	Controller remoteDriver=new  Controller();
	
	
	//*********************************************************************//
	//# Function Name:            getLocator
	//# Function Description:     Function To Return appropriate  WebElement
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
	public By getLocator(String prop)
	{
		try
		{
			String[] locator = prop.split("::");
			String locatorType = locator[0];

			if (locatorType.contains("id"))
				return By.id(locator[1]);
			else if (locatorType.contains("name"))
				return By.name(locator[1]);
			else if (locatorType.contains("link"))
				return By.linkText(locator[1]);
			else if (locatorType.contains("xpath"))
				return By.xpath(locator[1]);
			else if (locatorType.contains("css"))
				return By.cssSelector(locator[1]);
			else if (locatorType.contains("class"))
				return By.className(locator[1]);
			else if (locatorType.contains("tag"))
				return By.tagName(locator[1]);
			else {
				System.out.println("Invalid Locator Type");
				System.exit(0);
				return null;
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Return appropriate  WebElement");
		}
		return null;
	}
		
	
	//*********************************************************************//
	//# Function Name:            getlist_ByTagName
	//# Function Description:     Function To Get a List By TagName
	//# Author Name:              Sundar
	//# Date:                     29-Dec-2016
	//*********************************************************************//
		public List<WebElement> getlist_ByTagName(String testScriptName,By tagname )
		{
			try
			{
				waitForElement(tagname,remoteDriver.driver);
				List<WebElement> list= remoteDriver.driver.findElements(tagname);
				if(list!=null)
				{
					return list;
				}
				else
				{
					Report.updateLog(testScriptName, LogStatus.FAIL, "Check List By TagName", "List is null");
					return null;
				}
			}
			catch(Exception E)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Get a List By TagName");
			}
			return null;
		}
	
	//*********************************************************************//
	//# Function Name:            chk_ElementPresent
	//# Function Description:     Function To return WebElement
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
	public  WebElement chk_ElementPresent(String testScriptName, String elementLocator,String elementName)
	{
		try
		{
			By locator = getLocator(elementLocator);
			WebElement element = remoteDriver.driver.findElement(locator);
			if(element!=null)
			{
				return element;
			}
			else
			{
				Report.updateLog(testScriptName, LogStatus.FAIL, "Check "+elementName+" Element", elementName+" Element is not displayed");
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Returnning WebElement");
		}
		return null;
	}
			
	//*********************************************************************//
	//# Function Name:            CallMeToWait
	//# Function Description:     Function To Wait For a Given Time in MilliSeconds
	//# Author Name:              Sundar
	//# Date:                     28-Dec-2016
	//*********************************************************************//
	public void CallMeToWait(int milliSeconds)
	{
		try
		{
			Thread.sleep(milliSeconds);
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Wait For a Given Time in MilliSeconds");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            waitForElement
	//# Function Description:     Function To Wait for an Element 
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
	public void waitForElement(By element,RemoteWebDriver driver)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 25);				
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Wait For a Given Time in MilliSeconds");
		}
	}
}
