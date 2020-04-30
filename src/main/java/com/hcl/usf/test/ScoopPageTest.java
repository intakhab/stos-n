package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.ScoopPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class ScoopPageTest extends CommonElement {
	
	private static final String STORY_ECR46_3121 = "1b. Scoop will have same change, since they display search results";
	
	private ScoopPage spage = null;

	@BeforeMethod
	public void beforeMethod() {
		spage = PageFactory.initElements(driver, ScoopPage.class);
	}
	
	@Test(enabled = true)
	public void testNoAddtoListOnScoopforLMACustomer() {
		try {
			int row = 1; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateNoAddListFromScoopPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_3121,testSenarios); // Row required you may change row as per your data
				spage.validationNoAddToListOnScoop(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_3121);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testAddtoListOnScoopforLMACustomer() {
		try {
			int row = 2; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateAddListFromScoopPageforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_3121,testSenarios); // Row required you may change row as per your data
				spage.validateAddToListOnScoop(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_3121);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	@Test(enabled = true)
	public void testAddtoListOnScoopforNonLMACustomer() {
		try {
			int row = 3; // AddToListSearch
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ValidateAddListFromScoopPageforNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_3121,testSenarios); // Row required you may change row as per your data
				spage.validateAddToListOnScoopforCustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_3121);
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
		spage = null;
	}

}
