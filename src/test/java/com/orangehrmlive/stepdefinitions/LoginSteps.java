package com.orangehrmlive.stepdefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.SideTopPanels;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	public static final Logger log = LogManager.getLogger(LoginSteps.class);
	
	LoginPage loginPage = new LoginPage(Hooks.getPage());
	DashboardPage dashboardPage = new DashboardPage(Hooks.getPage());
	SideTopPanels panels = new SideTopPanels(Hooks.getPage());

	@Given("I navigate to OrangeHRM login page {string}")
	public void i_navigate_to_orange_hrm_login_page(String url) {
	    loginPage.navigateToURL(url);
	}

	@Given("I verify login page is visible")
	public void i_verify_login_page_is_visible() {
		assertTrue(loginPage.verifyLoginPage());  
	}

	@When("I enter username as {string} and password as {string}")
	public void i_enter_username_as_and_password_as(String username, String password) {
		loginPage.enterLoginCredentials(username, password);
	}

	@When("I click login")
	public void i_click_login() {
	    loginPage.clickLogin();
	}

	@Then("I verify I am logged into the dashboard")
	public void i_verify_i_am_logged_into_the_dashboard() {
	    dashboardPage.verifyDashboardPage();
	}

	@Then("I verify invalid credentials error pop up")
	public void i_verify_invalid_credentials_error_pop_up() {
		assertTrue(loginPage.verifyInvalidCredentialsErrorMsg());
	}

	@When("I click logout")
	public void i_click_logout() {
	    panels.clickLogout();
	}

	@Then("I verify required error pop up")
	public void i_verify_required_error_pop_up() {
		assertTrue(loginPage.verifyRequiredErrorMsgs());
	}
	
	@When("I navigate back with the browser button")
	public void i_navigate_back_with_the_browser_button() {
	    loginPage.goBackAPage();
	}

	@Then("I verify I am not logged in")
	public void i_verify_i_am_not_logged_in() {
	    assertFalse(panels.verifyLoggedIn());
	}

}
