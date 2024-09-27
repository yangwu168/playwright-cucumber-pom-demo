package com.orangehrmlive.tests;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.orangehrmlive.base.Base;
import com.orangehrmlive.pageobjects.AddCandidatePage;
import com.orangehrmlive.pageobjects.CandidateProfilePage;
import com.orangehrmlive.pageobjects.DashboardPage;
import com.orangehrmlive.pageobjects.LoginPage;
import com.orangehrmlive.pageobjects.RecruitmentPage;
import com.orangehrmlive.pageobjects.SideTopPanels;
import com.orangehrmlive.utility.DatabaseManager;
import com.orangehrmlive.utility.PropertiesManager;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class AddCandidateAppsTest extends Base {
	public static final Logger log = LogManager.getLogger(AddCandidateAppsTest.class);
	
	private static String username;
	private static String password;
	
	@BeforeClass
	public static void getLoginCredentials() {
		PropertiesManager prop = new PropertiesManager("src/test/resources/ddt.properties");
		username = prop.readProperty("loginUsername");
		password = prop.readProperty("loginPassword");
		log.info("Login Username: " + username);
		log.info("Login Password: " + password);
	}
	
	@Before
	public void setUp() {
		page = initDriver();
	}
	
	@After
	public void tearDown() {
		tearDownTest();
	}
	
	@DataProvider
	public static Object[][] hrData() {
		Object[][] data = null;
		PropertiesManager prop = new PropertiesManager("src/test/resources/ddt.properties");
		String hostURL = prop.readProperty("hostURL");
		String username = prop.readProperty("databaseUsername");
		String password = prop.readProperty("databasePassword");
		DatabaseManager db = new DatabaseManager(hostURL, username, password);
		String query = "select * from employees where employee_id < 106";
		data = db.getDatabaseData(db.getCon(), query);
		return data;
	}
	
	@Test
	@UseDataProvider("hrData")
	public void addCandidateApplicationToRecords(String employeeID, String firstName, String lastName,
			String email, String phone, String date, String jobID, String salary,
			String commissionPct, String managerID, String departmentID) {
		LoginPage loginPage = new LoginPage(page);
		loginPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		assertTrue(loginPage.verifyLoginPage());
		loginPage.enterLoginCredentials(username, password);
		loginPage.clickLogin();
		DashboardPage dashboardPage = new DashboardPage(page);
		assertTrue(dashboardPage.verifyDashboardPage());
		SideTopPanels panels = new SideTopPanels(page);
		panels.clickRecruitmentButton();
		RecruitmentPage recruitmentPage = new RecruitmentPage(page);
		assertTrue(recruitmentPage.verifyRecruitmentPage());
		recruitmentPage.clickAddButton();
		AddCandidatePage addCandidatePage = new AddCandidatePage(page);
		assertTrue(addCandidatePage.verifyAddCandidatePage());
		addCandidatePage.fillOutCandidateDetails(firstName, lastName, email);
		addCandidatePage.clickSaveButton();
		CandidateProfilePage candidateProfilePage = new CandidateProfilePage(page);
		assertTrue(candidateProfilePage.verifyCandidateProfilePage());
		candidateProfilePage.clickCandidatesButton();
		assertTrue(recruitmentPage.verifyRecruitmentPage());
		assertTrue(recruitmentPage.verifyTableLoaded());
		assertTrue(recruitmentPage.verifyCandidateAdded(firstName + "  " + lastName));
	}
}
