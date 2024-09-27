package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class AddCandidatePage extends Base {
	public static final Logger log = LogManager.getLogger(AddCandidatePage.class);

	public AddCandidatePage(Page page) {
		this.page = page;
	}

	public boolean verifyAddCandidatePage() {
		Locator addCandidateHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Add Candidate"));
		assertThat(addCandidateHeader).isVisible();
		return lib.waitUntilElementVisible(addCandidateHeader);
	}
	
	public void fillOutCandidateDetails(String firstName, String lastName, String email) {
		page.pause();
		Locator firstNameInput = page.locator("//input[@name='firstName']");
		lib.enterTextByFill(firstNameInput, firstName);
		Locator lastNameInput = page.locator("//input[@name='lastName']");
		lib.enterTextByFill(lastNameInput, lastName);
		Locator vacancySelect = page.locator("//*[@class='oxd-select-wrapper']");
		lib.clickButton(vacancySelect);
		Locator jobSelectOption = page.getByText("Sales Representative");
		lib.scrollIntoView(jobSelectOption);
		lib.clickButton(jobSelectOption);
		Locator emailInput = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
		lib.enterTextByFill(emailInput, email + "@gmail.com");
	}
	
	public void clickSaveButton() {
		Locator saveButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));
		lib.clickButton(saveButton);
	}
	
	public void enterDate(String date) {
		Locator dateInput = page.getByPlaceholder("yyyy-dd-mm");
		lib.enterTextByFill(dateInput, date);
	}
	
	public boolean verifyWhitespaceBeforeFirstCharErrorMsg() {
		boolean isVisible = false;
		Locator whitespaceError = page.getByText("Whitespace before first character in name!");
		assertThat(whitespaceError);
		isVisible = lib.waitUntilElementVisible(whitespaceError);
		return isVisible;
	}
	
	public boolean verifyShouldBeCurrentOrPreviousDateErrorMsg() {
		Locator dateError = page.getByText("Should be the current date or a previous date");
		assertThat(dateError).isVisible();
		return lib.waitUntilElementVisible(dateError);
	}
	
	public boolean verifyVacancyErrorMsg() {
		boolean isVisible = false;
		Locator vacancyError = page.getByText("Must select a vacancy!");
		assertThat(vacancyError);
		isVisible = lib.waitUntilElementVisible(vacancyError);
		return isVisible;
	}
	
	public void uploadFile(String filePath, String fileVerification) {
		page.onFileChooser(fileChooser -> {
			fileChooser.setFiles(Paths.get(filePath));
		});
		lib.clickButton(page.locator("//*[@class='oxd-file-button']"));
		Locator fileUploadText = page.getByText(fileVerification);
		assertThat(fileUploadText).isVisible();
	}
	
}
