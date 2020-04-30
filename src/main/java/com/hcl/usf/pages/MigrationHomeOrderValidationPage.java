package com.hcl.usf.pages;

public class MigrationHomeOrderValidationPage extends CommonPageElement {
	
	
	public void validateHomePageOrderingSectionPageAllexception(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.home.order.text"))) {
		if ("HomePageOrderingSectionAllExceptionTest".equalsIgnoreCase(findData("ScenarioDescription"))){
				
					validateDisplayedElement(getProperty("ecom.order.Home.exception1"), "Home page 1st exception order");
					validateDisplayedElement(getProperty("ecom.order.Home.exception2"), "Home page 2nd exception order");
					validateDisplayedElement(getProperty("ecom.order.Home.exception3"), "Home page 3rd exception order");
		}else if("HomePageOrderingSectionAlltypeorderTest".equalsIgnoreCase(findData("ScenarioDescription"))){
			validateDisplayedElement(getProperty("ecom.order.Home.exception1"), "Home page 1st exception order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 1st Not Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.sub1"), "Home page 1st Submitted order");
		}else if("HomePageOrderingSectionExceptionandNotsubmittedTest".equalsIgnoreCase(findData("ScenarioDescription"))){
			validateDisplayedElement(getProperty("ecom.order.Home.exception1"), "Home page 1st exception order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 1st Not Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 2nd Not Submitted order");
			
		}else if("HomePageOrderingSectionSubmittedandNotsubmittedTest".equalsIgnoreCase(findData("ScenarioDescription"))){
			validateDisplayedElement(getProperty("ecom.order.Home.sub1"), "Home page 1st Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 1st Not Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 2nd Not Submitted order");
			
		}else if("HomePageOrderingSectionSubmittedTest".equalsIgnoreCase(findData("ScenarioDescription"))){
			validateDisplayedElement(getProperty("ecom.order.Home.sub1"), "Home page 1st Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.sub1"), "Home page 2nd Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.sub1"), "Home page 3rd Submitted order");
			
			
		}else if("HomePageOrderingSectionNotSubmittedTest".equalsIgnoreCase(findData("ScenarioDescription"))){
			
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 1st Not Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 2nd Not Submitted order");
			validateDisplayedElement(getProperty("ecom.order.Home.Notsub1"), "Home page 3rd Not Submitted order");
			
		}
		}
		else {
			commonInfoLog("Customer does not have any orders placed");
			if(isExists(getProperty("ecom.home.order.status"))) {
				validateDisplayedElement(getProperty("ecom.home.order.status"), "Ordering Status");

			}
		}

		iMDone();
		pauseInSeconds(1);
	}
	
	
	public void validateHomePageNotificationValidation(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.order.Home.Notification"))) {
			validateDisplayedElement(getProperty("ecom.order.Home.Notification"), "Notification section is displayed");
		}
		else {
			commonInfoLog("Notification section is not dipslayed in the home page");
			}
		
		if (isExists(getProperty("ecom.order.Home.Notification.promotion"))) {
			validateDisplayedElement(getProperty("ecom.order.Home.Notification.promotion"), "Promotional offers are dispalyed in the home page notification");
		}
		else {
			commonInfoLog("Promotional offers are not dispalyed in the home page notification");
			}
		if (isExists(getProperty("ecom.order.Home.Notification.suggested "))) {
			validateDisplayedElement(getProperty("ecom.order.Home.Notification.suggested "), "Suggested product details are dispalyed in the home page notification");
		}
		else {
			commonInfoLog("Suggested product details are not dispalyed in the home page notification");
			}
		if (isExists(getProperty("ecom.order.Home.Notification.farmer "))) {
			validateDisplayedElement(getProperty("ecom.order.Home.Notification.farmer"), "Farmer Notification are dispalyed in the home page notification");
		}
		else {
			commonInfoLog("Farmer Notification are not dispalyed in the home page notification");
			}

		iMDone();
		pauseInSeconds(1);
	}
	
	
	public void validateHomePageOrderstatusTest(String customerNo, String departNo) {
		// Customer Login and Customer Search..
		commonLoginAndCustomerSearch(customerNo, departNo);
		// validatingTC
		pauseInSeconds(2);
		if (isExists(getProperty("ecom.home.order.status"))) {
			validateDisplayedElement(getProperty("ecom.home.order.status"), "Ordering Status");
			  clickElement(getProperty("ecom.home.order.status"), "Clicked Ordering Status");
	            pauseInSeconds(3);
	    		waitForLoad();//
	    		validateDisplayedElement(getProperty("ecom.order.ordersection.header"), "Ordering status Header");
	    		validateDisplayedElement(getProperty("ecom.order.ordersection.Notsubmitted.header"), "Not submitted order section Header");
	    		validateDisplayedElement(getProperty("ecom.order.ordersection.submitted.header"), "submitted order section Header");
	    		validateDisplayedElement(getProperty("ecom.order.ordersection.Noorder"), "Noorder on the orderingstatus page");
		   		validateDisplayedElement(getProperty("ecom.order.ordersection.Notsubmitted"), "Not submitted order on the orderingstatus page");
		   		validateDisplayedElement(getProperty("ecom.order.ordersection.submittedwithexception"), "Submitted with exception on the orderingstatus page");
		   		validateDisplayedElement(getProperty("ecom.order.ordersection.submitted"), "Submitted order on the orderingstatus page");
		   		
		}
		else {
			commonInfoLog("Order Status button is not dispalyed in the home page");
			}
		
		
		iMDone();
		pauseInSeconds(1);
	}

}
