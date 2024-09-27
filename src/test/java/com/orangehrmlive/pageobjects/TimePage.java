package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class TimePage extends Base {
	public static final Logger log = LogManager.getLogger(TimePage.class);
	
	public TimePage(Page page) {
		this.page = page;
	}
	
	public boolean verifyTimePage() {
		Locator timeHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Time").setExact(true));
		assertThat(timeHeader).isVisible();
		return lib.waitUntilElementVisible(timeHeader);
	}
	
	public void clickMyTimesheets() {
		Locator timesheetsDropdown = page.locator("li").filter(new Locator.FilterOptions().setHasText("Timesheets"));
		lib.clickButton(timesheetsDropdown);
		Locator myTimesheetsButton = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("My Timesheets"));
		assertThat(myTimesheetsButton).isVisible();
		lib.clickButton(myTimesheetsButton);
	}
}
