package com.orangehrmlive.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrmlive.pageobjects.ChangeProfilePicPage;
import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.MyInfoPage;
import com.orangehrmlive.pageobjects.SideTopPanels;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditProfileSteps {
	public static final Logger log = LogManager.getLogger(EditProfileSteps.class);
	
	LoginPage loginPage = new LoginPage(Hooks.getPage());
	DashboardPage dashboardPage = new DashboardPage(Hooks.getPage());
	SideTopPanels panels = new SideTopPanels(Hooks.getPage());
	MyInfoPage myInfoPage = new MyInfoPage(Hooks.getPage());
	ChangeProfilePicPage changeProfilePicPage = new ChangeProfilePicPage(Hooks.getPage());
	
	@Given("I click on the My Info button on the sidebar")
	public void i_click_on_the_my_info_button_on_the_sidebar() {
	    panels.clickMyInfo();
	}
	
	@Given("I verify My Info page is visible")
	public void i_verify_my_info_page_is_visible() {
	    assertTrue(myInfoPage.verifyMyInfoPage());
	}

	@When("I update employee full name to {string} {string}")
	public void i_update_employee_full_name_to(String firstName, String lastName) {
	    myInfoPage.updateEmployeeName(firstName, lastName);
	}
	
	@When("I click save on the my info page")
	public void i_click_save_on_the_my_info_page() {
	    myInfoPage.clickSaveButton();
	}
	
	@Then("I verify name is updated in profile to {string} {string}")
	public void i_verify_name_is_updated_in_profile_to(String firstName, String lastName) {
	    assertTrue(myInfoPage.verifyUpdatedName(firstName + " " + lastName));
	}

	@When("I update DOB to {string}")
	public void i_update_dob_to(String date) {
	    myInfoPage.updateDOB(date);
	}
	
	@Then("I verify DOB should not be in the future error")
	public void i_verify_dob_should_not_be_in_the_future_error() {
	    assertTrue(myInfoPage.verifyFutureDOBErrorMsg());
	}

	@Then("I verify invalid DOB format error")
	public void i_verify_invalid_dob_format_error() {
	    assertTrue(myInfoPage.verifyInvalidDOBFormatErrorMsg());
	}

	@When("I click on profile photo")
	public void i_click_on_profile_photo() {
	    myInfoPage.clickProfilePhoto();
	    assertTrue(changeProfilePicPage.verifyChangeProfilePicPage());
	}
	
	@When("I upload a new profile photo {string}")
	public void i_upload_a_new_profile_photo(String filePath) {
	    changeProfilePicPage.uploadPhoto(filePath);
	}
	
	@Then("I verify profile photo is updated")
	public void i_verify_profile_photo_is_updated() {
	    assertTrue(changeProfilePicPage.verifySuccessfullyUpdatedToast());
	}
	
	@Then("I verify image required error")
	public void i_verify_image_required_error() {
	    assertTrue(changeProfilePicPage.verifyImageRequiredErrorMsg());
	}
}
