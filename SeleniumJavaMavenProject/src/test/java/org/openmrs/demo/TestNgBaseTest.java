package org.openmrs.demo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class TestNgBaseTest {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.out.println("beforeSuite");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		System.out.println("beforeTest");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"UserName","Password"})
	public void beforeMethod(String userName, String password, Method currentMethodName) {
		System.out.println("beforeMethod");
//		System.out.println(userName);
//		System.out.println(password);
		System.out.println("CurrentTestName: "+ currentMethodName.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("afterClass");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("afterTest");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		System.out.println("afterSuite");
	}
	
	
	@DataProvider(name="data1")
	public Object[] data1() {
		Object[] objArr = {"Ram","Ramesh","Raj"};		
		return objArr;
	}
	
	@DataProvider(name="data2")
	public Object[][] data2() {
		Object[][] objArr = {{"Ram","20","9876543210"}, {"Ramesh","22","9876543211"},{"Raj","24","9876543212"}};		
		return objArr;
	}
	
	@DataProvider(name="data3")
	public Iterator<String[]> data3(Method curentTestName) {
		
		System.out.println("CurrentTestName: "+ curentTestName.getName());
		
		List<String[]> list1 = new ArrayList<String[]>();
		
		String[] str1 = {"Ram","20","9876543210"};
		String[] str2 = {"Ramesh","22","9876543211"};
		String[] str3 = {"Raj","24","9876543212"};	
		
		list1.add(str1);
		list1.add(str2);
		list1.add(str3);		
		return list1.iterator();
	}
}

