package com.hcl.usf.pages;

/**
 * 
 * @author intakhabalam.s@hcl.com user story-14 (4.5)
 *
 */
public class EndUSFSssionUserTransfersCartPage extends CommonPageElement {

	/***
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validateUsfSessionCheckFromBirchStreet(String customerId, String departmentId) {
		// Login and search Customer
		commonInfoLog("Birch Street TC running");
		loginBirchStreet(findData("URL"), findData("UserName"), findData("UserPass"));
		// WebDriver driver2=switchBrowserTab(driver,0);
		pauseInSeconds(1);
		String parentURL = driver.getCurrentUrl();
		pauseInSeconds(3);
		waitForLoad();
		openNewBrowserTab(parentURL);
		pauseInSeconds(2);
		switchBrowserTab(driver, 2);
		pauseInSeconds(2);
		commonInfoLog("URL to open " + parentURL);
		driver.navigate().to(parentURL);
		waitForLoad();
		findCustomer(customerId, departmentId);
		pauseInSeconds(4);
		searchList(findData("ListName"));
		int size = filterProduct(findData("ProductName"));
		pauseInSeconds(2);
		for (int i = 0; i < size;) {
			String textBoxPath = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
			boolean b=putValueInQuantityBox(textBoxPath, 1);
			if(!b) {
				commonFailLog("Text box is disabled, You may validate data setup");
				return;
			}
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			break;
		}
		waitForLoad();
		clickReviewAndSubmitOrder();
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
			pauseInSeconds(1);

		}
		captureScreenShot("Page Screenshot");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.birch.street.transfer"), "clicked transfer button");
		pauseInSeconds(2);
		// Third Tab has been closed automatically so changing to tab 2
		switchBrowserTab(driver, 1);
		pauseInSeconds(1);
		refreshPage();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.signin"), "Submit button");
		commonInfoLog("Done....");
	}
	/***
	 * 
	 * @param customerId  {@link String}
	 * @param departmentId  {@link String}
	 */
	public void validateUsfSessionCheckFromBrookdale(String customerId, String departmentId) {
		commonInfoLog("Brookdale TC running");
        loginBrookDyale(findData("URL"), findData("UserName"), findData("UserPass"));
        pauseInSeconds(1);
		String parentURL = driver.getCurrentUrl();
		pauseInSeconds(3);
		waitForLoad();
		openNewBrowserTab(parentURL);
		pauseInSeconds(2);
		switchBrowserTab(driver, 1);
		pauseInSeconds(2);
		commonInfoLog("URL to open " + parentURL);
		driver.navigate().to(parentURL);
		waitForLoad();
		findCustomer(customerId, departmentId);
		pauseInSeconds(4);
		searchList(findData("ListName"));
		pauseInSeconds(2);
		int size = filterProduct(findData("ProductName"));
		for (int i = 0; i < size;) {
			String textBoxPath = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
			putValueInQuantityBox(textBoxPath, 1);
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			break;
		}
		waitForLoad();
		waitExplicitlyForClickable(getProperty("ecom.review.order")).click();
		commonInfoLog("Review Order button clicked");
		waitExplicitlyForClickable(getProperty("ecom.reserve.stock")).click();
		commonInfoLog("Reserve Stock button clicked");
		pauseInSeconds(5);
		captureScreenShot("Page Screenshot");
		pauseInSeconds(1);
		switchBrowserTab(driver, 0);
		pauseInSeconds(1);
		refreshPage();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.signin"), "Submit button");
		commonInfoLog("Done....");
	}
    /***
     * 
     * @param customerId {@link String}
     * @param departmentId  {@link String}
     */
	public void validateUsfSessionCheckFromIBuyEfficent(String customerId, String departmentId) {
		commonInfoLog("IBuy Efficent TC running");
        loginByEfficent(findData("URL"), findData("UserName"), findData("UserPass"));
        pauseInSeconds(1);
		String parentURL = driver.getCurrentUrl();
		pauseInSeconds(3);
		waitForLoad();
		openNewBrowserTab(parentURL);
		pauseInSeconds(1);
		switchBrowserTab(driver, 1);
		pauseInSeconds(2);
		commonInfoLog("URL to open " + parentURL);
		driver.navigate().to(parentURL);
		waitForLoad();
		pauseInSeconds(1);
		changeFrame("punchout-frame");// iframePreLoad
		findCustomer(customerId, departmentId);
		pauseInSeconds(4);
		searchList(findData("ListName"));
		int size = filterProduct(findData("ProductName"));
		for (int i = 0; i < size;) {
			String textBoxPath = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
			scrollIntoViewJS(textBoxPath, -90);// It will come middle of screen
			putValueInQuantityBox(textBoxPath, 1);
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			break;
		}
		waitForLoad();
		clickReviewAndSubmitOrder();
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
			pauseInSeconds(1);

		}
		captureScreenShot("Page Screenshot");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.birch.street.transfer"), "clicked transfer button");
		// Third Tab has been closed automatically so changing to tab 2
		pauseInSeconds(10);
		switchBrowserTab(driver, 0);
		pauseInSeconds(1);
		changeFrame("punchout-frame");
		refreshPage();
		pauseInSeconds(1);
		changeFrame("punchout-frame");// iframePreLoad
		pauseInSeconds(1);
		jsScrollWindowDown();
		validateDisplayedElement(getProperty("ecom.signin"), "Submit button");
		commonInfoLog("Done....");

	}

}
