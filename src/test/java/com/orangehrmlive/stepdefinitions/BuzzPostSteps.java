package com.orangehrmlive.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrmlive.base.Base;
import com.orangehrmlive.pageobjects.BuzzPage;
import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.SideTopPanels;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuzzPostSteps extends Base {
	public static final Logger log = LogManager.getLogger(BuzzPostSteps.class);

	LoginPage loginPage = new LoginPage(Hooks.getPage());
	DashboardPage dashboardPage = new DashboardPage(Hooks.getPage());
	SideTopPanels panels = new SideTopPanels(Hooks.getPage());
	BuzzPage buzzPage = new BuzzPage(Hooks.getPage());

	@Given("I click on the Buzz button on the sidebar")
	public void i_click_on_the_buzz_button_on_the_sidebar() {
	    panels.clickBuzzButton();
	}
	
	@Given("I verify Buzz page is visible")
	public void i_verify_buzz_page_is_visible() {
		assertTrue(buzzPage.verifyBuzzPage());
	}

	@When("I enter a post {string}")
	public void i_enter_a_post(String post) {
	    buzzPage.enterBuzzPost(post);
	}

	@When("I attach a file {string} and click share")
	public void i_attach_a_file_and_click_share(String filePath) {
		buzzPage.attachFileAndClickShare(filePath);
	}
	
	@Then("I verify post is visible")
	public void i_verify_post_is_visible() {
		assertTrue(buzzPage.verifyPost());
	}
	
	@Then("I delete post")
	public void i_delete_post() {
	    buzzPage.deletePost();
	}
	
	@Then("I verify post is deleted")
	public void i_verify_post_is_deleted() {
		assertTrue(buzzPage.verifySuccessfullyDeletedToast());
	}

	@When("I enter the following text {string} {int} times")
	public void i_enter_the_following_text_times(String text, Integer amount) {
	    buzzPage.enterBuzzPost(text, amount);
	}
	
	@When("I click post")
	public void i_click_post() {
	    buzzPage.clickPost();
	}
	
	@Then("I verify excessive character error pops up")
	public void i_verify_excessive_character_error_pops_up() {
		assertTrue(buzzPage.verifyExcessiveCharactersErrorMsg());
	}

	@Then("I verify post was not submitted with {string} {int} times")
	public void verify_post_was_not_submitted_with_times(String text, Integer amount) {
	    assertTrue(buzzPage.verifyPostNotSubmitted(text, amount));
	}

	@When("I edit the post with the following text {string} {int} times")
	public void i_edit_the_post_with_the_following_text_times(String text, Integer amount) {
	    buzzPage.editPost(text, amount);
	}

	@When("I comment on a post {string}")
	public void i_comment_on_a_post(String comment) {
	    buzzPage.commentPost(comment);
	}
	
	@Then("I verify comment is visible {string}")
	public void i_verify_comment_is_visible(String comment) {
	    assertTrue(buzzPage.verifyComment(comment));
	}
	
	@Then("I delete comment")
	public void i_delete_comment() {
	    buzzPage.deleteComment();
	}
	
	@Then("I verify comment is deleted")
	public void i_verify_comment_is_deleted() {
	    buzzPage.verifySuccessfullyDeletedToast();
	}

	@Then("I verify no post was submitted")
	public void i_verify_no_post_was_submitted() {
	    assertTrue(buzzPage.verifyBlankPostNotSubmitted());
	}
	
}
