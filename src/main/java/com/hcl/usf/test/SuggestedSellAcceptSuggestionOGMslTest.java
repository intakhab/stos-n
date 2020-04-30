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
public class SuggestedSellAcceptSuggestionOGMslTest extends CommonElement {

	private static final String STORY_EC45_416 = "1. When user selects \"Accept\" for a suggested pair on OG/MSL they should be displayed the same \"Accept suggestion\" "
			+ "pop-up that comes up on list page. (screenshot attached)"
			+ "2. Options on the pop-up will be same as list i.e. 1. Accept on current list or "
			+ "2. Accept on all lists "
			+ "3. Update note text for pop-up. Change applies to all page where the pop-up is displayed. "
			+ " New text should be \"Please note: Changes made to Order Guides and Master "
			+ "Shopping Lists will take longer to update. "
			+ "If you have specific questions about your lists, please contact customer service or your sales representative.\"";
	
	private SuggestedSellAcceptSuggestionPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellAcceptSuggestionPage.class);
	}
    
	@Test(enabled = true)
	public void testSuggestedSellProductAcceptOGMsl() {
		try {
			int row = 12; // SuggestedSellAcceptSuggestionOGMslTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellAcceptSuggestionOGMslTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_416,testSenarios); // Row required you may change row as per your data
				page.valdiateSuggestedSellProductAcceptOGMSL(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_416);
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
