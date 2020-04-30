package com.hcl.usf.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.hcl.usf.common.STOSException;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;
import com.hcl.usf.util.ThreadLocalUtil;

/***
 * 
 * @author intakhabalam@hcl.com
 *
 */
public class CommonPageElement extends CommonElement {
	/***
	 * Home link
	 */
	public void homeLink() {
		if(isExists(getProperty("ecom.home.link"))) {
			jsScrollTop();
			clickElement(getProperty("ecom.home.link"), "Clicked home link");
		}
	}

	public void validateTitle(String tile) {
		String pageTitle = driver.getTitle();
		if (tile.equalsIgnoreCase(pageTitle)) {
			commonPassWithScreenShotLog("Page title found-" + pageTitle);
		} else {
			commonFailWithScreenShotLog("Page title not found-" + pageTitle);
		}

	}

	/***
	 * 
	 * @param prop {@link String}
	 * @param i {@link String}
	 * @return {@link String}
	 */
	public String deletePropertyDeliminator(String prop, int i) {
		return prop.replaceAll(AppUtil.DELIMINATOR, String.valueOf(i));
	}

	/****
	 * Custom login and department search
	 * @param customerNo {@link String}
	 * @param departNo {@link String}
	 */
	public void commonLoginAndCustomerSearch(String customerNo, String departNo) {
		consoleInfoLog("Login steps start");
		if(findData("URL")!=null && !findData("URL").isEmpty()) {
			doLogin(findData("URL"));
		}else {
			doLogin();
		}
		waitForLoad();//
		consoleInfoLog("Find Customer steps start");
		pauseInSeconds(2);
		findCustomer(customerNo, departNo);
		waitForLoad();
		captureScreenShot("Page Screenshot");
		pauseInSeconds(2);
		consoleInfoLog("Login and Customer steps finished");
	}
	
	/***
	 * Select drop values
	 * @param values
	 */
	public void selectDropDownValues(String values) {
        Actions action = getAction();
		WebElement clictheobject = waitForVisibilityOfElement(".//li//a[text()='" + values + "']");
		action.moveToElement(clictheobject).perform();
		action.moveToElement(clictheobject).click();
		clictheobject.click();
	}
	
	/***
	 * THis method will take a url and credentials from xsl and do the login
	 * in cca portal
	 */
	public void ccaLogin(){
		consoleInfoLog("Login steps for CCA Portal ");
		commonInfoLog("Invoking CCA URL...." , findData("URL"));
		driver.manage().window().maximize();
		driver.navigate().to(findData("URL"));
		pauseInSeconds(2);
		setText(getProperty("ecom.cca.username"), findData("UserName"));
		setText(getProperty("ecom.cca.userpass"), findData("Password"));
		pauseInSeconds(1);
		findElementByXpath(getProperty("ecom.cca.signin")).click();
		pauseInSeconds(2);
	}

	/***
	 * This is the generic login for all screen
	 */
	public void doLogin() {
		openURL(config.cDto.getEnvUrl());
		login(config.cDto.getAppUserId(),config.cDto.getAppUserPass());
	}

	private void login(String user,String pass) {
		pauseInSeconds(1);
		waitForLoad();
		pauseInSeconds(1);
		WebElement userElement=findElementByXpath(getProperty("ecom.username"));
		waitForLoad();
		if(isExists(userElement)) {
			setText(getProperty("ecom.username"), user);
			setText(getProperty("ecom.userpass"), pass);
			pauseInSeconds(1);
			findElementByXpath(getProperty("ecom.signin")).click();
			pauseInSeconds(2);
			userLog();
			waitForLoad();
			extraPauseForChromAndIE();
		}else {
			commonFailWithScreenShotLog("It seems site is down!!!");
			ThreadLocalUtil.get().siteRunningStatus="DOWN";
		}

	}

	/***
	 * 
	 */
	public void doLogin(String url) {
		consoleInfoLog("Login steps for for parameter check ");
		openURL(url);
		pauseInSeconds(2);
		if(findData("UserName")!=null && !findData("UserName").isEmpty()) {
			login(findData("UserName"),findData("Password"));
		}else {
			login(config.cDto.getAppUserId(),config.cDto.getAppUserPass());
		}
	}
	/***
	 * User logging
	 */
	public void userLog() {
		commonInfoLog("User Id " + cdto.getAppUserId() + " is logged in!!!");
		boolean usern = isExists(getProperty("ecom.user.name"));
		if (usern) {
			commonInfoLog("Username " + findElementByXpath(getProperty("ecom.user.name")).getText());
		}
		ThreadLocalUtil.get().siteRunningStatus="UP";
	}

	/***
	 * This search is based on product when we go for order
	 * @param product {@link String}
	 */
	public void searchFilter(String product) {
		consoleInfoLog("Search Filter steps");
		commonInfoLog("Product searching in filter box");
		waitForLoad();
		pauseInSeconds(2);
		if(isExists(getProperty("ecom.cross.search.icon.img"))) {
			jsScrollWindowUp();
			clickElement(getProperty("ecom.cross.search.icon.img"), "Refreshed close button");
			pauseInSeconds(2);
		}
		pauseInSeconds(2);
		highlightElement(getProperty("ecom.filter.box"));
		setText(getProperty("ecom.filter.box"), product.trim());
		pauseInSeconds(2);
		clickElement(getProperty("ecom.filter.search"), "Clicked search icon");
		consoleInfoLog("Clicked search icon");
		pauseInSeconds(2);
	}

	/***
	 * Generic for all product search and putting values in text box
	 * @param product {@link String}
	 * @param value {@link Integer}
	 * @return {@link Boolean}
	 */
	public boolean putValueInQuantityBox(String textBoxPath, int value) {
		consoleInfoLog("Putting values in quantity box steps");
		commonInfoLog("Putting value in quantity box");
		pauseInSeconds(2);
		boolean flag = isElementDisabled(textBoxPath, "Quantity Text Box");
		//scrollIntoViewJS(textBoxPath, -90);// It will come middle of screen
		if (flag) {
			setText(textBoxPath,""+value);
		}
		consoleInfoLog("Putting values in quantity box finished");
		return flag;
	}

	/****
	 * 
	 * @param customerNumber
	 *            {@link String}
	 * @param departmentNumber
	 *            {@link String}
	 */
	public void findCustomer(String customerNumber, String departmentNumber) {
		boolean valueChanged = false;
		int loopCount = 0;
		String id, xPath, xPathForDept;
		while (!valueChanged) {
			loopCount++;
			if (loopCount > 10) {
				break;
			}
			if(checkAlreadySelectedCustomer(customerNumber,departmentNumber)) {
				break;
			}
			pauseInSeconds(2);
			//WebElement elementImg = waitExplicitlyForClickable(getProperty("ecom.customer.dropdown.img"));
			boolean flag = isExists(getProperty("ecom.customer.dropdown.img"));
			if (!flag) {
				commonInfoLog("Customer dropdown is not loaded, again checking");
				pauseInSeconds(1);
				continue;
			}
			pauseInSeconds(1);
			//elementImg.click();
			clickElement(getProperty("ecom.customer.dropdown.img"), "Clicked customer dropdown");
			pauseInSeconds(1);
			commonInfoLog("STOS able to click Customer list dropdown");
			if (flag) {
				pauseInSeconds(1);
				waitForLoad();
				if(isExists(getProperty("ecom.customer.dropdown.search"))) {
					clickElement(getProperty("ecom.customer.dropdown.search"), "Clicked customer search");
					pauseInSeconds(1);
					waitForVisibilityOfElementLocated(getProperty("ecom.customer.dropdown.search"))//dgfSPT:pt_pgl31
					.sendKeys(customerNumber);
					pauseInSeconds(1);
					commonInfoWithScreenShotLog("STOS able to Enter customer number in the Search box of Customer dropdown");
				}else {
					commonInfoWithScreenShotLog("STOS able to Enter customer number in Customer dropdown");

				}
				if (departmentNumber.equals("") || departmentNumber.isEmpty() || departmentNumber.equals("0")) {
					pauseInSeconds(1);
					findElementsByCustomerNumber(customerNumber).click();
					pauseInSeconds(4);
					extraPauseForChromAndIE();
					waitForLoad();
					valueChanged = verifyCustomerInfo(customerNumber,
							departmentNumber,null);
				} else {
					commonInfoWithScreenShotLog("STOS found department number in the Search box of Customer dropdown");
					id = getIndexFromListOfCustomers(customerNumber);
					xPath = ".//*[contains(@id,':pt_i9:" + id + ":pt_i12:') and contains(@id, 'pt_cl25')]";
					int size=getElementsSize(xPath);
					pauseInSeconds(2);
					for (int i = 0; i <size ; i++) {
						pauseInSeconds(1);
						xPathForDept = ".//*[contains(@id,':pt_i9:" + id + ":pt_i12:" + i + ":pt_cl25')]";
						if (getText(xPathForDept).replaceAll("[^0-9]", "").contains(departmentNumber)) {
							pauseInSeconds(1);
							waitExplicitlyForClickable(
									".//*[contains(@id,':pt_i9:" + id + ":pt_i12:" + i + ":pt_cl25')]").click();
							pauseInSeconds(5);
							waitForLoad();
							valueChanged = verifyCustomerInfo(customerNumber,
									departmentNumber,"depart");
							break;
						}
					}
				}
			} 
			waitForLoad();
			if (valueChanged) {
				break;
			}
		}

	}
	/***
	 * @param customerNumber {@link String}
	 * @param departmentNumber {@link String}
	 * @return {@link Boolean}
	 */
	private boolean checkAlreadySelectedCustomer(String customerNumber, String departmentNumber) {
		boolean valueChanged=false;
		if (departmentNumber.equals("") || departmentNumber.isEmpty() || departmentNumber.equals("0")) {
			valueChanged=verifyCustomerInfo(customerNumber,
					departmentNumber,null);

		}else {
			valueChanged=verifyCustomerInfo(customerNumber,
					departmentNumber,"depart");
		}
		return valueChanged;
	}

	/***
	 * @param customerNumber {@link String}
	 * @return {@link WebElement}
	 */
	private WebElement findElementsByCustomerNumber(String customerNumber) {
		return waitForVisibilityOfElementLocated(
				".//*[contains(@id,':pt_pgl117')]//*[text()='(" + customerNumber.trim() + ")']");
	}

	/***
	 * @param customerNumber {@link String}
	 * @return {@link String}
	 */
	private String getIndexFromListOfCustomers(String customerNumber) {
		WebElement element = findElementByXpath(
				".//*[contains(@id,':pt_pgl117')]//*[text()='(" + customerNumber.trim() + ")']//preceding::a[1]");
		String id = element.getAttribute("id");
		String arrID[] = id.split(":");
		return arrID[2];
	}


	/***
	 * @param customerNumberSelected {@link String} 
	 * @param customerNumber  {@link String} 
	 * @param departmentNumber  {@link String} 
	 * @return Boolean
	 */
	private boolean verifyCustomerInfo( String customerNumber, String departmentNumber,String isDepart) {
		boolean valueChanged = false;
		if(isDepart!=null) {
			customerNumber=customerNumber+departmentNumber;
		}
		waitForLoad();
		if (getCustomerNumberSelected().contains(customerNumber)) {
			valueChanged = true;
			commonPassWithScreenShotLog( "STOS is able to select customer: " + customerNumber + " with "
					+ "department: " + departmentNumber);
		} else {
			commonInfoWithScreenShotLog( "STOS is not able to select customer: " + customerNumber + " with "
					+ "department: " + departmentNumber +"- Retrying again......");
		}
		return valueChanged;

	}
	
	public void extraPauseForChromAndIE() {
		if("firefox".equalsIgnoreCase(browser) && "ie".equalsIgnoreCase(browser)) {
			pauseInSeconds(10);
		}
		waitForLoad();
	}

	/***
	 * Global search with product name
	 * @param pdname {@link String} 
	 */
	public void globalSearch(String pdname) {
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		setText(getProperty("ecom.global.search"), pdname);
		pauseInSeconds(1);
		findElementByXpath(getProperty("ecom.global.search.icon")).click();
		pauseInSeconds(1);
	}
	/***
	 * This method will validate lead time
	 * @param pageLeadTime  {@link String} 
	 * @param excelLeadTime  {@link String} 
	 * @param prod  {@link String} 
	 */
	public void validateLeadTime(String pageLeadTime, String excelLeadTime, String prod) {
		String newDate = AppUtil.dateCalculaterValue(excelLeadTime);
		consoleInfoLog("Date after Addition: " + newDate);
		String calLeadTime = "Get it by " + newDate;
		if ((pageLeadTime.contains(calLeadTime))) {
			commonInfoLog("Lead time for prod " + prod + " :: " + calLeadTime);
			commonInfoLog("Lead time in the Hover text and in the page value are same");
		} else {
			commonFailLog("Lead time in the Hover text and in the page value are not same");
		}

	}
	/***
	 * @return {@link String}
	 */
	public String getCustomerNumberSelected() {
		waitForLoad();
		String custNbr = getText(getProperty("ecom.selected.customer.text"));
		commonInfoLog("Selected customer text : " + custNbr);
		return custNbr.replaceAll("[^0-9]", "");
	}

	/***
	 * @param xPath {@link String}
	 * @param strToCmpr {@link String}
	 */
	public void validateOptionsInDropdown(String xPath, String [] strToCmpr) {
		WebElement element = driver.findElement(By.xpath(xPath));
		driver.findElement(By.cssSelector(".jqTransformSelectOpen")).click();
		pauseInSeconds(1);
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();
		int j = 0;
		String str;
		for (WebElement option : list) {
			str = option.getAttribute("title");
			commonInfoLog("Dropdown has: " + str);
			if (str.contains(strToCmpr[j])) {
				commonInfoLog( strToCmpr[j] + " is displayed in the dropdown");
			} else {
				commonFailLog( strToCmpr[j] + " is not displayed in the dropdown");
			}
			j++;
		}
	}

	/***
	 * @param listName {@link String}
	 * @return {@link Boolean}
	 */
	public boolean selectManageByUSFShoppingList(String listName) {
		commonInfoLog("Searching [ "+listName+ "] in Managed by US Foods List");
		pauseInSeconds(5);
		// Finding the values from table which need to select r1:0:pt1:lv1:0:pgl28
		List<WebElement> prodList = findElementsByXpath(getProperty("ecom.managebyusfood.list"));
		int i = 0;
		for (WebElement e : prodList) {
			String xPath = "//*[@id='r1:0:pt1:lv1:" + i + ":pgl30']";
			String text = e.findElement(By.xpath(xPath)).getText();
			if (AppUtil.removeSpace(text.trim()).
					equalsIgnoreCase(AppUtil.removeSpace(listName.trim()))) {
				String pageItem = "//a[contains(.,'" + text + "')]";
				commonInfoLog(listName + " is clicked");
				scrollIntoViewJS(pageItem, 110);
				pauseInSeconds(1);
				highlightElement(pageItem);
				pauseInSeconds(2);
				actionMoveJSClick(findElementByXpath(pageItem));
				return true;
			}
			i++;
		} //
		return false;
	}
	/****
	 * @param listName {@link String}
	 * @return {@link Boolean}
	 */
	public boolean selectMyShoppingList(String listName) {
		commonInfoLog("Searching [ "+listName+ "] in My Shopping List");
		pauseInSeconds(5);
		// Finding the values from table which need to select
		List<WebElement> prodList = findElementsByXpath(getProperty("ecom.myshopping.list"));
		int index = 0;
		for (WebElement e : prodList) {
			String xPath = "//*[@id='r1:0:pt1:lv2:" + index + ":pgl26']";
			String text = e.findElement(By.xpath(xPath)).getText();
			if (AppUtil.removeSpace(text.trim()).
					equalsIgnoreCase(AppUtil.removeSpace(listName.trim()))) {

				String pageItem = "//a[contains(.,'" + text + "')]";
				commonInfoLog(listName + " is clicked");
				scrollIntoViewJS(pageItem, 110);
				pauseInSeconds(1);
				highlightElement(pageItem);
				pauseInSeconds(2);
				actionMoveJSClick(findElementByXpath(pageItem));
				return true;
			}
			index++;
		} //
		return false;
	}

	/****
	 * @param listName {@link String}
	 * @return {@link Boolean}
	 */
	public boolean selectMyShoppingListLMA(String listName) {
		commonInfoLog("Searching [ "+listName+ "] in My Shopping List");
		pauseInSeconds(5);
		// Finding the values from table which need to select
		List<WebElement> prodList = findElementsByXpath(getProperty("ecom.myshopping.list.LMA"));
		int index = 0;
		for (WebElement e : prodList) {
			String xPath = "//*[@id='r1:0:pt1:lv2:" + index + ":pgl26']";
			String text = e.findElement(By.xpath(xPath)).getText();
			if (AppUtil.removeSpace(text.trim()).
					equalsIgnoreCase(AppUtil.removeSpace(listName.trim()))) {

				String pageItem = "//a[contains(.,'" + text + "')]";
				commonInfoLog(listName + " is clicked");
				scrollIntoViewJS(pageItem, 110);
				pauseInSeconds(3);
				highlightElement(pageItem);
				pauseInSeconds(2);
				actionMoveJSClick(findElementByXpath(pageItem));
				return true;
			}
			index++;
		} //
		return false;

	}

	/***
	 * @param text {@link String}
	 * @return {@link WebElement}
	 */
	public WebElement getListText(String text) {
		String xPath= "//a[contains(.,'" + text + "')]";
		if(isExists(xPath)) {
			return findElementByXpath(xPath);
		}else {
			return null;
		}
	}


	/***
	 * Order pop up select radio button with new order
	 */
	public void checkOrderPopUp() {
		waitForLoad();
		commonInfoLog("Checking order popup");
		if (isExists(getProperty("ecom.continue.button"))) {
			if(isExists(getProperty("ecom.new.order.radio.button.class"))){
				clickElement(getProperty("ecom.new.order.radio.button.class"), "Selected radio button for new order creation");
				pauseInSeconds(2);
			}
			clickElement(getProperty("ecom.continue.button"), "Clicked continue button for new order creation");
		}
		commonInfoLog("Order popup closed");
		pauseInSeconds(2);
		waitForLoad();
	}

	/***
	 * After submit button pop is coming
	 */
	public void conifrmOrderPopUp() {
		waitForLoad();
		commonInfoLog("Confirm order popup checking");
		pauseInSeconds(1);
		commonInfoLog("Confirm popup open");
		if (isExists(getProperty("ecom.popup.confirmation"))) {
			pauseInSeconds(2);
			clickElement(getProperty("ecom.order.popup"), "Clicked popup submit button for new order creation");
		}
		pauseInSeconds(5);
		commonInfoLog("Confirm order closed");

	}
	/***
	 * After submitting order confirmation text reading
	 * r
	 */
	public boolean confirmOrderText() {
		waitForLoad();
		pauseInSeconds(2);
		commonInfoLog("Confirmation text reading");
		String ecomCOnfim = getProperty("ecom.order.confirmation");
		if (isExists(ecomCOnfim)) {
			if ("Order Confirmation".equalsIgnoreCase(getText(getProperty("ecom.order.confirmation")))) {
				return true;
			}
		}
		return false;
	}
	/***
	 * Review And Submit Order for generic use
	 */
	public void clickReviewAndSubmitOrder() {
		waitForLoad();
		consoleInfoLog("Review and Submit steps");
		//Click Review Order
		clickElement(getProperty("ecom.review.order"), "Review Order button clicked");
		waitForLoad();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.submit"), "Submit button clicked");
		waitForLoad();
		pauseInSeconds(3);
		confirmAndResolveException();
		consoleInfoLog("Review and Submit steps finished");
	}
	/**
	 * COnfirm and Resolved Exception checked.
	 */
	public void confirmAndResolveException() {
		conifrmOrderPopUp();
		waitForLoad();
		pauseInSeconds(3);
		checkResolveException();
		pauseInSeconds(2);

	}

	/**
	 * Resolved exception will check
	 */
	public void checkResolveException() {
		consoleInfoLog("Checking resolve exception");
		if(isExists(getProperty("ecom.resolve.exception")) 
				&& isExists(getProperty("ecom.resolve.exception.radiobox"))) {
			commonInfoLog("Resolve exception found");
			validateDisplayedElement(getProperty("ecom.resolve.exception"), "Resolve Exception");
			pauseInSeconds(1);
			clickElement(getProperty("ecom.resolve.exception.radiobox"),"Clicked radio button for resolved  exception");
			pauseInSeconds(1);
			if(isExists(getProperty("ecom.submit"))) {
				clickElement(getProperty("ecom.submit"), "Submit button clicked");
			}
		}
	}
	/***
	 * Login Birch Street
	 */
	public void loginBirchStreet(String url,String userName,String userPass) {
		commonInfoLog("Navigating to BirchStreet URL");
		navigateURL(url);
		setText(getProperty("ecom.birch.street.username"), userName);
		setText(getProperty("ecom.birch.street.userpass"), userPass);
		clickElement(getProperty("ecom.birch.street.login.button"), "LogIn button clicked");
		pauseInSeconds(5);
		commonInfoLog("[ "+cdto.getAppUserId()+" ] is logged in!!!");
		waitForLoad();
		changeFrame("EntryScreen");
		clickElement(getProperty("ecom.birch.street.catlogtabid"), "Clicked catalogs tab");
		changeFrame("TopCatalog");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.birch.street.popout"), "Birch Street popout clicked");
		pauseInSeconds(5);
		switchBrowserTab(driver,1);
	}

	/***
	 * @param driver {@link WebDriver}
	 * @param tab {@link Integer}
	 * @return {@link WebDriver}
	 */
	public WebDriver switchBrowserTab(WebDriver driver,int tab) {
		List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		return  driver.switchTo().window(tabs.get(tab));
	}

	/***
	 * @param urlToOpen take url and open browser in new tab
	 */
	public void openNewBrowserTab(String urlToOpen) {
		if("chrome".equalsIgnoreCase(browser)) {
			js.executeScript("window.open()");
		}else {
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		}
		pauseInSeconds(1);
		commonInfoLog("Opened new tab in browser");
	}
	/**
	 * Login Birch Street
	 */
	public void loginByEfficent(String url,String userName,String userPass) {
		commonInfoLog("Navigating to ByEfficent URL");
		navigateURL(url);
		setText(getProperty("ecom.buy.efficient.username"), userName);
		setText(getProperty("ecom.buy.efficient.userpass"), userPass);
		clickElement(getProperty("ecom.buy.efficient.login.button"),"Clicked login button");
		pauseInSeconds(5);
		commonInfoLog(cdto.getAppUserId()+"  is logged in!!!");
		clickElement(getProperty("ecom.buy.efficient.marketplacelink"), "Clicked MarketPlace link");
		clickElement(getProperty("ecom.buy.efficient.foodandbeveragelink"), "Clicked FoodAndBeverage link");
		pauseInSeconds(5);
		clickElement(getProperty("ecom.buy.efficient.usfoodlogo"), "Clicked Usfood logo");
		pauseInSeconds(2);
		//changeFrame("punchout-frame");// iframePreLoad
	}
	/***
	  Login BrookDayle Street
	 */
	public void loginBrookDyale(String url,String userName,String userPass)  {
		commonInfoLog("Navigating to BrookDyale URL");
		navigateURL(url);
		setText(getProperty("ecom.brookdyale.username"),userName);
		setText(getProperty("ecom.brookdyale.userpass"), userPass);
		clickElement(getProperty("ecom.brookdyale.login.button"),"Clicked login button");
		pauseInSeconds(5);
		commonInfoLog(cdto.getAppUserId()+" is logged in!!!");
		clickElement(getProperty("ecom.brookdyale.usfood.icon"), "Clicked USfood Icon");
		pauseInSeconds(5);

	}

	/***
	 * Navigate to specific URL once send the parameter
	 * @param url {@link String}
	 */
	public void navigateURL(String url) {
		driver.navigate().to(url);
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {}
		url = driver.getCurrentUrl();
		commonInfoLog("Login URL : " + url);
	}

	/***
	 * This method will search list name from datasheet onto ecomportal
	 * @param listName {@link String}
	 */
	public void searchList(String listName) {
		consoleInfoLog("Search List steps");
		pauseInSeconds(2);
		WebElement element=getListText(listName);
		if(element!=null && element.getText().trim().equalsIgnoreCase(listName)) {
			commonInfoLog("List name matched with current list: "+listName);
			String xPath= "//a[contains(.,'" + listName + "')]";
			highlightElement(xPath);
			pauseInSeconds(1);
			element.click();
			pauseInSeconds(1);
			return;
		}
		// Move to shopping list and select all view list
		moveToIconAndClick(getProperty("ecom.shopping.list.icon"),getProperty("ecom.view.all.list"));
		// After loading page all list, select specific list which will be coming from
		// xsl
		waitForLoad();
		pauseInSeconds(1);
		//here i modified ragu code,
		String manageBtnText = isExists(getProperty("ecom.mlm.managebtn")) ? getText(getProperty("ecom.mlm.managebtn"))
				: null;
		boolean b=false;
		if (manageBtnText != null && "Manage List".equalsIgnoreCase(manageBtnText.trim())) {
			b = selectMyShoppingListLMA(listName);
		} else {
			b = selectMyShoppingList(listName);
		}
		if (!b) {
			b = selectManageByUSFShoppingList(listName);
			if (!b) {
				throw new STOSException(
						listName + " list name not found in \"MyShopping List\" and \"ManageByUsFood Shopping\" List");
			}
		}
		consoleInfoLog("Search List steps finished");
	}

	/***
	 * This method will filter product once you select with list name and search product from data sheet
	 * @param pd {@link String}
	 * @return {@link Integer} size of product in table
	 */
	public int filterProduct(String pd) {
		waitForLoad();
		pauseInSeconds(2);
		searchFilter(pd);
		waitForLoad();
		pauseInSeconds(2);// pause 3 seconds 
		waitForLoad();
		pauseInSeconds(7);// pause 3 seconds 
		waitForLoad();
		int size= getElementsSize(getProperty("ecom.filter.prod.size"));
		pauseInSeconds(2);// pause 3 seconds 
		commonInfoLog("Filter product size :"+size);
		return size;
	}
	/**
	 * Generic method will Search Product from suggested update page
	 * @param pd  Product name
	 */
	public boolean validateProductAtSuggestedUpdatePage(String pd) {
		pauseInSeconds(2);
		String prd = "//span[contains(.,'# " + pd + "') or contains(.,'#" + pd + "')]";
		if(isExists(prd)) {
			scrollIntoViewJS(prd, -250);
			pauseInSeconds(1);
			validateDisplayedElement(prd, pd+" product");

			pauseInSeconds(1);
			String suggXpath=getProperty("ecom.pdp.sugg.prod.label").replaceAll(AppUtil.DELIMINATOR, pd);
			if(isExists(suggXpath)) {
				String suggText=getText(suggXpath);
				pauseInSeconds(1);
				validateDisplayedElement(suggXpath, suggText+" product");
			}
			String sugg2Xpath=getProperty("ecom.pdp.sugg.prod.span").replaceAll(AppUtil.DELIMINATOR, pd);
			if(isExists(sugg2Xpath)) {
				String sugg2Text=getText(sugg2Xpath);
				pauseInSeconds(1);
				validateDisplayedElement(sugg2Xpath, sugg2Text+" product");
			}
			return true;
		}
		else {
			captureScreenShot("Suggested product not found at \"Suggested Product Update Page\"");
			//commonInfoLog("Suggested product not found at \"Suggested Product Update Page\"");
			return false;
		}
	}

	/**
	 * @param xpath {@link String} xpath
	 * @param tagName {@link String} and tagname
	 * @return {@link String}
	 */
	public String getCssValue(String xpath,String tagName) {
		try {
			return findElementByXpath(xpath).getCssValue(tagName);
		} catch (Exception e) {
			return null;
		}
	}
     
	/***
	 * Pass listname at listpage 
	 * @param listName
	 */
	public void validatelistHeaderAtListProductPage(String listName) {
		String listHeader = getProperty("ecom.list.header.text").replaceAll(AppUtil.DELIMINATOR, listName);
		if(isExists(listHeader)) {
		   validateDisplayedElement(listHeader, listName);
		}
	}
	/***
	 * Validate suggested product at Review/Submit order page
	 * @param index {@link Integer}
	 * @return String Later try to remove it
	 */
	public String validateSuggestedSellFilterProduct(int index) {
		pauseInSeconds(1);
		scrollIntoViewJS(getProperty("ecom.suggested.prod.text2"), -270);// Automatically up
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.original.prod.text"), "Original Product");
		// Validate Suggested product
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.prod.text2"), "Suggested Product");
		// Validate pack size box
		pauseInSeconds(1);
		// Validate check box
		String currentPro = deletePropertyDeliminator(getProperty("ecom.original.prod.name"), index);
		validateDisplayedElement(currentPro, "Original product");
		String cText = waitForVisibilityOfElementLocated(currentPro).getText();
		String currProduct = cText.split(" ")[0].replaceAll("#", "").trim();
		commonInfoLog("Original product name :: " + currProduct);
		pauseInSeconds(1);
		String suggProd = deletePropertyDeliminator(getProperty("ecom.suggested.prod.name"), (index + 1));
		String text = waitForVisibilityOfElementLocated(suggProd).getText();
		String suggestedProd = text.split(" ")[0].replaceAll("#", "").trim();
		commonInfoLog("Sugested product name :: " + suggestedProd);
		validateDisplayedElement(suggProd, "Suggested product");
		pauseInSeconds(1);
		// Validating suggested product text box
		String originalTextBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), index);
		pauseInSeconds(1);
		String originalTextBoxPathEA = deletePropertyDeliminator(getProperty("ecom.quantity.textbox.ea"), index);
		// Validating CS Text
		validateDisplayedElement(originalTextBoxPathCS, "Original Product CS text box");
		// Validating EA Text
		if (isExists(originalTextBoxPathEA)) {
			validateDisplayedElement(originalTextBoxPathEA, "Original Product EA text box");
		}
		String suggTextBoxPathCS = deletePropertyDeliminator(getProperty("ecom.quantity.textbox"), (index + 1));
		pauseInSeconds(1);
		String sugTextBoxPathEA = deletePropertyDeliminator(getProperty("ecom.quantity.textbox.ea"), (index + 1));
		// Validating CS Text
		validateDisplayedElement(suggTextBoxPathCS, "Suggested Product CS text box");
		// Validating EA Text
		if (isExists(sugTextBoxPathEA)) {
			validateDisplayedElement(sugTextBoxPathEA, "Suggested Product EA text box");
		}
		return suggestedProd;
	}

	/***
	 * Common Pack Size validation at Review/Submit order page
	 * @param index {@link Integer}
	 */
	public void validatePackSize(int index) {
		String originalPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"),index);
		String orignalPack =getText(originalPackXpath);
		commonInfoLog("Original Pack size " + orignalPack);
		String suggestedPackXpath = deletePropertyDeliminator(getProperty("ecom.prod.size.pack"), (index+1));
		String suggestedPack = getText(suggestedPackXpath);
		commonInfoLog("Suggested Pack  size " + suggestedPack);
		if (orignalPack.trim().equalsIgnoreCase(suggestedPack.trim())) {
			commonInfoLog("Original Pack and Suggested Pack size are same");
		} else {
			if(isExists(getProperty("ecom.packsize.different.text"))) {
				validateDisplayedElement(getProperty("ecom.packsize.different.text"), "Original Pack and Suggested Pack size are different");
			}
		}
	}
	/**
	 * This method will validate the Suggestion and Check at Review/Submit order page
	 * @param index {@link Integer}
	 */
	public void validateAcceptSuggestionAndClick(int index) {
		validateDisplayedElement(getProperty("ecom.accept.suggestion"), "Accept Suggestion");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.accept.suggestion.checkbox.new"), "Clicked Suggested Check Box");
		// Validate check box
		pauseInSeconds(3);
		waitForLoad();
		clickContinueButton();
	}

	public void clickContinueButton() {
		int i = 0;
		while (i < 15) {
			if (isExists(getProperty("ecom.continue.button"))) {
				clickElement(getProperty("ecom.continue.button"), "Continue Button");
				pauseInSeconds(3);
				break;
			}
			pauseInSeconds(2);
			i++;
		}
	}
	/***
	 * Right Rail validation for product order and product case at Review/Submit order page
	 */
	public void validateRightRailCurrentOrdAndQntity() {
		validateDisplayedElement(getProperty("ecom.product.case.text"), "Current product case");
		validateDisplayedElement(getProperty("ecom.total.product.text"), "Products in Order");
	}
	//Click to View Current Product from Image Icon navigate to PDP
	public void clickToPDPPageFromProdImageIcon(int index) {
		String xPath=getProperty("ecom.original.prod.link");//.replaceAll(AppUtil.DELIMINATOR, ""+index);
		validateDisplayedElement(xPath, "Image icon");
		clickElement(xPath, "Clicked product icon will navigate to PDP page.");
		pauseInSeconds(3);
		validateDisplayedElement(getProperty("ecom.pdp.blue.header"), "Blue header");
		pauseInSeconds(1);
		clickElement(getProperty("ecom.pdp.view.suggested.prod.link"), "Clicked \"View Suggested Product\" link");
		pauseInSeconds(2);

	}
	// Validate Suggested  Compare product at PDP page
	public boolean validateProductCompareWithAcceptButton(String pd, String pd2) {
		boolean isDisabled = false;
		validateDisplayedElement(getProperty("ecom.product.compare.header"), "Product Comparison header");
		validateDisplayedElement(getProperty("ecom.product.current.header"), "Current Product header");
		validateDisplayedElement(getProperty("ecom.product.suggested.header2"), "Suggested Product header");
		scrollIntoViewJS(getProperty("ecom.product.accept"), 100);// Automatically up
		validateDisplayedElement(getProperty("ecom.product.accept"), "Acccept");
		pauseInSeconds(1);
		validateProductAtSuggestedUpdatePage(pd);
		if(pd2!=null) {
			validateProductAtSuggestedUpdatePage(pd2);
		}
		isDisabled = isElementDisabled(getProperty("ecom.product.accept"), "Accept Button");
		if (isDisabled) {
			consoleInfoLog("Clicked accept button");
			actionMoveJSClick(findElementByXpath(getProperty("ecom.product.accept")));
		} else {
			jsScrollWindowUp();// UP windows
		}
		return isDisabled;
	}

	//Right Rail suggested product and Exclusive drop down product and Go to Suggested Page
	public void validateSugSellAtMenuDropdownAndRightRailGoToSuggPage() {
		consoleInfoLog("Validating suggeseted product link right rail and dropdown");
		homeLink();
		pauseInSeconds(1);
		validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"), "Suggested Products Right Rail");
		pauseInSeconds(1);
		jsScrollTop();
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		validateDisplayedElement(getProperty("ecom.suggested.update"), "Suggested Product Updates");
		pauseInSeconds(1);
		actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
		clickElement(getProperty("ecom.suggested.update"), "Clicked suggested product updates link");
		pauseInSeconds(1);
		consoleInfoLog("Finished suggeseted product link right rail and dropdown");
		jsScrollTop();
	}

	//Right Rail suggested product and Exclusive drop down product
	public boolean validateSugSellAtMenuDropdownAndRightRailPage() {
		boolean isfound = false;
		pauseInSeconds(1);
		if (isExists(getProperty("ecom.suggested.prod.right.rail.text"))) {
			validateDisplayedElement(getProperty("ecom.suggested.prod.right.rail.text"),
					"Suggested Products Right Rail");
			pauseInSeconds(1);
			jsScrollWindowUp();
			pauseInSeconds(1);
			actionMoveToElement(findElementByXpath(getProperty("ecom.our.exculsive")));
			validateDisplayedElement(getProperty("ecom.suggested.update"), "Suggested Product Updates");
			isfound = true;
		} else {
			isfound = false;
		}
		return isfound;

	}

	/***
	 * Product Grid and Detailed View-AddToList
	 */

	public void productViewAddToList(String linkName) {
		clickElement(getProperty("ecom.grid.active.icon.search"), "Grid View");
		for (int i = 1; i <= 2; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		waitForLoad();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.active.icon"), "List View");
		for (int i = 1; i <= 2; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		pauseInSeconds(2);
		clickElement(getProperty("ecom.grid.active.icon.search"), "Grid View");
		pauseInSeconds(2);
		captureScreenShot("Items are showing for comparison");
		for (int i = 1; i <= 4; i++) {
			String xpathCheckbox = getProperty("ecom.product.comapre.checkbox").replaceAll(AppUtil.DELIMINATOR, "" + i);
			clickElement(xpathCheckbox, "Clicked checkbox " + i);
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		clickElement(getProperty(linkName), "Back To Page");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.list.active.icon"), "List View");
		pauseInSeconds(4);
		captureScreenShot("Items are showing for comparison");
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
	}

	/***
	 * Product Grid and Detailed View-NoAddToList
	 */

	public void productViewNoAddToList(String linkName) {
		clickElement(getProperty("ecom.grid.active.icon.search"), "Grid View");
		for (int i = 1; i <= 2; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		waitForLoad();
		pauseInSeconds(3);
		clickElement(getProperty("ecom.list.active.icon"), "Detailed View");
		for (int i = 1; i <= 2; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		pauseInSeconds(2);
		clickElement(getProperty("ecom.grid.active.icon.search"), "Grid View");
		pauseInSeconds(2);
		captureScreenShot("Items are showing for comparison");
		for (int i = 1; i <= 4; i++) {
			String xpathCheckbox = getProperty("ecom.product.comapre.checkbox").replaceAll(AppUtil.DELIMINATOR, "" + i);
			clickElement(xpathCheckbox, "Clicked checkbox " + i);
			pauseInSeconds(1);
		}
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}
		clickElement(getProperty(linkName), "Back To Page");
		pauseInSeconds(2);
		clickElement(getProperty("ecom.list.active.icon"), "List View");
		pauseInSeconds(4);
		captureScreenShot("Items are showing for comparison");
		pauseInSeconds(1);
		jsScrollWindowUp();
		pauseInSeconds(1);
		clickElement(getProperty("ecom.home.prod.compare"), "Clicked Product Compare button");
		pauseInSeconds(2);
		for (int i = 1; i <= 4; i++) {
			String xpathAddOrderText = getProperty("ecom.product.add.order.text").replaceAll(AppUtil.DELIMINATOR,
					"" + i);
			validateNotDisplayedElement(xpathAddOrderText, "Add to... icon");
			pauseInSeconds(1);
		}

	}

}