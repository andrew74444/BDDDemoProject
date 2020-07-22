package com.projectName.cucumber.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/features/validateCRM.feature",
		glue="com.projectName.cucumber.steps"
			
		)



public class ValidateCRMFeatureRunner extends AbstractTestNGCucumberTests{
	
	
	

}
