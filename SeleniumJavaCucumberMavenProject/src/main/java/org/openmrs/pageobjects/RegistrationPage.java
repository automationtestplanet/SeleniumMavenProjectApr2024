package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
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
	
	@FindBy(id="address1")
	WebElement address1Element;
	
	@FindBy(id="address2")
	WebElement address2Element;
	
	@FindBy(id="cityVillage")
	WebElement cityELement;	
	
	@FindBy(id="stateProvince")
	WebElement stateElement;	
	
	@FindBy(id="country")
	WebElement countryElement;
	
	@FindBy(id="postalCode")
	WebElement pinCodeElement;
	
	
	@FindBy(name="phoneNumber")
	WebElement phoneNumberElement;	

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
	
	public WebElement getAddress1() {
		return address1Element;
	}
	
	public WebElement getAddress2() {
		return address2Element;
	}
	
	public WebElement getCity() {
		return cityELement;
	}
	
	public WebElement getState() {
		return stateElement;
	}
	
	public WebElement getCountry() {
		return countryElement;
	}
	
	public WebElement getPincode() {
		return pinCodeElement;
	}
	
	public WebElement getPhoneNumber() {
		return phoneNumberElement;
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
	
	public void setAddress1(String addres1) {
		getAddress1().sendKeys(addres1);
	}
	
	public void setAddress2(String addres2) {
		getAddress2().sendKeys(addres2);
	}
	
	public void setCity(String city) {
		getCity().sendKeys(city);
	}
	
	public void setState(String state) {
		getState().sendKeys(state);
	}
	
	public void setCountry(String country) {
		getCountry().sendKeys(country);
	}
	
	public void setPinCode(String pinCode) {
		getPincode().sendKeys(pinCode);
	}

	public void setPhoneNumber(String phoneNumber) {
		getPhoneNumber().sendKeys(phoneNumber);
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
	
	public void enterAddress(String address) {
		String[] addressArr = address.split(",");
		String address1 = addressArr[0].trim();
		String address2 = addressArr[1].trim();
		String city = addressArr[2].trim();
		String state = addressArr[3].trim();
		String country = addressArr[4].trim();
		String pinCode = addressArr[5].trim();
		
		setAddress1(address1);
		setAddress2(address2);
		setCity(city);
		setState(state);
		setCountry(country);
		setPinCode(pinCode);		
	}

}
