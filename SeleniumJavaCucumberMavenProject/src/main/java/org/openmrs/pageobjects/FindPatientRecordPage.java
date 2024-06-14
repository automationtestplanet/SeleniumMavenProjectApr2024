package org.openmrs.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FindPatientRecordPage extends BasePage {

	public FindPatientRecordPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "patient-search")
	WebElement patientSearchELement;

	@FindBy(xpath = "//table[@id='patient-search-results-table']/thead/tr/th/div")
	List<WebElement> allColumnNamesPath;
	
	@FindBy(xpath = "//td[contains(text(),'No matching records found')]")
	WebElement noRecordsFoundElement;

	public WebElement getPatientSeach() {
		return patientSearchELement;
	}

	public List<WebElement> getAllColumnNames() {
		return allColumnNamesPath;
	}

	public void setPatientIdInPatientSearchFiled(String patientId) {
		try {
			getPatientSeach().sendKeys(patientId);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Exception occured while searching the patient");
		}
	}

	public int getPatientSearchResultTableColumnIndex(String columnName) {
		int index = 1;
		Map<String, Integer> columnNamesAndIndexMap = new HashMap<>();
		for (WebElement eachColumnNameElement : getAllColumnNames()) {
			columnNamesAndIndexMap.put(eachColumnNameElement.getText().trim(), index);
			index++;
		}
		return columnNamesAndIndexMap.get(columnName);
	}

	public WebElement getPatientSerachResultTableColumnElement(String columnName) {
		return driver.findElement(By.xpath("//table[@id='patient-search-results-table']/tbody/tr/td["
				+ getPatientSearchResultTableColumnIndex(columnName) + "]"));
	}

	public String getPatientSearchResultTableColumnValue(String columnName) {
		return getPatientSerachResultTableColumnElement(columnName).getText().trim();
	}
	
	public WebElement getNoRecordsFoundElement() {
		return noRecordsFoundElement;
	}

	public boolean verifyPatientSearchResult(String columnName, String expectedColumnValue) {
		if (getPatientSearchResultTableColumnValue(columnName).contains(expectedColumnValue))
			return true;
		else
			return false;
	}
	
	public WebElement getPatientSerachResultTableRow(String columnName) {
		return driver.findElement(By.xpath("//table[@id='patient-search-results-table']/tbody/tr/td["
				+ getPatientSearchResultTableColumnIndex(columnName) + "]//parent::tr"));
	}

	public void openPatientRecord(String columnName) {

//		getPatientSerachResultTableColumnElement(columnName).click();
//		WebElement element = getPatientSerachResultTableColumnElement(columnName);
//		Actions actions = new Actions(driver);
//		Action action = actions.moveToElement(element).click(element).build();
//		action.perform();
//		actions.moveToElement(element).click(element).build().perform();
//		element.findElement(By.xpath("//parent::tr")).click();
//		actions.doubleClick(element);
//		actions.dragAndDrop(element, element);
//		actions.contextClick(element);		
//		actions.moveToElement(element).click(element).sendKeys(Keys.CONTROL+"a").sendKeys(Keys.CONTROL+"c");
		getPatientSerachResultTableRow(columnName).click();

	}
	
	public boolean verifyDeletePatient() {
		return getNoRecordsFoundElement().isDisplayed();
	}

}
