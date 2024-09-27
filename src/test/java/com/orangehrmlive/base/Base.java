package com.orangehrmlive.base;

import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.orangehrmlive.utility.PlaywrightLibrary;

import io.cucumber.java.Scenario;

public class Base {
	
	public static final Logger log = LogManager.getLogger(Base.class);

	protected Browser browser;
	protected BrowserContext context;
	protected Page page;
	protected static PlaywrightLibrary lib;

//	public Base() {
//		lib = new SeleniumLibrary();
//	}
	
	public Page getPage() {
		return page;
	}
	
	public Page initDriver() {
		browser = Playwright.create().chromium().launch(
				new LaunchOptions()
				.setHeadless(false)
				.setArgs(Arrays.asList(new String[]{"--start-maximized"}))
				);
		context = browser.newContext(new NewContextOptions().setViewportSize(null));
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
		page = context.newPage();
		lib = new PlaywrightLibrary(page);
		return page;
	}
	
	public void tearDownTest(Scenario scenario) {
		if(scenario.isFailed()) {
			String testName = scenario.getName();
			// take a screenshot
			log.info("Test failed...taking a screenshot");			
			final byte[] screenshot = page.screenshot();
			// attach the embedded screenshot to Cucumber report
			log.info("Attaching screenshot to Cucumber report");
			scenario.attach(screenshot, "image/png", testName);
		}
		context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/traces/" + scenario.getName() + ".zip")));
		browser.close();
		page.close();
	}
	
	public void tearDownTest() {
		context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/traces/" + lib.getCurrentTime() + ".zip")));
		browser.close();
		page.close();
	}
	
}
