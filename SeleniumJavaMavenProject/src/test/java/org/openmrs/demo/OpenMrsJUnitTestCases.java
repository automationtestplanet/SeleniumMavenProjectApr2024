package org.openmrs.demo;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openmrs.pageobjects.Commons;
import org.openmrs.pageobjects.FindPatientRecordPage;
import org.openmrs.pageobjects.HomePage;
import org.openmrs.pageobjects.LoginPage;
import org.openmrs.pageobjects.PatientDetailsPage;
import org.openmrs.pageobjects.RegistrationPage;
import org.openmrs.pageobjects.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class OpenMrsJUnitTestCases extends JUnitTestSuit{

	WebDriver driver;
	Commons commons;
	LoginPage loginPage;
	HomePage homePage;
	RegistrationPage registrationPage;
	PatientDetailsPage patientDetailsPage;
	FindPatientRecordPage findPatientRecordPage;
	JavascriptExecutor jsExec;

	@BeforeAll
	public void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + Utils.appProperties.getProperty("chrome.driver"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		commons = new Commons(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
		patientDetailsPage = new PatientDetailsPage(driver);
		findPatientRecordPage = new FindPatientRecordPage(driver);
		jsExec = (JavascriptExecutor) driver;

		commons.navigateToApplication(Utils.appProperties.getProperty("url"));
	}

	@BeforeEach
	public void setUp() throws Exception {
		loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
				Utils.appProperties.getProperty("password"), "Registration Desk");
		Assertions.assertEquals(true, commons.verifyPage("Home") && homePage.verifyLogoutButton());
	}

	@Test
	@Order(1)
	public void registerPatientTest() {
		homePage.selectModule("Register a patient");
		Assertions.assertTrue(commons.verifyModulePage("Register a patient"));
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
		Assertions.assertTrue(registrationPage.verifyRegisterDetials("K, Ram, Babu", "Male", "01, January, 1990"));
		registrationPage.clickConfrim();
		Assertions.assertTrue(patientDetailsPage.verifyPatientName("K, Ram, Babu"));
		String patientId = patientDetailsPage.getPatientIdValue();
		System.out.println("Patient Id: " + patientId);
		Utils.setProperty("patient.id", patientId);
	}

	@Test
	@Order(2)
	public void findPatientTest() {
		homePage.selectModule("Find Patient Record");
		Assertions.assertTrue(commons.verifyModulePage("Find Patient Record"));
		findPatientRecordPage.setPatientIdInPatientSearchFiled(Utils.appProperties.getProperty("patient.id"));
		Assertions.assertTrue(findPatientRecordPage.verifyPatientSearchResult("Identifier",
				Utils.appProperties.getProperty("patient.id")));
		findPatientRecordPage.openPatientRecord("Identifier");
		Assertions.assertTrue(
				patientDetailsPage.getPatientIdValue().equals(Utils.appProperties.getProperty("patient.id")));
		System.out.println("Find Patient is passed");

	}

	@Test
	@Order(3)
	@Disabled
	public void activeVisitsAndAddAttachementsTest() {
		findPatientTest();
		patientDetailsPage.clickStartVisit();
		patientDetailsPage.clickStartVisitConfirm();
		patientDetailsPage.clickAttachments();
		String filePath = Utils.appProperties.getProperty("file.path");
		patientDetailsPage.uploadFile(filePath, "File1");
		Assertions.assertTrue(patientDetailsPage.verifyUploadedFile());
		System.out.println("File Upload is passed");		
	}

	@Test
	@Order(4)
	@Disabled
	public void deletePatientTest() {
		findPatientTest();
		patientDetailsPage.clickDeletePatient();
		patientDetailsPage.setDeleteReason("Other");
		patientDetailsPage.clickDeleteConfirm();
		findPatientRecordPage.setPatientIdInPatientSearchFiled(Utils.appProperties.getProperty("patient.id"));
		Assertions.assertTrue(findPatientRecordPage.verifyDeletePatient());
		System.out.println("Delete ptient is passed");
	}

	@AfterEach
	public void tearDown() throws Exception {
		homePage.clickLogoutButton();
		Thread.sleep(10000);
		Assertions.assertEquals(true, commons.verifyPage("Login"));
	}

	@AfterAll
	public void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
