package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.OrderingPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
import com.hcl.usf.util.ThreadLocalUtil;

/**
 * @author intakhabalam.s@hcl.com
 */
public class NormalOrderHealthCheckTest extends CommonElement {

	private static final String Ordering_1 = "Ordering_TC01-Validate Normal ordering functionality";
	private  OrderingPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, OrderingPage.class);
		ThreadLocalUtil.get().isHealthCheck = true;
	}

	@Test(enabled = true, priority = 1)
	public void normalOrderingTestSIT() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 1; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestSIT".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	

	/******************************** SIT2 *************************************/

	@Test(enabled = true, priority = 2)
	public void normalOrderingTestSIT2() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 2; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestSIT2".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/*********************************************************
	 * SIT4
	 ***************************************/
	@Test(enabled = true, priority = 3)
	public void normalOrderingTestSIT4() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 3; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestSIT4".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/****************************************************
	 * UAT
	 **************************************************************************/
	@Test(enabled = true, priority = 4)
	public void normalOrderingTestUAT() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 4; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestUAT".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/******************************** UIT2 *************************************/

	@Test(enabled = true, priority = 5)
	public void normalOrderingTestUAT2() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 5; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestUAT2".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
																						// row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/*********************************************************
	 * SIT4
	 ***************************************/
	@Test(enabled = true, priority = 6)
	public void normalOrderingTestUAT4() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 6; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestUAT4".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
																						// row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/**********************************************************
	 * EXTERNAL
	 ***********************************************/
	@Test(enabled = true, priority = 7)
	public void normalOrderingTestExt() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 7; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestExt".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
																						// row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***************************************************************************************
	 * PERF2
	 ******************************************/

	@Test(enabled = true, priority = 8)
	public void normalOrderingTestPRef1() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 8; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestPERF1".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
																						// row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***************************************************************************************
	 * PERF4
	 ******************************************/

	@Test(enabled = true, priority = 9)
	public void normalOrderingTestRef2() {
		try {
			ThreadLocalUtil.get().siteRunningStatus="";
			int row = 9; // Normal Order creation flow
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTestPERF2".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, Ordering_1, testSenarios); // Row required you may change
																						// row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, Ordering_1);
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
