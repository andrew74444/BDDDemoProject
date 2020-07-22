package com.projectName.companyName.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoCRMPage extends BasePage{

	@FindBy(xpath="//*[contains(text(),'Everlasting customer')]")
	public WebElement trustYourSales;
	
	@FindBy(xpath="//*[@id='namefield']")
	public WebElement namefield;
	
	@FindBy(xpath="//*[@id='email']")
	public WebElement email;
	
	@FindBy(xpath="//*[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//*[@id='mobile']")
	public WebElement mobile;
	
	
	
	@FindBy(xpath="//*[@class='leaf  zmenu-pricing']//*[contains(text(),'Pricing')]")
	public WebElement pricing;
	
	
	@FindBy(xpath="(//*[contains(text(),'Customers')])[3]")
	public WebElement customers;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(trustYourSales);
	}
	
	
	public void startWith15DayTrail(String name,String email,String password,String mobile) {
		type(namefield, name, "Name");
		type(this.email, email, "email");
		type(this.password, password, "Password");
		type(this.mobile, mobile, "mobile");
		
	}
	
	
	public ZohoPricingPage gotoPricing(){
		System.out.println("go to pricing");
		click(pricing, "pricing Link");
		return (ZohoPricingPage) openPage(ZohoPricingPage.class);
			
	}
	
	public ZohoCustomersPage gotoCustomers(){
		System.out.println("go to customers");
		click(customers, "customers Link");
		return (ZohoCustomersPage) openPage(ZohoCustomersPage.class);
			
	}


	@Override
	protected void getPageScreenSot() {
		// TODO Auto-generated method stub
		
	}
	
	

}
