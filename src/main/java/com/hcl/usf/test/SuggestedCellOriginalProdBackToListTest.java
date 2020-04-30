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
public class SuggestedCellOriginalProdBackToListTest extends CommonElement {

	
	private static final String STORY_EC45_312="EC45-312 Suggested Sell - Show yellow module after adding product to list";
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

    //
	//STORY_EC45_312
	@Test(enabled = true)
	public void testSuggCellOriginalProdBackToList() {
		try {
			int row = 7; // SuggestedCellOriginalProdBackToListTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedCellOriginalProdBackToListTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_312,testSenarios); // Row required you may change row as per your data
				page.validateOriginalProdBacktoList(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_312);
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
