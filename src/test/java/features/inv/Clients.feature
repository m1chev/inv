Feature: Clients tests http://inv.bg/clients/manage

  Scenario: Open items page
    Given user is logged in the system
    Then user panel should contain text:"karamfilovs@gmail.com"
    When I navigate to Clients Page
    Then Add New Client button should contain text:"Нов клиент"


  Scenario Outline: Add new client with valid data
    Given user is logged in the system
    Then user panel should contain text:"karamfilovs@gmail.com"
    When I navigate to Clients Page
    Then Add New Client button should contain text:"Нов клиент"
    When I create new client with name:"<name>" and vat:"<vat>" and address:"<address>" and town:"<town>"
    Then client message with text should be displayed:"<addSuccess>"
    When I delete all clients
    Then client message with text should be displayed:"<deleteSuccess>"
    Examples:
      | name             | vat        | address  | town    | addSuccess                  | deleteSuccess                           |
      | АЛ-ИМПОРТ ООД    | 123123122  | Ruski 20 | Plovdiv | Клиентът е добавен успешно. | Избраните клиенти бяха изтрити успешно. |
      | Pragmatic LTD      | 123        | 20 Руски    | Пловдив   | Клиентът е добавен успешно. | Избраните клиенти бяха изтрити успешно. |
      | Грабо ООД | 123123122e | ruski 20 ruski 20      | СОФИЯ     | Клиентът е добавен успешно. | Избраните клиенти бяха изтрити успешно. |

