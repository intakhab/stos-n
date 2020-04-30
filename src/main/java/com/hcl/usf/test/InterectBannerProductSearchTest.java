package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.ProductSearchPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class InterectBannerProductSearchTest extends CommonElement {

	private static final String STORY_EC45_42 = "EC45_42 1. There will be a new Interact banner on ECOM screens</p>"
			+ " <p>1a. This new Interact banner will follow current logic on Interact banner placement on ECOM</p>"
			+ "<p> 1b. Once a user clicks this new Interact banner, they will be led to the Search page.</p>"
			+ "<p> 1c. The link on this banner must contain a key word that is given to us by marketing</p>"
			+ "<p> 1ci. This key word will be the key word that we"
			+ " use on the search page that the user is directed to.";
	
	private ProductSearchPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, ProductSearchPage.class);
	}

	@Test(enabled = true)
	public void testInteractPageSearch() {
		try {
			int row = 2; // InterectBannerProductSearchTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("InterectBannerProductSearchTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_42,testSenarios); // Row required you may change row as per your data
				page.validateInteractPageSearch(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_42);
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
