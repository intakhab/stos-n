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
public class SuggestedSellProductCompareTest extends CommonElement {

	private static final String STORY_EC45_66 = "EC45-66 -1. When user clicks on Compare for a product pair located in the SL/OG/MSL,"
			+ " the user should be navigated to the suggested sell"
			+ "compare for those two products. 1a. Products should be shown as per existing functionality.</p>"
			+ "<p>2. If a user has not accepted the suggested products for OG/MSL original product and the user clicks compare, the accept button should be visible.</p>"
			+ "<p>2a. if user clicks on accept on the Product Compare Page, The user should be navigated to the page prior as per existing functionality for products on SL.</p>"
			+ "<p>2ai.The prior page will have \"Pending\" treatment for original products on the OG/MSL.</p> "
			+ "<p>2aii. If there was a value in the suggested product order quantity, order quantity should be retained.</p>"
			+ "<p>	2aiii. All product note, GL, inventory related fields will be transferred from the original product to the new product as per existing logic.</p> "
			+ "<p>3. If user clicks accept on product compare for any product pair, the following would occur in terms of quantity logic.</p> "
			+ "<p> 3ai. If substitute product value is 0 and original product has a value when user clicks accept, Substitute product should take on original product's value and original product has value of 0).</p>"
			+ "<p> 3aii.If substitute product has a value more than 0 and original product has value of 0, Substitute product will retain the same value as it had before the accept.</p> "
			+ "<p> 3aiii. If substitute product and original product have a value more than 0, Original product will have value of 0 and the substitute product will retain current value.</p>"
			+ "<p> 3aiv. If pending product pair (OG/MSL) is updated or a regular list is updated, original product is replaced by the suggested product the previous page and the yellow module disappears on the existing page as per existing functionality. </p>"
			+ "<p>4. If a user comes from a pending change on OG/MSL (original product is on OG/MSL) and they clicked compare link, the Accept button on the Product Compare page is greyed out.</p>";
	
	
	private SuggestedSellProductComparePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellProductComparePage.class);
	}

	
	@Test(enabled = true)
	public void testSuggestedSellProductCompare() {
		try {
			int row = 3; // SuggestedSellProductCompareTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellProductCompareTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_66,testSenarios); // Row required you may change row as per your data
				page.valdiateSuggestedSellProductCompare(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_66);
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
