package com.hcl.usf.pages;

import org.openqa.selenium.WebElement;

import com.hcl.usf.util.AppUtil;

/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class TextVerifyCommonChangePage extends CommonPageElement {

	/***
	 * EC45-04 Promotion Link Finding
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void valdiatePromotionsLinkTextChangePage(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		String promotionsText = getProperty("ecom.promotions.link");
		// validatingTC
		highlightElement(getProperty("ecom.our.exculsive"));
		WebElement exculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(exculsive);
		captureScreenShot("Page Screenshot");
		pauseInSeconds(1);

		if (!isExists(promotionsText)) {
			commonFailLog("Customer is without Promotions so Promotions option is not available "
					+ "under Our Exclusive Dropdown");
		} else {
			commonInfoLog("Customer is with Promotions so Promotions option is available ");
			actionMoveToElement(exculsive);
			validateDisplayedElement(promotionsText, "Promotions link at Our Execulsive Menu Drop Down");
			exculsive.click();
			commonInfoLog("Clicked Exculsive Link");
			pauseInSeconds(2);
			scrollIntoViewJS(getProperty("ecom.promotions.text"), -50);
			pauseInSeconds(1);
			String txt = getText(getProperty("ecom.getdetails.text"));
			if (!txt.contains("Get Details")) {
				commonPassLog("At Promotions section Get Details Text is not displayed");
			} else {
				commonFailLog("At Promotions section Get Details Text is displayed");

			}
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.access.promotions.text"), "Access Promotions Text");
		}
		iMDone();
	}

	/***
	 * EC45-501
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */

	public void validateBussinessMLMLandingPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.my.bussiness"));
		pauseInSeconds(1);
		actionMoveToElement(element);
		validateDisplayedElement(getProperty("ecom.mlm.link"), "MLM link");
		pauseInSeconds(1);
		element.click();
		commonInfoLog("Clicked our My Bussiness link");
		scrollIntoViewJS(getProperty("ecom.mlm.header"), -200);
		validateDisplayedElement(getProperty("ecom.mlm.header"), "MLM header");
		clickElement(getProperty("ecom.mlm.next.link"), "Clicked Get Details link");
		pauseInSeconds(2);
		waitForLoad();//
		validateDisplayedElement(getProperty("ecom.mlm.header"), "MLM header");
		WebElement element2 = waitExplicitlyForClickable(getProperty("ecom.my.bussiness"));
		actionMoveToElement(element2);
		clickElement(getProperty("ecom.mlm.link"), "Clicked MLM link");
		pauseInSeconds(2);
		waitForLoad();//
		validateDisplayedElement(getProperty("ecom.mlm.header"), "MLM header");
		iMDone();
	}

	/***
	 * 
	 * @param customerNo {@link String}
	 * @param departNo   {@link String}
	 */
	public void validateOurExclusivePromotionsLink(String customerNo, String departNo) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		String[] tt = new String[] { "", "OG", "MS" };
		for (String s : tt) {
			String listName = findData(s + "ListName");
			commonInfoLog("Customer list name:: " + listName);
			validateCommonSteps(listName);

		}
		iMDone();
	}

	/***
	* 
	* 
	*/
	private void validateCommonSteps(String listName) {
		waitForLoad();
		pauseInSeconds(4);
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(element);
		if (isExists(getProperty("ecom.suggested.update"))) {
			searchList(listName);
			pauseInSeconds(2);
			jsScrollWindowUp();
			pauseInSeconds(1);
			actionMoveJSClick(findElementByXpath(getProperty("ecom.home.link")));
			pauseInSeconds(2);
			waitForLoad();
			validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"),
					"Right rail suggested product");
			clickElement(getProperty("ecom.suggested.prod.right.rail.text"), "Clicked Suggested Products Right Rail");
			waitForLoad();
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates"), "Suggested Products Header");
			// validatingTC
			validateExclusiveLink();
			pauseInSeconds(2);
			clickElement(getProperty("ecom.our.exculsive"), "Clicked our exculsive link");
			pauseInSeconds(2);
			scrollIntoViewJS(getProperty("ecom.suggested.product.updates.exclusive"), -200);
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates.exclusive"),
					"Suggested Product Updates Text");
			clickElement(getProperty("ecom.access.suggested.prod"), "Clicked Access Suggested Products Link");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.suggested.product.updates"), "Suggested Products header");
			pauseInSeconds(1);
		} else {
			commonFailLog("Customer having not Suggested Products! Please setup data properly");
		}

	}

	private void validateExclusiveLink() {
		WebElement element = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(element);
		validateDisplayedElement(getProperty("ecom.suggested.update"), "Suggested Products Updates Link");
		clickElement(getProperty("ecom.suggested.update"), "Clicked Suggested Product Updates Link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.suggested.product.updates"), "Suggested Products header");
	}

	/***
	 * ECR46-62
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateRemoveOptionsMyListsPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		waitForLoad();
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.text"), "My Shopping list");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Manage Lists Button");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.table.name.text"),
				"My Shopping list table name text ");
		validateDisplayedElement(getProperty("ecom.my.shopping.list.table.products.text"),
				"My Shopping list table products text");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list"), "My Shopping list name");
		validateNotDisplayedElement(getProperty("ecom.my.shopping.list.options"), "My Shopping List Options");
		validateNotDisplayedElement(getProperty("ecom.my.shopping.list.options.delete"),
				"My Shopping list options delete icon");
		pauseInSeconds(1);
		scrollIntoViewJS(getProperty("ecom.manage.usfood.list.text"), -200);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.text"), "Manage by usfoods list");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.table.name.text"),
				"Manage by usfoods table name text");
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.table.products.text"),
				"Manage by usfoods table products text");
		validateDisplayedElement(getProperty("ecom.manage.by.usfood.list"), "Manage By usfoods list name");
		validateNotDisplayedElement(getProperty("ecom.manage.usfood.list.options"), "Manage by usfoods list options");
		validateNotDisplayedElement(getProperty("ecom.manage.usfood.list.options.del"),
				"Manage by usfoods list options delete icon");
		pauseInSeconds(2);
		iMDone();
	}

	public void validateOptionsMyListsPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		waitForLoad();
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.text"), "My Shopping list");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.createlist.btn"), "Manage Lists Button");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.options"), "My Shopping List Options");
		validateDisplayedElement(getProperty("ecom.my.shopping.list.options.delete"),
				"My Shopping list options delete icon");
		pauseInSeconds(1);
		scrollIntoViewJS(getProperty("ecom.manage.usfood.list.text"), -200);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.text"), "Manage by usfoods list");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.options"), "Manage by usfoods list options");
		pauseInSeconds(2);
		iMDone();
	}

	/***
	 * ECR46-67
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateRemoveActionsDownloadPrintOnPage(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "S", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String listName = findData(list[i] + "ListName");
			commonInfoLog("List name :: " + listName);
			validateRemoveActionsDownloadPrint(listName);
		}
		iMDone();
	}

	public void validateActionsDownloadPrintOnPage(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "S", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String listName = findData(list[i] + "ListName");
			commonInfoLog("List name :: " + listName);
			validateActionsDownloadPrint(listName);
		}
		iMDone();
	}

	/**
	 * @param listName {@link String}
	 */
	private void validateRemoveActionsDownloadPrint(String listName) {
		pauseInSeconds(2);
		waitForLoad();
		searchList(listName);
		validateNotDisplayedElement(getProperty("ecom.actions.text"), "Actions text");
		validateNotDisplayedElement(getProperty("ecom.download.text"), "Download text");
		validateNotDisplayedElement(getProperty("ecom.print.text"), "Print text");
		pauseInSeconds(2);
		jsScrollWindowUp();
		
	}

	private void validateActionsDownloadPrint(String listName) {
		pauseInSeconds(2);
		waitForLoad();
		searchList(listName);
		validateDisplayedElement(getProperty("ecom.actions.text"), "Actions text");
		validateDisplayedElement(getProperty("ecom.download.text"), "Download text");
		validateDisplayedElement(getProperty("ecom.print.text"), "Print text");
		pauseInSeconds(2);
		jsScrollWindowUp();
	}

	/***
	 * ECR46-315
	 * 
	 * @param customerId   {@link String}
	 * @param departmentId {@link String}
	 */
	public void validateRemoveAddListFromMyKitchePage(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		validateNoAddToListMykitchenProductComparePage();
		iMDone();

	}

	public void validateAddListFromMyKitchePage(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		String[] list = { "S" };
		for (int i = 0; i < list.length; i++) {
			String listName = findData(list[i] + "ListName");
			commonInfoLog("List name :: " + listName);
			validateAddToListFromMyKitchenPage(listName);
		}
		iMDone();
	}

	/**
	 * @param listName {@link String}
	 */
	private void validateAddToListFromMyKitchenPage(String listName) {
		searchList(listName);
		pauseInSeconds(3);
		jsScrollTop();
		pauseInSeconds(1);
		homeLink();
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.mykitchen.link1"));
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.your.exclusive.offers"), "Your Exclusive offers");
		clickElement(getProperty("ecom.your.exclusive.offers.img"), "Clicked Your Exclusive offers Link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.grid.active.icon"), "Clicked active grid");
		pauseInSeconds(1);
		// scrollIntoViewJS(getProperty("ecom.grid.active.icon"), -400);
		captureScreenShot("Items are shwoing for comparison");
		for (int i = 1; i <= 4; i++) {
			String xpathCheckbox = getProperty("ecom.product.comapre.checkbox").replaceAll(AppUtil.DELIMINATOR, "" + i);
			clickElement(xpathCheckbox, "Clicked checkbox " + i);
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}

	}

	private void validateNoAddToListMykitchenProductComparePage() {
		waitForLoad();
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.our.exculsive"), getProperty("ecom.mykitchen.link1"));
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.your.exclusive.offers"), "Your Exclusive offers");
		clickElement(getProperty("ecom.your.exclusive.offers.img"), "Clicked Your Exclusive offers Link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.grid.active.icon"), "Clicked active grid");
		pauseInSeconds(1);
		// scrollIntoViewJS(getProperty("ecom.grid.active.icon"), -400);
		captureScreenShot("Items are showing for comparison");
		for (int i = 1; i <= 4; i++) {
			String xpathCheckbox = getProperty("ecom.product.comapre.checkbox").replaceAll(AppUtil.DELIMINATOR, "" + i);
			clickElement(xpathCheckbox, "Clicked checkbox " + i);
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}

	}

}
