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
public class ImportOrderTextValidationTest extends CommonElement {
    /***
     * @TODO next
     */
	private static final String STORY_EC45_596 = "EC45-596\r\n" + 
			"1. Update text at the bottom of imported orders page that has information and link to go to view invoices page to the following... \"Looking for a past order? Once an order is shipped, the invoice is available for your review. "
			+ "<View Invoices link>\" Note - Existing screenshot attache";
	         
	private ProductSearchPage page = null;

	@BeforeMethod
	public void beforeMethod() {
		page = PageFactory.initElements(driver, ProductSearchPage.class);
	}

	@Test(enabled = true)
	public void testImportTextValidation() {
		try {
			int row = 11; // ImportOrderTextValidationTest
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("ImportOrderTextValidationTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,STORY_EC45_596,testSenarios); // Row required you may change row as per your data
				page.validationImportText(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,STORY_EC45_596);
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
