package com.projectName.companyName.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoCustomersPage extends BasePage {

	
	
	@FindBy(xpath="//*[contains(text(),'Trusted by Over')]")
	public WebElement trustedByOver;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(trustedByOver);
	}

	@Override
	protected void getPageScreenSot() {
		// TODO Auto-generated method stub
		
	}
	
	
}
