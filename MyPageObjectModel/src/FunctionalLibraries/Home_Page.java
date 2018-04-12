package FunctionalLibraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import ObjectRepostries.*;
import Data.ReadDataSheet;
import supportLibraries.ElementsAction;
import supportLibraries.CaptureScreenShot;
import supportLibraries.Report;

public class Home_Page extends ElementsAction
{
	ReadDataSheet data=new ReadDataSheet();
	CaptureScreenShot captureScreenShot= new CaptureScreenShot();
	ElementsAction actions=new ElementsAction();
	
	
	//*********************************************************************//
	//# Function Name:            navigateTo_loginPage
	//# Function Description:     Function To Click on Login Button and Navigate to SignIn PoPUp
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public void navigateTo_loginPage()
	{
		try
		{
			WebElement signInBtn=actions.chk_ElementPresent("LoginTest", OR_HomePage.loginBtn_HomePage, "SignIn Button");
			if(signInBtn!=null)
			{
				signInBtn.click();
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Clicking at Login Button");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            verify_LoginPopUp
	//# Function Description:     Function To Verify Login PopUp Text Message
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public void verify_LoginPopUp(String MessageTxt)
	{
		try
		{
			String getText;
			WebElement loginPopTxt_Class=actions.chk_ElementPresent("LoginTest", OR_HomePage.loginPopClass,"Login PopUp Txt Class");
			if(loginPopTxt_Class!=null)
			{
				List<WebElement> loginPopUpTxt_List=loginPopTxt_Class.findElements(By.tagName("span"));
				if(loginPopUpTxt_List!=null)
				{
					for(int i=0;i<loginPopUpTxt_List.size();i++)
					{
						getText=loginPopUpTxt_List.get(i).getText().trim();
						if(getText.contains(MessageTxt))
						{
							Report.updateLog("LoginTest", LogStatus.PASS, "Verify Login PopUp Text", "Login PopUp is displayed as Expected");
							break;
						}
						else if(i==loginPopUpTxt_List.size()-1)
						{
							Report.updateLog("LoginTest", LogStatus.FAIL, "Verify Login PopUp Text", "Login PopUp is not displayed as Expected");
						}
					}
				}
				else 
				{
					Report.updateLog("LoginTest", LogStatus.FAIL, " ", "List is null");
				}
			}

		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Verifying SignIn PopUp");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            login
	//# Function Description:     Function To Login to Application
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public void login(String userName,String password)
	{
		String getText;
		try{

			WebElement userID_Feild=actions.chk_ElementPresent("LoginTest",OR_HomePage.userID , "UserID");
			if(userID_Feild!=null)
			{
				getText=userID_Feild.getText();
				if(!getText.equals(null))
				{
					userID_Feild.clear();
				}
				userID_Feild.sendKeys(userName);
			}
			WebElement passowrd_Feild=actions.chk_ElementPresent("LoginTest",OR_HomePage.password , "Password");
			if(passowrd_Feild!=null)
			{
				passowrd_Feild.sendKeys(password);
			}
			WebElement loginBtn=actions.chk_ElementPresent("LoginTest",  "css::button[class='_2AkmmA _1LctnI _7UHT_c']", "Login Button");
			if(loginBtn!=null)
			{
				loginBtn.click();
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," While Login To Application");
		}
	}
}
