package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class MyTimesheetPage extends Base {
	public static final Logger log = LogManager.getLogger(MyTimesheetPage.class);
	
	public MyTimesheetPage(Page page) {
		this.page = page;
	}
	
	public boolean verifyMyTimesheetPage() {
		Locator myTimesheetHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My Timesheet"));
		assertThat(myTimesheetHeader).isVisible();
		return lib.waitUntilElementVisible(myTimesheetHeader);
	}
	
	public void clickEditButton() {
		Locator editButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit"));
		lib.clickButton(editButton);
	}
	
	public boolean[] validateTimesheet() {
		boolean[] isValid = new boolean[4];
		Locator project = page.getByText("Apache Software Foundation - ASF - Phase 1");
		isValid[0] = lib.verifyElementText(project, "Apache Software Foundation - ASF - Phase 1");
		Locator activity = page.getByText("Bug Fixes");
		isValid[1] = lib.verifyElementText(activity, "Bug Fixes");
		Locator mondayHours = page.locator("span").filter(new Locator.FilterOptions().setHasText("10:00"));
		isValid[2] = lib.verifyElementText(mondayHours, "10:00");
		Locator totalHours = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("10:00")).last();
		isValid[3] = lib.verifyElementText(totalHours, "10:00");
		return isValid;
	}
	
	public void clickSubmitButton() {
		if (!verifyTimesheetSubmission()) {
			Locator submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
			lib.clickButton(submitButton);
		}
	}
	
	public boolean verifyTimesheetSubmission() {
		Locator status = page.getByText("Status:");
		assertThat(status).isVisible();
		return lib.verifyElementText(status, "Status: Submitted");
	}
}
