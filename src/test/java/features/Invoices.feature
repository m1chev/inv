Feature: Invoices tests http://inv.bg/clients/manage


  Scenario: Open invoices page
    Given user is logged in the system
    Then user panel should contain text "karamfilovs@gmail.com"
    When I navigate to Invoices Page
    Then Add New Invoice button should contain text "Нова фактура"
    When I clean all invoices on API level
