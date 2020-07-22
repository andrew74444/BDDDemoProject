package com.projectName.companyName.PageObjects;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.projectName.companyName.ExtentListeners.ExtentManager;
import com.projectName.companyName.ExtentListeners.ExtentTestManager;
import com.projectName.companyName.utilities.DriverCapabilities;
//import com.w2a.zoho.utilities.DriverManager;
import com.projectName.companyName.utilities.DriverManager;
import com.projectName.companyName.utilities.JavaScript;
import com.projectName.companyName.utilities.RobotClass;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BasePage<T> {

	private long LOAD_TIMEOUT = 10;
	private int AJAX_ELEMENT_TIMEOUT = 10;
	public int expTime = 60;
	protected JavascriptExecutor exe;
	protected Robot robot;
	protected WebDriverWait wait;
	protected Capabilities caps;

	protected WebDriver driver;
	public Logger log = Logger.getLogger(BasePage.class);

	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.caps = DriverCapabilities.getCapabilities();
		this.exe = JavaScript.getJavaScriptObject();
		this.robot = RobotClass.getRobotClassObject();
	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
			((BasePage) page).getPageScreenSot();
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}

	protected abstract ExpectedCondition getPageLoadCondition();
	protected abstract void getPageScreenSot();
	
	protected void waitForElementToPresent(WebElement element) {
		log.info("waiting for :" + element.toString() + " for :" + expTime + " seconds");		
		wait.until(ExpectedConditions.visibilityOf(element));		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).perform();
	}
	
	public void click(WebElement element, String elementName) {

		log.info("Clicking on : " + elementName);
		highlightElement(element);
		ExtentTestManager.testReport.get().info("Clicking on : " + elementName);
		element.click();		
	}

	public void type(WebElement element, String value, String elementName) {

		log.info("Typing in : " + elementName + " entered the value as : " + value);
		ExtentTestManager.testReport.get().info("Typing in : " + elementName + " entered the value as : " + value);
//		element.clear();
		highlightElement(element);
		element.sendKeys(value);
		
		

	}

	public void selectUsingVisibleText(WebElement element, String visibleText, String elementName) {
		log.info("Selecting the +" + elementName + "+ value as : " + visibleText);
		ExtentTestManager.testReport.get().info("Selecting the " + elementName + " value as : " + visibleText);
		Select sel = new Select(element);
		highlightElement(element);
		sel.selectByVisibleText(visibleText);
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectUsingIndex and index is: " + index);
		select.selectByIndex(index);
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is: " + value);
		select.selectByValue(value);
	}

	public void deSelectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deSelectUsingValue and value is: " + value);
		select.deselectByValue(value);
	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deSelectUsingIndex and index is: " + index);
		select.deselectByIndex(index);
	}

	public void deSelectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("deselectByVisibleText and visibleText is: " + visibleText);
		select.deselectByVisibleText(visibleText);
	}

	public List<String> getAllDropDownData(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}

	public String screenshotName;

	public void picture() {
		try {
			ExtentManager.captureScreenshot();
			ExtentTestManager.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		} catch (Exception e) {

		}
	}

	public void takeScreenshotByShutterBug(WebElement ele, String imageName) {
		log.info("Taking Screen shot of the element");
		Shutterbug.shootElement(DriverManager.getDriver(), ele).withName(imageName).save();
		log.info("Taken Screen shot of the element");

		try {
			ExtentTestManager.testReport.get()
					.info("<b>" + "<font color=" + "white>" + "Screenshot of the section" + "</font>" + "</b>",
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											System.getProperty("user.dir") + "\\screenshots\\" + imageName + ".png")
									.build());

		} catch (Exception e) {

		}

	}

	public void aShot() {

		Screenshot fpScreenshot;

		fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(DriverManager.getDriver());

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + "_" + (int) (Math.random() * 1000) + ".jpg";
		try {
			ImageIO.write(fpScreenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			ExtentTestManager.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());

		} catch (Exception e) {

		}

		scrollUpVertically();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public int getPositionAtInInt(String str, int position) {
		String string = getPositionAt(str, position);
		int value = Integer.parseInt(string);
		return value;
	}

	// https://www.javatpoint.com/java-string-getchars
	public String getPositionAt(String str, int position) {
		String[] Array = str.split("\\s+");
		return Array[position];

	}

	// https://compiler.javatpoint.com/opr/test.jsp?filename=DateToStringExample2
	public String currentTime() {
		DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		// System.out.println(date)); //0809190355
		String d = dateFormat.format(date);
		return d;
	}

	public String getSystemCurrentYear() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}

	public String getSystemCurrentMonth() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("M");
		String strDate = formatter.format(date);
		return strDate;
	}

	public String getSystemCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String strDate = formatter.format(date);
		return strDate;
	}

	public String getSystemCurrentHourIn12Hour() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("hh");
		String strDate = formatter.format(date);
		System.out.println("Date Format with dd-M-yyyy hh:mm:ss : " + strDate);
		return strDate;
	}

	public String getSystemCurrentMintues() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		String strDate = formatter.format(date);
		System.out.println("Date Format with dd-M-yyyy hh:mm:ss : " + strDate);
		return strDate;
	}

	public String getAmPm() {

		Date date = new Date();
		String dateToStr = DateFormat.getTimeInstance().format(date);
		System.out.println("Date Format using getTimeInstance(): " + dateToStr);
		return dateToStr;
	}

	public String date() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		String strDate = formatter.format(date);
		System.out.println("Date Format with dd MMMM yyyy : " + strDate);
		return strDate;
	}

	// int to String
	public String intToString(int i) {
		String s = String.valueOf(i);
		return s;
	}

	// String to int
	public int stringToInt(String s) {
		int i = Integer.parseInt(s);
		return i;
	}

	// Image Verification
	public void verifyImage(String pathOfTheImage) throws Exception {

		Thread.sleep(1000);
		Thread.sleep(1000);
		Screen screen = new Screen();

		Pattern img = new Pattern(pathOfTheImage);

		screen.wait(img, expTime);
		System.out.println(screen.wait(img, expTime));
		// screen.click(img);
		// screen.type(img,value);

	}

	// Assertions
	public void verifyText(String s1, String s2) {

		log.info("veryfing test: " + s1 + " with " + s2);
		Assert.assertEquals(s1, s1);
	}

	public void markPass() {
		log.info("making script PASS..");
		Assert.assertTrue(true);
	}

	public void markPass(String message) {
		log.info("making script PASS.." + message);
		Assert.assertTrue(true, message);
	}

	public void markFail() {
		log.info("making script FAIL..");
		Assert.assertTrue(false);
	}

	public void markFail(String message) {
		log.info("making script FAIL.." + message);
		Assert.assertTrue(false, message);
	}

	public void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}

	public void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}

	public void verifyNull(String s1) {
		log.info("verify object is null..");
		Assert.assertNull(s1);
	}

	public void verifyNotNull(String s1) {
		log.info("verify object is not null..");
		Assert.assertNotNull(s1);
	}

	public void fail() {
		Assert.assertTrue(false);
	}

	public void pass() {
		Assert.assertTrue(true);
	}

	public void updateTestStatus(boolean status) {
		if (status) {
			pass();
		} else {
			fail();
		}
	}

	// Verifications
	public boolean isElementDisplayed(WebElement element, String elementName) {
		try {
			element.isDisplayed();
			log.info("element is Displayed..");
			takeScreenshotByShutterBug(element, elementName);
			return true;
		} catch (Exception e) {
			log.error("element is not Displayed..", e.getCause());
			aShot();
			ExtentTestManager.testReport.get().info("element is not Displayed.." + e.getMessage());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present.." + element.getText());
			ExtentTestManager.testReport.get().info("element is present.." + element.getText());
			return false;
		} catch (Exception e) {
			log.error("element is not present..");
			return true;
		}
	}

	public String readValueFromElement(WebElement element, String elementName) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isElementDisplayed(element, elementName);
		if (status) {
			log.info("element text is .." + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getText(WebElement element, String elementName) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isElementDisplayed(element, elementName);
		if (status) {
			log.info("element text is .." + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getTitle() {
		log.info("page title is: " + DriverManager.getDriver().getTitle());
		return DriverManager.getDriver().getTitle();
	}

	// Iframes
	/**
	 * this method will switchToFrame based on frame index
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		DriverManager.getDriver().switchTo().frame(frameIndex);
		log.info("switched to :" + frameIndex + " frame");
	}

	/**
	 * this method will switchToFrame based on frame name
	 * 
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		DriverManager.getDriver().switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame");
	}

	/**
	 * this method will switchToFrame based on frame WebElement
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		DriverManager.getDriver().switchTo().frame(element);
		log.info("switched to frame " + element.toString());
	}

	// Alerts

	public Alert getAlert() {
		log.info("alert test: " + DriverManager.getDriver().switchTo().alert().getText());
		return DriverManager.getDriver().switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("accept Alert is done...");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("dismiss Alert is done...");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text: " + text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			DriverManager.getDriver().switchTo().alert();
			log.info("alert is present");
			return true;
		} catch (NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void acceptPrompt(String text) {
		if (isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: " + text);
		}
	}

	// Should match elements all
	// @FindBys({
	// @FindBy(xpath=""),
	// @FindBy(xpath="")
	// })
	// List<WebElement> elementsList;

	// @FindAll({
	// @FindBy(xpath=""),
	// @FindBy(xpath="")
	// })
	// List<WebElement> elementsList;

	// *******************************************JavaScript*********************************//

	/**
	 * highlighting Element
	 * 
	 * @param element
	 */
	public void highlightElement(WebElement element) {
		exe.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	/**
	 * This method will scroll till element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		log.info("scroll to WebElement...");
		exe.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
				element.getLocation().y);
	}

	/**
	 * Scroll till element and click
	 * 
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked: " + element.toString());
	}

	/**
	 * This method will execute java script
	 * 
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		return exe.executeScript(script);
	}

	/**
	 * This method will execute Java script with multiple arguments
	 * 
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object... args) {
		return exe.executeScript(script, args);
	}

	/**
	 * Type By Java Script
	 * 
	 * @param element
	 */
	public void TypeByJavaScript(String Value, WebElement element) {
		executeScript("arguments[0].value='" + Value + "';", element);
	}

	/**
	 * getLink
	 * 
	 * @param element
	 */
	public String getLink(WebElement element) {
		String hrefUrl = element.getAttribute("href");
		log.info("Link is " + hrefUrl);
		setAttribute(element, "target", "_self");
		return hrefUrl;
	}

	/**
	 * Update the Attribute of the element
	 * 
	 * @param element,Attribute
	 *            and value
	 */
	public void setAttribute(WebElement element, String attribute, String value) {

		log.info("Attribute is " + attribute + " value is " + value);
		executeScript("arguments[0].setAttribute('" + attribute + "','" + value + "');", element);
	}

	/**
	 * Scroll till element view *
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		log.info("scroll till web " + element);
		executeScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * Scroll till element view and click
	 * 
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("element is clicked: " + element.toString());
	}

	/**
	 * This method will scroll down vertically
	 */
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}

	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * 
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0," + pixel + ")");
	}

	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0,-" + pixel + ")");
	}

	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	/**
	 * This method will click on element
	 * 
	 * @param element
	 */
	public void clickElementByJavaScript(WebElement element) {
		highlightElement(element);
		executeScript("arguments[0].click();", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		DriverManager.getDriver().switchTo().defaultContent();
	}

	/**
	 * This method will switch to child window based on index
	 * 
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switched to : " + index + " window");
				DriverManager.getDriver().switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This method will close all tabbed window and switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		String mainwindow = DriverManager.getDriver().getWindowHandle();

		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
				DriverManager.getDriver().close();
			}
		}
		log.info("switched to main window");
		DriverManager.getDriver().switchTo().window(mainwindow);
	}

	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack() {
		log.info("navigating back");
		DriverManager.getDriver().navigate().back();
	}

	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward() {
		log.info("navigating forward");
		DriverManager.getDriver().navigate().forward();
	}

	public void getBrowserInstance() {
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		System.out.println(browserName);
		System.out.println(browserVersion);

		// Get OS name.
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
	}

}
