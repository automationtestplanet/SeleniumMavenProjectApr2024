package org.openmrs.pageobjects;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientDetailsPage extends BasePage{
	
	public PatientDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "PersonName-givenName")
	WebElement givenNameElement;
	
	@FindBy(className = "PersonName-middleName")
	WebElement middleNameElement;
	
	@FindBy(className = "PersonName-familyName")
	WebElement familyNameElement;
	
	@FindBy(xpath = "//em[contains(text(),'Patient ID')]//following-sibling::span")
	WebElement patientIdElement;
	
	public WebElement getGivenName() {
		return givenNameElement;
	}
	
	public WebElement getMiddleName() {
		return middleNameElement;
	}
	
	public WebElement getFamilyNmeName() {
		return familyNameElement;
	}
	
	public WebElement getPatientId() {
		return patientIdElement;
	}
	
	public boolean verifyPatientName(String expectedPatientName) {
		
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOf(getGivenName()));
		
//		FluentWait wait  = new FluentWait(driver);
//		wait.withTimeout(20,TimeUnit.SECONDS).pollingEvery(5,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
////		wait.until(new Function<WebDriver, WebElement>() {
////			@Override
////			public WebElement apply(WebDriver t) {
////				// TODO Auto-generated method stub
////				return getGivenName();
////			}
////		});		
//		WebElement givenNameElement = (WebElement) wait.until(driver->getGivenName());
		
		String givenName = getGivenName().getText().trim();
		String middleName = getMiddleName().getText().trim();
		String familyName = getFamilyNmeName().getText().trim();
		
		if(expectedPatientName.contains(givenName) && expectedPatientName.contains(middleName)&&expectedPatientName.contains(familyName)) {
			System.out.println("Patient Name is verified");
			return true;
		}else {
			System.out.println("Patient Name is not verified");
			return false;
		}
	}
	
	public String getPatientIdValue() {
		return getPatientId().getText().trim();
	}

}
