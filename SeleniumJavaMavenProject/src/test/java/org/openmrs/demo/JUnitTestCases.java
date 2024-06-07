package org.openmrs.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitTestCases extends JUnitTestSuit{

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before All");
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Before each");
	}
	
	@Test
	public void registerPatient() {
		System.out.println("registerPatient");
	}
	
	@Test
	public void findPatient() {
		System.out.println("findPatient");
		Assertions.assertEquals(true, false);
	}
	
	@Test
	public void activeVisitsAndAddAttachments() {
		System.out.println("activeVisitsAndAddAttachments");
	}
	
	@Test
	public void deletePatient() {
		System.out.println("deletePatient");
	}

	@AfterEach
	public void tearDown() throws Exception {
		System.out.println("After each");
	}


	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After All");
	}

	

}
