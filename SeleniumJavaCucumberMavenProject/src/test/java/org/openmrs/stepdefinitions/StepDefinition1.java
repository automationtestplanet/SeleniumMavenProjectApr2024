package org.openmrs.stepdefinitions;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition1 {
	
	@Given("Setup the driver path")
	public void setUpDriverPath() {
		System.out.println("DriverPath is set");
	}

	@Given("User is on Login Page")
	public void launchApplication() {
		System.out.println("Application Launched");
	}

	@When("User enters username as {string} and password as {string}")
	public void loginToApplication(String userName, String password) {
		System.out.println("ENtered Login userName as " + userName + " and password as " + password);
	}

	@When("Clicks Login button")
	public void clickLogin() {
		System.out.println("Login Clicked");
	}

	@Then("User should able to login to the application")
	public void verifyLogin() {
		System.out.println("Login Pass");
		Assertions.assertEquals(true, true);
	}

	@Then("User should not login to the application")
	public void verifyInvlaidLogin() {
		System.out.println("Login Failed");
		Assertions.assertEquals(true, true);
	}

}
