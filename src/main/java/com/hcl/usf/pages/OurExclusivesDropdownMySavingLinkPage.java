package com.hcl.usf.pages;

import org.openqa.selenium.WebElement;

/**
 * @author intakhabalam.s@hcl.com
 *
 */
public class OurExclusivesDropdownMySavingLinkPage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void valdiatemySavingLinText(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		String mySaving = getProperty("ecom.my.saving.link");
		// validatingTC
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));

		String suggestedProdUpdate = mySaving + "/preceding::a[2]";

		String checkBussinessTool = mySaving + "/following::a[2]";

		actionMoveToElement(ourExculsive);
		validateDisplayedElement(suggestedProdUpdate, "Before \"My Savings\" link \"Suggested Product Updates\" link");

		actionMoveToElement(ourExculsive);

		validateDisplayedElement(checkBussinessTool, "After \"My Savings\" link \"Check Bussiness Tools\" link");
		
		validateDisplayedElement(mySaving, "My Savings");

		ourExculsive.click();
		
		waitForLoad();
		
		scrollIntoViewJS(getProperty("ecom.my.saving.text"), -300);

		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.saving.text"), "My Savings header");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.saving.next.text"), "My Savings paragraph text");
		String text = getText(getProperty("ecom.my.saving.next.text"));
		if (text.contains("Track the conversions you've made to quality produ")) {
			validateDisplayedElement(getProperty("ecom.my.saving.next.text"), "" + text + "");
		}

		validateDisplayedElement(getProperty("ecom.access.my.saving.link"), "My Savings Acess My Saving link");
		waitExplicitlyForClickable(getProperty("ecom.access.my.saving.link")).click();
		validateDisplayedElement(getProperty("ecom.my.saving.text"),
				"Navigate to My Savings page where My Saving header");
		
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.scoop.link"));
		mySavingLink();
		pauseInMiliSeconds(2);
		validateDisplayedElement(getProperty("ecom.my.saving.text"),
				"Navigate to My Savings page from scoop page where My Saving header");

		waitForLoad();
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.recommendation.link"));
		mySavingLink();
		pauseInMiliSeconds(2);
		validateDisplayedElement(getProperty("ecom.my.saving.text"),
				"Navigate to My Savings page from Recommendation page where My Saving header");
		
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.healthcare.link"));
		mySavingLink();
		pauseInMiliSeconds(2);
		validateDisplayedElement(getProperty("ecom.my.saving.text"),
				"Navigate to My Savings page from Healthcare page where My Saving header");
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.mykitchen.link"));
		mySavingLink();
    	pauseInMiliSeconds(2);
		validateDisplayedElement(getProperty("ecom.my.saving.text"),
				"Navigate to My Savings page from My Kitchen page where My Saving header");
		 iMDone();

	}

	private void mySavingLink() {
		jsScrollWindowDown();
        pauseInSeconds(2);
        boolean isClickFirst=false;
        if (isExists(getProperty("ecom.my.saving.link4.page"))) {
        	highlightElement(getProperty("ecom.my.saving.link4.page"));
        	pauseInMiliSeconds(1);
        	consoleInfoLog("Clicked saving link1");
        	findElementByXpath(getProperty("ecom.my.saving.link4.page")).click();			
			isClickFirst=true;
		}
		if (!isClickFirst&& isExists(getProperty("ecom.my.saving.link5.page"))) {
			highlightElement(getProperty("ecom.my.saving.link5.page"));
        	pauseInMiliSeconds(1);
        	consoleInfoLog("Clicked saving link2");
        	findElementByXpath(getProperty("ecom.my.saving.link5.page")).click();	
		}
		captureScreenShot("Page Screenshot");
        pauseInSeconds(2);
	}

}
