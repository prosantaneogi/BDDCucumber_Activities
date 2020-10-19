@ProjAct_7
Feature: Add multiple employees
Scenario Outline: Add multiple employees using an the Examples table
	Given Open the ​OrangeHRM​ page and login with credentials provided
	When Find the PIM option in the menu and click it.
	When Click the Add button to add a new Employee.
	And  Make sure the Create Login Details checkbox is checked.
	And Fill in the required fields "<FName>" and "<LName>" and "<UName>" and "<PWord>" and "<ConfirmPWord>" and click Save.
	Then Verify that the employees have been created with "<FName>" and "<LName>".
	And Close browser
Examples:
	|FName |LName |UName   |PWord   |ConfirmPWord|
	|Pro123|Neo123|Pro1Neo2|Pa$$w0rd|Pa$$w0rd    |
	|Pro321|Neo321|Pro2Neo2|Pa$$w0rd|Pa$$w0rd    |