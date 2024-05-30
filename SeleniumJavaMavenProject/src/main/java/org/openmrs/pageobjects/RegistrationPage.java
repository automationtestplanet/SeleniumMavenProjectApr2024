package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(name = "givenName")
	WebElement givenNameElement;

	@FindBy(name = "middleName")
	WebElement middleNameElement;

	@FindBy(name = "familyName")
	WebElement familyNameElement;

	@FindBy(id = "next-button")
	WebElement nextButtonElement;

	@FindBy(id = "gender-field")
	WebElement genderDropdownElement;

	@FindBy(id = "birthdateDay-field")
	WebElement birthDayElement;

	@FindBy(id = "birthdateMonth-field")
	WebElement birthMonthDropDown;

	@FindBy(id = "birthdateYear-field")
	WebElement birthYearElement;

	@FindBy(xpath = "//span[contains(text(),'Name:')]//parent::p")
	WebElement actualNameELement;

	@FindBy(xpath = "//span[contains(text(),'Gender:')]//parent::p")
	WebElement actualGender;

	@FindBy(xpath = "//span[contains(text(),'Birthdate:')]//parent::p")
	WebElement actualDtaeOfBirthElement;

	@FindBy(css = "input[value='Confirm']")
	WebElement confirmButtonElement;

	@FindBy(id = "cancelSubmission")
	WebElement cancelButtonElement;

	public WebElement getGivenName() {
		return givenNameElement;
	}

	public WebElement getMiddleName() {
		return middleNameElement;
	}

	public WebElement getFamilyName() {
		return familyNameElement;
	}

	public WebElement getNextButton() {
		return nextButtonElement;
	}

	public WebElement getGenderDropdown() {
		return genderDropdownElement;
	}

	public WebElement getBirthDay() {
		return birthDayElement;
	}

	public WebElement getBirthMonthDropDown() {
		return birthMonthDropDown;
	}

	public WebElement getBirthYear() {
		return birthYearElement;
	}

	public WebElement getActualName() {
		return actualNameELement;
	}

	public WebElement getActualGender() {
		return actualGender;
	}

	public WebElement getActualDtaeOfBirth() {
		return actualDtaeOfBirthElement;
	}

	public WebElement getConfirmButton() {
		return confirmButtonElement;
	}

	public WebElement getCancelButton() {
		return cancelButtonElement;
	}

	public void setGivenName(String givenName) {
		getGivenName().sendKeys(givenName);
	}

	public void setMiddleName(String middleName) {
		getMiddleName().sendKeys(middleName);
	}

	public void setFamilyName(String familyName) {
		getFamilyName().sendKeys(familyName);
	}

	public void setBirthDay(String birthDay) {
		getBirthDay().sendKeys(birthDay);
	}

	public void setBirthYear(String birthYear) {
		getBirthYear().sendKeys(birthYear);
	}

	public void clickNextButton() {
		getNextButton().click();
	}

	public void clickConfrim() {
		getConfirmButton().click();
	}

	public void clickCancel() {
		getCancelButton().click();
	}

	public void enterFullName(String fullName) {
		String name[] = fullName.split(",");
		String givenName = name[0].trim();
		String middleName = name[1].trim();
		String familyName = name[2].trim();
		setGivenName(givenName);
		setMiddleName(middleName);
		setFamilyName(familyName);
	}

	public void selectGender(String gender) {
		Select genderDropdown = new Select(getGenderDropdown());
		genderDropdown.selectByVisibleText(gender);
	}

	public void enterDateOfBirth(String birthDate) {
		String[] dateofBirth = birthDate.split(",");
		String birthDay = dateofBirth[0].trim();
		String birthMonth = dateofBirth[1].trim();
		String birthYear = dateofBirth[2].trim();
		setBirthDay(birthDay);
		Select birthMonthDropdown = new Select(getBirthMonthDropDown());
		birthMonthDropdown.selectByVisibleText(birthMonth);
		setBirthYear(birthYear);
	}

	public boolean verifyRegisterDetials(String expectedName, String expectedGender, String expectedDateOfBirth) {
		String actualName = getActualName().getText();
		String actualGender = getActualGender().getText();
		String actualBirthDate = getActualDtaeOfBirth().getText();

		if (actualName.contains(expectedName) && actualGender.contains(expectedGender)
				&& actualBirthDate.contains(expectedDateOfBirth)) {
			System.out.println("Register Details are correct");
			return true;
		} else {
			System.out.println("Register Details are not correct");
			return false;
		}
	}

}
