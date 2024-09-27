package com.orangehrmlive.runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		// add feature files
		features = {"src/test/resources/features/"},
		glue = {"com.orangehrmlive.stepdefinitions"},
		monochrome = true,
		plugin = {"pretty", 
				"html:target/htmlReports/htmlReport.html",
				"json:target/jsonReports/jsonReport.json",
				"junit:target/junitReports/junitReport.xml"},
		tags = "@Active",
		// @Active @Regression @Recruitment @Buzz @Profile @Timesheet @Login @Bug
		dryRun = false
		// change this to (dry run = false) to run the tests
		// the (dry run = true) option is generate code snippet for
		// newly added steps for the feature file / files
		)
public class TestRunner {
	public static final Logger log = LogManager.getLogger(TestRunner.class);
}