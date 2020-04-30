package com.hcl.usf.pages;

/***
 * 
 * @author Ragu
 *
 */
public class CES120Page extends  CommonPageElement {
	

	//LMA Customer CES120 Page
			public void validationNoAddToListOnCES120(String customerId, String departmentId) {
				commonLoginAndCustomerSearch(customerId, departmentId);
				waitForLoad();
				pauseInSeconds(3);
				moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
				pauseInSeconds(2);
				clickElement(getProperty("ecom.home.link"), "Clicked Home Link");
				waitForLoad();
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.CES120.banner"), "CES120 Banner");
				clickElement(getProperty("ecom.CES120.banner"), "Clicked CES120 Link");
				productViewNoAddToList("ecom.home.backto.search");
				iMDone();
			}
			
			//CES 120 
			public void validateAddToListOnCES120(String customerId, String departmentId) {
				commonLoginAndCustomerSearch(customerId, departmentId);
				waitForLoad();
				pauseInSeconds(3);
				moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.myshop.list.LMA"), "My Shopping List");
				pauseInSeconds(2);
				clickElement(getProperty("ecom.home.link"), "Clicked Home Link");
				waitForLoad();
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.CES120.banner"), "CES120 Banner");
				clickElement(getProperty("ecom.CES120.banner"), "Clicked CES120 Link");
				productViewAddToList("ecom.home.backto.search");
				iMDone();
			}
			
			// LMA Customer CES120
			public void validateAddToListOnCES120forCustomer(String customerId, String departmentId) {
				commonLoginAndCustomerSearch(customerId, departmentId);
				waitForLoad();
				pauseInSeconds(3);
				moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.my.shopping.list"), "My Shopping List");
				pauseInSeconds(2);
				clickElement(getProperty("ecom.home.link"), "Clicked Home Link");
				waitForLoad();
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.CES120.banner"), "CES120 Banner");
				clickElement(getProperty("ecom.CES120.banner"), "Clicked CES120 Link");
				productViewAddToList("ecom.home.backto.search");
				iMDone();
			}
		
	

}
