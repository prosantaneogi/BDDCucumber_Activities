@ProjAct_1
Feature: Create a new user
Scenario: Visit the sites backend and create a new user
	Given Open a browser and Navigate to Job Board​
	And log in with "root" and "pa$$w0rd"
	When Locate the left hand menu and click the menu item that says Users
	Then Locate the Add New button and click it.
	And Fill in the necessary details.
	And Click the Add New User button.
	And Verify that the user was created
	And Close the Browser