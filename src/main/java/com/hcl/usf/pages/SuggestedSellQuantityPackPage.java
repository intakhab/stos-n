package com.hcl.usf.pages;

import com.hcl.usf.util.AppUtil;

/**
 * 
 * @author intakhabalam.s@hcl.com user story-14,30 (4.5), ECR46-762
 *
 */
public class SuggestedSellQuantityPackPage extends CommonPageElement {

	/***
	 * IAS
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void validateOriginalProdBacktoList(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		pauseInSeconds(2);
		String[] list = { "N" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			originalProdBacktoList(lname, pname);
		}

		iMDone();
	}

	/***
	 * EC45-509
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateSuggAlternateProdMSLStatusTwo(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(2);
		String[] list = { "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			validateAlternativeProduct(lname, pname);
		}

		iMDone();
	}

	/***
	 * @param lname {@link String}
	 * @param pname {@link String}
	 */
	private void validateAlternativeProduct(String lname, String pname) {
		pauseInSeconds(2);
		searchList(lname);
		waitForLoad();
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.alternative.text"))) {
			scrollIntoViewJS(getProperty("ecom.alternative.text"), -270);// Automatically up
			validateDisplayedElement(getProperty("ecom.alternative.text"), "Alternative product");
			validateDisplayedElement(getProperty("ecom.alternative.show.hide"), "Show product link");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.alternative.show.hide"), "Clicked show link");
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.original.prod.text"))) {
				validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original product");
			}
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.alternative.prod.text"))) {
				validateDisplayedElement(getProperty("ecom.alternative.prod.text"), "Alternative product");
			}
			pauseInSeconds(1);
			if (isExists(getProperty("ecom.alternative.sugg.prod.text"))) {
				validateDisplayedElement(getProperty("ecom.alternative.sugg.prod.text"), "Suggested product");
			}

			pauseInSeconds(2);
		} else {
			commonFailWithScreenShotLog("Not alternative product founds for list-" + lname);
		}
		iMDone();
	}

	// Stroy-43
	public void valdiateOrderFlowWithOgMsl(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		String[] list = { "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			pauseInSeconds(2);
			searchList(lname);
			waitForLoad();
			validateQuantityProdAtSuggUpdatePage(pname, "");
			captureScreenShot("Page Screenshot");
			pauseInSeconds(2);
			searchList(lname);// Again Search
			pauseInSeconds(2);
			waitForLoad();
			int size = filterProduct(pname);
			commonInfoLog("Product size after search :: " + size);
			waitForLoad();
			for (int k = 0; k < 1;) {
				validateSuggestedSellFilterProduct(k);
				pauseInSeconds(1);
				validatePackSize(k);
				pauseInSeconds(1);
				clickReviewAndSubmitOrder();
				pauseInSeconds(1);
				if (confirmOrderText()) {
					commonInfoLog("Order Confirmation!!!");
				}
				pauseInSeconds(1);
				captureScreenShot("Page Screenshot");
				break;
			}
		}

		iMDone();
	}

	/***
	 * 
	 * @param lname
	 * @param pname
	 */
	private void originalProdBacktoList(String lname, String pname) {

		boolean nextStep = false;
		// Login and search Customer
		searchList(lname);
		waitForLoad();
		// validateProdAtSuggUpdatePage(pname);
		validateHomeSuggestedSell("");
		waitForLoad();
		searchList(lname); // Again search the NListName
		int size = filterProduct(pname);
		pauseInSeconds(2);
		for (int i = 0; i < size;) {
			pauseInSeconds(1);

			String checkBoxPath = deletePropertyDeliminator(getProperty("ecom.accept.suggestion.checkbox"), (i + 1));
			// Validate check box
			validateDisplayedElement(checkBoxPath, "Accept Suggestion");
			String text = waitForVisibilityOfElementLocated(
					deletePropertyDeliminator(getProperty("ecom.suggested.prod.name"), (i + 1))).getText();
			commonInfoLog("Suggested Product Name " + text);
			clickElement(checkBoxPath, "Clicked checkbox suggestion");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.continue.button"), "Continue Button");
			pauseInSeconds(3);
			jsScrollWindowUp();
			nextStep = true;
			captureScreenShot("Page Screenshot");
			break;
		}
		//
		if (nextStep) {
			pauseInSeconds(1);
			jsScrollTop();
			pauseInSeconds(1);
			clickElement(getProperty("ecom.prod.action.link"), "Clicked actions link");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.prod.action.edit.list.link"), "Clicked edit list");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.prod.add.prod.link"), "Clicked add produuct link");
			pauseInSeconds(1);
			setText(getProperty("ecom.prod.add.input"), pname);
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.add.prod.button"), "Clicked \"Add Product Button\"");
			pauseInSeconds(1);
			searchList(lname);
			size = filterProduct(pname);
			pauseInSeconds(2);
			validateProductAtSuggestedUpdatePage(pname);

		} else {
			commonFailWithScreenShotLog("Pls setup data properly");
		}

	}

	/***
	 * 
	 * @param customerNo {@link String}
	 * @param departNo   {@link String}
	 */
	public void valdiateDisplayQuantitySizePack(String customerNo, String departNo) {
		String searchProdFromPage = "";
		boolean nextStep = false;
		// Login and search Customer
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC2
		searchList(findData("NListName"));// listname
		waitForLoad();
		pauseInSeconds(1);
		validateProdAtSuggUpdatePage(findData("NProductName"));// Prodname
		waitForLoad();
		pauseInSeconds(1);
		searchList(findData("NListName")); // Again search the NListName
		pauseInSeconds(1);
		int size = filterProduct(findData("NProductName"));
		pauseInSeconds(2);
		for (int i = 1; i < size;) {
			pauseInSeconds(1);
			scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
			// Validate Suggested product
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
			// Validate pack size box
			// scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), 200);//
			// Automatically up
			pauseInSeconds(1);
			String originalPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"), (i - 1));
			String orignalPack = waitForVisibilityOfElementLocated(originalPackXpath).getText();
			commonInfoLog("Original Pack size " + orignalPack);
			String suggestedPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"), i);
			String suggestedPack = waitForVisibilityOfElementLocated(suggestedPackXpath).getText();
			commonInfoLog("Suggested Pack  size " + suggestedPack);
			if (orignalPack.trim().equalsIgnoreCase(suggestedPack.trim())) {
				commonPassLog("Original Pack and Suggested Pack size are same");
			} else {
				if (isExists(getProperty("ecom.packsize.different.text"))) {
					validateDisplayedElement(getProperty("ecom.packsize.different.text"), "Pack size is different");
					commonPassLog("Original Pack and Suggested Pack size are different");
				}
			}

			String checkBoxPath = deletePropertyDeliminator(getProperty("ecom.accept.suggestion.checkbox"), i);
			// Validate check box
			validateDisplayedElement(checkBoxPath, "Accept Suggestion");
			String text = getText(deletePropertyDeliminator(getProperty("ecom.suggested.prod.name"), i));
			commonInfoLog("Suggested Product Name " + text);
			// Validating suggested product text box
			String textBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (i - 1));
			pauseInSeconds(1);
			String textBoxPathEA = deletePropertyDeliminator(getProperty("ecom.quantity.textbox.ea"), i);
			// Validating CS Text
			validateDisplayedElement(textBoxPathCS, "Suggested Product CS Text Box");
			// Validating EA Text
			if (isExists(textBoxPathEA)) {
				validateDisplayedElement(textBoxPathEA, "Suggested Product EA Text Box");
			}
			putValueInQuantityBox(textBoxPathCS, 1);
			captureScreenShot("Page Screenshot");
			pauseInSeconds(1);
			checkOrderPopUp();// Generic Popup
			nextStep = true;
			break;
		}
		//
		if (nextStep) {
			clickReviewAndSubmitOrder();
			if (confirmOrderText()) {
				commonInfoLog("Order Confirmation!!!");
			}
			captureScreenShot("Page Screenshot");
			pauseInSeconds(2);
			// Verify that If user uses quantity box after checking "Accept suggestion"
			// check box,
			// then the Original Product will be replaced by the suggested product in the
			// regular list.
			searchList(findData("NListName"));
			int size2 = filterProduct(findData("NProductName"));
			// Search the filter box with prod name
			pauseInSeconds(1);
			for (int k = 1; k < size2;) {
				// Validate check box
				pauseInSeconds(1);
				scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);
				pauseInSeconds(2);
				String prodXpath = deletePropertyDeliminator(getProperty("ecom.suggested.prod.name"), k);
				String text = waitForVisibilityOfElementLocated(prodXpath).getText();
				searchProdFromPage = text.split(" ")[0].replaceAll("#", "").trim();
				commonInfoLog("Sugested Product Name  " + searchProdFromPage);
				String suggestionBoxPath = deletePropertyDeliminator(getProperty("ecom.accept.suggestion.checkbox"), k);
				validateDisplayedElement(getProperty("ecom.accept.suggestion"), "Accept Suggestion");
				pauseInSeconds(1);
				clickElement(suggestionBoxPath, "Clicked Suggested Check Box");
				pauseInSeconds(6);
				clickElement(getProperty("ecom.continue.button"), "Continue Button");
				waitForLoad();
				pauseInSeconds(4);
				jsScrollWindowUp();
				pauseInSeconds(2);
				searchFilter(searchProdFromPage);
				pauseInSeconds(1);
				validateDisplayedElement(deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (k - 1)),
						"Suggested Product Text Box");
				commonInfoLog("Done!");
				pauseInSeconds(1);
				iMDone();
				break;
			}
		} else {
			commonFailWithScreenShotLog("Pls setup data properly");
		}
	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	// Story-30
	public void validateSuggestedProductQntySizePack(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			String searchProdFromPage = shoppingListOrder(lname, pname, "withoutsuggestion", "", list[i]);
			shoppingListOrder(lname, pname, "withsuggestion", searchProdFromPage, list[i]);
		}

		iMDone();
	}

	/***
	 * 
	 * @param lname              {@link String}
	 * @param pname              {@link String}
	 * @param type               {@link String}
	 * @param searchProdFromPage {@link String}
	 * @return {@link String}
	 */
	private String shoppingListOrder(String lname, String pname, String type, String searchProdFromPage, String list) {
		pauseInSeconds(2);
		searchList(lname);
		waitForLoad();
		validateQuantityProdAtSuggUpdatePage(pname, type);
		captureScreenShot("Page Screenshot");
		pauseInSeconds(2);
		searchList(lname);// Again Search
		pauseInSeconds(2);
		int size = 0;
		if ("withsuggestion".equalsIgnoreCase(type)) {
			size = filterProduct(searchProdFromPage);
		} else {
			size = filterProduct(pname);
		}
		pauseInSeconds(2);
		for (int k = 0; k < size;) {
			if ("withoutsuggestion".equalsIgnoreCase(type)) {
				scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
				pauseInSeconds(1);
				if ("N".equalsIgnoreCase(list)) {
					String prodXpath = deletePropertyDeliminator(getProperty("ecom.suggested.prod.name"), (k + 1));
					String text = waitForVisibilityOfElementLocated(prodXpath).getText();
					searchProdFromPage = text.split(" ")[0].replaceAll("#", "").trim();
					commonInfoLog("Sugested Product Name  " + searchProdFromPage);
				} else {
					String prodXpath = deletePropertyDeliminator(getProperty("ecom.original.prod.name"), k);
					String text = waitForVisibilityOfElementLocated(prodXpath).getText();
					searchProdFromPage = text.split(" ")[0].replaceAll("#", "").trim();
				}
				String textBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (k + 1));
				validateDisplayedElement(textBoxPathCS, "Suggested Product CS Text Box");
			} else {
				String currentProd = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), k);
				validateDisplayedElement(currentProd, "Current Product Text Box");
			}
			clickReviewAndSubmitOrder();
			pauseInSeconds(1);
			if (confirmOrderText()) {
				commonInfoLog("Order Confirmation!!!");
			}
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
			break;
		}
		return searchProdFromPage;
	}

	/***
	 */
	private void validateProdAtSuggUpdatePage(String pname) {
		validateHomeSuggestedSell("");
		validateProductAtSuggestedUpdatePage(pname);
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
	}

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
		// validate lbl
		String currl1Xpath = getProperty("ecom.pdp.curr.quantity.label").replaceAll(AppUtil.DELIMINATOR, productName);
		String currlbl1Text = getText(currl1Xpath).split(" ")[0];
		pauseInSeconds(1);
		// String currlbl2Text = getText(currl1Xpath).split(" ")[1];
		String suggl1Xpath = getProperty("ecom.pdp.sugg.quantity.label2").replaceAll(AppUtil.DELIMINATOR, productName)
				.replaceAll("###", "5");
		if (getText(suggl1Xpath).contains("#")) {
			suggl1Xpath = getProperty("ecom.pdp.sugg.quantity.label2").replaceAll(AppUtil.DELIMINATOR, productName)
					.replaceAll("###", "6");
			/*
			 * if(getText(suggl1Xpath).contains("#")) {
			 * suggl1Xpath=getProperty("ecom.pdp.sugg.quantity.label2").replaceAll(AppUtil.
			 * DELIMINATOR, productName) .replaceAll("###", "6"); }
			 */
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
		pauseInSeconds(2);
		setText(quantityXpath, "1");
		pauseInSeconds(2);
		checkOrderPopUp();// Popup modification
		pauseInSeconds(3);
		if ("withsuggestion".equalsIgnoreCase(type)) {
			boolean bb = validateProductAtSuggestedUpdatePage(productName);
			if (!bb) {
				return;
			}
			pauseInSeconds(1);
			clickElement(getProperty("ecom.pdp.great.radio.button").replaceAll(AppUtil.DELIMINATOR, productName),
					"Clicked Great! Switch to Suggested Product.");
			pauseInSeconds(2);
			// scrollIntoViewJS(getProperty("ecom.update"), -150);
			clickElement(getProperty("ecom.update"), "Clicked Update button");
			pauseInSeconds(2);
		}
	}

	/***
	 * Reusable method
	 * 
	 * @param type
	 */
	private void validateHomeSuggestedSell(String type) {
		waitForLoad();
		pauseInSeconds(1);
		homeLink();
		pauseInSeconds(3);
		waitForLoad();
		if ("withoutsuggestion".equalsIgnoreCase(type) || type.isEmpty()) {
			validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"),
					"Suggested Products Right Rail");
			pauseInSeconds(1);
		}
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

	/**
	 * EC46-69
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateSuggestedSellNonLMACustomer(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(2);
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			validateRightRailNotificationAndSuggProduct(lname, pname);
		}
		iMDone();
	}

	public void validateSuggestedSellLMACustomer(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		pauseInSeconds(2);
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			validateRightRailNotificationAndSuggProductLMACust(lname, pname);
		}
		iMDone();
	}

	/***
	 * @param lname {@link String}
	 * @param pname {@link String}
	 */
	private void validateRightRailNotificationAndSuggProduct(String lname, String pname) {
		waitForLoad();
		searchList(lname);
		waitForLoad();
		pauseInSeconds(1);
		homeLink();
		pauseInSeconds(3);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"), "Suggested Products Right Rail");
		clickElement(getProperty("ecom.suggested.prod.right.rail.text"), "Clicked Suggested Products Right Rail");
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.product.updates"), "Suggested Products header");
		pauseInSeconds(1);
		validateProductAtSuggestedUpdatePage(pname);
		pauseInSeconds(1);
		jsScrollTop();
	}

	private void validateRightRailNotificationAndSuggProductLMACust(String lname, String pname) {
		waitForLoad();
		searchList(lname);
		waitForLoad();
		pauseInSeconds(1);
		homeLink();
		pauseInSeconds(3);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"), "Suggested Products Right Rail");
		clickElement(getProperty("ecom.suggested.prod.right.rail.text"), "Clicked Suggested Products Right Rail");
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.product.updates"), "Suggested Products header");
		pauseInSeconds(1);
		validateProductAtSuggestedUpdatePage(pname);
		pauseInSeconds(1);
		jsScrollTop();
	}
}
