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
public class ErpDyfTextBannerTest extends CommonElement {
    /***
     * @TODO next
     */
	private static final String STORY_EC45_270_271 = "EC45_270 "
			+ "1. Number of products shown on DYF Banner should show the correct amount of products on DYF "
			+ "1a. If original product or suggested product is in the current order, "
			+ "this should not be included in the total count of products in DYF because it should not be shown in DYF if it is in current order."
			+ " Note: Consumption of new DYF API"
			+"<br/><hr>"
			+ "EC45_271 1. Number of products shown on EPR Banner should show the correct amount of products on EPR "
			+ " 1a.If original product or suggested product is in the current order, this should not be included in the"
			+ " total count of products in EPR because it should not be shown in EPR if it is in current order. Note: COnsumption of EPR API";
	         
	private SuggestedSellErpDyfPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellErpDyfPage.class);
	}

	@Test(enabled = true)
	public void testErpDyfBanner() {
		try {
			int row = 9; // ErpDyfTextBannerTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ErpDyfTextBannerTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_270_271,testSenarios); // Row required you may change row as per your data
				page.validateTestErpDyfBanner(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_270_271);
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
