Feature: The like button likes cities 
  Scenario: Liking a city on Acitivity Planning Page
    Given I am on the Activity Planning Page and I have cities from the search results
    When I click the like button of a city on the Activity Planning Page
    Then the number of likes for that city should increase by one on the Activity Planning Page

  Scenario: Liking a city on Vacation Planning Page
    Given I am on the Vacation Planning Page and I have cities from the search results
    When I click the like button of a city on the Vacation Planning Page
    Then the number of likes for that city should increase by one on the Vacation Planning Page

  Scenario: Disliking a city on Vacation Planning Page
    Given I am on the Vacation Planning Page and I have cities from the search results
    When I click the dislike button of a city
    Then the number of likes for that city should decrease by one

  Scenario: Disliking a city on Activity Planning Page
    Given I am on the Activity Planning Page and I have cities from the search results
    When I click the dislike button of a city
    Then the number of likes for that city should decrease by one

  Scenario: Sorting by likes on Vacation Planning Page
    Given I am on the Vacation Planning Page and I have cities from the search results
    When I click likes
    Then cities should be displayed in decreasing order of likes

  Scenario: Sorting by likes on Activity Planning Page
    Given I am on the Activity Planning Page and I have cities from the search results
	When I click likes
	Then cities should be displayed in decreasing order of likes
