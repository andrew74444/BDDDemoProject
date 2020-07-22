package com.projectName.cucumber.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/features/validateCustomers.feature",
		glue="com.projectName.cucumber.steps"
		
		//dryRun = true
		)



public class ValidateCustomerFeatureRunner extends AbstractTestNGCucumberTests {
	
	
	
}
