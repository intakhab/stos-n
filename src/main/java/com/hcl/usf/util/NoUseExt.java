package com.hcl.usf.util;
/*package com.hcl.usf.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
*//****
 * 
 * @author X3O6026
 *
 *//*
public class ExtentManager {
	
	private static ExtentReports extent;
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	
	*//***
	 * 
	 * @return
	 *//*
	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	*//***
	 * 
	 * @return
	 *//*
	// Create an extent report instance
	public static ExtentReports createInstance() {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/reports/" + reportFileName);
		// Create an object of Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "prod");
		extent.setSystemInfo("User Name", "IAS");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here ");
		// Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here ");
		// Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		return extent;
	}


}
*/