package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import FunctionalLibraries.*;
import supportLibraries.*;
import Data.*;

public class LoginTest extends Controller 
{

	GeneralFunctions genFun = new GeneralFunctions();
	Home_Page home_page=new Home_Page();
	Welcome_Page welcome_page=new Welcome_Page();
	
	//*********************************************************************//
	//# Script Name:            LoginTest
	//# Script Description:     Check Login and logout Functionality
	//# Author Name:            Smarth
	//# Date:                   28-Dec-2016
	//*********************************************************************//
  @Test
  public void Login_Flipkart() 
  {
	  try
	  {
		  //Data from Excel
	  String homePage_Url = ReadDataSheet.getData("General_Data", "LoginTest", "Page_URL");
	  String message=ReadDataSheet.getData("General_Data", "LoginTest", "Message");
	  String message_Array[]=message.split(";");
	  String userName=ReadDataSheet.getData("General_Data", "LoginTest", "UserName");
	  String password=ReadDataSheet.getData("General_Data", "LoginTest", "Password");
	  
	  genFun.hitURL(GlobalTestData.get_GlobalData("URL")); 			// httting URL
	  genFun.verify_Page_URL(homePage_Url, "FlipKart home Page"); 	// Verify Page URL
	  home_page.navigateTo_loginPage(); 							// Naviage To Login Screen
	  home_page.verify_LoginPopUp(message_Array[0]); 				// Verify Login PopUp
	  home_page.login(userName, password); 							// Perform Login
	  welcome_page.verify_WelcomePage(message_Array[1]);			// Verfiy Welcome Page
	 						
	  } 
	  catch (IOException e)
	  {
		  Report.updateLog("LoginTest", LogStatus.FAIL, "Exception Occured", "");
      }
  }
}
