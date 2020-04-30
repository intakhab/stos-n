package com.hcl.usf.pages;


public class ForceListUpdatePage extends CommonPageElement {
	
	public void validateForceListUpdateLMACustomer(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		searchList("NListName");
		pauseInSeconds(3);
		filterProduct(findData("NProductName"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.force.list.menu"),"Save with Corporate Menu Change.");
		pauseInSeconds(3);
		homeLink();
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.force.list.notifcation"),"Force List Update Notification On Home Page");
		clickElement(getProperty("ecom.force.list.notifcation"), "Clicked Force List Update Notification");
		validateDisplayedElement(getProperty("ecom.force.list.page"),"Force List Update Page");
		validateDisplayedElement(getProperty("ecom.force.list.banner.message"),"Force List Update Banner Message");
		validateDisplayedElement(getProperty("ecom.force.list.update.backtohome"),"Force List Update Back To Home");
		clickElement(getProperty("ecom.force.list.update.backtohome"), "Clicked Back To home");
	}
	public void validateForceListUpdateNonLMACustomer(String customerId, String departmentId) {
		waitForLoad();
		commonLoginAndCustomerSearch(customerId, departmentId);
		searchList("NListName");
		pauseInSeconds(3);
		filterProduct(findData("NProductName"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.force.list.menu"),"Save with Corporate Menu Change.");
		pauseInSeconds(3);
		homeLink();
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.force.list.notifcation"),"Force List Update Notification On Home Page");
		clickElement(getProperty("ecom.force.list.notifcation"), "Clicked Force List Update Notification");
		validateDisplayedElement(getProperty("ecom.force.list.page"),"Force List Update Page");
		validateDisplayedElement(getProperty("ecom.force.list.banner.message"),"Force List Update Banner Message");
		validateDisplayedElement(getProperty("ecom.force.list.update.backtohome"),"Force List Update Back To Home");
		clickElement(getProperty("ecom.force.list.update.backtohome"), "Clicked Back To home");
	}
	
}
