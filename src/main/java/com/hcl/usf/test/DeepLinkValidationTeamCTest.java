package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.DeepLinkPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class DeepLinkValidationTeamCTest extends CommonElement {
	
	private static final String EC45C_4 = "1. We will update the deeplink methodology and "
			+ "structure for the deeplinks that lead users to the Suggested Sell page to follow the new deeplink architecture";
	
	private static final String EC45C_3 = "1.We will update the deeplink methodology and"
	+ " structure for the deeplinks that lead users to the Product Details Page to follow the new deeplink architecture";
	
	private static final String EC45C_6 = "1. We will update the deeplink methodology and" 
	+"structure for the deeplinks that lead users to the Promotions page to follow the new deeplink architecture";
	
	private static final String EC45C_5 ="1. When user clicks on a link to the Search Results page from USF marketing emails they should be navigated to login page. Following information will be available as part of the link"
    +"User,Customer,Department- Optional field. If field is populated, then navigate user to the specified department mentioned in link. If field is not populated then navigate user to the last department they were logged in as (default department)"
    +"Division,Offer type/id = 'SEARCH', Source, Search Keyword";
			
			
			private DeepLinkPage deeplink = null;

			@BeforeMethod
			public void beforeMethod() {
				deeplink = PageFactory.initElements(driver, DeepLinkPage.class);
			}

			@Test(enabled = true)
			public void testSuggestedSellDeepLink() {
				try {
					int row = 1; 
					String testSenarios=selectRowAndReturnSenarios(row);
					if ("DeepLinkSuggestedSell".equalsIgnoreCase(testSenarios)
							&& findData("Run").equalsIgnoreCase("yes")) {
						startTestWithDescription(AppUtil.TEST_NAME, EC45C_4,testSenarios); 
						deeplink.validateSuggestedSellDeepLink();
						cdto.setRunStatus(AppUtil.PASSED);
						commonInfoLog(testSenarios + " scenario completed");
					} else {
						skipTestCase(testSenarios,EC45C_4);
					}
				} catch (Throwable ex) {
						commonErrorUpdate(ex);
				} finally {
					updateTC(cdto);
					signOut();
				}
			}
			
			@Test(enabled = true)
			public void testProductDetailsPageDeepLink() {
				try {
					int row = 2; 
					String testSenarios=selectRowAndReturnSenarios(row);
					if ("DeepLinkProductDetailsPage".equalsIgnoreCase(testSenarios)
							&& findData("Run").equalsIgnoreCase("yes")) {
						startTestWithDescription(AppUtil.TEST_NAME, EC45C_3,testSenarios); 
						deeplink.validateProductDetailsPageDeepLink();
						cdto.setRunStatus(AppUtil.PASSED);
						commonInfoLog(testSenarios + " scenario completed");
					} else {
						skipTestCase(testSenarios,EC45C_3);
					}
				} catch (Throwable ex) {
						commonErrorUpdate(ex);
				} finally {
					updateTC(cdto);
					signOut();
				}
			}
			
			@Test(enabled = true)
			public void testPromotionPageDeepLink() {
				try {
					int row = 3; 
					String testSenarios=selectRowAndReturnSenarios(row);
					if ("DeepLinkPromotionPage".equalsIgnoreCase(testSenarios)
							&& findData("Run").equalsIgnoreCase("yes")) {
						startTestWithDescription(AppUtil.TEST_NAME, EC45C_6,testSenarios); 
						deeplink.validatePromotionPageDeepLink();
						cdto.setRunStatus(AppUtil.PASSED);
						commonInfoLog(testSenarios + " scenario completed");
					} else {
						skipTestCase(testSenarios,EC45C_6);
					}
				} catch (Throwable ex) {
						commonErrorUpdate(ex);
				} finally {
					updateTC(cdto);
					signOut();
				}
			}
			@Test(enabled = true)
			public void testSearchResultsPageDeepLink() {
				try {
					int row = 4; 
					String testSenarios=selectRowAndReturnSenarios(row);
					if ("DeepLinkSearchResultsPage".equalsIgnoreCase(testSenarios)
							&& findData("Run").equalsIgnoreCase("yes")) {
						startTestWithDescription(AppUtil.TEST_NAME, EC45C_5,testSenarios); 
						deeplink.validateSearchResultsPageDeepLink();
						cdto.setRunStatus(AppUtil.PASSED);
						commonInfoLog(testSenarios + " scenario completed");
					} else {
						skipTestCase(testSenarios,EC45C_5);
					}
				} catch (Throwable ex) {
						commonErrorUpdate(ex);
				} finally {
					updateTC(cdto);
					signOut();
				}
			}

}
