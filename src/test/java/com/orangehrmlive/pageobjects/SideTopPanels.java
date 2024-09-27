package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class SideTopPanels extends Base {
	public static final Logger log = LogManager.getLogger(SideTopPanels.class);
	
	public SideTopPanels(Page page) {
		this.page = page;
	}
	
	public void clickLogout() {
		Locator profile = page.locator("//*[@class='oxd-userdropdown-name']");
		lib.clickButton(profile);
		Locator logout = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Logout"));
		assertThat(logout).isVisible();
		lib.clickButton(logout);
	}
	
	public boolean verifyLoggedIn() {
		Locator profile = page.locator("//*[@class='oxd-userdropdown-name']");
		assertThat(profile).isVisible();
		return lib.waitUntilElementVisible(profile);
	}
	
	public void clickTimeButton() {
		Locator timeButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Time"));
		lib.clickButton(timeButton);
	}
	
	public void clickBuzzButton() {
		Locator buzzButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Buzz"));
		lib.clickButton(buzzButton);
	}
	
	public void clickRecruitmentButton() {
		Locator recruitmentButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Recruitment"));
		lib.clickButton(recruitmentButton);
	}
	
	public void clickMyInfo() {
		Locator myInfoButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Info"));
		lib.clickButton(myInfoButton);
	}
}
