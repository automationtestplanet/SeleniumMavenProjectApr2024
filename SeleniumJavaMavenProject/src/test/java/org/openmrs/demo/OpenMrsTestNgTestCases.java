package org.openmrs.demo;

import org.openmrs.pageobjects.Utils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OpenMrsTestNgTestCases extends OpenMrsTestNgBaseTest{
	
	@Test(groups = {"SmokeTest","RegressionTest"})
	public void registerPatientTest() {
		homePage.selectModule("Register a patient");
		Assert.assertTrue(commons.verifyModulePage("Register a patient"));
		System.out.println("Registraion Page is present");
		registrationPage.enterFullName("K, Ram, Babu");
		registrationPage.clickNextButton();
		registrationPage.selectGender("Male");
		registrationPage.clickNextButton();
		registrationPage.enterDateOfBirth("01, January, 1990");
		registrationPage.clickNextButton();
		registrationPage.enterAddress("Flat No:101, Ameerpet,Hyderabad,Telangana,India,500038");
		registrationPage.clickNextButton();
		registrationPage.setPhoneNumber("9876543210");
		registrationPage.clickNextButton();
		registrationPage.clickNextButton();
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");		
		Assert.assertTrue(registrationPage.verifyRegisterDetials("K, Ram, Babu", "Male", "01, January, 1990"));
		registrationPage.clickConfrim();
		Assert.assertTrue(patientDetailsPage.verifyPatientName("K, Ram, Babu"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		String patientId = patientDetailsPage.getPatientIdValue();
		System.out.println("Patient Id: " + patientId);
		Utils.setProperty("patient.id", patientId);
	}

	@Test(groups = {"SanityTest","RegressionTest"})
	public void findPatientTest() {
		homePage.selectModule("Find Patient Record");
		Assert.assertTrue(commons.verifyModulePage("Find Patient Record"));
		findPatientRecordPage.setPatientIdInPatientSearchFiled(Utils.appProperties.getProperty("patient.id"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(findPatientRecordPage.verifyPatientSearchResult("Identifier",
				Utils.appProperties.getProperty("patient.id")));
		findPatientRecordPage.openPatientRecord("Identifier");
		Assert.assertTrue(
				patientDetailsPage.getPatientIdValue().equals(Utils.appProperties.getProperty("patient.id")));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		System.out.println("Find Patient is passed");

	}

	@Test(groups = {"Sanity","RegressionTest"})
	public void activeVisitsAndAddAttachementsTest() {
		findPatientTest();
		patientDetailsPage.clickStartVisit();
		patientDetailsPage.clickStartVisitConfirm();
		patientDetailsPage.clickAttachments();
		String filePath = Utils.appProperties.getProperty("file.path");
		patientDetailsPage.uploadFile(filePath, "File1");
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(patientDetailsPage.verifyUploadedFile());
		System.out.println("File Upload is passed");		
	}

	@Test(groups = "RegressionTest")
	public void deletePatientTest() {
		findPatientTest();
		patientDetailsPage.clickDeletePatient();
		patientDetailsPage.setDeleteReason("Other");
		patientDetailsPage.clickDeleteConfirm();
		findPatientRecordPage.setPatientIdInPatientSearchFiled(Utils.appProperties.getProperty("patient.id"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(findPatientRecordPage.verifyDeletePatient());
		System.out.println("Delete ptient is passed");
	}
}
