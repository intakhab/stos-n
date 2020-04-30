package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellAcceptSuggestionPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellAcceptSuggestionTest extends CommonElement {

	private static final String STORY_EC45_16 = "EC45-16 1. When user accepts a product that is on SL (and therefore does not need pending state), follow existing flow."
			+ "<p> 1a. If substitute product value is 0 and original product has a value when user clicks accept, Substitute product should take on original product's value."
			+ "</p>1b. If substitute product has a value more than 0 and original product has value of 0, Substitute product will retain the same value as it had before the accept. "
			+ "<p> 1c. If substitute product and original product have a value more than 0, Original product will be deleted off order and the substitute product will retain current value."
			+ "</p> 2. When user clicks on the accept suggestion checkbox on OG/MSL to accept the suggestion, the check box should remain checked and greyed out so that user cannot uncheck it."
			+ "<p> 2a. Upon checking the checkbox, the product pair information should be sent to the ECOM table. From the ECOM table, the data should then be sent to CCA page (EC45-61)."
			+ "</p> 3. When a user clicks accept suggestion on a pair that is on OG/MSL, A message should appear stating that the list is updating. See mockup for details."
			+ "<p> 3a. The message should say \"List is updating\" if the product is on any OG/MSL list. 3b. Pending Message appears on OG, MSL and on Suggested Sell when product pair is"
			+ " on an OG/MSL and has a pending state. "
			+ "</p>3c. Quantity logic for accepting a product on OG/MSL should be consistent with accepting a logic on SL only. "
			+ "<p>3ci. If substitute product value is 0 and original product has a value when user clicks accept, Substitute product should take on original product's value and "
			+ "original product has value of 0) 3cii. If substitute product has a value more than 0 and original product has value of 0, Substitute product will retain the same value as "
			+ "it had before the accept. "
			+ "</p>3ciii. If substitute product and original product have a value more than 0, Original product will have value of 0 and the substitute product will retain current value. "
			+ "<p>3d. If pending product pair is updated, original product is replaced by the suggested product and the yellow module disappears as per existing functionality."
			+ "</p>4. If the user accepts the suggested product, the product pair had been updated, and the original product is added back to the list with a suggested sell module, the "
			+ "<p>'Pending' treatment on the OG/MSL/Suggested Sell page should be displayed for 'X' days. If X days is greater than end date, then end date will take preference. "
			+ "</p>4a. X should be externalized. **product note, GL, inventory related fields will be transferred to the new product as per existing logic.<br/>";
	
	
	private static final String EC45_02="EC45_02 - 1. Display Suggested Sell Module on MSL when the original product is a part of these lists 1a. See mockup for UI Changes 1b.If user is restricted to MSL, both original and suggested product need to be on MSL to be displayed. 1bi. If user is unrestricted, suggested product does NOT need to be on MSL to be displayed. 2. User should be able to order the suggested product without accepting the suggestion. 2a. Add a quantity box to the yellow suggested sell module. 2b. When user fill the quantity box for suggested product with a number, suggested product should be updated with that number in the order. 2c. Existing quantity box lMSLic should apply for this quantity box. 3. If the pack size of the original product is different from the pack size of the suggested product, display red text \"Pack size is different.\" (The text that currently displays is \"Pack size is different. Adjust order quantity if needed.\") **Please note that adding the \"Accept Suggestion\" functionality and testing is in a separate story EC45-16 **	";

	
	
	private static final String ECR46_732 = "ECR46_732-Suggested sell notification caching";
	
	private SuggestedSellAcceptSuggestionPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellAcceptSuggestionPage.class);
	}

	
	@Test(enabled = false)
	public void testSuggestedSellProductAccept() {
		try {
			int row = 11; // SuggestedSellAcceptSuggestionTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellAcceptSuggestionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_16+EC45_02,testSenarios); // Row required you may change row as per your data
				page.valdiateSuggestedSellProductAccept(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_16+EC45_02);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/**
	 * ECR46-732
	 */
	@Test(enabled = true)
	public void testSuggestedSellAcceptAllProduct() {
		try {
			int row = 19; // SuggestedSellAllProductAcceptTest//DisplaySuggestedSell
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellAllProductAcceptTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,ECR46_732,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellAcceptAllProduct(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,ECR46_732);
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
