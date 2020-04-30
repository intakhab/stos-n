package com.hcl.usf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.client.RestTemplate;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.hcl.usf.config.Config;
import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.dto.MailDto;
import com.hcl.usf.dto.ReportsDto;
import com.hcl.usf.service.TCService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

/***
 * @author intakhabalam.s@hcl.com 
 * This is the Utility base class which will do initialization for resources
 */
public class STOSTestExecutor{
	private static final Logger CONSOLELOG = LogManager.getLogger("excu");
	private static String REPORTS_FILE_NAME = null;
	//public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static WebDriverWait wait;
	public static Wait<WebDriver> fluentWait;
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private ThreadLocalSession threadLocalSession=new ThreadLocalSession();
    public static WebDriver driver;
	public static Config config;
	public static CommonDto cdto;
	public static DataTableUtils dataUtils;
	public static JavascriptExecutor js;
	public static String browser = "chrome";// default
	public static String USER_DIR=System.getProperty("user.dir");
	private List<ReportsDto> reportsList=new ArrayList<>(0);
	private static MailDto mailDto;



	/***
	 * Before starting the Test, need to setup some configuration files
	 */
	@BeforeTest
	public void init() {
		CONSOLELOG.info("Setting Thread local session...");
		ThreadLocalUtil.set(threadLocalSession);
		CONSOLELOG.info("Init method called...");
		config = BeanUtil.getBean(Config.class);
		mailDto=new MailDto();
		cdto = config.getTcService().loadTCSettings();
		// Default stos data path otherwise will take a path form UI
		final String dataPath = AppUtil.isObjectEmpty(cdto.getDataPath()) ? getProperty("default.ecom.data.path")
				: cdto.getDataPath();
		cdto.setDataPath(dataPath);
		dataUtils = new DataTableUtils(dataPath);
		// Init Reports
		initReport();
		// here Load Driver
		initDriver();
	}

	/***
	 * Init Driver
	 */
	private void initDriver() {
		CONSOLELOG.info("Init Driver method called...");
		driver = getDriver(cdto.getBrowserType());
		browser = cdto.getBrowserType();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 60);
		wait.pollingEvery(Duration.ofSeconds(2));
		wait.ignoring(ElementNotInteractableException.class);
		wait.withMessage("Time out exception-"+driver.toString());
		// Fluent wait
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(AppUtil.POOL_SECONDS)).ignoring(NoSuchElementException.class);

	}
	/***
	 * @return {@link WebDriver}
	 */
	public synchronized static WebDriver getDriver () {
        return threadLocalDriver.get();
    }
    /***
     * Action return
     * @return
     */
	public Actions getAction() {
		  return new Actions(driver);
	}
	/***
	 * Will init reports, dto and load config file
	 */
	private void initReport() {
		CONSOLELOG.info("Init Reports method called...");
		// Default report path otherwise will take from UI
		final String desDir = AppUtil.isObjectEmpty(cdto.getReportsPath()) ? getProperty("default.ecom.report.extent.path")
				: cdto.getReportsPath();
		final String fileName = AppUtil.currentTimeYYYYMMDD() + ".html";

		final String reportsFullPath = desDir.concat("/").concat(AppUtil.capitalizeFirstLetter(cdto.getBrowserType())).concat("_")
				.concat(this.getClass().getSimpleName()).concat("_").concat(fileName);
		AppUtil.CUSTOM_REPORTS_NAME = desDir.concat("/").concat("STOS_" + cdto.getBrowserType()).concat("_").concat(fileName);
		AppUtil.REPORTS_PATH=reportsFullPath;
		CONSOLELOG.info("Reports Path:" + reportsFullPath);
		cdto.setReportsPath(reportsFullPath);
		cdto.setTcRunTime(AppUtil.currentTimeDDMMYYYY());
		// Instance for ext reports
		report = new ExtentReports(reportsFullPath, true);
		report.addSystemInfo("Host IP", AppUtil.getIPHostName().split("-")[0]);
		report.addSystemInfo("Environment", cdto.getEnvType());
		report.addSystemInfo("User Name", cdto.deafultUser);
		report.addSystemInfo("Browser", cdto.getBrowserType());
		report.loadConfig(Paths.get(getProperty("default.ecom.report.config.path")).toFile());
		//mail
		initMailSetup();
	}
    /***
     * Init mail setup
     */
	private void initMailSetup() {
		mailDto.setFileURL(Paths.get(AppUtil.REPORTS_PATH).toFile());
		mailDto.setFrom(cdto.getFromMail());
		mailDto.setTo(Arrays.asList(cdto.getToWhomEmail().split(",")));
		mailDto.setHtml(true);
	}

	/***
	 * 
	 * @param {@value
	 * 			WebDriver} driver
	 * @param {@value 
	 * 			String} screenshotName
	 * @return {@value String}
	 */
	// This method is to capture the screenshot and return the path of the
	// screenshot.
	public String getScreenShot(String screenshotName) {
		File source = getScreenshot().getScreenshotAs(OutputType.FILE);
		String destination = USER_DIR + "/screenshots/" + screenshotName + AppUtil.currentTimeYYYYMMDD() + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			CONSOLELOG.error("Capturing screen shot error {} "+e.getMessage());
		}
		return destination;
	}

	/***
	 * Capture Shoot
	 * @param {@link
	 *            WebDriver} driver
	 */
	public void captureScreenShot() {
		CONSOLELOG.info("Capturing screen shot");
		info("",logger.addBase64ScreenShot(getBase64String()));

	}
	/**
	 * 
	 * @return {@link TakesScreenshot}
	 */
	private TakesScreenshot  getScreenshot() {
		return (TakesScreenshot) driver;
	}
	/***
	 * Capture Shoot
	 * @param {@link
	 *            WebDriver} driver
	 */
	public void captureScreenShot(String description) {
		CONSOLELOG.info("Capturing screen shot with description-"+description);
		pass(description,logger.addBase64ScreenShot(getBase64String()));

	}
     /**
      * 
      * @return {@link String} base 64 string
      */
	public String getBase64String() {
		String base64String =getScreenshot().getScreenshotAs(OutputType.BASE64);
		return "data:image/png;base64," + base64String;
	}

	/***
	 * @param driver {@link WebDriver}
	 */
	public void captureFullScreenShot(WebDriver driver) {
		CONSOLELOG.info("Capturing full screen shot");
		String base64String = CustomScreenShot.captureFullScreenImage(getDriver());
		String scS = "data:image/png;base64," + base64String;
		info("",logger.addBase64ScreenShot(scS));

	}

	/****
	 * @param driver
	 *            {@link WebDriver}
	 * @param element
	 *            {@link WebElement}
	 */
	public void captureElementScreenShot(WebDriver driver, WebElement element) {
		String base64String = CustomScreenShot.specificElementCaptureScreenImage(getDriver(), element);
		if (base64String != null) {
			String scS = "data:image/png;base64," + base64String;
			info("",logger.addBase64ScreenShot(scS));
		} else {
			captureScreenShot("");
		}
	}

	/***
	 * @param {@value WebDrriver} driver
	 * @param {@value String} screenshotName
	 * @return {@value String}
	 */
	private String addScreenShot(WebDriver driver) {
		CONSOLELOG.info("Adding screen shot");
		return getScreenshot().getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * @param browserType
	 * @return {@link WebDriver}
	 */
	private WebDriver getDriver(String browserType) {
		switch (browserType) {
		case "chrome":
			return initChromeDriver();
		case "firefox":
			return initFirefoxDriver();
		case "ie":
			return initIEDrive();
		case "edge":
			return initEdgeDrive();	
		default:
			CONSOLELOG.info("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
			return initChromeDriver();
		}
	}
	/***
	 * @return {@link WebDriver}
	 */
	private WebDriver initChromeDriver() {
		try {
			CONSOLELOG.info("Intializing Chrome driver");
			String ecomVdi = config.getEnv().getProperty("ecom.vdi");
			CONSOLELOG.info("VDI Option enabled-" + ecomVdi);
			if (ecomVdi != null && "true".equals(ecomVdi)) {
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
				System.setProperty("webdriver.chrome.driver", USER_DIR + "\\lib\\chromedriver.exe");
			} else {
				WebDriverManager.chromedriver().setup();
			}
			ChromeOptions options = new ChromeOptions();
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>(0);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", USER_DIR + "\\" + getProperty("default.ecom.download.dir"));
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("start-maximized", "disable-popup-blocking");
			// WebDriver driver = new ChromeDriver(options);
			return ThreadLocal.withInitial(() -> new ChromeDriver(options)).get();
		} catch (Exception e) {
			CONSOLELOG.error("Intializing Chrome driver failed {} " + e.getMessage());
		}
		return null;
	}

	/***
	 * @return {@link WebDriver}
	 */
	private WebDriver initFirefoxDriver() {
		try {
			CONSOLELOG.info("Intializing Firefox driver");
			// WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", USER_DIR + "\\lib\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>(0);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", USER_DIR + "\\" + getProperty("default.ecom.download.dir"));
			options.setCapability("prefs", chromePrefs);
			options.setCapability("start-maximized", "disable-popup-blocking");
			return ThreadLocal.withInitial(() -> new FirefoxDriver(options)).get();
		} catch (Exception e) {
			CONSOLELOG.error("Intializing Firefox driver failed {} " + e.getMessage());
		}
		return null;
	}

	/***
	 * @return {@link String}
	 */
	private WebDriver initIEDrive() {
		try {
			CONSOLELOG.info("Intializing IE driver");
			System.setProperty("webdriver.ie.driver", USER_DIR + "\\lib\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.bin", "C:/Program Files (x86)/Internet Explorer/iexplore.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>(0);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", USER_DIR + "\\" + getProperty("default.ecom.download.dir"));
			options.setCapability("prefs", chromePrefs);
			options.setCapability("start-maximized", "disable-popup-blocking");
			options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);

			return ThreadLocal.withInitial(() -> new InternetExplorerDriver(options)).get();

		} catch (Exception e) {
			CONSOLELOG.error("Intializing IE driver failed {} " + e.getMessage());
		}
		return null;
	}
	
	/***
	 * @return {@link String}
	 */
	private WebDriver initEdgeDrive() {
		CONSOLELOG.info("Intializing Edge driver");
		System.setProperty("webdriver.edge.driver", USER_DIR + "\\lib\\msedgedriver.exe");
		EdgeOptions options = new EdgeOptions();
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>(0);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", USER_DIR + "\\" + getProperty("default.ecom.download.dir"));
		options.setCapability("prefs", chromePrefs);
		options.setCapability("start-maximized", "disable-popup-blocking");
		return ThreadLocal.withInitial(() -> new EdgeDriver(options)).get();
	}
	
	/***
	 * Invoking URL
	 */
	public void openURL(String url) {
		commonPassLog("URL :: " +url);
		driver.manage().window().maximize();
		driver.navigate().to(url);
	}
	/***
	 * @param result {@link ITestResult}
	 */
	@AfterMethod
	public void getResult(ITestResult result) {
		String testName=AppUtil.capitalizeFirstLetter(result.getName());
		try {
			CONSOLELOG.info("Get Result method called::"+testName);
			if (result.getStatus() == ITestResult.FAILURE) {
				String base64Str = addScreenShot(driver);
				fail(testName + " Test Case Failed",logger.addBase64ScreenShot("data:image/png;base64," + base64Str));
				//logger.log(LogStatus.FAIL,"<span style='color:red'>" + result.getThrowable() + " - Test Case Failed<span>");
				// adding base64 strings to log:
				// String screenshotPath = getScreenShot(driver, result.getName());
				// To add it in the extent report
				//logger.log(LogStatus.INFO, "Error Snapshot is " , logger.addBase64ScreenShot("data:image/png;base64," + base64Str));
				//fail("Error Snapshot is ",logger.addBase64ScreenShot("data:image/png;base64," + base64Str));
			} else if (result.getStatus() == ITestResult.SKIP) {
				skip(testName + " Test Case Skipped");
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				pass(testName +" Test Case Passed");
			}
		} catch (Exception e) {
			fail(testName + "<=Fatal=>" +analaysisTestException(e));
		} finally {
			report.endTest(logger);
			report.flush();
			CONSOLELOG.info("Reports end and flush");
			try {
			   countReports();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public String analaysisTestException(Throwable e) {
		String errorMsg="";
		if(e instanceof TimeoutException) {
			errorMsg="Due to environment slowness, Time out occured";
		}else if(e instanceof ElementNotInteractableException) {
			errorMsg="Thrown to indicate that although an element is present on the DOM, it is not in a state that canbe interacted with";
		}else if(e instanceof ElementNotVisibleException) {
			errorMsg="Thrown to indicate that although an element is present on the DOM, it is not visible, and so isnot able to be interacted with.";
		}else if(e instanceof ElementNotSelectableException) {
			errorMsg="Thrown to indicate that although an element is present on the DOM, it is not selectable, and sois not able to be interacted with.";
		}else if(e instanceof NoSuchElementException) {
			errorMsg="This exception is due to accessing an element which is not available on the page.";
		}else if(e instanceof WebDriverException) {
			errorMsg="Exception comes when a code is unable to initialize WebDriver.";
		}else {
			errorMsg= e.getMessage();
		}
			
		return errorMsg;
	}
	
	/***
	 * Close driver
	 */
	private void closeWebDriver() {
		try {
		if (driver != null) {
			driver.close();
			driver.quit();
			CONSOLELOG.info("Webdriver closed");
		}
		}catch (Exception e) {
			CONSOLELOG.error("Driver cleanup error {} "+e.getMessage());
			driver.quit();
			CONSOLELOG.info("Forcely closing webdriver");
		}
	}
	/***
	 * After test end this method will called
	 */
	@AfterTest
	public void endReport() {
		report.close();
		CONSOLELOG.info("Reports closed");
		saveReports();
		closeWebDriver();
	}
	
	private void postStatus(String strPost) {
		RestTemplate restTemplate=new RestTemplate();
		final String uri="http://localhost:"+config.getEnv().getProperty("server.port");
		CONSOLELOG.info("Posting reports to status node ===>"+uri);
		String url = uri+"/call/poststatus";
		String result = restTemplate.postForObject(url, strPost, String.class);
		CONSOLELOG.info("Posting result-" + result);

	}
	/***
	 * This method will count all reports and manipulate it
	 * and also save content into XML and DB
	 */
	public void countReports() {
		 int pass=0;
		 int fail=0;
		 int totalsteps=0;
		 String status="FAIL";
		try {
			ReportsDto reportsDto=new ReportsDto();
			CONSOLELOG.info("Reports generating......");
			List<Log> list = logger.getTest().getLogList();
			String testSenarios = logger.getTest().getName();
			String testName=AppUtil.TEST_NAME;
			status = logger.getTest().getStatus().name();
			String runDuration=logger.getTest().getRunDuration();
			CONSOLELOG.info("Staus::" + status);
			CONSOLELOG.info("TestSenarios::" + testSenarios);
			CONSOLELOG.info("Run Duration::" + runDuration);
			ThreadLocalUtil.get().runDuration=runDuration;
			ThreadLocalUtil.get().runStatus=status;
			if("skip".equalsIgnoreCase(status)) {
				CONSOLELOG.info("Skipping....");
			    return;
			}
			
			List<String> stepsList=new ArrayList<>();
			List<String> detailsList=new ArrayList<>();
			List<String> stepsStatusList=new ArrayList<>();
			List<String> stepsDateList=new ArrayList<>();
			for (Log t : list) {
				if (t.getLogStatus().toString().equalsIgnoreCase("pass")) {
					pass++;
					stepsList.add(t.getStepName());
					detailsList.add(t.getDetails());
					stepsStatusList.add(t.getLogStatus().name());
					stepsDateList.add(AppUtil.getMMDDYYY(t.getTimestamp().getTime()));
				}
				if (t.getLogStatus().toString().equalsIgnoreCase("fail")) {
					fail++;
					stepsList.add(t.getStepName());
					detailsList.add(t.getDetails());
					stepsStatusList.add(t.getLogStatus().name());
					stepsDateList.add(AppUtil.getMMDDYYY(t.getTimestamp().getTime()));
				}
				
			}
			if(findData("URL")!=null) {
			    reportsDto.setUrl(findData("URL"));
			    cdto.setEnvUrl(findData("URL"));
			}else {
				reportsDto.setUrl(config.cDto.getEnvUrl());
				 cdto.setEnvUrl(config.cDto.getEnvUrl());
			}
			reportsDto.setEnvStatus(ThreadLocalUtil.get().siteRunningStatus);
			reportsDto.setTotalPass(""+pass);
			reportsDto.setTotalFail(""+fail);
			totalsteps = pass + fail;
			reportsDto.setTotalSteps(""+totalsteps);
			reportsDto.setSenariosName(testSenarios);
			reportsDto.setTcId(cdto.getRunTcId());
			reportsDto.setTcName(testName);
			reportsDto.setStatus(status);
			reportsDto.setBrowser(cdto.getBrowserType());
			//
			reportsDto.setSteps(stepsList);
			reportsDto.setDetails(detailsList);
			reportsDto.setRunDate(stepsDateList);
			reportsDto.setStepStatus(stepsStatusList);
			reportsList.add(reportsDto);
			AppUtil.createXMLReports(reportsDto,ThreadLocalUtil.get().isHealthCheck);
			CONSOLELOG.info("Reports generating successfully...");
			
		} catch (Exception e) {
			CONSOLELOG.error("Report has not generated {} "+e.getMessage());
		}
		finally {
			try {
				postStatus(status);
			} catch (Exception ex) {
			}
		}
	}
	/***
	 * Saving reports
	 */
	public void saveReports() {
		try {
			CONSOLELOG.info("DevOps Operation Starting....");
			devOpsOperation();
			CONSOLELOG.info("Reports generating......");
			for(ReportsDto repo:reportsList) {
				cdto.setTotalSteps(repo.getTotalSteps());
				cdto.setPassSteps(repo.getTotalPass());
				cdto.setFailSteps(repo.getTotalFail());
				cdto.setTcName(repo.getTcName());
				cdto.setRunStatus(repo.getStatus());
				cdto.setRunTcId(repo.getTcId());
				cdto.setReportsPath(AppUtil.REPORTS_PATH);
				//cdto.setTcRunTime(AppUtil.currentTimeDDMMYYYY());
				cdto.setRunDuration(ThreadLocalUtil.get().runDuration);
				updateTC(cdto);
			}
			CONSOLELOG.info("Reports generating successfully...");
		} catch (Exception e) {
			CONSOLELOG.error("Reports not saved {} "+e.getMessage());
		}
		finally {
			sendMailAndReportSaved();
			reportsList.clear();
			ThreadLocalUtil.get().reset();
		}
	}
	/***
	 * Util method jira and confulence
	 */
	private void devOpsOperation() {
		if(AppUtil.WILL_JIRA_TCIKET_CREATE) {
			String description="Reporting bug agaist this test [ "+ AppUtil.TEST_NAME+ " ] , Attached the report file, kindly refer it.";
			String header=config.getEnv().getProperty("jira.issue.header")+"-"+AppUtil.TEST_NAME;
			String ticket = config.jiraClientService.createIssueOnJira(config.getEnv().getProperty("jira.product.key"),
					1L, header, description, cdto.getReasonFail(),
					Paths.get(AppUtil.REPORTS_PATH).toAbsolutePath().toString());
			cdto.setJiraTicket(ticket);
			CONSOLELOG.info("JIRA ticket created with attachement");
			try {
				String comments="Following user story has been covered in this attached sheet "+cdto.getTcStory();
				String url=config.confluenceService.postAttachment(Paths.get(AppUtil.REPORTS_PATH).toFile(), comments);
				cdto.setConfluenceUrl(url);
				CONSOLELOG.info("File Uploaded to confulence with attachement");
			} catch (Exception e) {
				CONSOLELOG.error("Uploading problem to confulence with attachement {} "+e.getMessage());
			} 
			
		}
		
	}

	/***
	 * Sending Mail and Reports
	 */
	private void sendMailAndReportSaved() {
		if (cdto.getEnableMail() && !ThreadLocalUtil.get().isHealthCheck) {
			CONSOLELOG.info("Normal mail send with attachement");
			config.emailService.prepareMail(cdto, mailDto);
		} else {
			CONSOLELOG.info("Email Enabled? " + cdto.getEnableMail());
			CONSOLELOG.info("Is HealthCheck Enabled? " + ThreadLocalUtil.get().isHealthCheck);
			if (ThreadLocalUtil.get().isHealthCheck) {
				CONSOLELOG.info("Regression mail send with attachement");
				String templateBody = config.downloadReportsService.generateRegReport();
				File file = Paths.get(AppUtil.REPORTS_PATH)
						.toFile();
				mailDto.setFileURL(file);
				mailDto.setInlineFilePath(USER_DIR+"/lib/Logo.png");
				config.emailService.prepareRegMail(cdto, mailDto, templateBody);
			}
		}
		String xmlPath=AppUtil.REPORTS_DIR+AppUtil.REG_REPORTS_INPUT_NAME;
		String htmlPath=AppUtil.REPORTS_DIR+AppUtil.REG_REPORTS_OUTPUT_NAME;
		File xmlFile = Paths.get(xmlPath).toFile(); // XML file to read and rename it
		File htmlFile=Paths.get(htmlPath).toFile();//html file to read and rename it
		if(xmlFile.exists()) {
			final String currTime=AppUtil.currentTimeYYYYMMDD();
			CONSOLELOG.info("XML File exist ==> "+htmlFile);
			CONSOLELOG.info("HTML File exist ==> "+xmlFile);
			String newXMLFile=xmlPath+"_"+currTime;
			String newHTMLFile=htmlPath+"_"+currTime;
			AppUtil.moveReplaceFile(xmlFile.toString(),newXMLFile);
			AppUtil.moveReplaceFile(htmlFile.toString(),newHTMLFile);
			
		}
		
	}
   

	public void appendReportFile(String logInfo){
		// Write Content
		try (FileWriter writer = new FileWriter(REPORTS_FILE_NAME, true);
				BufferedWriter bw = new BufferedWriter(writer);
				PrintWriter pw = new PrintWriter(bw);) {
			pw.println(logInfo);
			pw.close();
		} catch (IOException e) {
			CONSOLELOG.error("Appending reports failed {} "+e.getMessage());

		}
	}
	/***
	 * @param steps {@link String}
	 * @param desc {@link String}
	 */
	private void info(String steps,String desc) {
		logger.log(LogStatus.INFO,steps,desc);
	}

	/***
	 * @param steps {@link String}
	 * @param desc {@link String}
	 */
	private void pass(String steps,String desc) { 
		CONSOLELOG.info(steps);
		logger.log(LogStatus.PASS,steps, desc);
	}
	/**
	 * @param msg {@link String}
	 */
	private void pass(String msg) {
		logger.log(LogStatus.PASS, msg);
	}

	/**
	 * @param msg
	 *            {@link String}
	 */
	private void skip(String msg) {
		logger.log(LogStatus.SKIP, AppUtil.testSkip(msg));
	}

	/**
	 * @param msg
	 *            {@link String}
	 */
	private void fail(String msg) {
		logger.log(LogStatus.FAIL, AppUtil.failFormat(msg));
	}
	/**
	 * @param stepName {@link String}
	 * @param details {@link String}
	 */
	private void fail(String stepName, String details) {
		logger.log(LogStatus.FAIL, AppUtil.failFormat(stepName), details);
	}
	/**
	 * @param msg {@link String}
	 */
	private void error(String msg) {
		logger.log(LogStatus.ERROR, AppUtil.errorFormat(msg));
	}

	/***
	 * @return {@link String}
	 */
	public String getProperty(String prop) {
		return config.getEnv().getProperty(prop);
	}

	/***
	 * @return {@link TCService}
	 */
	public TCService getTCService() {
		return config.getTcService();
	}

	/***
	 * Common info Logging
	 * @param steps {@link String}
	 */
	public void commonInfoLog(String steps) {
		CONSOLELOG.info(steps);
		info(steps,"-");
	}

	/***
	 * @param steps {@link String}
	 * @param details {@link String}
	 */
	public void commonInfoLog(String steps,String details) {
		CONSOLELOG.info(steps);
		info(steps,details);
	}

	/**
	 * @param msg {@link String}
	 */
	public void commonSkipLog(String msg) {
		CONSOLELOG.warn(msg);
		skip(msg);
	}
	/***
	 * Common Pass Logging
	 * @param msg {@link String}
	 */
	public void commonPassLog(String msg) {
		CONSOLELOG.info(msg);
		pass(msg,"-");
	}
	/**
	 * @param steps {@link String}
	 * @param details {@link String}
	 */
	public void commonInfoWithScreenShotLog(String steps) {
		CONSOLELOG.info(steps);
		info(steps,logger.addBase64ScreenShot(getBase64String()));
	}
	/**
	 * @param steps {@link String}
	 * @param details {@link String}
	 */
	public void commonPassWithScreenShotLog(String steps) {
		CONSOLELOG.info(steps);
		pass(steps,logger.addBase64ScreenShot(getBase64String()));
	}
	/**
	 * @param steps {@link String}
	 * @param details {@link String}
	 */
	public void commonFailWithScreenShotLog(String steps) {
		CONSOLELOG.error(steps);
		fail(steps,logger.addBase64ScreenShot(getBase64String()));
	}
	
	/***
	 * Common Fail Logging
	 * @param msg
	 *            {@link String}
	 */
	public void commonFailLog(String msg) {
		CONSOLELOG.error(msg);
		fail(msg);
	}

	/***
	 * Common Fail Logging with desc
	 * @param msg {@link String}
	 * @param desc {@link String}
	 */
	public void commonFailLog(String msg, String desc) {
		CONSOLELOG.error(msg);
		fail(msg, desc);
	}

	/***
	 * Common Error Logging with desc
	 * @param msg {@link String}
	 */
	public void commonErrorLog(String msg) {
		CONSOLELOG.error(msg);
		error(msg);
	}

	/***
	 * Console info Logging
	 * @param msg {@link String}
	 */
	public void consoleInfoLog(String msg) {
		CONSOLELOG.info(msg);
	}

	/***
	 * Console error logging
	 * @param msg {@link String}
	 */
	public void consoleErrorLog(String msg) {
		CONSOLELOG.error(msg);
	}

	/**
	 * Common method will find the data from xsl
	 * @param col XSL column
	 * @return data from xsl sheet
	 */
	public String findData(String col) {
		return dataUtils.getData(col);
	}

	// Sleep in seconds
	public void pauseInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}
	// Sleep in Milliseconds
	public void pauseInMiliSeconds(int mil) {
		try {
			Thread.sleep(mil * 10);
		} catch (InterruptedException e) {
		}
	}

	/***
	 * First insert into table then update 
	 * @param testCase {@link String}
	 * @param cdto {@link String}
	 */
	public CommonDto initAndSaveTC(String testCase, CommonDto cdto) {
		cdto.setTcName(testCase);
		CommonDto dto = getTCService().findByTCName(testCase);// Required tc id as todo
		cdto.setTcId(dto.getTcId());
		cdto.setTcSheetName(dto.getTcSheetName());
		cdto.setTcVersion(dto.getTcVersion());
		cdto.setTcStory(dto.getTcStory());
		cdto.setRunStatus(AppUtil.RUNNING);
		cdto.setTcRunTime(AppUtil.currentTimeDDMMYYYY());//Runtime ias
		Long id = getTCService().saveOrUpdateRuningTC(cdto).getId();// First time it will save
		AppUtil.REPORT_ID=id;
		cdto.setRunTcId(String.valueOf(id));
		return cdto;
	}

	/***
	 * Update test case 
	 * @param cdto  {@link CommonDto}
	 */
	public void updateTC(CommonDto cdto) {
		getTCService().saveOrUpdateRuningTC(cdto);
	}
}