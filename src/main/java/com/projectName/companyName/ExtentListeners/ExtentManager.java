package com.projectName.companyName.ExtentListeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.projectName.companyName.utilities.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;


 
  
public class ExtentManager {
 
	static ExtentReports extent;
    static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
        	
        	 ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/"+fileName);
  	       
 	        
 	        htmlReporter.config().setTheme(Theme.DARK);
 	        htmlReporter.config().setDocumentTitle(fileName);
 	        htmlReporter.config().setEncoding("utf-8");
 	        htmlReporter.config().setReportName(fileName);
 	        
 	       extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Vinod");
	        extent.setSystemInfo("Training", "Selenium");
	        extent.setSystemInfo("Contact No", "9704033377");
	        extent.setSystemInfo("Organization", "xyz");
	        extent.setSystemInfo("Build no", "xyz-3.4");
	        
        }
        return extent;
    }
  
   
   public static String screenshotPath;
	public static String screenshotName;
	static int i=0;
	public static void captureScreenshot() {
		i = i + 1;
		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + "_"+i+".jpg";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/reports/" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
	}

