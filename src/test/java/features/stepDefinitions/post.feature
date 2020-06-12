Feature: Web application uses POST to send data to the controllers (back-end)

  Scenario: No data should be sent through the URL
    Given I am on the Activity Page
    When I search for the weather
    Then I do not see my parameters passed in the URL


