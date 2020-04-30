package com.hcl.usf.pages;

/**
 * @author intakhabalam.s@hcl.com
 */
public class InvoicesAndPaymentsPage extends CommonPageElement {
	/**
	 * ECR46-599
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validateInvoicesAndPayments(String customerId, String departmentId) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(2);

		commonSteps();

		viewInvoices();

		commonSteps();

		makeOneTimePayment();

		commonSteps();

		schedulePayment();

		commonSteps();

		manageAutopay();

		iMDone();

	}
	

	/***
	 * 
	 */
	private void viewInvoices() {
		// validateDisplayedElement("(//span[contains(.,'Invoices & Payments')])[2]",
		// "Invoice and Payments page");// 1
		validateDisplayedElement(getProperty("ecom.view.invoice"), "View Invoices page");
		clickElement(getProperty("ecom.invoice.details.link"),
				"Clicked get details link for View Invoices");
		waitForLoad();
		pauseInSeconds(2);
		setText(getProperty("ecom.start.next.input"), "01/01/2010"); // As of now hard code dat
		clickElement(getProperty("ecom.invoice.search"), "Clicked search button");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.invoice.download.button"), "Download Invoices button");// 2
		String getInvoceNo=getText(getProperty("ecom.invoice.no"));
		setText(getProperty("ecom.invoice.number.input"), getInvoceNo);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.invoice.search.button"), "Clicked Search Invoices button");
		pauseInSeconds(2);
		captureScreenShot("Search result displayed");
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.invoice.first.check.box"),"Clicked invoice checkbox for download");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.invoice.dropdown.icon"), "Clicked invoice button Download Dropdown icon");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.invoice.dropdown.type"), "Invoice type selected");// 2
		clickElement(getProperty("ecom.invoice.dropdown.type"), "Clicked invoice Download Dropdown for Type");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.invoice.download.button"), "Clicked Download invoices button");// 3
		pauseInSeconds(1);
		jsScrollTop();

	}

	/**
	 * 
	 */
	private void makeOneTimePayment() {
		
		validateDisplayedElement(getProperty("ecom.ontime.payment.small.header"), "View Invoices page");
		pauseInSeconds(1);
        		  
		clickElement(getProperty("ecom.ontime.payment.get.details.link"),"Clicked get details link for Make One-Time Payment");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.ontime.payment.big.header"), "One time payment headar");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.payment.checkbox.link"), "Clicked check box for pament");
		captureScreenShot("After selecting checkbox");
		clickElement(getProperty("ecom.next.button"), "Clicked Next button");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.ontime.review.payment.header"), "Review and Submit Payment");
		pauseInSeconds(1);
		goBack();
	}
	/**
	 * 
	 */
	private void manageAutopay() {
		
		validateDisplayedElement(getProperty("ecom.manage.autopay.header"), "Manage Autopay");//
		pauseInSeconds(1);
		scrollIntoViewJS(getProperty("ecom.manage.autopay.header"), -10);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.manage.autopay.header.manage.link"), "Clicked Manage Autopay link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.manage.autopay.header.big"), "Manage AutoPay Header");
		clickElement("(//td[contains(.,'View Current AutoPay')])[4]", "Clicked View Current AutoPay link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.manage.autopay.header.big"), "Manage AutoPay Header");
		jsScrollDown();
		clickElement(getProperty("ecom.back.button"), "Clicked Back button");
		pauseInSeconds(1);

		clickElement("(//span[contains(.,'Customers')])[2]/following::a[2]", "Clicked checkbox");
		pauseInSeconds(1);

		scrollIntoViewJS(getProperty("ecom.next.button"), 50);
		pauseInSeconds(1);

		clickElement(getProperty("ecom.next.button"), "Clicked Next button");

		captureScreenShot("Page Screen shot");
		jsScrollDown();
		pauseInSeconds(1);
		captureScreenShot("Page Screen shot");
		clickElement(getProperty("ecom.back.button"), "Clicked Back button");
	}
     /***
      * 
      */
	private void schedulePayment() {
		validateDisplayedElement(getProperty("ecom.scheduled.payment.header"), "Scheduled Payments Page Header");//
		pauseInSeconds(1);
         
		scrollIntoViewJS(getProperty("ecom.scheduled.payment.header"), -10);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.scheduled.payment.header.details.link"), "Clicked Scheduled Payments link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.scheduled.payment.header2"), "Scheduled Payments Header");// 2
		pauseInSeconds(1);
		clickElement(getProperty("ecom.payment.view.details.link"), "Clicked View Details link");
		pauseInSeconds(1);

		validateDisplayedElement(getProperty("ecom.payment.details.link"), "Payment Details");

		//clickElement("(//button[contains(.,'Cancel')])[1]", "Clicked Cancel button");
		//pauseInSeconds(2);
		//clickElement("//button[contains(.,'Yes')]", "Clicked yes button");
		//pauseInSeconds(2);
		//captureScreenShot("After Selecting yes buttton");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.back.button"), "Clicked Back button");
		waitForLoad();
		pauseInSeconds(2);
	}
	/**
	 * 
	 */
	private void commonSteps() {
		moveToIconAndClick(getProperty("ecom.my.bussiness"), getProperty("ecom.invoices.payments"));
		captureScreenShot("Page Screen shot");
	}

}
