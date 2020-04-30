package com.hcl.usf.util;
/*package com.hcl.usf.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

*//***
 * 
 * @author X3O6026
 *
 *//*
public class TestExecutor {

	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeMethod
	public void initDriver() {
		setDriver("chrome", "https://google.com");
	}
	
	
	@BeforeTest
	public void startReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/reports/STMExtentReport.html");
		// Create an object of Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("User Name", "IAS");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here ");
		// Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here ");
		// Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD);
		
	}

	// This method is to capture the screenshot and return the path of the
	// screenshot.
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	public String captureScreenShots(WebDriver driver) throws IOException {
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String base64String = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	    System.out.println(base64String);
	    System.out.println(System.getProperty("user.dir"));
	    
	    logger.log(Status.INFO, " " +  MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
		return base64String;

	}
	*//***
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return
	 *//*
	public static String addBase64ScreenShot(WebDriver driver)  {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}

	*//***
	 * 
	 * @param browserType
	 * @param appURL
	 *//*
	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
			driver = initChromeDriver(appURL);
		}
	}

	*//***
	 * @param appURL
	 * @return
	 *//*
	private WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		// System.setProperty("webdriver.chrome.driver", driverPath +
		// "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.com/");
		return driver;
	}

	*//***
	 * 
	 * @param appURL
	 * @return
	 *//*
	private WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
    *//***
     * 
     * @param result
     * @throws Exception
     *//*
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				// MarkupHelper is used to display the output in different colors
				logger.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				logger.log(Status.FAIL, "<span style='color:red'>" + result.getThrowable()
						+ " - Test Case Failed<span>");
				String base64Str = addBase64ScreenShot(driver);
				// adding base64 strings to log:
				// String screenshotPath = getScreenShot(driver, result.getName());
				
				// To add it in the extent report
				logger.fail("Test Case Failed Snapshot is below "
						+ logger.addScreenCaptureFromBase64String(base64Str));
				logger.fail("Test Case Failed Snapshot is ", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Str).build());
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(Status.SKIP,
						MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(Status.PASS,
						MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN));
			}
		} finally {

			driverClose();
		}
	}
	
	public void driverClose() {
		if (driver != null)
			driver.close();
		driver.quit();
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}
	//
	public  void info(String msg) {
		logger.log(Status.INFO, msg);
	}

	*//***
	 * 
	 *//*
	public  void debug(String msg) {
		logger.log(Status.DEBUG, msg);
	}

	*//***
	 * 
	 *//*
	public  void skip(String msg) {
		logger.log(Status.SKIP, msg);
	}

	*//***
	 * 
	 *//*
	public  void fail(String msg) {
		logger.log(Status.FAIL, MarkupHelper.createLabel(msg, ExtentColor.RED));
	}

	*//***
	 * 
	 *//*
	public  void error(String msg) {
		logger.log(Status.ERROR, MarkupHelper.createLabel(msg, ExtentColor.RED));
	}

	*//***
	 * 
	 *//*
	public  void fatal(String msg) {
		logger.log(Status.FATAL, MarkupHelper.createLabel(msg, ExtentColor.RED));
	}
}
*/