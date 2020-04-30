package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.CES120Page;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author ragu
 */
public class CES120PageTest extends CommonElement {
	
	private static final String STORY_ECR46_312 = "STORY_ECR46 : 312-1b.CES 120 pages will have same change, since they display search results";
				
	
	private CES120Page cespage = null;

	@BeforeMethod
	public void beforeMethod() {
		cespage = PageFactory.initElements(driver, CES120Page.class);
	}

	@Test(enabled = true)
	public void testNoAddtoListOnCES120forLMACustomer() {
		try {
			int row = 1; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateNoAddListFromCES120PageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				cespage.validationNoAddToListOnCES120(cdto.getCustomerId(), cdto.getDepartmentId());
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
	public void testAddtoListOnCES120forLMACustomer() {
		try {
			int row = 2; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateAddListFromCES120PageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				cespage.validateAddToListOnCES120(cdto.getCustomerId(), cdto.getDepartmentId());
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
	public void testAddtoListOnCES120forNonLMACustomer() {
		try {
			int row = 3; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateAddListFromCES120PageforNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_312,testSenarios); // Row required you may change row as per your data
				cespage.validateAddToListOnCES120forCustomer(cdto.getCustomerId(), cdto.getDepartmentId());
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
  
	
	@AfterTest
	public void afterTest() {
		cespage = null;
	}

}
