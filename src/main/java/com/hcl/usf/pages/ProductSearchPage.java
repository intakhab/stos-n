package com.hcl.usf.pages;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com
 */
public class ProductSearchPage extends CommonPageElement {

	/***
	 * @param customerNo
	 *            {@link String}
	 * @param departNo
	 *            {@link String}
	 */
	public void validateSearchWithIn(String customerNo, String departNo) {
		// Login and search Customer
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		String[] list = { "N", "OG", "MS" };
		for (int i = 0; i < list.length; i++) {
			String lname = findData(list[i] + "ListName");
			String pname = findData(list[i] + "ProductName");
			commonInfoLog("List name :: " + lname + "  Product Name :: " + pname);
			searchList(lname);// Search Product
			pauseInSeconds(2);
			int x = filterProduct(pname); // Filter product
			if (x > 0) {
				for (int k = 0; k < x; k++) {
					String prodText = "";
					String prodXpath = deletePropertyDeliminator(getProperty("ecom.prod.name.text"), k);
					if (isExists(prodXpath)) {
						prodText = getText(prodXpath);
						scrollIntoViewJS(prodXpath, -50);
						pauseInSeconds(2);
						validateDisplayedElement(prodXpath, prodText);
						pauseInSeconds(1);
						jsScrollTop();
						pauseInSeconds(1);
					} else {
						commonInfoLog("Product does't matched with current content");
					}
					if (k == 1)
						break;
				}
			} else {
				commonInfoLog("Data not found with current key in seach box :" + pname);
			}
		}

		iMDone();
	}

	/***
	 * User story EC45-42
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validateInteractPageSearch(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		jsScrollDown();
		validateDisplayedElement(getProperty("ecom.home.page.merchandising.region"), "Merchandising region");
		clickElement(getProperty("ecom.home.page.merchandising.region"), "Clicked Merchandising region");
		pauseInSeconds(2);
		waitForLoad();
		String keySearch = findData("ProdKey1");

		validateDisplayedElement(getProperty("ecom.home.prod.search.key").replaceAll("@@@", keySearch), keySearch);

		String x1 = callSearchIcon(1);
		String x2 = callSearchIcon(2);

		clickCheckBox(x1, x2);

		String[] keySearch2 = findData("ProdKey2").split(",");
		String funcSearch = keySearch2[0] + "," + keySearch2[1];
		setText(getProperty("ecom.home.search.within"), funcSearch);
		pauseInSeconds(2);
		clickElement(getProperty("ecom.home.search.icon"), "Clicked searche icon");
		pauseInSeconds(3);

		validateDisplayedElement(
				getProperty("ecom.home.prod.search.key").replaceAll("@@@",
						keySearch + " + " + keySearch2[0] + "," + keySearch2[1]),
				keySearch + "+" + keySearch2[0] + "," + keySearch2[1]);
		// Search Again
		pauseInSeconds(1);
		if (isExists(getProperty("ecom.cross.search.icon.img"))) {
			clickElement(getProperty("ecom.cross.search.icon.img"), "Refreshed close button");
			pauseInSeconds(1);
		}

		pauseInSeconds(1);
		setText(getProperty("ecom.home.search.within"), keySearch2[2]);
		pauseInSeconds(2);
		clickElement(getProperty("ecom.home.search.icon"), "Clicked searche icon");
		pauseInSeconds(3);
		validateDisplayedElement(
				getProperty("ecom.home.prod.search.key").replaceAll("@@@", keySearch + " + " + keySearch2[2]),
				keySearch + "+" + keySearch2[2]);

		// validate DIRECT AND SCOOP
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.home.prod.direct"), "Direct checkbox");
		clickElement(getProperty("ecom.home.prod.direct"), "Clicked direct checkbox");
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.home.prod.scoop"), "Scoop checkbox");
		clickElement(getProperty("ecom.home.prod.scoop"), "Clicked scoop checkbox");
		pauseInSeconds(2);
		iMDone();
	}

	private void clickCheckBox(String text1, String text2) {
		String xpath1 = deletePropertyDeliminator(getProperty("ecom.home.prod.checkbox"), 1);
		clickElement(xpath1, "Clicked checkbox index " + 1);
		pauseInSeconds(2);
		String xpath2 = deletePropertyDeliminator(getProperty("ecom.home.prod.checkbox"), 2);
		clickElement(xpath2, "Clicked checkbox index " + 2);
		captureScreenShot("Page Screenshot");

		pauseInSeconds(1);
		jsScrollWindowUp();
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked compare button");
		pauseInSeconds(2);
		jsScrollWindowDown();
		pauseInSeconds(1);
		validateDisplayedElement("//span[contains(.,'" + text1 + "')]", text1);
		validateDisplayedElement("//span[contains(.,'" + text2 + "')]", text2);
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.backto.search"), "Clicked to back to search link");
	}

	private String callSearchIcon(int i) {
		String xpath1 = deletePropertyDeliminator(getProperty("ecom.home.prod.href"), i);
		String text1 = getText(xpath1);
		validateDisplayedElement(xpath1, text1);
		clickElement(xpath1, "Clicked to icon [" + text1 + "]");
		pauseInSeconds(2);
		validateDisplayedElement("//span[contains(.,'" + text1 + "')]", text1);
		pauseInSeconds(2);
		clickElement(getProperty("ecom.home.backto.search"), "Clicked to back to search link");
		pauseInSeconds(2);
		return text1;
	}

	/***
	 * EC45-596
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validationImportText(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		actionMoveToElement(findElementByXpath(getProperty("ecom.quick.entry.hover.icon")));
		clickElement(getProperty("ecom.import.order.options.text"), "Clicked \"Import Order\" link");
		pauseInSeconds(3);
		jsScrollDown();
		validateDisplayedElement(getProperty("ecom.looking.past.order"), "Looking for a past order");
		clickElement(getProperty("ecom.view.invoices"), "Clicked \"View Invoices\" link");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.view.invoice"), "View Invoice");
		iMDone();
	}

	/***
	 * ECR46-312 and ECR46-314 AddToList
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	// LMA Customer Search Page
	public void validationNoAddToListOnSearch(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(3);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
		pauseInSeconds(2);
		String keySearch = findData("ProdKey1");
		globalSearch(keySearch);
		waitForLoad();
		pauseInSeconds(3);
		productViewNoAddToList("ecom.home.backto.search");
		iMDone();
	}

	// LMA Customer Search Page
	public void validateAddToListOnSearch(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(4);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.list.LMA"), "My Shopping List");
		pauseInSeconds(3);
		String keySearch = findData("ProdKey1");
		globalSearch(keySearch);
		waitForLoad();
		pauseInSeconds(3);
		productViewAddToList("ecom.home.backto.search");
		iMDone();
	}

	// Non LMA Customer Search Page
	public void validateAddToListOnSearchforCustomer(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(4);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.my.shopping.list"), "My Shopping List");
		pauseInSeconds(3);
		String keySearch = findData("ProdKey1");
		globalSearch(keySearch);
		waitForLoad();
		pauseInSeconds(3);
		productViewAddToList("ecom.home.backto.search");
		iMDone();
	}

	/***
	 * ECR46-313
	 */

	public void validationNoAddToListOnPDPPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(3);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
		pauseInSeconds(2);
		String keySearch = findData("ProdKey1");
		globalSearch(keySearch);
		waitForLoad();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.active.icon"), "Detailed View");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.search.product.click"), "Product");
		for (int i = 1; i <= 1; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		iMDone();
	}

	public void validateAddToListOnPDP(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(4);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.list.LMA"), "My Shopping List");
		pauseInSeconds(2);
		String keySearch = findData("ProdKey1");
		globalSearch(keySearch);
		waitForLoad();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.active.icon"), "Detailed View");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.search.product.click"), "Product");
		for (int i = 1; i <= 1; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		iMDone();
	}

	/***
	 * ECR46_470
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validateListPageRatherThenEditPage(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.browse.products.text"), getProperty("ecom.cheese.text"));
		waitForLoad();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.show.all.products"), "Show All Products");
		pauseInSeconds(1);
		validateListPageProduct();
		pauseInSeconds(1);
		// Global Search
		globalSearch(findData("ProdKey1"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		validateListPageProduct();
		pauseInSeconds(1);
		// Global Search
		globalSearch(findData("ProdKey2"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		validateListPageProduct();
		globalSearch(findData("ProdKey3"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		String pd=getText(getProperty("ecom.product.add.order.prod.name").replaceAll(AppUtil.DELIMINATOR,"1"));
		actionMoveJSClick(findElementByXpath(getProperty("ecom.home.prod.href")));
		commonInfoWithScreenShotLog("Product description");
		clickOrderIcon(pd);
		//
		moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.scoop.link"));
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.scoop.favorites.link"), "View ScoopTM Favorites");
		clickElement(getProperty("ecom.scoop.favorites.link"), "Clicked View ScoopTM Favorites Link");
		pauseInSeconds(2);
		jsScrollWindow(200);
		validateListPageProduct();
		
		moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.mykitchen.link1"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.your.exclusive.offers"), "Your Exclusive offers");
		clickElement(getProperty("ecom.your.exclusive.offers.img"), "Clicked Your Exclusive offers Link");
		pauseInSeconds(2);
		jsScrollWindow(200);
		validateListPageProduct();
		
		iMDone();
		//

	}

	private void validateListPageProduct() {
		String pd=getText(getProperty("ecom.product.add.order.prod.name").replaceAll(AppUtil.DELIMINATOR,"1"));
		commonInfoLog("Product Ids "+pd);
		clickOrderIcon(pd);
	}

	private void clickOrderIcon(String pd) {
		clickElement(getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,"1"), "Clicked Add To List");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.product.add.order.text.list").replaceAll(AppUtil.DELIMINATOR,"1")
				.replaceAll("###",findData("NListName")), "Clicked List "+findData("NListName"));
		
		commonInfoWithScreenShotLog("Clicked order icon");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.view.list"), "Clicked view list");
		commonInfoWithScreenShotLog("Clicked view list");
		pauseInSeconds(3);
		pd=pd.replaceAll("#", "").trim();
		pauseInSeconds(3);
		searchFilter(pd);
		pauseInSeconds(1);
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		validateDisplayedElement(prd, pd+" product");
	}
	
	/***
	 * ECR46_406
	 * 
	 * @param customerId
	 *            {@link String}
	 * @param departmentId
	 *            {@link String}
	 */
	public void validateAddToOrder(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.browse.products.text"), getProperty("ecom.cheese.text"));
		waitForLoad();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.show.all.products"), "Show All Products");
		pauseInSeconds(1);
		
		validateProductAddedToOrder();
		pauseInSeconds(1);
		// Global Search
		globalSearch(findData("ProdKey1"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		//validateListPageProduct();
		validateProductAddedToOrder();
		pauseInSeconds(1);
		// Global Search
		globalSearch(findData("ProdKey2"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		//validateListPageProduct();
		validateProductAddedToOrder();
		globalSearch(findData("ProdKey3"));
		commonInfoWithScreenShotLog("Gloabl Search result");
		pauseInSeconds(2);
		String pd=getText(getProperty("ecom.product.add.order.prod.name").replaceAll(AppUtil.DELIMINATOR,"1"));
		actionMoveJSClick(findElementByXpath(getProperty("ecom.home.prod.href")));
		commonInfoWithScreenShotLog("Product description");
		clickOrderIcon(pd);
		//
		moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.scoop.link"));
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.scoop.favorites.link"), "View ScoopTM Favorites");
		clickElement(getProperty("ecom.scoop.favorites.link"), "Clicked View ScoopTM Favorites Link");
		pauseInSeconds(2);
		jsScrollWindow(200);
		//validateListPageProduct();
		validateProductAddedToOrder();
		moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.mykitchen.link1"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.your.exclusive.offers"), "Your Exclusive offers");
		clickElement(getProperty("ecom.your.exclusive.offers.img"), "Clicked Your Exclusive offers Link");
		pauseInSeconds(2);
		jsScrollWindow(200);
		//validateListPageProduct();
		validateProductAddedToOrder();
		iMDone();
		//

	}

	private void validateProductAddedToOrder() {
		clickElement(getProperty("ecom.product.add.order.btn").replace("XX",findData("ProdKey3").trim()), "Clicked Add To order");
		pauseInSeconds(10);
		validateDisplayedElement(getProperty("ecom.product.addproducttoorder"), "Add Product to Order Popup is present");
		clickElement(getProperty("ecom.promotions.continuebutton"), "Clicked Continue button");
		pauseInSeconds(20);
		validateDisplayedElement(getProperty("ecom.promotions.addproducttoordersuccessmessage"),
						"Product added to order success message");
		Integer prodNumActual =Integer.parseInt(getText(getProperty("ecom.product.oredredtoyourorder.prodnum")).replace("#", "").trim());
		Integer prodNumExpc= Integer.parseInt(findData("ProdKey3").trim());
		if (prodNumActual==prodNumExpc) {
			commonInfoWithScreenShotLog("The Product :"+prodNumExpc+" is added to the cart successfully"); 
		}
		else {
			commonInfoWithScreenShotLog("The Product :"+prodNumExpc+" is not added to the cart successfully"); 
		}
	
	}
    /***
     * ECR46-116
     * @param customerId
     * @param departmentId
     */
	public void validateUndoAddingListTest(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		firstUndoSearchList(findData("ProdKey1"));
		secondUndoSearchList(findData("ProdKey2"));
		pauseInSeconds(1);
		iMDone();
	}

	private void firstUndoSearchList(String prodKey) {
		String pd = commonUndoSearch(prodKey);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.add.anaother.group.dropdown"), "Clicked add anaother group dropdown");
		pauseInSeconds(1);
		selectDropDownValues(findData("ProdKey4")); // Group
		pauseInSeconds(1);
		clickElement(getProperty("ecom.view.list"), "Clicked view list");
		commonInfoWithScreenShotLog("Clicked view list");
		pauseInSeconds(1);
		pd = pd.replaceAll("#", "").trim();
		pauseInSeconds(2);
		searchFilter(pd);
		pauseInSeconds(1);
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		validateDisplayedElement(prd, pd + " product");

	}
	
	private void secondUndoSearchList(String prodKey) {
		String pd = commonUndoSearch(prodKey);
		pauseInSeconds(1);
		clickElement(getProperty("ecom.prod.cancel.text"), "Clicked cancel button");
		commonInfoLog("The Product :"+pd+" is not added"); 
		pauseInSeconds(2);
		jsScrollTop();
		pauseInSeconds(1);
		searchList(findData("NListName"));
		pauseInSeconds(2);
		pd = pd.replaceAll("#", "").trim();
		searchFilter(pd);
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		validateNotDisplayedElement(prd, pd + " product");
	}

	private String commonUndoSearch(String prodKey) {
		globalSearch(prodKey);
		waitForLoad();
		pauseInSeconds(3);
		String pd=getText(getProperty("ecom.product.add.order.prod.name").replaceAll(AppUtil.DELIMINATOR,"1"));
		String xPathOrderText=getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,"1");
		clickElement(xPathOrderText, "Clicked Add To List");
		pauseInSeconds(1);
		scrollIntoViewJS(xPathOrderText, -50);
		pauseInSeconds(2);
		clickElement(getProperty("ecom.product.add.order.text.list").replaceAll(AppUtil.DELIMINATOR,"1")
				.replaceAll("###",findData("NListName")), "Clicked List name : "+findData("NListName"));
		
		pauseInSeconds(3);
		String txt=getText(getProperty("ecom.prod.added.successfull.text"));
		validateDisplayedElement(getProperty("ecom.prod.added.successfull.text"), txt);
		pauseInSeconds(1);
		commonInfoWithScreenShotLog("Product Ids "+pd);
		pauseInSeconds(1);
		return pd;
	}
    /**
     * EC46-737
     * @param customerId {@link String} 
     * @param departmentId {@link String} 
     */
	public void validateAddingListWithoutUnassignedGroupTest(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		String pd = commonUndoSearch(findData("ProdKey1"));
		pauseInSeconds(2);
		clickElement(getProperty("ecom.view.list"), "Clicked view list");
		commonInfoWithScreenShotLog("Clicked view list");
		pauseInSeconds(1);
		pd = pd.replaceAll("#", "").trim();
		pauseInSeconds(2);
		searchFilter(pd);
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.unassigned.group.text"),"Unassigned group header");
		pauseInSeconds(1);
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		validateDisplayedElement(prd, pd + " product");
		iMDone();
	}
}