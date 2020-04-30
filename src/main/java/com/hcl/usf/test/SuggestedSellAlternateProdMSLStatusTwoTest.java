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
public class SuggestedSellAlternateProdMSLStatusTwoTest extends CommonElement {

	private static final String STORY_EC45_509 = "EC45-509 - "
			+ "1. On unrestricted MSL when a primary product is status 2 and has alternate products by default "
			+ "the alternate products will be collapsed and user will see substitute (when available) "
			+ "below the primary product \r\n" + 
			"1a. When user selects the \"Show\" link for alternates the alternate products should "
			+ "expanded and displayed below the substitute \r\n" + 
			"\r\n" + 
			"2. On unrestricted MSL when a primary product has suggested product and has alternate "
			+ "products by default the alternate products will be collapsed and user will see suggested product"
			+ " below the primary product \r\n" + 
			"1a. When user selects the \"Show\" link for alternates the alternate products "
			+ "should expanded and displayed below the suggested product <br/>";
	
	
	private static final String STORY_EC46_762="EC46-762 - On all custom list, OG and search pages when displaying a status 2 products where user is allowed to see stat2 experience do the following to determine substitute to be displayed "
			+ "1. Check if customer has MSL from MLM 1a. Display sub provided by MLM if stat2 product is on any of the MSL's. "
			+ "1ai. MLM is doing following logic to provide sub. When secondary product is available send secondary product as sub. When there is no secondary product and customer is restricted to MSL, check division sub flag and send division sub if division sub flag is yes."
			+ " 2. Check if customer has MSL from COGM and no MSL from MLM then do the following"
			+ " 2a. Check if stat 2 product is on COGM MSL and if there is a secondary product available. "
			+ "The secondary product should be displayed as sub. "
			+ "2b. If there is not secondary product and customer is restricted to MSL then check division sub flag."
			+ " If division sub flag is yes then display division sub "
			+ "1. If division sub flag is no then do not display sub "
			+ "2c. If there is no secondary product and customer is not restricted to MSL then display division sub "
			+ "3. If customer does not have MSL then display division sub"
			+ " 4. This logic already exists on MSL page and should continue to work	"
			+ "";
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

	
	@Test(enabled = true)
	public void testSuggestedSellAlternateProdMSLStatusTwo() {
		try {
			int row = 15; // SuggestedSellAlternateProdMSLStatusTwoTest//DisplaySuggestedSell
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellAlternateProdMSLStatusTwoTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_509+STORY_EC46_762,testSenarios); // Row required you may change row as per your data
				page.validateSuggAlternateProdMSLStatusTwo(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_509);
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
