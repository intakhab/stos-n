package com.hcl.usf.pages;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellQuickOrderEntryPage extends CommonPageElement {

	/***
	 * EC45-396
	 * @param customerNo {@link String}
	 * @param departNo   {@link String}
	 */
	public void validateSuggestedSellProductQOE(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "N","OG","MS"};
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			quickEntrySteps(lname, pname);
		}
		iMDone();
	}

	

	/***
	 * @param listname
	 *            {@link String}
	 * @param prdname
	 *            {@link String}
	 * @param type
	 *            {@link String}
	 */
	public void quickEntrySteps(String listname, String prdname) {
		pauseInSeconds(1);
		waitForLoad();
		searchList(listname); // Again search the listname
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		quickEntrySelection(prdname);
		// latest rst
		jsScrollTop();
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");

	}
    /***
     * 
     */
	private void quickEntrySelection(String pdname) {
		actionMoveToElement(findElementByXpath(getProperty("ecom.quick.entry.hover.icon")));
		clickElement(getProperty("ecom.create.order.options.text"), "Clicked \"Create Order Options\" link");
		pauseInSeconds(5);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.quick.entry.text"), "\"Quick Entry\" link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.quick.entry.text"), "Clicked \"Quick Entry\" link");
		pauseInSeconds(2);
        clickElement(getProperty("ecom.create.button"),"Clicked create button");
        
        commonInfoLog("Quick Entry with product name::"+pdname);
        pauseInSeconds(2);
		setText(getProperty("ecom.quick.entry.prod"), pdname);
		pauseInSeconds(1);
         if(isExists(getProperty("ecom.quick.entry.prod.red.color"))) {
     		validateDisplayedElement(getProperty("ecom.quick.entry.prod.red.color"), "Product Conversion Opportunity");
    		pauseInSeconds(1);

         }
         pauseInSeconds(2);
         if(isExists(getProperty("ecom.confirm.button"))) {
        	 clickElement(getProperty("ecom.confirm.button"), "Clicked confirm button");
         }
         pauseInSeconds(2);
         //
        String prodDesc=getText(getProperty("ecom.quick.entry.prod.desc.text"));
 		pauseInSeconds(1);
        clickElement(getProperty("ecom.quick.entry.prod.desc.text"), "Clicked Product description");
 		pauseInSeconds(2);
 		waitForLoad();
 		validateDisplayedElement(getProperty("ecom.pdp.blue.header"), "Blue header");
 		pauseInSeconds(1);
 		waitForLoad();
		validateDisplayedElement("//span[contains(.,'" + prodDesc + "')]", prodDesc);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.pdp.view.suggested.prod.link"), "Clicked \"View Suggested Product\" link");
		pauseInSeconds(2);
		waitForLoad();
		validateProductAtSuggestedUpdatePage(pdname);
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.back.prod.details.link"), "Clicked  \"Back roduct details\" link");
		waitForLoad();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.back.quick.entry.link"), "Clicked \"Back quick entry\" link");
		waitForLoad();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.quick.view.sugg.prod"), "Clicked view suggested product link");
		pauseInSeconds(2);
		waitForLoad();
		validateProductAtSuggestedUpdatePage(pdname);
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.back.quick.entry.link"), "Clicked \"Back quick entry\" link");
		waitForLoad();
		pauseInSeconds(1);
		setText(getProperty("ecom.quick.entry.qnty"), "1");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.review.order"), "Clicked \"Review Order\" button");
		pauseInSeconds(3);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
		// Validate Suggested product
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		pauseInSeconds(3);
		confirmAndResolveException();
		pauseInSeconds(2);
		waitForLoad();
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
		}
		captureScreenShot("Page Screenshot");

	}


}
