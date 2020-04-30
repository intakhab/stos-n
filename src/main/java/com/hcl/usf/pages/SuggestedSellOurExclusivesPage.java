package com.hcl.usf.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellOurExclusivesPage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void validateAccessSuggestedProductUpdates(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		validateExclusiveDropDownLink();
		pauseInSeconds(2);
		validateProductAtSuggestedUpdatePage(findData("ProductName"));
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");

		jsScrollWindowUp();
		iMDone();
	}

	/***
	 * 
	 */
	private void validateExclusiveDropDownLink() {
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		pauseInSeconds(1);
		actionMoveToElement(element);
		if (isExists(getProperty("ecom.suggested.update"))) {
			validateDisplayedElement(getProperty("ecom.suggested.update"), "Suggested Products Updates Link");
			commonInfoLog("Clicked exculsive link");
			element.click();
			pauseInSeconds(2);
			scrollIntoViewJS(getProperty("ecom.suggested.product.updates.exclusive"), -200);
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates.exclusive"),
					"Suggested Product Updates Text");
			pauseInSeconds(1);
			String txt = getText(getProperty("ecom.suggested.product.updates.exclusive.text"));
			if (txt.contains("Suggested Products can help you find products that support your purchasing goals")) {
				commonInfoLog(txt);
			} else {
				commonInfoLog("Exclusive text does not matched");
			}

			validateDisplayedElement(getProperty("ecom.access.suggested.prod"), "Access Suggested product link");
			clickElement(getProperty("ecom.access.suggested.prod"), "Clicked Access Suggested product link");
			pauseInMiliSeconds(2);
		} else {
			commonInfoLog("Customer having not Suggested Products");
		}

	}

	public void downloadCSVFromMySavingPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(2);
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		pauseInSeconds(1);
		actionMoveToElement(element);
		clickElement(getProperty("ecom.my.saving.link"), "Clicked my saving page");
		
		String txt=getText(getProperty("ecom.my.saving.estimate.text"));
		if(txt.contains("The total estimated annual savings relates to the conversions you accepted during the date range below.")) {
			validateDisplayedElement(getProperty("ecom.my.saving.estimate.text"), txt);
		}else {
			commonFailLog("Text validation", txt);
		}
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.reset"), "Reset button");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.hr"), "HR line");
		pauseInSeconds(1);
		String date1 = getValue(getProperty("ecom.my.saving.date1"));
		commonInfoLog("Date from "+date1);
		String date2 = getValue(getProperty("ecom.my.saving.date2"));
		commonInfoLog("Date to "+date2);
		validateDisplayedElement(getProperty("ecom.display.sugg.sell.text"), "Suggested Products");
		clickElement(getProperty("ecom.my.saving.download.csv"), "Download my saving CSV");
		pauseInSeconds(2);
		String downloadFileName = getValue(getProperty("ecom.my.saving.reports.name"));
		pauseInSeconds(1);
		clickElement(getProperty("ecom.download.button"), "Clicked donwload button");
		pauseInSeconds(2);
		String fileName = USER_DIR + "\\download\\"+ downloadFileName + ".csv";;
		List<List<String>> headList = AppUtil.readFilCSV(fileName);
		for(List<String> ss:headList) {
			for (String s : ss) {
				commonInfoLog(s);
			}
		}
	      try {
			Files.delete(Paths.get(fileName).getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Validation required
		iMDone();

	}

	//User story EC45-07
	public void validateAccessMySavingLink(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(2);
		clickMySavingAndValidate(); //clickMySavingAndValidate
		pauseInSeconds(1);
		actionMoveToElement(waitExplicitlyForClickable(getProperty("ecom.our.exculsive")));
		clickElement(getProperty("ecom.suggested.update"), "Clicked suggested product updates link");
		pauseInSeconds(1);
		String productName=findData("NProductName");
		validateProductAtSuggestedUpdatePage(productName);
		pauseInSeconds(1);
		
		String quantityXpath=getProperty("ecom.suggested.product.qnty.text").replaceAll(AppUtil.DELIMINATOR, productName);
		scrollIntoViewJS(quantityXpath, -140);
		validateDisplayedElement(quantityXpath, "Quantity box");
		pauseInSeconds(1);
		setText(quantityXpath, "1");
		pauseInSeconds(2);
		checkOrderPopUp();//Popup modification
		pauseInSeconds(3);
    	clickElement(getProperty("ecom.pdp.great.radio.button").replaceAll(AppUtil.DELIMINATOR, productName), "Clicked Great! Switch to Suggested Product.");
    	pauseInSeconds(2);
    	clickElement(getProperty("ecom.update"),"Clicked Update button");
    	pauseInSeconds(5);
    	clickMySavingAndValidate();//clickMySavingAndValidate
    	pauseInSeconds(2);
    	callLink(getProperty("ecom.recommendations.link"),"Recommendations");
    	pauseInSeconds(1);
    	callLink(getProperty("ecom.mykitchen.link1"),"My Kitchen");
    	pauseInSeconds(1);
    	iMDone();
	}
      
	private void callLink(String xpath, String type) {
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		pauseInSeconds(1);
		actionMoveToElement(element);
		clickElement(xpath, "Clicked "+type);
		jsScrollWindowDown();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.saving.link4.page"), "My saving link");
		pauseInSeconds(2);
		jsScrollTop();
	}
	/***
	 * Common Validation
	 */
	private void clickMySavingAndValidate() {
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		pauseInSeconds(1);
		actionMoveToElement(element);
		clickElement(getProperty("ecom.my.saving.link"), "Clicked my saving page");
		waitForLoad();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.saving.text"), "My Saving text");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.saving.total.estimation"), "Estimation total");
		pauseInSeconds(1);
		String date1 = getValue(getProperty("ecom.my.saving.date1"));
		commonInfoLog("Date from "+date1);
		pauseInSeconds(1);
		String date2 = getValue(getProperty("ecom.my.saving.date2"));
		commonInfoLog("Date to "+date2);
		if(isExists(getProperty("ecom.my.saving.product.heading"))) {
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.my.saving.product.subheading"), "Saving data");
		}else {
			commonInfoLog("No Saving data exist");
			captureScreenShot("Page Screenshot");

		}
	}
}
