package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hcl.usf.pages.InvoicesAndPaymentsPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class InvoicesAndPaymentsTest  extends CommonElement  {
	
	private static final String ECR56_599 = "ECR56_599-Test ENTIRE flows for the following from Invoices & Payments "
			+ "1. Make One time payment"
			+ " 2. Scheduled Payment"
			+ " 3. Manage Autopay";
	;
	private InvoicesAndPaymentsPage invoicesAndPaymentsPage = null;
	
	@BeforeMethod
	public void beforeMethod() {
		invoicesAndPaymentsPage = PageFactory.initElements(driver, InvoicesAndPaymentsPage.class);
	}
	
	
	@Test(enabled = true)
	public void invoicesAndPaymentsTest() {
		try {
			int row = 1; //Normal Order creation flow
			String testSenarios=selectRowAndReturnSenarios(row);
			if ("InvoicesAndPaymentsTest".equalsIgnoreCase(testSenarios)
					&& findData("Run").equalsIgnoreCase("yes")) {
				startTestWithDescription(AppUtil.TEST_NAME,ECR56_599,testSenarios); // Row required you may change row as per your data
				invoicesAndPaymentsPage.validateInvoicesAndPayments(cdto.getCustomerId(), cdto.getDepartmentId());
				cdto.setRunStatus(AppUtil.PASSED);
				commonInfoLog(testSenarios + " scenario completed");
			} else {
				skipTestCase(testSenarios,ECR56_599);
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
		invoicesAndPaymentsPage = null;
	}


}
 