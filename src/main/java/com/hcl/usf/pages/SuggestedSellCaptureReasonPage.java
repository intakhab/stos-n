package com.hcl.usf.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.hcl.usf.util.AppUtil;

/**
 * 
 * @author intakhabalam.s@hcl.com 
 * 
 */
public class SuggestedSellCaptureReasonPage extends CommonPageElement {

	/***
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void validateCaptureReasonPage(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.suggested.update"))) {
			commonInfoLog("Customer having Suggested Products");
			clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.suggested.page.thanks.text"),
					"Thanks. I'll keep this Text found");
			clickElement(getProperty("ecom.suggested.page.thanks.checkbox"), "Clicked Thanks Check Box");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.update"), "Update Button");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.page.popup.text"), "We Would Like Your Feedback Text");
			String[] checkboxArryas = { "Cost", "Quality", "Pack size", "Flavor profile",
					"Not nutritionally equivalent", "Conflicts with current menu or menu cycle",
					"Conflicts with brand offerings", "Conflicts with rebated / contracted products", "Other" };
			int i = 1;
			pauseInSeconds(1);
			for (String chkBoxText : checkboxArryas) {
				String prop = getProperty("ecom.suggested.page.popup.chkbox.text").replaceAll(AppUtil.DELIMINATOR,
						chkBoxText);
				validateDisplayedElement(prop, chkBoxText);
				pauseInSeconds(1);
				String xPathCheckBox = deletePropertyDeliminator(getProperty("ecom.suggested.page.popup.checkbox"), i);
				clickElement(xPathCheckBox, "Clicked Check Box " + chkBoxText);
				pauseInSeconds(1);
				i++;
			}
			validateDisplayedElement(getProperty("ecom.suggested.page.popup.close"), "Close icon");
			pauseInSeconds(1);
			// clickElement(getProperty("ecom.suggested.page.popup.close"), "Clicked close
			// icon"); As part of EC45-16
			clickElement(getProperty("ecom.submit"), "Clicked submit button");
			pauseInSeconds(1);
			jsScrollWindowUp();
			pauseInSeconds(1);
			clickElement(getProperty("ecom.update"), "Clicked update button");
			waitForLoad();//
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.your.shopping.text"),
					"Your shopping lists have been updated with the Suggested Products selected");
			pauseInSeconds(2);
		} else {
			commonInfoLog("Customer does not having Suggested Products");
		}
		iMDone();
		pauseInSeconds(1);
	}

	/***
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void validatesuggestedreasonandsubmitwithreason(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.suggested.update"))) {
			commonInfoLog("Customer having Suggested Products");
			clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.suggested.page.thanks.text"),
					"Thanks. I'll keep this Text found");
			clickElement(getProperty("ecom.suggested.page.thanks.checkbox"), "Clicked Thanks Check Box");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.update"), "Update Button");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.page.prod1"), "Submit Button");
			pauseInSeconds(1);
			// String product1 = getText(getProperty("ecom.suggested.page.prod1"));
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.page.popup.text"), "We Would Like Your Feedback Text");
			String[] checkboxArryas = { "Cost", "Quality", "Pack size", "Flavor profile",
					"Not nutritionally equivalent", "Conflicts with current menu or menu cycle",
					"Conflicts with brand offerings", "Conflicts with rebated / contracted products", "Other" };
			int i = 1;
			pauseInSeconds(1);
			for (String chkBoxText : checkboxArryas) {
				String prop = getProperty("ecom.suggested.page.popup.chkbox.text").replaceAll(AppUtil.DELIMINATOR,
						chkBoxText);
				validateDisplayedElement(prop, chkBoxText);
				pauseInSeconds(1);
				String xPathCheckBox = deletePropertyDeliminator(getProperty("ecom.suggested.page.popup.checkbox"), i);
				clickElement(xPathCheckBox, "Clicked Check Box " + chkBoxText);
				pauseInSeconds(1);
				i++;
			}

			validateDisplayedElement(getProperty("ecom.suggested.page.Packsizereason"), "Pach size checkbox");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.suggested.page.Packsizereason"), "Clicked Pach size checkbox");
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.suggested.page.popup.Submit"), "Submit Button");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.suggested.page.popup.Submit"), "Clicked Submit button");
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.suggested.page.Update"), "Update Button");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.suggested.page.Update"), "Clicked Update Button");
			pauseInSeconds(1);
		} else {
			commonInfoLog("Customer does not having Suggested Products");
		}
		iMDone();
		pauseInSeconds(1);
	}

	public void validatesuggestedAcceptreasonandsubmitwithreason(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.suggested.update"))) {
			commonInfoLog("Customer having Suggested Products");
			clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.suggested.page.Accept.text"),
					"Great! Switch to Suggested Product. Text found");
			clickElement(getProperty("ecom.suggested.page.Accept.checkbox"), "Clicked Thanks Check Box");
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.suggested.page.prod1"), "Submit Button");
			pauseInSeconds(1);
			String product1 = getText(getProperty("ecom.suggested.page.prod1"));
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.update"), "Update Button");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.update"), "Clicked Update Button");
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.suggested.page.Accept.successmsg"))) {
				commonInfoLog("Suggested Accept Message is dispalyed");

			} else {
				commonInfoLog("Suggested Accept Message is not dispalyed");
			}

			// CCA Login
			ccaLogin();

			pauseInSeconds(2);
			// Navigate to SL conversion Upload page
			WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
			actionMoveToElement(element);
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			commonPassLog("Techinicl tab is displayed in the application");
			findElementByXpath(getProperty("ecom.cca.slconversion.reportlink")).click();
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.section.header"));
			isExists(getProperty("ecom.cca.slconversion.section.header"));
			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - search criteria page is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.header"));
			isExists(getProperty("ecom.cca.slconversion.page.header"));
			commonPassLog("SL conversion - search criteria page header is displayed");
			// validate the date filed are available on the SLconversion report page
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
			commonPassLog("SL conversion - search criteria page start date field is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.enddate"));
			commonPassLog("SL conversion - search criteria page End date field is displayed");

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			// Getting current date
			Calendar cal = Calendar.getInstance();
			// Displaying current date in the desired format
			System.out.println("Current Date: " + sdf.format(cal.getTime()));
			// Number of Days to add
			cal.add(Calendar.DAY_OF_MONTH, 365);
			// Date after adding the days to the current date
			String todayAsString = sdf.format(cal.getTime());
			// Displaying the new Date after addition of Days to current date
			System.out.println("Date after Addition: " + todayAsString);
			// changinf start date to current date
			findElementByXpath(getProperty("ecom.cca.slconversion.page.enddate")).clear();
			setText(getProperty("ecom.cca.slconversion.page.enddate"), todayAsString);
			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - search criteria page Start date changed to current date");
			// clickin on search btn from the SL conversion page
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.Search.btn"));
			findElementByXpath(getProperty("ecom.cca.slconversion.page.Search.btn")).click();

			// SL conversion page report Validation
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
			isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

			commonPassLog("SL conversion - Report page is displayed");

			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
			isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - Search report page is dispalayed");

			// SL conversion page cloumn header Validation
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.custaction"));
			isExists(getProperty("ecom.cca.slconversion.clmnheader.custaction"));

			commonPassLog("SL conversion - Search report page Column header cust auction is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));
			isExists(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));

			commonPassLog("SL conversion - Search report page Column header Refusal reason is displayed");
			///

		} else {
			commonInfoLog("Customer does not having Suggested Products");
		}
		iMDone();
		pauseInSeconds(1);
	}

	public void validatesuggestedAcceptreasonandsubmitwithreasonSL(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.suggested.update"))) {
			commonInfoLog("Customer having Suggested Products");
			clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.suggested.page.Accept.text"),
					"Great! Switch to Suggested Product. Text found");
			clickElement(getProperty("ecom.suggested.page.Accept.checkbox"), "Clicked Thanks Check Box");
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.suggested.page.prod1"), "Submit Button");
			pauseInSeconds(1);
			String product1 = getText(getProperty("ecom.suggested.page.prod1"));
			pauseInSeconds(1);

			validateDisplayedElement(getProperty("ecom.update"), "Update Button");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.update"), "Clicked Update Button");
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.suggested.page.Accept.successmsg"))) {
				commonInfoLog("Suggested Accept Message is dispalyed");

			} else {

				commonInfoLog("Suggested Accept Message is not dispalyed");

			}

			// CCA Login
			ccaLogin();

			pauseInSeconds(2);
			// Navigate to SL conversion Upload page
			WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
			actionMoveToElement(element);
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			commonPassLog("Techinicl tab is displayed in the application");
			findElementByXpath(getProperty("ecom.cca.slconversion.reportlink")).click();
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.section.header"));
			isExists(getProperty("ecom.cca.slconversion.section.header"));
			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - search criteria page is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.header"));
			isExists(getProperty("ecom.cca.slconversion.page.header"));
			commonPassLog("SL conversion - search criteria page header is displayed");
			// validate the date filed are available on the SLconversion report page
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
			commonPassLog("SL conversion - search criteria page start date field is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.enddate"));
			commonPassLog("SL conversion - search criteria page End date field is displayed");

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			// Getting current date
			Calendar cal = Calendar.getInstance();
			// Displaying current date in the desired format
			System.out.println("Current Date: " + sdf.format(cal.getTime()));

			// Number of Days to add
			cal.add(Calendar.DAY_OF_MONTH, 365);
			// Date after adding the days to the current date
			String todayAsString = sdf.format(cal.getTime());
			// Displaying the new Date after addition of Days to current date
			System.out.println("Date after Addition: " + todayAsString);

			// changinf start date to current date
			findElementByXpath(getProperty("ecom.cca.slconversion.page.enddate")).clear();
			setText(getProperty("ecom.cca.slconversion.page.enddate"), todayAsString);
			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - search criteria page Start date changed to current date");
			// clickin on search btn from the SL conversion page
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.Search.btn"));
			findElementByXpath(getProperty("ecom.cca.slconversion.page.Search.btn")).click();

			// SL conversion page report Validation
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
			isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

			commonPassLog("SL conversion - Report page is displayed");

			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
			isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

			captureScreenShot("Page Screenshot");
			commonPassLog("SL conversion - Search report page is dispalayed");

			// SL conversion page cloumn header Validation
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.custaction"));
			isExists(getProperty("ecom.cca.slconversion.clmnheader.custaction"));

			commonPassLog("SL conversion - Search report page Column header cust auction is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));
			isExists(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));

			commonPassLog("SL conversion - Search report page Column header Refusal reason is displayed");
			///

		} else {
			commonInfoLog("Customer does not having Suggested Products");
		}
		iMDone();
		pauseInSeconds(1);
	}

	public void validatesuggestedAcceptreasonandsubmitwithreasonReview(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		searchList(findData("ListName"));
		waitForLoad();

		int size = filterProduct(findData("ProductName"));
		pauseInSeconds(2);

		// Enter Quantity for the products
		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(2);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		pauseInSeconds(1);
		// Select New order radio button
		clickElement(getProperty("ecom.new.order.radio.button"), "Clicked Create New Order radio button");
		pauseInSeconds(3);

		// Select Continue button
		clickElement(getProperty("ecom.continue.button"), "Clicked Continue button");
		pauseInSeconds(3);

		// Select Review Order button
		clickElement(getProperty("ecom.review.order"), "Review order button");
		pauseInSeconds(2);

		if (isExists(getProperty("ecom.review.order.title"))) {
			commonPassLog("Review order page is dispalyed ");
		} else {
			commonFailLog("Review order page is Not dispalyed ");
		}

		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
		// Validate Suggested product
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
		// Validate pack size box
		pauseInSeconds(1);
		scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), 200);// Automatically up
		pauseInSeconds(1);

		validateDisplayedElement(getProperty("ecom.product.compare.link"), "Compare link");
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.product.compare.link"), "Clicked Compare link");
		pauseInSeconds(2);

		boolean isDisabled = false;
		validateDisplayedElement(getProperty("ecom.product.compare.header"), "Product Comparison header");
		validateDisplayedElement(getProperty("ecom.product.current.header"), "Current Product header");
		validateDisplayedElement(getProperty("ecom.product.suggested.header2"), "Suggested Product header");
		scrollIntoViewJS(getProperty("ecom.product.accept"), 100);// Automatically up
		validateDisplayedElement(getProperty("ecom.product.accept"), "Acccept");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.product.accept"), "Clicked Accept button");
		pauseInSeconds(4);

		// CCA Login
		ccaLogin();

		pauseInSeconds(2);
		// Navigate to SL conversion Upload page
		WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
		commonPassLog("Techinicl tab is displayed in the application");
		findElementByXpath(getProperty("ecom.cca.slconversion.reportlink")).click();
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.section.header"));
		isExists(getProperty("ecom.cca.slconversion.section.header"));
		captureScreenShot("Page Screenshot");
		commonPassLog("SL conversion - search criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.header"));
		isExists(getProperty("ecom.cca.slconversion.page.header"));
		commonPassLog("SL conversion - search criteria page header is displayed");
		// validate the date filed are available on the SLconversion report page
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
		commonPassLog("SL conversion - search criteria page start date field is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.enddate"));
		commonPassLog("SL conversion - search criteria page End date field is displayed");

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		System.out.println("Current Date: " + sdf.format(cal.getTime()));

		// Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, 365);
		// Date after adding the days to the current date
		String todayAsString = sdf.format(cal.getTime());
		// Displaying the new Date after addition of Days to current date
		System.out.println("Date after Addition: " + todayAsString);

		// changinf start date to current date
		findElementByXpath(getProperty("ecom.cca.slconversion.page.enddate")).clear();
		setText(getProperty("ecom.cca.slconversion.page.enddate"), todayAsString);
		captureScreenShot("Page Screenshot");
		commonPassLog("SL conversion - search criteria page Start date changed to current date");
		// clickin on search btn from the SL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.slconversion.page.Search.btn")).click();

		// SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

		commonPassLog("SL conversion - Report page is displayed");

		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.slconversion.reportpage.header"));

		captureScreenShot("Page Screenshot");
		commonPassLog("SL conversion - Search report page is dispalayed");

		// SL conversion page cloumn header Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.custaction"));
		isExists(getProperty("ecom.cca.slconversion.clmnheader.custaction"));

		commonPassLog("SL conversion - Search report page Column header cust auction is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));
		isExists(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));

		commonPassLog("SL conversion - Search report page Column header Refusal reason is displayed");
		///

		iMDone();
		pauseInSeconds(1);
	}

}
