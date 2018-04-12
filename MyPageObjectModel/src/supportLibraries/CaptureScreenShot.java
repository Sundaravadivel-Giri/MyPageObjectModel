package supportLibraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class CaptureScreenShot 
{
	//*********************************************************************//
	//# Function Name:            TakeScreenShot
	//# Function Description:     Function to Set path For Capturing ScreenShot
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public static  String TakeScreenShot(RemoteWebDriver driver,String ScreenShotName) throws Exception
	{
		try
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			String screenShotPath="./ScreenShots/"+ScreenShotName+".png";
			FileUtils.copyFile(source, new File(screenShotPath));
			return screenShotPath;
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Set path For Capturing ScreenShot");
		}
		return null;
	}
}
