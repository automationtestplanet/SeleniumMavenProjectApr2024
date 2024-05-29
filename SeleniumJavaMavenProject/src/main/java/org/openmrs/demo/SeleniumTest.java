package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//drivers//chrome124//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://demo.openmrs.org/openmrs/login.htm");
//		driver.navigate().to("https://demo.openmrs.org/openmrs/login.htm");
		
//		By userNameId = By.id("username");		
//		WebElement userNameElement = driver.findElement(userNameId);
//		userNameElement.sendKeys("Admin");
		
		
//		By passwordName = By.name("password");		
//		WebElement passwordElement = driver.findElement(passwordName);
//		passwordElement.sendKeys("Admin123");
		
		
//		By regsitrationDeskId = By.id("Registration Desk");		
//		WebElement registrationDeskElement = driver.findElement(regsitrationDeskId);
//		registrationDeskElement.click();
		
		
//		By loginButtonId = By.id("loginButton");		
//		WebElement loginButtonElement = driver.findElement(loginButtonId);
//		loginButtonElement.click();		
		
		
//		String title = driver.getTitle();		
//		if(title.equals("Home")) {
//			System.out.println("Login Successful");
//		}else {
//			System.out.println("Current Page title: "+ title);
//			System.out.println("Login failed");
//		}
//		
//		By logoutLinkText = By.partialLinkText("Logout");
//		WebElement logoutElement = driver.findElement(logoutLinkText);		
//		if(logoutElement.isDisplayed()) {
//			System.out.println("Login Successful");
//		}else {
//			System.out.println("Login failed");
//		}
		
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("Admin123");
		driver.findElement(By.id("Registration Desk")).click();
		driver.findElement(By.id("loginButton")).click();
		
		if(driver.getTitle().equals("Home")) {
			System.out.println("Login Successful");
		}else {
			System.out.println("Current Page title: "+ driver.getTitle().equals("Home"));
			System.out.println("Login failed");
		}
		
		if(driver.findElement(By.partialLinkText("Logout")).isDisplayed()) {
			System.out.println("Login Successful");
		}else {
			System.out.println("Login failed");
		}

//		driver.close();
//		driver.quit();

	}

}
