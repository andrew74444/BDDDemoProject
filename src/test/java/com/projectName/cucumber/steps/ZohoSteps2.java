package com.projectName.cucumber.steps;

import io.cucumber.java.en.And;

public class ZohoSteps2 extends BaseSteps {

	private BaseSteps base;
	
	public ZohoSteps2(BaseSteps base) {
		
		this.base = base;
	}
	
	

	
	
	@And("^user click on CRM$")
	public void user_click_on_CRM() throws Exception  {
		base.zohoCRMPage = base.zohoContactSalesPage.gotoCRM();
	    
	}
	
		
	
	
	

}
