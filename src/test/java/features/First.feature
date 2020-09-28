Feature: Login feature

  Background:
    Given I am at the Login page

  Scenario: Can login with valid credentials
    When I enter email "karamfilovs@gmail.com"
    And I enter password "123456"
    And I press Login button
    Then user panel should contain text "karamfilovs@gmail.com"


  Scenario Outline: Cant login with bad/invalid credentials
    When I enter username "<username>" and password "<password>" and click Login button
    Then login error message with text should be displayed "<expected_error_message>"

    Examples:
      | username              | password | expected_error_message                                      |
      |                       |          | Моля, попълнете вашия email                                 |
      | karamfilovs@gmail.com | 12345    | Грешно потребителско име или парола. Моля, опитайте отново. |
      | alex@pragmatic.bg     | 123456   | Грешно потребителско име или парола. Моля, опитайте отново. |


  Scenario: Can reset password
    When I click reset password link
    Then Forgotten password page title is "Възстановяване на парола"
    When I enter email "karamfilovs@gmail.com" for forgotten password restore
    And I click send button

  Scenario Outline: Cant reset password with invalid email
    When I click reset password link
    Then Forgotten password page title is "Възстановяване на парола"
    When I enter email "<email>" for forgotten password restore
    And I click send button
    Then error message with text "<error_message>" is displayed

    Examples:
      | email             | error_message                                                     |
      |                   | Въведеният имейл е невалиден                                      |
      | alex@pragmatic    | Въведеният имейл е невалиден                                      |
      | alex@pragmatic.bg | Грешно потребителско име или e-mail адрес. Моля, опитайте отново. |
