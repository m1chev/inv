@test1
Feature: Login tests http://inv.bg/login

  Background:
    Given user is at the Login page

  Scenario Outline: Bad Login
    When I enter username "<username>"
    And I enter password "<password>"
    And I press Login button
    Then login error message with text should be displayed "<error_message>"
    Examples:
      | username              | password | error_message                                               |
      |                       |          | Моля, попълнете вашия email                                 |
      | karamfilovs@gmail.com | 1234     | Грешно потребителско име или парола. Моля, опитайте отново. |
      | karamfilovs@gmail     | 123456   | Грешно потребителско име или парола. Моля, опитайте отново. |


  Scenario: Successful login
    When I enter username "karamfilovs@gmail.com"
    And I enter password "123456"
    And I press Login button
    Then user panel should contain text "karamfilovs@gmail.com"


