package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class BuzzPage extends Base {
	public static final Logger log = LogManager.getLogger(BuzzPage.class);

	public BuzzPage(Page page) {
		this.page = page;
	}

	public boolean verifyBuzzPage() {
		Locator buzzNewsfeedHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Buzz"));
		assertThat(buzzNewsfeedHeader).isVisible();
		return lib.waitUntilElementVisible(buzzNewsfeedHeader);
	}

	public void enterBuzzPost(String post) {
		Locator postInput = page.getByPlaceholder("What's on your mind?");
		lib.enterTextByFill(postInput, post);
	}

	public void enterBuzzPost(String text, Integer amount) {
		Locator postInput = page.getByPlaceholder("What's on your mind?");
		String fullText = buildString(text, amount);
		lib.enterTextByFill(postInput, fullText);
	}

	public void attachFileAndClickShare(String filePath) {
		Locator sharePhotos = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Share Photos"));
		lib.clickButton(sharePhotos);
		page.onFileChooser(fileChooser -> {
			fileChooser.setFiles(Paths.get(filePath));
		});
		lib.clickButton(page.getByText("Add Photos"));
		lib.clickButton(page.getByText("Share", new Page.GetByTextOptions().setExact(true)));
//		Locator file = page.locator("//input[@type='file']");
//		assertThat(file).isVisible();
//		lib.uploadFile(file, filePath);
//		Locator shareButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Share").setExact(true));
//		lib.clickButton(page.getByText("Share", new Page.GetByTextOptions().setExact(true)));
//		Locator image = page.locator("//div[@class='orangehrm-buzz-photos-item']").first();
//		assertThat(image).isVisible();
	}

	public boolean verifyPost() {
		lib.refreshPage();
		Locator post = page.locator("//div[@class='orangehrm-buzz-post-body']/p").first();
		assertThat(post).isVisible();
		return lib.verifyElementText(post, "Just got back from my vacay in Fiji, miss the water there already");
	}

	public void deletePost() {
		Locator ellipsisButton = page.locator("//button[@class='oxd-icon-button']").nth(1);
		lib.clickButton(ellipsisButton);
		Locator deletePost = page.getByText("Delete Post");
		lib.clickButton(deletePost);
		Locator yesDeleteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, Delete"));
		lib.clickButton(yesDeleteButton);
	}

	public boolean verifySuccessfullyDeletedToast() {
		Locator succesfullyDeletedToast = page.getByText("Successfully Deleted");
		assertThat(succesfullyDeletedToast).isVisible();
		return lib.verifyElementText(succesfullyDeletedToast, "Successfully Deleted");
	}

	public void clickPost() {
		Locator postButton = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Post").setExact(true));
		lib.clickButton(postButton);
	}

	public boolean verifyExcessiveCharactersErrorMsg() {
		Locator excessCharsError = page.getByText("Should not exceed 65530 characters");
		assertThat(excessCharsError).isVisible();
		return lib.waitUntilElementVisible(excessCharsError);
	}

	public boolean verifyPostNotSubmitted(String text, Integer amount) {
		String fullText = buildString(text, amount);
		Locator post = page.locator("//div[@class='orangehrm-buzz-post-body']/p").first();
		assertThat(post).isVisible();
		return !lib.verifyElementText(post, fullText);
	}

	public void editPost(String text, Integer amount) {
		String fullText = buildString(text, amount);
		Locator ellipsisButton = page.locator("//button[@class='oxd-icon-button']").nth(1);
		lib.clickButton(ellipsisButton);
		Locator editPost = page.getByText("Edit Post");
		lib.clickButton(editPost);
		Locator editPostInput = page.locator("//textarea[@class='oxd-buzz-post-input']").last();
		lib.enterTextByFill(editPostInput, fullText);
	}
	
	public void commentPost(String comment) {
		Locator commentButton = page.locator("//*[@class='oxd-icon bi-chat-text-fill']").first();
		lib.clickButton(commentButton);
		Locator commentInput = page.getByPlaceholder("Write your comment...");
		lib.enterTextByFill(commentInput, comment);
		lib.enterTextByKeystroke("Enter");
	}
	
	public boolean verifyComment(String comment) {
		Locator commentElement = page.getByText(comment);
		assertThat(commentElement).isVisible();
		return lib.verifyElementTextContains(commentElement, comment);
	}
	
	public void deleteComment() {
		Locator deleteComment = page.getByText("Delete");
		lib.clickButton(deleteComment);
		Locator yesDeleteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, Delete"));
		lib.clickButton(yesDeleteButton);
	}

	public boolean verifyBlankPostNotSubmitted() {
		List<Locator> posts = page.locator("//*[@class='oxd-grid-1 orangehrm-buzz-newsfeed-posts']/div[@class='oxd-grid-item oxd-grid-item--gutters']").all();
		for(Locator post : posts) {
			if(post.textContent().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public String buildString(String text, Integer amount) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < amount; i++) {
			string.append(text);
		}
		log.info("Partial Text: " + text + "; Amount To Be Copied: " + amount);
		log.info("Full Text: " + string);
		return string.toString();
	}

}
