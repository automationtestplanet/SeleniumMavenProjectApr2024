package org.openmrs.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestNgBaseTest {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.out.println("beforeSuite");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		System.out.println("beforeTest");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"UserName","Password"})
	public void beforeMethod(String userName, String password) {
		System.out.println("beforeMethod");
		System.out.println(userName);
		System.out.println(password);
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("afterClass");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("afterTest");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		System.out.println("afterSuite");
	}
}

