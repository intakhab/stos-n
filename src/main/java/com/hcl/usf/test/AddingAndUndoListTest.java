package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.ProductSearchPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class AddingAndUndoListTest extends CommonElement{
	
	private static final String ECR46_116 = "ECR46-116 -1. On success message there is a cancel button to undo adding list "
			+ "1a. Must call API and remove from that list	";

	
	private static final String ECR46_737 = "ECR46-737 - Adding products to list without Unassigned Group	";
	private ProductSearchPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, ProductSearchPage.class);
	}
	
	@Test(enabled = true)
	public void testAddingUndoList() {
		try {
			int row = 9; //UndoAddingListTest //Excel Tab-SearchValidation
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("AddingAndUndoListTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,ECR46_116,testSenarios); // Row required you may change row as per your data
				page.validateUndoAddingListTest(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,ECR46_116);
			}

		} catch (Throwable ex) {
			commonErrorUpdate(ex);
		} finally {
			signOut();
			updateTC(cdto);
		}
	}
	
	
	@Test(enabled = true)
	public void testAddingListWithoutUnassignedGroup() {
		try {
			int row = 10; //AddingListWithoutUnassignedGroupTest //Excel Tab-SearchValidation
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("AddingListWithoutUnassignedGroupTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,ECR46_737,testSenarios); // Row required you may change row as per your data
				page.validateAddingListWithoutUnassignedGroupTest(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,ECR46_116);
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
