package com.projectName.cucumber.steps;

import io.cucumber.java.en.And;

public class ZohoSteps3 extends BaseSteps {

	private BaseSteps base;
	
	public ZohoSteps3(BaseSteps base) {
		
		this.base = base;
	}
	
	

	
	
	
	@And("^user enters the day trail form with '(.*?)' and '(.*?)' and '(.*?)'  and '(.*?)'$")
	public void user_enters_the_day_trail_form_with_Name_and_zoho_gmail_com_and_and(String name, String email, String password, String mobile)  {
	
		base.zohoCRMPage.startWith15DayTrail(name, email, password, mobile);
	
	}


	
//	@And("^user enters the day trail form with '(.*?)' and '(.*?)' and '(.*?)'  and '(.*?)'$")
//	public void user_enters_the_day_trail_form_with_and_and_and(String name, String email, String password, String mobile)  {
//		
//		zohoCRMPage.startWith15DayTrail(name, email, password, mobile);
//
//
//	}

	@And("^user click on Pricing$")
	public void user_click_on_Pricing()  {
	    
		base.zohoCRMPage.gotoPricing();
	}
	
	
	@And("^user click on Customers$")
	public void user_click_on_Customers()  {
	    
		base.zohoCRMPage.gotoCustomers();
	}
	
	
	
	

}
