package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.CCACommonPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * @author intakhabalam.s@hcl.com
 */
public class CCATextVerificationTest extends CommonElement {

	private static final String EC45_267 = "EC45_267 1. In CCA on customer detail page in the"
			+ " user listing table add \"Last Login Date\" column 1a. Add column between \"Site version\" and \"Status\"";

	private static final String EC45_183 = "EC45_183 1. Rename \"Hide Value Added Svc\" on user detail page to \"Hide Check Business Tools\""
			+ " 2. Rename \"Hide Value Added Svc\" on user import sheet to \"Hide Check Business Tools\""
			+ " 3. Rename \"Hide Value Added Svc\" on user import template to \"Hide Check Business Tools\"";

	private static final String EC45_507_547 = "1. Under 'Customer' tab there should be an option called \"ECOM Customer Data transfer\" (last option in dropdown) "
			+ "1a. Access should only be available to role \"USF-CustomerCare-HelpDeskMGR\" "
			+ "2. When user selects this option they should land on \"ECOM Customer Data Transfer Upload\" page which should have the following "
			+ "2a. Page label \"Customer transfer via File Import\" "
			+ "2b. Information text \"Customer transfer file required\" "
			+ "2c. \"Select file\" text box where user can specify path and browse option. On selecting browse user should be able to select a"
			+ " file from their computer 2d. Note template link - Clicking on the link should down "
			+ "a csv template with the name \"EcomCustomerDataTransferUpload\" the template should have following columns "
			+ "i. Old division number ii. Old customer number iii. New division number iv. New customer number "
			+ "2e. \"Upload File\" button i. If user has not selected a file to upload show error message \"Select file to upload\" "
			+ "ii. If user has selected file in a different format then display parsing errors "
			+ "3. Show standard CCA header and footer "

	        +"<br/><hr>EC45-547 1. When user selects \"Upload\" on \"ECOM Customer Data Transfer Upload\" page they should be navigated to "
			+ "\"ECOM Customer Data Transfer Validation\" page 2. Following should be displayed on Customer Transfer Validation page "
			+ "2a. Page label \"ECOM Customer Data Transfer Validation\" 2b. Table that shows all the records that were uploaded in the same order in which they were uploaded with the "
			+ "following columns "
			+ "i. Line number (line number in spreadsheet) "
			+ "ii. Old Division number"
			+ " iii. Old Customer Number "
			+ "iv. New Division number "
			+ "v. New Division number"
			+ " i. Error - Customer rows for which all customer-division data is valid this column will be blank - "
			+ "When division number is blank/invalid show error \"Provide valid 4 digit division number\" below the column with error - When division and customer number is not valid show error \"Customer number and division combination is invalid\" below division number column. - When customer number is blank/invalid show error \"Provide valid customer number\" below customer number column 2c. Commit 2d. Return - Should navigate users back to Ecom Customer Data Transfer Upload page 2e. CCA global header and footer should be displayed 3. When user selects 'Commit' on Ecom Customer Data Transfer Validation page transfer data from following tables for old division-customer to new division-customer i. EC_CUST_COMM_INFO ii. EC_CUST_EXTRNL_PROD iii. EC_CUST_GEOCORD iv. EC_CUST_GL v. EC_CUST_GL_POSTED vi. EC_CUST_GL_WORKING vii. EC_CUST_GL_OWNER viii.EC_CUST_VNDR_INFO ix. EC_INVY_HIST_HDR x. EC_INVY_HIST_DTL xi. EC_LIST xii. EC_MSTR_LIST_CUST xiii. EC_MSTR_LIST_PRODUCT xiv. EC_NON_USF_PROD xv. MP_CUSTOMER xvi. MP_HACCP xvii. MP_ITEM_ALLERGENS xviii. MP_ITEM_NUTRITIONAL xix.MP_MEAL_PLAN xx. MP_MENU_ITEM xxi. MP_MENU_ITEM_CATEGORY xxii. MP_POS_DATA xxiii. MP_PRODUCT xxiv. MP_RECIPE xxv. MP_SERVING_SIZE xxvi. TRDNG_PRTNR_CUST 4. When user selects 'Commit' on Ecom Customer Data Transfer Validation page along with transferring data mentioned in AC3 user should be navigated to Ecom Customer Data Transfer Results page with the following 4a. Page label \"ECOM Customer Data Transfer Results\" 4b. Table that shows all the records that were uploaded in the same order in which they were uploaded with the following columns i. LIne number ii. Old Division number iii. Old Customer Number iv. New Division number v. New Division number i. Results - Skipped: where valid division-customer information was not available - Successful: where valid division-customer information was available 4c. Export - All columns in the results table should be exported in .csv format with file name \"Customer_Transfer_Status_<Current date mm/dd/yyyy>\" "
			+ "4d. Return - Should navigate user back to Ecom Customer Data Transfer Upload page "
			+ "4e. CCA global header and footer should be displayed ";

	private CCACommonPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, CCACommonPage.class);
	}

	@Test(enabled = true)
	public void testCCALastLoginStatus() {
		try {
			int row = 1; // CCALastLoginStatusTest
			// Row required you may change row as per your
			// data
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCALastLoginStatusTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				
				startTestWithDescription(AppUtil.TEST_NAME, EC45_267,testSenarios); 
				page.validateCCALastLoginStatus();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_267);
			}
		} catch (Throwable ex) {
				commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOutCCA();
		}
	}

	/***
	 * 
	 */
	@Test(enabled = true)
	public void testHideCheckBussinessTool() {
		try {
			int row = 2; // CCAHideCheckBussinessToolTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAHideCheckBussinessToolTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_183,testSenarios); // Row required you may change row as per your data
				page.validateHideCheckBussinessTool();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_183);
			}

		} catch (Throwable ex) {
				commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOutCCA();
		}

	}

	@Test(enabled = true)
	public void testCCACustomerCareHelpDeskMGR() {
		try {
			int row = 3; // CCACustomerCareHelpDeskMGR
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCACustomerCareHelpDeskMGR".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_507_547,testSenarios); // Row required you may change row as per your data
				page.validateCCACustomerCareHelpDeskMGR();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_507_547);
			}

		} catch (Throwable ex) {
				commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOutCCA();
		}
	}

	/***
	 * End
	 */
	@AfterTest
	public void afterTest() {
		page = null;
	}

}
