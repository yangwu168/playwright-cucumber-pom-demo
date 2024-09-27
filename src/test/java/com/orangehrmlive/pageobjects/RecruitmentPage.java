package com.orangehrmlive.pageobjects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrmlive.base.Base;

public class RecruitmentPage extends Base {
	public static final Logger log = LogManager.getLogger(RecruitmentPage.class);

	public RecruitmentPage(Page page) {
		this.page = page;
	}

	public boolean verifyRecruitmentPage() {
		Locator recruitmentHeader = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName("Recruitment"));
		assertThat(recruitmentHeader).isVisible();
		return lib.waitUntilElementVisible(recruitmentHeader);
	}

	public void clickAddButton() {
		Locator addButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add"));
		lib.clickButton(addButton);
	}

	public boolean verifyTableLoaded() {
		Locator table = page.locator("//*[@class='oxd-table-card']//div[3]/div").first();
		assertThat(table).isVisible();
		return lib.waitUntilElementVisible(table);
	}

	public boolean verifyCandidateAdded(String name) {
		List<Locator> candidateCells = page.locator("//*[@class='oxd-table-card']//div[3]/div").all();
		log.info(name);
		for (Locator cell : candidateCells) {
			lib.scrollIntoView(cell);
			log.info(cell.textContent());
			if (cell.textContent().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public int generateCandidateRow(String name) {
		List<Locator> candidateCells = page.locator("//*[@class='oxd-table-card']//div[3]/div").all();
		log.info(name);
		int count = 1;
		for (Locator cell : candidateCells) {
			lib.scrollIntoView(cell);
			log.info(cell.textContent());
			if (cell.textContent().equals(name)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public String downloadFile(int rowNum) {
		Locator downloadButton = page.locator("//*[@class='oxd-table-body']/div["
				+ rowNum + "]//div[7]//button[3]");
		Download download = page.waitForDownload(() -> {
			downloadButton.click();
		});
		log.info("Path: " + download.path().toString());
		log.info("Suggested File Name: " + download.suggestedFilename());
		String fileName = download.suggestedFilename();
		download.delete();
		return fileName;
	}
	
	public void verifyFileDownloaded() {
		
	}

}
