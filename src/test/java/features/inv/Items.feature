Feature: Items tests http://inv.bg/objects/manage

  Scenario: Open Items Page
    Given user is logged in the system
    When I navigate to Items Page
    Then Add New Item button should contain text:"Нов Артикул"


  Scenario Outline: Open Items Page
    Given user is logged in the system
    Then user panel should contain text:"karamfilovs@gmail.com"
    When I navigate to Items Page
    Then Add New Item button should contain text:"Нов Артикул"
    And I create new item with name:"<name>" and nameENG:"<nameENG>" and price:"<price>" and priceQuantity:"<priceQuantity>"
    Then item message with text should be displayed:"<addSuccess>"
    When I delete all items
    Then item message with text should be displayed:"<deleteSuccess>"
    Examples:
      | name             | nameENG        | price | priceQuantity | addSuccess              | deleteSuccess                           |
      | Кафе             | Coffee         | 10    | 1             | Артикулът е добавен успешно. | Избраните артикули бяха изтрити успешно. |
      | Джони Уокър      | Johny Walker   | 25.56 | 1.1           | Артикулът е добавен успешно. | Избраните артикули бяха изтрити успешно. |
      | Курс: Тестване 1 | Testing Course | 650   | 20            | Артикулът е добавен успешно. | Избраните артикули бяха изтрити успешно. |

