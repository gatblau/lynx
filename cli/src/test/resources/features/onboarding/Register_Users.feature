Feature: Register Users
  As an Administrator
  I want to register one or more people
  So that they can be notified and can activate their accounts

  # Success Scenario

  Scenario: Registers a list of Users
    Given a list of details of users to be registered is known
    Given the users are not already registered
    Given a registration email template to notify the users is defined
    When a request to register the users is made
    Then the users are registered
    Then an activation code for each of the users has been created
    Then the users have been notified by email

  # Handling technical errors

  Scenario: Gives a BAD REQUEST response if a Request is made with an invalid payload
    Given an incorrect payload is created
    When a request to register the users is made
    Then a bad request response is received

  # Handling validation errors

  Scenario: Gives a BAD REQUEST response if the request payload has missing data
    Given a payload with missing information is created
    When a request to register the users is made
    Then a bad request response is received

  # Handling business errors

  Scenario: Gives a business error response if the users are already registered
    Given a list of details of users to be registered is known
    Given the users are already registered
    Given a registration email template to notify the users is defined
    When a request to register the users is made
    Then an error indicating the users are already registered is received

  Scenario: Gives a business error response if an email template is not defined
    Given a list of details of users to be registered is known
    Given the users are not already registered
    Given an email template to notify the users is not defined
    When a request to register the users is made
    Then an error indicating the email template is not defined is received