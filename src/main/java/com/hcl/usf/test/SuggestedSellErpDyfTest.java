package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellErpDyfPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellErpDyfTest extends CommonElement {
    /***
     */
	private static final String STORY_EC45_64 = "EC45_64 1. Display Suggested Sell Module on DYF/EPR, when the original product is a part of any list and appears on DYF or EPR. "
			+ "1a. See mockup for UI Changes 2. User should be able to order the suggested product without accepting the suggestion."
			+ "2a. Add a quantity box to the yellow suggested sell module."
			+ "3. When user fill the quantity box for suggested product with a number, suggested product should be updated with that number in the order."
			+ "3a. For Did You Forget and EPR/EDLP, if user has either the original product (P#) or Suggested Product (S#) in the current order, do not display product pair in DYF/EPR/EDLP. "
			+ "4. If the pack size of the original product is different from the pack size of the suggested product, display red text \"Pack size is different.\" (The text that currently displays is \"Pack size is different. Adjust order quantity if needed.\")"
			+ " **Please note that adding the \"Accept Suggestion\" functionality and testing is in a separate story EC45-180. This scenario only deals with Scenarios 1-4 on the flows **";
	         
	private static final String STORY_EC45_180="<p></p>1. If user accepts the suggested product via checking the checkbox on DYF/EPR page, the suggested sell yellow module should disappear and the original product (P#) should be replaced with Suggested Product (S#) as per current functionality. \r\n" + 
			"1a. If user had a quantity value in the original product (P#), the suggested product (S#) should inherit that same quantity value if the quantity value in S# is 0, as per existing functionality. \r\n" + 
			"1b. If user had a quantity value of 0 in the original product (P#) and the suggested product (S#) had a quantity value, S# should retain it's current value as it replaces P#. \r\n" + 
			"1c. If user had a quantity value (Q1) in the original product (P#) and the suggested product (S#) had a quantity value (Q2), P# should be deleted from the order and S# should retain its same quantity value (Q2). \r\n" + 
			"\r\n" + 
			"2. If user navigates to a different page (Review Order, etc) from DYF/EPR and navigates back to DYF/EPR page with either P# or S# in the order, the product pair should not show. \r\n" + 
			"2a. If user has S# in order with a quantity value but not P#, the DYF/EPR page should not display product pair. \r\n" + 
			"2b. If user has P# in order with a quantity value but not S#, the DYF/EPR page should not display product pair \r\n" + 
			"2a. If user has P# and S# in order with quantity values in each, the DYF/EPR page should not display product pair \r\n" + 
			"\r\n" + 
			"3. If user has P# or S# in the current order and navigates to DYF/EPR, suggested sell pair should no longer display in DYF/EPR as per existing functionality, and banner text should be updated ( EC45-270 and EC45-271 ) \r\n" + 
			"3a. If user has all products P#, all products S# or a combination of both P# and S#, in DYF/EPR in their current order, do not display the DYF/EPR banner. \r\n" + 
			"";
	private SuggestedSellErpDyfPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellErpDyfPage.class);
	}

	@Test(enabled = true)
	public void testSuggestedSellErpDyf() {
		try {
			int row = 14;// SuggestedSellErpDyfTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellErpDyfTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_64+STORY_EC45_180,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellErpDyf(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_64+STORY_EC45_180);
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
