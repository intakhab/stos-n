package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.MyShoppingListPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class ManageListTest extends CommonElement {

	private static final String ECR46_311 = "ECR46_311-1. When there are no shopping lists, user will see message \"You do not have any lists at this time. To create a list click the Manage List button.\"\r\n"
			+ "2. 'Create List' will be renamed to 'Manage List', clicking on it will navigate user to list app\r\n"
			+ "3. If user has OG/MSL they will show in \"Managed by US Foods\" section else existing \"No results found\" text will show";
	//ECR46_400

	private static final String ECR46_310 = " ECR46_310-Update Global Header and List Dropdown";

	private static final String ECR46_90 = "ECR46_90-1. Manage list buttons on list dropdown from Global Header, and on My List page";

	private static final String ECR46_64 = "ECR46_64-Consuming list products API";

	private static final String ECR46_596 = "ECR46_596-1. Deleting a product from a list in LMA should reflect in ECOM";

	private MyShoppingListPage mysl = null;

	@BeforeMethod
	public void beforeMethod() {
		mysl = PageFactory.initElements(driver, MyShoppingListPage.class);
	}

	@Test(enabled = true)
	public void testManageList() {
		try {
			int row = 1;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateUpdateListPage".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_311, testSenarios); // Row required you may change row
				mysl.validateMyListPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_311);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testHeaderandList() {
		try {
			int row = 2; // RemoveOptionsMyListsPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateGlobalListUpdate".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_310, testSenarios); // Row required you may change row
				mysl.validateHeaderandListdropDown(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_310);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testNoListLMACustomer() {
		try {
			int row = 3;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateNoListLMACustomer".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_90, testSenarios); // Row required you may change row
				mysl.validateNoListLMACustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_90);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testLMACustomerListView() {
		try {
			int row = 4;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateListViewForLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_64, testSenarios); // Row required you may change row
				mysl.validateListView(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_64);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testLMACustomerProductExpand() {
		try {
			int row = 5;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateListPrdExpandForLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_64, testSenarios); // Row required you may change row
				mysl.validateProductExpandLMA(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_64);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testNonLMACustomerProductExpand() {
		try {
			int row = 6;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateListPrdExpandForNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_64, testSenarios); // Row required you may change row
				mysl.validateProductExpandNonLMA(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_64);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testLMACustomerSL() {
		try {
			int row = 7;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateSLProductForLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_64, testSenarios); // Row required you may change row
				mysl.validateCustomShoppingList(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_64);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}


	@Test(enabled = true)
	public void testDeleteProductInList() {
		try {
			int row = 8;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateDeleteProductInList".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_596, testSenarios); // Row required you may change row
				mysl.validateDeleteProductInList(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_596);
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
		mysl = null;
	}

}
