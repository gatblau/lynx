Feature: Create Token

  Scenario: Create Token
    Given the user id is known
    Given the login date and time is known
    Given the user IP is known
    Given the user agent is known
    When the creation of an encrypted token is requested
    Then the token is created