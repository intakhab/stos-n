package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.springframework.core.annotation.Order;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.EndUSFSssionUserTransfersCartPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class EndUSFSssionUserTransfersCartTest extends CommonElement {

	private static final String EC45_38_1 = "EC45-38 1. End the USF session when user transfers cart. EC45-38-TC01"
			+ "	  TC01:Pre-requisite: Login to BuyEfficient Validate in review order page when"
			+ "	  user clicks on Transfer cart and user relaunch Buyefficient then old session"
			+ "	  should end and user should land on home page in lab Manual PASS";
	private static final String EC45_38_2 = "EC45-38-TC02 TC02:Pre-requisite: Login to Birchstreet Validate in review"
			+ "	  order page when user clicks on Transfer cart and user relaunch Birchstreet"
			+ "	  then old session should end and user should land on home page in lab Manual" + "	  PASS";
	private static final String EC45_38_3 = "EC45-38-TC03 TC03:Pre-requisite: Login to Brookdale Validate in review order"
			+ "	 page when user clicks on Transfer cart and user relaunch Brookdale then old"
			+ "	 session should end and user should land on home page in lab";
	private EndUSFSssionUserTransfersCartPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, EndUSFSssionUserTransfersCartPage.class);
	}

	@Order(value = 1)
	@Test(enabled = true)
	public void sessionCheckFromBirchStreet() {
		try {
			int row = 1;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("BirchStreetTest".equalsIgnoreCase(testSenarios) && findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_38_1, testSenarios); // Row required you may change row
																						// as per your data
				page.validateUsfSessionCheckFromBirchStreet(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_38_1);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOut();
		}
	}

	@Order(value = 2)
	@Test(enabled = true)
	public void sessionCheckFromBrookdale() {
		try {
			int row = 2;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("BrookDaleTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_38_2, testSenarios); // Row required you may change row
																						// as per your data
				page.validateUsfSessionCheckFromBrookdale(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_38_2);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Order(value = 3)
	@Test(enabled = true)
	public void sessionCheckFromIBuyEfficent() {
		try {
			int row = 3;
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("IBuyEfficientTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45_38_3, testSenarios); // Row required you may change row
																						// as per your data
				page.validateUsfSessionCheckFromIBuyEfficent(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_38_3);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOut();
		}
	}

	@AfterTest
	public void afterTest() {
		page = null;
	}

}
