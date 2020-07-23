package com.projectName.cucumber.steps;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.projectName.companyName.utilities.DriverCapabilities;
import com.projectName.companyName.utilities.DriverFactory;
import com.projectName.companyName.utilities.DriverManager;
import com.projectName.companyName.utilities.JavaScript;
import com.projectName.companyName.utilities.RobotClass;
import com.projectName.companyName.utilities.waitHelper;
import com.projectName.companyName.ExtentListeners.ExtentTestManager;
import com.projectName.companyName.PageObjects.ZohoCRMPage;
import com.projectName.companyName.PageObjects.ZohoContactSalesPage;
import com.projectName.companyName.PageObjects.ZohoHomePage;

public class BaseSteps {
	
	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseSteps.class);
	public boolean grid=false;
	private String defaultUserName;
	private String defaultPassword;
	
	public ZohoHomePage home;
	public ZohoContactSalesPage zohoContactSalesPage;	
	public ZohoCRMPage zohoCRMPage;
	

	public String getDefaultUserName() {
		return defaultUserName;
	}




	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}




	public String getDefaultPassword() {
		return defaultPassword;
	}




	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}




	public void setUpFramework() {

		configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
	
		
        if (System.getProperty("os.name").equalsIgnoreCase("mac")) {
        	
        	DriverFactory.setChromeDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
    		DriverFactory.setGeckoDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
    	
        }else {
		
		
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		/*DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
*/
        }
		
        

	}
	

	
	
	public void logInfo(String message) {
		
		ExtentTestManager.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
				+ "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void destroyFramework() {

	}

	public void openBrowser(String browser) {
		
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
//			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equals("Grid")) {
			
			grid=true;
		}
		

		DriverFactory.setRemote(grid);
		Capabilities caps;
		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else

		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver",
					DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver",
					DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}	
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		JavaScript.setJavaScriptObject(js);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		waitHelper.setWebDriverWaitObject(wait);
		try {
			Robot robot = new Robot();
			RobotClass.setRobotClassObject(robot);	
		} catch (AWTException e) {

			e.printStackTrace();
		}
		
		
		caps = ((RemoteWebDriver) driver).getCapabilities();
		DriverCapabilities.setCapabilities(caps);		
		
		
		
		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setDefaultUserName(Config.getProperty("defaultUserName"));
		setDefaultPassword(Config.getProperty("defaultPassword"));
	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}
	
	public String date() {
	Date date = new Date();  
    SimpleDateFormat formatter=new SimpleDateFormat("dd MMMM yyyy");  
    String strDate = formatter.format(date);    
    System.out.println("Date Format with dd MMMM yyyy : "+strDate);
    return strDate;
}
	
	
	

}
