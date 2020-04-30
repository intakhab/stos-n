package com.hcl.usf.test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellUpdateCaseCalculationPackSizePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellUpdateCaseCalculationPackSizeTest extends CommonElement {

	private static final String STORY_EC45_10 = "EC45-10 - Update calculation to look at pack size. "
			+ "If pack size is same do not use case net weight, this should be a 1:1 calculation."
			+ "Verify there is a 1:1 conversion for products that have same packsize on all pages where a suggested sell yellow module is shown. The savings is shown on the Yellow Module text on the following pages: Product Compare, SL, MSL, OG, Suggested Sell Page, Review Order, Review Order Summary, DYF, and EDLP.\r\n" + 
			"\r\n" + 
			"This conversion is also shown on the blue banner on PDP page of the original product.\r\n" + 
			"";
	
	
	private SuggestedSellUpdateCaseCalculationPackSizePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellUpdateCaseCalculationPackSizePage.class);
	}

	
	@Test(enabled = true)
	public void testSuggestedSellUpdateCaseCalculationPackSize() {
		try {
			int row = 6; // SuggestedSellUpdateCaseCalculationPackSizeTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellUpdateCaseCalculationPackSizeTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_10,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellUpdateCaseCalculationPackSize(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_10);
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
