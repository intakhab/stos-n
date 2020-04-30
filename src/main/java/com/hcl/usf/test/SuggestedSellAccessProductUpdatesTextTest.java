package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellOurExclusivesPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class SuggestedSellAccessProductUpdatesTextTest extends CommonElement {

	private static final String EC45_108 = "EC45-108 - 1.Update Suggested Product Update Paragraph Text to read: "
			+ " \"Suggested Products can help you find products that support your purchasing goals."
			+ " You'll find options for saving money, switching to higher quality products or products with a cleaner label.\""
			+ " 2. Update Get Details link to read \"Access Suggested Product Updates\"";
	
	private SuggestedSellOurExclusivesPage page = null;
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellOurExclusivesPage.class);
	}
	
	@Test(enabled = true)
	public void accessSuggestedProductUpdates () {
		try {
			int row = 7;//SuggestedSellAccessProductUpdatesTextTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellAccessProductUpdatesTextTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_108,testSenarios); // Row required you may change row as per your data
				page.validateAccessSuggestedProductUpdates(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_108);
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
