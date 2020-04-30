package com.hcl.usf.util;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import com.hcl.usf.common.STOSException;
import com.hcl.usf.dto.CommonDto;

/***
 * This is the Common Function class which will used throughout application
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class CommonElement extends STOSTestExecutor {

	public CommonElement() {
		super();
	}

	/***
	 * Wait For Visibility of Element waitExplicitlyForPresence
	 * @param xpath {@link String}
	 * @return {@link WebElement}
	 */
	public WebElement waitForVisibilityOfElement(String xpath) {
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));

	}
    /**
     * Wait for Explicitly Clickable
     * @param xPath {@link String}
     * @return {@link WebElement}
     */
	public WebElement waitExplicitlyForClickable(String xPath) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}

	/***
	 * Load the page..
	 */
	public void waitForLoad() {
		for (int i = 0; i < 20; i++) {
			pauseInSeconds(1);
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")
					|| js.executeScript("return document.readyState").equals("loaded")) {
				consoleInfoLog("Page on ready state...");
				break;
			}
			consoleInfoLog("Waiting for page ready state...");
		}
	}
	/***
	 * Wait for invisibility of element
	 * @param element {@value String}
	 * @return Boolean {@value Boolean}
	 */
	public Boolean waitForInvisibilityOfElement(String element) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
	}
    /**
     * Wait for Visibility
     * @param xpathExpression {@value String}
     * @return Boolean {@value Boolean}
     */
	public WebElement waitForVisibilityOfElementLocated(String xpathExpression) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
	}

	/***
	 * @param xpath {@link String}
	 * @return {@link WebElement}
	 */
	public WebElement findElement2ByXpath(String xpath) { // NO_UCD (use private)
		try {
			highlightElement(xpath);
			consoleInfoLog("Finding object xpath=> "+xpath);
			return driver.findElement(By.xpath(xpath));
		} catch (Exception t) {
			throw new STOSException("Object having Xpath as " + xpath + " not found.");
		}
	}

	/***
	 * Fluent weight will wait for specific second to find the element
	 * @param xpath {@link String}
	 * @return {@link WebElement}
	 */
	public WebElement findElementByXpath(String xpath) {
		WebElement clickLink = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				consoleInfoLog("Finding element xpath => "+xpath);
				return driver.findElement(By.xpath(xpath));
			}
		});
		return clickLink;
	}

	/***
	 * Fluent weight will wait for specific second to find the element
	 * @param xpath {@link String}
	 * @return {@link List}
	 */
	public List<WebElement> findElementsByXpath(String xpath) {
		return wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(final WebDriver driver) {
				consoleInfoLog("Finding element xpath => "+xpath);
				return driver.findElements(By.xpath(xpath));
			}
		});

	}
	

	/***
	 * Will check the element visibility
	 * @param xpath {@link String}
	 * @param messageToDisplay {@link String}
	 */
	public void validateDisplayedElement(String xpath, String messageToDisplay) {
		try {
			WebElement ele = waitForVisibilityOfElementLocated(xpath);
			if (isExists(ele)) {// once again do verification
				highlightElement(xpath);
				captureScreenShot(messageToDisplay + " is displayed");
				//commonPassLog(messageToDisplay + " is displayed");
			}
		} catch (Exception e) {
			commonFailWithScreenShotLog("Element not found ["+xpath+"] : "+e.getMessage());
			// throw(e);
		}
	}

	/**
	 *  Will check the element invisibility
	 * @param xpath {@link String}
	 * @param messageToDisplay {@link String}
	 */
	public void validateNotDisplayedElement(String xpath, String messageToDisplay) {
		try {
			if (!isExists(xpath)) {
				commonPassLog(messageToDisplay + " is not displayed");
			} else {
				highlightElement(xpath);
				commonFailWithScreenShotLog(messageToDisplay + " is displayed");
			}
		} catch (Exception e) {
			commonFailWithScreenShotLog("Element not found ["+xpath+"] : "+e.getMessage());
		}
	}

	// Handling alert to either accept or dismiss
	public boolean handleAlert(boolean acceptOrDismiss) {
		Alert alert = driver.switchTo().alert();
		if (acceptOrDismiss) {
			alert.accept();
			return true;
		}
		alert.dismiss();
		return false;
	}

	/***
	 * Only Pass buttonname
	 * @param buttonName  {@link String}
	 */
	public void clickButton(String buttonName) {
		if (isExists(buttonName)) {
			final String element = "//button[text()='" + buttonName + "']";
			highlightElement(element);
			findElementByXpath(element).click();
			commonInfoLog(buttonName + " is clicked");
		}
	}

	/***
	 * @param Xpath {@link String}
	 * @param msg {@link String}
	 */
	public void clickElement(String xPath, String msg) {
		highlightElement(xPath);
		waitExplicitlyForClickable(xPath).click();
		if (msg != null) {
			commonInfoLog(msg);
		}
	}
	// Check element present or not
	public boolean isExists(String xpath) {
		try {
			consoleInfoLog("Element existance finding-"+xpath);
			return driver.findElement(By.xpath(xpath)).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	// Check element present or not
	public boolean isExists(WebElement element) {
		try {
			// return element.isDisplayed();
			boolean foo = wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					consoleInfoLog("Element existance finding");
					return element.isDisplayed();
				}
			});
			return foo;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// Back button in any page
	public void back() {
		findElementByXpath("//a[@href='javascript:history.go(-1);']").click();
	}
    /**
     * Back through Browser back key
     */
	public void goBack() {
		driver.navigate().back();
	}
    /**
     * @param frameId {@link String}
     */
	public void changeFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}
	/**
	 * @param xPath {@link String}
	 * @return {@link String}
	 */
	public String getText(String xPath) {
		highlightElement(xPath);
		return findElementByXpath(xPath).getText();
	}
	
	/***
	 * Some time read value not getting through gettext then value will get with attribute
	 * @param xPath {@link String}
	 * @return {@link String}
	 */
	public String getValue(String xPath) {
		highlightElement(xPath);
		return findElement2ByXpath(xPath).getAttribute("value");
	}

	/***
	 * @param table {@link WebElement}
	 */
	public void uncheckAllCheckboxes(WebElement table) {
		// Find all the input tags inside the mainTable and save it to a list
		List<WebElement> checkBoxes = table.findElements(By.xpath("//input[@type='checkbox']"));
		// iterate through the list of check boxes and if checked, uncheck them
		for (WebElement checkbox : checkBoxes) {
			if (checkbox.isSelected()) {
				this.jsClick(checkbox);
			}
		}
	}
    //Js clicked is use full sometime when selenium function do not work
	public void jsClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
		pauseInSeconds(1);
	}

	public void checkAllCheckboxes(WebElement table) {
		// Find all the input tags inside the mainTable and save it to a list
		List<WebElement> checkBoxes = table.findElements(By.xpath("//input[@type='checkbox']"));
		// iterate through the list of checkboxes and if uncheck , checked them
		for (WebElement checkbox : checkBoxes) {
			if (!checkbox.isSelected()) {
				this.jsClick(checkbox);
			}
		}
	}
   
	public void zoomIn() {
		// To zoom In page 4 time using CTRL and + keys.
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}
	public void zoomOut() {
		// To zoom out page 4 time using CTRL and - keys.
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}
	public void zoomdefault() {
		// To set browser to default zoom level 100%
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}

	/***
	 * This is Generic method which set the value in Text Box
	 * @param property {@link String}
	 * @param value  {@link String}
	 */
	public void setText(String property, String value) {
		waitForLoad();
		pauseInSeconds(1);
		//findElement2ByXpath(property).sendKeys("");
		findElement2ByXpath(property).clear();
		pauseInSeconds(2);
		findElement2ByXpath(property).sendKeys(value);
		pauseInSeconds(1);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		pauseInSeconds(1);
	}

	// Creating a custom function
	public void highlightElement(String element) {
		pauseInSeconds(1);
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow;color:black; border: 2px solid red;');",
				findElementByXpath(element));
		pauseInSeconds(1);
	}

	/***
	 * Generic pass for all
	 */
	public void iMDone() {
		try {
			commonInfoWithScreenShotLog("All steps completed successfully..");
			jsScrollTop();
			js.executeScript(
					"arguments[0].setAttribute('style', 'color:yellow;font-size:28px;border:2px solid yellow;text-shadow: 2px 2px 4px #000;');",
					driver.findElement(By.xpath(getProperty("ecom.our.exculsive"))));
			pauseInMiliSeconds(3);
			js.executeScript("var ele=arguments[0]; ele.innerHTML = 'Successfully Completed TC';",
					driver.findElement(By.xpath(getProperty("ecom.our.exculsive"))));
			pauseInSeconds(1);
		} catch (Exception e) {
		}
	}
	
	public void ccaDone() {
		try {
			commonInfoWithScreenShotLog("All steps completed successfully..");
			jsScrollTop();
			js.executeScript(
					"arguments[0].setAttribute('style', 'color:yellow;font-size:28px;border:2px solid yellow;text-shadow: 2px 2px 4px #000;');",
					driver.findElement(By.xpath(getProperty("ecom.cca.technical"))));
			pauseInMiliSeconds(3);
			js.executeScript("var ele=arguments[0]; ele.innerHTML = 'Successfully Completed TC';",
					driver.findElement(By.xpath(getProperty("ecom.cca.technical"))));
			pauseInSeconds(1);
		} catch (Exception e) {
		}
	}
	/**
	 * Generic Fail for all
	 */
	public void iMFail() {
		try {
			commonInfoLog("TC failed. Check Report(s)");
			jsScrollTop();
			js.executeScript(
					"arguments[0].setAttribute('style', 'color:red;font-size:28px;border:2px solid yellow;text-shadow: 2px 2px 4px #000;');",
					driver.findElement(By.xpath(getProperty("ecom.our.exculsive"))));
			pauseInMiliSeconds(3);
			js.executeScript("var ele=arguments[0]; ele.innerHTML = 'Test Cases Failed';",
					driver.findElement(By.xpath(getProperty("ecom.our.exculsive"))));
			pauseInSeconds(1);
		} catch (Exception e) {
		}
	}

	// Refresh current page
	public void refreshPage() {
		driver.navigate().refresh();
	}

	// Select drop down option. Gets option name and option to be selected
	public boolean selectOption(String optionName, String optionToBeSelected, String type) {
		int count = 0;
		Select select = null;
		if("id".equalsIgnoreCase(type)) {
			select=new Select(driver.findElement(By.id(optionName)));
		}else {
			select=new Select(driver.findElement(By.className(optionName)));

		}

		List<WebElement> options = select.getOptions();
		if (options.size() > 0) {
			for (WebElement option : options) {
				if (option.getText().equals(optionToBeSelected)) {
					option.click();
					return true;
				} else {
					count++;
				}
			}
			if (count == options.size()) {
				commonInfoLog(optionToBeSelected + " not available to select.");
				return false;
			}
		} else {
			commonInfoLog("No options available to select.");

		}
		return false;
	}

	/**
	 * Get The count of options
	 * @param optionName {@link String}
	 * @return {@link Integer}
	 */
	public int getOptionSize(String optionName) {
		Select select = new Select(driver.findElement(By.name(optionName)));
		return select.getOptions().size();
	}

	/***
	 * 
	 * @param element
	 */
	public void actionMoveJSClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
		Actions act =  getAction();
		act.moveToElement(element);
		act.build().perform();

	}

	/***
	 * This method hover and click
	 */
	public void moveToIconAndClick(String icon, String link) {
		Actions action =  getAction();
		action.moveToElement(findElementByXpath(icon))
		.click(findElementByXpath(link))
		.build().perform();
	}
	/***
	 * This method hover 
	 * @param element {@link WebElement}
	 */
	public void actionMoveToElement(WebElement element) {
		Actions act = getAction();
		act.moveToElement(element);
		act.build().perform();
	}

	/***
	 * js scroll down 500px 
	 */
	public void jsScrollWindowDown() {
		js.executeScript("window.scrollBy(0,500)", "");
	}

	/***
	 * up and down on the bases of - and +
	 * @param no {@link Integer}
	 */
	public void jsScrollWindow(int no) {
		js.executeScript("window.scrollBy(0," + no + ")", "");
	}
     /***
      * Scroll Top
      */
	public void jsScrollTop() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	/**
	 * Scroll down
	 */
	public void jsScrollDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	/***
	 * Scroll windows up only 500 px
	 */
	public void jsScrollWindowUp() {
		js.executeScript("window.scrollBy(0,-500)", "");
	}

	/**
	 * Scroll view with xpath
	 * @param xPath {@link String}
	 */
	public void scrollIntoViewJS(String xPath) {
		WebElement element = findElementByXpath(xPath);
		pauseInSeconds(1);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		pauseInSeconds(1);
	}

	/***
	 * Scroll view with xpath particular location
	 * @param xPath {@link String}
	 * @param no {@link Integer}
	 */
	public void scrollIntoViewJS(String xPath, int no) {
		WebElement element = findElementByXpath(xPath);
		pauseInSeconds(1);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		jsScrollWindow(no);
	}

	/***
	 * Will check element is enable or disable
	 * @param xpath {@link String}
	 * @param msg {@link String}
	 * @return {@link Boolean}
	 */
	public boolean isElementDisabled(String xpath, String msg) {
		String isDisabled = waitForVisibilityOfElementLocated(xpath) != null
				? waitForVisibilityOfElementLocated(xpath).getAttribute("disabled")
				: null;
		if (isDisabled != null && isDisabled.equals("true")) {
			commonInfoLog( msg + " element is disabled");
			return false;
		} else {
			commonInfoLog( msg + " element is not disabled");
			return true;
		}
	}

	/***
	 * Get element size i.e table
	 * @param xPath {@link String}
	 * @return {@link Integer}
	 */
	public int getElementsSize(String xPath) {
		waitForLoad();
		return findElementsByXpath(xPath).size();
	}
	/**
	 * @param url  {@link String}
	 * @return {@link String}
	 */
	public String openURLinNewTab(String url) { 
		String childHandler = null;
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			commonInfoLog("Switching to desire window");
			driver.switchTo().window(handle); // Switch to the desired window first and then execute commands using
			pauseInSeconds(2);
			driver.get(url);
			childHandler = handle;
		}
		return childHandler;
	}
	
	/***
	 * This is generic sign out
	 */
	public void signOut() {
		try {
			pauseInSeconds(1);
			if(isExists(getProperty("ecom.signout.text"))) {
			findElement2ByXpath(getProperty("ecom.signout.text")).click();
			commonInfoLog("Logout clicked");
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.popup"))) {
				commonInfoLog("Continue popup clicked");
				clickButton(getProperty("ecom.continue.text"));
			}
			}else {
				consoleInfoLog("Logout not found");
			}
		} catch (Exception e) {
			consoleInfoLog("Logout problem {} "+e.getMessage());
		}
	}
	
	/***
	 * CCA Signout
	 */
	public void signOutCCA() {
		try {
			if(isExists(getProperty("ecom.signout.text"))) {
				pauseInSeconds(1);
				clickElement(getProperty("ecom.signout.text"), "CCA Logout clicked");
				pauseInSeconds(1);
			}
		} catch (Exception e) {
			
		}
	}
	/***
	 * Common Error for all
	 * @param ex {@link Throwable}
	 */
	public void commonErrorUpdate(Throwable ex) {
		CommonDto dto = getTCService().findByTCName(AppUtil.TEST_NAME);// Required tc id as todo
		cdto.setTcId(dto.getTcId());
       if(ex instanceof  SkipException) {
    	    // updateTC(cdto);
    	     throw new SkipException("Skipped......");
       }else {
			iMFail();
			consoleErrorLog(ex.toString());
			cdto.setReasonFail(AppUtil.errorText(ex.getMessage()));
			cdto.setRunStatus(AppUtil.FAILED);
			Assert.assertTrue(false, ex.getMessage());
       }
	}
	/**
	 * @param testCase {@link String}
	 * @param testDesc {@link String}
	 */
	public void startTestWithDescription(String testCase,String testDesc,String testScenarios) {
		logger = report.startTest(testScenarios,testDesc);
		cdto=initAndSaveTC(testCase, cdto);
		cdto.setCustomerId(findData("CustomerNo"));
		cdto.setDepartmentId(findData("DepartmentNo"));
	}
	
	/***
	 * @param testSenarios {@link String}
	 * @param testDesc {@link String}
	 */
	public void skipTestCase(String testSenarios,String testDesc) {
		logger = report.startTest(testSenarios,testDesc);
		cdto.setRunStatus(AppUtil.SKIPPED);
		cdto=initAndSaveTC(AppUtil.TEST_NAME, cdto);
		ThreadLocalUtil.get().siteRunningStatus="";// ForSITE RUnning status
		commonSkipLog(testSenarios + " Scenario Skipped, Please validate test class [ "+AppUtil.TEST_NAME+" ] or StosData sheet");
	    throw new SkipException(testSenarios + " Scenario Skipped");
	}
	
	/***
	 * @param row {@link Integer}
	 * @return {@link String}
	 */
	public String selectRowAndReturnSenarios(int row) {
		dataUtils.setSheetName(AppUtil.DATE_SHEET_NAME);
		dataUtils.getRowData(row);
		String  testSenarios = findData("ScenarioDescription");
		consoleInfoLog("Sheet name initalizing::"+AppUtil.DATE_SHEET_NAME);
		consoleInfoLog("Row no::"+row);
		consoleInfoLog("Test Scenario ::"+testSenarios);
		return testSenarios;
	}
	
	
}
