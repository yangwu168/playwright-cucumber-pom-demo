package com.orangehrmlive.utility;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PlaywrightLibrary extends JavaHelperLibrary {

	public static final Logger log = LogManager.getLogger(PlaywrightLibrary.class);
	
	private Page page;
	
	public PlaywrightLibrary(Page page) {
		this.page = page;
	}
	
	public void getWebsite(String url) {
		try {
			page.navigate(url);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void enterTextByFill(Locator locator, String text) {
		try {
			locator.clear();
			locator.fill(text);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void enterTextByFillWithoutClear(Locator locator, String text) {
		try {
			locator.fill(text);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void enterTextByKeystroke(String keyInput) {
		try {
			page.keyboard().press(keyInput);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void enterKeyboardInput(Locator locator, String keyInput) {
		try {
			locator.clear();
			locator.press(keyInput);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void clickButton(Locator locator) {
		try {
			locator.click();
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public boolean waitUntilElementVisible(Locator locator) {
		boolean elementVisible = false;
		try {
			elementVisible = locator.isVisible();
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
		return elementVisible;
	}
	
	public void navigateBrowserBack() {
		try {
			page.goBack();
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public void refreshPage() {
		try {
			page.reload();
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public String generateTableCellLocator(int row, int col) {
		String xpathLocator = null;
		try {
		xpathLocator = "//tbody/tr[" + String.valueOf(row) + "]/td[" + String.valueOf(col) + "]";
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
		return xpathLocator;
	}
	
	public void scrollIntoView(Locator locator) {
		try {
			locator.scrollIntoViewIfNeeded();
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public boolean verifyElementText(Locator locator, String expectedText) {
		boolean isEqual = false;
		try {
			isEqual = locator.textContent().equals(expectedText);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
		return isEqual;
	}
	
	public boolean verifyElementTextContains(Locator locator, String expectedText) {
		boolean isContained = false;
		try {
			isContained = locator.textContent().contains(expectedText);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
		return isContained;
	}
	
	public void uploadFile(Locator locator, String filePath) {
		try {
			locator.setInputFiles(Paths.get(filePath));
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
}
