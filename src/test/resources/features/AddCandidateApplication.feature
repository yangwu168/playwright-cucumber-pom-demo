@Regression @Recruitment
Feature: Add candidate application to internal records
	This feature verifies that the user can submit candidate applications and have it listed
	in the portal.
	
Background: User has launched Chrome, navigated to OrangeHRM login page,
	and verified login
	Given I navigate to OrangeHRM login page "https://opensource-demo.orangehrmlive.com/"
	And I verify login page is visible
	And I login with username "Admin" and password "admin123"
	And I click on the Recruitment button on the sidebar
	And I verify Recruitment page is visible
	And I click Add button
	And I verify Add Candidate page is visible
	
Scenario: Verify candidate application is unable to be added when using invalid date of application
	When I enter candidate details "First" as first name and "Last" as last name and "test123" as email
	And I enter an invalid date of application "2030-08-08"
	Then I verify current date or previous date error
@Bug
Scenario: Verify candidate application is unable to be added when using invalid first and last names
	When I enter candidate details "     Slim" as first name and "        Shady" as last name and "therealslimshady" as email
	Then I verify whitespace before first character error
@Bug
Scenario: Verify candidate application is unable to be added when vacancy is left blank
	When I enter candidate details "First" as first name and "Last" as last name and "test123" as email
	And I click save to submit candidate application
	Then I verify vacancy error after I click save
@Active
Scenario: Verify valid resume is uploaded to candidate application and can be downloaded from the records
	When I enter candidate details "First" as first name and "Last" as last name and "test123" as email
	And I upload resume "src/test/resources/testData/pdf-sample.pdf" by verifying it is displayed on screen "pdf-sample.pdf"
	And I click save to submit candidate application
	And I click on the Recruitment button on the sidebar
	And I download the file associated with the candidate "First" "Last"
	Then I verify the file is the same as the one uploaded "pdf-sample.pdf"