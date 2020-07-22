package com.projectName.cucumber.steps;

import com.aventstack.extentreports.Status;
import com.projectName.companyName.ExtentListeners.ExtentManager;
import com.projectName.companyName.ExtentListeners.ExtentTestManager;
import com.projectName.companyName.PageObjects.*;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



//import com.w2a.zoho.utilities.DriverManager;




public class ZohoSteps extends BaseSteps {

	
	
	private BaseSteps base;
	
	public ZohoSteps(BaseSteps base) {
		
		this.base = base;
	}

	protected Scenario scenario;
	static String scenarioName;
	

	@Before
	public synchronized void  before(Scenario scenario) {

		
		this.scenario = scenario;
		scenarioName = scenario.getName();
		setUpFramework();
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	
	
	@Given("^launch browser '(.*?)'$")
	public void launch_browser(String browserName)  {
		ExtentTestManager.startTest("Scenario : " + scenario.getName()+" @"+browserName);
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
		base.openBrowser(browserName);
		ExtentTestManager.logInfo("Browser Opened : "+browserName);

	   
	}

	@When("^user navigates to the URL '(.*?)'$")
	public void user_navigates_to_the_URL(String URL)  {
		
		base.home = new ZohoHomePage().open(URL);
		ExtentTestManager.logInfo("Application Opened : "+URL);
	}

	@Then("^user click on login$")
	public void user_click_on_login()  {
		
		base.home.gotoLogin();
	   
	}
	
	@And("^user click on Contact Sales$")
	public void user_click_on_Contact_Sales()  {
	    
		base.zohoContactSalesPage =	base.home.gotoContactSalesPage();
	}

	@And("^user mouse hover on products sales$")
	public void user_mouse_hover_on_products_sales() throws Exception  {
		
	    
	}
	
	

	

}
