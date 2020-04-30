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
public class ProductSearchTest extends CommonElement {

	private static final String STORY_EC45_357 = "EC45-357 -Testing - Search Within all list";
	
	private static final String STORY_ECR46_312 = "1. For search results, both list and grid view\r\n" + 
			"     1a. If Customer does not have Shopping List (even if they have OG/MSL), we will hide 'Add to List'\r\n";
	
	//STORY_ECR46_314 ="1. On product comparison page, when customer does not have shopping list"_incorporated along with Story_ECR46_312
	
	private static final String STORY_ECR46_313= "1. On the Product Description page, when a customer does not have Shopping List"
			+ "(even if they have OG/MSL) then we will hide 'Add to List'";
	
	private static final String ECR46_470= "Point to the Lists page rather than the Edit list page";
	
	private static final String ECR46_406="Add to order behaviour";

			
	
	private ProductSearchPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, ProductSearchPage.class);
	}

	@Test(enabled = true)
	public void testSearchWithIn() {
		try {
			int row = 1; // ProductSearchTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ProductSearchTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_357,testSenarios); // Row required you may change row as per your data
				page.validateSearchWithIn(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_357);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testNoAddtoListSearch() {
		try {
			int row = 3; // RemoveAddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValdiateNoAddListFromSearchPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				page.validationNoAddToListOnSearch(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_312);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testAddtoListSearch() {
		try {
			int row = 4; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValdiateAddListFromSearchPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				page.validateAddToListOnSearch(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_312);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testAddtoListOnSearchforNonLMACustomer() {
		try {
			int row = 5; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValdiateAddListFromSearchPageforNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				page.validateAddToListOnSearchforCustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_312);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}

	@Test(enabled = true)
	public void testNoAddtoListOnPDPforLMACustomer() {
		try {
			int row = 6; // NoAddToListPDP
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValdiateNoAddListFromPDPPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_313,testSenarios); // Row required you may change row as per your data
				page.validationNoAddToListOnPDPPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_313);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testAddtoListOnPDPforLMACustomer() {
		try {
			int row = 7; //AddToListPDP
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValdiateAddListFromPDPPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_313,testSenarios); // Row required you may change row as per your data
				page.validateAddToListOnPDP(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_313);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
		
	}
	@Test(enabled = true)
	public void testListPageRatherThenEditPage() {
		try {
			int row = 8; 
			String testSenarios = selectRowAndReturnSenarios(row);
			if ("ValidatePointToListRatherThanEditPageTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, ECR46_470, testSenarios); // Row required you may change row
				page.validateListPageRatherThenEditPage(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios, ECR46_470);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	/**
	 * 
	 * 	
	 */
		@Test(enabled = true)
		public void testAddToOrderAllPossiblePage() {
			try {
				int row = 9; 
				String testSenarios = selectRowAndReturnSenarios(row);
				if ("validateAddToOrderPossiblePages".equalsIgnoreCase(testSenarios)
						&& findData("Run").equalsIgnoreCase("yes")) {
					startTestWithDescription(AppUtil.TEST_NAME, ECR46_406, testSenarios); // Row required you may change row
					page.validateAddToOrder(cdto.getCustomerId(), cdto.getDepartmentId());
					cdto.setRunStatus(AppUtil.PASSED);
					commonInfoLog(testSenarios + " scenario completed");
				} else {
					skipTestCase(testSenarios, ECR46_406);
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
