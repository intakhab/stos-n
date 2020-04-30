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
public class SuggestedSellQuantityPackTest extends CommonElement {

	private static final String STORY_EC45_14 = "EC45-14 - List Page - Display Suggested Sell - Quantity & packsize\r\n" + 
			"Acceptance Criteria : \r\n" + 
			"1. Display Suggested Sell Module on list when the original product is a part of these lists \r\n" + 
			"1a. See mockup for UI Changes. \r\n" + 
			"\r\n" + 
			" 2. User should be able to order the suggested product without accepting the suggestion. \r\n" + 
			" 2a. Add a quantity box to the yellow suggested sell module. \r\n" + 
			" 2b. When user fill the quantity box for suggested product with a number, suggested product should be updated with that number in the order. \r\n" + 
			" 2c. Existing quantity box logic should apply for this quantity box. \r\n" + 
			" 2d. If user uses quantity box without checking accept suggestion, suggested pair should still remain. Original Product will not be replaced. \r\n" + 
			"\r\n" + 
			" 3. Only if the user checks \"Accept Suggestion\", should the user have the original product replaced in the list. \r\n" + 
			"\r\n" + 
			"4. If the pack size of the original product is different from the pack size of the suggested product, display red text \"Pack size is different.\" (The text that currently displays is \"Pack size is different. Adjust order quantity if needed.\")\r\n" + 
			"";
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

	
	@Test(enabled = true)
	public void testSuggestedCellQntySizePack() {
		try {
			int row = 1; // SuggestedSellQuantityPackTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellQuantityPackTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_14,testSenarios); // Row required you may change row as per your data
				page.valdiateDisplayQuantitySizePack(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_14);
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
