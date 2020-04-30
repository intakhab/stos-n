package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.ForceListUpdatePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class ForeceListUpdateTest  extends CommonElement{
	
private static final String ECR46_403 = "1. Forced update\r\n" + 
		"      1a. LMA API for converted customers\r\n" + 
		"      1b. EJB for not converted customers";
	

	private ForceListUpdatePage flp= null;

	@BeforeMethod
	public void beforeMethod() {
		flp = PageFactory.initElements(driver, ForceListUpdatePage.class);
	}

	@Test(enabled = true)
	public void testForceListUpdateForLMACustomer() {
		try {
			int row = 1; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateForceListNotificationForLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_403, testSenarios); // Row required you may change row
				flp.validateForceListUpdateLMACustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_403);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testForceListUpdateForNonLMACustomer() {
		try {
			int row = 2; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateForceListNotificationForNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_403, testSenarios); // Row required you may change row
				flp.validateForceListUpdateNonLMACustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_403);
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
		flp = null;
	}
	

}
