@Regression @Buzz
Feature: Submit Buzz post
	This feature verifies that the user can post on their Buzz feed.
	
Background: User has launched Chrome, navigated to OrangeHRM login page,
	and verified login
	Given I navigate to OrangeHRM login page "https://opensource-demo.orangehrmlive.com/"
	And I verify login page is visible
	And I login with username "Admin" and password "admin123"
	And I click on the Buzz button on the sidebar
	And I verify Buzz page is visible

Scenario: Verify Buzz post can be submitted and deleted when using a valid post and attachment
	When I enter a post "Just got back from my vacay in Fiji, miss the water there already"
	And I attach a file "src/test/resources/photos/water.png" and click share
	Then I verify post is visible
	And I delete post
	And I verify post is deleted

Scenario: Verify Buzz post cannot be submitted if character limited is exceeded
	When I enter the following text 'hello' 13107 times
	And I click post
	Then I verify excessive character error pops up
	And I verify post was not submitted with 'hello' 13107 times

Scenario: Verify Buzz post cannot be edited if the edit exceeds the character limt
	When I enter a post "Management here is mid"
	And I click post
	And I edit the post with the following text 'hello' 13107 times
	Then I verify excessive character error pops up

Scenario: Verify post can be commented and comment can be deleted
	When I comment on a post "I'm quitting and moving to Fiji"
	Then I verify comment is visible "I'm quitting and moving to Fiji"
	And I delete comment
	And I verify comment is deleted

Scenario: Verify Buzz post cannot be submitted when blank
	When I click post
	Then I verify no post was submitted