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
public class SuggestedSellWithAndWithoutSuggestionTest extends CommonElement {

	private static final String STORY_EC45_30 = "<p>EC45-30 1. Display Suggested Sell on Suggested Sell Page when user has any sort of suggested sell (list, OG, or MSL) "
			+ "</p><p>1a. See mockup for UI Changes."
			+ " 2. User should be able to order the suggested product without accepting the suggestion. "
			+ "</p><p>2a. Add a quantity box to the yellow suggested sell module. "
			+ " 2b. When user fill the quantity box for suggested product with a number, suggested product should "
			+ "be updated with that number in the order. "
			+ "<p>2c. Existing quantity box logic should apply for this quantity box. "
			+ "</p>"
			+ "<p>2d. If user uses quantity box without checking accept suggestion, suggested pair should still remain. Original Product will not be replaced. "
			+ "</p>"
			+ "3. Only if the user clicks \"Great! Switch to Suggested Product.\", should the user have the original product replaced in the list. "
			+ "</p>"
			+ "<p>4. If the pack size of the original product is different from the pack size of the suggested product, display red text \"Pack size is different.\""
			+ " (The text that currently displays is \"Pack size is different."
			+ " Adjust order quantity if needed.\")<p>";
	
	
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

   //User Story-30
	@Test(enabled = true)
	public void testSuggestedProductWithAndWithoutSuggestion() {
		try {
			// Row required you may change row as per your data
			int row = 5; // SuggestedSellWithAndWithoutSuggestionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellWithAndWithoutSuggestionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_30,testSenarios); 
				page.validateSuggestedProductQntySizePack(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_30);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOut();
		}
	}
    //
	
	
	@AfterTest
	public void afterTest() {
		page = null;
	}

}
