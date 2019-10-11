@items
Feature: Items tests http://inv.bg/objects/manage

  Background:
    Given user is logged in the system
    Then user panel should contain text "karamfilovs@gmail.com"

  Scenario: Can navigate to items page
    When I navigate to Items page
    Then Add New Item button should contain text "Нов aртикул"
    When I clean all items on API level


  Scenario Outline: Can create new item with valid information
    When I navigate to Items page
    Then Add New Item button should contain text "Нов артикул"
    When I create new item with name "<name>" and nameENG "<nameENG>" and price "<price>" and priceQuantity "<priceQuantity>"
    Then item message with text should be displayed "Артикулът е добавен успешно."
    When I delete all items
    Then item message with text should be displayed "Избраните артикули бяха изтрити успешно."
    Examples:
      | name             | nameENG                     | price  | priceQuantity |
      | Кафе             | Coffee                      | 10                     | 1                      |
      | Джони Уокър      | Johny Walker                | 25.56                  | 1.1                    |
      | Курс: Тестване 1 | Testing Course              | 650                    | 20                     |
      | КАФЕСГОЛЕМИБУКВИ | COFFEEWITHCAPITALLETTERONLY | AAAAAA | AAAAAA        |


  Scenario: Can delete existing item
    When I clean all clients on API level
