package com.hcl.usf.pages;

import org.openqa.selenium.WebElement;

/**
 * 
 */
public class BlueprintBusinessSolutionsPage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void validateBlueprintBusinessSolutions(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		WebElement elem = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		pauseInSeconds(1);
		commonInfoLog("Clicked Bussiness exculsive link");
		elem.click();
		pauseInSeconds(2);
		scrollIntoViewJS(getProperty("ecom.bussiness.text"), -150);
		pauseInSeconds(1);
		captureScreenShot("Bussiness Solution section screenshot");
		String moreLink = getText(getProperty("ecom.learn.more.text"));
		if (moreLink.contains("Learn More")) {
			commonPassLog("At Business Solution section Learn More Text is displayed");
		} else {
			commonFailLog("At Business Solution section Learn More Text is not displayed");
		}
		pauseInSeconds(1);
		waitExplicitlyForClickable(getProperty("ecom.learn.more.text")).click();
		pauseInSeconds(6);
		String bussinessUrl = driver.getCurrentUrl();
		if (bussinessUrl.contains(dataUtils.getData("URL1").trim())) {
			commonPassLog("Bussiness Solution URL matched");
		} else {
			commonFailLog("Bussiness Solution URL does not matched");
		}
		captureScreenShot("Page Screenshot");
		driver.navigate().back();
		pauseInSeconds(2);
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.our.exculsive.healthcare"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.healthcare.text"), "Healthcare Menus & Recipes");
		pauseInSeconds(1);
		
		String moreLink2 = getText(getProperty("ecom.learn.more.span"));
		if (moreLink2.contains("Learn More")) {
			commonPassLog("At Business Solution section Learn More Text is displayed");
		} else {
			commonFailLog("At Business Solution section Learn More Text is not displayed");
		}
		pauseInSeconds(1);
		String printLink = getText(getProperty("ecom.blue.print.link"));
		if (printLink.contains("Login to BluePrint System")) {
			commonPassLog("Text Login to BluePrint System is displayed after Learn More link");
		} else {
			commonFailLog("Text Login to BluePrint System is not displayed after Learn More link");
		}

		actionMoveJSClick(findElementByXpath(getProperty("ecom.blue.print.link")));
		pauseInSeconds(2);
		String bussSolURL = "";
		String parentWindow = driver.getWindowHandle();
		for (String allWindowsIds : driver.getWindowHandles()) {
			if (!parentWindow.equalsIgnoreCase(allWindowsIds)) {
				driver.switchTo().window(allWindowsIds);
				pauseInSeconds(5);
				bussSolURL = driver.getCurrentUrl();
				captureScreenShot("Page Screenshot");
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}
		pauseInSeconds(2);
		waitForLoad();
		if (bussSolURL.contains(dataUtils.getData("URL2").trim())) {
			commonPassLog("Blue print bussiness solution URL matched");
		} else {
			commonFailLog("Blue print bussiness solution URL does not matched");
		}
		iMDone();
		pauseInSeconds(1);
	}

}
