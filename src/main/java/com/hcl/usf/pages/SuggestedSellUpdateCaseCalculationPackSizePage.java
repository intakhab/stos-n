package com.hcl.usf.pages;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com 
 */
public class SuggestedSellUpdateCaseCalculationPackSizePage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void validateSuggestedSellUpdateCaseCalculationPackSize(String customerNo, String departNo) {
		
		commonLoginAndCustomerSearch(customerNo, departNo);
		String[] list = {"N","OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			shoppingListOrder(lname,pname,list[i]);
		}
		iMDone();
	}
	
	/***
	 * @param lname {@link String} 
	 * @param pname {@link String} 
	 * @param type {@link String} 
	 * @param searchProdFromPage {@link String} 
	 * @return {@link String} 
	 */
	private String shoppingListOrder(String lname, String pname 
			,String list) {
		String searchProdFromPage="";
		pauseInSeconds(2);
		searchList(lname);
		waitForLoad();
		validateQuantityProdAtSuggUpdatePage(pname);
		pauseInSeconds(2);
		searchList(lname);//Again Search
		pauseInSeconds(2);
		int size = 0;
		size = filterProduct(pname);
		pauseInSeconds(2);
		for(int k=0;k<size;) {
			pauseInSeconds(1);
			validatelistHeaderAtListProductPage(lname);
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.prod.conversion.header"), "Product Conversion Opportunity");
			// Validate Suggested product
			pauseInSeconds(1);
			scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
			pauseInSeconds(1);
			String originalPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"), k);
			String orignalPackText = waitForVisibilityOfElementLocated(originalPackXpath).getText();
			commonInfoLog("Original Pack size " + orignalPackText);
			String originalProdText = waitForVisibilityOfElementLocated(
					deletePropertyDeliminator(getProperty("ecom.original.prod.name"), k)).getText();
			commonInfoLog("Original Product : " + originalProdText);
			//
			validateDisplayedElement(originalPackXpath, "Original Pack");
			String suggestedPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"), (k+1));
			String suggestedPackTest = waitForVisibilityOfElementLocated(suggestedPackXpath).getText();
			validateDisplayedElement(suggestedPackXpath, "Suggested Pack");
			super.validatePackSize(k);
			this.compareValidation(orignalPackText,suggestedPackTest);
			pauseInSeconds(1);
			String textBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), k);
			boolean b=putValueInQuantityBox(textBoxPathCS, 1);
			captureScreenShot("Page Screenshot");
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			if(b) {
				submitOrderWithValidation(orignalPackText,suggestedPackTest);
			}
			
			break;
		
		}
		return searchProdFromPage;
	}

	
	private void submitOrderWithValidation(String originalPackTest,String suggeseteddPackTest) {
		waitForLoad();
		consoleInfoLog("Review And Submit steps");
		 //Click Review Order
		waitExplicitlyForClickable(getProperty("ecom.review.order")).click();
		commonInfoLog("Review Order button clicked");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.summary.view"), "Clicked summary view");
		pauseInSeconds(2);
		jsScrollDown();
		pauseInSeconds(1);
		String t1="//span[contains(.,'" + originalPackTest + "')]";
		String t2="//span[contains(.,'" + originalPackTest + "')]/following::span[starts-with(.,'"+suggeseteddPackTest+"')]";
		scrollIntoViewJS(t1,-180);// Automatically down
		pauseInSeconds(1);
		validateDisplayedElement(t1, "Original product pack");
		pauseInSeconds(1);
		validateDisplayedElement(t2, "Suggested product pack");
		pauseInSeconds(2);
	    jsScrollTop();
	    pauseInSeconds(2);
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		pauseInSeconds(2);
		waitForLoad();
		conifrmOrderPopUp();
		waitForLoad();
		pauseInSeconds(3);
		checkResolveException();// Sometimes exception comes,
		pauseInSeconds(1);
		waitForLoad();
		consoleInfoLog("Review And Submit steps finished");
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
		}
		captureScreenShot("Page Screenshot");
	}
 
    /***
     * 
     * @param originalProdText  {@link String}
     * @param suggestedPackTest  {@link String}
     */
	private void compareValidation(String originalProdText, String suggestedPackTest) {
		validateDisplayedElement(getProperty("ecom.product.compare.link"), "Compare link");
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.product.compare.link"), "Clicked Compare link");
		validateQuantityLabel(originalProdText,suggestedPackTest);
	    pauseInSeconds(1);
		clickElement(getProperty("ecom.backto.shopping.list.link"), "Clicked back to shopping list");
		pauseInSeconds(2);
	}

    /**
     * 
     * @param originalProdText  {@link String}
     * @param suggestedPackTest {@link String}
     */
	private void validateQuantityLabel(String originalProdText, String suggestedPackTest) {
		validateDisplayedElement(getProperty("ecom.product.compare.header"), "Product Comparison header");
		validateDisplayedElement(getProperty("ecom.product.current.header"), "Current Product header");
		validateDisplayedElement(getProperty("ecom.product.suggested.header2"), "Suggested Product header");
		String t1="//span[contains(.,'" + originalProdText + "')]";
		String t2="//span[contains(.,'" + originalProdText + "')]/following::span[starts-with(.,'"+suggestedPackTest+"')]";
		scrollIntoViewJS(t1,-200);// Automatically down
		validateDisplayedElement(t1, "Original product pack");
		pauseInSeconds(1);
		validateDisplayedElement(t2, "Suggested product pack");
		pauseInSeconds(1);
	    jsScrollTop();
	}


	private void validateQuantityProdAtSuggUpdatePage(String productName) {
		pauseInSeconds(1);
		super.validateSugSellAtMenuDropdownAndRightRailGoToSuggPage();
		pauseInSeconds(1);
		boolean b=validateProductAtSuggestedUpdatePage(productName);
		if(!b) {
			return;
		}
		this.validateCompareProduct(productName);
	}
	/***
	 * 
	 * @param productName
	 */
	private void validateCompareProduct(String productName) {
		pauseInSeconds(1);
		String quantityXpath=getProperty("ecom.suggested.product.qnty.text").replaceAll(AppUtil.DELIMINATOR, productName);
		scrollIntoViewJS(quantityXpath, -220);
		//validate lbl
		String currl1Xpath = getProperty("ecom.pdp.curr.quantity.label").replaceAll(AppUtil.DELIMINATOR, productName);
		String currlbl1Text = getText(currl1Xpath).split(" ")[0];
		pauseInSeconds(1);
		String currlbl2Text = getText(currl1Xpath).split(" ")[1];
		String suggl1Xpath = getProperty("ecom.pdp.sugg.quantity.label").replaceAll(AppUtil.DELIMINATOR, productName)
				.replaceAll("###", currlbl2Text);
		String sugglbl1Text = getText(suggl1Xpath).split(" ")[0];
		//validate quantity
		validateDisplayedElement(quantityXpath, "Quantity box");
		pauseInSeconds(1);
		String quantity2Xpath=getProperty("ecom.suggested.product.qnty2.text").replaceAll(AppUtil.DELIMINATOR, productName);
		if(isExists(quantity2Xpath)) {
			validateDisplayedElement(quantity2Xpath, "Quantity box");
		}
		if (!currlbl1Text.equalsIgnoreCase(sugglbl1Text)) {
			String packdifferent = getProperty("ecom.pdp.diff.qnty.size.text").replaceAll(AppUtil.DELIMINATOR,
					productName);
			pauseInSeconds(1);
			if (isExists(packdifferent)) {
				validateDisplayedElement(packdifferent, "Different pack size");
			}
		} else {
			commonInfoLog("Pack size are same");
		}
		pauseInSeconds(1);
		jsScrollTop();
	}

	
}
