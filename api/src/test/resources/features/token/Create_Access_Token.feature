Feature: Create Access Token
  Scenario: Create Access Token
    Given the user id is known
    Given the login date and time is known
    Given the user agent is known
    Given the user IP is known
    When the creation of an encrypted token is requested
    Then an encrypted token is created

