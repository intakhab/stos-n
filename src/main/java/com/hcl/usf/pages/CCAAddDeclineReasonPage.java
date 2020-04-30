package com.hcl.usf.pages;

import java.awt.AWTException;
import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author intakhabalam.s@hcl.com
 *        
 */
public class CCAAddDeclineReasonPage extends CommonPageElement {
           
	/**
	 * 
	 * @param customerNo
	 * @param departNo
	 */
	public void validateCCAConversionReportsPage(String customerNo, String departNo) {
		// CCA Login 
		ccaLogin();
		pauseInSeconds(2);
		//Navigate to SL conversion Upload page
		WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Capture Screenshot");
		commonPassLog("Techinicl tab is displayed in the application");
		findElementByXpath(getProperty("ecom.cca.slconversion.reportlink")).click();
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.section.header"));
		isExists(getProperty("ecom.cca.slconversion.section.header"));
		captureScreenShot("Capture Screenshot");
		commonPassLog("SL conversion - search criteria page is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.header"));
		isExists(getProperty("ecom.cca.slconversion.page.header"));
		commonPassLog("SL conversion - search criteria page header is displayed");
		//validate the date filed are available on the SLconversion report page
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.startdate"));
		commonPassLog("SL conversion - search criteria page start date field is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.enddate"));
		commonPassLog("SL conversion - search criteria page End date field is displayed");
		//seleting the current date
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();        
		String todayAsString = df.format(today);
		System.out.println("Today is: " + todayAsString);
		
		//changinf start date to current date
		findElementByXpath(getProperty("ecom.cca.slconversion.page.startdate")).clear();
		setText(getProperty("ecom.cca.slconversion.page.startdate"), todayAsString );
		captureScreenShot("Capture Screenshot");
		commonPassLog("SL conversion - search criteria page Start date changed to current date");
		//clickin on search btn from the SL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.slconversion.page.Search.btn")).click();
		
	    //SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.slconversion.reportpage.header"));
		
		commonPassLog("SL conversion - Report page is displayed");
		
		 //Validating No data is available for the selected data 
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.Nodatamsg"));
		isExists(getProperty("ecom.cca.slconversion.reportpage.Nodatamsg"));
		
		pauseInSeconds(2);
		captureScreenShot("Capture Screenshot");
		commonPassLog("SL conversion - Report page is displayed with No Data available in the page");
		
		
		WebElement element1=waitForVisibilityOfElement(getProperty("ecom.cca.techinical.tab"));
		actionMoveToElement(element1);
		pauseInSeconds(1);
		findElementByXpath(getProperty("ecom.cca.slconversion.reportlink")).click();
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.section.header"));
		isExists(getProperty("ecom.cca.slconversion.section.header"));
		captureScreenShot("Capture Screenshot");
		commonPassLog("SL conversion - Search criteria page is dispalayed");
		
		//clickin on search btn from the SL conversion page
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.page.Search.btn"));
		findElementByXpath(getProperty("ecom.cca.slconversion.page.Search.btn")).click();
		commonPassLog("SL conversion - Search criteria Page search button is clicked");		
        //SL conversion page report Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.reportpage.header"));
		isExists(getProperty("ecom.cca.slconversion.reportpage.header"));
				
		captureScreenShot("Capture Screenshot");
		commonPassLog("SL conversion - Search report page is dispalayed");
		
		//SL conversion page cloumn header Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.custaction"));
		isExists(getProperty("ecom.cca.slconversion.clmnheader.custaction"));
	
		commonPassLog("SL conversion - Search report page Column header cust auction is displayed");
		waitForVisibilityOfElement(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));
		isExists(getProperty("ecom.cca.slconversion.clmnheader.refusalreson"));
	
		commonPassLog("SL conversion - Search report page Column header Refusal reason is displayed");
		///

//		iMDone();
		pauseInSeconds(1);
	}
	
	public void validateCCACustomerlastlogin(String customerNo, String departNo) {
		// CCA Login 
		ccaLogin();
		
		//search the customer
		waitForVisibilityOfElement(getProperty("ecom.cca.search.txtbox"));
		isExists(getProperty("ecom.cca.search.txtbox"));
	
		commonPassLog("Search box is displayed in the CCA ");
		
		setText(getProperty("ecom.cca.search.txtbox"), "33356783");
		
		
		waitForVisibilityOfElement(getProperty("ecom.cca.search.subbtn"));
		isExists(getProperty("ecom.cca.search.subbtn"));
	
		commonPassLog("Search button is displayed in the CCA ");
		findElementByXpath(getProperty("ecom.cca.search.subbtn")).click();
		///
      //Customer details page header
		waitForVisibilityOfElement(getProperty("ecom.cca.cust.details.header"));
		isExists(getProperty("ecom.cca.cust.details.header"));
	
		commonPassLog("Customer details page header is displayed in the CCA ");
		
		waitForVisibilityOfElement(getProperty("ecom.cca.lastlogin.clm.aftersiteversion"));
		isExists(getProperty("ecom.cca.lastlogin.clm.aftersiteversion"));
	
		commonPassLog("Last Login cloumn is dispalyed after the site version column ");
		
		waitForVisibilityOfElement(getProperty("ecom.cca.lastlogin.clm.beforestatus"));
		isExists(getProperty("ecom.cca.lastlogin.clm.beforestatus"));
	
		commonPassLog("Last Login cloumn is dispalyed Before the status column ");
		
//		iMDone();
		pauseInSeconds(1);
	}
	
	
	public void validateccaUserSearch(String customerNo, String departNo) throws AWTException {
		// CCA Login 
		ccaLogin();
		//User Hover
				waitForVisibilityOfElement(getProperty("ecom.cca.User.tab"));
				pauseInSeconds(2);
				//Navigate to SL conversion Upload page
				WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.User.tab"));
				actionMoveToElement(element);
				pauseInSeconds(1);
				captureScreenShot("Capture Screenshot");
				commonPassLog("User  tab is displayed in the application");
				
				
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.link"));
				pauseInSeconds(1);
				findElementByXpath(getProperty("ecom.cca.Usersearch.link")).click();
				commonPassLog("Usersearch link is clicked");
				
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.header"));
				isExists(getProperty("ecom.cca.search.subbtn"));
				commonPassLog("Usersearch page header is displayed");
				
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Extendsearch.link"));
				isExists(getProperty("ecom.cca.Extendsearch.link"));
				findElementByXpath(getProperty("ecom.cca.Extendsearch.link")).click();
				commonPassLog("Usersearch page extended search link is clicked");
				
				//User search page check box Validation	
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.mlm.txt"));
				isExists(getProperty("ecom.cca.Extendsearch.link"));
				commonPassLog("Usersearch MLM link is displayed");
				
						
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.Gladmin.txt"));
				isExists(getProperty("ecom.cca.Usersearch.Gladmin.txt"));
				commonPassLog("Usersearch Gladmin link is displayed");
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.dwostatus.txt"));
				isExists(getProperty("ecom.cca.Usersearch.dwostatus.txt"));
				commonPassLog("Usersearch DWO status link is displayed");
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.hideBusinessAnalytics.txt"));
				isExists(getProperty("ecom.cca.Usersearch.hideBusinessAnalytics.txt"));
				commonPassLog("Usersearch Hide Business Analytics link is displayed");
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.trueReports.txt"));
				isExists(getProperty("ecom.cca.Usersearch.trueReports.txt"));
				commonPassLog("Usersearch truereport link is displayed");
				
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.cpmReports.txt"));
				isExists(getProperty("ecom.cca.Usersearch.cpmReports.txt"));
				commonPassLog("Usersearch CPM report link is displayed");
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.punchOut.txt"));
				isExists(getProperty("ecom.cca.Usersearch.punchOut.txt"));
				commonPassLog("Usersearch Puncout link is displayed");
				
				
				//Select client or concept option from drop down
				selectOption("clientSelection","COUNTER","id");
				pauseInSeconds(2);
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.page.header"));
				isExists(getProperty("ecom.cca.Usersearch.page.header"));
				commonPassLog("Usersearch result page header link is displayed");
				
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn1"));
				isExists(getProperty("ecom.cca.Usersearch.clmn1"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn2"));
				isExists(getProperty("ecom.cca.Usersearch.clmn2"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn3"));
				isExists(getProperty("ecom.cca.Usersearch.clmn3"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn4"));
				isExists(getProperty("ecom.cca.Usersearch.clmn4"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn5"));
				isExists(getProperty("ecom.cca.Usersearch.clmn5"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn6"));
				isExists(getProperty("ecom.cca.Usersearch.clmn6"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn7"));
				isExists(getProperty("ecom.cca.Usersearch.clmn7"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn8"));
				isExists(getProperty("ecom.cca.Usersearch.clmn8"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn9"));
				isExists(getProperty("ecom.cca.Usersearch.clmn9"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn10"));
				isExists(getProperty("ecom.cca.Usersearch.clmn01"));
				waitForVisibilityOfElement(getProperty("ecom.cca.Usersearch.clmn11"));
				isExists(getProperty("ecom.cca.Usersearch.clmn11"));
				commonPassLog("All the column details are displayed in the user search result page");
				
				
	}
	
	
	public void validateccaWMTtester(String customerNo, String departNo) throws AWTException {
		// CCA Login 
		ccaLogin();
		//User Hover
		pauseInSeconds(2);
				waitForVisibilityOfElement(getProperty("ecom.cca.resources.tab"));
				pauseInSeconds(2);
				//Navigate to SL conversion Upload page
				WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.resources.tab"));
				actionMoveToElement(element);
				pauseInSeconds(1);
				captureScreenShot("Capture Screenshot");
				commonPassLog("recources  tab is displayed in the application");
				pauseInSeconds(1);
				if(isExists(getProperty("ecom.cca.wmttester.link"))){
					commonFailLog("WMT teaster link is displayed");
				}else{
					commonPassLog("WMT teaster link is not displayed");
				}
	}
	
	public void validateccaUpdatehidevalueaddedservices(String customerNo, String departNo) throws AWTException {
		// CCA Login 
		ccaLogin();
		
		//search the customer
		waitForVisibilityOfElement(getProperty("ecom.cca.search.txtbox"));
		isExists(getProperty("ecom.cca.search.txtbox"));
	
		commonPassLog("Search box is displayed in the CCA ");
		
		setText(getProperty("ecom.cca.search.txtbox"), "foruser120");
		
		
		waitForVisibilityOfElement(getProperty("ecom.cca.search.subbtn"));
		isExists(getProperty("ecom.cca.search.subbtn"));
	
		commonPassLog("Search button is displayed in the CCA ");
		findElementByXpath(getProperty("ecom.cca.search.subbtn")).click();
		///
      
		
		//Customer details page header
		waitForVisibilityOfElement(getProperty("ecom.cca.User.details.header"));
		isExists(getProperty("ecom.cca.User.details.header"));
	
		commonPassLog("User details page header is displayed in the CCA ");
		
		
		waitForVisibilityOfElement(getProperty("ecom.cca.hidebusiness.checkbox"));
		isExists(getProperty("ecom.cca.hidebusiness.checkbox"));
	
		commonPassLog("Hide check Business tool text is updated in user details page");
		
		
	
	//User Hover
		waitForVisibilityOfElement(getProperty("ecom.cca.User.tab"));
		pauseInSeconds(2);
		//Navigate to SL conversion Upload page
		WebElement element=waitForVisibilityOfElement(getProperty("ecom.cca.User.tab"));
		actionMoveToElement(element);
		pauseInSeconds(1);
		captureScreenShot("Capture Screenshot");
		commonPassLog("Use  tab is displayed in the application");
		
		
		
		waitForVisibilityOfElement(getProperty("ecom.cca.Dataimport.link"));
		pauseInSeconds(1);
		findElementByXpath(getProperty("ecom.cca.Dataimport.link")).click();
		commonPassLog("Data Import link is clicked");
		
		//upload btn
		waitForVisibilityOfElement(getProperty("ecom.cca.Dataimport.Upload.txt"));
					String downloadDir = System.getProperty("user.dir") + "\\download\\CustomerImportExample.csv";
					js.executeScript("document.getElementById('defaultFocus').focus();");
					pauseInSeconds(1);
					Actions action=new Actions(driver);
					action.sendKeys(Keys.ESCAPE).build().perform();
					pauseInSeconds(3);
					driver.switchTo().activeElement().sendKeys(downloadDir);
					captureScreenShot("Capture Screenshot");
					pauseInSeconds(1);
					clickElement("//*[@id='mainDiv']/div[2]/img", "Clicked upload file button");
					pauseInSeconds(1);
					captureScreenShot("Capture Screenshot");
					File f = Paths.get(downloadDir).toFile();
					if (f.exists()) {
						f.delete();
					}
	
		

		commonPassLog("Data Import Upload button is clicked");
		
	  

		// import view header validation
		waitForVisibilityOfElement(getProperty("ecom.cca.Importview.header"));
		commonPassLog("Import page is dispalyed");
		
		
		//show all link 
		waitForVisibilityOfElement(getProperty("ecom.cca.showall.link"));
		findElementByXpath(getProperty("ecom.cca.showall.link")).click();
		commonPassLog("Show all link is clicked");
		
		//Hide check business tool column Validation
		waitForVisibilityOfElement(getProperty("ecom.cca.hidecheck.clmn"));
		isExists(getProperty("ecom.cca.hidecheck.clmn"));
	
		commonPassLog("Import user page  Hide check business tool column is displayed");
		
		
		
//		iMDone();
		pauseInSeconds(1);
	}
	

}
