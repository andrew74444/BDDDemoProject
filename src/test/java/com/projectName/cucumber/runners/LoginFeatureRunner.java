package com.projectName.cucumber.runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;





@CucumberOptions(
		features="src/test/resources/features/login.feature",
		glue="com.projectName.cucumber.steps",
		monochrome = true,
				tags= {"@end2endTesting"},
//		format={"pretty"},
		dryRun = false,
		plugin= {"pretty","html:cucumber"}
		
		)



public class LoginFeatureRunner extends AbstractTestNGCucumberTests  {
	
	
	

}
