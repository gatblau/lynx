Feature: Read Token

  Scenario: Read Token
    Given an encrypted token for a user is known
    Given the user id is known
    Given the login date and time is known
    Given the user IP is known
    Given the user agent is known
    When a request to read the token is made
    Then the token is read
    Then the user in the token matches the user id
    Then the date in the token matches the login date
    Then the IP in the token matches the user IP
    Then the user agent in the token matches the user agent