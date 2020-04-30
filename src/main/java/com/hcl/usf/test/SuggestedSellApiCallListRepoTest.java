package com.hcl.usf.test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.SuggestedSellQuantityPackPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellApiCallListRepoTest extends CommonElement {

	private static final String STORY_ECR46_69 = "EC46_69 1. The suggested sell api needs to call against the list repository "
			+ "1a. Show the same 'Suggested Products' notification in the notification module";
	
	
	
	private SuggestedSellQuantityPackPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, SuggestedSellQuantityPackPage.class);
	}

	@Test(enabled = true)
	public void testSuggestedSellNonLMACustomer() {
		try {
			// Row required you may change row as per your data
			int row = 16; // SuggestedSellApiCallListRepoTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellforNonLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_69,testSenarios); 
				page.validateSuggestedSellNonLMACustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_69);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOut();
		}
	}
    //
	
	@Test(enabled = true)
	public void testSuggestedSellLMACustomer() {
		try {
			// Row required you may change row as per your data
			int row = 17; // SuggestedSellApiCallListRepoTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("SuggestedSellforLMACustomer".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_69,testSenarios); 
				page.validateSuggestedSellLMACustomer(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_ECR46_69);
			}
		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOut();
		}
	}
	
	@AfterTest
	public void afterTest() {
		page = null;
	}

}
