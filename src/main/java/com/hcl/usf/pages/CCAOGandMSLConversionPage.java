package com.hcl.usf.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebElement;
/***
 * 
 *This code is written by Rajesh, 
 *Bad and Dead code is there, we need to correct it
 */
public class CCAOGandMSLConversionPage extends CommonPageElement {

	public void validateCCAOGandMSLConversionReportsPage(String customerNo, String departNo) {
		// CCA Login
		ccaLogin();

		pauseInSeconds(2);
		// Navigate to SL conversion Upload page
		WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
		commonPassLog("Techinicl tab is displayed in the application");
		// OGand MSL conversion Link
		findElementByXpath(getProperty("ecom.cca.OGandMSLconversion.reportlink")).click();
		// OGand MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.section.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.section.headerr"));
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		commonPassLog("OG/MSL Conversion - Search Criteria page header is displayed");
		// validate the date filed are available on the OG and MSL conversion report
		// page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGconversion.page.startdate"));
		commonPassLog("OG/MSL Conversion - Search Criteria page start date field is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.enddate"));
		commonPassLog("OG/MSL Conversion - Search Criteria page End date field is displayed");
		// seleting the current date
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
		System.out.println("Today is: " + todayAsString);

		// changinf start date to current date
		findElementByXpath(getProperty("ecom.cca.OGconversion.page.startdate")).clear();
		setText(getProperty("ecom.cca.OGconversion.page.startdate"), todayAsString);
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page Start date changed to current date");
		// clickin on search btn from the SL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.header"));

		commonPassLog("OG/MSL Conversion - Report page is displayed");

		// Validating No data is available for the selected data
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.Nodatamsg"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.Nodatamsg"));

		pauseInSeconds(2);
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Report page is displayed with No Data available in the page");

		WebElement element1 = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element1);
		// OGand MSL conversion Link
		findElementByXpath(getProperty("ecom.cca.OGandMSLconversion.reportlink")).click();
		// OGand MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.section.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.section.headerr"));
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		commonPassLog("OG/MSL Conversion - Search Criteria page header is displayed");

		// clickin on Specific user radio btn
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.radiobtn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.SpecificUser.radiobtn")).click();

		// clickin on Specific user radio btn
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.inputtxt"));
		// findElementByXpath(getProperty(ecom.cca.ogconversion.SpecificUser.inputtxt")).click();
		setText(getProperty("ecom.cca.ogconversion.SpecificUser.inputtxt"), "Z");

		// clickin on search btn from the Og conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// OG page Invalid user error message Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.errormsg"));
		isExists(getProperty("ecom.cca.ogconversion.SpecificUser.errormsg"));
		commonPassLog("Invalid User Error message is displayed");

		// clickin on search btn from the Og conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.header"));

		commonPassLog("OG/MSL Conversion - Report page is displayed");

		// OG MSL conversion page cloumn header Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn1"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn1"));

		commonPassLog("OG MSL conversion report page Column header CUSTOMER NUMBER is displayed");

		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn2"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn2"));

		commonPassLog("OG MSL conversion report page Column header  DIVISION NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn3"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn3"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED BY USER ID is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn4"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn4"));

		commonPassLog("OG MSL conversion report page Column header SUGGESTED BY USER ID is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn5"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn5"));

		commonPassLog("OG MSL conversion report page Column header ORIGINAL PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn6"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn6"));

		commonPassLog("OG MSL conversion report page Column header ORIGINAL PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn7"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn7"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn8"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn8"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED PRODUCT DESCRIPTION is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn9"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn9"));

		commonPassLog("OG MSL conversion report page Column header REASON MESSAGE is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn11"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn11"));

		commonPassLog("OG MSL conversion report page Column header OG GROUP NAME is displayed");

		///

		iMDone();
		pauseInSeconds(1);
	}

	public void validateCCAMSLConversionReportsPage(String customerNo, String departNo) {
		// CCA Login
		ccaLogin();

		pauseInSeconds(2);
		// Navigate to SL conversion Upload page
		WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
		commonPassLog("Techinicl tab is displayed in the application");
		// OGand MSL conversion Link
		findElementByXpath(getProperty("ecom.cca.OGandMSLconversion.reportlink")).click();
		// OGand MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.section.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.section.headerr"));
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		commonPassLog("OG/MSL Conversion - Search Criteria page header is displayed");
		// validate the date filed are available on the OG and MSL conversion report
		// page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGconversion.page.startdate"));
		commonPassLog("OG/MSL Conversion - Search Criteria page start date field is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.enddate"));
		commonPassLog("OG/MSL Conversion - Search Criteria page End date field is displayed");
		// seleting the current date
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
		System.out.println("Today is: " + todayAsString);

		// changinf start date to current date
		findElementByXpath(getProperty("ecom.cca.OGconversion.page.startdate")).clear();
		setText(getProperty("ecom.cca.OGconversion.page.startdate"), todayAsString);
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page Start date changed to current date");

		// Select the MSL radio button from OG and MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.mslradiobtn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.SpecificUser.mslradiobtn")).click();

		// clickin on search btn from the MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// MSL conversion report page section header
		waitForVisibilityOfElement(getProperty("ecom.cca.mslconversion.sectionheader"));
		findElementByXpath(getProperty("ecom.cca.mslconversion.sectionheader")).click();

		// SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.header"));

		commonPassLog("OG/MSL Conversion - Report page is displayed");

		// Validating No data is available for the selected data
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.Nodatamsg"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.Nodatamsg"));

		pauseInSeconds(2);
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Report page is displayed with No Data available in the page");

		WebElement element1 = waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element1);
		// OGand MSL conversion Link
		findElementByXpath(getProperty("ecom.cca.OGandMSLconversion.reportlink")).click();
		// OGand MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.section.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.section.headerr"));
		captureScreenShot("Page Screenshot");
		commonPassLog("OG/MSL Conversion - Search Criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		isExists(getProperty("ecom.cca.OGandMSLconversion.page.header"));
		commonPassLog("OG/MSL Conversion - Search Criteria page header is displayed");

		// clickin on Specific user radio btn
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.radiobtn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.SpecificUser.radiobtn")).click();

		// clickin on Specific user radio btn
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.inputtxt"));
		// findElementByXpath(getProperty(ecom.cca.ogconversion.SpecificUser.inputtxt")).click();
		setText(getProperty("ecom.cca.ogconversion.SpecificUser.inputtxt"), "Z");

		// clickin on search btn from the Og conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// OG page Invalid user error message Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.errormsg"));
		isExists(getProperty("ecom.cca.ogconversion.SpecificUser.errormsg"));
		commonPassLog("Invalid User Error message is displayed");

		// Select the MSL radio button from OG and MSL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.SpecificUser.mslradiobtn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.SpecificUser.mslradiobtn")).click();

		// clickin on search btn from the Og conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.ogconversion.page.Search.btn")).click();

		// SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.ogconversion.reportpage.header"));

		commonPassLog("OG/MSL Conversion - Report page is displayed");

		// OG MSL conversion page cloumn header Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn1"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn1"));

		commonPassLog("OG MSL conversion report page Column header CUSTOMER NUMBER is displayed");

		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn2"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn2"));

		commonPassLog("OG MSL conversion report page Column header  DIVISION NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn3"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn3"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED BY USER ID is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn4"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn4"));

		commonPassLog("OG MSL conversion report page Column header SUGGESTED BY USER ID is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn5"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn5"));

		commonPassLog("OG MSL conversion report page Column header ORIGINAL PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn6"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn6"));

		commonPassLog("OG MSL conversion report page Column header ORIGINAL PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn7"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn7"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED PRODUCT NUMBER is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn8"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn8"));

		commonPassLog("OG MSL conversion report page Column header ACCEPTED PRODUCT DESCRIPTION is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn9"));
		isExists(getProperty("ecom.cca.ogmslconversion.clmn9"));

		commonPassLog("OG MSL conversion report page Column header REASON MESSAGE is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.ogmslconversion.clmn10"));
		if (isExists(getProperty("ecom.cca.ogmslconversion.clmn10"))) {
			commonPassLog("OG MSL conversion report page Column header MSL NAME is displayed");
		}

		///

		// iMDone();
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");

	}

}
