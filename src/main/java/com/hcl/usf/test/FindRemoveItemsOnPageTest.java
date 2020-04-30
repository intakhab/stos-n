package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.TextVerifyCommonChangePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * @author intakhabalam.s@hcl.com
 */
public class FindRemoveItemsOnPageTest extends CommonElement {

	private static final String ECR46_62 = "ECR46-62 - 1. On 'My Lists' page remove Options and Delete columns "
			+ "1a. These are categorized under Actions " + "1b. Redefine table based on mockup provided <br/>";

	private static final String ECR46_67 = "EC46-67 - 1. Remove the Actions, Download and Print options above the Current Order box <br/>"
			+ "1a. This applies to SL, OG and MSL " + "1b. Re-format page based on mockup provided";
	
	private static final String ECR46_315 = "EC46-315 - 1. On My Kitchen, when customer does not have shopping list (even if they have OG/MSL),"
			+ " we will hide 'Add to List'";

	private TextVerifyCommonChangePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, TextVerifyCommonChangePage.class);
	}

	@Test(enabled = true)
	public void testRemoveOptionsMyListsPage() {
		try {
			int row = 12; // RemoveOptionsMyListsPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateManageListOptionforLMAConvertedCustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_62, testSenarios); // Row required you may change row
				page.validateRemoveOptionsMyListsPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_62);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testOptionsMyListsPage() {
		try {
			int row = 13; // OptionsMyListsPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateCreateListOptionforLMANotConvertedCustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_62, testSenarios); // Row required you may change row
				page.validateOptionsMyListsPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_62);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testRemoveActionsDownloadPrintOnPage() {
		try {
			int row = 14; // RemoveActionsDownloadPrintOnPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateActionsDownloadPrintOnPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_67, testSenarios); // Row required you may change data
				page.validateRemoveActionsDownloadPrintOnPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_67);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testeActionsDownloadPrintOnPage() {
		try {
			int row = 15; // ActionsDownloadPrintOnPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateActionsDownloadPrintOnPageforNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_67, testSenarios); // Row required you may change data
				page.validateActionsDownloadPrintOnPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_67);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testRemoveAddListFromMyKitchePage() {
		try {
			int row = 16; // RemoveAddListFromMyKitchePageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValdiateAddListFromMyKitchePageforLMACustomer-NoShoppingList".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_315, testSenarios); // Row required you may change row as per your data
				page.validateRemoveAddListFromMyKitchePage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_315);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testAddListFromMyKitchePage() {
		try {
			int row = 17; // AddListFromMyKitchePageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValdiateAddListFromMyKitchePageforLMACustomer-ShoppingList".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_315, testSenarios); // Row required you may change row as per your data
				page.validateAddListFromMyKitchePage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_315);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@AfterTest
	public void afterTest() {
		page = null;
	}

}
