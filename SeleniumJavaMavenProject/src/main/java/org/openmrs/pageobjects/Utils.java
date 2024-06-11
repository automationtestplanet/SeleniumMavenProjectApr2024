package org.openmrs.pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Utils {
	public static String propFilePath = System.getProperty("user.dir")
			+ "//src//main//resources//application.properties";

	public static Properties appProperties = new Properties();

	public static void loadProperties() {
		try {
			appProperties.load(new FileInputStream(new File(propFilePath)));
		} catch (Exception e) {
			System.out.println("Exception Occured while loading the application properties " + e.getCause());
		}
	}

	static {
		loadProperties();
	}

	public static void setProperty(String propName, String propVale) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(propFilePath));
			appProperties.setProperty(propName, propVale);
			appProperties.store(fos, "");
		} catch (Exception e) {
			System.out.println("Exception Occured while setting the application properties " + e.getCause());
		}
	}
	
	
	public static List<String[]> readDataFromExcel(String filePath, String sheetname) {		
		
		List<String[]> testDataList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			
			int rows = sheet.getLastRowNum();
			System.out.println("Row Count: " + rows);

			int index = 0;
			for (int row1 = 1; row1 < rows + 1; row1++) {
				String[] eachRowData = new String[sheet.getRow(row1).getLastCellNum()];

				Iterator<Cell> cellIterator = sheet.getRow(row1).cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case NUMERIC:
						eachRowData[index] = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						eachRowData[index] = cell.getStringCellValue();
						break;
					case BOOLEAN:
						eachRowData[index] = String.valueOf(cell.getBooleanCellValue());
						break;
					case BLANK:
						eachRowData[index] = "";
						break;
					}
					index++;
				}
				index = 0;
				testDataList.add(eachRowData);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testDataList;
	}
}
