package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellQuickOrderEntryPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellQuickOrderEntryTest extends CommonElement {

	private static final String STORY_EC45_396 = "EC45-396 1. Expand Suggested Sell Logic on QOE to include products on OG/MSL that have a suggested sell product. "
			+ "1a. If a product entered on QOE has a suggested sell product attached to it, display "
			+ "suggested sell product regardless of whether that product is on SL/OG/MSL.";
	
	private SuggestedSellQuickOrderEntryPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuickOrderEntryPage.class);
	}

	
	@Test(enabled = true)
	public void testSuggestedSellProductQOE() {
		try {
			int row = 13; // SuggestedSellQuickOrderEntryTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellQuickOrderEntryTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_396,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellProductQOE(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_396);
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
