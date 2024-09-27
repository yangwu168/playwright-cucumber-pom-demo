package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.orangehrmlive.base.Base;

public class DashboardPage extends Base {
	public static final Logger log = LogManager.getLogger(DashboardPage.class);
	
	public DashboardPage(Page page) {
		this.page = page;
	}
	
	public boolean verifyDashboardPage() {
		Locator sidebar = page.locator("//*[@class='oxd-sidepanel-body']");
		assertThat(sidebar).isVisible();
		return lib.waitUntilElementVisible(sidebar);
	}
}
