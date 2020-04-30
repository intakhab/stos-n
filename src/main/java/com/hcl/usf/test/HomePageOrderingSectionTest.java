package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.HomePageOrderingSectionPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * EC45-45 	Change Promotions link "Get Details" to say "Access Promotions"
 * @author intakhabalam.s@hcl.com
 */
public class HomePageOrderingSectionTest extends CommonElement {

	private static final String EC45_109_105 = " EC45-109 - Verify that Home Page section is\r\n" + 
			"   not available in home page if the customer does not having the orders\r\n" + 
			"   Verify that Home Page section is available in home page if the\r\n" + 
			"   customer does not having the orders"
	
	        +"<br/> EC45_105 - 1. For ordering status & Icon – space after should be 20px "
			+ " 1a. Space between imported orders and create order should be 30px."
			+ " 2. Below Create order button should be 30px spacing (between create order button and table) ";
	
	private static final String ECR46_309 = "1. Disable create from list\r\n" + 
			"2. In Quick Order Entry, disable the option to order from list.\r\n" + 
			"     2a. Customer can still use QOE with product numbers, Use Product Number would shift to left.\r\n" + 
			"     2b. Once you click Create, remove the \"Use List Number\" box from top";
	
	private static final String ECR47_51="1) Allow up to 2 notes to show on notification module on homepage\r\n" + 
			"     1a. Most recent 2 notes, most recent at top\r\n" + 
			"2)If there are more than 2 active notes, a link to view more will show under the second notification \"More Notes From Your Team\"\r\n" + 
			"3) Priority stays the same as it is now\r\n" + 
			"     3a.Priority 2 position, will move up if messages with higher priority are not present";
	
	private static final String ECR47_52="1) \"Recommended By Your Team\" rename (no longer Personal Recommendations)\r\n" + 
			"2) Each TM note as a subpage";
	
	
	
	private HomePageOrderingSectionPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, HomePageOrderingSectionPage.class);
	}
	
	@Test(enabled = true)
	public void testHomePageOrderingSectionPage() {
		try {
			int row = 3; //HomePageOrderingSectionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("HomePageOrderingSectionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_109_105,testSenarios); // Row required you may change row as per your data
				page.validateHomePageOrderingSectionPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_109_105);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testCreateOrderNoList() {
		try {
			int row = 18; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateCreateOrderOptionforNoListLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_309, testSenarios); // Row required you may change row
				page.validatecreateOrderOptionforNoList(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_309);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testCreateOrderList() {
		try {
			int row = 19; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateCreateOrderOptionforListLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_309, testSenarios); // Row required you may change row
				page.createOrderListOptionFromHomepage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_309);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	@Test(enabled = true)
	public void testTMNotification() {
		try {
			int row = 20; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateTMNoteNotification".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR47_51, testSenarios); // Row required you may change row
				page.tmNoteNotification(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR47_51);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testTMNoteRecmd() {
		try {
			int row = 21; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateRecommendedbyTMpage".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR47_52, testSenarios); // Row required you may change row
				page.recommendedbytmpage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR47_52);
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

