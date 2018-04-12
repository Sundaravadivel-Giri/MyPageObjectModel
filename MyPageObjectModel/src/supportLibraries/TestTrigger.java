
package supportLibraries;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
public class TestTrigger {
	static List<TestParam> list;

	public static void main(String args[]) {

		try 
		{
			TestNG testNG = new TestNG();
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			testNG.addExecutionListener(new Controller()); // Add an executionListener, Controller is the class implementing TestNG listeners
			List<List<TestParam>> testLists = testList();
			System.out.println(" The testList Size is" +testLists.size());
			for (int j = 0; j < testLists.size(); j++) {
				List<XmlTest> tests = new ArrayList<XmlTest>();
				List<TestParam> suiteTest = testLists.get(j);
				XmlSuite suite = new XmlSuite();
				suite.setName("Suite " + "FrameWorkTest"); // you can specify any name here for suite name
				for (int i = 0; i < suiteTest.size(); i++) {
					XmlTest test = new XmlTest(suite);
					List<XmlClass> xmlclass = new ArrayList<XmlClass>();
					xmlclass.add(new XmlClass(suiteTest.get(i).getTestClass()));
					test.setName(suiteTest.get(i).getTestName());
					test.setXmlClasses(xmlclass);
					tests.add(test);
				}
				suite.setTests(tests);
				suites.add(suite);
			}
			testNG.setXmlSuites(suites);
			testNG.run();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public XmlSuite getXmlSuite() {
		XmlSuite suite = new XmlSuite();
		suite.setName("TestSuite");
		return suite;
	}
	public static List<List<TestParam>> testList() throws Exception {
		List<List<TestParam>> testRunner = new ArrayList<List<TestParam>>();
		FileInputStream fis = new FileInputStream("./RunManager.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		int totalSheets = wb.getNumberOfSheets();
		try{
		for (int i = 0; i < totalSheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();

			list = new ArrayList<TestParam>();
			for (int count = 1; count <= sheet.getLastRowNum(); count++) {
				TestParam test = new TestParam();
				HSSFRow row = sheet.getRow(count);
//				 System.out.println("Running test case " +
//				 row.getCell(3).toString() +count );
				if (row.getCell(2).toString().equalsIgnoreCase("Yes")) 
				{
					// List a1=new ArrayList<>();
//					String testClass_withPackage = "testscripts."+row.getCell(0).toString()+"."; 		
//					System.out.println(" WIth the pack " +testClass_withPack);
					test.setTestName(row.getCell(0).toString()); 						// the testCase Name 
					test.setTestClass("Test."+row.getCell(0).toString()); 				// The testClass Name along with package name Test.
//					System.out.println("check the testClass"+test.getTestClass());
//					test.setBrowser(row.getCell(4).toString());  						// The Browser Name 
					list.add(test);
				}
				else if(row.getCell(2).toString().equalsIgnoreCase(""))// If execution in RunManager is left blank- Exception Handling
				{
					System.out.println("No TestCase present");
					break;
				}
				else if(row.getCell(0).toString().equalsIgnoreCase(""))
				{
					 break;
				}

			}
			testRunner.add(list);
		}
			
//		}
		}
		catch(Exception E)
		{
			System.out.println(" Exception in Reading TestCases from Run Manager - " +E);
		}
		return testRunner;
	}





}
