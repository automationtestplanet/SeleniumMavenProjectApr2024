package org.openmrs.demo;

import java.util.Map;

import org.openmrs.pageobjects.Utils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OpenMrsKeywordAndDataDrivenTestCases extends OpenMrsKeywordAndDataDrivenBaseTest {

	@Test(dataProvider = "TestDataProvider")
	public void registerPatientTest(Map<String,String> testData) {
		homePage.selectModule("Register a patient");
		Assert.assertTrue(commons.verifyModulePage("Register a patient"));
		registrationPage.enterFullName(testData.get("Name"));
		registrationPage.clickNextButton();
		registrationPage.selectGender(testData.get("Gender"));
		registrationPage.clickNextButton();
		registrationPage.enterDateOfBirth(testData.get("DateOfBirth"));
		registrationPage.clickNextButton();
		registrationPage.enterAddress(testData.get("Address"));
		registrationPage.clickNextButton();
		registrationPage.setPhoneNumber(testData.get("PhoneNumber"));
		registrationPage.clickNextButton();
		registrationPage.clickNextButton();
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");		
		Assert.assertTrue(registrationPage.verifyRegisterDetials(testData.get("Name"), testData.get("Gender"), testData.get("DateOfBirth")));
		registrationPage.clickConfrim();
		Assert.assertTrue(patientDetailsPage.verifyPatientName(testData.get("Name")));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		String patientId = patientDetailsPage.getPatientIdValue();
		Utils.setProperty("patient.id", patientId);
	}

	@Test(dataProvider = "TestDataProvider")
	public void findPatientTest(Map<String,String> testData) {
		homePage.selectModule("Find Patient Record");
		Assert.assertTrue(commons.verifyModulePage("Find Patient Record"));
		findPatientRecordPage.setPatientIdInPatientSearchFiled(testData.get("Name"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(findPatientRecordPage.verifyPatientSearchResult("Name",
				testData.get("Name")));
		findPatientRecordPage.openPatientRecord("Name");
		Assert.assertTrue(
				patientDetailsPage.getPatientIdValue().length() > 0);
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		System.out.println("Find Patient is passed");

	}

	@Test(dataProvider = "TestDataProvider")
	public void activeVisitsAndAddAttachementsTest(Map<String,String> testData) {
		findPatientTest(testData);
		patientDetailsPage.clickStartVisit();
		patientDetailsPage.clickStartVisitConfirm();
		patientDetailsPage.clickAttachments();
		String filePath = System.getProperty("user.dir") + Utils.appProperties.getProperty("file.path")+testData.get("FileName");
		patientDetailsPage.uploadFile(filePath, testData.get("Caption"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(patientDetailsPage.verifyUploadedFile());
		System.out.println("File Upload is passed");		
	}

	@Test(dataProvider = "TestDataProvider")
	public void deletePatientTest(Map<String,String> testData) {
		findPatientTest(testData);
		patientDetailsPage.clickDeletePatient();
		patientDetailsPage.setDeleteReason(testData.get("Reason"));
		patientDetailsPage.clickDeleteConfirm();
		findPatientRecordPage.setPatientIdInPatientSearchFiled(testData.get("Name"));
		commons.captureScreenshot();
		Reporter.log("<img src=\""+commons.screenshotPath+"\" />");
		Assert.assertTrue(findPatientRecordPage.verifyDeletePatient());
		System.out.println("Delete ptient is passed");
	}
	
	@Test(dataProvider = "TestDataProvider")
	public void getTestData(Map<String,String> testData) {
		System.out.println(testData);
	}
}
