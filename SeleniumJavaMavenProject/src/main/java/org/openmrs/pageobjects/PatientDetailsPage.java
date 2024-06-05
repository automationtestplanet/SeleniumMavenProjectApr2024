package org.openmrs.pageobjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientDetailsPage extends BasePage {

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

	@FindBy(xpath = "//div[contains(text(),'Start Visit')]//ancestor::a")
	WebElement startVisitElement;

	@FindBy(id = "start-visit-with-visittype-confirm")
	WebElement startVisitConfirmButtonElement;

	@FindBy(css = "a[id='attachments.attachments.visitActions.default']")
	WebElement attachmentsElement;

	@FindBy(xpath = "//div[contains(text(),'Click or drop a file here')]")
	WebElement clickAndDropFileElement;

	@FindBy(css = "textarea[placeholder='Enter a caption']")
	WebElement captionELement;

	@FindBy(xpath = "//button[text()='Upload file']")
	WebElement fileUploadButtonElement;

	@FindBy(css = ".icon-file-pdf-o")
	WebElement uploadedFileElement;

	@FindBy(xpath = "//div[contains(text(),'Delete Patient')]//ancestor::a")
	WebElement deletePatientElement;

	@FindBy(id = "delete-reason")
	WebElement deleteReasonElement;

	@FindBy(css = "#delete-patient-creation-dialog button.confirm.right")
	WebElement deleteConfirmButtonElement;

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

	public WebElement getStartVisit() {
		return startVisitElement;
	}

	public WebElement getStartVisitConfirmButton() {
		return startVisitConfirmButtonElement;
	}

	public WebElement getAttachments() {
		return attachmentsElement;
	}

	public WebElement getClickAndDropFile() {
		return clickAndDropFileElement;
	}

	public WebElement getCaption() {
		return captionELement;
	}

	public WebElement getFileUploadButton() {
		return fileUploadButtonElement;
	}

	public WebElement getUploadedFileElement() {
		return uploadedFileElement;
	}

	public WebElement getDeletePatient() {
		return deletePatientElement;
	}

	public WebElement getDeleteReason() {
		return deleteReasonElement;
	}

	public WebElement getDeleteConfirmButton() {
		return deleteConfirmButtonElement;
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

		if (expectedPatientName.contains(givenName) && expectedPatientName.contains(middleName)
				&& expectedPatientName.contains(familyName)) {
			System.out.println("Patient Name is verified");
			return true;
		} else {
			System.out.println("Patient Name is not verified");
			return false;
		}
	}

	public String getPatientIdValue() {
		return getPatientId().getText().trim();
	}

	public void clickStartVisit() {
		getStartVisit().click();
	}

	public void clickStartVisitConfirm() {
		getStartVisitConfirmButton().click();
	}

	public void clickAttachments() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(getAttachments()));
			getAttachments().click();
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Exception occured while clicking Attachments " + e.getCause());
		}
	}

	public void clickDropFile() {
		getClickAndDropFile().click();
	}

	public void setCaption(String caption) {
		getCaption().sendKeys(caption);
	}

	public void clickFileUpload() {
		getFileUploadButton().click();
	}

	public void uploadFile(String filePath, String caption) {
		try {
			clickDropFile();
			Thread.sleep(5000);
			StringSelection strSelect = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelect, null);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			Thread.sleep(5000);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000);

			setCaption(caption);

			clickFileUpload();

		} catch (Exception e) {
			System.out.println("Exception occured while uploading the file " + e.getCause());
		}
	}

	public boolean verifyUploadedFile() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getUploadedFileElement()));
		return getUploadedFileElement().isDisplayed();
	}

	public void clickDeletePatient() {
		getDeletePatient().click();
	}

	public void setDeleteReason(String deleteReason) {
		getDeleteReason().sendKeys(deleteReason);
	}

	public void clickDeleteConfirm() {
		getDeleteConfirmButton().click();
	}

}
