package com.hcl.usf.pages;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com
 */
public class SuggestedSellAcceptSuggestionPage extends CommonPageElement {

	/***
	 * EC45-12
	 * @param customerNo {@link String}
	 * @param departNo   {@link String}
	 */
	public void valdiateSuggestedSellProductAccept(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			suggestedSellProductAccept(lname, pname, list[i]);
		}
		iMDone();
	}

	/***
	 * EC45-416
	 * @param customerNo {@link String}
	 * @param departNo {@link String}
	 */
	public void valdiateSuggestedSellProductAcceptOGMSL(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			suggestedSellProductAccept(lname, pname, list[i]);
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
	public void suggestedSellProductAccept(String listname, String prdname, String type) {
		String suggestedPageText = "";
		searchList(listname);// list name
		waitForLoad();
		validateQuantityProdAtSuggUpdatePage(prdname, type);
		waitForLoad();
		searchList(listname); // Again search the list name
		pauseInSeconds(2);
		int size = filterProduct(prdname);
		pauseInSeconds(1);
		for (int i = 0; i < size;) {
			pauseInSeconds(2);
			// Validate suggested product and quantity box
			suggestedPageText = validateSuggestedSellFilterProduct(i);
			// Validate Pack size
			validatePackSize(i);
			validateAcceptSuggestionAndClick(i);
			if ("N".equalsIgnoreCase(type)) {
				this.reuseValidation(i, suggestedPageText);
			}
			// again validate suggestion list
			if (isExists(getProperty("ecom.suggested.prod.list.updating.text"))) {
				validateDisplayedElement(getProperty("ecom.suggested.prod.list.updating.text"),
						"Updating suggested list");
				pauseInSeconds(1);

			}

			break;
		}
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
	}

	/****
	 * @param listname
	 * @param prdname
	 * @param type
	 */
	public void suggestedSellProductAcceptOGMSL(String listname, String prdname, String type) {
		String suggestedPageText = "";
		waitForLoad();
		searchList(listname); // Again search the list name
		pauseInSeconds(2);
		int size = filterProduct(prdname);
		pauseInSeconds(1);
		for (int i = 0; i < size;) {
			pauseInSeconds(2);
			// Validate suggested product and quantity box
			suggestedPageText = validateSuggestedSellFilterProduct(i);
			// Validate Pack size
			validatePackSize(i);
			// validateAcceptSuggestionAndClick(i);
			validateDisplayedElement(getProperty("ecom.accept.suggestion"), "Accept Suggestion");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.accept.suggestion.checkbox.new"), "Clicked Suggested Check Box");
			// Validate check box
			pauseInSeconds(5);
			clickElement(getProperty("ecom.suggested.popup.list.including.radio"), "Clicked seconds radio button");
			pauseInSeconds(1);
			String noteText=getText(getProperty("ecom.suggested.popup.note.text"));
			if(noteText.contains("If you have specific questions about your lists, please contact customer service or your sales representative.")) {
				validateDisplayedElement(getProperty("ecom.suggested.popup.note.text"),
						getText(getProperty("ecom.suggested.popup.note.text")));
			}
			pauseInSeconds(2);
			if (isExists(getProperty("ecom.continue.button"))) {
				clickElement(getProperty("ecom.continue.button"), "Continue Button");
				pauseInSeconds(3);
			}
			// Scroll windows up
			jsScrollWindowUp();
			pauseInSeconds(2);
			// Other things
			this.reuseValidation(i, suggestedPageText);
			if (isExists(getProperty("ecom.suggested.prod.list.updating.text"))) {
				validateDisplayedElement(getProperty("ecom.suggested.prod.list.updating.text"),
						"Updating suggested list");
				pauseInSeconds(1);

			}
			break;
		}
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
	}

	/***
	 * 
	 * @param i
	 * @param suggestedPageText
	 */
	private void reuseValidation(int i, String suggestedPageText) {
		if (suggestedPageText != null && !suggestedPageText.isEmpty()) {
			searchFilter(suggestedPageText);//
			pauseInSeconds(2);
			validateDisplayedElement(deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), i),
					"Suggested Product Text Box");
			String suggXpath = "//span[contains(.,'# " + suggestedPageText + "') or contains(.,'#" + suggestedPageText
					+ "')]";
			pauseInSeconds(1);
			validateDisplayedElement(suggXpath, suggestedPageText);
			pauseInSeconds(1);
		}
		// again validate suggestion list

	}

	/**
	 * @param productName
	 *            {@link String}
	 * @param type
	 *            {@link String}
	 */
	private void validateQuantityProdAtSuggUpdatePage(String productName, String type) {
		validateHomeSuggestedSell(type);
		pauseInSeconds(1);
		boolean b = validateProductAtSuggestedUpdatePage(productName);
		if (!b) {
			return;
		}
		pauseInSeconds(1);
		String quantityXpath = getProperty("ecom.suggested.product.qnty.text").replaceAll(AppUtil.DELIMINATOR,
				productName);
		scrollIntoViewJS(quantityXpath, -300);
		String currl1Xpath = getProperty("ecom.pdp.curr.quantity.label").replaceAll(AppUtil.DELIMINATOR, productName);
		String currlbl1Text = getText(currl1Xpath).split(" ")[0];
		pauseInSeconds(1);
		String suggl1Xpath = getProperty("ecom.pdp.sugg.quantity.label2").replaceAll(AppUtil.DELIMINATOR, productName)
				.replaceAll("###", "5");
		if (getText(suggl1Xpath).contains("#")) {
			suggl1Xpath = getProperty("ecom.pdp.sugg.quantity.label2").replaceAll(AppUtil.DELIMINATOR, productName)
					.replaceAll("###", "6");
		}
		String sugglbl1Text = getText(suggl1Xpath).split(" ")[0];
		// validate qntity
		validateDisplayedElement(quantityXpath, "Quantity box");
		pauseInSeconds(1);
		String quantity2Xpath = getProperty("ecom.suggested.product.qnty2.text").replaceAll(AppUtil.DELIMINATOR,
				productName);
		if (isExists(quantity2Xpath)) {
			validateDisplayedElement(quantity2Xpath, "Quantity box");
		}
		//
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

		jsScrollTop();
	}

	/***
	 * Reusable method
	 * @param type
	 *            {@link String}
	 */
	private void validateHomeSuggestedSell(String type) {
		waitForLoad();
		homeLink();
		pauseInSeconds(3);
		waitForLoad();
		if ("N".equals(type)) {
			validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"),
					"Suggested Products Right Rail");
		}
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		// pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.update"), "Suggested Product Updates");
		// pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		clickElement(getProperty("ecom.suggested.update"), "Clicked suggested product updates link");
		pauseInSeconds(1);
	}
    /***
     * ECR46-732
     * @param customerId
     * @param departmentId
     */
	public void validateSuggestedSellAcceptAllProduct(String customerId, String departmentId) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerId, departmentId);
		// validatingTC
		waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		pauseInSeconds(1);
		if (isExists(getProperty("ecom.suggested.update"))) {
			commonInfoLog("Customer having Suggested Products");
			clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates"),	"Suggested sell header");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.accept.all.suggested.checkbox"), "Clicked Select all suggested products");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.update"), "Update Button");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.update"), "Clicked update button");
			waitForLoad();//
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.your.shopping.text"),
					"Your shopping lists have been updated with the Suggested Products selected");
			pauseInSeconds(2);
		} else {
			commonInfoLog("Customer does not having Suggested Products");
		}
		pauseInSeconds(1);
		iMDone();
		
	}

}
