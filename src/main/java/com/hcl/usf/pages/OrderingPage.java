package com.hcl.usf.pages;
/***
 * 
 * @author This class is written by Rajesh, I have found suspicious code, need to verify   
 *
 */
public class OrderingPage extends CommonPageElement {

	public void validateNormalOrder(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		searchList(findData("ListName")); // Again search the NListName
		pauseInSeconds(5);
		filterProduct(findData("Product1"));
		pauseInSeconds(2);
		waitForLoad();
		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(3);
		// ecom.continue.button
		clickContinueButton();
		pauseInSeconds(3);
		clickReviewAndSubmitOrder();
		pauseInSeconds(3);
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
		}

	}

	public void validateSPlitOrder(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		// String listName = findData("NListName");
		searchList(findData("ListName")); // Again search the NListName
		pauseInSeconds(5);
		filterProduct(findData("Product1"));
		pauseInSeconds(2);
		waitForLoad();
		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(1);
		// ecom.continue.button
		clickContinueButton();
		consoleInfoLog("Review and Submit steps");
		// Click Review Order
		clickElement(getProperty("ecom.review.order"), "Review Order button clicked");
		waitForLoad();
		pauseInSeconds(2);
		AddproductfromQOE();
		validateDisplayedElement(getProperty("ecom.order.Splitorde.msg"), "Split Order");
	}

	public void validateSPlitOrderWithException(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);

		// String listName = findData("NListName");
		searchList(findData("ListName")); // Again search the NListName
		pauseInSeconds(5);
		filterProduct(findData("Product1"));
		pauseInSeconds(2);
		waitForLoad();
		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(1);
		// ecom.continue.button
		clickContinueButton();
		consoleInfoLog("Review and Submit steps");
		// Click Review Order
		clickElement(getProperty("ecom.review.order"), "Review Order button clicked");
		waitForLoad();
		pauseInSeconds(2);
		setText(getProperty("ecom.order.QOE.prod"), findData("Product2"));
		setText(getProperty("ecom.order.QOE.qty"), "1");
		clickElement(getProperty("ecom.order.QOE.Addbtn"), "Add button from QOE");
		pauseInSeconds(2);
		// clickReviewAndSubmitOrder();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		waitForLoad();
		pauseInSeconds(3);
		confirmAndResolveException();
		consoleInfoLog("Review and Submit steps finished");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.order.Splitorde.msg"), "Split Order");

	}

	public void cancelFromTheOrderConfirmationPage() {
		validateDisplayedElement(getProperty("ecom.submit.order.cancelbtn"), "Cancel button");
		clickElement(getProperty("ecom.submit.order.cancelbtn"), "Cancel button");
		validateDisplayedElement(getProperty("ecom.submit.order.cancelyesbtn"), "Yes confirmation button");
		clickElement(getProperty("ecom.submit.order.cancelyesbtn"), "Yes confirmation button");
		validateDisplayedElement(getProperty("ecom.submit.order.cancelmsg"), "Cancel message");
		clickElement(getProperty("ecom.submit.order.cancelmsg"), "Cancel message");
	}

	public void creditHoldValidation(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		// creditHold Msg validation
		validateDisplayedElement(getProperty("ecom.order.credithold.titlemsg"), "Credit hold message");
		commonPassLog("Credit hold banner is dispalyed in the customer");
		isElementDisabled(getProperty("ecom.create.order"), "Create order button");
		commonPassLog("Create order button is disabled due to customer is credit Hold");
	}

	public void oldOrderSubmittedPageNavigation(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		// Vaiw all order page validation
		validateDisplayedElement(getProperty("ecom.order.home.viewallorder"), "View all order link");
		clickElement(getProperty("ecom.order.home.viewallorder"), "View all order link");
		validateDisplayedElement(getProperty("ecom.order.home.Myorder"), "My order page");
		validateDisplayedElement(getProperty("ecom.order.Myorder.submittedorder"), "First submitted order");
		clickElement(getProperty("ecom.order.Myorder.submittedorder"), "First submitted	");
		validateDisplayedElement(getProperty("ecom.order.submittedorder.title"), "Submitted order title");
		// validateDisplayedElement(getProperty("ecom.order.order.Specialinstraction"),
		// "Special Instraction message");
	}

	public void editingAndResubmitingFromNewlyCreatedOrder() {
		validateDisplayedElement(getProperty("ecom.order.edit.btn"), "Edit order button");
		clickElement(getProperty("ecom.order.edit.btn"), "Cancel button");
		AddproductfromQOE();
	}

	public void AddproductfromQOE() {
		setText(getProperty("ecom.order.QOE.prod"), findData("Product2"));
		setText(getProperty("ecom.order.QOE.qty"), "1");
		clickElement(getProperty("ecom.order.QOE.Addbtn"), "Add button from QOE");
		pauseInSeconds(2);
		// clickReviewAndSubmitOrder();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		waitForLoad();
		pauseInSeconds(3);
		confirmAndResolveException();
		consoleInfoLog("Review and Submit steps finished");
		pauseInSeconds(1);
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");

			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");

		}

	}

	public void minOrderValidationTest(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);

		// String listName = findData("NListName");
		searchList(findData("ListName")); // Again search the NListName
		pauseInSeconds(5);
		filterProduct(findData("Product1"));
		pauseInSeconds(2);
		waitForLoad();

		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(1);
		// ecom.continue.button
		clickContinueButton();
		pauseInSeconds(2);

		String minvalue = findData("MinOrderValue");
		if ("CS".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");
			validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min Order Cases text Validation");

		} else if ("dollar".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");

		} else if ("CSAndDollar".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");
			validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min Order Cases text Validation");
			validateDisplayedElement(getProperty("ecom.order.minorder.Dollercheck"),
					"Min Order Dollar text Validation");
		}
		// clickReviewAndSubmitOrder();
		waitForLoad();
		consoleInfoLog("Review and Submit steps");
		// Click Review Order
		clickElement(getProperty("ecom.review.order"), "Review Order button clicked");
		waitForLoad();
		pauseInSeconds(3);
		if ("CS".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");
			validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min Order Cases text Validation");

		} else if ("dollar".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");

		} else if ("CSAndDollar".equalsIgnoreCase(minvalue)) {
			validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order Header Message");
			validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min Order Cases text Validation");
			validateDisplayedElement(getProperty("ecom.order.minorder.review.Dollercheck"),
					"Min Order Dollar text Validation");
		}
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		waitForLoad();
		pauseInSeconds(3);
		confirmAndResolveException();
		// if("CS".equalsIgnoreCase(minvalue)) {
		// validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order
		// Header Message");
		// validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min
		// Order Cases text Validation");
		//
		// }else if("dollar".equalsIgnoreCase(minvalue)){
		// validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order
		// Header Message");
		//
		// }
		// else if("CAAndDollar".equalsIgnoreCase(minvalue)){
		// validateDisplayedElement(getProperty("ecom.order.minorder.title"), "Min Order
		// Header Message");
		// validateDisplayedElement(getProperty("ecom.order.minorder.CScheck"), "Min
		// Order Cases text Validation");
		// validateDisplayedElement(getProperty("ecom.order.minorder.Dollercheck"), "Min
		// Order Dollar text Validation");
		// }
		consoleInfoLog("Review and Submit steps finished");
		pauseInSeconds(1);
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");

			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");

		}
	}

}
