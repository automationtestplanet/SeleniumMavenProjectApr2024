package org.openmrs.cucumber.testrunner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features",
				glue = "org.openmrs.stepdefinitions",
				plugin = { "pretty","html:target" }, 
				tags = {"@RegisterMultiplePatients"},
				dryRun = false,
				snippets = SnippetType.CAMELCASE)
public class TestRunner {

}
