package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	WebElement userNameElement;
	
	@FindBy(name="password")
	WebElement passwordElement;
	
	@FindBy(id="loginButton")
	WebElement loginButton;

	public WebElement getUserName() {
		return userNameElement;
	}

	public WebElement getPassword() {
		return userNameElement;
	}

	public WebElement getMainModule(String moduleName) {
		return driver.findElement(By.id(moduleName));
	}

	public WebElement getLogout() {
		return loginButton;
	}

	public void setUserName(String userName) {
		getUserName().sendKeys(userName);
	}

	public void setPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void selectMainModule(String moduleName) {
		getMainModule(moduleName).click();
	}

	public void clickLogin() {
		getLogout().click();
	}

	public void loginToOpenMrs(String userName, String password, String moduleName) {
		setUserName(userName);
		setPassword(password);
		selectMainModule(moduleName);
		clickLogin();
	}

}
