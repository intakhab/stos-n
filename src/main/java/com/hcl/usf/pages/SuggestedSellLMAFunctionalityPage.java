package com.hcl.usf.pages;

import org.openqa.selenium.WebElement;

/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class SuggestedSellLMAFunctionalityPage extends CommonPageElement {
    /***
     * EC46-386
     * @param customerId
     * @param departmentId
     */
	public void validateSuggestedSellLMAFunctionality(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		validateManageOrCreateListList();
		//right notification
		validateDisplayedElement(getProperty("ecom.notification.text"), "Notification header");
		//
		validateListUpdatesRightRail();
		
		validateSugSellAtMenuDropdownAndRightRailGoToSuggPage();
		
		validateProductAtSuggestedUpdatePage(findData("NProductName"));
		//
		if(isExists(getProperty("ecom.total.savings.div"))) {
			validateDisplayedElement(getProperty("ecom.total.savings.div"), "Total Saving div");
		}
		//
		if(isExists(getProperty("ecom.annual.savings.label"))) {
			validateDisplayedElement(getProperty("ecom.annual.savings.label"), "Annual Saving label");
			validateDisplayedElement(getProperty("ecom.annual.savings.money.label"), "Annual Saving money label");
		}
		
		iMDone();
	}

	private void validateListUpdatesRightRail() {

		if (isExists(getProperty("ecom.list.updates.header"))) {
			validateDisplayedElement(getProperty("ecom.list.updates.header"), "List Updatates Header");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.list.updates.header"), "Clicked List Updatates Header link");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.list.updates.header"), "List Updatates Header");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.list.sub.header.text"), "List Updatates Sub header");
			
			pauseInSeconds(1);
			validateListProducts();
		}

	}
    
	private void validateListProducts() {
		// TODO Auto-generated method stub
		String pd=findData("ListUpdateProduct");
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		if(isExists(prd)) {
			scrollIntoViewJS(prd, -250);
			pauseInSeconds(1);
			validateDisplayedElement(prd, pd+" product");
		}
		pauseInSeconds(1);
		validateDisplayedElement(findData("SAVINGS_VALIDATION"), findData("SAVINGS_VALIDATION"));
		pauseInSeconds(1);

		validateDisplayedElement(findData("SAVINGS_VALIDATION_AMOUNT"), findData("SAVINGS_VALIDATION_AMOUNT"));
		pauseInSeconds(1);

		clickElement(getProperty("ecom.back.to.homepage"), "Clicked Back to Home Page link");
		pauseInSeconds(1);

	}

	private void validateManageOrCreateListList() {
		WebElement element=waitForVisibilityOfElement(getProperty("ecom.shopping.list.icon"));
		actionMoveToElement(element);
		
		String href=getText(getProperty("ecom.view.all.list.next.href"));
		// After loading page all list, select specific list which will be coming from
		// xsl
		waitForLoad();
		pauseInSeconds(1);
		if ("Mange Lists".equalsIgnoreCase(href)) {
			validateDisplayedElement(getProperty("ecom.view.all.list.next.href"), "Manage Lists");
		} else {
			validateDisplayedElement(getProperty("ecom.view.all.list.next.href"), "Create Lists");
		}// Steps
		
	}
	
	/****
	 * EC46_729 EC46_730 EC46_731
	 * @param customerId
	 * @param departmentId
	 */
	public void validateReplaceProductListCachingTest(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list.next.href"));
		pauseInSeconds(2);
		waitForLoad();
		pauseInSeconds(5);
		waitForLoad();
		//
		pauseInSeconds(2);

		String xPath= "//a[contains(.,'" + findData("NListName") + "')]";
		highlightElement(xPath);
		pauseInSeconds(1);
		clickElement(xPath, "Clicked list "+findData("NListName"));
		pauseInSeconds(5);
		if (isExists(getProperty("ecom.discontinue.product.text"))) {
			scrollIntoViewJS(getProperty("ecom.discontinue.product.text"), -250);
			validateDisplayedElement(getProperty("ecom.discontinue.product.text"), "Discontinue product");
			validateDisplayedElement(getProperty("ecom.replacement.product.text"), "Replacement product");
			pauseInSeconds(1);
			String replacementPr = getText(getProperty("ecom.replacement.prod.number"));
			if (replacementPr.contains("#")) {
				replacementPr = replacementPr.replaceAll("#", "").trim();
			}
			clickElement(getProperty("ecom.accept.replacement.tooltip"), "Clicked accept replacement");
			pauseInSeconds(2);
			jsScrollTop();
			validateDisplayedElement(getProperty("ecom.successfull.replace.msg"), "Successful Replace message");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.backto.ecom.usfood"), "Clicked usfoods online link");
			waitForLoad();
			pauseInSeconds(1);
			searchList(findData("NListName")); // Again search the NListName
			pauseInSeconds(5);
			filterProduct(replacementPr);
			pauseInSeconds(2);
			String prd = "//span[contains(.,'# " + replacementPr + "') or contains(.,'#" + replacementPr + "')]";
			validateDisplayedElement(prd, replacementPr);
			pauseInSeconds(1);

		} else {
			commonFailWithScreenShotLog("Discontinued products not found for this customer");
		}

		iMDone();
	}
}

