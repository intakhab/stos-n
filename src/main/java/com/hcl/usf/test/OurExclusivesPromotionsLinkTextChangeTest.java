package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.springframework.core.annotation.Order;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.TextVerifyCommonChangePage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * 1. Change Promotions link "Get Details" to say "Access Promotions"	
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class OurExclusivesPromotionsLinkTextChangeTest extends CommonElement {

	private static final String EC45_41 = "EC45-41 1. Change Promotions link \"Get Details\" to say \"Access Promotions\" ";
	
	private static final String EC45_4 = "EC45-04 - Our Exclusives - Promotions Link\r\n" + 
			" Text Change Change Promotions link \"Get Details\" to say \"Access\r\n" + 
			" Promotions\" 1.Verify that if the customer is with Promotions then the\r\n" + 
			" \"Promotions\" option is available under Our Exclusive dropdown\r\n" + 
			" 2.Verify that If the customer is without Promotions then the\r\n" + 
			" \"Promotions\" option is not available under Our Exclusive dropdown\r\n" + 
			" 3.Verify that \"Get Details\" text is not available below \"Promotions\"\r\n" + 
			" section under Our Exclusive Landing page 4.Verify that \"Access\r\n" + 
			" Promotions\" text is available instead \"Get Details\" under promotions\r\n" + 
			"  section under Our Exclusive page";
	
	private TextVerifyCommonChangePage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, TextVerifyCommonChangePage.class);
	}
	@Order(1)
	@Test(enabled = true)
	public void testPromotionsLinkTextChangePage() {
		try {
			int row = 1;//OurExclusivesPromotionsLinkTextChangeTest
			String testSenarios=selectRowAndReturnSenarios(row); //
			if ("OurExclusivesPromotionsLinkTextChangeTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_41,testSenarios); // Row required you may change row as per your data
				page.valdiatePromotionsLinkTextChangePage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_41);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Order(2)
	@Test(enabled = true)
	public void testOurExclusivePromotionsLink() {
		try {
			int row = 6;//SuggestedSellOurExclusivesPromoTextTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellOurExclusivesPromoTextTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").toLowerCase().equals("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,EC45_4,testSenarios); // Row required you may change row as per your data
				page.validateOurExclusivePromotionsLink(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45_4);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			//updateTC(cdto);
		}
	}
	@AfterTest
	public void afterTest() {
		page = null;
	}

}

