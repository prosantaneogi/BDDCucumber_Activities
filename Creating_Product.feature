@ProjAct_12
Feature: Creating a Product
Scenario Outline: To use an Examples table to add products.
	Given Open the browser to the ​Alchemy CRM​ site and login.​
	When Navigate to All -> Products-> Create Product.
	Then Enter the details of the "<Products>"
	And Click Save to finish.
	And Go to the View Products page to see all "<Products>"
	And Close the open browser.
	Examples:
	|   Products  |
	|Product 0001 |