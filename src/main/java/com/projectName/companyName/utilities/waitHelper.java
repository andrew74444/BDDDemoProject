package com.projectName.companyName.utilities;

import org.openqa.selenium.support.ui.WebDriverWait;

public class waitHelper {

	public static ThreadLocal<WebDriverWait> dr = new ThreadLocal<WebDriverWait>();

	public static WebDriverWait getWebDriverWaitObject() {

		return dr.get();

	}

	public static void setWebDriverWaitObject(WebDriverWait wait) {

		dr.set(wait);
	}

}
