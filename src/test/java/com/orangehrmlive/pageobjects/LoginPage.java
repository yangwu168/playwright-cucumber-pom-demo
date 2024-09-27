package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.orangehrmlive.base.Base;

public class LoginPage extends Base {
	public static final Logger log = LogManager.getLogger(LoginPage.class);
	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	public void navigateToURL(String url) {
		lib.getWebsite(url);
	}
	
	public boolean verifyLoginPage() {
		Locator login = page.locator("//*[text()='Username : Admin']");
		assertThat(login).isVisible();
		return lib.waitUntilElementVisible(login);
	}
	
	public void enterLoginCredentials(String username, String password) {
		lib.enterTextByFill(page.locator("//input[@name='username']"), username);
		lib.enterTextByFill(page.locator("//input[@name='password']"), password);
	}
	
	public void clickLogin() {
		lib.clickButton(page.locator("//button[@type='submit']"));
	}
	
	public boolean verifyInvalidCredentialsErrorMsg() {
		Locator invalidCredentialsError = page.locator("//p[text()='Invalid credentials']");
		assertThat(invalidCredentialsError).isVisible();
		return lib.waitUntilElementVisible(invalidCredentialsError);
	}
	
	public boolean verifyRequiredErrorMsgs() {
		Locator requiredUsernameError = page.getByText("Required").first();
		assertThat(requiredUsernameError).isVisible();
		Locator requiredPasswordError = page.getByText("Required").last();
		assertThat(requiredPasswordError).isVisible();
		return lib.waitUntilElementVisible(requiredUsernameError) && lib.waitUntilElementVisible(requiredPasswordError);
	}
	
	public void goBackAPage() {
		lib.navigateBrowserBack();
	}
}
