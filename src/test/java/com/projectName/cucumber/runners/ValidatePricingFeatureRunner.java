package com.projectName.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/features/validatePricing.feature",
		glue="com.projectName.cucumber.steps"
				
		//dryRun = true
				
		)



public class ValidatePricingFeatureRunner extends AbstractTestNGCucumberTests{
	
	
	

}
