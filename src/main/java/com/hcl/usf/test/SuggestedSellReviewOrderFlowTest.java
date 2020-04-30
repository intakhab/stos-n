package com.hcl.usf.test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellReviewOrderFlowPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;


public class SuggestedSellReviewOrderFlowTest extends CommonElement{
	
	
	private static final String EC45_311 = "EC45-311 1. If user accepts on suggested sell and goes to review order, the yellow module needs to be shown for "
			+ "the old product if it is in the current order."
			+ " 1a. This suggested sell module treatment should reflect on all flows within review order (i.e. navigating to PDP, Product Compare, QOE).";
	

	private SuggestedSellReviewOrderFlowPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellReviewOrderFlowPage.class);
	}
	
	@Test(enabled = true)
	public void testSuggestedSellReviewOrderFlowTest() {
		try {
			int row = 8; //SuggesetSellReviewOrderFlowTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggesetSellReviewOrderFlowTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_311,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellReviewOrderFlow(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_311);
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
