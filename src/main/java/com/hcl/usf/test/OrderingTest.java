package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.OrderingPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class OrderingTest  extends CommonElement  {
	
	private static final String Ordering_1 = "Ordering_TC01-Validate Normal ordering functionality";
	private static final String Ordering_2 = "Ordering_TC02-Validate ordering functionality and Cancel Order";
	private static final String Ordering_3 = "Ordering_TC03-Validate Ordering is disabled for Credit hold customer";
	private static final String Ordering_4 = "Ordering_TC04-Validate Split Order (ResolveException)";
	private static final String Ordering_5 = "Ordering_TC12-Validate Edit Order functionality editing already submitted order";
	private static final String Ordering_6 = "Ordering_TC11-Validate ordering functionality and Cancel Order-(old)";
	private static final String Ordering_7 = "Ordering_TC08-Validate Minimum Order (CS)";
	private static final String Ordering_8 = "Ordering_TC09-Validate Minimum Order (dollar)";
	private static final String Ordering_9 = "Ordering_TC10-Validate Minimum Order (CS and dollar)";
	private static final String Ordering_10 = "Ordering_TC14-Validate Special Instruction in Order Confirmation Page for TM user";
	private OrderingPage page = null;
	
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, OrderingPage.class);
	}
	
	
	
	
	public void testDB(String type) {
		//selectRowAndReturnSenarios(7);
		consoleInfoLog(type+"=>"+findData("Query"));
		String x=config.dbConnection.doUpdate(findData("Query"));
		System.out.println("Query upldated :"+x);
	}
	
	

	@Test(enabled = true)
	public void normalOrdering() {
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
	@Test(enabled = true)
	public void cancelorder() {
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
	
	@Test(enabled = true)
	public void creaditholdValidtion() {
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

	@Test(enabled = true)
	public void validateEditingAndReubmittingTheNewOrder() {
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
	
	@Test(enabled = true)
	public void validateEditingAndReubmittingTheoldOrder() {
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
	
	@Test(enabled = true)
	public void validateCancletheoldorder() {
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
	@Test(enabled = true)
	public void validateMinorderwithCSValue() {
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

	@Test(enabled = true)
	public void validateMinorderwithCdollarValue() {
		try {
			int row = 8; //MinOrderdollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderdollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("dollar");  //Called DB for pre-requistion data setup with respect to particular customer
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

	@Test(enabled = true)
	public void validateMinorderwithCSwithdollarValue() {
		try {
			int row = 9; //MinOrderCSandDollarValueCheckTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MinOrderCSandDollarValueCheckTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				testDB("CSAndDollar");  //Called DB for pre-requistion data setup with respect to particular customer
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
	
	@Test(enabled = true)
	public void normalOrderingTM() {
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
	
	@Test(enabled = true)
	public void splitOrderwithoutExceptiontest() {
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
	
	@Test(enabled = true)
	public void splitOrderwithExceptiontest() {
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


}
 