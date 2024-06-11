package org.openmrs.pageobjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Commons extends BasePage {

	public Commons(WebDriver driver) {
		super(driver);
	}

	public static String screenshotPath;
	
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

	public void captureScreenshot() {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File sreenshot = ts.getScreenshotAs(OutputType.FILE);
			String screenshotName = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()).replaceAll("[^0-9]",
					"") + ".jpg";
			screenshotPath = System.getProperty("user.dir") + "//src/main//resources//screenshots//"
					+ screenshotName;
			File destiationLoc = new File(screenshotPath);

			FileUtils.copyFile(sreenshot, destiationLoc);
		} catch (Exception e) {
			System.out.println("Exception occured while capturing the screenshot " + e.getCause());
		}

	}

}
