package com.projectName.companyName.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.projectName.companyName.utilities.DriverManager;





//import com.w2a.zoho.utilities.DriverManager;

public class ZohoHomePage extends BasePage {
		
	@FindBy(xpath="//*[contains(text(),'Login')]")
	public WebElement logIn;
	
	@FindBy(xpath="//*[@class='zh-contact']")
	public WebElement sales;
	
	
	public ZohoHomePage open(String url) {
	
		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (ZohoHomePage) openPage(ZohoHomePage.class);
	}
	
	public ZohoLoginPage gotoLogin(){
		System.out.println("inside go to login");
		click(logIn, "Login Link");
		return (ZohoLoginPage) openPage(ZohoLoginPage.class);
			
	}
	
	public ZohoContactSalesPage gotoContactSalesPage(){
		System.out.println("go to Sales");
		click(sales, "Sales");
		return (ZohoContactSalesPage) openPage(ZohoContactSalesPage.class);
			
	}
	


	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(logIn);
	}

	@Override
	protected void getPageScreenSot() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
