package org.openmrs.demo;

import java.util.concurrent.TimeUnit;

import org.openmrs.pageobjects.Commons;
import org.openmrs.pageobjects.HomePage;
import org.openmrs.pageobjects.LoginPage;
import org.openmrs.pageobjects.PatientDetailsPage;
import org.openmrs.pageobjects.RegistrationPage;
import org.openmrs.pageobjects.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsTestCases {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + Utils.appProperties.getProperty("chrome.driver"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		Commons commons = new Commons(driver);
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
		PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver);
		commons.navigateToApplication(Utils.appProperties.getProperty("url"));
		loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
				Utils.appProperties.getProperty("password"), "Registration Desk");
		if (commons.verifyPage("Home") && homePage.verifyLogoutButton()) {
			System.out.println("Login Successfull");
			homePage.selectModule("Register a patient");
			if (commons.verifyModulePage("Register a patient")) {
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
				if (registrationPage.verifyRegisterDetials("K, Ram, Babu", "Male", "01, January, 1990")) {
					registrationPage.clickConfrim();
					if (patientDetailsPage.verifyPatientName("K, Ram, Babu")) {
						String patientId = patientDetailsPage.getPatientIdValue();
						System.out.println("Patient Id: "+ patientId);
						Utils.setProperty("patient.id", patientId);
					}
				} else
					registrationPage.clickCancel();
			} else {
				System.out.println("Registraion Page is not present");
			}
		} else {
			System.out.println("Login Failed");
		}
	}
}
