package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.CCAAddDeclineReasonPage;
import com.hcl.usf.pages.SuggestedSellCaptureReasonPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * EC45-45 	Change Promotions link "Get Details" to say "Access Promotions"
 * @author intakhabalam.s@hcl.com
 */
public class CCAAddDeclineReasonTest extends CommonElement {

	private static final String EC45_23 = "1. Add column called 'Reason for Refusal' on CCA Suggested Sell "
			+ "Conversion report for decline reason after \"Customer Action\" column."
			+ "1a. If user did not Decline, please populate column with Not Applicable."
			+ "1b. If user declines but does not choose a reason, please populate column with No Response."
			+ "1c. If user declines and submits a reason, please display that reason messaging in the column."
			+ "•	Cost"
			+ "•	Quality"
			+ "•	Pack size"
			+ "•	Flavor profile"
			+ "•	Not nutritionally equivalent"
			+ "•	Conflicts with current menu or menu cycle"
			+ "•	Conflicts with brand offerings"
			+ "•	Conflicts with rebated / contracted products"
			+ "•	Other "
			+ "For both CCA Conversion report screen and download.";


	private static final String EC45_183 = "1. Rename Hide Value Added Svc on user detail page to Hide Check Business Tools"
			+"2. Rename Hide Value Added Svc on user import sheet to Hide Check Business Tools"
			+"3. Rename Hide Value Added Svc on user import template to Hide Check Business Tools";

	private static final String EC45_609 = "1. In CCA under 'Resources' option remove 'WMT Tester' option"
			+"1a. Do not remove the functionality code just the option from menu so it can be brought back";



	private static final String EC45_606 ="1. In CCA add the following options to extended search option of User Search"
			+"1a. Customization dropdown - Should have all user customization values (add above customer group)"
			+"1a. Client/Concept dropdowns - Two dropdowns first for Client which should have all user client values. Concept drop down should have values for the selected client (add between Customization and customer group)"
			+"1b. Site Role dropdown - When user selects a site role return all users that are associated. (add above Site version)"
			+"1c. Under 'Attributes' add the following selections"
			+"i. MLM"
			+"ii. GL Admin"
			+"iii. DWO Status"
			+"iv. Hide Business Analytics"
			+"v. True Mfr Reports"
			+"vi. CPM reports"
			+"vii. Punch out"
			+"2. when user selects search and has selected one of the above options then return all users associated to that option"
			+"2a. When user has selected more then one option then only users associated to all the selected options should be returned";

	private static final String EC45_267 ="1. In CCA on customer detail page in the user listing table add Last Login Date column"
			+"1a. Add column between Site version and Status";

	private static final String EC45_311 ="1. If user accepts on suggested sell and goes to review order, the yellow module needs to be shown for the old product if it is in the current order."
			+"1a. This suggested sell module treatment should reflect on all flows within review order (i.e. navigating to PDP, Product Compare, QOE).";

	private static final String EC45_181 ="1. If user has a suggested sell pair on the Review Order/Review Order Summary page (regardless of whether it is on SL/OG/MSL), and user selects Accept Suggestion, existing functionality occurs (i.e. Suggested Product will replace original product)."
			+"2. If original product has a number value in the quantity box and user accepts the suggestion, the suggested product should replace the original product and contain the same quantity as the original product did."
			+"3. If the original product has a number value and user adds cases/each of suggested product, then both products should be added to the order with their respective values."
			+"3a. If user accepts suggestion with both original product and suggested product containing cases, the original product cases will be deleted and suggested product will retain it's value in the order."
			+"4. If the original product has a number value and user adds cases/each of suggested product, then navigates to another page without accepting the suggestion, Original product should show suggested sell with the quantity boxes filled for both the original product and the suggested product";
	private CCAAddDeclineReasonPage page = null;
	private SuggestedSellCaptureReasonPage page1 = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, CCAAddDeclineReasonPage.class);
		page1 = PageFactory.initElements(driver, SuggestedSellCaptureReasonPage.class);
	}

	@Test(enabled = false)
	public void homePageOrderingSectionPage() {
		try {
			int row = 1; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAAddDeclineConverionReportTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_23,testSenarios); // Row required you may change row as per your data
				page.validateCCAConversionReportsPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_23);

			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}
	}



	@Test(enabled = false)
	public void ccaSuggestedProductDeclineResonValidation() {
		try {
			int row = 2; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCASuggestedProductDeclineReasonValidationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_23,testSenarios); // Row required you may change row as per your data
				page1.validatesuggestedreasonandsubmitwithreason(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_23);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}


	}


	@Test(enabled = false)
	public void ccaSuggestedProductAcceptResonValidation() {
		try {
			int row = 3; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCASuggestedProductAcceptReasonValidationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_23,testSenarios); // Row required you may change row as per your data
				page1.validatesuggestedAcceptreasonandsubmitwithreason(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_23);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}
	}

	@Test(enabled = false)
	public void ccaSuggestedProductAcceptResonValidationSL() {
		try {
			int row = 4; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCASuggestedProductAcceptReasonValidationSLTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_23,testSenarios); // Row required you may change row as per your data
				page1.validatesuggestedAcceptreasonandsubmitwithreasonSL(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_23);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}


	}


	@Test(enabled = false)
	public void ccaSuggestedProductAcceptResonValidationReviewOrder() {
		try {
			int row = 5; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCASuggestedProductAcceptReasonValidationReviewTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_311,testSenarios); // Row required you may change row as per your data
				page1.validatesuggestedAcceptreasonandsubmitwithreasonReview(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_311);

			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}




	}


	@Test(enabled = true)
	public void ccacustomerlastlogindater() {
		try {
			int row = 6; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCACustomerlastloginTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_267,testSenarios); // Row required you may change row as per your data
				page.validateCCACustomerlastlogin(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_267);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}

	}

	@Test(enabled = false)
	public void ccaUpdatehidevalueaddedservices() {
		try {
			int row = 7; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAUpdatehidevalueaddedservicesTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_183,testSenarios); // Row required you may change row as per your data
				page.validateccaUpdatehidevalueaddedservices(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_183);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}

	}

	@Test(enabled = true)
	public void ccausersearchValidationtest() {
		try {
			int row = 8; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAUserServiceTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_606,testSenarios); // Row required you may change row as per your data
				page.validateccaUserSearch(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_606);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}

	}


	@Test(enabled = false)
	public void ccawmttestertest() {
		try {
			int row = 9; //CCAAddDeclineConverionReportTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAWMTtesterremovelTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_609,testSenarios); // Row required you may change row as per your data
				page.validateccaWMTtester(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_609);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}

	}

	@AfterTest
	public void afterTest() {
		page = null;
	}

}

