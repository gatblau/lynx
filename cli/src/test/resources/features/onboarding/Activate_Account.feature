Feature: Activate Respondent Account

  Scenario: Activate Account
    Given the activation details are known
    Given the all required account details are specified
    When a request to activate the account is made
    Then the account is activated
    Then the account details are updated

  Scenario: Gives a BAD REQUEST response if a request is made with an invalid payload

  Scenario: Gives a BAD REQUEST response if the request payload has missing data

  Scenario: Gives a business error response if the account is are already active