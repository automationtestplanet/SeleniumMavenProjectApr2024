package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(partialLinkText = "Logout")
	WebElement logoutElement;

	public WebElement getLogout() {
		return logoutElement;
	}

	public WebElement getModule(String moduleName) {
		return driver.findElement(By.partialLinkText(moduleName));
	}

	public boolean verifyLogoutButton() {
		if (getLogout().isDisplayed()) {
			System.out.println("Login button present");
			return true;
		} else {
			System.out.println("Login button not present");
			return false;
		}
	}

	public void selectModule(String moduleName) {
		getModule(moduleName).click();
	}

}