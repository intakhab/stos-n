package com.hcl.usf.pages;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Naveen Kumar
 *        
 */
public class CCALMAMigrationPage extends CommonPageElement{
	
	  public void validateCCALMAMigrationReportPage(String customerNo, String departNo) {
		// CCA Login 
		ccaLogin();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.cca.userguide"), "userguide Link is displayed in the application");
		findElementByXpath(getProperty("ecom.cca.userguide")).click();
		pauseInSeconds(2);
		findElementByXpath(getProperty("ecom.cca.documentlink")).click();
		//Validate MigrationReport is available under Technical tab 
		WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element);
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.cca.LMAMigrationReport.link"), "MigrationReport Link is displayed in the application");
		//validate LMA Migration - Search Criteria lable is available
		findElementByXpath(getProperty("ecom.cca.LMAMigrationReport.link")).click();
		validateDisplayedElement(getProperty("ecom.cca.LMASearchCriteria.lable"), "LMA Search Criteria  is displayed in the application");
		//validate the date filed are available on the SLconversion report page
		validateDisplayedElement(getProperty("ecom.cca.slconversion.page.startdate"), "start date field is displayed");
		validateDisplayedElement(getProperty("ecom.cca.slconversion.page.enddate"), "End date field is displayed");
   
		//validating error message as "Invalid Date - please ensure that Start Date is before End Date"
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
		setText(getProperty("ecom.cca.slconversion.page.startdate"), "05/03/2020");
		
		setText(getProperty("ecom.cca.slconversion.page.enddate"), "03/02/2020");
		findElementByXpath(getProperty("ecom.CCA.SearchButton")).click();
		pauseInSeconds(1);
		
		validateDisplayedElement(getProperty("ecom.cca.InvalidDate.ErrorMessage"), "Invalid Date - please ensure that Start Date is before End Date  is displayed in the application");

		// Validate Division dropdown and Customer text input
		setText(getProperty("ecom.cca.slconversion.page.startdate"), "02/16/2020");
		setText(getProperty("ecom.cca.slconversion.page.enddate"), "03/17/2020");
		validateDisplayedElement(getProperty("ecom.cca.DivisionNumber.Dropdown"), "DivisonNumber Dropdown is displayed");
		validateDisplayedElement(getProperty("ecom.cca.CustomerNumber.TextBox"), "Customer Number field is displayed");
	    validateDisplayedElement(getProperty("ecom.cca.DivisionNumber.Dropdown"), "< < < < < Select By Division Name");
	    findElementByXpath(getProperty("ecom.CCA.SearchButton")).click();
	    pauseInSeconds(3);
        cammonMigrationSearchValidation();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.nasr.webadmin"), "Select USF NASR-WebAdmin");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	   
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.nasr.webupdate"), "Select USF NASR-WebUpdate");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.nasr.webuser"), "Select USF NASR-WebUser");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.mgr"), "Select USF Customer Care HelpDeskMGR");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.analyst"), "Select USF Customer Care HelpDeskAnalyst");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.custupdate"), "Select USF CustomerCare-Update");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.nasr.sales.support"), "Select USF NASR-SalesSupport");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	    
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.sales.division.support"), "Select USF-Divisional-SalesSupport");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
	   
	    technicalTabSwitchUser();
	    clickElement(getProperty("ecom.cca.select.user.sales.division.support"), "Select USF-Divisional-SalesSupport");
	    cammonMigrationReportLinkValidation();
	    cammonMigrationSearchValidation();
	    findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();

	     }
	 
	  
	  public void cammonMigrationReportLinkValidation()
	  {
		  clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
			pauseInSeconds(1);
			WebElement ele1=waitForVisibilityOfElement(getProperty("ecom.CCA.Tools"));
			actionMoveToElement(ele1);
			pauseInSeconds(1);
			waitForVisibilityOfElement(getProperty("ecom.cca.Tools.LMAMigrationReport.link"));
			findElementByXpath(getProperty("ecom.cca.Tools.LMAMigrationReport.link")).click();
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
		    commonPassLog("start date field is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.enddate"));
			commonPassLog("End date field is displayed");
			waitForVisibilityOfElement(getProperty("ecom.cca.DivisionNumber.Dropdown"));
		    isExists(getProperty("ecom.cca.DivisionNumber.Dropdown"));
			commonPassLog("DivisonNumber Dropdown is displayed");
	        waitForVisibilityOfElement(getProperty("ecom.cca.CustomerNumber.TextBox"));
	        isExists(getProperty("ecom.cca.CustomerNumber.TextBox"));
		    commonPassLog("Customer Number field is displayed");
		    findElementByXpath(getProperty("ecom.CCA.SearchButton")).click();
		    pauseInSeconds(1);
	  }
	  
	  public void cammonMigrationSearchValidation()
	  {
		  validateDisplayedElement(getProperty("ecom.CCA.SearchTextBox"), "SearchTextBox field is displayed");
		  
		  validateDisplayedElement(getProperty("ecom.CCA.SearchIcon"), "SearchIcon field is displayed");
		  
		  validateDisplayedElement(getProperty("ecom.CCA.RecordsFoundField"), "RecordsFound field is displayed");
		    
		  validateDisplayedElement(getProperty("ecom.CCA.CurrentPageLink"), "CurrentPageLink field is displayed");
		    
		  validateDisplayedElement(getProperty("ecom.CCA.PrevPage"), "PrevPage field is displayed");
		    
		  validateDropdownElements();
		    
		  validateDisplayedElement(getProperty("ecom.CCA.NextPage"), "NextPage field is displayed");
		  
		  validateDisplayedElement(getProperty("ecom.CCA.ReturnButton"), "ReturnButton field is displayed");
		    
  }
	  
	  private void validateDropdownElements()
	  {
	    WebElement ele = waitForVisibilityOfElement(getProperty("ecom.CCA.MigrationDropdown"));
		Select select = new Select(ele);
		List<WebElement> allOptions = select.getOptions();
		for(int i=0; i<allOptions.size(); i++) {
			String value = allOptions.get(i).getText();
			System.out.println(value);
		}
	  }
	  
	  
	  public void validateCCALMAMigrationUploadPage(String customerNo, String departNo) {
			// CCA Login 
			ccaLogin();
			pauseInSeconds(2);
			//Validate MigrationReport is available under Technical tab
			WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
			actionMoveToElement(element);
			pauseInSeconds(1);
			
			validateDisplayedElement(getProperty("ecom.CCA.LMAMigrationUpload"), "MigrationReport Link is displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.LMAMigrationUpload")).click(); 
			
			validateDisplayedElement(getProperty("ecom.CCA.LMAMigrationUpload.Label"), "MigrationUploadLablel is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ChooseFile"), "ChooseFileOption is displayed in the application");
			
			clickElement(getProperty("ecom.cca.upload.file"), "Clicked upload file button");
			
			validateDisplayedElement(getProperty("ecom.CCA.FileImportingErrorMsg"), "FileImportingErrorMsg is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.MigrationUploadTemplate"), "MigrationUploadTemplate displayed in the application");
			
			uploadFile("LmaUpload.csv");
			
			validateDisplayedElement(getProperty("ecom.CCA.DivisoinNumber"), "DivisoinNumber displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.SalesRoute"), "SalesRoute displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.CustomerNumber"), "CustomerNumber displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.PrevPage"), "PrevPage displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.NextPage"), "NextPage displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ExportButton"), "ExportButton displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.CommitButton"), "CommitButton displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ReturnButton"), "ReturnButton displayed in the application");
			
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload1.csv");
			validateDisplayedElement(getProperty("ecom.CCA.ErrorDivsionNumber"), "ErrorDivsionNumber displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
            uploadFile("LmaUpload_InvalidDivisonNumber.csv");
            validateDisplayedElement(getProperty("ecom.CCA.ErrorDivsionNumber"), "ErrorDivsionNumber displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_InvalidSalesRoute.csv");
			validateDisplayedElement(getProperty("ecom.CCA.ErrorSalesRoute"), "SalesRouteDivsionNumber displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_InvalidSalesRouteCombination.csv");
			validateDisplayedElement(getProperty("ecom.CCA.InvalidDivisionCustomerCombination"), "InvalidDivisionCustomerCombination displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload.csv");
			validateDisplayedElement(getProperty("ecom.CCA.CustomerNumber"), "CustomerNumber displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_Invalid_CustomerNumber.csv");
			validateDisplayedElement(getProperty("ecom.CCA.InvalidCustomerErrorMsg"), "InvalidCustomerErrorMsg displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_Invalid_CustomerNumberandDivision.csv");
	        validateDisplayedElement(getProperty("ecom.CCA.ErrorMsgCustomerDivision"), "ErrorMsgCustomerDivision displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_InvalidCustomerNumber_InvalidDivision.csv");
			validateDisplayedElement(getProperty("ecom.CCA.InvalidDivison"), "InvalidDivison displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_ValidCustomer_NoDivision.csv");
		    validateDisplayedElement(getProperty("ecom.CCA.InvalidDivison"), "DivisonErrorMsg displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			pauseInSeconds(1);
			
			uploadFile("LmaUpload_ValidCustomer_NoDivision.csv");
			validateDisplayedElement(getProperty("ecom.CCA.Error"), "ErrorMsg displayed in the application");
			validateDisplayedElement(getProperty("ecom.CCA.RowUploadedField"), "RowUplaodedField displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.PrevPage"), "PrevPage displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.NextPage"), "NextPage displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ReturnButton"), "ReturnButton displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ExportButton"), "ExportButton displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.CommitButton"), "CommitButton displayed in the application");
			
			findElementByXpath(getProperty("ecom.CCA.CommitButton")).click();
			
			validateDisplayedElement(getProperty("ecom.CCA.Table"), "Table displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.Line"), "Line displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.Results"), "Results displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.SkippedField"), "SkippedField displayed in the application");
			
			}
		
		    private void uploadFile(String filename) {
			String downloadDir =USER_DIR + "\\download\\"+filename;
			js.executeScript("document.getElementById('defaultFocus').focus();");
			pauseInSeconds(1);
			Actions action=new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			pauseInSeconds(3);
			driver.switchTo().activeElement().sendKeys(downloadDir);
			captureScreenShot("Page Screenshot");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.cca.upload.file"), "Clicked upload file button");
			pauseInSeconds(1);
			captureScreenShot("Page Screenshot");
		//	File f = Paths.get(downloadDir).toFile();
		/*
		 * if (f.exists()) { f.delete(); }
		 */
}
		public void validateMLMNewOptions(String customerNo, String departNo) {
			// CCA Login 
			ccaLogin();
			pauseInSeconds(2);
			setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
			findElementByXpath(getProperty("ecom.cca.user.search.icon")).click();
			pauseInSeconds(1);
			//findElementByXpath(getProperty("ecom.CCA.UserName")).click();
			//validate SelecttoTurnoff field
			
			validateDisplayedElement(getProperty("ecom.CCA.SelecttoTurnOff"), "SelecttoTurnOff displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.NewlyPurchased"), "NewlyPurchased displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.Inventory"), "Inventory displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.BuisnessAnalytics"), "BuisnessAnalytics is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.CheckBusinessTool"), "CheckBusinessTool is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.HealthCareMenus"), "HealthCareMenus is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.Supplier"), "Supplier is displayed in the application");
			
			
			
			validateDisplayedElement(getProperty("ecom.CCA.SelectTuron"), "SelectTuronis displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CAA.MPP"), "MPPis displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CAA.GLADMIN"), "GLADMIN is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CAA.Baseline"), "Baseline is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.Nutritional"), "Nutritional is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.MassCopyingList"), "MassCopyingList is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.InvoicePayment"), "InvoicePayment is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.cpmreports"), "cpmreports is displayed in the application");
		
			validateDisplayedElement(getProperty("ecom.cca.dwostatus"), "dwostatus is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.massshopptinglistdelete"), "massshopptinglistdelete is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.punchthru"), "punchthru is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.punchout"), "punchout is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.trendview"), "trendview is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CAA.COGMADMIN"), "COGADMIN is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.cogm/lto"), "cogm/lto is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.level"), "level is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.selecttoturnoffallsections"), "selecttoturnoffallsections is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.selectturnoffpayments"), "selectturnoffpayments is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.cca.invoicesonly"), "invoicesonly is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.TrueMFRReports"), "TrueMFRReports is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.MLMAdmin"), "MLMAdmin is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CAA.MLMReporting"), "MLMReporting is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ScoopAlertPopupDisabled"), "ScoopAlertPopupDisabled is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.DisabledScoop"), "DisabledScoop is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.ScoopIConDisabled"), "ScoopIConDisabled is displayed in the application");
			
			validateDisplayedElement(getProperty("ecom.CCA.HideContractedSuggestiveCell"), "HideContractedSuggestiveCell is displayed in the application");
			
			
			findElementByXpath(getProperty("ecom.CCA.SelectToTurnonImage")).click();
			pauseInSeconds(1);
			findElementByXpath(getProperty("ecom.CCA.ViewTurnonDataLink")).click();
			
			technicalTabSwitchUser();
			clickElement(getProperty("ecom.cca.select.user.mgr"), "Select USF Customer Care HelpDeskMGR");
			cammonSwitch();
			findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
			
			
			technicalTabSwitchUser();
			clickElement(getProperty("ecom.cca.select.user.analyst"), "Select USF Customer Care HelpDeskAnalyst");
			cammonSwitch();
			cammonFieldValidation();
			
			technicalTabSwitchUser();
			clickElement(getProperty("ecom.cca.select.user.custupdate"), "Select USF CustomerCare-Update");
			cammonSwitch();
			cammonFieldValidation();
			
			technicalTabSwitchUser();
			clickElement(getProperty("ecom.cca.select.user.nasr.webupdate"), "Select USF NASR-WebUpdate");
			cammonSwitch();
			cammonFieldValidation();
			
			
		    technicalTabSwitchUser();
		    clickElement(getProperty("ecom.cca.select.user.nasr.webuser"), "Select USF NASR-WebUser");
			cammonSwitch();
			cammonFieldValidation();
			
			 technicalTabSwitchUser();
			 clickElement(getProperty("ecom.cca.select.user.product.upload.all"), "Select CustomerCare-ProductUploadAll");
			 cammonSwitch();
			 cammonFieldValidation();
			 
			 technicalTabSwitchUser();
			 clickElement(getProperty("ecom.cca.select.user.sales.division.support"), "Select USF-Divisional-SalesSupport");
			 cammonSwitch();
			 cammonFieldValidation();
			 
			 //findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
			 setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
			 findElementByXpath(getProperty("ecom.cca.user.search.icon")).click();
			 pauseInSeconds(1);
			 jsScrollWindowDown();
			 //findElementByXpath(getProperty("ecom.CCA.UserName")).click();
			 validateDisplayedElement(getProperty("ecom.cca.usfsupportmailfield"), "USFSupportMail is displayed in the application");
			 validateDisplayedElement(getProperty("ecom.cca.usfnetworkidfield"), "USFNetworkId is displayed in the application");
			// setText(getProperty("ecom.CCA.NetworkIdTextBox"), "abcd");
			// findElementByXpath(getProperty("ecom.CCA.UpdateUserButton")).click();
			// waitForVisibilityOfElement(getProperty("ecom.CCA.NetworkIdErrorMessage"));
			// validateDisplayedElement(getProperty("ecom.CCA.NetworkIdErrorMessage"),"NetworkIdErrorMessage ");
			// boolean mailText =getText(getProperty("MLMSupportMailTextBox")).endsWith("usfoods.com");
			// String networkIdText = findElementByXpath(getProperty("ecom.CCA.NetworkIdTextBox")).getText();
			// System.out.println(networkIdText.matches("[a-zA-Z0-9]+"));
			 }
		
		
		public void validateCCAUserscreenchanges(String customerNo, String departNo) {
			// CCA Login 
			ccaLogin();
			pauseInSeconds(2);
			setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
			findElementByXpath(getProperty("ecom.cca.user.search.icon")).click();
			pauseInSeconds(1);
			validateDisplayedElement(getProperty("ecom.cca.usfsupportmailfield"), "USFSupportMail is displayed in the application");
			validateDisplayedElement(getProperty("ecom.cca.usfnetworkidfield"), "USFNetworkId is displayed in the application");
			validateDisplayedElement(getProperty("ecom.cca.trumfrreports"), "trumfrreports are displayed in the application");
			technicalTabSwitchUser();
			clickElement(getProperty("ecom.cca.select.user.mgr"), "Select USF Customer Care HelpDeskMGR");
			cammonSwitch();
			pauseInSeconds(2);
			jsScrollWindowDown();
			validateDisplayedElement(getProperty("ecom.CCA.MLMAdmin"),"MLMAdmin is displayed in the application");
			validateDisplayedElement(getProperty("ecom.CAA.MLMReporting"),"MLMReporting is displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.MLMAdmin.CheckBox")).click();
			pauseInSeconds(1);
			findElementByXpath(getProperty("ecom.CCA.MLMAdmin.CheckBox")).click();
			findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
			}
		
		public void validateCCARecordsOptions(String customerNo, String departNo) {
			// CCA Login 
			ccaLogin();
			pauseInSeconds(2);
			WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
			actionMoveToElement(element);
			pauseInSeconds(2);
			findElementByXpath(getProperty("ecom.cca.LMAMigrationReport.link")).click();
			setText(getProperty("ecom.cca.divisionnumber.textbox"), "2160");
			findElementByXpath(getProperty("ecom.CCA.SearchButton")).click();
			pauseInSeconds(1);
            validateDisplayedElement(getProperty("ecom.CCA.ExportButton"), "ExportButton displayed in the application");
			validateDisplayedElement(getProperty("ecom.CCA.ReturnButton"), "ReturnButton displayed in the application");
			findElementByXpath(getProperty("ecom.CCA.ReturnButton")).click();
			findElementByXpath(getProperty("ecom.CCA.SearchButton")).click();
			pauseInSeconds(2);
            validateDisplayedElement(getProperty("ecom.CCA.ReturnButton"), "ReturnButton displayed in the application");
		    validateDisplayedElement(getProperty("ecom.CCA.ExportButton"), "ExportButton displayed in the application");
		    validateDisplayedElement(getProperty("ecom.cca.showmoreresultsbutton"), "showmoreresultsbutton displayed in the application");
		    validateDisplayedElement(getProperty("ecom.cca.seemoresresultsmsg"), "seemoresresultsmsg displayed in the application");
		    findElementByXpath(getProperty("ecom.cca.showmoreresultsbutton")).click();
		    pauseInSeconds(2);
			
		}
			
		private void cammonFieldValidation()
		{
			jsScrollWindowDown();
			validateDisplayedElement(getProperty("ecom.CCA.MLMAdmin"),"MLMAdmin is displayed in the application");
			boolean isDisabled=isElementDisabled(getProperty("ecom.cca.mlmadmin.checkboxdisabled"),"Checkbox");
			if(isDisabled) {
		       commonPassWithScreenShotLog("Checkbox is disabled");
			}
			validateDisplayedElement(getProperty("ecom.CAA.MLMReporting"),"MLMReporting is displayed in the application");
			boolean isDisabled1=isElementDisabled(getProperty("ecom.CCA.MLMReporting.CheckBox"),"Element is disabled");
			if(isDisabled1) {
			       commonPassWithScreenShotLog("Checkbox is disabled");
				}
			findElementByXpath(getProperty("ecom.CCA.SwitchBackToAdmin")).click();
			}
		
		private void cammonSwitch()
		{
			clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
			pauseInSeconds(2);
			setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
			findElementByXpath(getProperty("ecom.cca.user.search.icon")).click();
			pauseInSeconds(1);
			//findElementByXpath(getProperty("ecom.CCA.UserName")).click();
		}
		private void technicalTabSwitchUser() {
			WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
			actionMoveToElement(element);
			pauseInSeconds(1);
			findElementByXpath(getProperty("ecom.cca.switch.user")).click();
		}
		
}