package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.CCAOGandMSLConversionPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class CCAOGandMSLConversionPageTest extends CommonElement {

	private static final String EC45_297 = "1. Create a new page under the Technical Tab called OG/MSL Conversion Report, for OG/MSL suggested sell acceptance process. When user selects the option they should land on OG/MSL Conversion - Search Criteria page that has the following options for search criteria"
			+ "1a. Text Enter Criteria Below to Initiate Report"
			+ "1b. Start Date (input field and calendar icon) (default to one year prior to current date)"
			+ "1c. End Date (input field and calendar icon) (default to current date)"
			+ "1d. Users: Radio button options for All Users and Specific User. All users should be selected by default. When user selects specific user it should open text field to enter user id. (Should be user id of the person that uploaded the suggestion)"
			+ "1e. List Type: Radio button options for 'Order Guide' and 'Master Shopping Lists' should default to Order Guide"
			+ "1f. Search CTA" + "1g. CCA global header and footer should be displayed on the page"
			+ "2. When user   selects 'Search' and if any of the search criteria is missing they should be displayed an error message on the same page Invalid Search Criteria. Please Try Again"
			+ "3. When user selects search and has provided all search criteria and selected list type as Order Guide they should be navigated to page called OG Conversion - Report. Page should have the following"
			+ "3a. Common CCA table options i.e. Filter, # of records, pages, page size drop down, previous page and next page options"
			+ "3b. Report table should have following columns" + "i. Customer Number" + "ii. Division number"
			+ "iii. Accepted by User id" + "iv. Suggested by User id" + "v. Original product number"
			+ "vi. Original product description" + "vii. Accepted product number" + "viii. Accepted product description"
			+ "ix. OG Number"
			+ "x. OG Group Name - If the product is in multiple groups then display all group names in same column using comma)"
			+ "xi. Reason message" + "3c. Pagination"
			+ "3d. Export - Should download all columns of the report in xls format. Download file should be called OG_Conversion_Report_<CurrentDate>. On download show group names in individual columns"
			+ "3e. Return - Should return user to OG/MSL Conversion - Search Criteria page"
			+ "4. When user selects search and has provided all search criteria and selected list type as Master Shopping List they should be navigated to page called MSL Conversion - Report. Page will only pull conversions for MSL's from MLM and not COGM. Page should have the following"
			+ "4a. Common CCA table options i.e. Filter, # of records, pages, page size drop down, previous page and next page options"
			+ "4b. Report table should have following columns" + "i. Customer Number" + "ii. Division number"
			+ "iii. Accepted by User id" + "iv. Suggested by User id" + "v. Original product number"
			+ "vi. Original product description" + "vii. Accepted product number" + "viii. Accepted product description"
			+ "ix. MSL name (When there is more than one MSL then display multiple names in same column using comma)"
			+ "xi. Reason message" + "4c. Pagination"
			+ "4d. Export - Should download all columns of the report in xls format. Download file should be called MSL_Conversion_Report_<CurrentDate>. In the download put MSL names in individual columns"
			+ "4e. Return - Should return user to OG/MSL Conversion - Search Criteria page"
			+ "5. Track the Group name of the original product"
			+ "5a. Display Group name of the product as a column in the OG conversion report i.e. AC 3b(ix)"
			+ "5b. If original product is in multiple groups, put the group names in a comma separated format..";

	private CCAOGandMSLConversionPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, CCAOGandMSLConversionPage.class);

	}

	@Test(enabled = false)
	public void ccogconversionValidation() {
		try {
			int row = 1; // CCAOGConverionReportTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("CCAOGConverionReportTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_297, testSenarios); // Row required you may change row
																						// as per your data
				page.validateCCAOGandMSLConversionReportsPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, EC45_297);
			}

		} catch (Throwable ex) {

			commonErrorUpdate(ex);
		} finally {
			signOutCCA();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void ccamslconversionValidation() {
		try {
			int row = 2; // CCAOGConverionReportTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("CCAMSLConverionReportTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_297, testSenarios); // Row required you may change row
																						// as per your data
				page.validateCCAMSLConversionReportsPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, EC45_297);

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
