@ProjAct_10
Feature: Create leads using parameterization
Scenario: Open the Leads page and add multiple lead accounts using values passed fromFeature file
	Given Open the browser to the ​Alchemy CRM​ site and login.​
	When Navigate to Sales -> Leads -> Create Lead
	Then Fill in the necessary details to create lead accounts using "fname" and "lname"
	And Click Save to finish.
	And Navigate to the View Leads page to see lead "fname lname"
	And Close the open browser.