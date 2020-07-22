package com.projectName.companyName.utilities;

import org.openqa.selenium.Capabilities;

public class DriverCapabilities {

	public static ThreadLocal<Capabilities>caps = new ThreadLocal<Capabilities>();
	
	public static Capabilities getCapabilities() {

		return caps.get();

	}

	public static void setCapabilities(Capabilities capabilities) {

		caps.set(capabilities);
	}
	
	
}
