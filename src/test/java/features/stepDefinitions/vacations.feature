Feature: Vacation Planning Page
  Scenario: Clicking the 'Find My Vacation Spot' button returns results that match distance criteria
    Given I am on the vacation page using distance criteria
    When I vacation search with inputs '0' '100' '34.052235' '-118.243683' '10'
    Then I should see the cities that are no farther than '10'
