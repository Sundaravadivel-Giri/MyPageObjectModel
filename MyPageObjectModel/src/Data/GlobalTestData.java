package Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import supportLibraries.Report;

import com.relevantcodes.extentreports.LogStatus;

public class GlobalTestData {
			
		static Properties GlobalData=new Properties();
		
	//*********************************************************************//
	//# Function Name:            loadPropertyFiles
	//# Function Description:     Function to Load Global Settings.Properties File
	//# Author Name:              Sundar
	//# Date:                     24-Dec-2016
	//*********************************************************************//
		public static void loadPropertyFiles()
		{
			try 
			{
				GlobalData.load(new FileInputStream("GlobalSettings.properties"));
			} 
			catch (IOException e)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Loading Properties File");
			}	 		
		}
		
		//*********************************************************************//
		//# Function Name:            get_GlobalData
		//# Function Description:     Function to Get Data from Global Settings.Properties File
		//# Author Name:              Sundar
		//# Date:                     24-Dec-2016
		//*********************************************************************//
		public static String get_GlobalData(String property) 
		{
			try
			{
				return GlobalData.getProperty(property);
			}
			catch (Exception E)
			{
				Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Get Data from Global Settings.Properties File");
			}
			return null;
		}

}

