package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import FunctionalLibraries.*;
import supportLibraries.*;
import Data.*;

public class Perform_Search extends Controller 
{
	Item_Results_Page item_results_page=new Item_Results_Page();
	GeneralFunctions genFun = new GeneralFunctions();
	Home_Page home_page=new Home_Page();
	Welcome_Page welcome_page=new Welcome_Page();
	
	
	//*********************************************************************//
	//# Script Name:            Perform Search
	//# Script Description:     Check Login and logout Functionality and Perform Search For an Item
	//# Author Name:            Sundar
	//# Date:                   29-Dec-2016
	//*********************************************************************//
  @Test
  public void SearchItem_Flipkart() 
  {
	  try
	  {
		  //Data from Excel
	  String homePage_Url = ReadDataSheet.getData("General_Data", "Perform_Search", "Page_URL");
	  String message=ReadDataSheet.getData("General_Data", "Perform_Search", "Message");
	  String message_Array[]=message.split(";");
	  String userName=ReadDataSheet.getData("General_Data", "Perform_Search", "UserName");
	  String password=ReadDataSheet.getData("General_Data", "Perform_Search", "Password");
	  String searchTerm=ReadDataSheet.getData("General_Data", "Perform_Search", "SearchTerm");
	  
	  genFun.hitURL(GlobalTestData.get_GlobalData("URL"));				// httting URL
	  genFun.verify_Page_URL(homePage_Url, "FlipKart home Page");		// Verify Page URL
	  home_page.navigateTo_loginPage();									// Naviage To Login Screen
	  home_page.verify_LoginPopUp(message_Array[0]);					// Verify Login PopUp
	  home_page.login(userName, password);								// Perform Login
	  welcome_page.verify_WelcomePage(message_Array[1]);				// Verfiy Welcome Page
	  welcome_page.searchItem(searchTerm);								// perform Search
	  item_results_page.check_ResultList_Title(searchTerm);				// Check ResultList title
	 							
	  } 
	  catch (IOException e)
	  {
		  Report.updateLog("LoginTest", LogStatus.FAIL, "Exception Occured", "");
      }
  }
}
