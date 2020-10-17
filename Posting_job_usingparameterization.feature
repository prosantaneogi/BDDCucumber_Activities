@ProjAct_3
Feature: Posting a job using parameterization
Scenario: ​Post a job using details passed from the Feature file
	Given Open browser with ​Alchemy Jobs site​ and navigate to Post a Job page​
	And Read job information from the Feature file table and fill in the details
	  | abc1@gmail.com | SDET Tester 001 | KOLKATA   | Test job 1 | http://abc.com   | IBM India Pvt. Ltd. |
      | abc2@gmail.com | SDET Tester 002 | Bangalore | Test job 2 | https://abc1.com | IBM India Pvt. Ltd. |
	When Click submit
	Then Go to the Jobs page
	And Confirm job listing is shown on page
	And Close the Browser
