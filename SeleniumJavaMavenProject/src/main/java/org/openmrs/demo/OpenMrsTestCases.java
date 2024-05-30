package org.openmrs.demo;

import org.openmrs.pageobjects.Commons;
import org.openmrs.pageobjects.HomePage;
import org.openmrs.pageobjects.LoginPage;
import org.openmrs.pageobjects.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsTestCases {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//drivers//chrome124//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Commons commons = new Commons(driver);
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
		driver.manage().window().maximize();
		
		commons.navigateToApplication("https://demo.openmrs.org/openmrs/login.htm");
		loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
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
				if (registrationPage.verifyRegisterDetials("K, Ram, Babu", "Male", "01, January, 1990"))
					registrationPage.clickConfrim();
				else
					registrationPage.clickCancel();
			} else {
				System.out.println("Registraion Page is not present");
			}
		} else {
			System.out.println("Login Failed");
		}
	}
}
