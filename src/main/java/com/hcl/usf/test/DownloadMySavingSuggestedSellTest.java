package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellOurExclusivesPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class DownloadMySavingSuggestedSellTest extends CommonElement {

	private static final String EC45_20_619 = "EC45-20 - 1 1. User must be able to download My Savings page into a CSV file. 1a. Use Existing Download flow Download -"
			+ " CSV 2. Download will include: -"
			+ " Division - Customer # - Start Date - End Date - Total Annual Savings - Old Product # - Old Product Description - Old Product Brand - "
			+ "New Product # - New Product Description - New Product Brand - Reason code - Reason Code Description - Estimated Annual"
			+ " Savings Please see attached example of Excel."
	        +"<br/> EC45-619 - Description # Change the position of the text \" The total estimated annual savings relates to the "
	        + " conversions you accepted during the date range"
	        + " above.\" # Add a line before \"Suggested Sell\" text. # Change \"Reset\" button to link.";
	
	private SuggestedSellOurExclusivesPage page = null;
	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellOurExclusivesPage.class);
	}
	
	@Test(enabled = true)
	public void testDownloadCSVFromMySavingPage () {
		try {
			int row = 8;//DownloadMySavingSuggestedSellTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("DownloadMySavingSuggestedSellTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_20_619,testSenarios); // Row required you may change row as per your data
				page.downloadCSVFromMySavingPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_20_619);
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
