package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(partialLinkText = "Logout")
	WebElement logoutElement;

	@FindBy(css = "i[class='icon-home small']")
	WebElement homeButton;

	@FindBy(css = "button.navbar-toggler")
	WebElement logoutMenuElement;

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
//		getLogout().click();
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getLogout()));
		js.executeScript("arguments[0].scrollIntoView(true)", getLogout());
		wait.until(ExpectedConditions.elementToBeClickable(getLogout()));		
		js.executeScript("arguments[0].click()", getLogout());
	}
	
	public WebElement getLogoutMenu() {
		return logoutMenuElement;
	}
	
	public void clickLogoutMenu() {
//		getLogoutMenu().click();
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getLogoutMenu()));
		js.executeScript("arguments[0].scrollIntoView(true)", getLogoutMenu());
		wait.until(ExpectedConditions.elementToBeClickable(getLogoutMenu()));		
		js.executeScript("arguments[0].click()", getLogoutMenu());
		
	}
	

}
