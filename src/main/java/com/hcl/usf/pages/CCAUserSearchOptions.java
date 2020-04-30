package com.hcl.usf.pages;

public class CCAUserSearchOptions extends CommonPageElement {
	public void validateUserSearchOptions(String customerNo, String departNo) {
		// CCA Login 
		ccaLogin();
		pauseInSeconds(2);
		setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
		findElementByXpath(getProperty("ecom.cca.user.search.icon")).click();
		pauseInSeconds(1);
		//validate SelecttoTurnoff field
		waitForVisibilityOfElement(getProperty("ecom.CCA.SelecttoTurnOff"));
		isExists(getProperty("ecom.CCA.SelecttoTurnOff"));
		commonPassLog("MigrationReport Link is displayed in the application");
		waitForVisibilityOfElement(getProperty("ecom.CCA.TandemOrderGuide"));
		isExists(getProperty("ecom.CCA.TandemOrderGuide"));
		commonPassLog("MigrationReport Link is displayed in the application");
		
}
}