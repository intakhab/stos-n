package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.OurExclusivesDropdownMySavingLinkPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class OurExclusivesDropdownMySavingLinkTest extends CommonElement {

	private static final String EC45_19 = "EC45_19 1. Our Exclusives Dropdown should have Promotions Summary Page added."
			+ " 1a. My Savings should be shown on the Our Exclusives dropdown."
			+ " 1b. The order of the dropdown should be as below. Promotions should have second priority after Recommendations. o Recommendations o Promotions o My Kitchen o Scoop o Suggested Product Updates o My Savings o Check Business Tools o Business Solutions o Healthcare Menu & Recipes o Serve Good 2.Our Exclusives Landing page, should have a section for My Savings page. "
			+ " 2a. Title should be \"My Savings\" "
			+ " 2b. Paragraph is \"Track the conversions you’ve made to quality products. My Savings gives you a comprehensive, in-depth view into the money you’ve saved with US Foods.\" "
			+ " 2c. the \"Access My Savings\" link should send user to My Savings page. "
			+ " 3. When on MyKitchen/Recommendations/Scoop//Value Added Services/Healthcare Menu & Recipes Pages and user clicks on My Savings on the left tab, the user should be directed to the My Savings page. 3a. My Savings should be shown in the left tab. ";
	
	private OurExclusivesDropdownMySavingLinkPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, OurExclusivesDropdownMySavingLinkPage.class);
	}

	@Test(enabled = true)
	public void testMySavingLinText() {
		try {
			int row = 5; //OurExclusivesDropdownMySavingLinkTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("OurExclusivesDropdownMySavingLinkTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_19,testSenarios); // Row required you may change row as per your data
				page.valdiatemySavingLinText(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_19);
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
