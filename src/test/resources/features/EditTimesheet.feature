@Regression @Timesheet
Feature: Edit personal timesheet
	This feature verifies that the user can edit and submit their personal timesheet.
	
Background: User has launched Chrome, navigated to OrangeHRM login page,
	and verified login
	Given I navigate to OrangeHRM login page "https://opensource-demo.orangehrmlive.com/"
	And I verify login page is visible
	And I login with username "Admin" and password "admin123"
	And I click on the Time button on the sidebar
	And I verify time page is visible
	When I click on my personal timesheets
	And I verify my timesheet page is visible
	And I click edit
	And I verify edit timesheet page is visible

Scenario: Verify timesheet can be saved and submitted when inputting valid project
	name, activity, and hours
	When I enter project name as "Apache Software"
	And I select "Bug Fixes" activity
	And I input "10:00" on Monday
	And I click save
	Then I verify my timesheet and total hours are correct
	And I click submit and verify timesheet is submitted
	
Scenario: Verify errors pop up when timesheet is saved with invalid project
	name and valid hours
	When I enter project name as "fakeProject123"
	And I input "10:00" on Monday
	And I click save
	Then I verify select a project error pops up
	And I verify select an activity error pops up
	And I verify edit timesheet page is visible

Scenario: Verify errors pop up when timesheet is saved with valid project
	name but is then edited to be invalid
	When I enter project name as "Apache Software"
	And I select "Bug Fixes" activity
	And I input "10:00" on Monday
	And I edit project name to "fakeProject123"
	And I click save
	Then I verify select a project error pops up
	And I verify select an activity error pops up
	And I verify edit timesheet page is visible

Scenario: Verify error pops up when timesheet is saved with valid project
	name and hours but no activity is selected
	When I enter project name as "Apache Software"
	And I input "10:00" on Monday
	And I click save
	Then I verify select an activity error pops up
	And I verify edit timesheet page is visible

Scenario: Verify errors pop up when timesheet is saved with valid project
	name and activity but an invalid amount of hours
	When I enter project name as "Apache Software"
	And I select "Bug Fixes" activity
	And I input "25:00" on Monday
	And I click save
	Then I verify invalid hours or format error
	And I verify edit timesheet page is visible
