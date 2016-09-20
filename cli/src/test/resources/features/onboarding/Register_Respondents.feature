Feature: Register Survey Respondents
  As an Administrator
  I want to register one or more people
  So that they can be notified and can activate their accounts

  # Success Scenario

  Scenario: Registers a list of Respondents
    Given a list of details of respondents to be registered is known
    Given the respondents are not already registered
    Given a registration email template to notify the respondents is defined
    When a request to register the respondents is made
    Then the respondents are registered
    Then an activation code for each of the respondents has been created
    Then the respondents have been notified by email

  # Handling technical errors

  Scenario: Gives a BAD REQUEST response if a Request is made with an invalid payload
    Given an incorrect payload is created
    When a request to register the respondents is made
    Then a bad request response is received

  # Handling validation errors

  Scenario: Gives a BAD REQUEST response if the request payload has missing data
    Given a payload with missing information is created
    When a request to register the respondents is made
    Then a bad request response is received

  # Handling business errors

  Scenario: Gives a business error response if the respondents are already registered
    Given a list of details of respondents to be registered is known
    Given the respondents are already registered
    Given a registration email template to notify the respondents is defined
    When a request to register the respondents is made
    Then an error indicating the respondents are already registered is received

  Scenario: Gives a business error response if an email template is not defined
    Given a list of details of respondents to be registered is known
    Given the respondents are not already registered
    Given an email template to notify the respondents is not defined
    When a request to register the respondents is made
    Then an error indicating the email template is not defined is received