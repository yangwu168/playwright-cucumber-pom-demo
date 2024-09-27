package com.orangehrmlive.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;
import com.orangehrmlive.base.Base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	public static final Logger log = LogManager.getLogger(Hooks.class);
	
	private Base base;
	private static Page page;
	
	public static Page getPage() {
		return page;
	}
	
	@Before
	public void startBrowser() {
		base = new Base();
		page = base.initDriver();
	}

	@After()
	public void tearDown(Scenario scenario) {
		log.info("After each test...");
		base.tearDownTest(scenario);
	}
}

