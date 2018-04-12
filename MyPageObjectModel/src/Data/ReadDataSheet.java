package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.LogStatus;

import supportLibraries.Report;

public class ReadDataSheet {
	static XSSFWorkbook W ;
	
	//*********************************************************************//
	//# Function Name:            loadExcelSheet
	//# Function Description:     Function to Set path For Excel File
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public  static void loadExcelSheet() throws IOException
	{
		try
		{
			String FilePath = "./Automation.xlsx";
			File src=new File(FilePath);
			FileInputStream fi = new FileInputStream(src);
			W = new XSSFWorkbook(fi);
		}
		catch (Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Set path For Excel File");
		}
	}
	
	//*********************************************************************//
	//# Function Name:            getData
	//# Function Description:     Function to get Data From Excel File
	//# Author Name:              Sundar
	//# Date:                     26-Dec-2016
	//*********************************************************************//
	public static String getData( String SheetName,String RowName,String ColumnName) throws IOException
	{
		try
		{
			boolean rowFlag=false,columnFlag=false;
			int rowIndex=0,columnIndex=0;
			XSSFSheet sheet=W.getSheet(SheetName);
			int rowLength=sheet.getLastRowNum();
			int ColumnLength=20;
			for(int row=1;row<=rowLength;row++)
			{
				String rowname=sheet.getRow(row).getCell(0).getStringCellValue();
				if(rowname.equalsIgnoreCase(RowName))
				{
					rowIndex=row;
					rowFlag=true;
					break;
				}
				else if(row==rowLength)
				{
					Report.updateLog("", LogStatus.FAIL, "Check Data"," Invalid Row Name");
				}
			}
			for(int column=1;column<ColumnLength;column++)
			{
				String columnname=sheet.getRow(0).getCell(column).getStringCellValue();
				if(columnname.equalsIgnoreCase(ColumnName))
				{
					columnIndex=column;
					columnFlag=true;
					break;
				}
				else if(column==ColumnLength-1)
				{
					Report.updateLog("", LogStatus.FAIL, "Check Data"," Invalid Column Name");
				}
			}
			if(rowFlag==true && columnFlag==true)
			{
				String cellValue=sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
				return cellValue;
			}
			else
			{
				return null;
			}
		}
		catch(Exception E)
		{
			Report.updateLog("", LogStatus.FAIL, "Exception Occured"," Get Data From Excel File");
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
