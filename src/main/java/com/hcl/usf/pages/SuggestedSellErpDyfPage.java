package com.hcl.usf.pages;

import org.openqa.selenium.WebDriverException;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com
 * 
 */

public class SuggestedSellErpDyfPage extends CommonPageElement {

	/***
	 * EC45-270
	 * EC45-271
	 * @param customerId
	 * @param departmentId
	 */
	public void validateTestErpDyfBanner(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		for (int i = 1; i <= 3; i++) {
			String xPath1 = getProperty("ecom.inprogress.text").replaceAll(AppUtil.DELIMINATOR, "" + i);
			if (isExists(xPath1)) {
				clickElement(xPath1, "Clicked in progress order in home page");
				pauseInSeconds(2);
				break;
			}
		}

		waitForLoad();
		pauseInSeconds(2);
		//
		validateMessages();
		//
		searchList(findData("ListName"));
		pauseInSeconds(2);
		filterProduct(findData("ProductName")); //Done filter
		pauseInSeconds(2);
		if(isExists(getProperty("ecom.suggested.prod.text2"))) {
		    scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
		    consoleInfoLog("Suggested prodcut found");
		}else {
			consoleInfoLog("Normal prodcut found");
		}
		String textBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), 0);// index 0 assume original product quantity
		putValueInQuantityBox(textBoxPathCS, 1);
		pauseInSeconds(2);
		captureScreenShot("Page Screenshot");
		pauseInSeconds(1);
		checkOrderPopUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.review.order"), "Clicked review order button");
		waitForLoad();
		pauseInSeconds(3);
		validateMessages();
		iMDone();
	}

	
	/***
	 * EC45-64
	 * EC45-180
	 * Both USer story merge, Functionality wise both same are same
	 * difference is only with suggested checkbox product    
	 * @param customerId {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateSuggestedSellErpDyf(String customerId, String departmentId) {
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
			pauseInSeconds(5);
			waitForLoad();
			// call for List
			String[] list = { "N" };
			for (int i = 0; i < list.length; i++) {
				String lname = findData(list[i] + "ListName");
				String pname = findData(list[i] + "ProductName");
				//
				commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
				suggestedProductBanner(lname, pname,"without");
				suggestedProductBanner(lname, pname,"with");

			}

		} else {
			commonInfoLog("Customer having not suggeseted products setup, please setup data properly");
		}
		iMDone();
	}
	
	
	/**
	 * Common with Suggested product and without suggested product
	 * @param listName  {@link String} 
	 * @param pd  {@link String} 
	 * @param type {@link String} 
	 */
	private void suggestedProductBanner(String listName, String pd,String type) {
		commonInfoLog("Running with " + "with".equals(type) != null ? "With Suggested Product checkbox click"
				: "Without Suggested product");
		searchList(listName); // Again search the NListName
		pauseInSeconds(2);
		int size = filterProduct(pd);
		pauseInSeconds(2);
		waitForLoad();
		validatelistHeaderAtListProductPage(listName);
		pauseInSeconds(2);
		this.reviewOrderDyfEprValiation(size, pd,type);
	}

	
	/***
	 * This method will validate all component of current and suggested textbox
	 */
	private String reviewOrderDyfEprValiation(int size, String pdname,String type) {
		String searchProdFromPage = "";
		if(size>3) {
			throw new WebDriverException("Data filter/Product search not worked.");
		}
		for (int i = 0; i < size;) {
				pauseInSeconds(1);
				String orgTextBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i);
				pauseInSeconds(1);
				boolean b=putValueInQuantityBox(orgTextBoxPathCS, 1);
				if(b) {
					pauseInSeconds(1);
					checkOrderPopUp();//Generic Popup
				}else {
					throw new WebDriverException("Quantity box was disabled...");
				}
				pauseInSeconds(2);
				validateMessages(); //Validate CMA,DYF and others here...
				if(isExists(getProperty("ecom.cma.view.product"))) {
					clickElement(getProperty("ecom.cma.view.product"), "Clicked ema view product link");
				}
				pauseInSeconds(2);
				searchProdFromPage=validateSuggestedSellFilterProduct(i);// important on common
				pauseInSeconds(1);
				validatePackSize(i);
				pauseInSeconds(1);
				if("with".equals(type)) {
					validateAcceptSuggestionAndClick(i);
					pauseInSeconds(2);
					jsScrollWindowUp();
					commonInfoLog("Suggested prodcut name :"+searchProdFromPage);
					size = filterProduct(searchProdFromPage);
					pauseInSeconds(2);
			    	validateProductAtSuggestedUpdatePage(searchProdFromPage);
					validateDisplayedElement(deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i),
							"Suggested Product Text Box");
				}
				pauseInSeconds(1);
				validateRightRailCurrentOrdAndQntity(); // Right Rail
				consoleInfoLog("Review steps");
				// Click Review Order
				clickElement(getProperty("ecom.review.order"), "Clicked \"Review Order\" button");
				pauseInSeconds(3);
				waitForLoad();
				if(isExists(getProperty("ecom.review.order.header"))) {
					validateDisplayedElement(getProperty("ecom.review.order.header"), "Review order header");
				}
				pauseInSeconds(1);
				validateMessages(); //Validate CMA,DYF and others here...
				if(isExists(getProperty("ecom.cma.view.product"))) {
					clickElement(getProperty("ecom.cma.view.product"), "Clicked ema view product link");
				}
				validateDisplayedElement(getProperty("ecom.cma.product.text"), "New CMA's Now Available text");
				String text=getText(getProperty("ecom.cma.product.name"));
				commonInfoLog("CMA product "+text);				validateDisplayedElement(getProperty("ecom.cma.product.name"), text);
				setText(getProperty("ecom.cma.product.input"),"2");
				//validateRightRailCurrentOrdAndQntity(); // Right Rail
				clickElement(getProperty("ecom.review.order"), "Clicked \"Review Order\" button");
				pauseInSeconds(3);
				waitForLoad();
				validateDisplayedElement(getProperty("ecom.cma.product.text"), "New CMA's Now Available text");
               pauseInSeconds(1);
               clickElement(getProperty("ecom.review.order.inactive.button"), "Clicked inactive button");
               pauseInSeconds(2);
               jsScrollDown();
               validateProductAtSuggestedUpdatePage(pdname);
               jsScrollTop();
               break;
		}
		return searchProdFromPage;
	}


	/***
	 * 
	 */
	private void validateMessages() {
		validateDyf();
		validateWul();
		validateEMA();
	}

	private void validateEMA() {
		if (isExists(getProperty("ecom.cma.product.text"))) {
			scrollIntoViewJS(getProperty("ecom.cma.product.text"), -200);
			validateDisplayedElement(getProperty("ecom.cma.product.text"), "New CMA's Now Available text");
			pauseInSeconds(2);
		}

	}

	private void validateDyf() {
		if (isExists(getProperty("ecom.dyf.text"))) {
			validateDisplayedElement(getProperty("ecom.dyf.text"), "Did You Forget Something text");
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		if(isExists(getProperty("ecom.forgotten.product"))) {
			clickElement(getProperty("ecom.forgotten.product"), "Clicked forgotten link");
			pauseInSeconds(1);

		}
		pauseInSeconds(1);
		if(isExists(getProperty("ecom.forgotten.product.text"))) {
			validateDisplayedElement(getProperty("ecom.forgotten.product.text"), "Forgotten text");
			pauseInSeconds(1);

		}

	}

	private void validateWul() {
		if (isExists(getProperty("ecom.would.you.like"))) {
			validateDisplayedElement(getProperty("ecom.would.you.like"), "Would You Like To Try text");
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		if(isExists(getProperty("ecom.forgotten.product"))) {
			clickElement(getProperty("ecom.forgotten.product"), "Clicked forgotten link");
			pauseInSeconds(1);

		}
		pauseInSeconds(1);
		if(isExists(getProperty("ecom.forgotten.product.text"))) {
			validateDisplayedElement(getProperty("ecom.forgotten.product.text"), "Forgotten text");
			pauseInSeconds(1);

		}

	}
	

}
