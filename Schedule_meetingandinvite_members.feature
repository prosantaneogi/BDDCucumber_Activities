@ProjAct_11
Feature: Schedule a meeting and invite members
Scenario Outline: Open the homepage and count the number of the dashlets on the page.
	Given Open the browser to the ​Alchemy CRM​ site and login.​
	When Navigate to Activities -> Meetings -> Schedule a Meeting.
	Then Enter the details of the meeting "<meetingname>"
	And Search for "<member1>" and "<member2>" and add them to the meeting
	And Click Save to finish Meeting.
	And Navigate to View Meetings page and confirm creation of the meeting "<meetingname>".
	And Close the open browser.
Examples:
|meetingname  |member1      |member2      |
|SDET Meeting |invitees_add_2|invitees_add_3  |	