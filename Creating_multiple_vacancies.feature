@ProjAct_8
Feature: Creating multiple vacancies
Scenario Outline: Creating multiple vacancies using data from an Examples table
	Given Open the ​OrangeHRM​ page and login with credentials provided
	When Navigate to the Recruitment page
	When Click on the Vacancies menu item to navigate to the vacancies page
	And Click on the Add button to navigate to the Add Job Vacancy form
	And Fill out the necessary details using "<vacName>"
	And Click the Save button to save the vacancy
	Then Verify that "<vacName>" the vacancies have been successfully created.
	And Close browser
Examples:
|       vacName               |
|Vacancy for SDET Tester      |
|Vacancy for Automation Tester|
