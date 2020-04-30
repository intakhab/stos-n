package com.hcl.usf.pages;

import com.hcl.usf.util.DBConnection;

public class DeepLinkPage extends CommonPageElement {

	public void validateSuggestedSellDeepLink() {
		doLogin(findData("URL"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.deeplink.suggestedSell"), "Suggested Product Updates");
		validateDisplayedElement(getProperty("ecom.deeplink.allsuggestedProducts"),
				"Select all suggested products checkbox");
		validateDisplayedElement(getProperty("ecom.deeplink.currentProduct"), "Current Product");
		validateDisplayedElement(getProperty("ecom.deeplink.suggestedProduct"), "Suggested Product");
	}

	public void validateProductDetailsPageDeepLink() {
		doLogin(findData("URL"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.deeplink.productdetailPage"), "Product Detail Page");
		validateDisplayedElement(getProperty("ecom.deeplink.productdetailPageImg"), "Product Detail Page Image");
	}

	public void validatePromotionPageDeepLink() {
		doLogin(findData("URL"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.deeplink.promotionPage"), "Promotion Page");
		validateDisplayedElement(getProperty("ecom.deeplink.promotionOffer"), "Promotion Offers");
		validateDisplayedElement(getProperty("ecom.deeplink.promotionOfferDetail"), "Promotion Offer Details Link");
	}

	public void validateSearchResultsPageDeepLink() {
		doLogin(findData("URL"));
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.deeplink.searchResults"), "Search Results Page");
		validateDisplayedElement(getProperty("ecom.deeplink.searchResultsProducts"), "Search Results Page Products ");
	}

	public void validateFSALink() {
		DBConnection
				.doUpdateTesting("update ecom.ec_business_cntl SET EC_APPL_VAL='Y' where EC_APPL_KEY='FSA_SSA_ENABLE'");
		pauseInSeconds(2);
		openURL(config.cDto.getEnvUrl());
		jsScrollWindowDown();
		pauseInSeconds(2);
		validateDisplayedElement(getProperty("ecom.fsaLink"), "FSA Link on ECOM Landing Page");
		validateDisplayedElement(getProperty("ecom.fsalink.clickHere"), "FSA Click Here Link");
		clickElement(getProperty("ecom.fsalink.clickHere"), "Click here to naviate into FSA Langing Page");
		pauseInSeconds(1);
		String url = driver.getCurrentUrl();
		commonInfoLog("Login URL : " + url);
		validateDisplayedElement(getProperty("ecom.fsa.landingPage"), "FSA Click Here Link");
	}

}
