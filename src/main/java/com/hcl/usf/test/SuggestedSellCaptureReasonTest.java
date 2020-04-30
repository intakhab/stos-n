package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellCaptureReasonPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * Change Promotions link "Get Details" to say "Access Promotions"
 * EC45-45 	
 * @author intakhabalam.s@hcl.com
 *
 */
public class SuggestedSellCaptureReasonTest extends CommonElement {

	private SuggestedSellCaptureReasonPage page = null;
    private final String EC45_45="EC45-45 - Suggested Sell - Capture Reason of Decline for Suggested Sell (POP UP)</p>"
    		+ "<p>1. On Suggested Sell Page, when user clicks on \"Thanks, I'll keep this.\","
    		+ "a pop up modal should appear prompting the user to choose from a predefined list of reasons for declining the suggested product."
    		+ "<p>1a. predefined reasons are shown on radio buttons. 1ai. Radio buttons should come unchecked. 1b. modal should be able to be closed without having to choose a reason.</p> <p> 2. Users should be able to pick from a predefined list of reasons for declining the product\"</p></p> 2a. These reasons must be externalized to be changed.";
    
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellCaptureReasonPage.class);
	}
	
	@Test(enabled = true)
	public void testCaptureReasonPage() {
		try {
			int row = 2;//SuggestedSellCaptureReasonTest;
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellCaptureReasonTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_45,testSenarios); // Row required you may change row as per your data
				page.validateCaptureReasonPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_45);
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

