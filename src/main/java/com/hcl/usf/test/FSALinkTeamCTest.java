package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.DeepLinkPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class FSALinkTeamCTest extends CommonElement {
	
	private static final String EC45C_53 = "1. On Ecom login page add text and link 'Welcome FSA/SSA customers. Please click here to access your ordering system'where click here should be a clickable link"
			+ "1a. The text should be externalized so that it can be modified without a release"
			+ "1b. There should be a easy way to turn off the text"
			+ "1c. Clicking the link should navigate user to 'https://www.usfoods.com/our-services/easy-ordering/fsa-toolbox.html' in the same tab";

	private DeepLinkPage deeplink = null;

	@BeforeMethod
	public void beforeMethod() {
		deeplink = PageFactory.initElements(driver, DeepLinkPage.class);
	}

	@Test(enabled = true)
	public void testFSALink() {
		try {
			int row = 1;
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateFSALink".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45C_53, testSenarios);
				deeplink.validateFSALink();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, EC45C_53);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			driver.close();
		}
	}

}
