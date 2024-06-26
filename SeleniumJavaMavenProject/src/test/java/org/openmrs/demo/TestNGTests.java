package org.openmrs.demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGTests extends TestNgBaseTest {

//	@Test(priority = 0)
	@Test(groups = { "SmokeTest", "RegressionTest" })
	public void registerPatient() {
		System.out.println("registerPatient");
	}

//	@Test(priority = 1)
//	@Test(dependsOnMethods = "registerPatient")
	@Test(groups = { "SanityTest", "RegressionTest" })
	@Parameters({ "UserName", "Password" })
	public void findPatient(String userName, String password) {
		System.out.println("findPatient");
		System.out.println(userName);
		System.out.println(password);
	}

//	@Test(priority = 2, enabled = false)
//	@Test(dependsOnMethods = "findPatient")
	@Test(groups = { "SanityTest", "RegressionTest" })
	public void activeVistsAndAddAttachments() {
		System.out.println("activeVistsAndAddAttachments");
	}

//	@Test(priority = 3)
//	@Test(dependsOnMethods = "activeVistsAndAddAttachments", invocationCount = 5)
	@Test(groups = "RegressionTest")
	public void deletePatient() {
		System.out.println("deletePatient");
	}

	@Test(dataProvider = "data1")
	public void testDataProvider1(Object[] objArr) {
		System.out.println(objArr[0]);
	}

	@Test(dataProvider = "data2")
	public void testDataProvider2(Object name, Object age, Object phoeNuber) {
		System.out.println(name);
		System.out.println(age);
		System.out.println(phoeNuber);
	}

	@Test(dataProvider = "data3")
	public void testDataProvider3(String name, String age, String phoeNuber) {
		System.out.println(name);
		System.out.println(age);
		System.out.println(phoeNuber);
	}
}
