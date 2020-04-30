package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.MigrationHomeOrderValidationPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class MigrationHomePageOrderStatusTest extends CommonElement  {
	private static final String HomepagesectionValidation_1 = "TC01-Home Page with 3 orders-SubmittedWithExceptions";
	private static final String HomepagesectionValidation_2 = "TC02-Home page with SubmittedWithExceptions,NotSubmitted,Submitted";
	private static final String HomepagesectionValidation_3 = "TC03-HomePage with Submitted with Exceptions and Not Submitted	";
	private static final String HomepagesectionValidation_4 = "TC04-HomePage with Not Submitted and Submitted";
	private static final String HomepagesectionValidation_5 = "TC05-HomePage with Submitted only";
	private static final String HomepagesectionValidation_6 = "TC06-HomePage with Not Submitted only";
	private static final String HomepagesectionValidation_7 = "TC08-HomePage Header Message - Ordering Not Available";
	private static final String HomepagesectionValidation_8 = "TC07-HomePage Notification validation";
	
	private MigrationHomeOrderValidationPage page = null;
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, MigrationHomeOrderValidationPage.class);
		
	}

	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithallException() {
		try {
			int row = 1; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionAllExceptionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_1,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_1);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithalltype() {
		try {
			int row = 2; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionAlltypeorderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_2,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_2);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithExceptionandNotSubmitted() {
		try {
			int row = 3; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionExceptionandNotsubmittedTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_3,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_3);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithsubmittedandNotsubmitted() {
		try {
			int row = 4; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionSubmittedandNotsubmittedTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_4,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_4);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithallSubmitted() {
		try {
			int row = 5; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionSubmittedTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_5,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_5);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithallNotSubmitted() {
		try {
			int row = 6; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionNotSubmittedTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_6,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_6);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true)
	public void testHomePageOrderingSectionPagewithNoOrder() {
		try {
			int row = 7; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionNoOrderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_7,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPageAllexception(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_7);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testHomePageNotification() {
		try {
			int row = 8; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageNotificationSectionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_8,testSenarios); // Row required you may change row as per your data
				page.validateHomePageNotificationValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_8);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true)
	public void testHomePagerderStatusSection() {
		try {
			int row = 9; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingstatussectionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,HomepagesectionValidation_8,testSenarios); // Row required you may change row as per your data
				page.validateHomePageNotificationValidation(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,HomepagesectionValidation_8);
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
