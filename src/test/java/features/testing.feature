Feature: This is my first testing feature file

  Scenario: Can login with valid credentials
    Given user is at the Login page
    When I enter username "myemail@gmail.com"
    And I enter password "mypassword"
    And I press Login button
    Then user panel should contain text "myemail@gmail.com"
