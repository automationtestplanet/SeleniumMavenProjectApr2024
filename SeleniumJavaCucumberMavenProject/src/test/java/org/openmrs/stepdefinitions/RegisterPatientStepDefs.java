package org.openmrs.stepdefinitions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
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

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterPatientStepDefs {

	WebDriver driver;
	Commons commons;
	LoginPage loginPage;
	HomePage homePage;
	RegistrationPage registrationPage;
	PatientDetailsPage patientDetailsPage;
	FindPatientRecordPage findPatientRecordPage;
	JavascriptExecutor jsExec;

	@Before(order = 1)
	public void beforeHook() {
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

		loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
				Utils.appProperties.getProperty("password"), "Registration Desk");
	}

	@After
	public void afterHook() throws InterruptedException {
		Thread.sleep(5000);
		homePage.clickLogoutButton();
		Thread.sleep(10000);
		Assertions.assertEquals(true, commons.verifyPage("Login"));
		driver.close();
	}

//	@Before("@RegisterSinglePatientDetails")
//	public void beforeHookForRegisterSinglePatientDetails() {
//		System.out.println("This Before hook for @RegisterSinglePatientDetails scenario");
//	}
//
//	@After("@RegisterSinglePatientDetails")
//	public void afterHookForRegisterSinglePatientDetails() {
//		System.out.println("This After hook for @RegisterSinglePatientDetails scenario");
//	}
//
//	@BeforeStep("@RegisterSinglePatientDetails")
//	public void beforeStepHookForRegisterSinglePatientDetails() {
//		System.out.println("This is Before Step Hook for @RegisterSinglePatientDetails scenario");
//	}
//
//	@AfterStep("@RegisterSinglePatientDetails")
//	public void afterStepHookForRegisterSinglePatientDetails() {
//		System.out.println("This is After Step Hook for @RegisterSinglePatientDetails scenario");
//	}
//
//	@BeforeStep
//	public void beforeStepHook() {
//		System.out.println("This is Before Step Hook");
//	}
//
//	@AfterStep
//	public void afterStepHook() {
//		System.out.println("This is After Step Hook");
//
//	}

	@Given("User is on the Home Page")
	public void userIsOnTheHomePage() {
		Assertions.assertEquals(true, commons.verifyPage("Home") && homePage.verifyLogoutButton());
	}

	@When("User selects the Module {string}")
	public void userSelectsTheModule(String moduleName) {
		homePage.selectModule(moduleName);
	}

	@Then("Register a Patient page should be displayed")
	public void registerAPatientPageShouldBeDisplayed() {
		Assertions.assertTrue(commons.verifyModulePage("Register a patient"));
	}

	@When("User enters name {string} and clicks next button")
	public void userEntersNameAndClicksNextButton(String name) {
		registrationPage.enterFullName(name);
		registrationPage.clickNextButton();
	}

	@When("User selects gender as {string} and clicks next button")
	public void userSelectsGenderAsAndClicksNextButton(String gender) {
		registrationPage.selectGender(gender);
		registrationPage.clickNextButton();
	}

	@When("User enters  date of birth as {string} and clicks next button")
	public void userEntersDateOfBirthAsAndClicksNextButton(String dateOfBirth) {
		registrationPage.enterDateOfBirth(dateOfBirth);
		registrationPage.clickNextButton();
	}

	@When("user enters address as {string} and clicks next button")
	public void userEntersAddressAsAndClicksNextButton(String address) {
		registrationPage.enterAddress(address);
		registrationPage.clickNextButton();
	}

	@When("user enters phone number as {string} and clicks next button")
	public void userEntersPhoneNumberAsAndClicksNextButton(String phoneNumber) {
		registrationPage.setPhoneNumber(phoneNumber);
		registrationPage.clickNextButton();
	}

	@When("user clicks next button")
	public void userClicksNextButton() {
		registrationPage.clickNextButton();
	}

	@Then("user should able to verify entered details")
	public void userShouldAbleToVerifyEnteredDetails1() {

	}

	@Then("user should able to verify entered details {string} {string} {string}")
	public void userShouldAbleToVerifyEnteredDetails(String name, String gender, String dateOfBirth) {
		commons.captureScreenshot();
		Assertions.assertTrue(registrationPage.verifyRegisterDetials(name, gender, dateOfBirth));
	}

	@When("user clicks confirm button")
	public void userClicksConfirmButton() {
		registrationPage.clickConfrim();
	}

	@Then("user should be registered and patient Id must be genrated")
	public void userShouldBeRegisteredAndPatientIdMustBeGenrated1() {
	}

	@Then("user should be registered with name {string} and patient Id must be genrated")
	public void userShouldBeRegisteredAndPatientIdMustBeGenrated(String name) {
		Assertions.assertTrue(patientDetailsPage.verifyPatientName(name));
		commons.captureScreenshot();
		String patientId = patientDetailsPage.getPatientIdValue();
		System.out.println(patientId);
		Utils.setProperty("patient.id", patientId);
	}

	@When("User enters patient details1")
	public void userEntersPatientDetails1(DataTable dataTable) {
		List<Map<String, String>> testData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> eachRowData : testData) {
			System.out.println(eachRowData.get("Name"));
			System.out.println(eachRowData.get("Gender"));
			System.out.println(eachRowData.get("DateOfBirth"));
			System.out.println(eachRowData.get("Address"));
			System.out.println(eachRowData.get("PhoneNumber"));
		}
	}

	@When("User enters patient details")
	public void userEntersPatientDetails(List<PatientDetails> patientDetails) {
		System.out.println("DataTableType Annnotation data");
		for (PatientDetails eachPatientDetails : patientDetails) {
			System.out.println(eachPatientDetails.name);
			System.out.println(eachPatientDetails.gender);
			System.out.println(eachPatientDetails.dateOfBirth);
			System.out.println(eachPatientDetails.address);
			System.out.println(eachPatientDetails.phoneNumber);
		}
	}

	@DataTableType()
	public PatientDetails patientDetails(Map<String, String> testData) {
		return new PatientDetails(testData.get("Name"), testData.get("Gender"), testData.get("DateOfBirth"),
				testData.get("Address"), testData.get("PhoneNumber"));
	}

	class PatientDetails {
		String name;
		String gender;
		String dateOfBirth;
		String address;
		String phoneNumber;

		public PatientDetails(String name, String gender, String dateOfBirth, String address, String phoneNumber) {
			this.name = name;
			this.gender = gender;
			this.dateOfBirth = dateOfBirth;
			this.address = address;
			this.phoneNumber = phoneNumber;
		}

	}

	@When("User enters patient details as {patientDetailsData}")
	public void userEntersPatientDetailsAs(List<String> patientDetails) {
		for (String eachString : patientDetails) {
			System.out.println(eachString);
		}
	}

	@ParameterType("(.*)")
	public List<String> patientDetailsData(String data) {
		return Arrays.asList(data.split(","));
	}
}
