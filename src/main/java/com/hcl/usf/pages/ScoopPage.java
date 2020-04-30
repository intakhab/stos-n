package com.hcl.usf.pages;

public class ScoopPage extends CommonPageElement {
	
	//Scoop Page LMA Customer
		public void validationNoAddToListOnScoop(String customerId, String departmentId) {
			commonLoginAndCustomerSearch(customerId, departmentId);
			waitForLoad();
			pauseInSeconds(3);
			moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
			validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
			pauseInSeconds(2);
			moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.scoop.link"));
			waitForLoad();
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.scoop.link1"), "View ScoopTM");
			clickElement(getProperty("ecom.scoop.link1"), "Clicked View ScoopTM Link");
			productViewNoAddToList("ecom.home.bacto.scoop");
			iMDone();
		}
		
		//Scoop Page LMA Customer
		public void validateAddToListOnScoop(String customerId, String departmentId) {
			commonLoginAndCustomerSearch(customerId, departmentId);
			waitForLoad();
			pauseInSeconds(3);
			moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
			pauseInSeconds(3);
			validateDisplayedElement(getProperty("ecom.myshop.list.LMA"), "My Shopping List");
			pauseInSeconds(2);
			moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.scoop.link"));
			waitForLoad();
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.scoop.link1"), "View ScoopTM");
			clickElement(getProperty("ecom.scoop.link1"), "Clicked View ScoopTM Link");
			productViewAddToList("ecom.home.bacto.scoop");
			iMDone();
		}
		
		//Scoop Page Non LMA Customer 
		public void validateAddToListOnScoopforCustomer(String customerId, String departmentId) {
			commonLoginAndCustomerSearch(customerId, departmentId);
			waitForLoad();
			pauseInSeconds(3);
			moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
			pauseInSeconds(3);
			validateDisplayedElement(getProperty("ecom.my.shopping.list"), "My Shopping List");
			pauseInSeconds(2);
			moveToIconAndClick(getProperty("ecom.our.exculsive"),getProperty("ecom.scoop.link"));
			waitForLoad();
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.scoop.link1"), "View ScoopTM");
			clickElement(getProperty("ecom.scoop.link1"), "Clicked View ScoopTM Link");
			productViewAddToList("ecom.home.bacto.scoop");
			iMDone();
		}

}
