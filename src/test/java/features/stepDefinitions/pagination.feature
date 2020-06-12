Feature: Pagination
 Scenario: Clicking the 'Find My Vacation Spot' button returns results with pagination
  Given I am on the vacation page
  When I vacations search with inputs '0' '100' '34.052235' '-118.243683' '10'
  Then I should see paginated results
 Scenario: Clicking another button returns the next page
 	Given I am on the vacation page 
 	When I vacations search with inputs '0' '100' '34.052235' '-118.243683' '10'
 	And I click on another button
 	Then I see the next page





