package com.hcl.usf.pages;

import java.io.File;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CCACommonPage extends CommonPageElement {

	/***
	 * EC45-267
	 */
	public void validateCCALastLoginStatus() {
		ccaLogin();
		pauseInSeconds(2);
		setText(getProperty("ecom.cca.user.search.textbox"), findData("CustomerNo"));
		captureScreenShot("Page Screenshot");

		clickElement(getProperty("ecom.cca.user.search.icon"), "Clicked search icon");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.cca.customer.details"), "Customer Details");
		String lastLogin = getText(getProperty("ecom.cca.last.login"));
		if ("Last Login Date".equalsIgnoreCase(lastLogin)) {
			validateDisplayedElement(getProperty("ecom.cca.last.login"), "Last Login Text");
		}
		String siteVersion = getText(getProperty("ecom.cca.site.version"));
		if ("SITE VERSION".equalsIgnoreCase(siteVersion)) {
			validateDisplayedElement(getProperty("ecom.cca.site.version"), "Site Version Text");
		}
		String status = getText(getProperty("ecom.cca.status"));
		if ("STATUS".equalsIgnoreCase(status)) {
			validateDisplayedElement(getProperty("ecom.cca.status"), "Status Text");
		}
		pauseInSeconds(1);
		ccaDone();
	}
	/***
	 * EC45-180
	 */
	public void validateHideCheckBussinessTool() {
		ccaLogin();
		pauseInSeconds(2);
		setText(getProperty("ecom.cca.user.search.textbox"), findData("UserId"));
		captureScreenShot("Page Screenshot");
		clickElement(getProperty("ecom.cca.user.search.icon"), "Clicked search icon");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.cca.hide.check.buss.tool"), "\"Hide Check Bussiness\" Test");
		pauseInSeconds(1);
		ccaDone();
	}
	
	/**
	 * EC45-507
	 */
	public void validateCCACustomerCareHelpDeskMGR() {
		ccaLogin();
		pauseInSeconds(2);
		tecnicalTab();
		pauseInSeconds(2);
		waitForLoad();
		clickElement(getProperty("ecom.cca.select.user.mgr"), "Select USF Customer Care HelpDeskMGR");
		clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.customers")));
		clickElement(getProperty("ecom.cca.data.transfer"), "Clicked data transfer link");
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.cca.data.transfer.upload"), "ECOM Customer Data Transfer Upload text");
		validateDisplayedElement(getProperty("ecom.cca.cust.import.file"), "Import: Customer Transfer File (required) text");
        pauseInSeconds(1);
		clickElement(getProperty("ecom.cca.cust.transfer.template"), "Clicked customer transfer template");
        uploadFile();
        validateDisplayedElement(getProperty("ecom.cca.data.transfer.validation"), "ECOM Customer Data Transfer Validation");
        pauseInSeconds(1);
        validateResultHeader();
        pauseInSeconds(1);
        clickElement(getProperty("ecom.cca.commit"), "Clicked Commit button");
        pauseInSeconds(1);
        //
        validateResultHeader();

        validateDisplayedElement(getProperty("ecom.cca.data.transfer.result"), "ECOM Customer Data Transfer result");

		validateDisplayedElement(getProperty("ecom.cca.header"), "Header");
		jsScrollDown();
		validateDisplayedElement(getProperty("ecom.cca.footer"), "Footer");
		jsScrollTop();
      
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.analyst"), "Select USF Customer Care HelpDeskAnalyst");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.custupdate"), "Select USF CustomerCare-Update");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.support.analyst"), "Select USF CustomerCare-SupportAnalyst");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.nasr.webadmin"), "Select USF NASR-WebAdmin");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.nasr.webupdate"), "Select USF NASR-WebUpdate");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.nasr.webuser"), "Select USF NASR-WebUser");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.product.upload.all"), "Select CustomerCare-ProductUploadAll");
		commonSwitchValidation();
		//
		tecnicalTab();
		clickElement(getProperty("ecom.cca.select.user.sales.division.support"), "Select USF-Divisional-SalesSupport");
		commonSwitchValidation();
		ccaDone();
	}
	
	private void validateResultHeader() {
		validateDisplayedElement(getProperty("ecom.cca.line"), "Line");
        //
        validateDisplayedElement(getProperty("ecom.cca.old.div.no"), "Old division number");
        //
        validateDisplayedElement(getProperty("ecom.cca.old.cust.no"), "Old customer number");
        
        validateDisplayedElement(getProperty("ecom.cca.new.div.no"), "New division number");
        
        validateDisplayedElement(getProperty("ecom.cca.new.cust.no"), "New customer number");
        if(isExists(getProperty("ecom.cca.results"))) {
        validateDisplayedElement(getProperty("ecom.cca.results"), "Results");
        }
        if(isExists(getProperty("ecom.cca.error"))) {
            validateDisplayedElement(getProperty("ecom.cca.error"), "Errors");
        }
		
	}
	private void uploadFile() {
		String downloadDir = USER_DIR + "\\download\\EcomCustomerDataTransferUpload.csv";
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
		File f = Paths.get(downloadDir).toFile();
		if (f.exists()) {
			f.delete();
		}
	}
	private void commonSwitchValidation() {
		clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.customers")));
		captureScreenShot("Page Screenshot");
		waitForLoad();
		if(!isExists(getProperty("ecom.cca.data.transfer"))) {
			commonPassLog("ECOM Customer Data Transfer is not visible ");
		}else {
			commonFailLog("ECOM Customer Data Transfer is visible ");
		}
	}
	/***
	 * Common Technical Tab
	 */
	private void tecnicalTab() {
        if(isExists(getProperty("ecom.cca.admin.link"))) {
		pauseInSeconds(1);
		clickElement(getProperty("ecom.cca.admin.link"), "Clicked admin link"); // Admin click reset
		pauseInSeconds(1);
        }
		WebElement element = waitForVisibilityOfElement(getProperty("ecom.cca.technical"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Page Screenshot");
		commonPassLog("Techinicl tab is displayed in the application");
		clickElement(getProperty("ecom.cca.switch.user"), "Clicked switch user link");
	}
	
	/***
	 * Ragu
	 */
	/* Team C - CCA Product Master File EC45C-21 */
	public void ccaProductMasterValidation() {
		ccaLogin();
		pauseInSeconds(2);
		tecnicalTab();
		pauseInSeconds(2);
		waitForLoad();
		clickElement(getProperty("ecom.cca.select.supportAnalyst"), "Select USF Customer Care SupportAnalyst");
		clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.cca.loggedas.supportAnalyst"), "Logged In As Support Analyst");
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.partners")));
		pauseInSeconds(2);
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.partnerManageRTI")));
		clickElement(getProperty("ecom.cca.RTIPartnerSearch"), "Selected RTIPartner Search");
		setText(getProperty("ecom.cca.RTIPartnerID"), findData("PartnerID"));
		clickElement(getProperty("ecom.cca.RTIPartnerSearchBtn"), "Click partner search button");
		validateDisplayedElement(getProperty("ecom.cca.RTIPartnerInfo"), "Partner Information Displayed");
		validateDisplayedElement(getProperty("ecom.cca.partnerProfile"), "Partner Profiles Section Displayed");
		jsScrollWindowDown();
		validateDisplayedElement(getProperty("ecom.cca.partnerprdMasterLable"), "Partner Master File Label");
		pauseInSeconds(2);
		String text = driver
				.findElement(By.xpath(getProperty("ecom.cca.partnerprdMasterLable")))
				.getAttribute("class");
		if (text.equalsIgnoreCase("fSetLabelSelected")) {
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.cca.partnerDivisions"), "Partner Division Section");
			clickElement(getProperty("ecom.cca.partnerDivisions"), "Select Divisions Sections");
			captureScreenShot("Page Screenshot");
		} else {
			clickElement(getProperty("ecom.cca.partnerprdMasterLable"), "Click Partner Master File Label CheckBox");
			clickElement(getProperty("ecom.cca.partnerUpdateBtn"), "Click update button");
			pauseInSeconds(2);
			validateDisplayedElement(getProperty("ecom.cca.partnerSuccessbanner"), "Partner updated Successfully");
			captureScreenShot("Page Screenshot");
			clickElement(getProperty("ecom.cca.partnerDivisions"), "Select Divisions Sections");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.cca.divisionCheckBox"), "Select Divisions CheckBox");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.cca.attachDivisionLink"), "Click attach selected divisions");
			validateDisplayedElement(getProperty("ecom.cca.divisionSuccessBanner"),
					"Divisions Attached Successfully with product master file option");
			captureScreenShot("Page Screenshot");
		}
	}
	
	/* Team C - CCA Product Master File EC45C-22 */
	public void CCAProductNutritionalValidation() {
		ccaLogin();
		pauseInSeconds(2);
		tecnicalTab();
		pauseInSeconds(2);
		waitForLoad();
		clickElement(getProperty("ecom.cca.select.supportAnalyst"), "Select USF Customer Care SupportAnalyst");
		clickElement(getProperty("ecom.cca.switch.user.button"), "Clicked switch to user button");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.cca.loggedas.supportAnalyst"), "Logged In As Support Analyst");
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.partners")));
		pauseInSeconds(2);
		actionMoveToElement(findElementByXpath(getProperty("ecom.cca.partnerManageRTI")));
		clickElement(getProperty("ecom.cca.RTIPartnerSearch"), "Selected RTIPartner Search");
		setText(getProperty("ecom.cca.RTIPartnerID"), findData("PartnerID"));
		clickElement(getProperty("ecom.cca.RTIPartnerSearchBtn"), "Click partner search button");
		validateDisplayedElement(getProperty("ecom.cca.RTIPartnerInfo"), "Partner Information Displayed");
		validateDisplayedElement(getProperty("ecom.cca.partnerProfile"), "Partner Profiles Section Displayed");
		jsScrollWindowDown();
		validateDisplayedElement(getProperty("ecom.cca.partnerprdNutritionalLable"), "Product Nutritional File Label");
		String text = driver
				.findElement(By.xpath(getProperty("ecom.cca.partnerprdNutritionalLable")))
				.getAttribute("class");
		if (text.equalsIgnoreCase("fSetLabelSelected")) {
			validateDisplayedElement(getProperty("ecom.cca.nutritionalOption"), "Product Nutritional File DropDown");
			captureScreenShot("Page Screenshot");
			validateDisplayedElement(getProperty("ecom.cca.partnerDivisions"), "Partner Division Section");
			clickElement(getProperty("ecom.cca.partnerDivisions"), "Select Divisions Sections");
			captureScreenShot("Page Screenshot");
		}
		 else {
			clickElement(getProperty("ecom.cca.partnerprdNutritionalLable"), "Click Product Nutritional File");
			validateDisplayedElement(getProperty("ecom.cca.nutritionalOption"), "Product Nutritional File DropDown");
			selectOption("itemDropDown", findData("NutritionalOption"), "id");
			clickElement(getProperty("ecom.cca.partnerUpdateBtn"), "Click update button");
			validateDisplayedElement(getProperty("ecom.cca.partnerSuccessbanner"),
					"Partner updated successfully with nutritional file option");
			captureScreenShot("Page Screenshot");
			clickElement(getProperty("ecom.cca.partnerDivisions"), "Select Divisions Sections");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.cca.divisionCheckBox"), "Select Divisions CheckBox");
			pauseInSeconds(2);
			clickElement(getProperty("ecom.cca.attachDivisionLink"), "Click attach selected divisions");
			validateDisplayedElement(getProperty("ecom.cca.divisionSuccessBanner"),
					"Divisions Attached Successfully with product nutritional file option");
		}

	}

}
