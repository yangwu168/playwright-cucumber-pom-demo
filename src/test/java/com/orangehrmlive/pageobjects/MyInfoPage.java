package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class MyInfoPage extends Base {
	public static final Logger log = LogManager.getLogger(MyInfoPage.class);

	public MyInfoPage(Page page) {
		this.page = page;
	}

	public boolean verifyMyInfoPage() {
		Locator myInfoHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Personal Details"));
		assertThat(myInfoHeader).isVisible();
		return lib.waitUntilElementVisible(myInfoHeader);
	}
	
	public void updateEmployeeName(String firstName, String lastName) {
		Locator firstNameInput = page.locator("//*[@name='firstName']");
		lib.enterTextByFill(firstNameInput, firstName);
		Locator lastNameInput = page.locator("//*[@name='lastName']");
		lib.enterTextByFill(lastNameInput, lastName);
	}
	
	public void clickSaveButton() {
		Locator saveButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));
		lib.clickButton(saveButton);
	}
	
	public boolean verifyUpdatedName(String fullName) {
		Locator profileDropdownName = page.locator("//*[@class='oxd-userdropdown-name']");
		log.info("Expected Full Name: " + fullName);
		log.info("Actual Full Name: " + profileDropdownName.textContent());
		return lib.verifyElementText(profileDropdownName, fullName);
	}
	
	public void updateDOB(String date) {
		Locator dobInput = page.getByPlaceholder("yyyy-dd-mm").last();
		lib.enterTextByFill(dobInput, date);
	}
	
	public boolean verifyFutureDOBErrorMsg() {
		boolean isVisible = false;
		Locator futureDOBError = page.getByText("DOB should not be in the future!");
		assertThat(futureDOBError);
		isVisible = lib.waitUntilElementVisible(futureDOBError);
		return isVisible;
	}
	
	public boolean verifyInvalidDOBFormatErrorMsg() {
		boolean isVisible = false;
		Locator invalidDOBFormatError = page.getByText("Should be a valid date in yyyy-dd-mm format");
		assertThat(invalidDOBFormatError);
		isVisible = lib.waitUntilElementVisible(invalidDOBFormatError);
		return isVisible;
	}
	
	public void clickProfilePhoto() {
		Locator profilePhoto = page.locator("//img[@class='employee-image']");
		lib.clickButton(profilePhoto);
	}
}
