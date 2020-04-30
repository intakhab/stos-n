package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.BlueprintBusinessSolutionsPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class BlueprintBusinessSolutionsTest extends CommonElement {

	private static final String EC45_18 = "EC45-18 - Link Changes - Blueprint/Business Solutions Acceptance Criteria :\r\n" + 
			" 1. On Our Exclusives Page, under Business Solutions, rename \"Get Details\" to\r\n" + 
			" \"Learn More\" 1a. Learn More should link to\r\n" + 
			" https://www.usfoods.com/our-services/national-support/business-solutions.html\r\n" + 
			"  2. On Healthcare Menus & Recipes Page under the BluePrint sections (Our\r\n" + 
			" Exlusives>Healthcare Menus and Recipes), add \"Login to BluePrint System\" link\r\n" + 
			" after the \"Learn More\" link. 2a. Login to BluePrint System should link to\r\n" + 
			" https://blueprint.cbord.com/usfoods/\r\n" + 
			"";
	private BlueprintBusinessSolutionsPage page = null;
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, BlueprintBusinessSolutionsPage.class);
	}
	
	@Test(enabled = true)
	public void testBlueprintBusinessSolutions() {
		try {
			int row = 4;//BlueprintBusinessSolutionsTest 
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("BlueprintBusinessSolutionsTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_18,testSenarios); // Row required you may change row as per your data
				page.validateBlueprintBusinessSolutions(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_18);
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

