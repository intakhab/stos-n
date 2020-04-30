package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.OrderingPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
import com.hcl.usf.util.ThreadLocalUtil;

@Deprecated
public class HealthCheckTest extends CommonElement{


	 static final String Ordering_1 = "Ordering_TC01-Validate Normal ordering functionality";
	 static final String Ordering_2 = "Ordering_TC02-Validate ordering functionality and Cancel Order";
	 static final String Ordering_3 = "Ordering_TC03-Validate Ordering is disabled for Credit hold customer";
	 static final String Ordering_4 = "Ordering_TC04-Validate Split Order (ResolveException)";
	 static final String Ordering_5 = "Ordering_TC12-Validate Edit Order functionality editing already submitted order";
	 static final String Ordering_6 = "Ordering_TC11-Validate ordering functionality and Cancel Order-(old)";
	 static final String Ordering_7 = "Ordering_TC08-Validate Minimum Order (CS)";
	 static final String Ordering_8 = "Ordering_TC09-Validate Minimum Order (dollar)";
	 static final String Ordering_9 = "Ordering_TC10-Validate Minimum Order (CS and dollar)";
	 static final String Ordering_10 = "Ordering_TC14-Validate Special Instruction in Order Confirmation Page for TM user";
	 OrderingPage page = null;
	
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, OrderingPage.class);
		ThreadLocalUtil.get().isHealthCheck=true;
	}
	
	
	public void testDB(String type) {
	
		consoleInfoLog(type+"=>"+findData("Query"));
		String x=config.dbConnection.doUpdate(findData("Query"));
		System.out.println("Query upldated :"+x);
	}
	

	@Test(enabled = true,priority=1)
	public void normalOrderingTestSIT() {
		try {
			int row = 1; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=2)
	public void cancelOrderTestSIT() {
		try {
			int row = 2; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=3)
	public void creaditholdValidtionTestSIT() {
		try {
			int row = 3; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=4)
	public void validateEditingAndReubmittingTheNewOrderTestSIT() {
		try {
			int row = 4; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=5)
	public void validateEditingAndReubmittingTheOldOrderTestSIT() {
		try {
			int row = 5; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=6)
	public void validateCancleTheOldOrderTestSIT() {
		try {
			int row = 6; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=7)
	public void validateMinorderWithCSValueTestSIT() {
		try {
			int row = 7; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=8)
	public void validateMinOrderWithCdollarValueTestSIT() {
		try {
			int row = 8; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=9)
	public void validateMinorderWithCSwithDollarValueTestSIT() {
		try {
			int row = 9; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=10)
	public void normalOrderingTMTestSIT() {
		try {
			int row = 10; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=11)
	public void splitOrderWithoutExceptionTestSIT() {
		try {
			int row = 11; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=12)
	public void splitOrderwithExceptionTestSIT() {
		try {
			int row = 12; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
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

	
/********************************SIT2*************************************/

	@Test(enabled = true,priority=13)
	public void normalOrderingTestSIT2() {
		try {
			int row = 13; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=14)
	public void cancelOrderTestSIT2() {
		try {
			int row = 14; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=15)
	public void creaditholdValidtionTestSIT2() {
		try {
			int row = 15; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=16)
	public void validateEditingAndReubmittingTheNewOrderTestSIT2() {
		try {
			int row = 16; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=17)
	public void validateEditingAndReubmittingTheOldOrderTestSIT2() {
		try {
			int row = 17; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=18)
	public void validateCancleTheOldOrderTestSIT2() {
		try {
			int row = 18; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=19)
	public void validateMinorderWithCSValueTestSIT2() {
		try {
			int row = 19; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=20)
	public void validateMinOrderWithCdollarValueTestSIT2() {
		try {
			int row = 20; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=21)
	public void validateMinorderWithCSwithDollarValueTestSIT2() {
		try {
			int row = 21; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=22)
	public void normalOrderingTMTestSIT2() {
		try {
			int row = 22; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=23)
	public void splitOrderWithoutExceptionTestSIT2() {
		try {
			int row = 23; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=24)
	public void splitOrderwithExceptionTestSIT2() {
		try {
			int row = 24; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

/*********************************************************SIT4***************************************/
	@Test(enabled = true,priority=25)
	public void normalOrderingTestSIT4() {
		try {
			int row = 25; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=26)
	public void cancelOrderTestSIT4() {
		try {
			int row = 26; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=27)
	public void creaditholdValidtionTestSIT4() {
		try {
			int row = 27; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=28)
	public void validateEditingAndReubmittingTheNewOrderTestSIT4() {
		try {
			int row = 28; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=29)
	public void validateEditingAndReubmittingTheOldOrderTestSIT4() {
		try {
			int row = 29; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=30)
	public void validateCancleTheOldOrderTestSIT4() {
		try {
			int row = 30; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=31)
	public void validateMinorderWithCSValueTestSTI4() {
		try {
			int row = 31; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=32)
	public void validateMinOrderWithCdollarValueTestSIT4() {
		try {
			int row = 32; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=33)
	public void validateMinorderWithCSwithDollarValueTestSTI4() {
		try {
			int row = 33; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=34)
	public void normalOrderingTMTestSTI4() {
		try {
			int row = 34; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=35)
	public void splitOrderWithoutExceptionTestSTI4() {
		try {
			int row = 35; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=36)
	public void splitOrderwithExceptionTestSIT4() {
		try {
			int row = 36; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/****************************************************UAT**************************************************************************/
	@Test(enabled = true,priority=37)
	public void normalOrderingTestUAT() {
		try {
			int row = 37; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=38)
	public void cancelOrderTestUAT() {
		try {
			int row = 38; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=39)
	public void creaditholdValidtionTestUAT() {
		try {
			int row = 39; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=40)
	public void validateEditingAndReubmittingTheNewOrderTestUAT() {
		try {
			int row = 40; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=41)
	public void validateEditingAndReubmittingTheOldOrderTestUAT() {
		try {
			int row = 41; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=42)
	public void validateCancleTheOldOrderTestUAT() {
		try {
			int row = 42; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=43)
	public void validateMinorderWithCSValueTestUAT() {
		try {
			int row = 43; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=44)
	public void validateMinOrderWithCdollarValueTestUAT() {
		try {
			int row = 44; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=45)
	public void validateMinorderWithCSwithDollarValueTestUAT() {
		try {
			int row = 45; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=46)
	public void normalOrderingTMTestUAT() {
		try {
			int row = 46; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=47)
	public void splitOrderWithoutExceptionTestUAT() {
		try {
			int row = 47; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=48)
	public void splitOrderwithExceptionTestUAT() {
		try {
			int row = 48; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	
/********************************UIT2*************************************/

	@Test(enabled = true,priority=49)
	public void normalOrderingTestUAT2() {
		try {
			int row = 49; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=50)
	public void cancelOrderTestUAT2() {
		try {
			int row = 50; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=51)
	public void creaditholdValidtionTestUAT2() {
		try {
			int row = 51; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=52)
	public void validateEditingAndReubmittingTheNewOrderTestUAT2() {
		try {
			int row = 52; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=53)
	public void validateEditingAndReubmittingTheOldOrderTestUAT2() {
		try {
			int row = 53; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=54)
	public void validateCancleTheOldOrderTestUAT2() {
		try {
			int row = 54; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=55)
	public void validateMinorderWithCSValueTestUAT2() {
		try {
			int row = 55; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=56)
	public void validateMinOrderWithCdollarValueTestUAT2() {
		try {
			int row = 56; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=57)
	public void validateMinorderWithCSwithDollarValueTestUAT2() {
		try {
			int row = 57; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=58)
	public void normalOrderingTMTestUAT2() {
		try {
			int row = 58; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=59)
	public void splitOrderWithoutExceptionTestUAT2() {
		try {
			int row = 59; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=60)
	public void splitOrderwithExceptionTestUAT2() {
		try {
			int row = 60; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

/*********************************************************SIT4***************************************/
	@Test(enabled = true,priority=61)
	public void normalOrderingTestUAT4() {
		try {
			int row = 61; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=62)
	public void cancelOrderTestUAT4() {
		try {
			int row = 62; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=63)
	public void creaditholdValidtionTestUAT4() {
		try {
			int row = 63; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=64)
	public void validateEditingAndReubmittingTheNewOrderTestUAT4() {
		try {
			int row = 64; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=65)
	public void validateEditingAndReubmittingTheOldOrderTestUAT4() {
		try {
			int row = 65; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=66)
	public void validateCancleTheOldOrderTestUAT4() {
		try {
			int row = 66; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=67)
	public void validateMinorderWithCSValueTestUAT4() {
		try {
			int row = 67; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=68)
	public void validateMinOrderWithCdollarValueTestUAT4() {
		try {
			int row = 68; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=69)
	public void validateMinorderWithCSwithDollarValueTestUAT4() {
		try {
			int row = 69; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=70)
	public void normalOrderingTMTestUAT4() {
		try {
			int row = 70; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=71)
	public void splitOrderWithoutExceptionTestUAT4() {
		try {
			int row = 71; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=72)
	public void splitOrderwithExceptionTestUAT4() {
		try {
			int row = 72; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/********************************************************** EXTERNAL ***********************************************/
	@Test(enabled = true,priority=73)
	public void normalOrderingTestExt() {
		try {
			int row = 73; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=74)
	public void cancelOrderTestExt() {
		try {
			int row = 74; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=75)
	public void creaditholdValidtionTestExt() {
		try {
			int row = 75; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=76)
	public void validateEditingAndReubmittingTheNewOrderTestExt() {
		try {
			int row = 76; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=77)
	public void validateEditingAndReubmittingTheOldOrderTestExt() {
		try {
			int row = 77; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=78)
	public void validateCancleTheOldOrderTestExt() {
		try {
			int row = 78; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=79)
	public void validateMinorderWithCSValueTestExt() {
		try {
			int row = 79; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=80)
	public void validateMinOrderWithCdollarValueTestExt() {
		try {
			int row = 80; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=81)
	public void validateMinorderWithCSwithDollarValueTestExt() {
		try {
			int row = 81; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=82)
	public void normalOrderingTMTestExt() {
		try {
			int row = 82; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=83)
	public void splitOrderWithoutExceptionTestExt() {
		try {
			int row = 83; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=84)
	public void splitOrderwithExceptionTestExt() {
		try {
			int row = 84; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	/***************************************************************************************PERF2******************************************/
	
	@Test(enabled = true,priority=85)
	public void normalOrderingTestRef2() {
		try {
			int row = 85; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=86)
	public void cancelOrderTestRef2() {
		try {
			int row = 86; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=87)
	public void creaditholdValidtionTesRef2() {
		try {
			int row = 87; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=88)
	public void validateEditingAndReubmittingTheNewOrderTestRef2() {
		try {
			int row = 88; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=89)
	public void validateEditingAndReubmittingTheOldOrderTestRef2() {
		try {
			int row = 89; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=90)
	public void validateCancleTheOldOrderTestRef2() {
		try {
			int row = 90; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=91)
	public void validateMinorderWithCSValueTestRef2() {
		try {
			int row = 91; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=92)
	public void validateMinOrderWithCdollarValueTestRef2() {
		try {
			int row = 92; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=93)
	public void validateMinorderWithCSwithDollarValueTestRef2() {
		try {
			int row = 93; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=94)
	public void normalOrderingTMTestRef2() {
		try {
			int row = 94; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=95)
	public void splitOrderWithoutExceptionTesRef2() {
		try {
			int row = 95; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=96)
	public void splitOrderwithExceptionTestRef2() {
		try {
			int row = 96; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
/***************************************************************************************PERF4******************************************/
	
	@Test(enabled = true,priority=97)
	public void normalOrderingTestRef4() {
		try {
			int row = 97; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_1,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=98)
	public void cancelOrderTestRef4() {
		try {
			int row = 98; //CancelNewOrder
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CancelNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_2,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_2);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=99)
	public void creaditholdValidtionTesRef4() {
		try {
			int row = 99; //CreditHoldTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CreditHoldTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_3,testSenarios); // Row required you may change row as per your data
				page.creditHoldValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_3);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=100)
	public void validateEditingAndReubmittingTheNewOrderTestRef4() {
		try {
			int row = 100; //EditingtheNewOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheNewOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_4,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=101)
	public void validateEditingAndReubmittingTheOldOrderTestRef4() {
		try {
			int row =101; //EditingtheoldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("EditingtheoldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_5,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.editingAndResubmitingFromNewlyCreatedOrder();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_5);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=102)
	public void validateCancleTheOldOrderTestRef4() {
		try {
			int row = 102; //CanceloldOrderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CanceloldOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_6,testSenarios); // Row required you may change row as per your data
				page.oldOrderSubmittedPageNavigation(cdto.getCustomerId(), cdto.getDepartmentId());
				page.cancelFromTheOrderConfirmationPage();
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_6);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=103)
	public void validateMinorderWithCSValueTestRef4() {
		try {
			int row = 103; //MinOrderCSValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_7,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_7);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=104)
	public void validateMinOrderWithCdollarValueTestRef4() {
		try {
			int row = 104; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_8,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_8);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true,priority=105)
	public void validateMinorderWithCSwithDollarValueTestRef4() {
		try {
			int row = 105; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CS");  //Called DB for pre-requistion data setup with respect to particular customer

				startTestWithDescription(AppUtil.TEST_NAME,Ordering_9,testSenarios); // Row required you may change row as per your data
				page.minOrderValidationTest(cdto.getCustomerId(), cdto.getDepartmentId());
				iMDone();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_9);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true,priority=106)
	public void normalOrderingTMTestRef4() {
		try {
			int row = 106; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("NormalOrderCreationTMuserTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateNormalOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=107)
	public void splitOrderWithoutExceptionTesRef4() {
		try {
			int row = 107; //SplitOrderWithoutExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithoutExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrder(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true,priority=108)
	public void splitOrderwithExceptionTestRef4() {
		try {
			int row = 108; //SplitOrderWithExceptionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SplitOrderWithExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,Ordering_10,testSenarios); // Row required you may change row as per your data
				page.validateSPlitOrderWithException(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,Ordering_10);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
}
