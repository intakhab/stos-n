package com.hcl.usf.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Ragu
 *
 */

public class MyShoppingListPage extends CommonPageElement {

	public void validateMyListPage(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Manage Lists");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Clicked Manage Lists");
		//accessLMA();
		validateDisplayedElement(getProperty("ecom.list.management"), "List Management Landing Page");
		validateDisplayedElement(getProperty("ecom.list.management.view.list"), "List Management view all lists");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Back to US foods link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Clicked Back to US foods link");
		//CloseLMA();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.text"), "Manage by usfoods list");
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.table.name.text"), "Manage by usfoods table name text");
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.table.products.text"), "Manage by usfoods table products text");
		validateDisplayedElement(getProperty("ecom.manage.by.usfood.list"), "Manage By usfoods list name");
	}
	
	public void validateNoListLMACustomer(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.manage.list"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.list.management"), "List Management Landing Page");
		validateDisplayedElement(getProperty("ecom.list.management.view.list"), "List Management view all lists");
		validateDisplayedElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Back to US foods link");
		clickElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Clicked Back to US foods link");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.myshop.no.LMA"), "No Shopping List");
		validateDisplayedElement(getProperty("ecom.manage.usfood.list.text"), "Manage by usfoods list");
	}

	public void validateHeaderandListdropDown(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.list.LMA"), "My Shopping List");
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.manage.list"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.list.management"), "List Management Landing Page");
		validateDisplayedElement(getProperty("ecom.list.management.view.list"), "List Management view all lists");
		validateDisplayedElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Back to US foods link");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Clicked Back to US foods link");
		//CloseLMA();
		pauseInSeconds(2);
		  ArrayList<String> obtainedList = new ArrayList<>(); 
		  List<WebElement>elementList= driver.findElements(By.xpath("//*[contains(@id,'dgfSPT:pt_i3:0:pt_sfm1:pt_i1')]")); 
		  for(WebElement we:elementList)
		  { 
			  obtainedList.add(we.getText()); 
			  }
		  ArrayList<String> sortedList = new ArrayList<>();
		  for(String s:obtainedList)
		  {
		  sortedList.add(s);
		  
		  } 
		  Collections.sort(sortedList);
		  if(sortedList.equals(obtainedList)) {
			  commonPassWithScreenShotLog("List Alpa sorted");
		  }else {
			  commonFailWithScreenShotLog("List Not Alpha Sorted");
			  
		  }
		 
	}

	public void accessLMA() {
		String parent_window = driver.getWindowHandle();
		pauseInSeconds(2);
		Set<String> winIds = driver.getWindowHandles();
		for (String allWindowsIds : winIds) {
			if (!parent_window.equalsIgnoreCase(allWindowsIds)) {
				pauseInSeconds(2);
				driver.switchTo().window(allWindowsIds);
				String url = driver.getCurrentUrl();
				commonInfoLog("LMA: "+url);
				captureScreenShot("LMA App" + " is displayed");
				waitForLoad();
				pauseInSeconds(2);
				validateDisplayedElement(getProperty("ecom.list.management"), "List Management Landing Page");
				validateDisplayedElement(getProperty("ecom.list.management.view.list"), "List Management view all lists");
			}
		}
	}

	
	public void CloseLMA()
	{
		String parent_window = driver.getWindowHandle();
		Set<String> winIds = driver.getWindowHandles();
		for (String allWindowsIds : winIds) {
			if (!parent_window.equalsIgnoreCase(allWindowsIds))
			{
				driver.close();
				driver.switchTo().window(allWindowsIds);
			}

		}

	}
	
	public void validateListView(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("NListName")); 
		jsScrollWindowUp();
		validateDisplayedElement(getProperty("ecom.filter.box"), "Filter By Text Box");
		validateDisplayedElement(getProperty("ecom.list.product.display"), "Display Product");
		validateDisplayedElement(getProperty("ecom.list.sortby"), "Sort By");
		validateDisplayedElement(getProperty("ecom.list.viewgrp"), "View Group");
		validateDisplayedElement(getProperty("ecom.list.recently.purchase.flag"), "Recently Purchage Flag");
		validateDisplayedElement(getProperty("ecom.list.pagination"), "Shopping List Pagination");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.list.compact.view"), "Compact View");
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.list.detailed.view"), "Detailed View");
		filterProduct(findData("NormalProd"));
		pauseInSeconds(2);
		waitForLoad();
		clickElement(getProperty("ecom.list.detailed.view"), "Detailed View");
		validateDisplayedElement(getProperty("ecom.list.product.image"), "Product Image in detailed view");
		validateDisplayedElement(getProperty("ecom.list.product.description"), "Product Decsription in detailed view ");
		validateDisplayedElement(getProperty("ecom.list.product.brand"), "Product Brand in detailed view ");
		validateDisplayedElement(getProperty("ecom.list.product.number"), "Product Number in detailed view ");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.compact.view"), "compact View");
		pauseInSeconds(3);
		validateNotDisplayedElement(getProperty("ecom.list.product.image"), "Product Image in compact view");
		validateDisplayedElement(getProperty("ecom.list.product.description"), "Product Decsription in compact view ");
		validateDisplayedElement(getProperty("ecom.list.product.brand"), "Product Brand in compact view ");
		validateDisplayedElement(getProperty("ecom.list.product.number"), "Product Number in compact view ");
		pauseInSeconds(3);
	}
	
	public void validateProductExpandLMA(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("NListName")); 
		pauseInSeconds(2);
		filterProduct(findData("NormalProd"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.product.row.expand"),"Product Row Expand link"); 
		clickElement(getProperty("ecom.list.product.row.expand"), "Product Row Expanded");
		pauseInSeconds(3);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.lists"), "Product On Lists");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.orderhistory"), "Product OrderHistory");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdattribute"), "Product Attribute");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.storage"), "Product Storage");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdnote"), "Product Note");
		validateNotDisplayedElement(getProperty("ecom.list.product.row.expand.prdaddnote"), "Add Note Link");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdDelete"), "Product Delete");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdDetail"), "Product Details");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.product.row.expand"), "Product Row Collapsed");
	}
	
	public void validateProductExpandNonLMA(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("NListName")); 
		pauseInSeconds(2);
		filterProduct(findData("NormalProd"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.product.row.expand"),"Product Row Expand link"); 
		clickElement(getProperty("ecom.list.product.row.expand"), "Product Row Expanded");
		pauseInSeconds(3);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.lists"), "Product On Lists");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.orderhistory"), "Product OrderHistory");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdattribute"), "Product Attribute");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.storage"), "Product Storage");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdnote"), "Product Note");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdDelete"), "Product Delete");
		validateDisplayedElement(getProperty("ecom.list.product.row.expand.prdDetail"), "Product Details");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.product.row.expand"), "Product Row Collapsed");
	}
	
	
	public void validateOrderGuide(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("OGListName")); 
		pauseInSeconds(5);
		validateProducts();
	}
	
	public void validateCustomShoppingList(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("NListName")); 
		pauseInSeconds(5);
		validateProducts();
	}
	
	public void validateMLM(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		searchList(findData("MSListName")); 
		pauseInSeconds(5);
		validateProducts();
	}
	
	public void validateProducts() {
		clickElement(getProperty("ecom.list.detailed.view"), "Detailed View");
		pauseInSeconds(5);
		filterProduct(findData("SuggestedProd"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.original.product")," Original Product");	
		validateDisplayedElement(getProperty("ecom.list.suggested"),"Suggested Product");	
		validateDisplayedElement(getProperty("ecom.list.suggested.accept"),"Accept Suggested");	
		filterProduct(findData("Dis.Prod"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.discontinue.product")," Discontinue Product");
		validateDisplayedElement(getProperty("ecom.list.original.product")," Original Product");	
		validateDisplayedElement(getProperty("ecom.list.replacement.product"),"Replacement Product");	
		filterProduct(findData("Stat2.Prod"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.temp.prod"),"Temporarily Unavailable from Manufacturer");
		validateDisplayedElement(getProperty("ecom.list.unavailable.prod")," Unavailable Product");	
		validateDisplayedElement(getProperty("ecom.list.sub.prod"),"Substitue Product");	
		validateDisplayedElement(getProperty("ecom.list.replacement.searchsub"),"Search for Sub");
		filterProduct(findData("DWO"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.dwo.prd"),"DWO");
		filterProduct(findData("SpecialOrder"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.call.order"),"Call To Order");
		validateDisplayedElement(getProperty("ecom.list.call.order.icon")," Special Order Product");
		filterProduct(findData("RecentlyPurchased"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.purchase.icon"),"Recently Purchase Icon");
		filterProduct(findData("CES"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.CES.icon")," CES RED Icon");
		filterProduct(findData("DIRECT"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.direct"),"DIRECT Icon");
		filterProduct(findData("LocallySourced"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.locally.sourced"),"Locally Sourced Icon");
		filterProduct(findData("JIT"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.JIT"),"JIT Icon");
		filterProduct(findData("MyKitchen"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.mykitchen"),"My Kitchen Icon");
		filterProduct(findData("Scoop"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.scoop"),"Scoop Icon");
		filterProduct(findData("Contract"));
		pauseInSeconds(2);
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.list.contracticon"),"Contract Icon");
	}

	
	public void validateDeleteProductInList(String customerNo, String departNo) {
		commonLoginAndCustomerSearch(customerNo, departNo);
		waitForLoad();
		pauseInSeconds(2);
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"), getProperty("ecom.view.all.list"));
		validateDisplayedElement(getProperty("ecom.myshop.list.listfordeleteproductscenario"), "List for Product delete is present");
		
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Manage Lists");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Clicked Manage Lists");
		pauseInSeconds(2);
		WebElement listformodification = waitExplicitlyForClickable(getProperty("ecom.myshop.list.listforproductdeleteinlma"));
		actionMoveJSClick(listformodification);
		//clickElement(getProperty("ecom.myshop.list.listforproductdeleteinlma"), "Clicked List for modification");
		pauseInSeconds(2);
		//clickElement(getProperty("ecom.myshop.list.producttobedeletedinlma"), "Clicked Checkbox against the product to be deleted");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.myshop.list.deleteiconinfooter"), "Clicked Delete icon in Footer");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.myshop.list.submitbuttoninmodal"), "Clicked Submit button");
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.myshop.list.successmessagefordelete"),"Success Message");
		pauseInSeconds(3);
		clickElement(getProperty("ecom.myshop.list.backtousfoodslink"), "Clicked Back to US foods link");
		pauseInSeconds(4);
		String productcount = getText(getProperty("ecom.myshop.list.productcountcell"));
		if (productcount.equals("2")) {
			commonPassLog("The selected product is deleted from the list");
		} else {
			commonFailLog("The selected product is NOT deleted from the list");
		}
		pauseInSeconds(2);
		//Code to  set the Pre-requisite data again
		clickElement(getProperty("ecom.my.shopping.list.managelist.btn"), "Clicked Manage Lists");
		clickElement(getProperty("ecom.myshop.list.listforproductdeleteinlma"), "Clicked List for modification");
		clickElement(getProperty("ecom.myshop.list.addproductsbutton"), "Clicked Add Products");
		setText(getProperty("ecom.myshop.list.searchboxforproductaddition"), "5599983");
		clickElement(getProperty("ecom.myshop.list.searchbutton"), "Clicked search button");
		clickElement(getProperty("ecom.myshop.list.producttobeaddedinlma"), "Clicked product to be added in LMA");
		new Select (driver.findElement(By.xpath("//div[@class='dropdown-label' and text()='List']/following::div[1]"))).selectByVisibleText("ECOMDelete");
		clickElement(getProperty("ecom.myshop.list.addtolistbutton"), "Clicked add to list footer button");
		
	}	
	
}