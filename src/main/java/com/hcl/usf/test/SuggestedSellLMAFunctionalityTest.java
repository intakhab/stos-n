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
public class SuggestedSellLMAFunctionalityTest extends CommonElement{
	
	
	private static final String EC46_386 = "EC46-386"
		+ "->1. Notifications \r\n" + 
			"    1a. With and without annual savings \r\n" + 
			"    1b. When only pending updates records are left \r\n" + 
			"2. Suggested product page \r\n" + 
			"    2a. Savings display/calculation with annual savings \r\n" + 
			"    2b. Accept product conversion and make sure lists were updated \r\n" + 
			"    2c. Decline product conversion and make sure product pair stops displaying \r\n" + 
			"3. Suggested product on list/OG/MSL \r\n" + 
			"    3a. Verify suggested product is being displayed properly \r\n" + 
			"    3b. Accept suggestion on list and verify lists got updated \r\n" + 
			"    3c. Accept suggestion on OG/MSL and verify pending update message is displaying \r\n" + 
			"4. Suggested product on Review Order/DYF \r\n" + 
			"    4a. Verify suggested product is being displayed properly. Suggested \r\n" + 
			"      product gets displayed even when original product is not on list \r\n" + 
			"     4b. Accept suggestion review order/DYF and verify list/order got \r\n" + 
			"      updated \r\n" + 
			"5. R2 direct \r\n" + 
			"     5a. GO to R2 direct user that has customer with suggested sell \r\n" + 
			"     5b. Verify suggested products page \r\n" + 
			"     5c. Accept suggestion and verify lists got updated \r\n" + 
			"6. CCA \r\n" + 
			"     6a. Verify SL Conversion report for above flows for R3 and R2 customer \r\n" + 
			"     6b. Verify OG Conversion report for above flows for R3 customer \r\n" + 
			"     6c. Verify MSL Conversion report for above flows for R3 customer \r\n" + 
			"7. Replace the old product with the new product on the OG \r\n" + 
			"    7a. Verify the OG page \r\n" + 
			"    7b. Verify the MSL page \r\n" + 
			"8. If no products are found on the List/OG/MSL, no products should be displayed as suggested sell except on: \r\n" + 
			"    8a. Review Order \r\n" + 
			"    8b. QoE \r\n" + 
			"    8c. PDP \r\n" + 
			"    8d. PC \r\n" + 
			"9. Products with same pack size \r\n" + 
			"    9a. Prodcuts with price \r\n" + 
			"    9b. No Price products \r\n" + 
			"10. Stat2 has sub and suggested products \r\n" + 
			"    10a. Restricted MSL behavior \r\n" + 
			"    10b. Unrestricted MSL behavior \r\n" + 
			"11. My Savings report under Our Exclusives <br/>";


	private static final String EC46_601 = "";
	

	private SuggestedSellLMAFunctionalityPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellLMAFunctionalityPage.class);
	}
	
	@Test(enabled = true)
	public void testSuggestedSellLMAFunctionality() {
		try {
			int row = 18; //SuggestedSellLMAFunctionalityTest //Excel Tab-DisplaySuggestedSell
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellLMAFunctionalityTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC46_386+EC46_601,testSenarios); // Row required you may change row as per your data
				page.validateSuggestedSellLMAFunctionality(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC46_386+EC46_601);
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
