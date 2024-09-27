@Regression @Profile
Feature: Edit profile
	This feature verifies that the user can edit their personal profile details
	
Background: User has launched Chrome, navigated to OrangeHRM login page,
	and verified login
	Given I navigate to OrangeHRM login page "https://opensource-demo.orangehrmlive.com/"
	And I verify login page is visible
	And I login with username "Admin" and password "admin123"
	And I click on the My Info button on the sidebar
	And I verify My Info page is visible
@Bug
Scenario: Verify profile updates when name is changed
	When I update employee full name to "First" "Last"
	And I click save on the my info page
	Then I verify name is updated in profile to "First" "Last"
@Bug
Scenario: Verify profile DOB cannot be changed to a date in the future
	When I update DOB to "2400-12-12"
	And I click save on the my info page
	Then I verify DOB should not be in the future error

Scenario: Verify invalid DOB format cannot be used in profile DOB
	When I update DOB to "30000-01-01"
	And I click save on the my info page
	Then I verify invalid DOB format error

Scenario: Verify profile photo is updated when valid photo is uploaded
	When I click on profile photo
	And I upload a new profile photo "src/test/resources/photos/trump.png"
	And I click save on the my info page
	Then I verify profile photo is updated

@Active
Scenario: Verify invalid photo is not loaded for profile photo
	When I click on profile photo
	And I upload a new profile photo "src/test/resources/testData/resume.txt"
	And I click save on the my info page
	Then I verify image required error