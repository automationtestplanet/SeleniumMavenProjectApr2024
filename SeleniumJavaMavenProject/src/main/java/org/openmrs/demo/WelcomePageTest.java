package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WelcomePageTest {

	public static void main(String[] args) {
		 
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//drivers//chrome124//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(System.getProperty("user.dir") + "//src//main//resources//html//Welcome.html");
		
//		driver.findElement(By.id("userName")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("[placeholder='Enter User Name']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[placeholder='Enter User Name']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[id='userName'][placeholder='Enter User Name']")).sendKeys("Test1234");		
		driver.findElement(By.cssSelector("#userName")).sendKeys("Test1234");
		
		
		
		
		
		
//		driver.findElement(By.name("Password")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("[name='Password']")).sendKeys("Test1234");
//		driver.findElement(By.cssSelector("input[name='Password']")).sendKeys("Test1234");
		driver.findElement(By.cssSelector("input[name='Password'][type='password']")).sendKeys("Test1234");
		
		
		
//		String str = "\"\"";
		
		
		
//		driver.findElement(By.tagName("input")).sendKeys("1234Test");

	}

}
