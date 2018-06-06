Feature: Invoices tests http://inv.bg/clients/manage

  Background:
    Given user is logged in the system
    Then user panel should contain text "karamfilovs@gmail.com"

  @test
  Scenario: Open invoices page
    When I navigate to Invoices page
    Then Add New Invoice button should contain text "Нова фактура"
    When I clean all invoices on API level
  # Add your steps here to finish the example
