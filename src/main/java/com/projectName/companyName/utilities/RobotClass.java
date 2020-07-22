package com.projectName.companyName.utilities;

import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;

public class RobotClass {

	public static ThreadLocal<Robot> dr = new ThreadLocal<Robot>();

	public static Robot getRobotClassObject() {

		return dr.get();

	}

	public static void setRobotClassObject(Robot robot) {

		dr.set(robot);
	}

}
