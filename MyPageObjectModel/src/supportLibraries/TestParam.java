package supportLibraries;

public class TestParam {
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName1) {
		this.testName = testName1;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass1) {
		testClass = testClass1;
	}

	public String getExcetionFlag() {
		return ExcetionFlag;
	}

	public void setExcetionFlag(String excetionFlag) {
		ExcetionFlag = excetionFlag;
	}

	private String toolName;
	private String testName;
	private String browser;

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	private String testClass;
	private String ExcetionFlag;

	/*
	 * public TestParameter1(String testName1, String testClass1) {
	 * this.testName = testName1; this.testClass = testClass1; }
	 */
}
