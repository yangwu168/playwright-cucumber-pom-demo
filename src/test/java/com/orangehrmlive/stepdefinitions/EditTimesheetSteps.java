package com.orangehrmlive.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.EditTimesheetPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.MyTimesheetPage;
import com.orangehrmlive.pageobjects.SideTopPanels;
import com.orangehrmlive.pageobjects.TimePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditTimesheetSteps {
	public static final Logger log = LogManager.getLogger(EditTimesheetSteps.class);
	
	LoginPage loginPage = new LoginPage(Hooks.getPage());
	DashboardPage dashboardPage = new DashboardPage(Hooks.getPage());
	SideTopPanels panels = new SideTopPanels(Hooks.getPage());
	TimePage timePage = new TimePage(Hooks.getPage());
	MyTimesheetPage myTimesheetPage = new MyTimesheetPage(Hooks.getPage());
	EditTimesheetPage editTimesheetPage = new EditTimesheetPage(Hooks.getPage());

	@Given("I login with username {string} and password {string}")
	public void i_login_with_username_and_password(String username, String password) {
	    loginPage.enterLoginCredentials(username, password);
	    loginPage.clickLogin();
	}

	@Given("I click on the Time button on the sidebar")
	public void i_click_on_the_time_button_on_the_sidebar() {
	    panels.clickTimeButton();
	}
	@Given("I verify time page is visible")
	public void i_verify_time_page_is_visible() {
		assertTrue(timePage.verifyTimePage()); 
	}
	@When("I click on my personal timesheets")
	public void i_click_on_my_personal_timesheets() {
	    timePage.clickMyTimesheets();
	}
	@When("I verify my timesheet page is visible")
	public void i_verify_my_timesheet_page_is_visible() {
		assertTrue(myTimesheetPage.verifyMyTimesheetPage());
	}
	@When("I click edit")
	public void i_click_edit() {
	    myTimesheetPage.clickEditButton();
	}
	@When("I verify edit timesheet page is visible")
	public void i_verify_edit_timesheet_page_is_visible() {
		assertTrue(editTimesheetPage.verifyEditTimesheetPage());
	}
	@When("I enter project name as {string}")
	public void i_enter_project_name_as(String project) {
		editTimesheetPage.clearTimesheet();
	    editTimesheetPage.enterProject(project);
	}
	@When("I select {string} activity")
	public void i_select_activity(String activity) {
	    editTimesheetPage.selectActivity(activity);
	}
	@When("I input {string} on Monday")
	public void i_input_on_monday(String hours) {
	    editTimesheetPage.enterMondayHours(hours);
	}
	@When("I click save")
	public void i_click_save() {
	    editTimesheetPage.clickSaveButton();
	}
	@Then("I verify my timesheet and total hours are correct")
	public void i_verify_my_timesheet_and_total_hours_are_correct() {
	    // return boolean array of timesheet checks - project name, activity name,
		// Monday hours, and total hours
		boolean[] timesheet = myTimesheetPage.validateTimesheet();
	    // validate project name
	    assertTrue(timesheet[0]);
	    // validate activity name
	    assertTrue(timesheet[1]);
	    // validate Monday hours
	    assertTrue(timesheet[2]);
	    // validate total hours
	    assertTrue(timesheet[3]);
	}
	@Then("I click submit and verify timesheet is submitted")
	public void i_click_submit_and_verify_timesheet_is_submitted() {
	    myTimesheetPage.clickSubmitButton();
	    myTimesheetPage.verifyMyTimesheetPage();
	}

	@Then("I verify select a project error pops up")
	public void i_verify_select_a_project_error_pops_up() {
	    assertTrue(editTimesheetPage.verifySelectAProjectErrorMsg());
	}
	
	@Then("I verify select an activity error pops up")
	public void i_verify_select_an_activity_error_pops_up() {
		assertTrue(editTimesheetPage.verifySelectAnActivityErrorMsg());
	}
	
	@When("I edit project name to {string}")
	public void i_edit_project_name_to(String invalidProject) {
	    editTimesheetPage.editProject(invalidProject);
	}

	@Then("I verify invalid hours or format error")
	public void i_verify_invalid_hours_or_format_error() {
	    assertTrue(editTimesheetPage.verifyInvalidHoursOrFormatErrorMsg());
	}
}
