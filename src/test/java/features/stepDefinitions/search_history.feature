Feature: Search History

  Scenario: Searching on the Homepage
    Given I am on the HomePage, using https
    When I search 'Los Angeles'
    Then I should see 'Los Angeles' in the list
         
Scenario: Search a nonexistent city
    Given I am on the HomePage, using https
    When I search 'notacity'
    Then I should not see 'notacity' in the list
 
 Scenario: Searching on the Homepage 4 times
	Given I am on the HomePage, using https
    When I search 'Reno' 'San Diego' 'Seattle' 'Portland'
    Then I should see 'Reno' 'San Diego' 'Seattle' 'Portland' in the list
    
Scenario: Searching on the Homepage for city already in the list
    Given I am on the HomePage, using https
    When I search 'Seattle' 
    Then I should see 'Seattle' in the list     
    
 Scenario: Searching on the Homepage with full list
    Given I am on the HomePage, using https
    When I search 'Chicago'
    Then I should see 'San Diego' 'Seattle' 'Portland' 'Chicago' in the list       
    
Scenario: Closing the browser and seeing your search results still, based on the previous scenario
    Given I am on the HomePage, using https
    When I close the browser
    Then I should see 'San Diego' 'Seattle' 'Portland' 'Chicago' in the list   
    
Scenario: Clicking on a button
    Given I am on the HomePage, using https
    When I click 'Chicago' 
    Then I should see 'Chicago' in result   
    
