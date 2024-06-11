package org.openmrs.demo;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class OpenMrsTestNgBaseTest {

	WebDriver driver;
	Commons commons;
	LoginPage loginPage;
	HomePage homePage;
	RegistrationPage registrationPage;
	PatientDetailsPage patientDetailsPage;
	FindPatientRecordPage findPatientRecordPage;
	JavascriptExecutor jsExec;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + Utils.appProperties.getProperty("chrome.driver"));
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
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
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		commons.navigateToApplication(Utils.appProperties.getProperty("url"));
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "UserName", "Password" })
	public void beforeMethod(String userName, String password) {
		loginPage.loginToOpenMrs(userName, password, "Registration Desk");
		Assertions.assertEquals(true, commons.verifyPage("Home") && homePage.verifyLogoutButton());
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception {
		Thread.sleep(5000);
		homePage.clickLogoutButton();
		Thread.sleep(10000);
		Assertions.assertEquals(true, commons.verifyPage("Login"));
	}

	@AfterClass()
	public void afterClass() {
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.quit();
	}

	@AfterSuite()
	public void afterSuite() {
	}

}
