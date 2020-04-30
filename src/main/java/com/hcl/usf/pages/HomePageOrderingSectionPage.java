package com.hcl.usf.pages;

/**
 * 
 * @author intakhabalam.s@hcl.com
 * 
 */
public class HomePageOrderingSectionPage extends CommonPageElement {

	/**
	 * @param customerNo {@link String}
	 * @param departNo   {@link String}
	 */
	public void validateHomePageOrderingSectionPage(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.home.order.text"))) {
			// Home page validation is part of TC
			validateDisplayedElement(getProperty("ecom.home.order.text"), "My Order");
			pauseInSeconds(1);
			String csLbl = getText(getProperty("ecom.home.order.cs.text"));
			String eaLbl = getText(getProperty("ecom.home.order.ea.text"));
			if (csLbl.contains("CS") && eaLbl.contains("EA")) {
				commonPassWithScreenShotLog("CS and EA labels are aligned vertically");
			} else {
				commonFailLog("CS and EA labels are not aligned vertically");
			}
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.home.order.status"), "Ordering Status");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.home.view.all.orders"), "View All Orders");
			clickElement(getProperty("ecom.home.view.all.orders"), "Clicked View All Orders");
			pauseInSeconds(3);
			waitForLoad();//
			validateDisplayedElement(getProperty("ecom.home.20px"), "20px Gap");
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.home.30px"), "30px Gap");
			pauseInSeconds(1);
		} else {
			commonInfoLog("Customer does not have any orders placed");
			if (isExists(getProperty("ecom.home.order.status"))) {
				validateDisplayedElement(getProperty("ecom.home.order.status"), "Ordering Status");

			}
		}

		iMDone();
		pauseInSeconds(1);

	}

	public void validatecreateOrderOptionforNoList(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.create.order.btn"), "Clicked create Order");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.create.order.options.link"), "Clicked create Order option");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.create.order.popup.text"), "Create an order window ");
		isElementDisabled(getProperty("ecom.create.order.list.tab"), "LMA Customer without Lists");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.create.order.QOE.tab"), "Clicked QOE tab");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.create.order.QOE.useproduct"), "Clicked Use Product Number");
		clickElement(getProperty("ecom.create"), "Clicked create Button");
		validateNotDisplayedElement(getProperty("ecom.create.order.QOE.uselist.text"), "Use List Line Numbers");
		pauseInSeconds(4);
		validateDisplayedElement(getProperty("ecom.create.order.QOE.text"), " Quick Order Entry page");
		setText(getProperty("ecom.create.order.QOE.Product"), findData("ProductName"));
		pauseInSeconds(3);
		setText(getProperty("ecom.create.order.QOE.CSQTY"), "1");
		clickElement(getProperty("ecom.create.order.QOE.PO.add"), "Clicked Add PO");
		setText(getProperty("ecom.create.order.QOE.PO.text"), "test1");
		clickElement(getProperty("ecom.create.order.QOE.PO.update"), "Clicked Update PO");
		clickReviewAndSubmitOrder();
		pauseInSeconds(1);
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
		}
	}

	public void createOrderListOptionFromHomepage(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		clickElement(getProperty("ecom.create.order.btn"), "Clicked create Order");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.create.order.options.link"), "Clicked create Order option");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.create.order.list.dropdown"), "Clicked create Order list drop down");
		/*
		 * Actions action = new Actions(driver); WebElement clictheobject =
		 * waitForVisibilityOfElement(".//li//a[text()='" + findData("SListName") +
		 * "']"); action.moveToElement(clictheobject).perform();
		 * action.moveToElement(clictheobject).click(); clictheobject.click();
		 */
		selectDropDownValues(findData("SListName"));// created generic method
		pauseInSeconds(1);
		clickElement(getProperty("ecom.create"), "Clicked create Button");
		pauseInSeconds(5);
		filterProduct(findData("ProductName"));
		pauseInSeconds(2);
		waitForLoad();
		setText(getProperty("ecom.List.cs.Qty"), "1");
		pauseInSeconds(1);
		clickContinueButton();
		pauseInSeconds(2);
		clickReviewAndSubmitOrder();
		pauseInSeconds(1);
		if (confirmOrderText()) {
			commonInfoLog("Order Confirmation!!!");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
		}

	}
	
	public void tmNoteNotification(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.order.Home.Notification"), "Home Page Notification");
		validateDisplayedElement(getProperty("ecom.TMNote1.Notification"), "Home Page TM Note1  Notification");
		validateDisplayedElement(getProperty("ecom.TMNote2.Notification"), "Home Page TM Note2 Notfication");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.MoreNote.Link"), "Home Page More TM Note Link");
		clickElement(getProperty("ecom.MoreNote.Link"), "Clicked More TM Note Link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.recomd.text"), "Recommended By Your Team");
		commonInfoLog("Recommended By Your Team With TM Note!!!");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.ourexclusive.breadcrumb"), "Clicked our exculsive link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.home.link"), "Clicked Home link");
		commonInfoLog("Home Page!!!");
	}
	
	public void recommendedbytmpage(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.order.Home.Notification"), "Home Page Notification");
		validateDisplayedElement(getProperty("ecom.TMNote1.Notification"), "Home Page TM Note1  Notification");
		validateDisplayedElement(getProperty("ecom.TMNote2.Notification"), "Home Page TM Note2 Notfication");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.MoreNote.Link"), "Home Page More TM Note Link");
		clickElement(getProperty("ecom.MoreNote.Link"), "Clicked More TM Note Link");
		waitForLoad();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.recomd.text"), "Recommended By Your Team");
		commonInfoLog("Recommendations Page With Multiple TM Note!!!");
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
		validateDisplayedElement(getProperty("ecom.recomd.byTeam.breadcrumb"), "Recommended By Your Team Bread Crumb");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.recomd.byTeam.link"), "Recommended By Your Team Bread Link");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.tmnote.subpage"), "TM Note Sub Page on Recommended By Your Team Page ");	
	}

}
