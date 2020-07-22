package com.projectName.companyName.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoPricingPage extends BasePage {

	
	
	@FindBy(xpath="//*[contains(text(),'Building great customer')]")
	public WebElement buildingGreatCustomer;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(buildingGreatCustomer);
	}

	@Override
	protected void getPageScreenSot() {
		// TODO Auto-generated method stub
		
	}
	
	
}
