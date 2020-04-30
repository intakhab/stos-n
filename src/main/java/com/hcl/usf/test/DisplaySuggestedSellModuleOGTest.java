package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.DisplaySuggestedSellModuleOGPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class DisplaySuggestedSellModuleOGTest extends CommonElement {

	private DisplaySuggestedSellModuleOGPage page = null;
     private String EC45_1="EC45-1 "
     		+ "	 1. Display Suggested Sell Module on OG when the original product is a\r\n" + 
     		"	  part of these lists 1a. See mockup for UI Changes 1b.If user is restricted to\r\n" + 
     		"	  OG, both original and suggested product need to be on OG to be displayed.\r\n" + 
     		"	  1bi. If user is unrestricted, suggested product does NOT need to be on OG to\r\n" + 
     		"	  be displayed. 2. User should be able to order the suggested product without\r\n" + 
     		"	  accepting the suggestion. 2a. Add a quantity box to the yellow suggested sell\r\n" + 
     		"	  module. 2b. When user fill the quantity box for suggested product with a\r\n" + 
     		"	  number, suggested product should be updated with that number in the order.\r\n" + 
     		"	  2c. Existing quantity box logic should apply for this quantity box. 3. If the\r\n" + 
     		"	  pack size of the original product is different from the pack size of the\r\n" + 
     		"	  suggested product, display red text \"Pack size is different.\" (The text that\r\n" + 
     		"	  currently displays is \"Pack size is different. Adjust order quantity if\r\n" + 
     		"	  needed.\") **Please note that adding the \"Accept Suggestion\" functionality and\r\n" + 
     		"	  testing is in a separate story EC45-16 ";
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, DisplaySuggestedSellModuleOGPage.class);
	}

	/***
	 */
	@Test(enabled = true)
	public void testSuggestedOG() {
		try {
			int row = 2;//DisplaySuggestedSellModuleOGTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("DisplaySuggestedSellModuleOGTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_1,testSenarios); // Row required you may change row as per your data
				commonInfoLog(testSenarios + " scenario is running");
				page.validateSuggestedOG(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_1);
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
