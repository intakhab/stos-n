package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.PromotionsBusinessPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/***
 * EC45-45 Change Promotions link "Get Details" to say "Access Promotions"
 * 
 * @author intakhabalam.s@hcl.com
 * 
 */
public class PromotionsBusinessTest extends CommonElement {

	private static final String ECR46_15 = "New Page for Promotions";
	private static final String ECR46TC_16 = "Move Existing My Kitchen to live with Promotions";
	private static final String ECR46TC_17 = "Promotion Grouping on Promotions Page";
	private static final String ECR46TC_63 = "My Kitchen Offer Pages to show Promotions";
	private static final String ECR46TC_5 = "Subpages under Promotions page for each Promotion";
	private static final String ECR46TC_18 = "Change Email Deeplinks to target Promotion Main Page";
	private static final String ECR46TC_90 = "Carousal behavior on more than 20 products";
	private static final String ECR46TC_13 = "Filtering Logic on Promotion Offer Page";
	private static final String ECR46TC_62 = "Fix deeplink for re-direct";
	private static final String ECR46TC_115 = "Right carousel arrow to show green on more than 20 products";
	private static final String ECR46TC_113 = "Promo name for 475 promo ID";
	private static final String ECR46TC_117 = "Test deeplinks in lab";
	private static final String ECR46TC_6 = "Homepage Merchandising Zones";
	private static final String ECR46TC_6A = "Homepage Merchandising Zones";
	private PromotionsBusinessPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, PromotionsBusinessPage.class);
	}

	@Test(enabled = true)
	public void promotionLandingPageTest() {
		try {
			int row = 1; // HomePageOrderingSectionTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("PromotionLandingPageTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_15, testSenarios); // Row required you may change row
																						// as per your data
				page.valdiatePromotionsOurExclusive(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_15);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-16 --->Move Existing My
	 *                               Kitchen to live with Promotions Test
	 *                               Description-This Test scenario is used to
	 *                               verify whether My kitchen link is displayed in
	 *                               the promotions page and it navigates to My
	 *                               Kitchen Offer page from Promotions page
	 */
	@Test(enabled = true)
	public void validationOfMyKitchenLink() {
		try {
			int row = 2; // ValidationOfMyKitchenLinkTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidationOfMyKitchenLinkTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_16, testSenarios);
				page.validationOfMyKitchenLink(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_16);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}

	}

	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-17 --->Promotion Grouping
	 *                               on Promotions Page Test Description-This test
	 *                               scenario is used to verify the Promotion page
	 *                               UI elements such as Zones, Alphabetical order
	 *                               of Promotions,View all button,Product
	 *                               description page and Product carousal
	 *                               

	 */
	@Test(enabled = true)
	public void validationOfPromotionGrouping() {
		try {
			int row = 3; // validationOfPromotionGrouping
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidationOfGroupingTest".equalsIgnoreCase(testSenarios) && findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_17, testSenarios);
				page.validationOfPromotionGrouping(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_17);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-63 My Kitchen Offer Pages
	 *                               to show Promotions Test Description-This test
	 *                               scenario is used to verify the promotion list
	 *                               in my kitchen page if the selected customer has
	 *                               promotions and only my kitchen offers if the
	 *                               selected customer don't have promotions
	 */
	@Test(enabled = true)
	public void validationOfPromotionInMyKitchen() {
		try {
			int row = 4; // validationOfPromotionInMyKitchen
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidationOfPromotionInMyKitchenTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_63, testSenarios);
				page.validationOfPromotionInMyKitchen(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_63);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-5 Subpages under
	 *                               Promotions page for each Promotion Test
	 *                               Description-This test scenario is used to
	 *                               verify the promotion page
	 *                               attributes,icons,ordering and products per page
	 */
	@Test(enabled = true)
	public void validationOfSubpagesUnderPromotionPageTest() {
		try {
			int row = 5; // validationOfSubpagesUnderPromotionPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidationOfSubpagesUnderPromotionPageTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_5, testSenarios);
				page.validationOfSubpagesUnderPromotionPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_5);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***
	 * @Author-Nithyakumar.t@hcl.com-- Nithyakumar.t ECR46TC-18 Change Email
	 *                                 Deeplinks to target Promotion Main Page Test
	 *                                 Description-This test scenario is used to
	 *                                 verify user is able to land on promotion page
	 *                                 after login with Deep link
	 */
	@Test(enabled = true)
	public void validateDeepLinkPromotionLandingPageTest() {
		try {
			int row = 6; // validateDeepLinkPromotionLandingPageTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateDeepLinkPromotionLandingPageTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_18, testSenarios);
				page.validateDeepLink();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_18);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-90 Carousal behavior on
	 *                               more than 20 products This Test Scenario is
	 *                               used to check the behaviour of carousal for
	 *                               Promotions which contain more than 20 Products
	 */
	
	
	@Test(enabled = true)
	public void validateProductCarousal() {
		try {
			int row = 7; // ValidateProductCarousalTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateProductCarousalTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_90, testSenarios);
				page.ValidateProductCarousal(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_90);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-13 Filtering Logic on Promotion Offer Page
	 *                               This Test Scenario iS used to check the filtering Logic on Promotions 
	 *                               and to filter out some products in UI.
	 */
	@Test(enabled = true)
	public void validateFilteringLogicOnPromotions() {
		try {
			int row = 8; // validateFilteringLogicOnPromotions
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateFilteringLogicOnPromotionsTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_13, testSenarios);
				page.validateFilteringLogicOnPromotions(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_13);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/***
	 * @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-62 Fix deeplink for re-direct
	 * This Test Scenario is used to verify the Deep link for various page in Promotion Module 
	 * 
	 * 
	 */
	@Test(enabled = true)
	public void validateDeeplinkInPromotionModule() {
		try {
			int row = 9; //validateDeeplinkInPromotionModule
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateDeeplinkInPromotionTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_62, testSenarios);
				page.validateDeeplinkInPromotionModule();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_62);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	 /* @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-115 Right carousel arrow 
	  * to show green on more than 20 products This Test Scenario is used to check
	  * whether the Right carousal arrow is displayed in Green color 
	 *                               .
	 */
	@Test(enabled = true)
	public void validateRightCarousalArrow() {
		try {
			int row = 10; // ValidateRightCarousalArrowTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateRightCarousalArrowTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_115, testSenarios);
				page.validateRightCarousalArrow(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_115);
			}

		} catch (Throwable ex) {
		} 
		finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/* @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-113 Promo name for 475 promo ID
	 *  This Test Scenario is used to check whether the promotions configured in the tandem
	 *  is reflected in ECOM Portal
	 *                               .
	 */
	@Test(enabled = true)
	public void validatePromoNameFor475PromoID() {
		try {
			int row = 11; // validatePromoNameFor475PromoID
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidatePromoNameTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_113, testSenarios);
				page.validatePromoName(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_113);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} 
		finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/* @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC-117 Promo name for 475 promo ID
	 *  This Test Scenario is used to check whether the deeplinks are redirected to the
	 *  corresponding pages in SIT4 
	 */
	@Test(enabled = true)
	public void validateDeeplinkInLab() {
		try {
			int row = 12; // ValidateDeeplinkInLabTest
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateDeeplinkInLabTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_117, testSenarios);
				page.validateDeeplinkInLab();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_117);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} 
		finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/* @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC_6 Homepage Merchandising Zones
	 *  This Test Scenario is used to check whether the appropriate pages are displayed in
	 *  Zone 1,2 and 3 in the Homepage of ECOM Portal
	 */
	@Test(enabled = true)
	public void validateHomepageMerchandisingZones() {
		try {
			int row = 13; // validateHomepageMerchandisingZones
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidateHomepageMerchandisingZonesTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_6, testSenarios);
				page.validateHomepageMerchandisingZones(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46TC_6);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} 
		finally {
			signOut();
			updateTC(cdto);
		}
		
	}
		/* @Author-Thiagarjani@hcl.com-- Thiagarajan.I ECR46TC_6 Homepage Merchandising Zones
		 *  This Test Scenario is used to check whether the appropriate pages are displayed in
		 *  Zone 1,2 and 3 in the Homepage of ECOM Portal
		 */
		@Test(enabled = true)
		public void validateHomepageMerchandisingZones1() {
			try {
				int row = 14; // validateHomepageMerchandisingZones
				String testSenarios = selectRowAndReturnSenarios(row);
				if ("ValidateHomepageMerchandisingZonesTest1".equalsIgnoreCase(testSenarios)
						&& findData("Run").equalsIgnoreCase("yes")) {
					startTestWithDescription(AppUtil.TEST_NAME, ECR46TC_6A, testSenarios);
					page.validateHomepageMerchandisingZones1(cdto.getCustomerId(), cdto.getDepartmentId());
					cdto.setRunStatus(AppUtil.PASSED);
					commonInfoLog(testSenarios + " scenario completed");
				} else {
					skipTestCase(testSenarios, ECR46TC_6A);
				}

			} catch (Throwable ex) {
				commonErrorUpdate(ex);
			} 
			finally {
				signOut();
				updateTC(cdto);
			}
	}
	@AfterTest
	public void afterTest() {
		page = null;
	}
}
