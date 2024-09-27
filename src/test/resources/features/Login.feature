@Regression @Login
Feature: Login to OrangeHRM website
	User should be able to login to the OrangeHRM portal
	
Background: User has launched Chrome, navigated to OrangeHRM login page,
	and verified login
	Given I navigate to OrangeHRM login page "https://opensource-demo.orangehrmlive.com/"
	And I verify login page is visible
	
Scenario: Verify login with valid username and password
	When I enter username as "Admin" and password as "admin123"
	And I click login
	Then I verify I am logged into the dashboard
	
Scenario: Verify login with invalid username and invalid password
	When I enter username as "asbk12%^@" and password as "g1bb3rish"
	And I click login
	Then I verify invalid credentials error pop up
	And I verify login page is visible
	
Scenario: Verify login with invalid username and valid password
	When I enter username as "Admin" and password as "Admin123"
	And I click login
	Then I verify invalid credentials error pop up
	And I verify login page is visible
@Bug
Scenario: Verify login with valid username and invalid password
	When I enter username as "admin" and password as "admin123"
	And I click login
	Then I verify invalid credentials error pop up
	And I verify login page is visible

Scenario: Verify login with blank username and blank password
	When I click login
	Then I verify required error pop up
	And I verify login page is visible
@Bug
Scenario: Verify navigating with the browser back button after logging out keeps user logged out
	When I enter username as "Admin" and password as "admin123"
	And I click login
	And I verify I am logged into the dashboard
	And I click logout
	And I verify login page is visible
	And I navigate back with the browser button
	Then I verify I am not logged in