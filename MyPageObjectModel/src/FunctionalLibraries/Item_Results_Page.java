package FunctionalLibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

import Data.ReadDataSheet;
import ObjectRepostries.OR_ObjTypes;
import ObjectRepostries.OR_Welcome_Page;
import supportLibraries.ElementsAction;
import supportLibraries.CaptureScreenShot;
import supportLibraries.Controller;
import supportLibraries.Report;

public class Item_Results_Page extends ElementsAction
{
	ReadDataSheet data=new ReadDataSheet();
	CaptureScreenShot captureScreenShot= new CaptureScreenShot();
	ElementsAction elementsAction=new ElementsAction();
	Controller remoteDriver=new  Controller();
	GeneralFunctions genFunctions= new GeneralFunctions();
	
	//*********************************************************************//
	//# Function Name:            check_ResultList_Title
	//# Function Description:     Function To Check ResultList Title
	//# Author Name:              Sundar
	//# Date:                     29-Dec-2016
	//*********************************************************************//
	public void check_ResultList_Title(String SearchItem)
	{
		try
		{
			String getText;
			List<WebElement>h1_List=elementsAction.getlist_ByTagName("Perform_Search", OR_ObjTypes.h1);
			if(h1_List!=null)
			{
				List<WebElement>span_List=h1_List.get(0).findElements(OR_ObjTypes.span);
				if(span_List!=null)
				{
					for(int i=0;i<span_List.size();i++)
					{
						getText=span_List.get(i).getText().trim();
						if(getText.equalsIgnoreCase(SearchItem))
						{
							Report.updateLog("Perform_Search", LogStatus.PASS, "Verify ResultList Title", "ResultList is Successfully loaded for the Search Item "+SearchItem);
							break;
						}
						else if(i==span_List.size()-1)
						{
							Report.updateLog("Perform_Search", LogStatus.FAIL, "Verify ResultList Title", "ResultList is not Successfully loaded for the Search Item "+SearchItem);
						}
					}
				}
				else
				{
					Report.updateLog("Perform_Search", LogStatus.FAIL, "Check List By TagName", "List is null");
				}	
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured","Verifying ResultList Title");
		}	
	}
}
