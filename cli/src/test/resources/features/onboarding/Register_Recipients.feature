Feature: Register Survey Recipients

  Scenario: Register Recipients
    Given a list of details of recipients to be registered is known
    When a request to register the recipients in the list is made
    Then the recipients are registered
    Then a registration code for each of the recipients has been created
    Then the recipients have been notified by email