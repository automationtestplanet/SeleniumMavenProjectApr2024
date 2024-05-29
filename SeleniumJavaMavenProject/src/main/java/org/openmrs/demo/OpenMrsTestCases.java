package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenMrsTestCases {
	public static WebDriver driver;

	public static void navigateToApplication(String url) {
		driver.get(url);
//		driver.get("https://demo.openmrs.org/openmrs/login.htm");
	}

	public static void loginToOpenMrs(String userName, String password) {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("Registration Desk")).click();
		driver.findElement(By.id("loginButton")).click();
	}

	public static void verifyLogin(String pageName) {
		if (driver.getTitle().equals(pageName)) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Current Page title: " + driver.getTitle().equals("Home"));
			System.out.println("Login failed");
		}

		if (driver.findElement(By.partialLinkText("Logout")).isDisplayed()) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login failed");
		}
	}

	public static void selectModule(String moduleName) {
		driver.findElement(By.partialLinkText(moduleName)).click();
	}

	public static void verifyModulePage(String moduleName) {
		if (driver.findElement(By.xpath("//h2[contains(text(),'" + moduleName + "')]")).isDisplayed()) {
			System.out.println("Registration Page Verifed");
		} else {
			System.out.println("Regstration Page not available");
		}
	}

	public static void enterFullName(String fullName) {

		String name[] = fullName.split(",");
		driver.findElement(By.name("givenName")).sendKeys(name[0].trim());
		driver.findElement(By.name("middleName")).sendKeys(name[1].trim());
		driver.findElement(By.name("familyName")).sendKeys(name[2].trim());
	}

	public static void clickNextButton() {
		driver.findElement(By.id("next-button")).click();
	}

	public static void selectGender(String gender) {
		Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
		genderDropdown.selectByVisibleText(gender);
//		genderDropdown.selectByValue("M");
//		genderDropdown.selectByIndex(0);
	}

	public static void enterDateOfBirth(String birthDate) {
		String[] birthDay = birthDate.split(",");
		driver.findElement(By.id("birthdateDay-field")).sendKeys(birthDay[0].trim());
		Select birthMonthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
		birthMonthDropdown.selectByVisibleText(birthDay[1].trim());
		driver.findElement(By.id("birthdateYear-field")).sendKeys(birthDay[2].trim());
	}

	public static boolean verifyRegisterDetials(String name, String gender, String dateOfBirth) {
		String actualName = driver.findElement(By.xpath("//span[contains(text(),'Name:')]//parent::p")).getText();
		String actualGender = driver.findElement(By.xpath("//span[contains(text(),'Gender:')]//parent::p")).getText();
		String actualBirthDate = driver.findElement(By.xpath("//span[contains(text(),'Birthdate:')]//parent::p"))
				.getText();

		if (actualName.contains(name) && actualGender.contains(gender) && actualBirthDate.contains(dateOfBirth)) {
			System.out.println("Register Details are correct");
			return true;
		} else {
			System.out.println("Register Details are not correct");
			return false;
		}
	}

	public static void clickConfirm() {
		driver.findElement(By.cssSelector("input[value='Confirm']")).click();
	}

	public static void clickCancel() {
		driver.findElement(By.id("cancelSubmission")).click();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//drivers//chrome124//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		navigateToApplication("https://demo.openmrs.org/openmrs/login.htm");
		loginToOpenMrs("Admin", "Admin123");
		verifyLogin("Home");
		selectModule("Register a patient");
		verifyModulePage("Register a patient");
		enterFullName("K, Ram, Babu");
		clickNextButton();
		selectGender("Male");
		clickNextButton();
		enterDateOfBirth("01, January, 1990");
		clickNextButton();

		if (verifyRegisterDetials("K, Ram, Babu", "Male", "01, January, 1990"))
			clickConfirm();
		else
			clickCancel();

	}

}
