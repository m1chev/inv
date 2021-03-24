@test
Feature: Item tests https://testvame.inv.bg/home

 Background:
   Given user is logged in the system with selected language BG
   Then user should be able to see BG before the valid email in the panel

 Scenario: Add product in the inventory
   When I click on "Артикули" button from the navigation bar
   And I click on "+Нов артикул" button
   And I enter the text "кисело мляко" in the "Име на артикул" field
#   And I enter the text "2.50" in the "Единична цена" field
#   And I click on "Добави нов артикул" button
   Then I should be able to see a confirmation message "Артикулът е добавен успешно.\n"
#   When I click on "Артикули" button from the navigation bar
#   Then I should be able to see the newly added item with text "кисело мляко" under "Име" filed, text "2.50лв." under "Цена" filed

# Scenario: Add product in the inventory with blank "name"
#   When I click on "Артикули" button from the navigation bar
#   And I click on "+Нов артикул" button
#   And I click on "Добави нов артикул" button
#   Then I should see error message "Моля, въведете име/номенклатура на артикула."

# Scenario Outline: Successfully adding new incomes using parameters
#   When I click on "Каса" button from the navigation bar
#   And I click on "Нов приход" button
#   And I enter text in "Стойност:" "<amount>" and "Основание:" "<info>"
#   And I click on the button "Добави запис"
#   Then I should see successful message "Информацията е добавена успешно."
#   And I should see the income added on the list having "Стойност:" "<amount>" and "Основание:" "<"info">
#
#   Examples:
#   |description|amount|info|
#   |yogurt     |2.50  |кисело мляко|
#   |pork meat  |13.50 |свкнско месо|
#   |hamon      |15.99 |прошуто     |