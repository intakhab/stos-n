package com.hcl.usf.pages;
/***
 * 
 * @author Nithya@hcl.com
 * This page will capture all the page methods for promotion project
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebElement;

public class PromotionsBusinessPage extends CommonPageElement {

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	public void valdiatePromotionsOurExclusive(String customerId, String departmentId) {
		commonLoginAndCustomerSearch(customerId, departmentId);
		/*
		 * PromotionsBusinessPage waitForLoad(); pauseInSeconds(5);
		 * findCustomer(customerId, departmentId); waitForLoad();
		 */

		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		// WebElement promotionHeaderLink =
		// driver.findElement(By.xpath(getProperty("ecom.promotions.ecom.promotions.link")));
		clickElement(getProperty("ecom.promotions.link"), "clicking promotion link");

		pauseInSeconds(1);
		// verify page title

		validateTitle("Promotions | US Foods");
		
		// Verify Bread crump
		validateDisplayedElement(getProperty("ecom.promotions.breadcrumb"),
				"Bread crump in promotion page");
		// Verify promotion is present in breadcrump
		validateDisplayedElement(getProperty("ecom.promotions.promotxtbrdcrmb"),
				"Bread crump and promotion text");
		// Verify Promotion header
		validateDisplayedElement(getProperty("ecom.promotions.promotionheader"),
				"Promotion Header in promotion page");
		// Verify Promotion names are sorted and get the no of promotion
		int totalPromotions = verifyPromotionaresorted();
		validateProductInformation();
		int noofzones = findElementsByXpath(getProperty("ecom.promotions.zones")).size();
		if (noofzones == totalPromotions) {
			commonPassWithScreenShotLog(
					"The zones are found and it is equal to total no of promotions :" + totalPromotions);
		} else {
			commonFailWithScreenShotLog(
					"The zones are found and it is not equal to total no of promotions :" + totalPromotions);
		}
	}

	/***
	 * @author c3e6026 To display the promotion is sorted in left rail of the
	 *         promotion and return the count
	 */
	public int verifyPromotionaresorted() {
		List<String> promotionNames = new ArrayList<String>();
		List<WebElement> promotionList = findElementsByXpath(getProperty("ecom.promotions.promotionnamesleftrail"));
		int totalpromotions = 0;
		commonInfoLog("Size of the prmotion elment is :: " + promotionList.size());
		String promo2 = promotionList.get(0).getText();
		String[] promo1 = promo2.split("\n");
		for (String promo : promo1) {
			String promoName = promo.trim();
			promotionNames.add(promoName);
			totalpromotions++;
		}
		List<String> sortedPromotion = new ArrayList<>(promotionNames);
		
		Collections.sort(sortedPromotion, String.CASE_INSENSITIVE_ORDER);
		
		commonInfoLog("The promotion nams are " + promotionNames);
		commonInfoLog("The sorted promotion nams are " + sortedPromotion);

		if (promotionNames.toString().contentEquals(sortedPromotion.toString())) {
			commonPassWithScreenShotLog("The promotions are sorted and promotion names are" + promotionNames);
		} else {
			commonFailWithScreenShotLog("The promotions are not sorted and promotion names are" + promotionNames);
		}

		commonInfoLog("No of promotion is the left rail: " + totalpromotions);
		return totalpromotions;

	}

	/***
	 * @author M406026 Method to click Right arrow in carousal till the Promotion
	 *         details page is displayed
	 */
	public void clickRightArrowInCarousal() {

		for (int i = 0; i <= 4; i++) {
			clickElement(getProperty("ecom.promotions.productcarousalimage"),
					"Clicked right arrow in carousal against the  promotion which contains more than 20 products");
			pauseInSeconds(2);
		}
		validateDisplayedElement(getProperty("ecom.promotions.promotionsubpagefooter"),	"Promotions details");
		commonPassWithScreenShotLog("The promotions details page is displayed");

	}

	/***
	 * @author M406026 Method to verify Green Colour in Right arrow Carousal in the
	 */
	public void verifyGreenColourInCarousal() {

		for (int i = 0; i <= 3; i++) {
			clickElement(getProperty("ecom.promotions.productcarousalimage"),
					"Clicked right arrow in carousal against the  promotion which contains more than 20 products");
			pauseInSeconds(2);
		}
		String colorString = findElement2ByXpath(getProperty("ecom.promotions.productcarousalimage"))
				.getAttribute("class");
		String[] CarousalColor = colorString.split("#");
		if (CarousalColor[1].equals("008000")) {

			commonPassWithScreenShotLog("The carousal in the last set of product is in Green Colour");
		}

	}

	/***
	 * @author M406026 Method for verification of 4 Products per Zone
	 */

	public void validatefourproductsperzone() {
		{
			validateDisplayedElement(getProperty("ecom.promotions.firstproductinpromotion"),
					"First product is displayed in the promotion");
			validateDisplayedElement(getProperty("ecom.promotions.secondproductinzone"),
					"Second product is displayed in the promotion");
			validateDisplayedElement(getProperty("ecom.promotions.thirdproductinzone"),
							"Third product is displayed in the promotion");
		    validateDisplayedElement(getProperty("ecom.promotions.fourthproductinzone"),
									"Fourth product is displayed in the promotion");			
			validateNotDisplayedElement(getProperty("ecom.promotions.fifthproductinpromotion"),
					"Fifth product is not displayed in the promotion");
		}

	}

	/***
	 * 
	 * 
	 * validate the product information within a zone for a single product
	 *
	 */

	public void validateProductInformation() {
		// validate image
		validateDisplayedElement((getProperty(("ecom.promotions.prdctimgzone"))), "Product image");
		// Promotion Banner with Offer Details Link
		validateDisplayedElement((getProperty(("ecom.promotions.promobnrofferdetaillnk"))),
				"Promotion Banner offer detail link");
		// Promotion banner discount value
		validateDisplayedElement((getProperty(("ecom.promotions.promobnrdiscountvalue"))), "Discount value");
		// validate product Description
		validateDisplayedElement((getProperty(("ecom.promotions.productdescribtion"))), "Product Decription");
		// Validate product id
		validateDisplayedElement((getProperty(("ecom.promotions.prdctid"))), "Product id");
		// Validate Add to cart
		validateDisplayedElement((getProperty(("ecom.promotions.addtoordrctid"))), "Add to cart button");

	}

	/***
	 * 
	 * @author M406026 validate the productID's are sorted in Numerical Order
	 *
	 */

	public boolean sortProductId() {
		List<WebElement> theList = findElementsByXpath(getProperty(("ecom.promotions.productidlist")));
		List<Integer> Prices = new ArrayList<>();
		for (int numberOfItem = 1; numberOfItem < theList.size(); numberOfItem++) {
			consoleInfoLog("Feteching by xpath price");
			String priceOneRaw = getText("//*[@id='p_" + numberOfItem + "_1']/div/");
			consoleInfoLog("Fetching by xpath price--------" + priceOneRaw);
			//// below 2 lines are to get the price in exact integers
			String priceOneFinal = priceOneRaw.replaceAll("[\\-\\?\\+\\.\\^:,\\\u20B9]", "");
			String PriceComplete = priceOneFinal.split("Rs")[1];
			int finalPrice = Integer.parseInt(PriceComplete);
			Prices.add(finalPrice);
		}
		int previousPrice = Prices.get(0);
		for (int sortCheckNumber = 1; sortCheckNumber < theList.size() - 1; sortCheckNumber++) {
			if (Prices.get(sortCheckNumber) < previousPrice) {
				consoleInfoLog("Not Sorted");
				return false;
			} else {
				previousPrice = Prices.get(sortCheckNumber);
			}
		}
		consoleInfoLog("All Sorted");
		return true;
	}

	/***
	 * @author M406026 Method for verification of Promotion Banner
	 */

	public void validatepromotionbanner() {
		// Verify Bread crump
		validateDisplayedElement(getProperty("ecom.promotions.breadcrumb"),
				"Bread crump in promotion page");
		// Verify Promotion header
		validateDisplayedElement(getProperty("ecom.promotions.promotionheader"),
				"Promotion Header in promotion page");
	}

	/***
	 * @author M406026 Method for verification of MY Kitchen Banner
	 */

	public void validateMyKitchenBanner() {
		validateDisplayedElement(getProperty("ecom.promotions.mykitchenpageheader"),
				"My Kitchen Header in promotion page");
	}

	/***
	 * @author M406026 Method for verification of Default Banner
	 */

	public void validateDefaultBanner() {
		String mainWindow = driver.getWindowHandle();
		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String childWinow = i1.next();
			if (!mainWindow.equalsIgnoreCase(childWinow)) {
				// Switching to Child window
				driver.switchTo().window(childWinow);
				validateDisplayedElement(getProperty("ecom.promotions.defaultbannersurveytext"),
						"Default banner survey page ");
				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(mainWindow);

	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	public void validationOfMyKitchenLink(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		actionMoveJSClick(promotionHeaderLink);
		pauseInSeconds(3);
		// verify My Kitchen Link
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.promotions.mykitchenlink"),
				"My Kitchen in promotion page");
		// Clicking on My Kitchen Link
		waitForLoad();
		clickElement(getProperty("ecom.promotions.mykitchenlink"), "Clicked My Kitchen Link");
		// Verify My Kitchen header in My Kitchen page
		pauseInSeconds(10);
		validateDisplayedElement(getProperty("ecom.promotions.mykitchenpageheader"),
				"My Kitchen Header in promotion page");

	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId.
	 * @author M406026
	 */
	public void validationOfPromotionGrouping(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		pauseInSeconds(1);
		// verification of 4 products displayed per zone
		validatefourproductsperzone();
		// Zone Number,order Verification and Promotion names are sorted and get the no
		// of promotion and Zones
		int totalPromotions = verifyPromotionaresorted();
		validateProductInformation();
		int noofzones =findElementsByXpath(getProperty("ecom.promotions.zones")).size();
		if (noofzones == totalPromotions) {
			commonPassWithScreenShotLog(
					"The zones are found and it is equal to total no of promotions :" + totalPromotions);
		} else {
			commonFailWithScreenShotLog(
					"The zones are found and it is not equal to total no of promotions :" + totalPromotions);
		}
		// Product details link and its navigation to product details page
		validateDisplayedElement(getProperty("ecom.promotions.productlink"),"Product Link in promotion page");
		clickElement(getProperty("ecom.promotions.productlink"), "Product Link has been clicked");
		validateDisplayedElement(getProperty("ecom.promotions.productdetailspageimage"),"Product Details Image in Product Details page");
		goBack();
		// Verification of Product Carousal in Promotions page
		waitForLoad();
		validateDisplayedElement(getProperty("ecom.promotions.productcarousalimage"), "Product carousal");
		// Verification of view all link and navigation to Promotion subpage:
		clickElement(getProperty("ecom.promotions.viewalllink"), "Product Link has been clicked");
		validateTitle("Promotions | US Foods");
		validateDisplayedElement(getProperty("ecom.promotions.promotionsubpagefooter"),
				"Promotion subpage footer");

	}

	

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	public void validationOfPromotionInMyKitchen(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement mykitchenHeaderLink = waitExplicitlyForClickable(getProperty("ecom.mykitchen.link"));
		jsClick(mykitchenHeaderLink);
		pauseInSeconds(1);
		// Clicking My Kitchen Offer page
		clickElement(getProperty("ecom.promotions.yourexclusiveofferinmykitchenofferpage"),
				"My Kitchen Offer page has been clicked");
		// Validate Promotion Link is displayed in the My Kitchen page
		validateDisplayedElement(getProperty("ecom.promotions.mykitchenofferpageheader"),
				"My Kitchen Offer page header");
		validateDisplayedElement(getProperty("ecom.promotions.promotionlinkinmykitchenofferpage"),
				"Promotion Link in My Kitchen Offer page");
		// Changing the Customer in which user is having My kitchen without Promotions
		findCustomer("20946042", "0");
		waitForLoad();
		WebElement ourExculsive1 = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive1);
		WebElement mykitchenHeaderLink1 = waitExplicitlyForClickable(getProperty("ecom.mykitchen.link"));
		jsClick(mykitchenHeaderLink1);
		pauseInSeconds(1);
		// Clicking My Kitchen Offer page
		clickElement(getProperty("ecom.promotions.yourexclusiveofferinmykitchenofferpage"),
				"My Kitchen Offer page has been clicked");
		// Validate Promotion Link is displayed in the My Kitchen page
		validateDisplayedElement(getProperty("ecom.promotions.mykitchenofferpageheader"),
				"My Kitchen Offer page header");
		validateNotDisplayedElement(getProperty("ecom.promotions.promotionlinkinmykitchenofferpage"),
				"Promotion Link in My Kitchen Offer page");

	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 */
	public void validationOfSubpagesUnderPromotionPage(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		// validate whether the expired Promotion is not displayed in the Promotions
		// page
		validateNotDisplayedElement(getProperty("ecom.promotions.expiredpromotionname"),
				"Expired Promotion in promotion page");
		// Validation of Product Information
		validateProductInformation();
		// sort Products in numeric order
		//sortProductId();
		// Product details link and its navigation to product details page
		validateDisplayedElement(getProperty("ecom.promotions.productlink"),"Product Link in promotion page");
		clickElement(getProperty("ecom.promotions.productlink"), "Clicked Product Link");
		validateDisplayedElement(getProperty("ecom.promotions.productdetailspageimage"),"Product Details Image in Product Details page");
		goBack();
		// Verification of view all link and navigation to Promotion subpage:
		clickElement(getProperty("ecom.promotions.viewalllink"), "Clicked view all link");
		validateTitle("Promotions | US Foods");
		
		validateDisplayedElement(getProperty("ecom.promotions.promotionsubpagefooter"),
				"Promotion subpage footer");
		// validation of product count in Promotions page
		clickElement(getProperty("ecom.promotions.bulkpromotionname"), "Clicked bulk promotion link");
		validateDisplayedElement(getProperty("ecom.promotions.productscountxpath"),
				"48 products in Promotion details page");
		// Add Product to Order
		clickElement(getProperty("ecom.promotions.addtoorderbutton"), "Clicked Add to order button");
		pauseInSeconds(10);
		clickElement(getProperty("ecom.promotions.continuebutton"), "Clicked Continue button");
		pauseInSeconds(20);
		validateDisplayedElement(getProperty("ecom.promotions.addproducttoordersuccessmessage"),
				"Product added to order success message");

	}

	/*
	 * @author:c3e6026 Validating deeplink
	 * 
	 */

	public void validateDeepLink() {
		doLogin(findData("URL"));
		pauseInSeconds(5);
		// verify page title

		validateTitle("Promotions | US Foods");
		
		// Verify Bread crump
		validateDisplayedElement(getProperty("ecom.promotions.breadcrumb"),
				"Bread crump in promotion page");
		// Verify promotion is present in breadcrump
		validateDisplayedElement(getProperty("ecom.promotions.promotxtbrdcrmb"),
				"Bread crump and promotion text");
		// Verify Promotion header
		validateDisplayedElement(getProperty("ecom.promotions.promotionheader"),
				"Promotion Header in promotion page");

	}
	
	/*
	 * @author:406026 Validating Product Carousal
	 * 
	 */

	public void ValidateProductCarousal(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		// Verify whether the Left arrow is not displayed in the promotion landing page
		validateNotDisplayedElement(getProperty("ecom.promotions.productcarousalleftarrow"),
				"Left arrow in the carousal");
		// Click on the Product carousal right arrow against the promotion which
		// contains more than 20 products
		clickElement(getProperty("ecom.promotions.productcarousalimage"),
				"Clicked right arrow in carousal against the  promotion which contains more than 20 products");
		// Verification of both Left and Right Arrow
		validateDisplayedElement(getProperty("ecom.promotions.productcarousalimage"),
				"Right arrow in the carousal");
		validateDisplayedElement(getProperty("ecom.promotions.productcarousalleftarrow"),
				"Left arrow in the carousal");
		// Clicking the Right arrow till the Promotion details page is displayed
		//clickRightArrowInCarousal();
	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 * @author M406026
	 */
	public void validateFilteringLogicOnPromotions(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		// Clicking the Promotion which is configured for MSL exclusion
		clickElement(getProperty("ecom.promotions.promotionnamemsl"),
				"Clicked the promotion which is configured for MSL");
		// Verify whether the Product is not listed in Promotion page
		validateNotDisplayedElement(getProperty("ecom.promotions.mslproductidforbogopromotion"),
				"product excluded from MSL");
		// Changing the Customer
		findCustomer("702738", "0");
		waitForLoad();
		WebElement ourExculsive1 = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive1);
		WebElement promotionHeaderLink1 = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink1);
		// Clicking the Promotion which is configured for Special Product
		clickElement(getProperty("ecom.promotions.promotionnamespecialproduct"),
				"Clicked the promotion which is configured for Special product");
		// Verify whether the Product is not listed in Promotion page
		validateNotDisplayedElement(getProperty("ecom.promotions.specialproductidforbogopromotion"),
				"Special product");
		// Clicking the Promotion which is configured for status2,3,chef status
		// 2,3,direct and OG Products
		clickElement(getProperty("ecom.promotions.promotionnameformultiverification"),
				"Clicked the promotion which is configured for Multi verfication");
		// Verify whether the Products are not listed in Promotion page
		validateNotDisplayedElement(getProperty("ecom.promotions.status2productforpriyapromotion"),
				"Status 2 product");
		validateNotDisplayedElement(getProperty("ecom.promotions.status9productforpriyapromotion"),
				"Status 9 product");
		validateNotDisplayedElement(getProperty("ecom.promotions.cashcarryidn3productforpriyapromotion"),
				"Cash carry ind3 product");
		validateNotDisplayedElement(getProperty("ecom.promotions.cashcarryidn2productforpriyapromotion"),
				"Cash carry ind2 product");
		validateNotDisplayedElement(getProperty("ecom.promotions.ogproductforpriyapromotion"),
				"OG product");
		validateNotDisplayedElement(getProperty("ecom.promotions.directproductforpriyapromotion"),
				"DIRECT product");
	}

	/*
	 * 
	 * @param customerId
	 * @param departmentId
	 * @author M406026
	 * 
	 */
	public void validateDeeplinkInPromotionModule() {
		validateDeeplinkInPromotionLandingPage();
		validateDeeplinkInProductDetailPage();
		validateDeeplinkInSuggestedProductPage();
		validateDeeplinkInProductSearchPage();
		validateDeeplinkInOrderConfirmationPage();
		validateDeeplinkInOrderSubmittedWithExceptions();
		validateDeeplinkInPastDue();
		validateDeeplinkInPaymentDue();
	}

	public void validateDeeplinkInPromotionLandingPage() {
		doLogin(findData("URL"));
		pauseInSeconds(5);
		// verify promotion landing page
		//validateTitle("Promotions | US Foods");
		// Verify promotion is present in breadcrump
		validateDisplayedElement(getProperty("ecom.promotions.promotxtbrdcrmb"),
				"Bread crump and Promotion text");
		// Verify Promotion header
		validateDisplayedElement(getProperty("ecom.promotions.promotionheader"),
				"Promotion Header in Promotion page");
	}

	public void validateDeeplinkInProductDetailPage() {
		doLogin(findData("Deeplink URL1"));
		pauseInSeconds(5);
		// verify promotion landing page
		// Verify Product Description page
		String pageTitle1 = driver.getTitle();
		if (("Product Detail | US Foods").equalsIgnoreCase(pageTitle1)) {
			commonPassWithScreenShotLog("Page title found-" + pageTitle1);
		} else {

			commonFailWithScreenShotLog("Page title not found-" + pageTitle1);
		}
		// Verify Product Description page
		validateDisplayedElement(getProperty("ecom.promotions.productdetailspageimage"),
				"Product Details page");

	}

	public void validateDeeplinkInSuggestedProductPage() {
		doLogin(findData("Deeplink URL2"));
		pauseInSeconds(5);

		// Verify Suggested Product page Title
		validateTitle("Suggested Product Updates | US Foods");
		// Verify Suggested Product page
		validateDisplayedElement(getProperty("ecom.promotions.suggestedproductpageheader"),
				"Suggested Product page");

	}

	public void validateDeeplinkInProductSearchPage() {
		doLogin(findData("Deeplink URL3"));
		pauseInSeconds(5);

		// Verify Product search page Title
		validateTitle("Shop Products | US Foods");
		// Verify Product search page
		validateDisplayedElement(getProperty("ecom.promotions.productsearchpageheader"),
				"Product search pag");

	}

	public void validateDeeplinkInOrderConfirmationPage() {
		doLogin(findData("Deeplink URL4"));
		pauseInSeconds(5);

		// Verify One Time Payment page Title
		validateTitle("Home Page  | US Foods");
		// Verify Order submitted with exceptions Notification
		validateDisplayedElement(getProperty("ecom.promotions.ordersubmittedheader"),
				"Order submitted header");
		validateDisplayedElement(getProperty("ecom.promotions.ordersubmittedwithexceptionsheader"),
				"Order submitted with exceptions Notification");
	}

	public void validateDeeplinkInOrderSubmittedWithExceptions() {
		doLogin(findData("Deeplink URL5"));
		pauseInSeconds(5);

		// Verify One Time Payment page Title
		validateTitle("Home Page  | US Foods");
		// Verify Order submitted with exceptions Notification
		validateDisplayedElement(getProperty("ecom.promotions.ordersubmittedheader"),
				"Order submitted header");
		validateDisplayedElement(getProperty("ecom.promotions.ordersubmittedwithexceptionsheader"),
				"Order submitted with exceptions notification");
	}

	public void validateDeeplinkInPastDue() {
		doLogin(findData("Deeplink URL6"));
		pauseInSeconds(5);

		// Verify One Time Payment page Title
		validateTitle("Make One-Time Payment | US Foods");
		// Verify One Time Payment page
		validateDisplayedElement(getProperty("ecom.promotions.makeonetimepaymentheader"),
				"Make One time Payment Header");
		validateDisplayedElement(getProperty("ecom.promotions.managepaymentbutton"),
				"Manage Payments button");
	}

	public void validateDeeplinkInPaymentDue() {
		doLogin(findData("Deeplink URL7"));
		pauseInSeconds(5);
		// Verify Suggested Product page Title
		validateTitle("Make One-Time Payment | US Foods");
		// Verify One Time Payment page
		validateDisplayedElement(getProperty("ecom.promotions.makeonetimepaymentheader"),
				"Make One time Payment Header");
		validateDisplayedElement(getProperty("ecom.promotions.managepaymentbutton"),
				"Manage Payments button");
	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 * @author M406026
	 */
	public void validateRightCarousalArrow(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		// Click on the Product carousal right arrow against the promotion which
		// contains more than 20 products
		clickElement(getProperty("ecom.promotions.productcarousalimage"),
				"Clicked right arrow in carousal against the  promotion which contains more than 20 products");
		// Click on the carousal till last Right carousal arrow is displayed
		verifyGreenColourInCarousal();
	}

	/***
	 * 
	 * @param customerId
	 * @param departmentId
	 * @author M406026
	 */
	public void validatePromoName(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		WebElement ourExculsive = waitExplicitlyForClickable(getProperty("ecom.our.exculsive"));
		actionMoveToElement(ourExculsive);
		WebElement promotionHeaderLink = waitExplicitlyForClickable(getProperty("ecom.promotions.link"));
		jsClick(promotionHeaderLink);
		// Verification of Tandem promo name 1
		validateDisplayedElement(getProperty("ecom.promotions.tandempromoname1"),
				"Tandem Promo name Flyer deals");
		// Click on the tandem promo name Flyer deals
		clickElement(getProperty("ecom.promotions.tandempromoname1"), "Clicked tandem promo name Flyer deals");
		// validation of Promotion Subpage
		validateDisplayedElement(getProperty("ecom.promotions.promotionsubpagefooter"),
				"Promotion subpage footer");
		
		validateTitle("Promotions | US Foods");
		// Verification of Tandem promo name 2
		validateDisplayedElement(getProperty("ecom.promotions.tandempromoname2"),
				"Tandem Promo name Stockup deals");
		// Click on the tandem promo name Stockup deals
		clickElement(getProperty("ecom.promotions.tandempromoname2"), "Clicked tandem promo name Stockup deals");
		// validation of Promotion Subpage
		validateDisplayedElement(getProperty("ecom.promotions.promotionsubpagefooter"),
				"Promotion subpage footer");
		validateTitle("Promotions | US Foods");
	}
		
	/***
	 *
	 * @author M406026
	 */

	public void validateDeeplinkInMyOrdersWithExceptionsInLab() {
		doLogin(findData("Deeplink URL4"));
		pauseInSeconds(5);
		// Verify One Time Payment page Title
		//validateTitle("My Orders  | US Foods");
		// Verify Order submitted with exceptions Notification
		validateDisplayedElement(getProperty("ecom.promotions.myorderpageheader"), "My Order Page header");
		validateDisplayedElement(getProperty("ecom.promotions.ordersubmittedwithexceptionsheader"),
				"Order submitted with exceptions notification");
	}

	/***
	 *
	 * @author M406026
	 */
	public void validateDeeplinkInPlacedOrdersInLab() {
		doLogin(findData("Deeplink URL5"));
		pauseInSeconds(5);
		// Verify One Time Payment page Title
		validateTitle("Placed Orders | US Foods");
		// Verify Order submitted with exceptions Notification
		validateDisplayedElement(getProperty("ecom.promotions.placedorderpageheader"), "Placed Order header");
		validateDisplayedElement(getProperty("ecom.promotions.orderstatusheader"), "Order status submitted");
	}

	/*
	 * 
	 * @param customerId
	 * @param departmentId
	 * @author M406026
	 * 
	 */
	public void validateDeeplinkInLab() {
		//validateDeeplinkInPromotionLandingPage();
		validateDeeplinkInProductDetailPage();
		validateDeeplinkInSuggestedProductPage();
		validateDeeplinkInProductSearchPage();
		validateDeeplinkInMyOrdersWithExceptionsInLab();
		validateDeeplinkInPlacedOrdersInLab();
	}

	/***
	 * @author M406026
	 * @param customerId
	 * @param departmentId
	 */
	public void validateHomepageMerchandisingZones(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		// Verification of Zone 1 and 3 for cutomer #1
		clickElement(getProperty("ecom.promotions.zone1hmpg"), "Clicked Zone1 in Homepage");
		// Verification of Promotion in Zone 1
		validatepromotionbanner();
		goBack();
		clickElement(getProperty("ecom.promotions.zone3hmpg"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 3
		validateDefaultBanner();
		// Changing the Customer #2
		// Verification of Zone 1 for cutomer #2
		pauseInSeconds(10);
		findCustomer("53971891", "0");
		waitForLoad();
		// Verifying the absence of Zone1
		validateNotDisplayedElement(getProperty("ecom.promotions.zone1hmpg"), "Zone 1 in Homepage");
		// Verifying the absence of Zone3
		validateNotDisplayedElement(getProperty("ecom.promotions.zone3hmpg"), "Zone 3 in Homepage");
		// Clicking the default banner moved in place of Zone 1
		clickElement(getProperty("ecom.promotions.zone1hmpgfornobanner"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 1
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #3
		findCustomer("40965964", "0");
		waitForLoad();
		// Verifying the absence of Zone1
		validateNotDisplayedElement(getProperty("ecom.promotions.zone1hmpg"), "Zone 1 in Homepage");
		// Clicking on Zone2 to verify default banner
		clickElement(getProperty("ecom.promotions.zone2hmpg"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 2
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #4
		findCustomer("50811777", "0");
		waitForLoad();
		// Verification of Zone 1 and 3 for customer #1
		clickElement(getProperty("ecom.promotions.zone1hmpg"), "Clicked Zone1 in Homepage");
		// Verification of Promotion in Zone 1
		validateMyKitchenBanner();
		goBack();
		clickElement(getProperty("ecom.promotions.zone3hmpg"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 3
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #5
		findCustomer("29785", "0");
		waitForLoad();
		// Verifying the absence of Zone1
		validateNotDisplayedElement(getProperty("ecom.promotions.zone1hmpg"), "Zone 1 in Homepage");
		clickElement(getProperty("ecom.promotions.zone2hmpg"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 1
		validateDefaultBanner();
		
	}	
		/***
		 * @author M406026
		 * @param customerId
		 * @param departmentId
		 */
		public void validateHomepageMerchandisingZones1(String customerId, String departmentId) {
		// Login and selection of Customer
		commonLoginAndCustomerSearch(customerId, departmentId);
		waitForLoad();
		// Verification of Zone 1 and 3 for customer #1
		clickElement(getProperty("ecom.promotions.zone1hmpg"), "Clicked Zone1 in Homepage");
		// Verification of Promotion in Zone 1
		validatepromotionbanner();
		goBack();
		// Verifying the absence of Zone3
		validateNotDisplayedElement(getProperty("ecom.promotions.zone3hmpg"), "Zone 3 in Homepage");
		// Clicking on Zone2 to verify default banner
		clickElement(getProperty("ecom.promotions.zone1hmpg"), "Clicked Zone in Homepage");
		// Verification of default banner in Zone 2
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #7
		findCustomer("93784965", "0");
		waitForLoad();
		// Verifying the absence of Zone1
		validateNotDisplayedElement(getProperty("ecom.promotions.zone1hmpg"), "Zone 1 in Homepage");
		// Verifying the absence of Zone3
		validateNotDisplayedElement(getProperty("ecom.promotions.zone3hmpg"), "Zone 3 in Homepage");
		// Clicking the default banner moved in place of Zone 1
		clickElement(getProperty("ecom.promotions.zone1hmpgfornobanner"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 1
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #8
		findCustomer("20946042", "0");
		waitForLoad();
		// Verification of Zone 1 and 3 for cutomer #1
		clickElement(getProperty("ecom.promotions.zone1hmpg"), "Clicked Zone1 in Homepage");
		// Verification of Promotion in Zone 1
		validateMyKitchenBanner();
		goBack();
		// Verifying the absence of Zone3
		validateNotDisplayedElement(getProperty("ecom.promotions.zone3hmpg"), "Zone 3 in Homepage");
		// Clicking on Zone2 to verify default banner
		clickElement(getProperty("ecom.promotions.zone2hmpg"), "Clicked Zone2 in Homepage");
		// Verification of default banner in Zone 2
		validateDefaultBanner();
		pauseInSeconds(10);
		// Changing the Customer #9
		findCustomer("90485426", "0");
		waitForLoad();
		// Verifying the absence of Zone1
		validateNotDisplayedElement(getProperty("ecom.promotions.zone1hmpg"), "Zone 1 in Homepage");
		// Verifying the absence of Zone3
		validateNotDisplayedElement(getProperty("ecom.promotions.zone3hmpg"), "Zone 3 in Homepage");
		// Clicking the default banner moved in place of Zone 1
		clickElement(getProperty("ecom.promotions.zone1hmpgfornobanner"), "Clicked Zone1 in Homepage");
		// Verification of default banner in Zone 1
		validateDefaultBanner();

	}
}
