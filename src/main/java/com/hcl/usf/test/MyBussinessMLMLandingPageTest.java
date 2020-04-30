package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.TextVerifyCommonChangePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class MyBussinessMLMLandingPageTest extends CommonElement {
	
	private static final String EC45_501 = "EC45-501 - 1. Change tab text when on MLM L2 landing page to \"US Foods | Master List Management\" ";
	
	private TextVerifyCommonChangePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, TextVerifyCommonChangePage.class);
	}

	@Test(enabled = true)
	public void testBussinessMLMLandingPage() {
		try {
			int row = 10;//MyBussinessMLMLandingPageTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("MyBussinessMLMLandingPageTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_501,testSenarios); // Row required you may change row as per your data
				page.validateBussinessMLMLandingPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_501);
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

