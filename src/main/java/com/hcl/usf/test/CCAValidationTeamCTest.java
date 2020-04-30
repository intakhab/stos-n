package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.CCACommonPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class CCAValidationTeamCTest extends CommonElement {

	private static final String EC45C_21 = "EC45C-21 1) Create a new option on the Partner configuration screen labeled Product Master Files (check box)"
		    +"2) When the Product Master Files check box is enabled. Allow selection of individual divisions for the file including an option for All Divisions";
	
	private static final String EC45C_22 = "1) Create a new option on the Partner configuration screen labeled Product Nutritional Files (check box)"
	         +"2) When the Product Master Files check box is enabled. Allow selection for EB Items Only or All Items (Non-food items still will be excluded)"
	         +"3) When the Nutritional Files check box is enabled. Allow selection of individual divisions for the file including an option for All Divisions"
	         +"4) Radio buttons to be used to selected between All Divisions or the ability to select individual divisions.";

	
	private CCACommonPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, CCACommonPage.class);
	}

	@Test(enabled = true)
	public void testCCAProductMasterFile() {
		try {
			int row = 1; 
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAProductMasterFileConfiguration".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				
				startTestWithDescription(AppUtil.TEST_NAME, EC45C_21,testSenarios); 
				page.ccaProductMasterValidation();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45C_21);
			}
		} catch (Throwable ex) {
				commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOutCCA();
		}
	}
	
	@Test(enabled = true)
	public void testCCAProductNutritionalFile() {
		try {
			int row = 2; 
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("CCAProductNutritionalFileConfiguration".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME, EC45C_22,testSenarios); 
				page.CCAProductNutritionalValidation();
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,EC45C_22);
			}
		} catch (Throwable ex) {
				commonErrorUpdate(ex);
		} finally {
			updateTC(cdto);
			signOutCCA();
		}
	}


}
