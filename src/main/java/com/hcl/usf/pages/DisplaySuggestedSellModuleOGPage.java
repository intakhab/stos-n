package com.hcl.usf.pages;

/**
 * 
 * @author intakhabalam.s@hcl.com
 * user story-1 (4.5)
 *
 */
public class DisplaySuggestedSellModuleOGPage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo {@link String}
	 * @param departNo {@link String}
	 */
	public void validateSuggestedOG(String customerNo, String departNo) {
		// Login and search Customer
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(1);
		String listName=findData("NListName");
		searchList(listName);
		pauseInSeconds(2);
		consoleInfoLog("Login and Customer serach  steps passed");
		int size = filterProduct(findData("NProductName"));
		pauseInSeconds(2);
		consoleInfoLog("Filter product steps passed");
		pauseInSeconds(2);
		boolean isValidateSteps=false;
		for (int i = 1; i < size;) {
			pauseInSeconds(1);
			if(isExists(getProperty("ecom.original.prod.text"))) {
			  validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
			}
			scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -110);
			pauseInSeconds(1);
			String textBox;
			if(isExists(getProperty("ecom.suggested.prod.text2"))){
				textBox = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
				// Validate Suggested product
			    validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
			    validateDisplayedElement(textBox, "Suggested product Text Box");
				validateDisplayedElement(getProperty("ecom.packsize.different.text"), "Pack size is different");
			}else {
				//validateNotDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
				commonFailLog("Suggested Product not displayed");
				textBox = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (i-1));
			    validateDisplayedElement(textBox, "Product Text Box");

			}
			/*********/
			boolean b=putValueInQuantityBox(textBox, 1);
			if(b) {
				pauseInSeconds(1);
				checkOrderPopUp();//Generic Popup
			  return;
			}
			isValidateSteps=true;
			break;
		}
		if (isValidateSteps) {
			clickReviewAndSubmitOrder();
			if (confirmOrderText()) {
				commonInfoLog("Order Confirmation!!!");
			}
		} else {
			commonFailLog("Please setup data properly");
		}
        iMDone();
	}
	

}
