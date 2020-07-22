package com.projectName.companyName.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoContactSalesPage extends BasePage{

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(salesEnquiries);
	}
		
	@FindBy(xpath="//*[contains(text(),'Welcome to Zoho')]")
	public WebElement salesEnquiries;


	@FindBy(xpath="(//*[contains(text(),'Products')])[1]")
	public WebElement products;
	
	
	@FindBy(xpath="//span[contains(text(),'Sales')]")
	public WebElement sales;
	
	@FindBy(xpath="(//*[contains(text(),'CRM')])[1]")
	public WebElement cRM;
	
	
	
	public ZohoCRMPage gotoCRM() throws Exception{
		System.out.println("Zoho Sales Page");
		Actions actions = new Actions(driver);
		actions.moveToElement(products);
		click(products, "products");
		Thread.sleep(2000);
		actions.moveToElement(sales);
		Thread.sleep(2000);
		click(cRM, "CRM");	 
		
		return (ZohoCRMPage) openPage(ZohoCRMPage.class);
			
	}



	@Override
	protected void getPageScreenSot() {
		// TODO Auto-generated method stub
		
	}
	
}
