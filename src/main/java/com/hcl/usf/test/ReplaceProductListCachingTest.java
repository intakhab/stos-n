package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellLMAFunctionalityPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class ReplaceProductListCachingTest extends CommonElement{
	
	private static final String EC46_729 = "EC46_729 Replace a product on list caching <br/>";
	private static final String EC46_730 = "EC46_730 Replace a product on multiple lists caching <br/>";
	private static final String EC46_731 = "EC46_731 Accept suggested sell on suggested sell page caching";

	private SuggestedSellLMAFunctionalityPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellLMAFunctionalityPage.class);
	}
	
	@Test(enabled = true)
	public void testReplaceProductListCachingTest() {
		try {
			int row = 20; //ReplaceProductListCachingTest //Excel Tab-DisplaySuggestedSell
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ReplaceProductListCachingTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC46_729+EC46_730+EC46_731,testSenarios); // Row required you may change row as per your data
				page.validateReplaceProductListCachingTest(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC46_729+EC46_730+EC46_731);
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
