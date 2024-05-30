package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Commons {

	WebDriver driver;
	
	public Commons(WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigateToApplication(String url) {
		driver.get(url);
	}

	public WebElement getModuleTitle(String moduleName) {
		return driver.findElement(By.xpath("//h2[contains(text(),'" + moduleName + "')]"));
	}	

	public boolean verifyModulePage(String moduleName) {
		if (getModuleTitle(moduleName).isDisplayed()) {
			System.out.println(moduleName + " is displayed");
			return true;
		} else {
			System.out.println(moduleName + " is not displayed");
			return false;
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public boolean verifyPage(String pageName) {
		if (getPageTitle().equals(pageName)) {
			System.out.println(pageName + " Page is displayed");
			return true;
		} else {
			System.out.println("Current Page title: " + driver.getTitle());
			System.out.println(pageName + " Page is not displayed");
			return false;
		}

	}

}
