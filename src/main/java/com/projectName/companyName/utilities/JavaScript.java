package com.projectName.companyName.utilities;

import org.openqa.selenium.JavascriptExecutor;

public class JavaScript {

	public static ThreadLocal<JavascriptExecutor> dr = new ThreadLocal<JavascriptExecutor>();

	public static JavascriptExecutor getJavaScriptObject() {

		return dr.get();

	}

	public static void setJavaScriptObject(JavascriptExecutor js) {

		dr.set(js);
	}

}
