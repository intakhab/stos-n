package com.hcl.usf.pages;

import com.hcl.usf.util.AppUtil;

/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class SuggestedSellProductComparePage extends CommonPageElement {

	/***
	 * 
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void valdiateSuggestedSellPDPBlueLinkHeader(String customerNo, String departNo) {
		// Login and search Customer
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			searchList(lname);// Search Product
			pauseInSeconds(2);
			int x=filterProduct(pname); // Filter product
			for(int k=0;k<x;) {
				captureScreenShot("Page Screenshot");
				clickToPDPPageFromProdImageIcon(i);
				captureScreenShot("Page Screenshot");
				break;
			}
		}
		iMDone();
	}

	

	/***
	 * 
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void valdiateSuggestedSellProductCompare(String customerNo, String departNo) {
		// Login and search Customer
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		// searchList(findData("ListName"));
		// waitForLoad();
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
			String[] list = { "N", "OG", "MS" };

			for (int i = 0; i < list.length; i++) {
				String lname = findData(list[i] + "ListName");
				String pname = findData(list[i] + "ProductName");
				//
				commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
				productCompareWithList(lname, pname,list[i]);
			}

		} else {
			commonInfoLog("Customer having not suggeseted products setup, please setup data properly");
		}
	}

	private void productCompareWithList(String listName, String pd,String type) {
		pauseInSeconds(1);
		searchList(listName);// Search Product
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(3);
		validateSuggestedProductCompare(pd, listName); // Validate Suggested Product
		pauseInSeconds(1);
		jsScrollWindowUp();// Up
		pauseInSeconds(1);
		searchList(listName);// Search Product
		waitForLoad();
		pauseInSeconds(1);
		filterProduct(pd); // Filter product
		pauseInSeconds(1);
		validatelistHeaderAtListProductPage(listName);
		pauseInSeconds(1);
		String searchProdFromPage = suggestedProductValidation();// Main
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.product.compare.link"), "Compare link");
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.product.compare.link"), "Clicked Compare link");
		pauseInSeconds(2);
		boolean nextStep=validateProductCompareCommonSteps(pd, "yes",type,listName);
		pauseInSeconds(1);
		if(nextStep) {
		  afterAcceptButtonSelect(searchProdFromPage);
		}
	}

	/***
	 * After selecting accept button it will gain validate suggested product replace
	 * to original product
	 * 
	 * @param searchProdFromPage
	 */
	private void afterAcceptButtonSelect(String searchProdFromPage) {
		consoleInfoLog("After Acccpet button-->"+searchProdFromPage);
		int x = filterProduct(searchProdFromPage);// Here after accept sugg prod will change to current prod
		for (int i = 0; i < x;) {
			String currentPro = deletePropertyDeliminator(getProperty("ecom.original.prod.name"), i);
			validateDisplayedElement(currentPro, "Current product");
			validateDisplayedElement(deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i),
					"Product Quantity Box");
			break;
		}
	}

	/***
	 * This method will validate all component of current and suggested textbox
	 */
	private String suggestedProductValidation() {
		String searchProdFromPage = "";
		for (int i = 0; i < 1; i++) {
			pauseInSeconds(1);
			searchProdFromPage=validateSuggestedSellFilterProduct(i);
			// Validating suggested product text box
			validatePackSize(i);
			pauseInSeconds(1);
			String checkBoxPath = deletePropertyDeliminator(getProperty("ecom.accept.suggestion.checkbox"), (i + 1));
			validateDisplayedElement(checkBoxPath, "Accept Suggestion");
			String suggTextBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (i + 1));
			String originalTextBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
			pauseInSeconds(1);
			putValueInQuantityBox(originalTextBoxPathCS, 2);
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			pauseInSeconds(1);
			putValueInQuantityBox(suggTextBoxPathCS, 1);
			//
			validateRightRailCurrentOrdAndQntity();
		}
		return searchProdFromPage;
	}

	private boolean validateProductCompareCommonSteps(String pd, String accept,String type,String listName) {
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		boolean isDisabled=false;
		validateDisplayedElement(getProperty("ecom.product.compare.header"), "Product Comparison header");
		validateDisplayedElement(getProperty("ecom.product.current.header"), "Current Product header");
		validateDisplayedElement(getProperty("ecom.product.suggested.header2"), "Suggested Product header");
		scrollIntoViewJS(getProperty("ecom.product.accept"), 100);// Automatically up
		validateDisplayedElement(getProperty("ecom.product.accept"), "Acccept");
		pauseInSeconds(1);
		validateProductAtSuggestedUpdatePage(pd);
		if ("yes".equalsIgnoreCase(accept)) {
			 isDisabled=isElementDisabled(getProperty("ecom.product.accept"), "Accept Button");
			if(isDisabled && "N".equals(type)) {
				commonInfoLog("Clicked accept button");
				actionMoveJSClick(findElementByXpath(getProperty("ecom.product.accept")));
			}else {
				jsScrollWindowUp();// UP windows
				pauseInSeconds(1);
			    searchList(listName);// Search Product
			}
		} else {

			jsScrollWindowUp();// UP windows
		}
		return isDisabled;
	}


     /***
      * Private method
      * @param productName {@link String}
      * @param listName {@link String}
      */
	private void validateSuggestedProductCompare(String productName, String listName) {
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		clickElement(getProperty("ecom.suggested.update"), "Clicked suggested product updates link");
		pauseInSeconds(2);
		boolean b=validateProductAtSuggestedUpdatePage(productName);
		if(!b) {
			return;
		}
		String compXpath = getProperty("ecom.suggested.product.compare").replaceAll(AppUtil.DELIMINATOR, productName);
		pauseInSeconds(1);
		scrollIntoViewJS(compXpath, -100);
		pauseInSeconds(1);
		validateDisplayedElement(compXpath, "Compare link");
		pauseInSeconds(1);
		clickElement(compXpath, "Clicked suggested product updates Compare link");
		pauseInSeconds(3);
		validateProductCompareCommonSteps(productName, "no","",listName);//null pass
	}
	
}
