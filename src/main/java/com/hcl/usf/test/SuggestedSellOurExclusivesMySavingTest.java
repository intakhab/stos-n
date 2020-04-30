package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellOurExclusivesPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class SuggestedSellOurExclusivesMySavingTest extends CommonElement {

	private static final String EC45_07 = "EC45-07 - 1. User should be able to view their savings based on their suggested sell conversions over a period of time."
			+ " This would be a new page located under My Exclusives. See Mockup. 1a. Display Our Exclusives rail on the right side of the page."
			+ " All links should work as per existing functionality. 1b. Page must display all the "
			+ "original and its \"accepted product\" product for the dates that were chosen between the dates selected in the filter by."
			+ "1bi. Page must display the reason code messaging for the \"accepted product\" as shown in the mockup. "
			+ "1c. Page must display the total annual savings. "
			+ "1d. Display disclaimer under Filter By Date."
			+ "1e. Display disclaimer about est. Annual Savings under the Suggested Sell "
			+ "2. Ecom would need to track savings by accepting the suggestion per product pair at annual savings "
			+ "2a. Estimated Annual Savings will be same calculation as in CCA savings report. "
			+ "3. User should be able to choose a timeframe to view products purchased within time frame."
			+ " (Example - if the user accepted a suggested product A on 03/01/2019 and the filter by date is between 02/20/2019 and 03/20/2019, the product pair should display on page. "
			+ "If the filter by date is between 04/10/2019 - 07/27/2019, suggested product A pair should not show) "
			+ "3a. Default of the time frame should be Year to Date. 3b. Start Date can go back 1 year from today. However, end date can only go up to 6 months after start date."
			+ " Rest of dates should be greyed out. (ex. If today is December 1 2019, user can change start date to December 1 2018, but their end date can only go up to June 1 2018.) "
			+ "4. When My Savings tab is clicked in the Our Exclusives Dropdown, they should be able to navigate to this My Savings Page. "
			+ "5. When \"Access My Savings\" is clicked on the Our Exclusives Page in the My Savings section, user should be navigated to this My Savings Page"
			+ "6. When My Savings tab is clicked on the Our Exclusives Left Navigation Rail from other Our Exclusives Pages (Promotions, Suggested Product Updates, etc), "
			+ "user should be navigated to this My Savings Page."
			+ "7. After the display of the savings, the text on the bottom of the page where the Latin is on the mockup should say: "
			+ "\"The annualized estimated savings is calculated as follows: "
			+ "a) Savings per case, serving or pound x annual usage of the original product "
			+ "b) Suggested products that do not save money are not included in the calculation.\" See mockup.";
	
	private SuggestedSellOurExclusivesPage page = null;
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellOurExclusivesPage.class);
	}
	
	@Test(enabled = true)
	public void testAccessMySavingLink() {
		try {
			int row = 10;//SuggestedSellOurExclusivesMySavingTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellOurExclusivesMySavingTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_07,testSenarios); // Row required you may change row as per your data
				page.validateAccessMySavingLink(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_07);
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
