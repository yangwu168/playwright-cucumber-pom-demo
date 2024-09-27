package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class CandidateProfilePage extends Base {
	public static final Logger log = LogManager.getLogger(CandidateProfilePage.class);

	public CandidateProfilePage(Page page) {
		this.page = page;
	}

	public boolean verifyCandidateProfilePage() {
		Locator candidateProfileHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Candidate Profile"));
		assertThat(candidateProfileHeader).isVisible();
		return lib.waitUntilElementVisible(candidateProfileHeader);
	}
	
	public void clickCandidatesButton() {
		Locator candidatesButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Candidates"));
		lib.clickButton(candidatesButton);
	}
}
