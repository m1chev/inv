@cashbox
Feature: CashBox tests http://inv.bg/cashbox

  Background:
    Given user is logged in the system
    Then user panel should contain text "karamfilovs@gmail.com"

  Scenario Outline: Create new expense
    When I navigate to Clients page
    And I delete all clients
    And I navigate to CashBox page
    Then Add New Expense button should contain text "Нов разход"
    When I create new expense with value "<value>" and title "<title>" and firmName "<firmName>" and category "<category>" and notes "<notes>"
    Then item message with text should be displayed "<addSuccess>"
    When I delete all expenses
    Then item message with text should be displayed "<deleteSuccess>"
    Examples:
      | value  | title       | firmName          | notes    | category | addSuccess                       | deleteSuccess                            |
      | 200    | Coffee      | Coffee Shop LTD   | blank    | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |
      | 240.23 | Rent        | Rent Property LTD | My notes | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |
      | 121212 | Electricity | Ginka             | Geez     | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |


  Scenario Outline: Create new income
    When I navigate to CashBox page
    Then Add New Expense button should contain text "Нов разход"
    When I create new income with value "<value>" and title "<title>" and firmName "<firmName>" and category "<category>" and notes "<notes>"
    Then item message with text should be displayed "<addSuccess>"
    When I delete all expenses
    Then item message with text should be displayed "<deleteSuccess>"
    Examples:
      | value  | title       | firmName     | notes    | category | addSuccess                       | deleteSuccess                            |
      | 20     | Coffee      | Coffee Shop  | blank    | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |
      | 240.23 | Rent        | RentProperty | My notes | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |
      | 121212 | Eletrcicity | Ginka        | Geez     | testing  | Информацията е добавена успешно. | Избраните артикули бяха изтрити успешно. |
