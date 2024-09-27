package com.orangehrmlive.stepdefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrmlive.pageobjects.AddCandidatePage;
import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.RecruitmentPage;
import com.orangehrmlive.pageobjects.SideTopPanels;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCandidateApplicationSteps {
	public static final Logger log = LogManager.getLogger(AddCandidateApplicationSteps.class);
	
	LoginPage loginPage = new LoginPage(Hooks.getPage());
	DashboardPage dashboardPage = new DashboardPage(Hooks.getPage());
	SideTopPanels panels = new SideTopPanels(Hooks.getPage());
	RecruitmentPage recruitmentPage = new RecruitmentPage(Hooks.getPage());
	AddCandidatePage addCandidatePage = new AddCandidatePage(Hooks.getPage());

	String fileName;
	
	@Given("I click on the Recruitment button on the sidebar")
	public void i_click_on_the_recruitment_button_on_the_sidebar() {
	    panels.clickRecruitmentButton();
	}
	
	@Given("I verify Recruitment page is visible")
	public void i_verify_recruitment_page_is_visible() {
		assertTrue(recruitmentPage.verifyRecruitmentPage());
	}
	
	@Given("I click Add button")
	public void i_click_add_button() {
	    recruitmentPage.clickAddButton();
	}
	
	@Given("I verify Add Candidate page is visible")
	public void i_verify_add_candidate_page_is_visible() {
		assertTrue(addCandidatePage.verifyAddCandidatePage());
	}
	
	@When("I enter candidate details {string} as first name and {string} as last name and {string} as email")
	public void i_enter_candidate_details_as_first_name_and_as_last_name_and_as_email(String firstName, String lastName, String email) {
	    addCandidatePage.fillOutCandidateDetails(firstName, lastName, email);
	}
	
	@Then("I verify whitespace before first character error")
	public void i_verify_whitespace_before_first_character_error() {
	    assertTrue(addCandidatePage.verifyWhitespaceBeforeFirstCharErrorMsg());
	}

	@When("I enter an invalid date of application {string}")
	public void i_enter_an_invalid_date_of_application(String date) {
	    addCandidatePage.enterDate(date);
	    addCandidatePage.clickSaveButton();
	}

	@Then("I verify current date or previous date error")
	public void i_verify_current_date_or_previous_date_error() {
	    assertTrue(addCandidatePage.verifyShouldBeCurrentOrPreviousDateErrorMsg());
	}

	@When("I click save to submit candidate application")
	public void i_click_save_to_submit_candidate_application() {
	    addCandidatePage.clickSaveButton();
	}
	
	@Then("I verify vacancy error after I click save")
	public void i_verify_vacancy_error_after_i_click_save() {
	    assertTrue(addCandidatePage.verifyVacancyErrorMsg());
	}

	@When("I upload resume {string} by verifying it is displayed on screen {string}")
	public void i_upload_resume_by_verifying_it_is_displayed_on_screen(String filePath, String fileVerification) {
		addCandidatePage.uploadFile(filePath, fileVerification);
	}

	@When("I download the file associated with the candidate {string} {string}")
	public void i_download_the_file_associated_with_the_candidate(String firstName, String lastName) {
		assertTrue(recruitmentPage.verifyRecruitmentPage());
		assertTrue(recruitmentPage.verifyTableLoaded());
		int rowNum = recruitmentPage.generateCandidateRow(firstName + "  " + lastName);
		fileName = recruitmentPage.downloadFile(rowNum);
	}

	@Then("I verify the file is the same as the one uploaded {string}")
	public void i_verify_the_file_is_the_same_as_the_one_uploaded(String actualFile) {
	    assertEquals(fileName, actualFile);
	}
	
}
