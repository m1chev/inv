@test @login
Feature: Login tests http://inv.bg/login

  Background:
    Given user is at the Login page

  Scenario Outline: Cant login with invalid credentials
    When I enter username "<username>" and password "<password>" and click Login button
    Then login error message with text should be displayed "<error_message>"
    Examples:
      | username              | password | error_message                                               |
      |                       |          | Моля, попълнете вашия email                                 |
      | karamfilovs@gmail.com | 1234     | Грешно потребителско име или парола. Моля, опитайте отново. |
      | karamfilovs@gmail     | 123456   | Грешно потребителско име или парола. Моля, опитайте отново. |


  Scenario: Can login with valid credentials
    When I enter username "karamfilovs@gmail.com"
    And I enter password "123456"
    And I press Login button
    Then user panel should contain text "karamfilovs@gmail.com"


  Scenario: Can login successfully
    When I enter username "karamfilovs@gmail.com" and password "123456" and click Login button
    Then user panel should contain text "karamfilovs@gmail.com"


