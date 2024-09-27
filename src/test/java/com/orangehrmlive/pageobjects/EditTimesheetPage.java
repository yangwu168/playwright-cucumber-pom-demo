package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class EditTimesheetPage extends Base {
	public static final Logger log = LogManager.getLogger(EditTimesheetPage.class);
	
	public EditTimesheetPage(Page page) {
		this.page = page;
	}
	
	public boolean verifyEditTimesheetPage() {
		Locator editTimesheetHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Edit Timesheet"));
		assertThat(editTimesheetHeader).isVisible();
		return lib.waitUntilElementVisible(editTimesheetHeader);
	}
	
	public void clearTimesheet() {
		String xpath = lib.generateTableCellLocator(1, 10);
		Locator trashButton = page.locator(xpath);
		lib.clickButton(trashButton);
	}
	
	public void enterProject(String project) {
		String xpath = lib.generateTableCellLocator(1, 1);
		String fullxpath = xpath + "//input[1]";
		Locator projectInput = page.locator(fullxpath);
		lib.enterTextByFill(projectInput, project);
		if (project.equals("Apache Software")) {
			Locator apacheSoftwareDropdown = page.getByText("Apache Software Foundation - ASF - Phase 1");
			assertThat(apacheSoftwareDropdown).isVisible();
			lib.clickButton(apacheSoftwareDropdown);
		} else if (project.equals("fakeProject123")) {
			Locator noRecordsFoundDropdown = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("No Records Found"));
			assertThat(noRecordsFoundDropdown).isVisible();
			lib.clickButton(noRecordsFoundDropdown);
		}
	}
	
	public void selectActivity(String activity) {
		String xpath = lib.generateTableCellLocator(1, 2);
		Locator activityDropdown = page.locator(xpath);
		lib.clickButton(activityDropdown);
		Locator bugFixActivityOption = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(activity));
		lib.scrollIntoView(bugFixActivityOption);
		lib.clickButton(bugFixActivityOption);
	}
	
	public void enterMondayHours(String hours) {
		String xpath = lib.generateTableCellLocator(1, 3);
		String fullxpath = xpath + "//input[1]"; 
		Locator mondayHours = page.locator(fullxpath);
		lib.enterTextByFill(mondayHours, hours);
	}
	
	public void clickSaveButton() {
		Locator saveButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));
		lib.clickButton(saveButton);
	}
	
	public boolean verifySelectAProjectErrorMsg() {
		Locator selectAProjectError = page.getByText("Select a Project");
		assertThat(selectAProjectError).isVisible();
		return lib.waitUntilElementVisible(selectAProjectError);
	}
	
	public boolean verifySelectAnActivityErrorMsg() {
		Locator selectAnActivityError = page.getByText("Select an Activity");
		assertThat(selectAnActivityError).isVisible();
		return lib.waitUntilElementVisible(selectAnActivityError);
	}
	
	public void editProject(String project) {
		String xpath = lib.generateTableCellLocator(1, 1);
		String fullxpath = xpath + "//input[1]";
		Locator projectInput = page.locator(fullxpath);
		lib.enterTextByFill(projectInput, project);
	}
	
	public boolean verifyInvalidHoursOrFormatErrorMsg() {
		Locator invalidHoursOrFormatError = page.getByText("Should Be Less Than 24 and in HH:MM or Decimal Format");
		assertThat(invalidHoursOrFormatError).isVisible();
		return lib.waitUntilElementVisible(invalidHoursOrFormatError);
	}
}
