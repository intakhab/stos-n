package com.hcl.usf.test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellQuantityPackPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellOrderFlowWithOGMslTest extends CommonElement {

	private static final String STORY_EC45_43 = "EC45-43 1. Display Suggested Sell on Suggested Sell Page when user has any sort of suggested sell (OG, MSL)"
			+ " 1a. See mockup for UI Changes. 2. User should be able to order the suggested product without accepting the suggestion."
			+ " 2a. Add a quantity box to the yellow suggested sell module. "
			+ " 2b. When user fill the quantity box for suggested product with a number, suggested product should be updated with that number in the order. "
			+ " 2c. Existing quantity box logic should apply for this quantity box. "
			+ "	2d. If user uses quantity box without checking accept suggestion, suggested pair should still remain. Original Product will not be replaced."
			+ " 3. Only if the user clicks \"Great! Switch to Suggested Product.\", should the user have the original product replaced in the shopping list."
			+ " For OG/MSL a pending treatment will be added in story EC45-16 ."
			+ " 4. If the pack size of the original product is different from the pack size of the"
			+ " suggested product, display red text \"Pack size is different.\" (The text that currently displays is \"Pack size is different. Adjust order quantity if needed.\")"
			+ " **Please note that adding the \"Accept Suggestion\" functionality and testing is in a separate story EC45-16 ** ";
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

	
	@Test(enabled = true)
	public void testOrderFlowWithOgMsl() {
		try {
			int row = 9; // SuggestedSellOrderFlowWithOgMslTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellOrderFlowWithOGMslTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_43,testSenarios); // Row required you may change row as per your data
				page.valdiateOrderFlowWithOgMsl(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_43);
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
