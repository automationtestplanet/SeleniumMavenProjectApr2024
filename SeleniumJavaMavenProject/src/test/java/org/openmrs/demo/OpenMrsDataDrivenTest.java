package org.openmrs.demo;

import org.openmrs.pageobjects.Utils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OpenMrsDataDrivenTest extends OpenMrsDataDrivenBaseTest{

	@Test(dataProvider = "RegisterPatientData")
	public void registerPatientTest(String name, String gender, String dateOfBirth, String address, String phneNumber) {
		homePage.selectModule("Register a patient");
		Assert.assertTrue(commons.verifyModulePage("Register a patient"));
		registrationPage.enterFullName(name);
		registrationPage.clickNextButton();
		registrationPage.selectGender(gender);
		registrationPage.clickNextButton();
		registrationPage.enterDateOfBirth(dateOfBirth);
		registrationPage.clickNextButton();
		registrationPage.enterAddress(address);
		registrationPage.clickNextButton();
		registrationPage.setPhoneNumber(phneNumber);
		registrationPage.clickNextButton();
		registrationPage.clickNextButton();
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");		
		Assert.assertTrue(registrationPage.verifyRegisterDetials(name, gender, dateOfBirth));
		registrationPage.clickConfrim();
		Assert.assertTrue(patientDetailsPage.verifyPatientName(name));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		String patientId = patientDetailsPage.getPatientIdValue();
		Utils.setProperty("patient.id", patientId);
	}
	

	@Test(dataProvider = "RegisterPatientData")
	public void registerPatientTestData(String name, String gender, String dateOfBirth, String address, String phneNumber) {		
		System.out.print(name+" ");
		System.out.print(gender+" ");
		System.out.print(dateOfBirth+" ");
		System.out.print(address+" ");
		System.out.print(phneNumber+" ");
	}
}
