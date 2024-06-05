package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(partialLinkText = "Logout")
	WebElement logoutElement;

	@FindBy(css = "i[class='icon-home small']")
	WebElement homeButton;

	public WebElement getLogout() {
		return logoutElement;
	}

	public WebElement getModule(String moduleName) {
		return driver.findElement(By.partialLinkText(moduleName));
	}

	public WebElement getHomeButton() {
		return homeButton;
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

	public void clickHomeButton() {
		getHomeButton().click();
	}

	public void clickLogoutButton() {
		getLogout().click();
	}

}
