package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class ChangeProfilePicPage extends Base {
	public static final Logger log = LogManager.getLogger(MyInfoPage.class);

	public ChangeProfilePicPage(Page page) {
		this.page = page;
	}

	public boolean verifyChangeProfilePicPage() {
		Locator changeProfilePicHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Change Profile Picture"));
		assertThat(changeProfilePicHeader).isVisible();
		return lib.waitUntilElementVisible(changeProfilePicHeader);
	}
	
	public void uploadPhoto(String filePath) {
		page.onFileChooser(fileChooser -> {
			fileChooser.setFiles(Paths.get(filePath));
		});
		lib.clickButton(page.locator("//button[@type='button']").last());
	}
	
	public boolean verifySuccessfullyUpdatedToast() {
		Locator succesfullyDeletedToast = page.getByText("Successfully Updated");
		assertThat(succesfullyDeletedToast).isVisible();
		return lib.verifyElementText(succesfullyDeletedToast, "Successfully Updated");
	}
	
	public boolean verifyImageRequiredErrorMsg() {
		Locator imageRequiredError = page.getByText("Required");
		assertThat(imageRequiredError).isVisible();
		return lib.waitUntilElementVisible(imageRequiredError);
	}
}
