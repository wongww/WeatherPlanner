Feature: Activity Planning Page

  Scenario: Clicking the 'Find My Activity Spot' button returns results that match distance criteria 
    Given I am on the activities page 
    When I activity search with inputs 'soccer' '34.052235' '-118.243683' '10' 
    Then I should see the cities that are no farther than '10'