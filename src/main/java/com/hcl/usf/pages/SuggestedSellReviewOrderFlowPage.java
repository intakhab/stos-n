package com.hcl.usf.pages;

/**
 * 
 * @author intakhabalam.s@hcl.com
 * 
 */

public class SuggestedSellReviewOrderFlowPage extends CommonPageElement {

	/***
	 * User story-311
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	public void validateSuggestedSellReviewOrderFlow(String customerId, String departmentId) {
		waitForLoad();
		// Login and search Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		if (validateSugSellAtMenuDropdownAndRightRailPage()) {
			// validatingTC2
			commonInfoLog("Clicked Our Exculsive link");
			findElementByXpath(getProperty("ecom.our.exculsive")).click();
			waitForLoad();
			pauseInSeconds(2);
			scrollIntoViewJS(getProperty("ecom.suggested.product.updates.exclusive"), -200);
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates.exclusive"),
					"Suggested Product Updates Text");
			pauseInSeconds(1);
			jsScrollTop();
			pauseInSeconds(1);
			// call for List
			String[] list = { "N" };

			for (int i = 0; i < list.length; i++) {
				String lname = findData(list[i] + "ListName");
				String pname = findData(list[i] + "ProductName");
				//
				commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
				productCompareWithList(lname, pname);
			}

		} else {
			commonInfoLog("Customer having not suggeseted products setup, please setup data properly");
		}

	}

	private void productCompareWithList(String listName, String pd) {
		waitForLoad();
		pauseInSeconds(1);
		searchList(listName); // Again search the NListName
		pauseInSeconds(1);
		int size = filterProduct(pd);
		pauseInSeconds(1);
		validatelistHeaderAtListProductPage(listName);
		pauseInSeconds(2);
		this.reviewOrderFlow(size, pd);

	}

	/***
	 * This method will validate all component of current and suggested textbox
	 */
	private String reviewOrderFlow(int size, String pdname) {
		String searchProdFromPage = "";
		boolean nextStep = false;
		for (int i = 0; i < size;) {
			searchProdFromPage=validateSuggestedSellFilterProduct(i);// important on common
			validateAcceptSuggestionAndClick(i);
			pauseInSeconds(2); //Bugs fixed
			jsScrollWindowUp();
			// again filter product
			pauseInSeconds(1);
			captureScreenShot("After accepting suggested checkbox, it will replace to normal product");
			filterProduct(searchProdFromPage);
			pauseInSeconds(2);
			// Original Product verification
			searchProdFromPage = validateCurrentProduct(i);
			pauseInSeconds(1);
			validateRightRailCurrentOrdAndQntity(); // Right Rail
			nextStep = true;
			if (nextStep) {
				pauseInSeconds(1);
				consoleInfoLog("Review steps");
				// Click Review Order
				clickElement(getProperty("ecom.review.order"), "Clicked \"Review Order\" button");
				pauseInSeconds(3);
				// after global
				globalSearch(pdname);
				pauseInSeconds(2);
				waitForLoad();
				clickElement(getProperty("ecom.add.to.order"), "Clicked \"Add to Order\" button");
				captureScreenShot("Page Screenshot");
				pauseInSeconds(2);
				clickElement(getProperty("ecom.review.order"), "Clicked \"Review Order\" button");
				pauseInSeconds(3);
				//validateRightRailCurrentOrdAndQntity(); //Not working here
				//validateSuggestedSellFilterProduct(i);// Again Validation on common
				validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
				// Validate Suggested product
				pauseInSeconds(1);
				scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
				pauseInSeconds(1);
				validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
				pauseInSeconds(1);
				clickToPDPPageFromProdImageIcon(i);// Click to Review Order Page
				commonInfoLog("Validate Product Compare with accept button");
				validateProductCompareWithAcceptButton(pdname, null);// After accepting again search
				//filterProduct(searchProdFromPage);// Again search
				pauseInSeconds(1);
				//validateCurrentProduct(i);
				String prd = "//span[contains(.,'# " + searchProdFromPage + "') or contains(.,'#" + searchProdFromPage + "')]";
				validateDisplayedElement(prd, searchProdFromPage+" product");
				pauseInSeconds(1);
				jsScrollWindowDown();
				commonInfoLog("Quick Entry with product name::"+pdname);
				quickEntry(pdname);
				pauseInSeconds(1);
				commonInfoLog("Validate Product Compare with accept button");
				validateProductCompareWithAcceptButton(pdname, null);// After accepting again search
				pauseInSeconds(2);
				clickElement(getProperty("ecom.review.order"), "Review Order button clicked");
				pauseInSeconds(3);
				//Again validate
				prd = "//span[contains(.,'# " + searchProdFromPage + "') or contains(.,'#" + searchProdFromPage + "')]";
				validateDisplayedElement(prd, searchProdFromPage+" product");
				pauseInSeconds(2);
				clickElement(getProperty("ecom.submit"), "Clicked Submit button");
				pauseInSeconds(1);
				confirmAndResolveException();
				pauseInSeconds(1);
			}

		}
		return searchProdFromPage;
	}

	private void quickEntry(String pdname) {
		pauseInSeconds(1);
		clickElement(getProperty("ecom.quick.entry.button"), "Clicked quick entry button");
		pauseInSeconds(1);
		setText(getProperty("ecom.quick.entry.prod"), pdname);
		pauseInSeconds(1);
		setText(getProperty("ecom.quick.entry.qnty"), "1");
		captureScreenShot("Page Screenshot");
		clickElement(getProperty("ecom.quick.view.sugg.prod"), "Clicked view suggested product link");
		pauseInSeconds(2);
		
	}

	private String validateCurrentProduct(int i) {
		String currProd = deletePropertyDeliminator(getProperty("ecom.original.prod.name"), i);
		String currtext = waitForVisibilityOfElementLocated(currProd).getText();
		String searchProdFromPage = currtext.split(" ")[0].replaceAll("#", "").trim();
		commonInfoLog("Current Product name :: " + searchProdFromPage);
		validateDisplayedElement(currProd, "Current product");
		pauseInSeconds(1);
		String textBoxPath = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
		validateDisplayedElement(textBoxPath, "Quantity Textbox");
		pauseInSeconds(1);
		putValueInQuantityBox(textBoxPath, 1);
		pauseInSeconds(1);
		checkOrderPopUp();// Popup modification
		pauseInSeconds(3);
		return searchProdFromPage;
	}

}
