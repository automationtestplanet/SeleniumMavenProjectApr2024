package org.openmrs.demo;

import java.util.Set;

import java.util.Set;

import org.openmrs.pageobjects.Commons;
import org.openmrs.pageobjects.HomePage;
import org.openmrs.pageobjects.LoginPage;
import org.openmrs.pageobjects.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePageTest {

	public static void main(String[] args) throws Exception {
		 
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//drivers//chrome124//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(System.getProperty("user.dir") + "//src//main//resources//html//Welcome.html");
		
//		driver.findElement(By.id("userName")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("[placeholder='Enter User Name']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[placeholder='Enter User Name']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[id='userName'][placeholder='Enter User Name']")).sendKeys("Test1234");		
//		driver.findElement(By.cssSelector("#userName")).sendKeys("Test1234");
	
		
//		driver.findElement(By.name("Password")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("[name='Password']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[name='Password']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[name='Password'][type='password']")).sendKeys("Test1234");
		
		
		
//		String str = "\"\"";
		
		
		
//		driver.findElement(By.tagName("input")).sendKeys("1234Test");
		
		
		
		JavascriptExecutor jsExec = (JavascriptExecutor)driver;
		
		jsExec.executeScript("window.alert('Welcome to Automation Tedt Planet')");
		Thread.sleep(5000);
		
//		Alert alert1 = driver.switchTo().alert();
//		alert1.accept();
//		alert1.dismiss();
//		driver.switchTo().alert().dismiss();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		alert1.dismiss();
		
		
//		Switch to different Frame 
		
		driver.switchTo().frame("frame1");  // id/name/any iFrame WebElement
		
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		Commons commons = new Commons(driver);
		
		loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
				Utils.appProperties.getProperty("password"), "Registration Desk");
		Thread.sleep(5000);	
		homePage.clickLogoutMenu();
		Thread.sleep(2000);	
		commons.captureScreenshot();
		homePage.clickLogoutButton();
		Thread.sleep(5000);		
		driver.switchTo().defaultContent();
		
		
		
//		Switch to Different Tab
		driver.findElement(By.linkText("OpenMRS in New Tab")).click();
		
		Thread.sleep(5000);
		
		String defaultWinId = driver.getWindowHandle();
		
		Set<String> allWinIds = driver.getWindowHandles();
		
		for(String eachWinddowId : allWinIds) {
			if(!eachWinddowId.equals(defaultWinId)) {
				driver.switchTo().window(eachWinddowId);
				loginPage = new LoginPage(driver);
				homePage = new HomePage(driver);
				commons.captureScreenshot();
				loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
						Utils.appProperties.getProperty("password"), "Registration Desk");
				homePage.clickLogoutButton();
				Thread.sleep(5000);
				driver.close();
				driver.switchTo().window(defaultWinId);
				break;
			}
		}
		
//		Switch to Different Window
		driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
		
		allWinIds = driver.getWindowHandles();
		for(String eachWinddowId : allWinIds) {
			if(!eachWinddowId.equals(defaultWinId)) {
				driver.switchTo().window(eachWinddowId);
				driver.manage().window().maximize();
				loginPage = new LoginPage(driver);
				homePage = new HomePage(driver);
				loginPage.loginToOpenMrs(Utils.appProperties.getProperty("user.name"),
						Utils.appProperties.getProperty("password"), "Registration Desk");
				commons.captureScreenshot();
				homePage.clickLogoutButton();
				Thread.sleep(5000);
				driver.close();
				driver.switchTo().window(defaultWinId);
				break;
			}
		}
		
		
		

	}

}
