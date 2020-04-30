package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellProductComparePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellPDPBlueLinkHeaderTest extends CommonElement {

	
	private static final String STORY_EC45_65 = "EC45-65 1. Display the blue Suggested Sell Banner at the top of the PDP Page when there"
			+ " is a suggested product attached to the original product, whether its located on the SL (existing functionality)/ OG (new) / MSL (new)."
			+ " 1a. Hitting the product compare button will navigate to the product compare page as per existing functionality";
	private SuggestedSellProductComparePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellProductComparePage.class);
	}
	
	@Test(enabled = true)
	public void testSuggestedSellPDPBlueLinkHeader() {
		try {
			int row = 4; // SuggestedSellPDPBlueLinkHeaderTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellPDPBlueLinkHeaderTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_65,testSenarios); // Row required you may change row as per your data
				page.valdiateSuggestedSellPDPBlueLinkHeader(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_65);
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
