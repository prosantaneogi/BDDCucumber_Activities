@ProjAct_6
Feature: Adding a candidate for recruitment
Scenario: Add information about a candidate for recruitment
	Given Open the ​OrangeHRM​ page and login with credentials provided
	When Navigate to the Recruitment page
	When click on the Add button to add candidateinformation
	And  fill in the details of the candidate
	And Upload a resume (docx or pdf) to the form
	And Click Save
	Then Navigate back to the Recruitments page to confirm candidate entry
	And Close browser