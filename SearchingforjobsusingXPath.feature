@ProjAct_2
Feature: Searching for jobs using XPath
Scenario: ​Searching for jobs and applying to them using XPath
	Given Open browser with ​Alchemy Jobs site​ and navigate to Jobs page​
	And Find the Keywords search input field.
	When Type in keywords to search for jobs and change the Job type
	Then Find the filter using XPath and filter job type to show only “Full Time” jobs
	And Find a job listing using XPath and it to see job details.
	And Find the title of the job listing using XPath and print it to the console.
	And Find and Click on the “Apply for job” button.
	And Close the Browser