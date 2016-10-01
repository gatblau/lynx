Feature: Read Access Token
  Scenario: Read Access Token
    Given a valid token for a user is known
    When a request to read the token is made
    Then the token is valid
    Then the user in the token matches the user id
    Then the date in the token matches the login date
    Then the IP in the token matches the user IP
    Then the user agent in the token matches the user agent

